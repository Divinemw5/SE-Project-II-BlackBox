public class Coordinate {

    private final int x;
    private final int y;
    private final int z;

    Coordinate(int x, int y, int z){
        if((x+y+z != 12) || (x > 8 || x < 0) || (y > 8 || y < 0) || (z > 8 || z < 0)) {
            throw new IllegalArgumentException();
        }
        this.x = x; this.y = y; this.z = z;
    }
    public int getX() {return x;}
    public int getY() {return y;}
    public int getZ() {return z;}

    /**
     * Function shifts stored coordinate by a vector (does not alter current coordinate) and takes :
     * @param vector - movement vector
     * @return new coordinate after being shifted by passed vector
     */
    public Coordinate move(Vector vector){
        return new Coordinate(x+ vector.getX_shift(), y+ vector.getY_shift(), z+ vector.getZ_shift());
    }

    /**
     * Function shifts stored coordinate by a vector (does not alter current coordinate) and takes :
     * @param vector - movement vector
     * @param n - number of times to move by vector
     * @return new coordinate after being shifted by passed vector
     */
    public Coordinate moveN(Vector vector, int n){
        return new Coordinate(x+ vector.getX_shift()*n, y+ vector.getY_shift()*n, z+ vector.getZ_shift()*n);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(this.getClass())){
            Coordinate c = (Coordinate) obj;
            return c.x==x && c.y == y && c.z == z;
        }
        else return false;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + (x-4) +
                ", y=" + (y-4) +
                ", z=" + (z-4) +
                '}';
    }
}
