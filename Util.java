import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Util {

    /**
     * colours : @see <a href="https://www.w3schools.blog/ansi-colors-java"></a>
     */
    public static final String ANSI_BLACK = "\033[0;30m";
    public static final String ANSI_WHITE = "\033[0;37m";
    public static final String ANSI_YELLOW = "\033[0;33m";
    public static final String ANSI_RED = "\033[0;31m";
    public static final String ANSI_PURPLE = "\033[0;35m";
    public static final String ANSI_RESET = "\033[0m";

    public static final String ANSI_BLACK_BG = "\033[40m";

    public static final String backgroundColour = ANSI_BLACK_BG;
    public static final String boardColour = ANSI_YELLOW + backgroundColour;
    public static final String atomColour = ANSI_WHITE + backgroundColour;
    public static final String numberColour = ANSI_RED + backgroundColour;
    public static final String textColour = ANSI_RESET + backgroundColour; //default

    public static void printWelcome(){
        System.out.println(textColour + "Welcome to BlackBox!!! :) :) :)");
    }
    public static Player createPlayer(int number){
        Player player;
        System.out.print("Please enter player "+number+" name:\t");
        while (true) {
            try {
                player = new Player(Util.getLine());
                // If input is valid, break out of the loop
                break;
            } catch (IllegalArgumentException e) {
                // Handle invalid input by printing assigned error message from thrown exception
                System.out.println(e.getMessage());
            }
        }
        return player;
    }
    public static String getLine(){
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static void printRayResponse(Ray ray){
        if (ray.getExit() == -1){
            System.out.print("Ray was absorbed!!\n");
        }
        else if(ray.getExit() == ray.getEntry()){
            System.out.print("Ray was reflected back to side " + ray.getEntry() + "\n");
        }
        else{
            System.out.println("Ray exited at "+ ray.getExit());
        }
    }

    public static Boolean unIsValid(String username){
        return !(username == null) && !username.isBlank() && (username.length() > 2) && (username.length() <17);
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
        System.out.println();
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
        for(String str : empty_board){
            int index = empty_board.indexOf(str);
            str += getIndent(5); //indent after string to avoid going out of bounds
            empty_board.set(index, getIndent(20)+ str); //board indent
        }
        appendSideNumbers(empty_board);
        empty_board.replaceAll(String::stripTrailing);
        return empty_board;
    }

    public static void appendSideNumbers(ArrayList<String> board){
        int countUp = 1;
        int countDown = 46;
        String appendFirst = ""+countUp;
        String appendLast = ""+countDown;

        //top half of board
        for(int i = 0; i < board.size()/2; i++){

            if(i%4 != 0){
                appendFirst = " ";
                appendLast = " ";
            }
            else {
                appendFirst = ""+countUp++;
                appendLast = ""+countDown--;
            }
            String line = board.get(i);
            int first_index = line.indexOf("░█");
            int last_index = line.lastIndexOf("░█");


            if((i+2)%4 == 0 && i != (board.size()/2)-1){ //add + remove append adjust
                appendFirst = ""+countUp;
                appendLast = ""+countDown;
                line = line.substring(0, first_index-2) + appendFirst + " " + line.substring(first_index);
                line = line.substring(0, last_index+3) + appendLast + line.substring(last_index+4);
                //adjust counters
                countUp++;
                countDown--;
            }
            else{
                //diagonals
                line = line.substring(0, first_index - 3) + appendFirst + "  " + line.substring(first_index);
                line = line.substring(0, last_index + 3) + " " + appendLast + line.substring(last_index + 3);
            }

            board.set(i, "  " +line);
        }

        //middle + bottom half
        for(int i = board.size()/2; i < board.size(); i++){
            if((i+6)%4 != 0){
                appendFirst = "  ";
                appendLast = "  ";
            }
            else {
                appendFirst = ""+countUp++;
                appendLast = ""+countDown--;
            }
            String line = board.get(i);
            int first_index = line.indexOf("░█");
            int last_index = line.lastIndexOf("░█");


            if(i == (board.size()/2)){ //append sides to middle of board
                appendFirst = ""+countUp;
                appendLast = ""+countDown;
                line = line.substring(0, first_index-2) + " " + appendFirst + " " + line.substring(first_index);
                line = line.substring(0, last_index+4)  +  " " + appendLast  +line.substring(last_index+4);
                //adjust counters
                countUp++;
                countDown--;
            }
            else if((i)%4 == 0 && i != board.size()-1 && i != (board.size()/2)+1){ //add + remove append adjust (horizontal)
                appendFirst = ""+countUp;
                appendLast = ""+countDown;
                line = line.substring(0, first_index-1) + appendFirst + " " + line.substring(first_index);
                line = line.substring(0, last_index+4) + " " + appendLast + line.substring(last_index+4);
                //adjust counters
                countUp++;
                countDown--;
            }
            else{ //append to diagonals + remaining
                line = line.substring(0, first_index - 2) + appendFirst + "  " + line.substring(first_index);
                line = line.substring(0, last_index+4) + "  " + appendLast + line.substring(last_index+4);
            }
            board.set(i, line);
        }

        //append line numbers to top and bottom lines
        String topLine = board.get(0);
        String bottomLine = board.get(board.size()-1);
        countUp = 20;
        countDown = 54;
        int first_index = topLine.indexOf("░█"); //both lines are symmetrical (ignoring side numbers)
        int curr_index = first_index + 3;
        for(int i = 0; i <4; i++){
            topLine = topLine.substring(0,curr_index-1) + countDown + "  " + (countDown-1) + topLine.substring(curr_index+5);
            bottomLine = bottomLine.substring(0,curr_index-1) + countUp + "  " + (countUp+1) + bottomLine.substring(curr_index+5);
            countDown-=2;
            countUp+=2;
            curr_index += 8;
        }
        board.set(0, topLine);
        board.set(board.size()-1, bottomLine);
    }

    public static void main(String[] args) {
        Atom[] atoms = Atom.generateAtoms(6);
        printBoard(getAtomizedBoard(atoms));
        System.out.println(Arrays.toString(atoms));
    }

    private static int findLineContaining(int side, ArrayList<String> board){
        int i = 0;
        for(String s : board){
            if(s.contains(side+"")) return i;
            i++;
        }
        return -1;
    }
    /**
     * Function to append ray markers to board with side numbers.
     * 1. Absorbed Rays - Black Ray Marker at Entry
     * 2. Reflected Rays - White Ray Marker at Entry
     * 3. Other Rays - Pair of Same Colour Ray Markers at Entry and Exit
     *
     * @param rays array list of rays
     * @return board with ray markers appended
     */

    public static ArrayList<String> appendRayMarkers(ArrayList<Ray> rays, ArrayList<String> board){

        char rayMarkerAbsorbed = 'A';   //set as white later ...
        char rayMarkerReflected = 'R';  //set as black later ...
        char rayMarkerPair = 'X';       //set up function to randomly choose colour later ...

        for(Ray ray : rays){
            //absorbed
            if(ray.getExit() == -1){
                int index =  findLineContaining(ray.getEntry(), board);
                int ray_pos = board.get(index).indexOf(ray.getEntry());

                //append to top of board
                if(ray.getEntry() > 46 && ray.getEntry() <= 54){
                    /*check if ray marker is already placed at position and add empty lines until empty space is found*/
                    for(String s : board){
                        if(s.charAt(ray_pos) != ' '){
                            board.add(0, getIndent(s.length()/2));
                        }
                        else{
                            break;
                        }
                    }
                    //append ray marker at position
                    char[] ch = board.get(0).toCharArray();
                    ch[ray_pos] = rayMarkerPair;
                    board.set(0, Arrays.toString(ch));
                }
                //append to bottom of board
                else if(ray.getEntry() > 19 && ray.getEntry() < 28){

                }
                //append to sides
                else{
                }
            }
            //reflected
            else if(ray.getExit() == ray.getEntry()){
            }
            //other
            else{
            }
        }
        return board;
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
                        line = line.substring(0,curr_index-1) + "  ░░" + line.substring(3+curr_index);
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
        //colour atoms
        i = 0;
        board.set(0, boardColour+board.get(0));
        for(String str : board){
            str = str.replaceAll("╔╗", atomColour+"╔╗"+boardColour);
            str = str.replaceAll("░░", atomColour+"░░"+boardColour);
            str = str.replaceAll("╚╝", atomColour+"╚╝"+boardColour);

            board.set(i, str);
            i++;
        }
        board.set(board.size()-1, board.get(board.size()-1) +textColour); //SET COLOUR BACK TO DEFAULT
        return board;
    }
    public static Atom getAtom(int x, int y, Box emptyBox) throws IOException {

        Ray rayx = new Ray(x, emptyBox);
        Ray rayy = new Ray(y, emptyBox);

        if(rayx.getExit() == rayy.getEntry()){
            throw new IllegalStateException();
        }
        ArrayList<Coordinate> coordsx = rayx.getCoords();
        ArrayList<Coordinate> coordsy = rayy.getCoords();

        Atom atom = null;

        for (Coordinate i : coordsx) {
            if (coordsy.contains(i)) {
                atom = new Atom(i.getX(), i.getY(), i.getZ());
                break;
            }
        }
        if(atom == null) throw new IOException();
        return atom;
    }
}
