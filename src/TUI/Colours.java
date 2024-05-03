package TUI;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static TUI.Board.*;

public class Colours {
    /**
     * colours : @see <a href="https://www.w3schools.blog/ansi-colors-java"></a>
     */
    public static final String ANSI_BLACK = "\033[0;30m";  //BLACK
    public static final String ANSI_RED = "\033[0;31m";    //RED
    public static final String ANSI_GREEN = "\033[0;32m";  //GREEN
    public static final String ANSI_YELLOW = "\033[0;33m"; //YELLOW
    public static final String ANSI_BLUE = "\033[0;34m";   //BLUE
    public static final String ANSI_PURPLE = "\033[0;35m"; //PURPLE
    public static final String ANSI_CYAN = "\033[0;36m";   //CYAN
    public static final String ANSI_WHITE = "\033[0;37m";  //WHITE
    public static final String ANSI_RESET = "\033[0m";     //RESET

    public static final String ANSI_BLACK_BG = "\033[40m"; //WHITE BG
    public static final String ANSI_WHITE_BG = "\033[47m"; //BLACK BG

    //constants for colouring the board, can be switched to instance variables to add multiple colour schemes
    public static final String backgroundColour = ANSI_BLACK_BG;
    public static final String boardColour = ANSI_RESET + backgroundColour;
    public static final String atomColour = ANSI_YELLOW + backgroundColour;
    public static final String numberColour = ANSI_WHITE + backgroundColour;
    public static final String textColour = ANSI_RESET + backgroundColour; //default

    public static final String absorbedColour = ANSI_BLACK + ANSI_WHITE_BG;
    public static final String reflectedColour = ANSI_RESET;
    public static final String[] pairColours = new String[]{ANSI_GREEN, ANSI_BLUE, ANSI_PURPLE, ANSI_CYAN};


    /**
     * Function to colour the board
     * @param board - board to colour
     * @return coloured board
     */
    public static ArrayList<String> colourBoard(ArrayList<String> board){
        //colour numbers
        int i = 0;
        for(String str : board) {
            char[] chars = str.toCharArray();
            StringBuilder stringBuilder = new StringBuilder();
            for(char ch : chars){
                if(Pattern.compile("[0-9]").matcher(ch+"").matches()){
                    stringBuilder.append(numberColour).append(ch).append(boardColour);
                }
                else stringBuilder.append(ch);
            }
            board.set(i,stringBuilder.toString());
            i++;
        }
        //colour atoms and ray markers
        i = 0;
        board.set(0, boardColour+board.get(0));
        for(String str : board){
            //colour atoms
            str = str.replaceAll("╔╗", atomColour+"╔╗"+boardColour);
            str = str.replaceAll("░░", atomColour+"░░"+boardColour);
            str = str.replaceAll("╚╝", atomColour+"╚╝"+boardColour);

            //colour ray markers
            str = str.replaceAll(String.valueOf(rayMarkerReflected), reflectedColour+rayMarkerReflected+boardColour);
            str = str.replaceAll(String.valueOf(rayMarkerAbsorbed), absorbedColour+rayMarkerAbsorbed+boardColour);

            for(int j = 0; j < pairMarkers.length; j++){
                if(str.contains(pairMarkers[j]+"")) {
                    str = str.replace(String.valueOf(pairMarkers[j]), pairColours[j%pairColours.length] + pairMarkers[j] + boardColour);
                }
            }
            board.set(i, str);
            i++;
        }
        board.set(board.size()-1, board.get(board.size()-1) +textColour); //SET COLOUR BACK TO DEFAULT
        return board;
    }
}
