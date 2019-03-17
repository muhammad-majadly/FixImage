package images;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ImageTest {

    private static final float EPSILON_DELTA = 0.000000001f;

    @Test
    public void pixelAt() throws Exception {
        float matrix[][] = {
                {0.5f, 0.2f, 0.2f, 0.5f},
                {0.3f, -1f, -1f,0.5f}
        };

        Image image = Image.buildImageFromMatrix(matrix, Connectivity.FOUR);

        Pixel pixel = image.pixelAt(1, 2);

        assertEquals(pixel.x(), 1);
        assertEquals(pixel.y(), 2);
        assertEquals(pixel.color(), -1f, EPSILON_DELTA);
    }

    @Test
    public void colorAt() throws Exception {
        float matrix[][] = {
                {0.5f, 0.2f, 0.2f, 0.5f},
                {0.3f, -1f, -1f,0.5f}
        };

        Image image = Image.buildImageFromMatrix(matrix, Connectivity.FOUR);

        assertEquals(image.colorAt(1, 2), -1f, EPSILON_DELTA);
    }

    @Test
    public void setPixel() throws Exception {
        float matrix[][] = {
                {0.5f, 0.2f, 0.2f, 0.5f},
                {0.3f, -1f, -1f,0.5f}
        };

        Image image = Image.buildImageFromMatrix(matrix, Connectivity.FOUR);

        assertEquals(image.colorAt(1, 2), -1f, EPSILON_DELTA);

        image.setPixel(1, 2, 0.4f);
        assertEquals(image.colorAt(1, 2), 0.4f, EPSILON_DELTA);
    }

    @Test
    public void getHolePoints() throws Exception {
        float matrix[][] = {
                {0.5f, 0.2f, 0.2f,0.5f},
                {0.3f, -1f, -1f,0.5f},
                {0.3f, -1f, -1f,0.5f},
                {0.5f, 0.6f, 0.6f,0.5f},
                {0.8f, 0.9f, 0.9f,0.9f}
        };

        Image image = Image.buildImageFromMatrix(matrix, Connectivity.FOUR);
        List<Pixel> holePoints = image.getHolePoints();
        List<Pixel> expectedHOlePoints = Arrays.asList(
                image.pixelAt(1, 1),
                image.pixelAt(1, 2),
                image.pixelAt(2, 1),
                image.pixelAt(2, 2));

        assertEqualPixels(holePoints, expectedHOlePoints);
    }

    @Test
    public void getHolePoints_containsEdges() throws Exception {
        float matrix[][] = {
                {0.5f, 0.2f, 0.2f,0.5f},
                {0.3f, -1f, -1f, -1f},
                {-1f, -1f, -1f,0.5f},
                {0.5f, 0.6f, 0.6f,0.5f},
                {0.8f, 0.9f, 0.9f,0.9f}
        };

        Image image = Image.buildImageFromMatrix(matrix, Connectivity.FOUR);
        List<Pixel> holePoints = image.getHolePoints();
        List<Pixel> expectedHOlePoints = Arrays.asList(
                image.pixelAt(1, 1),
                image.pixelAt(1, 2),
                image.pixelAt(1, 3),
                image.pixelAt(2, 1),
                image.pixelAt(2, 2),
                image.pixelAt(2, 0));

        assertEqualPixels(holePoints, expectedHOlePoints);
    }

    @Test
    public void getBoundaryPoints_fourConnectivity() throws Exception {
        float matrix[][] = {
                {0.5f, 0.2f, 0.2f,0.5f},
                {0.3f, -1f, -1f,0.5f},
                {0.3f, -1f, -1f,0.5f},
                {0.5f, 0.6f, 0.6f,0.5f},
                {0.8f, 0.9f, 0.9f,0.9f}
        };

        Image image = Image.buildImageFromMatrix(matrix, Connectivity.FOUR);
        List<Pixel> boundaryPoints = image.getBoundaryPoints();
        List<Pixel> expectedHOlePoints = Arrays.asList(
                image.pixelAt(0, 1),
                image.pixelAt(0, 2),
                image.pixelAt(1, 0),
                image.pixelAt(1, 3),
                image.pixelAt(2, 0),
                image.pixelAt(2, 3),
                image.pixelAt(3, 1),
                image.pixelAt(3, 2));

        assertEqualPixels(boundaryPoints, expectedHOlePoints);
    }

    @Test
    public void getBoundaryPoints__fourConnectivity_containsEdges() throws Exception {
        float matrix[][] = {
                {0.5f, 0.2f, 0.2f,0.5f},
                {0.3f, -1f, -1f, -1f},
                {-1f, -1f, -1f,0.5f},
                {0.5f, 0.6f, 0.6f,0.5f},
                {0.8f, 0.9f, 0.9f,0.9f}
        };

        Image image = Image.buildImageFromMatrix(matrix, Connectivity.FOUR);
        List<Pixel> boundaryPoints = image.getBoundaryPoints();
        List<Pixel> expectedHOlePoints = Arrays.asList(
                image.pixelAt(0, 1),
                image.pixelAt(0, 2),
                image.pixelAt(0, 3),
                image.pixelAt(1, 0),
                image.pixelAt(2, 3),
                image.pixelAt(3, 0),
                image.pixelAt(3, 1),
                image.pixelAt(3, 2));

        assertEqualPixels(boundaryPoints, expectedHOlePoints);
    }

    @Test
    public void getBoundaryPoints_eightConnectivity() throws Exception {
        float matrix[][] = {
                {0.5f, 0.2f, 0.2f,0.5f},
                {0.3f, -1f, -1f,0.5f},
                {0.3f, -1f, -1f,0.5f},
                {0.5f, 0.6f, 0.6f,0.5f},
                {0.8f, 0.9f, 0.9f,0.9f}
        };

        Image image = Image.buildImageFromMatrix(matrix, Connectivity.EIGHT);
        List<Pixel> boundaryPoints = image.getBoundaryPoints();
        List<Pixel> expectedHOlePoints = Arrays.asList(
                image.pixelAt(0, 0),
                image.pixelAt(0, 1),
                image.pixelAt(0, 2),
                image.pixelAt(0, 3),
                image.pixelAt(1, 0),
                image.pixelAt(1, 3),
                image.pixelAt(2, 0),
                image.pixelAt(2, 3),
                image.pixelAt(3, 0),
                image.pixelAt(3, 1),
                image.pixelAt(3, 2),
                image.pixelAt(3, 3));

        assertEqualPixels(boundaryPoints, expectedHOlePoints);
    }

    @Test
    public void getBoundaryPoints__eightConnectivity_containsEdges() throws Exception {
        float matrix[][] = {
                {0.5f, 0.2f, 0.2f,0.5f},
                {0.3f, -1f, -1f, -1f},
                {-1f, -1f, -1f,0.5f},
                {0.5f, 0.6f, 0.6f,0.5f},
                {0.8f, 0.9f, 0.9f,0.9f}
        };

        Image image = Image.buildImageFromMatrix(matrix, Connectivity.EIGHT);
        List<Pixel> boundaryPoints = image.getBoundaryPoints();
        List<Pixel> expectedHOlePoints = Arrays.asList(
                image.pixelAt(0, 0),
                image.pixelAt(0, 1),
                image.pixelAt(0, 2),
                image.pixelAt(0, 3),
                image.pixelAt(1, 0),
                image.pixelAt(2, 3),
                image.pixelAt(3, 0),
                image.pixelAt(3, 1),
                image.pixelAt(3, 2),
                image.pixelAt(3, 3));

        assertEqualPixels(boundaryPoints, expectedHOlePoints);
    }

    private void assertEqualPixels(List<Pixel> list1,
                                   List<Pixel> list2) {
        assertEquals(list1.size(), list2.size());
        assertContains(list1, list2);
        assertContains(list2, list1);
    }

    /** Asserts that list1 contains list2 */
    private void assertContains(List<Pixel> list1,
                          List<Pixel> list2) {
        if (list2.size() == 0) {
            return;
        }
        for (Pixel p2 : list2) {
            boolean found = false;
            for (Pixel p1: list1) {
                if (equalPixels(p1, p2)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found);
        }
    }

    private boolean equalPixels(Pixel p1, Pixel p2) {
        return p1.x() == p2.x() &&
                p1.y() == p2.y() &&
                fuzzyEquals(p1.color(), p2.color());
    }

    @Test
    public void createImage_imageCreatedProperly() throws Exception {
        float matrix[][] = {
                {0.5f, 0.2f, 0.2f,0.5f},
                {0.3f, -1f, -1f,0.5f},
                {0.3f, -1f, -1f,0.5f},
                {0.5f, 0.6f, 0.6f,0.5f},
                {0.8f, 0.9f, 0.9f,0.9f}
        };

        Image image = Image.buildImageFromMatrix(matrix, Connectivity.FOUR);

        assertEquals(4 ,image.getWidth());
        assertEquals(5 ,image.getHeight());

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(matrix[i][j], image.pixelAt(i, j).color(),
                        EPSILON_DELTA);
            }
        }
    }

    @Test
    public void createImage_fixHoles_holesAreFixed() throws Exception {
        float matrix[][]={
                {0.5f, 0.2f, 0.2f,0.5f},
                {0.3f, -1f, -1f,0.5f},
                {0.3f, -1f, -1f,0.5f},
                {0.5f, 0.6f, 0.6f,0.5f}
        };

        float expectedResult[][]={
                {0.5f, 0.2f, 0.2f,0.5f},
                {0.3f, 0.3626811f, 0.38756034f,0.5f},
                {0.3f, 0.4124397f, 0.43731895f,0.5f},
                {0.5f, 0.6f, 0.6f,0.5f}
        };

        Image image = Image.buildImageFromMatrix(matrix, Connectivity.FOUR);
        Image resultImage = ImageProcessingUtils.fixHoles(image, new DefaultWeightCalculator(2, 0.1f));
        float[][] actualMatrix = resultImage.toMatrix();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(expectedResult[i][j], actualMatrix[i][j],
                        EPSILON_DELTA);
            }
        }
    }

    @Test
    public void createImage_clone_imageIsCloned() throws Exception {
        float matrix[][]={
                {0.5f, 0.2f, 0.2f,0.5f},
                {0.3f, -1f, -1f,0.5f},
                {0.3f, -1f, -1f,0.5f},
                {0.5f, 0.6f, 0.6f,0.5f}
        };

        Image image = Image.buildImageFromMatrix(matrix, Connectivity.FOUR);

        Image cloned = image.clone();
        assertEquals(cloned.getWidth(), image.getWidth());
        assertEquals(cloned.getHeight(), image.getWidth());


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(image.pixelAt(i, j).color(),
                        cloned.pixelAt(i, j).color(),
                        EPSILON_DELTA);
            }
        }

        image.setPixel(0, 0, 0.9999f);
        assertTrue(cloned.toMatrix() != image.toMatrix());
        assertFalse(fuzzyEquals(cloned.pixelAt(0, 0).color(), 0.9999f));
    }

    private static boolean fuzzyEquals(float a, float b) {
        return Math.abs(a - b) < EPSILON_DELTA;
    }
}