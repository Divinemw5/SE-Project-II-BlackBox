package math;

public class Vector {
    private final int x_shift;
    private final int y_shift;
    private final int z_shift;

    public Vector(int x_shift, int y_shift, int z_shift){
        //validation check
        this.x_shift = x_shift; this.y_shift = y_shift; this.z_shift = z_shift;
    }

    public int getZ_shift() {
        return z_shift;
    }

    public int getY_shift() {
        return y_shift;
    }

    public int getX_shift() {
        return x_shift;
    }
}
