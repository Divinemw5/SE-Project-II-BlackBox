/**
 *  (Class Description)
 *
 */
public class Player {
    //player details, class requires constructor + getters & setters, toString method, equals method (compare wins)
    final int MIN_LENGTH = 3;
    final int MAX_LENGTH = 16;
    private String name;
    private int round_score = 0;    //player‘s score at end of last round
    private int number_of_wins = 0; //player‘s total number of wins against a player 2

    /**
     *
     * @param name - player name
     */
    Player(String name){
        //validity check
        if (!Util.unIsValid(name)){
            throw new IllegalArgumentException("Please enter a valid username [MAX LENGTH 16, MIN LENGTH 3]");
        }
        else {
            //assignment
            this.name = name;
        }
    }


    public String getName(){
        return this.name;
    }

    public void setName(String s){
        this.name = s;
    }
}
