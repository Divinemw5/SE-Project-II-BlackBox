import java.util.Arrays;
import java.util.Random;

/**
 *  (Class Description)
 *
 */
public class Atom {
    //atom coordinates
    private final Coordinate location;
    private static final Random rand = new Random();

    /**
     * This constructor generates a new atom and takes a tuple (x,y,z) where:
     * @param x - is the x coordinate
     *   @param y - is the y coordinate
     * @param z - is the z coordinate
     */
    Atom(int x, int y, int z){
        location = new Coordinate(x,y,z);
    }

    /**
     * This method randomly generates an array of atoms, it takes :
     * @param n - number of atoms to generate
     * @return an array of n randomly generated atoms
     */
    public static Atom[] generateAtoms(int n){
        Atom[] atoms = new Atom[n];
        for(int i = 0; i < n; i++){
            int x; int y; int z;
            do{
                x = rand.nextInt(0,9);
                y = rand.nextInt(0,9);
                z = rand.nextInt(0,9);
            }while((x+y+z != 12) || containsAtom(atoms, new Coordinate(x,y,z)));
            atoms[i] = new Atom(x,y,z);
        }
        return atoms;
    }

    /*check if a location contains an atom*/
    public static boolean containsAtom(Atom[] atoms, Coordinate location){
        for(Atom atom : atoms) {
            if (atom != null && atom.getLocation().equals(location)){
                return true;
            }
        }
        return false;
    }

    /**
     * @return a string with stored values of (x,y,z) for an Atom
     */
    @Override
            public String toString() {
                return "Atom(" + "x:" + (location.getX()-4) + ", y:" + (location.getY()-4) + ", z:" + (location.getZ()-4) + ')';
            }

    public static void main(String[] args) {
        //Atom a = new Atom(8,0,4); //TESTING
        Atom[] arr = generateAtoms(6); //TESTING
        //System.out.println(a); //TESTING
        System.out.println(Arrays.deepToString(arr)); //TESTING
    }

    public Coordinate getLocation() {
        return location;
    }
}
