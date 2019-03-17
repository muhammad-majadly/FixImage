package images;

/**
 * Represents the connectivity level for the image processor.
 */
public enum  Connectivity {
    /**
     * Only these adjacent cells are connected: up, down, right, and left.
     * C denotes the target cell, and Vs are the connected cells.
     * - - - - -
     * - - v - -
     * - v C v -
     * - - v - -
     * - - - - -
     */
    FOUR,

    /**
     * Only these adjacent cells are connected: up, down, right, and left.
     * C denotes the target cell, and Vs are the connected cells.
     * - - - - -
     * - v v v -
     * - v C v -
     * - v v v -
     * - - - - -
     */
    EIGHT
}
