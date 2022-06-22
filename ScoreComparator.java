import java.util.Comparator;

/** Compare two Highscore objects*/
public class ScoreComparator implements Comparator<HighScore>
{
	@Override public int compare(final HighScore o1, final HighScore o2) 
	{
		if (o2.getScore() < o1.getScore()) 
		{
	    return 1;
		} 
		else if (o2.getScore() > o1.getScore()) 
		{
	    return -1;
		}
		return 0;
  }
}
