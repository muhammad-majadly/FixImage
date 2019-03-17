package images;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/** Represents an image that might include holes. */
public class Image implements Cloneable {

    public static final int HOLE_VALUE = -1;

    private float[][] imageMatrix;

    private final int width;
    private final int height;
    private final Connectivity connectivity;

    /** Please use one of the builder functions below to create new images. */
    private Image(int width, int height, Connectivity connectivity) {
        this.width = width;
        this.height = height;
        this.connectivity = connectivity;
        this.imageMatrix = new float[height][width];
    }

    /** Returns an image with all pixels set to black (color == 0.0f) */
    public static Image getBlackImage(int width, int height, Connectivity connectivity) {
        return new Image(width, height, connectivity);
    }

    /**
     * Builder function to build an image from the given matrix.
     *
     * <p>All rows in the given matrix must be of the same length.
     * This function will throw an exception if the given matrix has invalid size.
     */
    public static Image buildImageFromMatrix(float[][] matrix, Connectivity connectivity)
            throws InvalidColorException {
        validateMatrix(matrix);

        int height = matrix.length;
        int width = matrix[0].length;
        Image image = new Image(width, height, connectivity);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                image.setPixel(i, j, matrix[i][j]);
            }
        }
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public Image clone() {
        Image cloned;
        try {
            cloned = (Image) super.clone();
        } catch (CloneNotSupportedException e) {
            // Shouldn't happen!
            throw new RuntimeException(e);
        }

        cloned.imageMatrix = new float[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cloned.imageMatrix[i][j] = this.imageMatrix[i][j];
            }
        }

        return cloned;
    }

    public float colorAt(int row, int col) {
        return imageMatrix[row][col];
    }

    public Pixel pixelAt(int row, int col) {
        return new Pixel(row, col, imageMatrix[row][col]);
    }

    public void setPixel(int row, int col, float color) throws InvalidColorException {
        validateColor(color);
        imageMatrix[row][col] = color;
    }

    /** Returns a matrix  representation of the image **/
    public float[][] toMatrix() {
        return this.imageMatrix.clone();
    }

    /** Returns a list of hole pixels. */
    public List<Pixel> getHolePoints() {
        List<Pixel> HolePoints = new ArrayList<Pixel>();
        for(int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (imageMatrix[i][j] == HOLE_VALUE) {
                    HolePoints.add(pixelAt(i, j));
                }
            }
        }
        return HolePoints;
    }

    /** Returns a list of boundary pixels **/
    public List<Pixel> getBoundaryPoints() {
    	List<Pixel> boundaryPoints = new ArrayList<Pixel>();
        for(int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (isBoundary(i, j)) {
                    boundaryPoints.add(pixelAt(i, j));
                }
            }
        }
        return boundaryPoints;
    }
    	
    

    private boolean isBoundary(int i, int j) {
        if(isValidAndHole(i, j)) {
            return false;
        }

        boolean fourConnectivity = isValidAndHole(i+1, j) || isValidAndHole(i-1, j) ||
                isValidAndHole(i, j+1) || isValidAndHole(i, j-1);

        if (connectivity == Connectivity.FOUR) {
            return fourConnectivity;
        }

        if (connectivity == Connectivity.EIGHT) {
            return fourConnectivity || isValidAndHole(i+1, j+1) || isValidAndHole(i-1, j-1)
                    || isValidAndHole(i-1, j+1) || isValidAndHole(i+1, j-1);
        }

        return false;
    }


    /** Returns whether the given pixel is valid and represents a hole. */
    private boolean isValidAndHole(int i, int j) {
        return i < height && j < width && i >= 0 && j >= 0
                && imageMatrix[i][j] == HOLE_VALUE;
    }

    private static void validateColor(float color) throws InvalidColorException {
        if (color == HOLE_VALUE || (color >= 0 && color <= 1)) {
            return;
        }

        throw new InvalidColorException("Invalid Color: " + color);
    }

    private static void validateMatrix(float[][] matrix) {
        if (matrix.length == 0) {
            throw new IllegalArgumentException("Matrix has 0 rows.");
        }

        int rowLength = matrix[0].length;
        if (rowLength == 0) {
            throw new IllegalArgumentException("First row has 0 cells.");
        }

        for (float[] row : matrix) {
            if (row.length != rowLength) {
                throw new IllegalArgumentException("Rows have different sizes.");
            }
        }
    }
}
