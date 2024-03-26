import java.util.ArrayList;

/**
 *  Main class (contains program flow throughout the game)
 *
 */
public class BlackboxPlus {
    public static void main(String[] args) {

        Box emptyBox = new Box(new Atom[] {null});


        //Initialize program state
        String userInput = "";
        Player [] player = new Player[2];
        int currentPlayer = 0; //set player who goes first

        //Welcome the user and take player information
        Util.printWelcome();
        player[0] = Util.createPlayer(1);
        //player[1] = Util.createPlayer(2);

        //player 1 start game message (add functionality into loop and allow switching between players, convert to function in Util)
        System.out.println("Hello, "+player[currentPlayer].getName()+"!");
        //System.out.println("It‘s your turn!");

        /*start new round*/
        while(!userInput.equals("quit"))
        {

            //initialize game state
            Atom[] atoms = Atom.generateAtoms(6);   //generate random atoms
            Box box = new Box(atoms);                  //create the empty board
            ArrayList<Ray> rays = new ArrayList<>();   //start empty array list (pass to Util to add ray markers to board)
            Atom[] atoms1 = new Atom[] {null, null, null, null, null, null};

            //play round /*do not let user end round if no atoms have been placed and no rays have been entered into the box (i.e no score calculation)*/
            while(!userInput.equals("end round")){
                /*output board with ray markers and atoms placed by user*/
                Util.printBoard(Util.colourBoard(Util.getAtomizedBoard(atoms))); //ONLY USING ATOMIZED BOARD FOR RAY TESTING
                System.out.println("OPTIONS : send ray into box (enter ‘ray‘)\t place atoms on board (enter ‘place atoms‘)\t end round and calculate score (enter ‘end round‘)");
                userInput = Util.getLine();

                /*place ray at entry point*/
                if(userInput.equalsIgnoreCase("ray")){
                    try{
                        System.out.print("Enter ray entry number : ");
                        int entry = Integer.parseInt(Util.getLine());
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
                    }
                }
                else if (userInput.equalsIgnoreCase("atoms")){

                    System.out.println("Enter 2 side numbers to place an atom where they intersect");
                    System.out.println("Enter first number:\n");
                    int x = Integer.parseInt(Util.getLine());
                    System.out.println("Enter second number:\n");
                    int y = Integer.parseInt(Util.getLine());

                    Ray rayx = new Ray(x, emptyBox);
                    Ray rayy = new Ray(y, emptyBox);

                    ArrayList<Coordinate> coordsx = rayx.getCoords();
                    ArrayList<Coordinate> coordsy = rayy.getCoords();

                    Atom atomToPlace;

                    for(Coordinate i : coordsx){
                        if(coordsy.contains(i)){
                            atomToPlace = new Atom(i.getX(), i.getY(), i.getZ());
                        }
                    }
                }
            }

            System.out.println("WOULD YOU LIKE TO CONTINUE (enter ‘quit‘ to exit program) "+/*(enter ‘atoms‘ to show hidden atoms)*/" (enter ‘continue‘ to switch players and start new game)");
            userInput = Util.getLine();
            //test functionality
            /*if(userInput.equals("atoms")){
                Util.printBoard(Util.getAtomizedBoard(atoms));
            }*/
        }
        //print goodbye message
    }
}
