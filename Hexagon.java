/**
 *  (Class Description)
 *
 */
public class Hexagon {
    private boolean hasAtom;
    private int barrierValue;

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

    @Override
    public String toString() {
        return "Hexagon{" +
                "hasAtom=" + hasAtom +
                ", barrierValue=" + barrierValue +
                '}';
    }

    public int getBarrierValue() {return barrierValue;}
    public boolean checkHasAtom() {return hasAtom;}
    public void setBarrierValue(int barrierValue) {this.barrierValue = barrierValue;}
    public void setHasAtom(boolean hasAtom) {this.hasAtom = hasAtom;}
}
