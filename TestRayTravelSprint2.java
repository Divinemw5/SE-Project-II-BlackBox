import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestRayTravelSprint2 {
    private Box box;

    @Before
    public void setUp() {
        Atom[] atoms = {
                new Atom(-1,3,-2),
                new Atom(0,1,-1),
                new Atom(1,1,-1),
                new Atom(4,-3,-1),
                new Atom(-1,1,0),
                new Atom(-2,2,0)
        };
        box = new Box(atoms);
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
        Ray ray = new Ray();

    }

}
