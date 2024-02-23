import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Util {

    public static void printWelcome(){
        System.out.println("Welcome to BlackBox!!! :) :) :)");
    }
    public static Player createPlayer(int number){
        Player player;
        System.out.print("Please enter player "+number+" name:\t");
        while (true) {
            try {
                player = new Player(Util.lineInput());
                // If input is valid, break out of the loop
                break;
            } catch (IllegalArgumentException e) {
                // Handle invalid input by printing assigned error message from thrown exception
                System.out.println(e.getMessage());
            }
        }
        return player;
    }
    public static String lineInput(){
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static Boolean unIsValid(String username){
        return !(username == null) && !username.isBlank() && (username.length() > 2) && (username.length() <17);
    }

    public static void main(String[] args) {
        Atom[] atoms = Atom.generateAtoms(6);
        printBoard(getAtomizedBoard(atoms));
        System.out.println(Arrays.toString(atoms));
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

    public static void printBoard(ArrayList<String> board){
        for(String str : board){
            System.out.println(str);
        }
    }
    /**
     *
     * @return a basic empty board as an ArrayList of type:String
     */
    public static ArrayList<String> getEmptyBoard(){
        ArrayList<String> empty_board = new ArrayList<>(); //empty string rep. of board //initialize once

        //init string builders
        String one = "    ░█  "; //first line
        String two = "  ░█  ░█"; //single spacing
        String three="░█      "; //triple spacing

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
        empty_board.replaceAll(String::stripTrailing);
        return empty_board;
    }

    /**
     *
     * @param atoms - array filled with atoms
     * @return a board filled with atoms as an ArrayList of type:String
     */
    public static ArrayList<String> getAtomizedBoard(Atom[] atoms){

        ArrayList<String> board = getEmptyBoard();
        ArrayList<String> atomizedBoard = getEmptyBoard();

        int z = 0; //set z (indexes 1-9)
        int z_wait = 0;

        Vector right = Box.directions[Box.MOVE_DIRECTLY_RIGHT];
        Vector down_left = Box.directions[Box.MOVE_DIAGONAL_DOWN_LEFT];
        Vector down_right = Box.directions[Box.MOVE_DIAGONAL_DOWN_RIGHT];

        Coordinate first_half = new Coordinate(4,8,0); //next line ref (first half of board)
        Coordinate second_half = new Coordinate(0,8,4); //next line ref (second half of board)
        Coordinate pos =  new Coordinate(4,8,0);
        int line_width = 4;

        /*loop through box*/
        for(int i = 0; i < board.size(); i++){
            /*update line info*/
            if(board.get(i).contains("░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█  ░█" )){ //line with atom in it after 2
                z++; //update z by 1 (next line)
                z_wait = 0; //alignment
                //update next line width and pos
                if(z < 4 && z!=0){
                    line_width++;
                    pos = first_half.moveN(down_left, z); //set pos to start of next line
                } else if(z != 0) {
                    line_width--;
                    pos = second_half.moveN(down_right, (z-4));//set pos to start of next line
                }
                if(z == 4){
                    line_width+=2;
                }
            }
            /*check line for atoms*/
            if((z == 0 && z_wait == 3) || (z!= 0 && z_wait == 2)){
                /*lines to edit for each atom*/
                String lineUp = board.get(i-1);
                String line = board.get(i);
                String lineDown = board.get(i+1);
                /*below variables used for string manipulation*/
                int first_index = line.indexOf("░█");
                int curr_index = first_index+3;
                /*check for each hexagon, whether it contains an atom*/
                for(int j = 0; j < line_width+1; j++){ // j < line_width
                    /*check current position*/
                    if(Atom.containsAtom(atoms, pos.moveN(right,j))){
                        /*update hexagon with atom*/
                        lineUp = lineUp.substring(0,curr_index-1) + "  ╔╗" + lineUp.substring(3+curr_index);
                        line = line.substring(0,curr_index-1) + "  ░░ " + line.substring(4+curr_index);
                        lineDown = lineDown.substring(0,curr_index-1) + "  ╚╝" + lineDown.substring(3+curr_index);
                    }
                    curr_index +=  8; /*atom offset (string manipulation)*/
                }
                atomizedBoard.set(i, line);
                atomizedBoard.set(i-1, lineUp);
                atomizedBoard.set(i+1, lineDown);
            }
            //else atomizedBoard.add(board.get(i));//System.out.println(board.get(i));
            //increment z_wait (changes alignment)
            z_wait++;
            //System.out.print(z_wait);
        }

        return atomizedBoard;
    }
}
