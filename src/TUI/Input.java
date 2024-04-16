package TUI;

import objects.Player;

import java.util.Scanner;

public class Input {
    private static final Scanner input = new Scanner(System.in);
    public static String getLine(){
        return input.nextLine().stripTrailing().stripLeading();
    }

    public static int getInt(){
        return input.nextInt();
    }

    public static Player getPlayer(int number){
        Player player;
        System.out.print("Please enter player "+number+" name:\t");
        while (true) {
            try {
                player = new Player(Input.getLine());
                // If input is valid, break out of the loop
                break;
            } catch (IllegalArgumentException e) {
                // Handle invalid input by printing assigned error message from thrown exception
                System.out.println(e.getMessage());
            }
        }
        return player;
    }
}
