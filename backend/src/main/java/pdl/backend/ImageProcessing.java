package pdl.backend;

import boofcv.abst.feature.dense.DescribeImageDense;
import boofcv.alg.color.ColorHsv;
import boofcv.factory.feature.dense.ConfigDenseHoG;
import boofcv.factory.feature.dense.FactoryDescribeImageDense;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.feature.TupleDesc_F64;
import boofcv.struct.image.GrayF32;
import boofcv.struct.image.ImageType;
import boofcv.struct.image.Planar;
import java.awt.image.BufferedImage;
import java.util.List;

public class ImageProcessing {

  public static float[] extractGlobalHog(BufferedImage inputImage) {
    GrayF32 grayImage = ConvertBufferedImage.convertFrom(inputImage, (GrayF32) null);

    ConfigDenseHoG config = new ConfigDenseHoG();
    DescribeImageDense<GrayF32, TupleDesc_F64> describer = FactoryDescribeImageDense.hog(
      config,
      ImageType.single(GrayF32.class)
    );

    describer.process(grayImage);
    List<TupleDesc_F64> descriptions = describer.getDescriptions();

    if (descriptions.isEmpty()) {
      return new float[31]; // Default fallback size for UoCTTI HOG descriptor
    }

    int singleDescSize = descriptions.get(0).size();
    float[] globalHistogram = new float[singleDescSize];

    // Requirement: Custom logic to aggregate localized data into a single 1D histogram
    for (TupleDesc_F64 desc : descriptions) {
      for (int i = 0; i < singleDescSize; i++) {
        globalHistogram[i] += (float) desc.data[i];
      }
    }

    return normalizeL2(globalHistogram);
  }

  public static float[] extractHsvHistogram(BufferedImage inputImage) {
    Planar<GrayF32> rgb = ConvertBufferedImage.convertFromPlanar(
      inputImage,
      null,
      true,
      GrayF32.class
    );
    Planar<GrayF32> hsv = rgb.createSameShape();
    ColorHsv.rgbToHsv(rgb, hsv);

    int hueBins = 16;
    int satBins = 16;
    float[] histogram2D = new float[hueBins * satBins];

    for (int y = 0; y < hsv.height; y++) {
      for (int x = 0; x < hsv.width; x++) {
        float hue = hsv.getBand(0).get(x, y);
        float sat = hsv.getBand(1).get(x, y);

        int hIndex = (int) ((hue / (2 * Math.PI)) * hueBins);
        int sIndex = (int) (sat * satBins);

        if (hIndex >= hueBins) hIndex = hueBins - 1;
        if (sIndex >= satBins) sIndex = satBins - 1;

        histogram2D[hIndex * satBins + sIndex]++;
      }
    }

    return normalizeL2(histogram2D);
  }

  public static float[] extractRgbHistogram(BufferedImage inputImage) {
    Planar<GrayF32> rgb = ConvertBufferedImage.convertFromPlanar(
      inputImage,
      null,
      true,
      GrayF32.class
    );
    int bins = 8;
    float[] histogram3D = new float[bins * bins * bins];

    for (int y = 0; y < rgb.height; y++) {
      for (int x = 0; x < rgb.width; x++) {
        float r = rgb.getBand(0).get(x, y);
        float g = rgb.getBand(1).get(x, y);
        float b = rgb.getBand(2).get(x, y);

        int rIndex = (int) ((r / 256.0f) * bins);
        int gIndex = (int) ((g / 256.0f) * bins);
        int bIndex = (int) ((b / 256.0f) * bins);

        if (rIndex >= bins) rIndex = bins - 1;
        if (gIndex >= bins) gIndex = bins - 1;
        if (bIndex >= bins) bIndex = bins - 1;

        histogram3D[rIndex * bins * bins + gIndex * bins + bIndex]++;
      }
    }
    return normalizeL2(histogram3D);
  }

  private static float[] normalizeL2(float[] vector) {
    double sumSquares = 0;
    for (float v : vector) {
      sumSquares += v * v;
    }

    float length = (float) Math.sqrt(sumSquares);
    if (length > 0) {
      for (int i = 0; i < vector.length; i++) {
        vector[i] /= length;
      }
    }
    return vector;
  }
}
