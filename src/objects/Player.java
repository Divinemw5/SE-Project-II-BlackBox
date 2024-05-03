package objects;

/**
 *  Class Player :
 *  Stores Player Information
 */

public class Player
{
    public final int MIN_LENGTH = 3;
    public final int MAX_LENGTH = 16;
    private final String name;
    private int previousRoundScore = 0;    //player‘s score at end of last round
    private int numberOfWins = 0;  //player‘s total number of wins against a player 2

    /**
     * @param name - player name
     */
    public Player(String name)
    {
        if ((name == null) || name.isBlank() || (name.length() < MIN_LENGTH) || (name.length() > MAX_LENGTH)){
            throw new IllegalArgumentException("Please enter a valid username [MAX LENGTH "+MAX_LENGTH+", MIN LENGTH "+MIN_LENGTH+"]");
        }
        else {
            this.name = name;
        }
    }

    //getters
    public String getName(){
        return this.name;
    }
    public int getRoundScore() {
        return previousRoundScore;
    }
    public int getNumberOfWins() {return numberOfWins;}

    //setters
    public void setRoundScore(int roundScore) {
        this.previousRoundScore = roundScore;
    }
    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }
    public void incrementNumberOfWins(){this.numberOfWins++;}

}
