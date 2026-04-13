package pdl.backend.vision;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.image.BufferedImage;
import org.junit.jupiter.api.Test;

class FeatureExtractorTest {

  @Test
  void testResizeImageLanczos3() {
    BufferedImage img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
    BufferedImage resized = FeatureExtractor.resizeImageLanczos3(img, 256, 256);
    
    assertThat(resized.getWidth()).isEqualTo(256);
    assertThat(resized.getHeight()).isEqualTo(256);
  }

  @Test
  void testExtractGlobalHogReturnsCorrectDimensions() {
    BufferedImage img = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
    float[] hog = FeatureExtractor.extractGlobalHog(img);
    
    assertThat(hog).hasSize(81);
  }
}