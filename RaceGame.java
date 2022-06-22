/**Main Class which starts the game and creates the RaceTrack, GameFrame and DrawComponents*/
public final class RaceGame
{
  private RaceGame() {}

	public static void main(String[] args) 
	{
		final RaceTrack raceTrack = new RaceTrack();
		final GameFrame frame = new GameFrame(raceTrack);
		frame.addKeyListener(raceTrack.getControls());
		DrawComponents paintComp = new DrawComponents(raceTrack);
		frame.add(paintComp);
		raceTrack.addBoardListener(paintComp);
  }
}