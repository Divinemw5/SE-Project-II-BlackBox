import objects.Atom;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestUserPlacedAtoms {
    private Atom[] userAtoms;

    @Before
    public void setUp() {
        // Initialize an empty array for every test to avoid shared state between tests
        userAtoms = new Atom[] {null, null, null, null, null, null};
    }

    @Test
    public void testAddAtomSuccessfully() {
        Atom atom = new Atom(4,6,2);  // Example atom
        BlackboxPlus.addAtom(userAtoms, atom);
        assertNotNull("Atom should be added successfully", userAtoms[0]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAtomBeyondCapacity() {
        for (int i = 0; i < 6; i++) {
            userAtoms[i] = new Atom(i, i+1, i+2);  // Fill the array
        }
        Atom newAtom = new Atom(7, 7, 7); // Seventh atom
        BlackboxPlus.addAtom(userAtoms, newAtom);  // Expect an exception here
    }

    @Test(expected = IllegalStateException.class)
    public void testAddDuplicateAtom() {
        Atom atom = new Atom(4,6,2);
        BlackboxPlus.addAtom(userAtoms, atom);
        BlackboxPlus.addAtom(userAtoms, atom);  // Trying to add the same atom again
    }

    @Test
    public void testRemoveAtomSuccessfully() {
        Atom atom = new Atom(4,6,2);
        BlackboxPlus.addAtom(userAtoms, atom);
        BlackboxPlus.removeAtom(userAtoms, atom);
        assertNull("Atom should be removed successfully", userAtoms[0]);
    }

    //tests nonexistent and 0 atoms left
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonexistentAtom() {
        Atom atom = new Atom(4,6,2);
        BlackboxPlus.removeAtom(userAtoms, atom);  // No atom to remove
    }
}
