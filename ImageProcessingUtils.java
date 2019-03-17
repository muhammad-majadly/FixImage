package images;

import java.util.List;

/**
 * Utility methods to process images.
 *
 * <p>Currently, this includes one utility method to fill holes inside images, but
 * it can be extended with more utils once needed.
 */
public class ImageProcessingUtils {

    /** Returns a new image fix fixed holes. */
    public static Image fixHoles(Image image,
                                 WeightCalculator weightCalculator) {
        Image resultImage = image.clone();

        List<Pixel> boundaryPoints = image.getBoundaryPoints();
        for (Pixel holePixel : image.getHolePoints()) {
            float color = fixSingleHolePixel(holePixel, boundaryPoints, weightCalculator);
            try {
                resultImage.setPixel(holePixel.x(), holePixel.y(), color);
            } catch (InvalidColorException e) {
                // THis can't happen!
                throw new RuntimeException(e);
            }
        }

        return resultImage;
    }

    private static float fixSingleHolePixel(
            Pixel pixel,
            List<Pixel> boundaryPoints,
            WeightCalculator weightCalculator) {
        float weidhtedColorSum = 0;
        float weightSum = 0;
        for(Pixel boundaryPoint : boundaryPoints) {
            float weight = weightCalculator.calculate(pixel, boundaryPoint);
            weightSum += weight;
            weidhtedColorSum += weight * boundaryPoint.color();
        }
        return weidhtedColorSum / weightSum;
    }
}