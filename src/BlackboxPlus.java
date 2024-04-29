
import objects.*;
import TUI.*;
import java.util.ArrayList;
/**
 *  The BlackBoxPlus class contains our program flow throughout the game
 *
 */


public class BlackboxPlus {

    public static final int MAX_PLAYERS = 4;
    private final Player[] players;
    private int currentPlayer = 0;
    private int turnsPlayed = 0;
    private int roundsPlayed = 0;

    public static final int DEV = 1; // (Can display hidden atoms)
    public static final int USER = 0;
    public static int PERMISSION_MODE = USER; // PERMISSION_MODE set to DEV or USER


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

        Message.printWelcome();
        initializePlayers();
        while(!userInput.equalsIgnoreCase("quit")){
            Message.printPlayerWelcome(players[currentPlayer].getName());
            playTurn();

            if(turnsPlayed % players.length == 0){
                roundsPlayed++;
                ArrayList<Player> winningPlayers = calculateRoundWinner(players);
                Message.printRoundScoreBreakdown(winningPlayers);
                Message.printLine("Current Leaderboard:");
                Message.printLeaderboard(players);

            }
            Message.printExitMenu();
            userInput = Input.getLine();

        }
        ArrayList<Player> winningList = calculateFinalScore(players);
        Message.printLine("Final Leaderboard: ( rounds played: " + roundsPlayed+" )");
        Message.printFinalLeaderboard(players, winningList);
        Message.printGoodbye();
    }


    private void playTurn(){
        String userInput = "";
        Atom[] atoms = Atom.generateAtoms(6);
        Box box = new Box(atoms);
        ArrayList<Ray> rays = new ArrayList<>();
        Atom[] userAtoms = new Atom[] {null, null, null, null, null, null};

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
            turnsPlayed++;
            //print board with real atom positions (no ray markers)
            Message.printLine("Displaying board with real atom positions ...");
            Message.printBoard(Board.getAtomizedBoard(atoms));
            //score calculation
            players[currentPlayer].setRoundScore(calculateScore(rays, atoms, userAtoms));
            //switch to next player
            currentPlayer = (currentPlayer + 1) % players.length;
    }

    public static int calculateScore(ArrayList<Ray> rays, Atom[] atoms, Atom[] userAtoms){
        int missedAtomsScore = 0;
        int rayMarkersScore = 0;
        for(Atom a : userAtoms) {
            if (a == null){
                missedAtomsScore +=5;
            }
            else if(!Atom.containsAtom(atoms, a.getLocation())){missedAtomsScore += 5;}
        }
        for(Ray ray : rays){rayMarkersScore += ray.getNumberOfMarkers();}
        Message.printScoreBreakdown(missedAtomsScore, rayMarkersScore);
        return missedAtomsScore + rayMarkersScore;
    }

    public static ArrayList<Player> calculateFinalScore(Player players[]){
        ArrayList<Player> winnerList = new ArrayList<Player>();
        int winningScore = 0;

        for (Player player : players){
            if(player.getNumberOfWins() > winningScore){
                winningScore = player.getNumberOfWins();
                winnerList.clear();
                winnerList.add(player);
            }
            else if (player.getNumberOfWins() == winningScore){
                winnerList.add(player);
            }
        }
        return winnerList;
    }


    public static int getLowestScore(Player[] players){
        int min = players[0].getRoundScore();
        for(Player player : players){
            if(player.getRoundScore() < min) min = player.getRoundScore();
        }
        return min;
    }

    public static ArrayList<Player> calculateRoundWinner(Player players[]){
        ArrayList<Player> winnerList = new ArrayList<Player>();

        int min = getLowestScore(players);

        for (Player player : players){
            if(player.getRoundScore() == min) winnerList.add(player);
        }
        for (Player player : winnerList){
            player.incrementNumberOfWins();
        }
        return winnerList;
    }

    public static void main(String[] args){
        if(args.length > 0 && args[0].equals("-D")) {
        	PERMISSION_MODE = DEV;
        	Message.printLine("Running in developer mode, while playing a turn use the command ‘display hidden atoms‘ "
        			+ "to display the actual atom locations.");
        }

        BlackboxPlus blackboxPlus = new BlackboxPlus(2);
        blackboxPlus.play();
    }
}
