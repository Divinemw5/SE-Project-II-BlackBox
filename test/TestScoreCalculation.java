import objects.Atom;
import objects.Box;
import objects.Player;
import objects.Ray;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//simple enough
public class TestScoreCalculation {
    private Box box;

    ArrayList<Ray> ray = new ArrayList<>();
    Atom[] atoms = {
            new Atom(4, 4, 4),
            new Atom(4, 8, 0),
            new Atom(4, 0, 8),
            new Atom(6, 3, 3),
            new Atom(3, 6, 3),
            new Atom(1, 8, 3)};
    // Test with empty rays

    private Atom[] createInitialUserAtoms() {
        return new Atom[]{null, null, null, null, null, null};  // Initialize with nulls
    }

    // Test with all correct userAtoms
    @Test
    public void testAllCorrectUserAtoms() {
        box = new Box(atoms);
        ArrayList<Ray> rays = new ArrayList<>();
        rays.add(new Ray(3, box));
        Atom[] userAtoms = createInitialUserAtoms();
        for(int i = 0; i < 6; i++){
            BlackboxPlus.addAtom(userAtoms, atoms[i]);
        }
        assert BlackboxPlus.calculateRoundScore(rays, atoms, userAtoms) == 1; // all correct
    }

    // Test with mix of correct and incorrect userAtoms
    @Test
    public void testMixedUserAtoms() {
        box = new Box(atoms);
        ArrayList<Ray> rays = new ArrayList<>();
        rays.add(new Ray(1, box));
        Atom[] userAtoms = createInitialUserAtoms();
        BlackboxPlus.addAtom(userAtoms, atoms[4]);
        BlackboxPlus.addAtom(userAtoms, new Atom(3, 3, 6));
        assert BlackboxPlus.calculateRoundScore(rays, atoms, userAtoms) == 26; // 1 ray + 25 points for 5 incorrect placements
    }

    // Test with all userAtoms incorrect
    @Test
    public void testAllIncorrectUserAtoms() {
        box = new Box(atoms);
        ArrayList<Ray> rays = new ArrayList<>();
        rays.add(new Ray(2, box));
        Atom[] userAtoms = createInitialUserAtoms();
        BlackboxPlus.addAtom(userAtoms, new Atom(8, 2, 2));
        BlackboxPlus.addAtom(userAtoms, new Atom(8, 4, 0));
        assert BlackboxPlus.calculateRoundScore(rays, atoms, userAtoms) == 31; // 1 rays + 30 points for 6 incorrect atoms
    }

    // Test with excessively many rays
    @Test
    public void testManyRays() {
        box = new Box(atoms);
        ArrayList<Ray> rays = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            rays.add(new Ray(i + 1, box));
        }
        Atom[] userAtoms = createInitialUserAtoms();
        BlackboxPlus.addAtom(userAtoms, atoms[0]);
        assert BlackboxPlus.calculateRoundScore(rays, atoms, userAtoms) == 35; // 10 rays + 25 points for 5 incorrect atoms
    }

    // Test with empty userAtoms array but initialized
    @Test
    public void testEmptyUserAtomsArray() {
        box = new Box(atoms);
        ArrayList<Ray> rays = new ArrayList<>();
        rays.add(new Ray(1, box));
        Atom[] userAtoms = createInitialUserAtoms();  // Initially all null
        assert BlackboxPlus.calculateRoundScore(rays, atoms, userAtoms) == 31; // 1 ray + 30 points for 6 incorrect atoms
    }

    // Test for calculateRoundWinner
    @Test
    public void testCalculateRoundWinner() {
        Player[] players = {
                new Player("Alice"),
                new Player("Bob"),
                new Player("Charlie"),
                new Player("David")
        };

        players[0].setRoundScore(10);
        players[1].setRoundScore(5);
        players[2].setRoundScore(5);
        players[3].setRoundScore(20);

        ArrayList<Player> winners = BlackboxPlus.calculateRoundWinner(players);
        assertEquals(2, winners.size()); // Expecting two winners
        assertTrue(winners.contains(players[1])); // Bob should be a winner
        assertTrue(winners.contains(players[2])); // Charlie should be a winner
        assertEquals(1, players[1].getNumberOfWins()); // Both should have their win counts incremented
        assertEquals(1, players[2].getNumberOfWins());
    }

    // Test for calculateFinalScore
    @Test
    public void testCalculateFinalScore() {
        Player[] players = {
                new Player("Alice"),
                new Player("Bob"),
                new Player("Charlie"),
                new Player("David")
        };

        players[0].incrementNumberOfWins(); // Alice wins twice
        players[0].incrementNumberOfWins();
        players[1].incrementNumberOfWins(); // Bob wins once
        players[2].incrementNumberOfWins(); // Charlie wins twice
        players[2].incrementNumberOfWins();
        players[3].incrementNumberOfWins(); // David wins once

        ArrayList<Player> winners = BlackboxPlus.calculateFinalScore(players);
        assertEquals(2, winners.size()); // Expecting two final winners
        assertTrue(winners.contains(players[0])); // Alice should be a winner
        assertTrue(winners.contains(players[2])); // Charlie should also be a winner
    }




}
