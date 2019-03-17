package images;

/** Represents a single pixel inside an image. */
public class Pixel {

    private float color;
    private final int x;
    private final int y;

    public Pixel(int x, int y,float color) {
        this.x = x;
        this.y = y;
        this.color=color;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public float color() {
        return color;
    }
}