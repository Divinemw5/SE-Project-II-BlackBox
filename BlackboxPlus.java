/**
 *  Main class (contains program flow throughout the game)
 *
 */
public class BlackboxPlus {
    //Welcome the user and take player information

    public static void main(String[] args) {

        String userInput = "";
        Player player1;
        Atom[] atoms;
        Box box;
        while(!userInput.equals("quit")) {


            atoms = Atom.generateAtoms(6);//generate random atoms
            box = new Box(atoms);               //create the empty board
            Util.printWelcome();                    //print welcome to user

            //get username
            while (true) {
                try {
                    player1 = new Player(Util.lineInput());
                    // If input is valid, break out of the loop
                    break;
                } catch (IllegalArgumentException e) {
                    // Handle invalid input by printing assigned error message from thrown exception
                    System.out.println(e.getMessage());
                }
            }

            Util.printBoard(Util.getEmptyBoard());
            System.out.println("WOULD YOU LIKE TO PLAY AGAIN OR NOT jhadsfKJHDFKSJDHFKJSHDFKJ");
            userInput = Util.lineInput();
        }
    }

    //Create new Box with randomly generated atoms

}
