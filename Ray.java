/**
 * (class description)
 */
public class Ray {
    Coordinate currentPosition;
    Coordinate startingPosition;
    int movementDirection;
    int entry;
    int exit; //-1 if ray is absorbed

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
        calculateExitPosition(box);
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
                        startingPosition = box.getHexagon(i).getLocation();
                        currentPosition = box.getHexagon(i).getLocation();
                        movementDirection = side[1];
                        return;
                    }
                }
            }
        }
    }

    /**
     * calculate exit Coordinate (use to set exit)
     */
    private void calculateExitPosition(Box box){

        Hexagon currentHexagon = box.getHexagonByCoordinate(currentPosition);

        if(startingPosition == currentPosition) {
            /*check if starting position contains an atom (edge case : absorbed)*/
            if (currentHexagon.checkHasAtom()) {
                // absorbed
                exit = -1;
                return;
            }
            /*check if starting position has a barrier value > 0 AND ATOM NEXT TO ENTRY POSITION (edge case : reflected)*/
            if (currentHexagon.getBarrierValue() > 0){
                // reflected
                exit = entry;
                return;
            }
        }

        /*path movement*/
        do {
            /*check if current hexagon contains no barrier*/
            if (currentHexagon.getBarrierValue() == 0) {
                currentPosition = currentPosition.move(Box.directions[movementDirection]);
                //System.out.println(currentPosition);
            }
            /*check if current hexagon contains barrier = 1*/
            else if(currentHexagon.getBarrierValue()==1)
            {
                if(box.getHexagonByCoordinate(currentPosition.move(Box.directions[movementDirection])).checkHasAtom())
                {
                    exit=-1;
                    return;
                }
                else if(box.getHexagonByCoordinate(currentPosition.move(Box.directions[Math.floorMod(movementDirection+1,6)])).checkHasAtom())
                {
                    movementDirection = Math.floorMod(movementDirection-1,6);
                    currentPosition = currentPosition.move(Box.directions[movementDirection]);
                }
                else
                {
                    movementDirection =Math.floorMod(movementDirection+1,6);
                    currentPosition = currentPosition.move(Box.directions[movementDirection]);
                }
            }
            /*check if current hexagon contains barrier = 3*/
            else if(currentHexagon.getBarrierValue()==3){
                exit = entry;
                return;
            }
            /*check if current hexagon contains barrier = 2*/
            else if(currentHexagon.getBarrierValue()==2){
                if(box.getHexagonByCoordinate(currentPosition.move(Box.directions[Math.floorMod(movementDirection+1,6)])).checkHasAtom() &&
                        box.getHexagonByCoordinate(currentPosition.move(Box.directions[Math.floorMod(movementDirection-1,6)])).checkHasAtom()){
                    exit = entry;
                    return;
                }
                else if(box.getHexagonByCoordinate(currentPosition.move(Box.directions[Math.floorMod(movementDirection+1,6)])).checkHasAtom()){
                    movementDirection = Math.floorMod(movementDirection-2,6);
                    currentPosition = currentPosition.move(Box.directions[movementDirection]);

                }
                else{
                    movementDirection =Math.floorMod(movementDirection+2,6);
                    currentPosition = currentPosition.move(Box.directions[movementDirection]);
                }
            }
            currentHexagon = box.getHexagonByCoordinate(currentPosition); //set next hexagon after move
        } while(!((currentHexagon instanceof SideHexagon) && (((SideHexagon) currentHexagon).sidesContainDirection(Math.floorMod(movementDirection+3, 6)))));
        /*check if current hexagon is instance of side hexagon with exit side opposite to movement direction
         (set exit to side with direction opposite to current position) (default condition)*/
        setExit(((SideHexagon) currentHexagon).getSideWithDirection(Math.floorMod(movementDirection+3,6)));
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

    @Override
    public String toString() {
        return "Ray{" +
                "entry=" + entry +
                ", exit=" + exit +
                '}';
    }
}
