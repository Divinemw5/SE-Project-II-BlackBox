import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.*;
public class Sprint3Tests {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    // Box with a hexagon @----- with barrier level two
    Atom[] TestAtoms = {
            new Atom(4,6,2),//0,2,-2
            new Atom(4,5,3), //0,1,-1
            new Atom(4,4,4),//0,0,0
            new Atom(3,3,6),//-1,-1,2
            new Atom(3,1,8),
            new Atom(4,0,8)
    };
    Atom[] TestAtoms2 = {
            new Atom(4,6,2),//0,2,-2
            new Atom(4,5,3), //0,1,-1
            new Atom(4,4,4),//0,0,0
            new Atom(3,3,6),//-1,-1,2
            new Atom(3,4,5),//-1,0,1
            new Atom(4,0,8)
    };
    Box box = new Box(TestAtoms);
    Box box1 = new Box(TestAtoms2);

    @Test
    public void RayResponse(){
        Ray ray = new Ray(35,box1);
        Util.printRayResponse(ray);
        assertEquals("Ray was reflected back to side " + ray.getEntry() + "\n", outContent.toString());

    }
    @Test
    public void PlaceAtoms()
    {
        Box emptyBox = new Box(new Atom[] {null});
        Atom[] atoms = new Atom[]{Util.getAtom(6,1, emptyBox), Util.getAtom(51,46,emptyBox), Util.getAtom(37,42,emptyBox), Util.getAtom(10,30,emptyBox), Util.getAtom(26,25,emptyBox), Util.getAtom(19,7, emptyBox)};
        System.out.println(Util.getAtom(6,1,emptyBox).toString());
        Box box1 = new Box(atoms);
        Hexagon hex = box1.getHexagonByCoordinate(new Coordinate(4,6,2));
        Hexagon hex1 = box1.getHexagonByCoordinate(new Coordinate(6,4,2));

        assertTrue(hex.checkHasAtom());
        assertTrue(hex1.checkHasAtom());

       //box1.getHexagonByCoordinate(Coordinate());
    }


}
