import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**Adds highscores to a list and sort it. The class is a Singleton because we want highscores from different
 * races to be stored in a single highscorelist. */
public final class HighScoreList
{
    private static final HighScoreList INSTANCE = new HighScoreList();
    private List<HighScore> highScoreList;

    private HighScoreList() {
	highScoreList = new ArrayList<>();
    }

    public static HighScoreList getInstance() 
    {
	    return INSTANCE;
    }

    public void addHighScore(HighScore highscore) 
    {
	    highScoreList.add(highscore);
	    Collections.sort(highScoreList, new ScoreComparator());
    }
    public String printHighScore() 
    {
        StringBuilder score = new StringBuilder();
        for (HighScore highScore: highScoreList) 
        {
            score.append(highScore).append("\n");
        }
        return score.toString();
    }
}
