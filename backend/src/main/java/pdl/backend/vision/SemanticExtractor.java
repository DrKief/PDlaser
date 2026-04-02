package pdl.backend.vision;

import ai.onnxruntime.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Collections;

public class SemanticExtractor {
    private static OrtEnvironment env;
    private static OrtSession session;

    static {
        try {
            env = OrtEnvironment.getEnvironment();
            InputStream modelStream = SemanticExtractor.class.getResourceAsStream("/resnet50.onnx");
            if (modelStream != null) {
                byte[] modelArray = modelStream.readAllBytes();
                session = env.createSession(modelArray, new OrtSession.SessionOptions());
            }
        } catch (Exception e) {
            System.err.println("CRITICAL: Could not load ResNet ONNX model.");
            e.printStackTrace();
        }
    }

    public static float[] extractSemanticFeatures(BufferedImage img) {
        if (session == null) return new float[1000];

        try {
            BufferedImage resized = ImageFeatureExtractorLayer.resizeImageLanczos3(img, 224, 224);
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

            try (OnnxTensor tensor = OnnxTensor.createTensor(env, inputTensor)) {
                String inputName = session.getInputNames().iterator().next();
                try (OrtSession.Result result = session.run(Collections.singletonMap(inputName, tensor))) {
                    float[][] output = (float[][]) result.get(0).getValue();
                    return normalizeL2(output[0]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new float[1000];
        }
    }

    private static float[] normalizeL2(float[] vector) {
        double sumSq = 0;
        for (float v : vector) sumSq += v * v;
        float length = (float) Math.sqrt(sumSq);
        if (length > 1e-9) {
            for (int i = 0; i < vector.length; i++) vector[i] /= length;
        }
        return vector;
    }
}