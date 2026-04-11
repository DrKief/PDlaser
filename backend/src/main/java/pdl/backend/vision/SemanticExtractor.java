package pdl.backend.vision;

import ai.djl.huggingface.tokenizers.Encoding;
import ai.djl.huggingface.tokenizers.HuggingFaceTokenizer;
import ai.onnxruntime.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SemanticExtractor {

  private static OrtEnvironment env;
  private static OrtSession visionSession;
  private static OrtSession textSession;
  private static HuggingFaceTokenizer tokenizer;

  private static final int TARGET_RESOLUTION = 224;
  private static final int SIGLIP_DIMENSIONS = 768;

  private static final String MODELS_DIR =
    System.getenv("APP_MODELS_DIR") != null ? System.getenv("APP_MODELS_DIR") : "models";
  private static final String VISION_URL =
    "https://huggingface.co/onnx-community/siglip2-base-patch16-224-ONNX/resolve/main/onnx/vision_model.onnx";
  private static final String TEXT_URL =
    "https://huggingface.co/onnx-community/siglip2-base-patch16-224-ONNX/resolve/main/onnx/text_model.onnx";
  private static final String TOKENIZER_URL =
    "https://huggingface.co/onnx-community/siglip2-base-patch16-224-ONNX/resolve/main/tokenizer.json";

  private static final Map<String, String> CUSTOM_TAGS = Map.ofEntries(
    Map.entry("cinematic_lighting", "a photo with moody cinematic lighting"),
    Map.entry("natural_light", "a photo with bright and airy natural light"),
    Map.entry("golden_hour", "a photo taken during golden hour"),
    Map.entry("black_and_white", "a high contrast black and white photo"),
    Map.entry("macro", "a macro photography shot"),
    Map.entry("landscape", "a wide angle landscape photograph"),
    Map.entry("architecture", "a minimalist architectural photo"),
    Map.entry("portrait", "a professional portrait of a person"),
    Map.entry("street_photography", "a candid street photography shot"),
    Map.entry("wedding", "a photo of a wedding"),
    Map.entry("wildlife", "a photo of wildlife"),
    Map.entry("food", "a photo of food"),
    Map.entry("vehicle", "a photo of a vehicle")
  );

  private static Map<String, float[]> cachedTextEmbeddings = new HashMap<>();

  private static File downloadIfMissing(String urlStr, String fileName) throws Exception {
    File dir = new File(MODELS_DIR);
    if (!dir.exists()) {
      dir.mkdirs();
    }
    File file = new File(dir, fileName);
    if (!file.exists()) {
      System.out.println(
        "Downloading " + fileName + " from HuggingFace (This may take a while, please wait...)"
      );
      URL url = URI.create(urlStr).toURL();
      try (InputStream in = url.openStream()) {
        Files.copy(in, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
      }
      System.out.println("Successfully downloaded: " + fileName);
    }
    return file;
  }

  static {
    try {
      env = OrtEnvironment.getEnvironment();

      File visionFile = downloadIfMissing(VISION_URL, "siglip2_vision.onnx");
      File textFile = downloadIfMissing(TEXT_URL, "siglip2_text.onnx");
      File tokenizerFile = downloadIfMissing(TOKENIZER_URL, "tokenizer.json");

      try (InputStream stream = new FileInputStream(visionFile)) {
        byte[] modelArray = stream.readAllBytes();
        visionSession = env.createSession(modelArray, new OrtSession.SessionOptions());
      }

      try (InputStream stream = new FileInputStream(textFile)) {
        byte[] modelArray = stream.readAllBytes();
        textSession = env.createSession(modelArray, new OrtSession.SessionOptions());
      }

      // Sanitize tokenizer.json to remove unsupported keys for older DJL versions
      try {
        String content = Files.readString(tokenizerFile.toPath());
        boolean changed = false;
        if (content.contains("\"fuse_unk\"")) {
          content = content.replaceAll("\"fuse_unk\":\\s*(true|false),?", "");
          changed = true;
        }
        if (content.contains("\"byte_fallback\"")) {
          content = content.replaceAll("\"byte_fallback\":\\s*(true|false),?", "");
          changed = true;
        }
        if (changed) {
          Files.writeString(tokenizerFile.toPath(), content);
          System.out.println("Sanitized tokenizer.json for compatibility.");
        }
      } catch (Exception e) {
        System.err.println("Failed to sanitize tokenizer.json: " + e.getMessage());
      }

      try (InputStream stream = new FileInputStream(tokenizerFile)) {
        tokenizer = HuggingFaceTokenizer.newInstance(stream, Map.of());
      }

      if (textSession != null && tokenizer != null) {
        System.out.println("Caching text embeddings for tags...");
        for (Map.Entry<String, String> entry : CUSTOM_TAGS.entrySet()) {
          cachedTextEmbeddings.put(entry.getKey(), extractTextFeature(entry.getValue()));
        }
      }
    } catch (Exception e) {
      System.err.println("CRITICAL: Failed to initialize SemanticExtractor.");
      e.printStackTrace();
    }
  }

  public static float[] extractSemanticFeatures(BufferedImage img) {
    if (visionSession == null) return new float[SIGLIP_DIMENSIONS];
    try {
      BufferedImage resized = FeatureExtractor.resizeImageLanczos3(
        img,
        TARGET_RESOLUTION,
        TARGET_RESOLUTION
      );
      float[][][][] inputTensor = new float[1][3][TARGET_RESOLUTION][TARGET_RESOLUTION];

      for (int y = 0; y < TARGET_RESOLUTION; y++) {
        for (int x = 0; x < TARGET_RESOLUTION; x++) {
          int rgb = resized.getRGB(x, y);
          float r = ((rgb >> 16) & 0xFF) / 255.0f;
          float g = ((rgb >> 8) & 0xFF) / 255.0f;
          float b = (rgb & 0xFF) / 255.0f;

          inputTensor[0][0][y][x] = (r * 2.0f) - 1.0f;
          inputTensor[0][1][y][x] = (g * 2.0f) - 1.0f;
          inputTensor[0][2][y][x] = (b * 2.0f) - 1.0f;
        }
      }

      try (
        OnnxTensor tensor = OnnxTensor.createTensor(env, inputTensor);
        OrtSession.Result result = visionSession.run(
          Collections.singletonMap("pixel_values", tensor)
        )
      ) {
        float[][] output = (float[][]) result.get("pooler_output").get().getValue();
        return normalizeL2(output[0]);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return new float[SIGLIP_DIMENSIONS];
    }
  }

  private static float[] extractTextFeature(String text) {
    if (textSession == null || tokenizer == null) return new float[SIGLIP_DIMENSIONS];
    try {
      Encoding encoding = tokenizer.encode(text);
      long[] ids = encoding.getIds();

      long[][] inputIds = new long[1][ids.length];
      System.arraycopy(ids, 0, inputIds[0], 0, ids.length);

      try (
        OnnxTensor tensor = OnnxTensor.createTensor(env, inputIds);
        OrtSession.Result result = textSession.run(Collections.singletonMap("input_ids", tensor))
      ) {
        float[][] output = (float[][]) result.get("pooler_output").get().getValue();
        return normalizeL2(output[0]);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return new float[SIGLIP_DIMENSIONS];
    }
  }

  public static List<String> getAutoTags(float[] imageVector) {
    List<String> detected = new ArrayList<>();
    if (cachedTextEmbeddings.isEmpty() || imageVector.length != SIGLIP_DIMENSIONS) return detected;

    List<Map.Entry<String, float[]>> entries = new ArrayList<>(cachedTextEmbeddings.entrySet());

    entries.sort((a, b) ->
      Float.compare(dotProduct(imageVector, b.getValue()), dotProduct(imageVector, a.getValue()))
    );

    float threshold = 0.2f;

    for (int i = 0; i < Math.min(3, entries.size()); i++) {
      Map.Entry<String, float[]> entry = entries.get(i);
      float score = dotProduct(imageVector, entry.getValue());

      if (score > threshold) {
        detected.add(entry.getKey());
      }
    }

    return detected;
  }

  private static float dotProduct(float[] a, float[] b) {
    float sum = 0;
    for (int i = 0; i < a.length; i++) sum += a[i] * b[i];
    return sum;
  }

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
