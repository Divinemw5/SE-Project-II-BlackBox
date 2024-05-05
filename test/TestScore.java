import objects.*;
import org.junit.Test;
import java.util.ArrayList;
import static junit.framework.TestCase.assertEquals;

public class TestScore {
    private Box box;

    ArrayList<Ray> ray = new ArrayList<>();
    Atom[] atoms = {
            new Atom(4, 4, 4),
            new Atom(4, 8, 0),
            new Atom(4, 0, 8),
            new Atom(6, 3, 3),
            new Atom(3, 6, 3),
            new Atom(1, 8, 3)};


    @Test
    public void ScoreTest1() {
        Atom[] userAtom = {
                new Atom(4, 4, 4),
                new Atom(3, 3, 6), // 3,3,6
                new Atom(8, 4, 0),//8,4,0
                new Atom(2, 4, 6), //2,4,6
                new Atom(3, 6, 3),
                new Atom(1, 8, 3)};
        box = new Box(atoms);

        Ray ray1 = new Ray(10, box);
        Ray ray2 = new Ray(25, box);
        Ray ray3 = new Ray(16, box);
        Ray ray4 = new Ray(2, box);
        ray.add(ray1);
        ray.add(ray2);
        ray.add(ray3);
        ray.add(ray4);
        assertEquals(20, BlackboxPlus.calculateRoundScore(ray, atoms, userAtom));


    }

    @Test
    public void ScoreTest2() // NO RIGHT
    {
        Atom[] userAtom = {
                new Atom(7, 3, 2),
                new Atom(5, 0, 7),
                new Atom(2, 4, 6),
                new Atom(2, 4, 6),
                new Atom(0, 7, 5),
                new Atom(2, 2, 8)};
        box = new Box(atoms);

        Ray ray1 = new Ray(10, box);
        Ray ray2 = new Ray(25, box);
        Ray ray3 = new Ray(16, box);
        Ray ray4 = new Ray(2, box);
        ray.add(ray1);
        ray.add(ray2);
        ray.add(ray3);
        ray.add(ray4);
        assertEquals(35, BlackboxPlus.calculateRoundScore(ray, atoms, userAtom));


    }

    @Test
    public void ScoreTest3() // ALL RIGHT
    {
        Atom[] userAtom = {new Atom(4, 4, 4),
                new Atom(4, 8, 0),
                new Atom(4, 0, 8),
                new Atom(6, 3, 3),
                new Atom(3, 6, 3),
                new Atom(1, 8, 3)};
        box = new Box(atoms);

        Ray ray1 = new Ray(10, box);
        Ray ray2 = new Ray(25, box);
        Ray ray3 = new Ray(16, box);
        Ray ray4 = new Ray(2, box);
        ray.add(ray1);
        ray.add(ray2);
        ray.add(ray3);
        ray.add(ray4);
        assertEquals(5, BlackboxPlus.calculateRoundScore(ray, atoms, userAtom));

    }

}

