import java.util.ArrayList;
import java.util.Scanner;

public class Util {

    static ArrayList<String> empty_board = new ArrayList<>(); //empty string rep. of board //initialize once

    public static void printWelcome(){
        System.out.println("Welcome to BlackBox!!! :) :) :)");
        System.out.println("Please enter user information:\t");
    }
    public static String lineInput(){
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static Boolean unIsValid(String username){
        return !username.isBlank() && (username.length() > 2) && (username.length() <17);
    }

    public static void main(String[] args) {
        printBoard(Atom.generateAtoms(6));
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
    public static void printBoard(Atom[] atoms){
        initializeEmptyBoard(); //move to constructor

        int z = 0; //set z to -1
        int z_wait = 0;

        Vector right = Box.directions[Box.MOVE_DIRECTLY_RIGHT];
        Vector down_left = Box.directions[Box.MOVE_DIAGONAL_DOWN_LEFT];
        Vector down_right = Box.directions[Box.MOVE_DIAGONAL_DOWN_RIGHT];

        Coordinate first_half = new Coordinate(4,8,0); //next line ref (first half of board)
        Coordinate second_half = new Coordinate(0,8,4); //next line ref (second half of board)
        Coordinate pos =  new Coordinate(4,8,0);
        int line_width = 4;

        for(int i = 0; i < empty_board.size(); i++){
            if(empty_board.get(i).contains("██  ██  ██  ██  ██  ██  ██  ██  ██  ██  ██  ██" ) && z !=8){ //line with atom in it after 2
                z++; //update z by 1 (next line)
                z_wait = 0;
                //update next line width and pos
                if(z < 4 && z!=0){
                    line_width++;
                    pos = first_half.moveN(down_left, z+1);//set pos to start of next line
                } else if(z != 8 && z != 0) {
                    line_width--;
                    pos = second_half.moveN(down_right, (z-4)+1);//set pos to start of next line
                }
            }
            if(z_wait == 2){
                String line = empty_board.get(i);
                //check if pos contains atom
                for(int j = 0; j < line_width; j++){ // j < line_width
                    int first_index = line.indexOf("██");
                    int curr_index = first_index+3;
                    if(Atom.containsAtom(atoms, pos)){ //check position
                        //update hexagon with atom

                        line = line.replace("██      ██", "██  X   ██");
                        curr_index += 6; //update hexagon index
                    }
                    else if(j != line_width-1) {
                        pos = pos.move(right); //get next pos
                    }
                }
                System.out.println(line);
            }
            else System.out.println(empty_board.get(i));

            //increment z_wait (aligned atoms)
            z_wait++;
        }
    }
}
