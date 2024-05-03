package objects;

import java.util.Arrays;
import math.Coordinate;

/**
 * Class SideHexagon :
 * Represents a Hexagon on the edge of the board, with an added sides array and helper methods for access
 */
public class SideHexagon extends Hexagon
{
    private int[][] sides;

    /**
     * This constructor generates a new SideHexagon and takes :
     *
     * @param hasAtom      - If the hexagon contains an atom (true if yes, false if no)
     * @param barrierValue - The number of atom barriers overlapping on the hexagon
     *                        and the second index contains a direction constant (0-5)
     * @throws IllegalArgumentException if barrierValue is negative, or if number of sides passed is not 2 or 3
     */
    SideHexagon(boolean hasAtom, int barrierValue, Coordinate location)
    {
        super(hasAtom, barrierValue, location);
    }

    public int[][] getSides()
    {
        return sides;
    }

    public void setSides(int[][] sides)
    {
        if (sides.length < 2 || sides.length > 3)
            throw new IllegalArgumentException();
        else
            this.sides = sides;
    }

    /**
     * Method checks if the sides array contains a given movement direction and takes :
     * @param direction to check if contained in sides array
     * @return true (contained in array) or false (not contained in array)
     */
    public boolean sidesContainDirection(int direction)
    {
        return getSideWithDirection(direction) != -1;
    }

    /**
     * Method gets corresponding side number of a specific entry direction in the sides array and takes :
     * @param direction to get side with given direction
     * @return side number (contained in array) or -1 (not contained in array)
     */
    public int getSideWithDirection(int direction)
    {
        for (int[] side : sides)
        {
            if (side[1] == direction)
            {
                return side[0];
            }
        }
        return -1;
    }

    @Override public String toString()
    {
        return "SideHexagon" + super.toString().substring(7) + " {sides=" + Arrays.deepToString(sides) + "} ";
    }
}
