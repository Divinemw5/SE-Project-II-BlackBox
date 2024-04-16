package TUI;

import objects.Player;
import java.util.ArrayList;
import static TUI.Colours.textColour;

public class Message {
    public static void printWelcome(){
        System.out.println(textColour + "Welcome to BlackBox!!! :) :) :)");
    }
    public static void printPlayerWelcome(String name){ System.out.println("Hello, "+name+"!\nIt‘s your turn!");}
    public static void printBoard(ArrayList<String> board){
        for(String str : board){
            System.out.println(str);
        }
        System.out.println();
    }

}
