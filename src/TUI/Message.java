package TUI;

import static TUI.Colours.textColour;

import java.util.ArrayList;
import objects.Player;

public class Message
{
    public static void printWelcome()
    {
        System.out.println(textColour + "Welcome to BlackBox!!! :) :) :)");
    }

    public static void printPlayerWelcome(String name)
    {
        System.out.println("Hello, " + name + "! It‘s your turn!");
    }

    public static void printTurnMenu()
    {
        System.out.println("OPTIONS : send ray into box (enter ‘ray‘)\t"
                           + "place atoms on board (enter ‘add atoms‘)\t"
                           + "remove atoms from board (enter ‘remove atoms‘)\t"
                           + "end turn and calculate score (enter ‘end turn‘)");
    }

    public static void printExitMenu()
    {
        System.out.println("WOULD YOU LIKE TO CONTINUE : (enter ‘quit‘ to exit program) (enter ‘continue‘ to switch " +
                           "players and start new game)");
    }

    public static void printGoodbye()
    {
        System.out.println("\n!!! ...Thanks for playing! !!!");
    }

    public static void printLine(String string)
    {
        System.out.println(string);
    }

    public static void print(String string)
    {
        System.out.print(string);
    }

    public static void printBoard(ArrayList<String> board)
    {
        Colours.colourBoard(board);
        for (String str : board)
        {
            System.out.println(str);
        }
        System.out.println();
    }

    public static void printRoundScoreBreakdown(ArrayList<Player> winningPlayers)
    {
        if (winningPlayers.size() > 1)
        {
            Message.printLine("Its a draw!!! These players tied: ");
            for (Player player : winningPlayers)
            {
                Message.printLine(player.getName() + ": " + player.getRoundScore());
            }
        }
        else if (winningPlayers.size() == 1)
        {
            Player winner = winningPlayers.get(0);
            Message.printLine("The winner of this round is: " + winner.getName());
            Message.printLine("Round score: " + winner.getRoundScore());
            System.out.println();
        }
    }

    public static void printLeaderboard(Player[] players)
    {
        for (Player player : players)
        {
            System.out.println(player.getName() + ": " + player.getNumberOfWins());
        }
        System.out.println();
    }

    public static void printFinalLeaderboard(Player[] players, ArrayList<Player> winningList)
    {
        Message.printLeaderboard(players);

        if (winningList.size() > 1)
        {
            System.out.println("It‘s a draw!!! These players tied: ");
            for (Player player : players)
            {
                System.out.println(player.getName() + ": " + player.getNumberOfWins());
            }
        }
        else if (winningList.size() == 1)
        {
            Player winner = winningList.get(0);
            System.out.println("The winner is: " + winner.getName());
            System.out.println("Number of rounds won: " + winner.getNumberOfWins());
        }
    }

    public static void printScoreBreakdown(int missedAtomsScore, int rayMarkersScore)
    {
        Message.printLine("\tScore Breakdown : \t");
        Message.printLine("Number of misplaced atoms : " + missedAtomsScore / 5 + "\tscore : " + missedAtomsScore);
        Message.printLine("Number of ray markers placed : " + rayMarkersScore + "\tscore : " + rayMarkersScore);
        Message.printLine("Total score : " + (missedAtomsScore + rayMarkersScore) + "\n");
    }
}
