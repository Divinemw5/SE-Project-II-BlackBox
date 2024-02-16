import java.util.Scanner;

public class Util {
    public static String printWelcome(){
        System.out.println("Welcome to BlackBox!!! :) :) :)");
        System.out.println("Please enter user information:\t");
        Scanner input = new Scanner(System.in);
        String username = input.nextLine();
        if(username.trim().isBlank()){
            throw new IllegalArgumentException("WEONG USNERNAMEN");
        }
        else{
            return username;
        }
    }



}
