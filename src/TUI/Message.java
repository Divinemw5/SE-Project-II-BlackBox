package TUI;

import objects.Player;
import java.util.ArrayList;
import static TUI.Colours.textColour;

public class Message {
    public static void printWelcome(){
        System.out.println(textColour + "Welcome to BlackBox!!! :) :) :)");
    }
    public static void printPlayerWelcome(String name){ System.out.println("Hello, "+name+"! It‘s your turn!");}
    public static void printTurnMenu(){System.out.println("OPTIONS : send ray into box (enter ‘ray‘)\t" +
                                                          "place atoms on board (enter ‘add atoms‘)\t" +
                                                          "remove atoms from board (enter ‘remove atoms‘)\t" +
                                                          "end turn and calculate score (enter ‘end turn‘)");}
    public static void printExitMenu(){System.out.println("WOULD YOU LIKE TO CONTINUE : (enter ‘quit‘ to exit program) (enter ‘continue‘ to switch players and start new game)");}
    public static void printGoodbye(){System.out.println("Thanks for playing!");}
    public static void printLine(String string){System.out.println(string);}
    public static void printBoard(ArrayList<String> board){
        Colours.colourBoard(board);
        for(String str : board){
            System.out.println(str);
        }
        System.out.println();
    }

    public static void printScoreBreakdown(int missedAtomsScore, int rayMarkersScore){
        Message.printLine("\tScore Breakdown : \t");
        Message.printLine("Number of misplaced atoms : " + missedAtomsScore/5 + "\tscore : " + missedAtomsScore);
        Message.printLine("Number of ray markers placed : " + rayMarkersScore + "\tscore : " + rayMarkersScore);
        Message.printLine("Total score : " + (missedAtomsScore+rayMarkersScore));
    }


}
