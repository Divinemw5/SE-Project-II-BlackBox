import org.junit.Test;
import static org.junit.Assert.*;
public class BarrierLevelTest {
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
    public void TestDeflectByTwoAxis()
    {
        //Hexagon hexagon;
        //hexagon = box.getHexagonByCoordinate(new Coordinate(5,5,2));
        // int b = hexagon.getBarrierValue();
        //     System.out.println(b);
        Ray ray = new Ray(41,box);
        assertEquals("Expected Ray to exit at 48", 48, ray.getExit());

    }
    @Test
    public void TestReflected() // Barreier Level 2 edge case
    {
        Ray ray = new Ray(35,box);
        assertEquals("Expected Ray to exit at 35", 35, ray.getExit());
    }
}
