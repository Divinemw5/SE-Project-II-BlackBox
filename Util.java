import java.util.ArrayList;
import java.util.Scanner;

public class Util {

    static ArrayList<String> empty_board = new ArrayList<>(); //empty string rep. of board //initialize once

    public static String printWelcome(){
        System.out.println("Welcome to BlackBox!!! :) :) :)");
        System.out.println("Please enter user information:\t");
        Scanner input = new Scanner(System.in);
        String username = input.nextLine();
        if(username.trim().isBlank()){
            throw new IllegalArgumentException("WEONG USNERNAMEN");
        }
        else{
            return username;
        }
    }

    public static void main(String[] args) {
        printBoard();
    }

    /**
     * Function takes parameters
     * @param str - String to be duplicated
     * @param x   - Number of times to duplicate
     * @return newly created String
     */
    private static String duplicate(String str, int x) {
        StringBuilder return_str = new StringBuilder();
        for(int i = 0; i < x; i++) {
            return_str.append(str);
        }
        return return_str.toString();
    }

    /**
     * Function takes parameters
     * @param x - desired indent size
     * @return a String of size 2x containing " " characters.
     */
    private static String getIndent(int x){
        StringBuilder return_val = new StringBuilder();
        for(int i = 0; i < x; i++){
            return_val.append("  ");
        }
        return return_val.toString();
    }

    /**
     * initializes empty_board
     * */
    public static void initializeEmptyBoard(){
        //init string builders
        String one = "    ██  "; //first line
        String two = "  ██  ██"; //single spacing
        String three="██      "; //triple spacing

        StringBuilder onea = new StringBuilder(duplicate(one,5)); //for appending lines
        StringBuilder twoa = new StringBuilder(duplicate(two,5));
        StringBuilder threea = new StringBuilder(duplicate(three,5));

        int indentSize = 8; //initialize indent size
        int appendLength = 0; //initialize appendLength

        for(int i = 0; i<10; i++) {
            //System.out.print(indentSize + ",");
            //System.out.print(appendLength + ",");

            //update indent (decrement for i < 5, increment for i > 5), update appendLength(opposite inc/dec)
            //top half of board
            if (i < 5) {
                //store in arraylist (with indent)
                empty_board.add(getIndent(indentSize) + onea + duplicate(one, appendLength));
                empty_board.add(getIndent(indentSize) + twoa + duplicate(two, appendLength));
                empty_board.add(getIndent(indentSize) + threea + duplicate(three, appendLength+1));
                empty_board.add(getIndent(indentSize) + threea + duplicate(three, appendLength+1));
                //update counter
                indentSize -= 2;
                appendLength++;
            } else { //bottom half
                if(i == 5){
                    appendLength--;
                }
                empty_board.add(getIndent(indentSize) + threea + duplicate(three, appendLength+1));
                empty_board.add(getIndent(indentSize) + twoa + duplicate(two, appendLength));
                if(i != 9) {
                    empty_board.add(getIndent(indentSize) + onea + duplicate(one, appendLength));
                }
                empty_board.add(getIndent(indentSize) + onea + duplicate(one, appendLength));
                //update counter
                if(i == 5){
                    indentSize +=2;
                }
                indentSize += 2;
                appendLength--;
            }
        }
        //System.out.println();
    }

    /**
     * Function to print the board without atoms (for now)
     */
    public static void printBoard(){
        initializeEmptyBoard(); //move to constructor
        for(String str : empty_board){
            System.out.println(str);
        }
    }

}
