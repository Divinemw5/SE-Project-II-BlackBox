import TUI.Util;
import objects.Atom;
import objects.Box;
import objects.Player;
import objects.Ray;
import math.*;
import objects.*;
import TUI.*;

import java.util.ArrayList;

/**
 *  Main class (contains program flow throughout the game)
 *
 */
public class BlackboxPlus {

    public static void main(String[] args){

        //Initialize program state
        String userInput = "";
        Player[] player = new Player[2];
        int currentPlayer = 0; //set player who goes first
        int roundsPlayed = 0; //total number of rounds played

        //Welcome the user and take player information
        Message.printWelcome();
        player[0] = Input.getPlayer(1);
        player[1] = Input.getPlayer(2);

        /*start new round*/
        while(!userInput.equals("quit"))
        {
            Message.printPlayerWelcome(player[currentPlayer].getName());

            //initialize game state
            Atom[] atoms = Atom.generateAtoms(6);   //generate random atoms
            Box box = new Box(atoms);                  //create the empty board
            ArrayList<Ray> rays = new ArrayList<>();   //start empty array list (pass to TUI.Util to add ray markers to board)
            Atom[] userAtoms = new Atom[] {null, null, null, null, null, null};

            //play round /*do not let user end round if no atoms have been placed and no rays have been entered into the box (i.e. no score calculation)*/
            while(!userInput.equals("end round")){
                /*output board with ray markers and atoms placed by user*/
                Message.printBoard(Colours.colourBoard(Board.appendRayMarkers(rays, Board.getAtomizedBoard(userAtoms)))); //ONLY USING ATOMIZED BOARD FOR RAY TESTING
                System.out.println("OPTIONS : send ray into box (enter ‘ray‘)\t place atoms on board (enter ‘place atoms‘)\t end round and calculate score (enter ‘end round‘)");
                userInput = Input.getLine();

                /*place ray at entry point*/
                if(userInput.equalsIgnoreCase("ray")){
                    try{
                        System.out.print("Enter ray entry number : ");
                        int entry = Integer.parseInt(Input.getLine());
                        Ray ray = new Ray(entry, box);
                        /*testing*/

                        //System.out.println(ray);
                        //setter announces position + add ray to rays array list
                        Util.printRayResponse(ray);
                        rays.add(ray);
                    }
                    catch(IllegalArgumentException ex){
                        //handle exception (prompt user to enter valid side number)
                        System.out.println("Please enter a valid side number (1-54)");
                        //ex.printStackTrace();
                    }
                }
                else if (userInput.equalsIgnoreCase("place atoms")) {
                    System.out.println("Would you like to add or remove an atom? ('add', 'remove')");
                    userInput = Input.getLine();
                    try{
                        if (userInput.trim().equalsIgnoreCase("add")) {
                                System.out.println("Enter 2 side numbers to place an atom where they intersect");
                                System.out.print("Enter first number: ");
                                int x = Input.getInt();
                                System.out.print("Enter second number: ");
                                int y = Input.getInt();

                                Atom atomToPlace = Util.getAtom(x, y);
                                if(Atom.containsAtom(userAtoms, atomToPlace.getLocation())) throw new IllegalStateException("atom already in list");

                                //System.out.println(atomToPlace);
                                for(int i = 0; i < userAtoms.length; i++){
                                    if(userAtoms[i] == null){
                                        userAtoms[i] = atomToPlace;
                                        break;
                                    }
                                    if(i == userAtoms.length-1){
                                        System.out.println("Please remove an atom before placing one");
                                    }
                                }
                                //System.out.println(Arrays.toString(userAtoms));
                        }
                        else if (userInput.trim().equalsIgnoreCase("remove")){
                                System.out.println("Enter 2 side numbers to remove an atom where they intersect");
                                System.out.print("Enter first number: ");
                                int x = Integer.parseInt(Input.getLine());
                                System.out.print("Enter second number: ");
                                int y = Integer.parseInt(Input.getLine());

                                Atom atomToRemove = Util.getAtom(x, y);

                                for(int i = 0; i < userAtoms.length; i++){
                                    if(userAtoms[i]!=null && userAtoms[i].equals(atomToRemove)){
                                        userAtoms[i] = null;
                                        break;
                                    }
                                    if(i == userAtoms.length-1){
                                        System.out.println("Did not find specified atom");
                                    }
                                }
                        }
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Please enter a valid side number (1-54)");
                    } catch (IllegalStateException ex){
                        System.out.println("Please enter side numbers with a valid intersection (example : try 37, 42)");
                    } catch (IllegalAccessError ex){ //change to custom exception type later !!!
                        System.out.println("Please ensure no guessed atom is already placed at guessed position.");

                    }
                }
            }

            //calculate score for round
            int missedAtomsScore = 0;
            int rayMarkersScore = 0;
            for(Atom atom : userAtoms) {
                if(!Atom.containsAtom(atoms, atom.getLocation())){
                    missedAtomsScore += 5;
                }
            }
            for(Ray ray : rays){
                rayMarkersScore += ray.getNumberOfMarkers();
            }
            player[currentPlayer].setRoundScore(missedAtomsScore + rayMarkersScore);

            //displays a breakdown of the experimenter‘s score including points for ray markers and misplaced atoms
            Util.outputScoreBreakdown(missedAtomsScore, rayMarkersScore);

            //calculate winner (out of 2 rounds)
            if(roundsPlayed % 2 == 0){
                //current player wins
                if(player[currentPlayer].getRoundScore() > player[Math.floorMod(currentPlayer+1,2)].getRoundScore()){

                }
                //current player loses
                else if(player[currentPlayer].getRoundScore() < player[Math.floorMod(currentPlayer+1,2)].getRoundScore()){

                }
                //tie
                else{

                }
            } roundsPlayed++;

            //output board with atoms
            Message.printBoard(Colours.colourBoard(Board.getAtomizedBoard(atoms)));

            System.out.println("WOULD YOU LIKE TO CONTINUE (enter ‘quit‘ to exit program) "+"(enter ‘atoms‘ to show hidden atoms)"+" (enter ‘continue‘ to switch players and start new game)");
            userInput = Input.getLine();
            //test functionality
            if(userInput.equals("atoms")){
                Message.printBoard(Colours.colourBoard(Board.getAtomizedBoard(atoms)));
            }
            System.out.println("WOULD YOU LIKE TO CONTINUE (enter ‘quit‘ to exit program) (enter ‘continue‘ to switch players and start new game)");
            userInput = Input.getLine();
            currentPlayer = Math.floorMod(currentPlayer+1,2); //switch player
        }
        //print goodbye message
        System.out.println("Thanks for playing !!!");
    }


}
