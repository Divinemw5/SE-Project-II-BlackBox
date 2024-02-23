import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestAtom {
    @Test
    public void testGenerateRandomAtoms()
    {
        Atom[] atoms = Atom.generateAtoms(6);
        assertEquals(6,atoms.length);
        for(Atom atom:atoms)
        {
            assertNotNull(atom);
            assertEquals(Atom.class,atom.getClass()); // Check if the atom is an instance of Atom
        }

    }
}
