import TUI.Util;
import objects.Atom;
import objects.Box;
import objects.Player;
import objects.Ray;
import TUI.*;

import java.util.ArrayList;

/**
 *  Main class (contains program flow throughout the game)
 *
 */
public class BlackboxPlus {

    public static final int MAX_PLAYERS = 4;
    private final Player[] players;
    private int currentPlayer = 0; //set player who goes first
    private int roundsPlayed = 0;  //total number of rounds played

    public static final int DEV = 1;
    public static final int USER = 0;
    public static int PERMISSION_MODE = USER; //default (cannot display hidden atoms)

    BlackboxPlus(int playerCount){
        if(playerCount > MAX_PLAYERS || playerCount < 1) throw new IllegalArgumentException("BlackboxPlus can be played with 1-4 players only.");
        this.players = new Player[playerCount];
    }
    private void initializePlayers(){
        for(int i = 0; i < players.length; i++){
            players[i] = Input.getPlayer(i+1);
        }
    }
    private void play(){
        String userInput = "";
        //Welcome the user and take player information
        Message.printWelcome();
        initializePlayers();
        while(!userInput.equalsIgnoreCase("quit")){
            Message.printPlayerWelcome(players[currentPlayer].getName());
            playTurn();
            Message.printExitMenu();
            userInput = Input.getLine();
            //determine winner every players.length turns
            if(roundsPlayed % players.length == 0){
                Player winner = players[0];
                for (Player player : players) {
                    if (player.getRoundScore() < winner.getRoundScore()) {
                        winner = player;
                    }
                }
                winner.setNumberOfWins(winner.getNumberOfWins() + 1);
                System.out.println(winner.getName() + " won this round!!!");
                System.out.println("Current scores");
                for(Player player : players){
                    System.out.println(player.getName() + ": " + player.getNumberOfWins());
                }
            }

        }
        Message.printGoodbye();
    }
    private void playTurn(){
        String userInput = "";

        Atom[] atoms = Atom.generateAtoms(6);   //generate random atoms
        Box box = new Box(atoms);                   //create the empty board
        ArrayList<Ray> rays = new ArrayList<>();    //start empty array list (pass to TUI.Util to add ray markers to board)
        Atom[] userAtoms = new Atom[] {null, null, null, null, null, null}; //setup empty array of user atoms

        while(!userInput.equalsIgnoreCase("end turn")){
            Message.printBoard(Board.appendRayMarkers(rays, Board.getAtomizedBoard(userAtoms)));
            Message.printTurnMenu();
                userInput = Input.getLine();
                if(userInput.equalsIgnoreCase("ray")){
                    try{
                        Ray ray = Input.getRay(box);
                        Util.printRayResponse(ray);
                        rays.add(ray);
                    }
                    catch(Exception ex){
                        System.out.println(ex.getMessage());
                    }
                }
                else if(userInput.equalsIgnoreCase("add atoms")){
                    try
                    {
                        Atom atomToPlace = Input.getAtomFromUser();
                        if(Atom.containsAtom(userAtoms, atomToPlace.getLocation())) throw new IllegalStateException("Please ensure no guessed atom is already placed at guessed position.");
                        int i = 0;
                        while(i < userAtoms.length && userAtoms[i] != null) i++;
                        if(i == userAtoms.length) throw new IllegalStateException("Please remove an atom before placing one.");
                        else userAtoms[i] = atomToPlace;
                        Message.printLine("Atom placed successfully!");
                    }
                    catch (Exception ex){
                        System.out.println(ex.getMessage());
                    }
                }
                else if(userInput.equalsIgnoreCase("remove atoms")){
                    try
                    {
                        Atom atomToRemove = Input.getAtomFromUser();
                        int i = 0;
                        while(i < userAtoms.length && !userAtoms[i].equals(atomToRemove)) i++;
                        if(i == userAtoms.length) throw new IllegalStateException("Please select an atom present on the board.");
                        else userAtoms[i] = null;
                        Message.printLine("Atom removed successfully!");
                    }
                    catch (Exception ex){
                        System.out.println(ex.getMessage());
                    }
                }
                else if(PERMISSION_MODE == DEV && userInput.equalsIgnoreCase("display hidden atoms")){
                    Message.printBoard(Board.getAtomizedBoard(atoms));
                }
                else if(!userInput.equalsIgnoreCase("end turn")){
                    userInput = "";
                    Message.printLine("Please enter a valid option.");
                }
        }
            roundsPlayed++;
            //print board with real atom positions (no ray markers)
            Message.printLine("Displaying board with real atom positions ...");
            Message.printBoard(Board.getAtomizedBoard(atoms));
            //score calculation
            players[currentPlayer].setRoundScore(calculateScore(rays, atoms, userAtoms));
            //switch to next player
            currentPlayer = (currentPlayer + 1) % players.length;
    }

    private static int calculateScore(ArrayList<Ray> rays, Atom[] atoms, Atom[] userAtoms){
        int missedAtomsScore = 0;
        int rayMarkersScore = 0;
        for(Atom a : userAtoms) {
            if(a!=null && !Atom.containsAtom(atoms, a.getLocation())){missedAtomsScore += 5;}
        }
        for(Ray ray : rays){rayMarkersScore += ray.getNumberOfMarkers();}
        Message.printScoreBreakdown(missedAtomsScore, rayMarkersScore);
        return missedAtomsScore + rayMarkersScore;
    }

    public static void main(String[] args){
        if(args.length > 1 && args[1].equals("-D")) PERMISSION_MODE = DEV;

        BlackboxPlus blackboxPlus = new BlackboxPlus(2);
        blackboxPlus.play();
    }
}
