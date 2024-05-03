package objects;

import math.*;

import java.util.ArrayList;

/**
 * The Ray Class includes the utilities for implementing the Ray Logic
 */
public class Ray {

    ArrayList<Coordinate> travelledLocations = new ArrayList<>();
    Coordinate currentPosition;
    Coordinate startingPosition;
    int movementDirection;
    int entry;
    int exit; //-1 if ray is absorbed

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

    public int getNumberOfMarkers(){
        if(getEntry() == getExit() || getExit() == -1){
            return 1;
        }
        else{
            return 2;
        }
    }

    public ArrayList<Coordinate> getTravelledLocations(){
        return travelledLocations;
    }

    /**
     *
     * @param entry - entry side number (for toString)
     * @param box - box ray is entering
     */
    public Ray(int entry, Box box){
        if(entry < 1 || entry > 54){ //validation check
            throw new IllegalArgumentException("Please enter a valid side number (1-54)");
        } else this.entry = entry;
        calculateEntryPosition(box);
        calculateExitPosition(box);
    }

    /** Method sets movementDirection and currentPosition
     * @param box - box ray is entering
     */
    private void calculateEntryPosition(Box box){
        for(int i = 0; i < Box.BOX_SIZE; i++){
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


    private boolean checkAtom(Box box, Coordinate position, Vector vector){
        try{
            Hexagon hex = box.getHexagonByCoordinate(position.move(vector));
            if(hex == null) return false;
            else return hex.checkHasAtom();
        }
        catch(IllegalArgumentException ex){
            return false;
        }
    }

    /**
     * calculate exit math.Coordinate (use to set exit)
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
            travelledLocations.add(currentPosition);
            /*check if current hexagon contains no barrier*/
            if (currentHexagon.getBarrierValue() == 0) {
                currentPosition = currentPosition.move(Box.directions[movementDirection]);
            }
            /*check if current hexagon contains barrier = 1*/
            else if(currentHexagon.getBarrierValue()==1)
            {
                if(checkAtom(box, currentPosition, Box.directions[movementDirection])) {
                    exit=-1;
                    return;
                }
                else if (checkAtom(box, currentPosition, Box.directions[Math.floorMod(movementDirection + 1, 6)])) {
                    movementDirection = Math.floorMod(movementDirection - 1, 6);
                    //try-catch handles illegal position movement (e.g. at edge of board)
                    try{
                        currentPosition = currentPosition.move(Box.directions[movementDirection]);
                    } catch (IllegalArgumentException ex){
                        break;
                    }
                }
                else
                {
                    movementDirection =Math.floorMod(movementDirection+1,6);
                    try{
                        currentPosition = currentPosition.move(Box.directions[movementDirection]);
                    } catch (IllegalArgumentException ex){
                        break;
                    }
                }
            }
            /*check if current hexagon contains barrier = 3*/
            else if(currentHexagon.getBarrierValue()==3){
                exit = entry;
                return;
            }
            /*check if current hexagon contains barrier = 2*/
            else if(currentHexagon.getBarrierValue()==2){
                if(checkAtom(box,currentPosition, Box.directions[Math.floorMod(movementDirection+1,6)]) &&
                        checkAtom(box,currentPosition, (Box.directions[Math.floorMod(movementDirection-1,6)]))){
                    exit = entry;
                    return;
                }
                else if(checkAtom(box, currentPosition, Box.directions[Math.floorMod(movementDirection+1,6)])){
                    movementDirection = Math.floorMod(movementDirection-2,6);
                    try {
                        currentPosition = currentPosition.move(Box.directions[movementDirection]);
                    } catch (IllegalArgumentException ex){
                        break;
                    }
                }
                else{
                    movementDirection =Math.floorMod(movementDirection+2,6);
                    try {
                        currentPosition = currentPosition.move(Box.directions[movementDirection]);
                    } catch (IllegalArgumentException ex){
                        break;
                    }
                }
            }
            currentHexagon = box.getHexagonByCoordinate(currentPosition); //set next hexagon after move
        } while(!((currentHexagon instanceof SideHexagon) && (((SideHexagon) currentHexagon).sidesContainDirection(Math.floorMod(movementDirection+3, 6)))));
        /*check if current hexagon is instance of side hexagon with exit side opposite to movement direction
         (set exit to side with direction opposite to current position) (default condition)*/
        travelledLocations.add(currentPosition); //add last position before exit to array list
        setExit(((SideHexagon) currentHexagon).getSideWithDirection(Math.floorMod(movementDirection+3,6)));
    }



    @Override
    public String toString() {
        return "objects.Ray{" +
                "entry=" + entry +
                ", exit=" + exit +
                '}';
    }
}
