package TUI;

import math.*;
import objects.*;
import java.util.ArrayList;

public class Board {

    public static final char rayMarkerAbsorbed = 'A';   //set as black later ...
    public static final char rayMarkerReflected = 'R';  //set as white later ...
    public static final char[] pairMarkers = new char[]{'#','≡','!','$','■','¤','«','§','¡','¿','¥','×','ƒ','¶'};

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
                line = line.substring(0, first_index-2) + appendFirst + "→" + line.substring(first_index);
                line = line.substring(0, last_index+2) + "←"+ appendLast +line.substring(last_index+4);
                //adjust counters
                countUp++;
                countDown--;
            }
            else{
                //diagonals
                String downRightArrow = (appendFirst != " ") ? "↘" : " ";
                String downLeftArrow = (appendLast != " ") ? "↙" : " ";

                line = line.substring(0, first_index - 4) + appendFirst + downRightArrow +"  " + line.substring(first_index);
                line = line.substring(0, last_index + 4) + downLeftArrow + appendLast + line.substring(last_index + 3);
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
                line = line.substring(0, first_index-2) + " " + appendFirst + "→" + line.substring(first_index);
                line = line.substring(0, last_index+4)  +  "←" + appendLast + line.substring(last_index+4);
                //adjust counters
                countUp++;
                countDown--;
            }
            else if((i)%4 == 0 && i != board.size()-1 && i != (board.size()/2)+1){ //add + remove append adjust (horizontal)
                appendFirst = ""+countUp;
                appendLast = ""+countDown;
                line = line.substring(0, first_index-1) + appendFirst + "→" + line.substring(first_index);
                line = line.substring(0, last_index+4) + "←" + appendLast + line.substring(last_index+4);
                //adjust counters
                countUp++;
                countDown--;
            }
            else{ //append to diagonals + remaining
                String upRightArrow = (appendFirst != "  ") ? "↗" : " ";
                String upLeftArrow =  (appendLast != "  ") ? "↖" : " ";
                line = line.substring(0, first_index - 3) + appendFirst + upRightArrow +"  " + line.substring(first_index);
                line = line.substring(0, last_index+4)  + "  " + upLeftArrow + appendLast + line.substring(last_index+4);
            }
            board.set(i, line);
        }

        //append line numbers to top and bottom lines
        String topLine = board.get(0);
        String bottomLine = board.get(board.size()-1);
        String topNumberLine =getIndent(board.get(0).length()/2);
        String bottomNumberLine =getIndent(board.get(0).length()/2);

        board.add(0, getIndent(board.get(0).length()/2));
        board.add(getIndent(board.get(board.size()-1).length()/2));

        countUp = 20;
        countDown = 54;
        int first_index = topLine.indexOf("░█"); //both lines are symmetrical (ignoring side numbers)
        int curr_index = first_index + 3;
        for(int i = 0; i <4; i++){
            topNumberLine = topNumberLine.substring(0,curr_index-1) + countDown + "  " + (countDown-1) +topNumberLine.substring(curr_index+5);
            bottomNumberLine = bottomNumberLine.substring(0,curr_index-1) + countUp + "  " + (countUp+1) + bottomNumberLine.substring(curr_index+5);
            topLine = topLine.substring(0,curr_index-1) + " ↙" + "  " + "↘ " +topLine.substring(curr_index+5);
            bottomLine = bottomLine.substring(0,curr_index-1) + " ↖" + "  " + "↗ " + bottomLine.substring(curr_index+5);
            countDown-=2;
            countUp+=2;
            curr_index += 8;
        }
        board.set(0, topNumberLine);
        board.set(1, topLine);
        board.set(board.size()-1, bottomNumberLine);
        board.set(board.size()-2, bottomLine);
    }


    /**
     * Function to find line containing a specified side number.
     * @param side -side number (must be listed on board)
     * @param board - textual board (must have side numbers appended)
     * @return index of line containing side number if found, and -1 if not found
     */
    public static int findLineContaining(int side, ArrayList<String> board){
        int i = 0;
        for(String s : board){
            s += " "; //for checking numbers at end of board with no trailing whitespace
            if(s.contains(" "+side+"↘") || s.contains("↙"+side+" ") || s.contains(" "+side+"→")
                    || s.contains("←"+side+" ") || s.contains("↖"+side+" ") || s.contains(" "+side+"↗")
            || s.contains(" "+side+" ")) return i;
            i++;
        }
        return -1;
    }

    /**
     * Function to append ray markers to board with side numbers.
     * 1. Absorbed Rays - Black objects.Ray Marker at Entry
     * 2. Reflected Rays - White objects.Ray Marker at Entry
     * 3. Other Rays - Pair of Same Colour objects.Ray Markers at Entry and Exit
     *
     * @param rays array list of rays
     * @return board with ray markers appended
     */
    public static ArrayList<String> appendRayMarkers(ArrayList<Ray> rays, ArrayList<String> board){
        char rayMarker;
        int index;
        int rayPos;
        int currentPair = 0;

        for(Ray ray : rays){
            if(ray.getExit() == -1){
                rayMarker = rayMarkerAbsorbed;
            } else if(ray.getExit() == ray.getEntry()) {
                rayMarker = rayMarkerReflected;
            } else {
                //choose character to replace with coloured marker when colouring board
                rayMarker = pairMarkers[currentPair];
                //System.out.println(currentPair);
                currentPair = Math.floorMod(currentPair+1, pairMarkers.length);
            }
            index = findLineContaining(ray.getEntry(), board);
            rayPos = board.get(index).indexOf(ray.getEntry()+"");
            placeMarker(board, index, rayPos, ray.getEntry(), rayMarker);

            //System.out.println(board.get(index));
            //if paired ray markers
            if(!(ray.getExit() == -1 || ray.getExit() == ray.getEntry())){
                index =  findLineContaining(ray.getExit(), board);
                rayPos = board.get(index).indexOf(ray.getExit()+"");
                //rayMarker = rayMarkerPair;
                placeMarker(board, index, rayPos, ray.getExit(), rayMarker);
            }

        }
        return board;
    }

    /**
     * Function to place a single marker on the board
     * @param board - board to append marker to as an ArrayList of type String
     * @param index - index of line to alter in ArrayList
     * @param rayPos - index of side number in line to alter
     * @param side - ray exit/entry side number
     * @param rayMarker - char to append
     */
    private static void placeMarker(ArrayList<String> board, int index, int rayPos, int side, char rayMarker){
        //append to top of board
        if(side > 46 && side <= 54){
            /*check if ray marker is already placed at position, if yes add empty line*/
            if(board.get(0).charAt(rayPos) != ' ') board.add(0, getIndent(board.get(0).length()/2));
            //System.out.println(board.get(0));
            //append ray marker at position
            String line = board.get(0).substring(0,rayPos) + rayMarker + board.get(0).substring(rayPos);
            board.set(0, line);
        }
        //append to bottom of board
        else if(side > 19 && side < 28){
            if(board.get(board.size()-1).charAt(rayPos) != ' ') board.add(board.size(), getIndent(board.get(0).length()/2));

            String line = board.get(board.size()-1).substring(0,rayPos) +rayMarker + board.get(board.size()-1).substring(rayPos);
            board.set(board.size()-1, line);
        }
        //append to sides (right)
        else if(side >= 28 && side <47){
            //System.out.println(side);
            while(board.get(index).charAt(rayPos) != ' '){
                board.set(index, board.get(index)+" ");
                rayPos++;
                //System.out.println(rayPos);
            }
            rayPos++;
            String line = board.get(index).substring(0, rayPos) + rayMarker +" "+ board.get(index).substring(rayPos);
            board.set(index, line.stripTrailing());
        }
        //append to sides (left)
        else{
            while(containsPairMarkers(board.get(index).substring(0, rayPos)) || board.get(index).substring(0, rayPos).contains(rayMarker+"")){
                rayPos--;
            }
            rayPos--;
            String line = board.get(index).substring(0, rayPos-1) + rayMarker + board.get(index).substring(rayPos);
            board.set(index, line);
        }
    }

    /**
     * Function to check if a member of the list of valid pair markers is in a string.
     * @param str - String to check
     * @return true if contained in str, false otherwise
     */
    public static boolean containsPairMarkers(String str){
        for(char ch : pairMarkers){
            if(str.contains(ch+"")) return true;
        }
        return false;
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
        for(int i = 1; i < board.size(); i++){
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
}
