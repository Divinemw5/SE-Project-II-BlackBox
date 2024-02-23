/**
 *  (Class Description)
 *
 */
public class Hexagon {
    private final boolean hasAtom;
    private final int barrierValue;
    private final Coordinate location;

    /**This constructor generates a new Hexagon and takes :
     * @param hasAtom - If the hexagon contains an atom (true if yes, false if no)
     * @param barrierValue - The number of atom barriers overlapping on the hexagon
     * @throws IllegalArgumentException if barrierValue is negative
     */
    Hexagon(boolean hasAtom, int barrierValue, Coordinate location){
        if(barrierValue < 0){
            throw new IllegalArgumentException();
        }
        this.hasAtom = hasAtom;
        this.barrierValue = barrierValue;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Hexagon{" +
                "hasAtom=" + hasAtom +
                ", barrierValue=" + barrierValue +
                ", location=" + location +
                '}';
    }

    public int getBarrierValue() {return barrierValue;}
    public boolean checkHasAtom() {return hasAtom;}
}
