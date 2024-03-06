import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestFeature3Sprint2 {
    @Test
    public void testTravelAcross(){
        Atom[] atoms  ={new Atom(4,4,4),new Atom(4,8,0),new Atom(4,0,8),new Atom(6,3,3),new Atom(3,6,3),new Atom(1,8,3)};
        Box box = new Box(atoms);
        Ray ray = new Ray(4, box);
    }
}
