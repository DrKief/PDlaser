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
import boofcv.alg.color.impl.ImplColorLab;
import com.twelvemonkeys.image.ResampleOp;
import java.awt.image.BufferedImage;
import java.util.List;

public class ImageProcessing {

  public static BufferedImage resizeImageLanczos3(
    BufferedImage inputImage,
    int targetWidth,
    int targetHeight
  ) {
    ResampleOp resampleOp = new ResampleOp(targetWidth, targetHeight, ResampleOp.FILTER_LANCZOS);
    return resampleOp.filter(inputImage, null);
  }

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
      return new float[31];
    }

    int singleDescSize = descriptions.get(0).size();
    float[] globalHistogram = new float[singleDescSize];

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
  
  public static float[] extractCieLabHistogram(BufferedImage inputImage) {
    Planar<GrayF32> rgb = ConvertBufferedImage.convertFromPlanar(
      inputImage,
      null,
      true,
      GrayF32.class
    );
    Planar<GrayF32> lab = rgb.createSameShape();
    ImplColorLab.rgbToLab_F32(rgb, lab);

    int binsPerChannel = 8;
    float[] histogram3D = new float[binsPerChannel * binsPerChannel * binsPerChannel];

    for (int y = 0; y < lab.height; y++) {
      for (int x = 0; x < lab.width; x++) {
        float l = lab.getBand(0).get(x, y);
        float a = lab.getBand(1).get(x, y);
        float b = lab.getBand(2).get(x, y);

        int lIndex = (int) ((l / 100.0f) * binsPerChannel);
        int aIndex = (int) (((a + 128) / 256.0f) * binsPerChannel);
        int bIndex = (int) (((b + 128) / 256.0f) * binsPerChannel);

        if (lIndex >= binsPerChannel) lIndex = binsPerChannel - 1;
        if (aIndex >= binsPerChannel) aIndex = binsPerChannel - 1;
        if (bIndex >= binsPerChannel) bIndex = binsPerChannel - 1;

        histogram3D[lIndex * binsPerChannel * binsPerChannel + aIndex * binsPerChannel + bIndex]++;
      }
    }
    return normalizeL2(histogram3D);
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
