package objects;

import math.*;
/**
 *  Class Box :
 *  Represents the foundational game logic for the game board + and contains additional utilities
 * necessary for interacting with the board
 */

public class Box
{
    private final Hexagon[] box;
    public static int BOX_SIZE = 61;

    /**
     * Vector[] directions :
     * Static array to store hexagon movement directions present in the hexagonal board,
     * Index with int constants below.
     */
    public static Vector[] directions = {
        new Vector(1, -1, 0), // MOVE DIRECTLY RIGHT (0)
        new Vector(0, -1, 1), // MOVE DIAGONAL (DOWN, RIGHT) (1)
        new Vector(-1, 0, 1), // MOVE DIAGONAL (DOWN, LEFT) (2)
        new Vector(-1, 1, 0), // MOVE DIRECTLY LEFT (3)
        new Vector(0, 1, -1), // MOVE DIAGONAL (UP, LEFT) (4)
        new Vector(1, 0, -1), // MOVE DIAGONAL (UP, RIGHT) (5)
    };
    public static int MOVE_DIRECTLY_RIGHT = 0;
    public static int MOVE_DIAGONAL_DOWN_RIGHT = 1;
    public static int MOVE_DIAGONAL_DOWN_LEFT = 2;
    public static int MOVE_DIRECTLY_LEFT = 3;
    public static int MOVE_DIAGONAL_UP_LEFT = 4;
    public static int MOVE_DIAGONAL_UP_RIGHT = 5;

    /**
     * This constructor generates a new Box and fills it with three kinds of Hexagons.
     * @param atoms - array of Atoms to be placed in the box.
     */
    public Box(Atom[] atoms)
    {
        if (atoms == null)
        {
            throw new IllegalArgumentException("atoms array cannot be null");
        }

        int index = 0;
        box = new Hexagon[BOX_SIZE];

        // fill box with hexagons (start from upper left and go right)
        for (int z = 0; z < 9; z++)
        {
            for (int x = 0; x < 9; x++)
            {
                for (int y = 0; y < 9; y++)
                {
                    // valid hexagons
                    if (x + y + z == 12)
                    {
                        // set variables
                        Coordinate location = new Coordinate(x, y, z);
                        boolean hasAtom = Atom.containsAtom(atoms, location);

                        // set barrier number (+1 for each atom contained in adjacent hexagons)
                        int barrierNumber = 0;
                        for (int w = 0; w < 6; w++)
                        {
                            // ignore exceptions thrown by illegal movement
                            try
                            {
                                Coordinate next = location.move(directions[w]);
                                if (Atom.containsAtom(atoms, next))
                                {
                                    barrierNumber++;
                                }
                            }
                            catch (IllegalArgumentException ignored)
                            {
                            }
                        }
                        // use special class for SideHexagons in order to initializeSides()
                        box[index] = (x % 8 == 0 || y % 8 == 0 || z % 8 == 0)
                                         ? new SideHexagon(hasAtom, barrierNumber, location)
                                         : new Hexagon(hasAtom, barrierNumber, location);

                        index++; // next hexagon in box
                    }
                }
            }
        }
        // set special variables for SideHexagons
        initializeSides();
    }

    /**
     * Method initializes sides array for all SideHexagons with (2 or 3) tuples (entrySideNumber,
     * directionIndex)
     */
    private void initializeSides()
    {
        Coordinate currentLocation = box[0].getLocation(); // start at upper-left-most hexagon
        int movementDirection = MOVE_DIAGONAL_DOWN_LEFT; // start moving to the lower left (used to circle around board)

        int sideDirection = MOVE_DIAGONAL_DOWN_RIGHT;    // initial entry directionIndex (used for sides array)
        int sideNumber = 0;

        int[][] sides;
        // initialize sides[] for all 24 SideHexagons
        for (int i = 0; i < 24; i++)
        {
            // Set Corner Hexagons (3 tuples)
            if (i % 4 == 0 && getHexagonByCoordinate(currentLocation) instanceof SideHexagon)
            {
                // if it‘s the first hexagon set manually (exception)
                if (i == 0)
                {
                    sides = new int[][] {
                        {54, MOVE_DIAGONAL_DOWN_LEFT}, {1, MOVE_DIAGONAL_DOWN_RIGHT}, {2, MOVE_DIRECTLY_RIGHT}};
                    ((SideHexagon)getHexagonByCoordinate(currentLocation)).setSides(sides);
                }
                // else set automatically
                else
                {
                    sides = new int[][] {{sideNumber, Math.floorMod(sideDirection, 6)},
                                         {sideNumber + 1, Math.floorMod(sideDirection - 1, 6)},
                                         {sideNumber + 2, Math.floorMod(sideDirection - 2, 6)}};
                    ((SideHexagon)getHexagonByCoordinate(currentLocation)).setSides(sides);
                }
                sideNumber += 3; // increment current side index by 3 (3 sides depleted)
            }
            // Set Side Hexagons (2 tuples)
            else if (getHexagonByCoordinate(currentLocation) instanceof SideHexagon)
            {
                sides = new int[][] {{sideNumber, Math.floorMod(sideDirection, 6)},
                                     {sideNumber + 1, Math.floorMod(sideDirection - 1, 6)}};
                ((SideHexagon)getHexagonByCoordinate(currentLocation)).setSides(sides);
                sideNumber += 2; // increment current side index by 2 (2 sides depleted)
            }
            /* After every corner except the first, change movementDirection and initial entry
               sideDirection*/
            if (i % 4 == 0 && i != 0)
            {
                movementDirection--;
                sideDirection--;
            }
            // Next position (circle around edge)
            currentLocation = currentLocation.move(directions[Math.floorMod(movementDirection, 6)]);
        }
    }

    /**
     * Method gets index of Hexagon referenced by Coordinate in box[]
     * @param location - location of hexagon to get index of
     * @return index of hexagon referenced by location
     */
    public int getIndexOf(Coordinate location)
    {
        for (int i = 0; i < box.length; i++)
        {
            if (box[i].getLocation().equals(location))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * Method gets Hexagon referenced by passed index in box[]
     * @param index - index of hexagon to grab in box[]
     * @return - hexagon referenced by box[index] , or null if index out of bounds
     */
    public Hexagon getHexagon(int index)
    {
        for (int i = 0; i < box.length; i++)
        {
            if (index == i)
            {
                return box[i];
            }
        }
        return null;
    }

    /**
     * Method gets Hexagon referenced at location matching passed Coordinate
     * @param location - Coordinate (location) of Hexagon to grab
     * @return Hexagon matching Coordinate, or null if Hexagon is not in Box
     */
    public Hexagon getHexagonByCoordinate(Coordinate location)
    {
        for (Hexagon hexagon : box)
        {
            if (hexagon.getLocation().equals(location))
            {
                return hexagon;
            }
        }
        return null;
    }

    @Override public String toString()
    {
        StringBuilder str = new StringBuilder();
        int i = 1;
        for (Hexagon hexagon : box)
        {
            str.append(i).append(" :");
            str.append(hexagon).append("\n");
            i++;
        }
        return str.toString();
    }
}
