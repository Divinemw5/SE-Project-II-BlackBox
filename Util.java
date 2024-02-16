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

    public static void main(String[] args) {
        printBoard();
    }

    private static String printIndent(int x){
        StringBuilder return_val = new StringBuilder();
        for(int i = 0; i < x; i++){
            return_val.append("  ");
        }
        return return_val.toString();
    }
    public static void printBoard(){

        StringBuilder onea = new StringBuilder();
        StringBuilder twoa = new StringBuilder();
        StringBuilder threea = new StringBuilder();

        String one = "    ██  ";
        String two = "  ██  ██";
        String three="██      ";


        for(int i = 0; i <5; i++){
            onea.append(one);
            twoa.append(two);
            threea.append(three);
            if(i == 4){
                threea.append(three.trim());
            }
        }

        //for(int i = 0; i < )
        System.out.println(printIndent(10) + onea.toString());
        System.out.println(printIndent(10) + twoa.toString());
        System.out.println(printIndent(10) + threea.toString());
        System.out.println(printIndent(10) + threea.toString());
        System.out.println(printIndent(10) + threea);



/*
        System.out.println("     ██      ██");
        System.out.println("   ██  ██  ██  ██");
        System.out.println(" ██      ██      ██");
        System.out.println(" ██      ██      ██");
        System.out.println(" ██      ██      ██");
        System.out.println("   ██  ██  ██  ██");
        System.out.println("     ██      ██");

 */


    }



}
