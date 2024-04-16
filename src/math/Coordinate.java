package math;

/**
 * Allowed to store any (x,y,z) tuple present in board (see constructor)
 */
public record Coordinate(int x, int y, int z) {

    /**
     * @param x - x coordinate (between 0 and 8)
     * @param y - y coordinate (between 0 and 8)
     * @param z - z coordinate (between 0 and 8)
     * @throws IllegalArgumentException if x+y+z != 12
     */
    public Coordinate {
        if ((x + y + z != 12) || (x > 8 || x < 0) || (y > 8 || y < 0) || (z > 8 || z < 0)) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Function shifts stored coordinate by a vector (does not alter current coordinate) and takes :
     *
     * @param vector - movement vector
     * @return coordinate shifted by vector
     */
    public Coordinate move(Vector vector) {
        return new Coordinate(x + vector.x_shift(), y + vector.y_shift(), z + vector.z_shift());
    }

    /**
     * Function shifts stored coordinate by a vector (does not alter current coordinate) and takes :
     *
     * @param vector - movement vector
     * @param n      - number of times to move by vector
     * @return new coordinate after being shifted by passed vector
     */
    public Coordinate moveN(Vector vector, int n) {
        return new Coordinate(x + vector.x_shift() * n, y + vector.y_shift() * n, z + vector.z_shift() * n);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().equals(this.getClass())) {
            Coordinate c = (Coordinate) obj;
            return c.x == x && c.y == y && c.z == z;
        } else return false;
    }

    @Override
    public String toString() {
        return "math.Coordinate{" +
                "x=" + (x - 4) +
                ", y=" + (y - 4) +
                ", z=" + (z - 4) +
                '}';
    }
}
