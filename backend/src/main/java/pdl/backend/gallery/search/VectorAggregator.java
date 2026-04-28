package pdl.backend.gallery.search;

import java.util.List;

public class VectorAggregator {

  private static final double EPSILON = 1e-10;

  public static float[] aggregate(List<float[]> vectors) {
    if (vectors == null || vectors.isEmpty()) {
      throw new IllegalArgumentException("Input vectors list cannot be empty");
    }

    if (vectors.size() == 1) {
      return normalize(vectors.get(0));
    }

    int dimensions = vectors.get(0).length;
    for (float[] v : vectors) {
      if (v.length != dimensions) {
        throw new IllegalArgumentException("All vectors must have the same dimensions");
      }
    }

    float[] center = computeCenter(vectors, dimensions);
    float[] weights = computeDimensionWeights(vectors, center);
    float[] weightedAverage = computeWeightedAverage(vectors, weights);
    return normalize(weightedAverage);
  }

  private static float[] computeCenter(List<float[]> vectors, int dimensions) {
    float[] center = new float[dimensions];
    int count = vectors.size();

    for (float[] v : vectors) {
      for (int i = 0; i < dimensions; i++) {
        center[i] += v[i];
      }
    }

    for (int i = 0; i < dimensions; i++) {
      center[i] /= count;
    }

    return center;
  }

  private static float[] computeDimensionWeights(List<float[]> vectors, float[] center) {
    int dimensions = center.length;
    int count = vectors.size();

    double totalStdDev = 0.0;
    double[] dimensionStdDevs = new double[dimensions];

    for (int d = 0; d < dimensions; d++) {
      double sumSquaredDiff = 0.0;
      for (float[] v : vectors) {
        double diff = v[d] - center[d];
        sumSquaredDiff += diff * diff;
      }
      double variance = sumSquaredDiff / count;
      double stdDev = Math.sqrt(variance);
      dimensionStdDevs[d] = stdDev;
      totalStdDev += stdDev;
    }

    float[] weights = new float[dimensions];
    if (totalStdDev < EPSILON) {
      for (int i = 0; i < dimensions; i++) {
        weights[i] = 1.0f / dimensions;
      }
      return weights;
    }

    double avgStdDev = totalStdDev / dimensions;

    for (int d = 0; d < dimensions; d++) {
      double weight = avgStdDev / (dimensionStdDevs[d] + EPSILON);
      weights[d] = (float) Math.sqrt(weight);
    }

    return weights;
  }

  private static float[] computeWeightedAverage(List<float[]> vectors, float[] weights) {
    int dimensions = weights.length;
    float[] result = new float[dimensions];
    int count = vectors.size();

    for (float[] v : vectors) {
      for (int d = 0; d < dimensions; d++) {
        result[d] += v[d] * weights[d];
      }
    }

    for (int d = 0; d < dimensions; d++) {
      result[d] /= count;
    }

    return result;
  }

  private static float[] normalize(float[] vector) {
    double sumSquares = 0.0;
    for (float v : vector) {
      sumSquares += v * v;
    }

    if (sumSquares < EPSILON) {
      return vector;
    }

    double magnitude = Math.sqrt(sumSquares);
    float[] normalized = new float[vector.length];
    for (int i = 0; i < vector.length; i++) {
      normalized[i] = (float) (vector[i] / magnitude);
    }

    return normalized;
  }
}