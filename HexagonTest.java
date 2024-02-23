import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HexagonTest {
    @Test
    public void TestPlaceHexagon()
    {
        Box box = new Box(Atom.generateAtoms(6));
        Coordinate location = new Coordinate(4,8,0);
        Coordinate location2 = new Coordinate(4,4,4);
        Coordinate location3= new Coordinate(4,0,8);
        assertEquals(box.getIndexOf(location),0);
       assertEquals(box.getIndexOf(location2),30);
        assertEquals(box.getIndexOf(location3),60);
    }
    @Test

   public void TestPlaceAtom() {
        Atom[] atoms = Atom.generateAtoms(6);
        Box box = new Box(atoms);
        int index;
        for(Atom atom:atoms)
        {
            index = box.getIndexOf(atom.getLocation());
            assertTrue(box.getHexagon(index).checkHasAtom());

        }

    }



}
