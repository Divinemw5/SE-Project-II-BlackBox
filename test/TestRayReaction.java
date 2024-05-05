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
//test valid and invalid ray inputs
//also use Util.ray response to test string output for valid and invalid

public class TestRayReaction {
    private Box box;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @Before
    public void setUp(){
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

        Ray ray3 = new Ray(33, box);
        assertEquals("Expected objects.Ray to exit at 49", 14, ray3.getExit());
    }

    @Test
    public void testDirectHitAbsorbed(){
        Ray ray = new Ray(10, box);
        Ray ray1 = new Ray(25, box);
        Ray ray2 = new Ray(30, box);
        assertEquals("Expected objects.Ray to be absorbed",-1, ray.getExit());
        assertEquals("Expected objects.Ray to be absorbed",-1, ray1.getExit());
        assertEquals("Expected objects.Ray to be absorbed",-1, ray2.getExit());

    }

    @Test
    public void testDeflectionBy1Axis(){
        Ray ray = new Ray(32, box);
        Ray ray1 = new Ray(49, box);
        Ray ray2 = new Ray(21, box);

        assertEquals("Expected objects.Ray to exit at 44",44, ray.getExit());
        assertEquals("Expected objects.Ray to exit at 23",23, ray1.getExit());
        assertEquals("Expected objects.Ray to exit at 34",34, ray2.getExit());
    }

    @Test
    public void testReflectOnEntry(){
        Ray ray = new Ray(41, box);
        assertEquals("Expected objects.Ray to be reflected",41, ray.getExit());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testInvalidRayLowBound(){
        Ray ray = new Ray(-1, box);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testInvalidRayHighBound(){
        Ray ray = new Ray(55, box);
    }


}
