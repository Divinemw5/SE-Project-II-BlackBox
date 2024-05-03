

import TUI.Util;
import objects.Atom;
import objects.Box;
import objects.Ray;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/*see image 3 in BlackBoxPlus-Rules.pdf for board set-up*/

public class TestRayTravel {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private Box box;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    @Before
    public void setUp() {
       /* objects.Atom[] atoms = {
                new objects.Atom(-1,3,-2),
                new objects.Atom(0,1,-1),
                new objects.Atom(1,1,-1),
                new objects.Atom(4,-3,-1),
                new objects.Atom(-1,1,0),
                new objects.Atom(-2,2,0)
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
    public void testTravelEmptyPath(){
        Ray ray = new Ray(2, box);
        assertEquals("Expected objects.Ray to exit at 45", 45, ray.getExit());

        Ray ray1 = new Ray(16, box);
        assertEquals("Expected objects.Ray to exit at 31", 31, ray1.getExit());
    }

    @Test
    public void testDirectHitAbsorbed(){
        Ray ray = new Ray(10, box);
        Ray ray1 = new Ray(25, box);
        assertEquals("Expected objects.Ray to be absorbed",-1, ray.getExit());
        assertEquals("Expected objects.Ray to be absorbed",-1, ray1.getExit());
    }

    @Test
    public void testAnnouncementAbsorption() {
        // Assuming you have a constructor for objects.Ray where you can set the exit to -1
        Ray absorbedRay = new Ray(10, box);
        Util.printRayResponse(absorbedRay);
        assertTrue(outContent.toString().toLowerCase().contains("absorbed"));
        //assertEquals("objects.Ray was absorbed!!\n", outContent.toString());
    }

    @Test
    public void testDeflectionBy1Axis(){
        Ray ray = new Ray(32, box);
        Ray ray1 = new Ray(49, box);
        assertEquals("Expected objects.Ray to exit at 44",44, ray.getExit());
        assertEquals("Expected objects.Ray to exit at 23",23, ray1.getExit());
    }

    @Test
    public void testReflectOnEntry(){
        Ray ray = new Ray(41, box);
        assertEquals("Expected objects.Ray to be reflected",41, ray.getExit());
    }

    @Test
    public void testAnnouncementReflection(){
        Ray ray = new Ray(41, box);
        //implement check for announcement
        Util.printRayResponse(ray);
        assertTrue(outContent.toString().toLowerCase().contains("reflected"));
    }

    @Test
    public void testAbsorbedOnEntry(){
        Ray ray = new Ray(39, box);
        assertEquals("Expected objects.Ray to be absorbed",-1, ray.getExit());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
