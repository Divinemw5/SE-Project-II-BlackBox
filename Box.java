import java.util.Arrays;

/**
 *  (Class Description)
 *
 */
public class Box {
    private Hexagon[][][] box;
    //static array to store hexagon movement directions (6 directions, each with 3 elements (x, y, z increment)), index with int constants
    public static int[][] directions = {
            {1,-1,0}, //MOVE DIRECTLY RIGHT
            {0,-1,1}, //MOVE DIAGONAL (DOWN, RIGHT)
            {-1,0,1}, //MOVE DIAGONAL (DOWN, LEFT)
            {-1,1,0}, //MOVE DIRECTLY LEFT
            {0,1,-1}, //MOVE DIAGONAL (UP, LEFT)
            {1,0,-1}, //MOVE DIAGONAL (UP, RIGHT)
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
        int i = 0; //FOR TESTING
        box = new Hexagon[9][9][9]; //set box size
        for(int x = 0; x < 9; x++){ //fill box with hexagons
            for(int y = 0; y < 9; y++){
                for(int z = 0; z < 9; z++){
                    if(x+y+z == 12){
                        i++; //FOR TESTING
                        //TODO check if corner hexagon, if yes generate new corner hexagon (atom, barrier value, sides x 3)
                        //TODO else check if side hexagon, if yes generate new side hexagon (atom, barrier value, sides x 2)
                        //TODO else must be regular hexagon, generate new hexagon (atom, barrier value)
                        System.out.println(i+": ("+(x-4)+","+(y-4)+","+(z-4)+")"); //FOR TESTING
                    }
                    else box[x][y][z] = null; //not a member of the board
                }
            }
        }
    }

    /**
     *
     * @return
     */
    @Override //TODO
    public String toString() {
        return super.toString();
    }

    public static void main(String[] args) {
        Box box1 = new Box(Atom.generateAtoms(6));
        //System.out.println(Arrays.deepToString(box1.box));
    }
}
