package TUI;

import java.util.InputMismatchException;
import java.util.Scanner;
import objects.Atom;
import objects.Box;
import objects.Player;
import objects.Ray;

public class Input
{
    private static final Scanner input = new Scanner(System.in);
    public static String getLine()
    {
        return input.nextLine().stripTrailing().stripLeading();
    }

    public static int getInt() throws NumberFormatException
    {
        int x;
        try
        {
            x = Integer.parseInt(Input.getLine());
        }
        catch (NumberFormatException ex)
        {
            throw new NumberFormatException("Integer not found in input!");
        }
        return x;
    }

    /**
     * Handle input for creating a Ray with side number entered by User
     * @param box - box to generate ray in
     * @return - ray generated
     * @throws IllegalStateException - if user quit before a valid ray was created
     */
    public static Ray getRay(Box box) throws IllegalStateException
    {
        int userInput = 0;
        Ray ray = null;

        while ((ray == null && userInput != -1))
        {
            try
            {
                Message.print("Please enter side number (‘-1‘ to quit menu) : ");
                userInput = Input.getInt();
                if (userInput != -1)
                    ray = new Ray(userInput, box);
            }
            catch (IllegalArgumentException ex)
            {
                Message.printLine(ex.getMessage());
            }
            catch (InputMismatchException ignored)
            {
            }
        }
        if (ray == null)
        {
            throw new IllegalStateException("User quit before valid ray was created.");
        }
        return ray;
    }

    /**
     * function to help set up players from user input
     * @param number player index
     * @return acquired player
     */
    public static Player getPlayer(int number)
    {
        Message.print("Please enter player " + number + " name:\t");
        Player player = null;

        while (player == null)
        {
            try
            {
                player = new Player(Input.getLine());
            }
            catch (IllegalArgumentException e)
            {
                Message.printLine(e.getMessage());
            }
        }
        return player;
    }

    /**
     * Handle input for editing an atom with side numbers input by the user
     * @return atom created
     * @throws IllegalStateException if user quit before an atom was created
     */
    public static Atom getAtomFromUser() throws IllegalStateException
    {
        int[] userInput = {0, 0};
        Atom atom = null;
<<<<<<< HEAD

        while ((atom == null && userInput[0] != -1))
        {
            try
            {
                Message.printLine("Please enter two side numbers to edit an atom where they intersect " +
                        "(‘-1‘ to quit menu) : ");
=======
        while((atom == null && userInput[0] != -1)){
            try {
                Message.printLine("Please enter two side numbers to edit an atom where their arrows intersect(‘-1‘ to quit menu) : ");
>>>>>>> d17ce0a5255d76caa353e02c036fe92151ae9913
                Message.print("Enter side number 1 : ");
                userInput[0] = getInt();
                if (userInput[0] != -1)
                {
                    Message.print("Enter side number 2 : ");
                    userInput[1] = getInt();
                    int x = userInput[0];
                    int y = userInput[1];
                    atom = Util.getAtom(x, y);
                }
            }
            catch (IllegalArgumentException | IllegalStateException ex)
            {
                Message.printLine(ex.getMessage());
            }
            catch (InputMismatchException ignored)
            {
            }
        }
        if (atom == null)
        {
            throw new IllegalStateException("User quit before valid atom could be selected for editing");
        }
        return atom;
    }
}
