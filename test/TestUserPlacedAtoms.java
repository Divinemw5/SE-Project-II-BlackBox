import TUI.Util;
import math.Coordinate;
import objects.Atom;
import objects.Box;
import objects.Hexagon;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

public class TestUserPlacedAtoms {
    private Box box1;

    @Before
    public void setUp() {
        Atom[] atoms = new Atom[] {
                Util.getAtom(6,1), // Suppose this creates an Atom at coordinate (4,6,2)
                Util.getAtom(51,46), // Suppose this creates an Atom at coordinate (6,4,2)
                Util.getAtom(37,42),
                Util.getAtom(10,30),
                Util.getAtom(26,25),
                Util.getAtom(19,7)
        };
        box1 = new Box(atoms);
    }

    @Test
    public void testAtomIsCorrectlyPlacedAtCoordinate46_2() {
        Hexagon hex = box1.getHexagonByCoordinate(new Coordinate(4,6,2));
        assertNotNull("Hexagon should not be null", hex);
        assertTrue("Hexagon at (4,6,2) should have an atom", hex.checkHasAtom());
    }

    @Test
    public void testAtomIsCorrectlyPlacedAtCoordinate64_2() {
        Hexagon hex1 = box1.getHexagonByCoordinate(new Coordinate(6,4,2));
        assertNotNull("Hexagon should not be null", hex1);
        assertTrue("Hexagon at (6,4,2) should have an atom", hex1.checkHasAtom());
    }

    //test initialising more than maximum atoms
    /*
    @Test(expected = IllegalArgumentException.class)
    public void testAddingSeventhAtomThrowsException() {
        // Trying to add a seventh Atom
        box1.atom(new Atom(5, 4, 3)); // This should throw IllegalArgumentException
    }

     */
}
