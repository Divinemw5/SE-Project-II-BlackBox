import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestFeature3Sprint2 {
    @Test
    public void testTravelAcross(){
        Atom[] atoms  ={new Atom(-1,3,-2),new Atom(0,1,-1),new Atom(1,1,-1),new Atom(4,-3,-1),new Atom(-1,1,0),new Atom(-2,2,0)};
        Box box = new Box(atoms);
        Ray ray = new Ray(2, box);
        assertEquals(ray.getExit(), 45);
    }
}
