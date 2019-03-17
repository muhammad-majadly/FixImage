package images;

/**
 * A default implementation for WeightCalculator:
 * weight = 1 / (euclidean_distance + epsilon).
 */
public class DefaultWeightCalculator implements WeightCalculator {

    private final int z;
    private final float epsilon;

    public DefaultWeightCalculator(int z, float epsilon) {
        this.z = z;
        this.epsilon = epsilon;
    }

    @Override
    public float calculate(Pixel pixel1, Pixel pixel2) {
        float distanceSquare = pow(pixel1.x() - pixel2.x())
                + pow(pixel1.y() - pixel2.y());

        float distance = (float) Math.sqrt(distanceSquare);

        return 1 / distance + epsilon;
    }

    private float pow(int num) {
        return (float) Math.pow(num, z);
    }
}
