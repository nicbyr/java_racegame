/** Stores the score and name of the player who finished first*/
public class HighScore
{
    private int score;
    private String name;

    public HighScore(final int score, final String name) 
    {
        this.score = score;
        this.name = name;
    }

    @Override public String toString() 
    {
	    return name + ": " + score;
    }

    public int getScore() 
    {
	    return score;
    }
}
