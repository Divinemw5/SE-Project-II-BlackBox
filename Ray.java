/**
 * (class description)
 */
public class Ray {
    Coordinate currentPosition;
    Vector movementDirection;
    int entry;
    int exit;

    /**
     *
     * @param entry - entry side number (for toString)
     * @param box - box ray is entering
     */
    Ray(int entry, Box box){
        if(entry < 1 || entry > 54){ //validation check
            throw new IllegalArgumentException("illegal ray entry");
        } else this.entry = entry;
        calculateEntryPosition(box);
    }

    /** Method sets movementDirection and currentPosition
     * @param box - box ray is entering
     */
    private void calculateEntryPosition(Box box){
        for(int i = 0; i < Box.BOX_MAX_SIZE; i++){
            if(box.getHexagon(i) instanceof SideHexagon){
                int[][] sides = ((SideHexagon) box.getHexagon(i)).getSides();
                for (int[] side : sides) {
                    if (side[0] == entry) {
                        currentPosition = box.getHexagon(i).getLocation();
                        movementDirection = Box.directions[side[1]];
                    }
                }
            }
        }
    }

    /**
     * calculate exit Coordinate (use to set exit)
     */
    private void calculateExitPosition(Box box){
        /*check if starting position contains an atom (edge case : absorbed)*/
        /*check if starting position has a barrier value > 0 (edge case : reflected)*/

        /*path movement*/

        /*check if current hexagon contains no barrier*/
        /*check if current hexagon contains barrier = 1*/
        /*check if current hexagon contains barrier = 2*/
        /*check if current hexagon contains barrier = 3*/

        /*check if current hexagon is instance of side hexagon (set exit to side with direction opposite to current position)*/
    }

    /**
     *
     * @param exit - exit side number
     */
    public void setExit(int exit) {
        if(exit < 1 || exit > 54){ //validation check
            throw new IllegalArgumentException("illegal ray exit");
        } else this.exit = exit;
    }

    public Coordinate getPosition() {
        return currentPosition;
    }
    public int getEntry(){
        return this.entry;
    }
    public int getExit(){
        return this.exit;
    }

}
