package TUI;

import math.Coordinate;
import math.Vector;
import objects.*;

import java.util.ArrayList;
import java.util.Scanner;

import static TUI.Colours.*;

public class Util {

    public static void printRayResponse(Ray ray){
        if (ray.getExit() == -1){
            System.out.print("Absorbed!\n");
        }
        else if(ray.getExit() == ray.getEntry()){
            System.out.print("Reflected!\n");
        }
        else{
            System.out.println("Ray exited at side "+ ray.getExit());
        }
    }

    /**
     * Function to return an atom based on intersecting side numbers ...
     * @param x - side x
     * @param y - side y
     * @return atom at intersection
     * @throws IllegalStateException if no intersection is found
     */
    public static Atom getAtom(int x, int y) throws IllegalStateException {

        Box emptyBox = new Box(new Atom[] {null});

        Ray rayX = new Ray(x, emptyBox);
        Ray rayY = new Ray(y, emptyBox);

        if(rayX.getExit() == rayY.getEntry()){
            throw new IllegalStateException("Please enter side numbers with a valid intersection (example : try 37, 42)");
        }

        ArrayList<Coordinate> travelledLocationsX = rayX.getTravelledLocations();
        ArrayList<Coordinate> travelledLocationsY = rayY.getTravelledLocations();

        Atom atom = null;

        for (Coordinate locationX : travelledLocationsX) {
            if (travelledLocationsY.contains(locationX)) {
                atom = new Atom(locationX);
                break;
            }
        }
        if(atom == null) throw new IllegalStateException("Please enter side numbers with a valid intersection (example : try 37, 42)");
        return atom;
    }


}
