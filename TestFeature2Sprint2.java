import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestFeature2Sprint2 {
    /**
     * testing that each index that should be a sideHexagon is an instance of sideHexagon
     */
    @Test
    public void testSideImplentation(){
        Atom[] atoms  ={new Atom(4,4,4),new Atom(4,8,0),new Atom(4,0,8),new Atom(6,3,3),new Atom(3,6,3),new Atom(1,8,3)};
        Box box = new Box(atoms);
        Integer[] array = {0, 1, 2, 3, 4, 5, 10, 11, 17, 18, 25, 26, 34, 35, 42, 43, 49, 50, 55, 56, 57, 58, 59, 60};//store coordinates of all sideHexagons
        List<Integer> SideHexagonIndices = Arrays.asList(array);
        for(int i = 0; i < 60; i++){
            if (SideHexagonIndices.contains(i)){
                assertTrue("Hexagon at index "+ i +" should be a side hexagon", box.getHexagon(i) instanceof SideHexagon);
            }
            else{
                assertFalse("Hexagon at index " + i + " should be a normal hexagon", box.getHexagon(i) instanceof SideHexagon);
            }
        }
    }

    @Test
    public void testSpecificHexagonSideNumbers() {

        Atom[] atoms  ={new Atom(4,4,4),new Atom(4,8,0),new Atom(4,0,8),new Atom(6,3,3),new Atom(3,6,3),new Atom(1,8,3)};
        Box box = new Box(atoms);

        int[][] expectedZero = new int[][] {{54, Box.MOVE_DIAGONAL_DOWN_LEFT},{1, Box.MOVE_DIAGONAL_DOWN_RIGHT},{2, Box.MOVE_DIRECTLY_RIGHT}};
        int[][] expectedThirtyFour = new int[][] {{36, Box.MOVE_DIAGONAL_UP_LEFT}, {37, Box.MOVE_DIRECTLY_LEFT}, {38, Box.MOVE_DIAGONAL_DOWN_LEFT}};
        // Test specific hexagons
        assertHexagonSideNumbers(box, 0, expectedZero);
        assertHexagonSideNumbers(box, 34, expectedThirtyFour);
        // Add more tests for other specific hexagons
    }

    private void assertHexagonSideNumbers(Box box, int index, int[][] expectedSideNumbers) {
        Hexagon hexagon = box.getHexagon(index);
        System.out.println(hexagon);
        //assertTrue("Hexagon at index " + index + " should be an instance of SideHexagon", hexagon instanceof SideHexagon);
        int[][] actualSideNumbers = ((SideHexagon) hexagon).getSides();
        assertArrayEquals("Side numbers do not match for hexagon at index " + index, expectedSideNumbers, actualSideNumbers);
    }


}
