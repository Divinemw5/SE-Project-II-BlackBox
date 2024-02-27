import java.util.Arrays;

/**
 *  (Class Description)
 *
 */
public class Box {

    private final Hexagon[] box;
    //array that stores all the Hexagons on the board

    //static array to store hexagon movement directions (6 directions, each with 3 elements (x, y, z increment)), index with int constants
    public static Vector[] directions = {
            new Vector(1,-1,0), //MOVE DIRECTLY RIGHT
            new Vector(0,-1,1), //MOVE DIAGONAL (DOWN, RIGHT)
            new Vector(-1,0,1), //MOVE DIAGONAL (DOWN, LEFT)
            new Vector(-1,1,0), //MOVE DIRECTLY LEFT
            new Vector(0,1,-1), //MOVE DIAGONAL (UP, LEFT)
            new Vector(1,0,-1), //MOVE DIAGONAL (UP, RIGHT)
    };
    public static int MOVE_DIRECTLY_RIGHT = 0;
    public static int MOVE_DIAGONAL_DOWN_RIGHT = 1;
    public static int MOVE_DIAGONAL_DOWN_LEFT = 2;
    public static int MOVE_DIRECTLY_LEFT = 3;
    public static int MOVE_DIAGONAL_UP_LEFT = 4;

    public static int MOVE_DIAGONAL_UP_RIGHT = 5;

    /** This constructor generates a new Box and fills it with three kinds of Hexagons. Takes
     * @param atoms - An array of Atoms to be placed in the box.
     */
    Box(Atom[] atoms){
        int hexagon = 0;       //index box array
        box = new Hexagon[61]; //set box size (61 total hexagons)
        for(int z = 0; z < 9; z++){ //fill box with hexagons (start from upper left and go right)
            for(int x = 0; x < 9; x++){
                for(int y = 0; y < 9; y++){
                    if(x+y+z == 12){
                        //check if corner hexagon, if yes generate new corner hexagon (atom, barrier value, sides x 3)
                        if(x%4 == 0 && y%4 == 0 && z%4 == 0 && !(x == 4 && y == 4 & z == 4)){
                            Coordinate location = new Coordinate(x, y, z); //init variables
                            int barrierNumber = 0;
                            boolean hasAtom = Atom.containsAtom(atoms, location);

                            for(int w = 0; w < 6; w++){ //check barrier number (check if atoms contains surrounding hexagon coordinates)
                                try{
                                    Coordinate next = location.move(directions[w]);
                                    if(Atom.containsAtom(atoms, next)){
                                        barrierNumber++;
                                    }
                                } catch (IllegalArgumentException ignored){} //if movement out of bounds do nothing
                            }

                            box[hexagon] = new SideHexagon(hasAtom, barrierNumber, location, null);
                            //passing null as we will define the sides in a different method
                        }
                        //else check if non-corner side hexagon, if yes generate new side hexagon (atom, barrier value, sides x 2)
                        else if(x%8 == 0 || y%8 == 0 || z%8 == 0 ){
                            Coordinate location = new Coordinate(x, y, z); //init variables
                            int barrierNumber = 0;
                            boolean hasAtom = Atom.containsAtom(atoms, location);

                            for(int w = 0; w < 6; w++){ //check barrier number (check if atoms contains surrounding hexagon coordinates)
                                try{
                                    Coordinate next = location.move(directions[w]);
                                    if(Atom.containsAtom(atoms, next)){
                                        barrierNumber++;
                                    }
                                } catch (IllegalArgumentException ignored){} //if movement out of bounds do nothing
                            }

                            box[hexagon] = new SideHexagon(hasAtom, barrierNumber, location, null);
                            //
                        }
                        //else must be regular hexagon, generate new hexagon (atom, barrier value, location)
                        else{
                            Coordinate location = new Coordinate(x, y, z);
                            int barrierNumber = 0;
                            boolean hasAtom = Atom.containsAtom(atoms, location);
                            for(int w = 0; w < 6; w++){ //check barrier number (check if atoms contains surrounding hexagon coordinates)
                                if(Atom.containsAtom(atoms, location.move(directions[w]))){
                                    barrierNumber++;
                                }
                            }
                            box[hexagon] = new Hexagon(hasAtom, barrierNumber, location);
                        }
                        hexagon++; //next hexagon in box
                    }
                }
            }
        }
    }

    private void initializeSides(){
        int index = 1;
        
    }

    /**
     *
     * @param location - location of hexagon to get index of
     * @return index of hexagon referenced by location
     */

    public int getIndexOf(Coordinate location){
        for(int i = 0; i < box.length; i++){
            if(box[i].getLocation().equals(location)){
                return i;
            }
        }
        return -1; //hexagon not in box
    }

    /**
     *
     * @param index - index of hexagon to grab
     * @return - hexagon indexed by parameter
     */
    public Hexagon getHexagon(int index){
        for(int i = 0; i < box.length; i++){
            if(index == i){
                return box[i];
            }
        }
        return null; //index out of bounds
    }
    public Coordinate getHexagonByCoordinate(Coordinate location) {
        for (Hexagon hexagon : box) {
            if (hexagon.getLocation().equals(location)) {
                return hexagon.getLocation();
            }
        }
        return null; //hexagon not in box
    }
    /**
     *
     * @return a String representation of the box
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int i = 1;
        for(Hexagon hexagon : box){
            str.append(i).append(" :");
            str.append(hexagon).append("\n");
            i++;
        }
        return str.toString();
    }

    public static void main(String[] args) {
        Atom[] atoms = Atom.generateAtoms(6);
        Box box1 = new Box(atoms);
        System.out.println(Arrays.toString(atoms));
        System.out.println(box1);
        //System.out.println(Arrays.deepToString(box1.box));
    }
}
