import TUI.Board;
import math.Coordinate;
import objects.Box;
import objects.Hexagon;
import objects.Atom;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestBarrierLevels {

    private Atom[] atoms;
    private Box box;

    @Before
    public void setUp() {
        // Initialize atoms in specific locations
        atoms = new Atom[] {
                new Atom(new Coordinate(3, 3, 6)),  // Assuming this location is valid and within the board
                new Atom(new Coordinate(2, 4, 6)),  // Adjacent to (3,3,6)
                new Atom(new Coordinate(1, 5, 6)),   // Adjacent to (2,4,6) and should influence (3,3,6) indirectly
                null,
                null,
                null
        };
        box = new Box(atoms);
        System.out.println(Board.getAtomizedBoard(atoms));
    }

    @Test
    public void testBarrierValues() {
        // Central hexagon should have atoms directly adjacent to it
        Hexagon centralHexagon = box.getHexagonByCoordinate(new Coordinate(3, 3, 6));
        assertEquals("Incorrect barrier value for central hexagon", 1, centralHexagon.getBarrierValue());

        // Hexagon adjacent to central should also have a high barrier value
        Hexagon adjacentHexagon = box.getHexagonByCoordinate(new Coordinate(2, 4, 6));
        assertEquals("Incorrect barrier value for adjacent hexagon", 2, adjacentHexagon.getBarrierValue());

        // Check a hexagon with no direct adjacent atoms
        Hexagon distantHexagon = box.getHexagonByCoordinate(new Coordinate(4, 4, 4));
        assertEquals("Barrier value should be 0 for isolated hexagon", 0, distantHexagon.getBarrierValue());
    }
}
