import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import objects.*;

@RunWith(Parameterized.class)
public class TestAtomPlacement {
    private Atom[] testAtoms;
    private Box testBox;

    // Constructor that takes an array of atoms as parameter
    public TestAtomPlacement(Atom[] atoms) {
        this.testAtoms = atoms;
    }

    // Creates test data
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { new Atom[] {
                        new Atom(4,6,2), // 0,2,-2
                        new Atom(4,5,3), // 0,1,-1
                        new Atom(4,4,4), // 0,0,0
                        new Atom(3,3,6), // -1,-1,2
                        new Atom(3,1,8),
                        new Atom(4,0,8)
                }},
                { new Atom[] {
                        new Atom(3,7,2), // 1,2,-3
                        new Atom(5,3,4), // 2,-1,1
                        new Atom(4,4,4), // 0,0,0
                        new Atom(3,6,3), // 1,1,-2
                        new Atom(3,2,7),
                        new Atom(4,1,7)
                }},
        });
    }

    @Before
    public void initialize() {
        testBox = new Box(testAtoms); // Initialize Box with test atoms
    }

    @Test
    public void testPrePlacedAtoms() {
        for (Atom atom : testAtoms) {
            int index = testBox.getIndexOf(atom.getLocation());
            assertTrue("Atom should be correctly placed at the initialized location",
                    testBox.getHexagon(index).checkHasAtom());
        }
    }

}
