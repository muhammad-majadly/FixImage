package images;

/**
 * Interface that defines the weight calculator for the image processor.
 *
 * <P>You can use DefaultWeightCalculator as the default implementation.
 */
public interface WeightCalculator {

    /** Calculates the weight between the given two pixels. */
    float calculate(Pixel pixel1, Pixel pixel2);
}
