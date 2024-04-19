import objects.Player;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class TestWinner {

    @Test
    public void FinalScoreTestPlayer1Win()//  Player one wins
    {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        player1.setNumberOfWins(5);
        player2.setNumberOfWins(4);

        Player[] players={player1,player2};
        ArrayList<Player> winninglist = BlackboxPlus.calculateFinalScore(players);
        assertEquals(winninglist.size(),1);
        assertEquals(winninglist.get(0).getName(),"player1");
    }
    @Test
    public void FinalScoreTestDraw()//  Draw
    {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        player1.setNumberOfWins(10);
        player2.setNumberOfWins(10);

        Player[] players={player1,player2};
        ArrayList<Player> winninglist = BlackboxPlus.calculateFinalScore(players);
        assertEquals(winninglist.size(),2);

    }
    @Test
    public void CalculateRoundWinnerPlayer1Win()
    {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        player1.setRoundScore(25);
        player2.setRoundScore(30);
        Player[] players = {player1,player2};
        ArrayList<Player> roundlist = BlackboxPlus.calculateRoundWinner(players);
        assertEquals(roundlist.size(),1);
        assertEquals(roundlist.get(0).getName(),"player1");

    }

    @Test
    public void CalculateRoundWinnerPlayer2Win()
    {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        player1.setRoundScore(30);
        player2.setRoundScore(25);
        Player[] players = {player1,player2};
        ArrayList<Player> roundlist = BlackboxPlus.calculateRoundWinner(players);
        assertEquals(roundlist.size(),1);
        assertEquals(roundlist.get(0).getName(),"player2");

    }

    @Test
    public void CalculateRoundWinnerDraw()
    {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        player1.setRoundScore(30);
        player2.setRoundScore(30);
        Player[] players = {player1,player2};
        ArrayList<Player> roundlist = BlackboxPlus.calculateRoundWinner(players);
        assertEquals(roundlist.size(),2);
        assertEquals(roundlist.get(0).getName(),"player1");
        assertEquals(roundlist.get(1).getName(),"player2");

    }


}
