package objects;

/**
 *  (Class Description)
 *
 */
public class Player {
    //player details, class requires constructor + getters & setters, toString method, equals method (compare wins)
    final int MIN_LENGTH = 3;
    final int MAX_LENGTH = 16;
    private String name;



    private int roundScore = 0;    //player‘s score at end of last round
    private int numberOfWins = 0; //player‘s total number of wins against a player 2

    /**
     *
     * @param name - player name
     */
    public Player(String name){

        //validity check
        if ((name == null) || name.isBlank() || (name.length() <= 2) || (name.length() >= 17)){
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

    public void setRoundScore(int round_score) {
        this.roundScore = round_score;
    }

    public void setNumberOfWins(int number_of_wins) {
        this.numberOfWins = number_of_wins;
    }
    public int getRoundScore() {
        return roundScore;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }
}
