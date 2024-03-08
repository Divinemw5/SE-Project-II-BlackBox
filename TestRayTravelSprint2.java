import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * A feature that allows the user to end a round or send a ray into the box
 * A feature that implements the edge positions around the board    DONE
 * A feature that implements a ray that encounters no atom and travels across the board     DONE
 * A feature that implements a ray that makes a direct hit and the setter responds with “Absorbed” - [when the ray is travelling in the same direction as the atom and encounters it]   DONE
 * A feature that implements a ray that hits a hexagon with a barrier level of 1 and is deflected by 1 axis (60 degrees)    DONE
 * A feature that implements a ray that hits a hexagon with barrier level 1 before it enters the box and the setter announces “Reflected”
 * A feature that implements a ray that hits an atom directly on entering the board is the setter announces “Absorbed”
 */

public class TestRayTravelSprint2 {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private Box box;


    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    @Before
    public void setUp() {
       /* Atom[] atoms = {
                new Atom(-1,3,-2),
                new Atom(0,1,-1),
                new Atom(1,1,-1),
                new Atom(4,-3,-1),
                new Atom(-1,1,0),
                new Atom(-2,2,0)
        };*/
        Atom[] alternativeAtoms = {
                new Atom(3,7,2),
                new Atom(4,5,3),
                new Atom(5,4,3),
                new Atom(8,1,3),
                new Atom(3,5,4),
                new Atom(2,6,4)
        };
        box = new Box(alternativeAtoms);
    }

    @Test
    public void testTravelAcross(){
        Ray ray = new Ray(2, box);
        assertEquals("Expected Ray to exit at 45", 45, ray.getExit());

        Ray ray1 = new Ray(16, box);
        assertEquals("Expected Ray to exit at 31", 31, ray1.getExit());
    }

    @Test
    public void testAbsorbed(){
        Ray ray = new Ray(10, box);
        Ray ray1 = new Ray(25, box);
        assertEquals("Expected Ray to be absorbed",-1, ray.getExit());
        assertEquals("Expected Ray to be absorbed",-1, ray1.getExit());

    }

    @Test
    public void testPrintRayResponseWithAbsorption() {
        // Assuming you have a constructor for Ray where you can set the exit to -1
        Ray absorbedRay = new Ray(10, box);

        Util.printRayResponse(absorbedRay);

        assertEquals("Ray was absorbed!!\n", outContent.toString());
    }

    @Test
    public void testLevel1(){
        Ray ray = new Ray(32, box);
        Ray ray1 = new Ray(49, box);
        assertEquals("Expected Ray to exit at 44",44, ray.getExit());
        assertEquals("Expected Ray to exit at 23",23, ray1.getExit());
    }

    @Test
    public void testInstantReflect(){
        Ray ray = new Ray(41, box);
        assertEquals("Expected Ray to be reflected",41, ray.getExit());
    }

    @Test
    public void testInstantAbsorbed(){
        Ray ray = new Ray(49, box);
        assertEquals("Expected Ray to be absorbed",39, ray.getExit());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
