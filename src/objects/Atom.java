package objects;

import math.*;
import java.util.Arrays;
import java.util.Random;

/**
 *  Class Atom :
 *  Stores a Coordinate representing an Atom object + helper methods for dealing with Atoms
 */
public class Atom {

    private final Coordinate location;
    private static final Random rand = new Random();

    /**
     * This constructor generates a new atom and takes a tuple (x,y,z)
     * @param x - is the x coordinate
     * @param y - is the y coordinate
     * @param z - is the z coordinate
     * @throws IllegalArgumentException if location is not present in box
     */
    public Atom(int x, int y, int z){location = new Coordinate(x,y,z);}

    /**
     * New constructor added after adaptation of the Coordinate class
     * @param location - Coordinate (x,y,z)
     */
    public Atom(Coordinate location){ this.location = location;}

    //getters
    public Coordinate getLocation() {
        return location;
    }

    /**
     * This method randomly generates an array of atoms
     * @param N - number of atoms to generate
     * @return an array of N randomly generated atoms
     */
    public static Atom[] generateAtoms(int N){

        Atom[] atoms = new Atom[N];
        int i = 0;
        while(i < N){
            try {
                int x = rand.nextInt(0, 9);
                int y = rand.nextInt(0, 9);
                int z = rand.nextInt(0, 9);
                Atom generatedAtom = new Atom(x,y,z);
                if(!containsAtom(atoms, generatedAtom.getLocation())){
                    atoms[i] = generatedAtom;
                    i++;
                }
            }
            catch(Exception ignored){}
        }
        return atoms;
    }

    /**
     * This method checks if an Atoms array contains an Atom at the specified Coordinate
     * @param atoms - array of Atoms
     * @param location - Coordinate to check
     * @return true  - if at least one Atom with the passed location is contained in the atoms array
     *         false - if no Atom with the passed location is contained in the atoms array
     */
    public static boolean containsAtom(Atom[] atoms, Coordinate location){
        if(atoms == null || location == null) throw new IllegalArgumentException("passed parameters cannot be null");
        for(Atom atom : atoms) {
            if (atom != null && atom.getLocation().equals(location)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Atom atom)) return false;
        return location.equals(atom.location);
    }

    @Override
    public String toString() {
        return "Atom(" + "x:" + (location.x()-4) + ", y:" + (location.y()-4) + ", z:" + (location.z()-4) + ')';
    }

}
