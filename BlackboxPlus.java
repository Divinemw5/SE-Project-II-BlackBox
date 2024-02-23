
/**
 *  Main class (contains program flow throughout the game)
 *
 */
public class BlackboxPlus {
    //Welcome the user and take player information

    public static void main(String[] args) {

        String userInput = "";
        Player [] player = new Player[2];
        int currentPlayer = 0; //set player who goes first

        Util.printWelcome();                    //print welcome to user
        player[0] = Util.createPlayer(1);
        //player[1] = Util.createPlayer(2);

        //player 1 start game message (add functionality into loop and allow switching between players, convert to function in Util)
        System.out.println("Hello, "+player[currentPlayer].getName()+"!");
        //System.out.println("It‘s your turn!");

        while(!userInput.equals("quit"))
        {
            //initialize game state
            Atom[] atoms = Atom.generateAtoms(6);   //generate random atoms
            Box box = new Box(atoms);               //create the empty board

            //play round
            while(!userInput.equals("end round")){
                break;
            }

            //test functionality
            if(userInput.equals("atoms")){
                Util.printBoard(Util.getAtomizedBoard(atoms));
            }
            else Util.printBoard(Util.getEmptyBoard());

            System.out.println("WOULD YOU LIKE TO CONTINUE (enter ‘quit‘ to exit program) (enter ‘atoms‘ to show hidden atoms)");
            userInput = Util.lineInput();
        }
    }
}
