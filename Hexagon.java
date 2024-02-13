/**
 *  (Class Description)
 *
 */
public class Hexagon {
    private boolean hasAtom = false;
    private int barrierValue = 0;

    /**This constructor generates a new Hexagon and takes :
     * @param hasAtom - If the hexagon contains an atom (true if yes, false if no)
     * @param barrierValue - The number of atom barriers overlapping on the hexagon
     * @throws IllegalArgumentException if barrierValue is negative
     */
    Hexagon(boolean hasAtom, int barrierValue){
        if(barrierValue < 0){
            throw new IllegalArgumentException();
        }
        this.hasAtom = hasAtom;
        this.barrierValue = barrierValue;
    }
}
