package pdl.backend.vision;

import ai.onnxruntime.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SemanticExtractor {

  private static OrtEnvironment env;
  private static OrtSession session;
  private static List<String> labels = new ArrayList<>();

  static {
    try {
      env = OrtEnvironment.getEnvironment();

      // Load the ResNet50 model using try-with-resources
      try (
        InputStream modelStream = SemanticExtractor.class.getResourceAsStream("/resnet50.onnx")
      ) {
        if (modelStream != null) {
          byte[] modelArray = modelStream.readAllBytes();
          session = env.createSession(modelArray, new OrtSession.SessionOptions());
        }
      }

      // Load the 1,000 ImageNet class names
      try (
        InputStream labelStream = SemanticExtractor.class.getResourceAsStream(
          "/imagenet_classes.txt"
        )
      ) {
        if (labelStream != null) {
          try (BufferedReader reader = new BufferedReader(new InputStreamReader(labelStream))) {
            labels = reader
              .lines()
              .map(line -> line.split(",")[0].trim())
              .collect(Collectors.toList());
          }
        }
      }
    } catch (Exception e) {
      System.err.println("CRITICAL: Failed to initialize SemanticExtractor.");
      e.printStackTrace();
    }
  }

  /**
   * Extracts raw logits from the ResNet-50 model.
   * Note: Does not normalize here as classification needs raw scores;
   * use normalizeL2() separately for similarity comparisons.
   */
  public static float[] extractSemanticFeatures(BufferedImage img) {
    if (session == null) return new float[1000];
    try {
      BufferedImage resized = FeatureExtractor.resizeImageLanczos3(img, 224, 224);
      float[][][][] inputTensor = new float[1][3][224][224];

      for (int y = 0; y < 224; y++) {
        for (int x = 0; x < 224; x++) {
          int rgb = resized.getRGB(x, y);
          float r = ((rgb >> 16) & 0xFF) / 255.0f;
          float g = ((rgb >> 8) & 0xFF) / 255.0f;
          float b = (rgb & 0xFF) / 255.0f;

          // ImageNet normalization
          inputTensor[0][0][y][x] = (r - 0.485f) / 0.229f;
          inputTensor[0][1][y][x] = (g - 0.456f) / 0.224f;
          inputTensor[0][2][y][x] = (b - 0.406f) / 0.225f;
        }
      }

      try (
        OnnxTensor tensor = OnnxTensor.createTensor(env, inputTensor);
        OrtSession.Result result = session.run(
          Collections.singletonMap(session.getInputNames().iterator().next(), tensor)
        )
      ) {
        float[][] output = (float[][]) result.get(0).getValue();
        return output[0]; // Raw logits
      }
    } catch (Exception e) {
      return new float[1000];
    }
  }

  /**
   * Identifies the Top 3 most likely tags based on model output scores.
   * Only includes tags that meet the 8.0f sanity threshold.
   */
  public static List<String> getAutoTags(float[] output) {
    List<String> detected = new ArrayList<>();
    if (labels.isEmpty() || output.length == 0) return detected;

    // 1. Create a list of indices (0-999)
    List<Integer> indices = new ArrayList<>();
    for (int i = 0; i < output.length; i++) {
      indices.add(i);
    }

    // 2. Sort indices based on the score (Descending order)
    indices.sort((a, b) -> Float.compare(output[b], output[a]));

    // 3. Select the Top 3 highest scores that pass the sanity check
    float sanityThreshold = 8.0f;

    for (int i = 0; i < Math.min(3, indices.size()); i++) {
      int index = indices.get(i);
      float score = output[index];

      if (score > sanityThreshold) {
        detected.add(labels.get(index).toLowerCase().replace(" ", "_"));
      }
    }

    return detected;
  }

  /**
   * Normalizes a feature vector to unit length for similarity calculations.
   */
  public static float[] normalizeL2(float[] vector) {
    double sumSq = 0;
    for (float v : vector) sumSq += v * v;
    float length = (float) Math.sqrt(sumSq);
    if (length > 1e-9) {
      for (int i = 0; i < vector.length; i++) vector[i] /= length;
    }
    return vector;
  }
}
