/** This class extends the class SideHexagon.java by providing an additional axis for entry from the
 *  outside of the box (marked by an additional numbered side). //*** CLASS MIGHT BE UNNECESSARY ***
 */
public class CornerHexagon extends SideHexagon{

    /**
     * This constructor generates a new Hexagon and takes :
     *
     * @param hasAtom      - If the hexagon contains an atom (true if yes, false if no)
     * @param barrierValue - The number of atom barriers overlapping on the hexagon
     * @param sides        - The numbers of the sides attached to the hexagon where
     *                     first index contains side number and the second index contains a direction constant (0-5)
     * @throws IllegalArgumentException if barrierValue is negative, or if number of sides passed is not 2 or 3
     */
    CornerHexagon(boolean hasAtom, int barrierValue, int[][] sides) {
        super(hasAtom, barrierValue, sides);
    }
}
