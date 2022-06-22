public class Main
{
	public Main() 
	{
		// final int height = 1100, width = 1930;
		//final int height = 13,width = 24;
		final RaceTrack raceTrack = new RaceTrack();
		final GameFrame frame = new GameFrame(raceTrack);
		frame.addKeyListener(raceTrack.getControls());
		PaintComponents paintComp = new PaintComponents(raceTrack);
		//frame.add(paintComp);
		raceTrack.addBoardListener(paintComp);
  }

	public static void main(String[] args) 
	{
		new Main();
  }
}