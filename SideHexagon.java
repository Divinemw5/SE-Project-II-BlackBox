import java.util.Arrays;

/** This class extends the class Hexagon.java by providing an additional 2 axes for entry from the
 *  outside of the box (marked by two numbered sides).
 */
public class SideHexagon extends Hexagon{
    private int[][] sides;
    /**
     * This constructor generates a new Hexagon and takes :
     *
     * @param hasAtom      - If the hexagon contains an atom (true if yes, false if no)
     * @param barrierValue - The number of atom barriers overlapping on the hexagon
     *                        and the second index contains a direction constant (0-5)
     * @throws IllegalArgumentException if barrierValue is negative, or if number of sides passed is not 2 or 3
     */
    SideHexagon(boolean hasAtom, int barrierValue, Coordinate location) {
        super(hasAtom, barrierValue, location);
    }

    public void setSides(int[][] sides){
        /*input validation for sides array*/
        if(sides.length < 2 || sides.length > 3 ){
            throw new IllegalArgumentException();
        }
        else this.sides = sides;
    }
    public int[][] getSides() {

        return sides;
    }

    @Override
    public String toString() {
        return "SideHexagon{" +
                "sides=" + Arrays.deepToString(sides) +
                "} " + super.toString();
    }
}
