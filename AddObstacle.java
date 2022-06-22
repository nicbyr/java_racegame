import java.util.List;

/**Add obstacles to a racetrack object. Creates all kinds of obstacles.*/
public class AddObstacle
{
	private List<Obstacle> obstacle;
	private int[] check4 = null;

	public AddObstacle(final List<Obstacle> obstacle) 
	{
		this.obstacle = obstacle;
  }

	/** The numbers in the arrays represents origoX, origoY, width/2, height/2
 	* and an identification-number used for making checkpoints */
	protected void addObstacles(int track, int squareSize) 
	{
		final int nrOfVerticesCheck = 4;
		if (track == 1) 
		{
			final int[] check1 = new int[] { 11*squareSize, 3*squareSize, 4*squareSize/3, squareSize/4, 1};
			final int[] check2 = new int[] { 22*squareSize, 7*squareSize, 4*squareSize/3, squareSize/4, 2 };
			final int[] check3 = new int[] { 2*squareSize, 7*squareSize, 4*squareSize/3, squareSize/4, 3 };
			check4 = new int[] { 13*squareSize, 8*squareSize, squareSize/4, 4*squareSize/3, 4 };
			final int[] oil1 = new int[] { 22*squareSize, 5*squareSize, squareSize/4, squareSize/4 };
			final int[] oil2 = new int[] { 22*squareSize, 7*squareSize, squareSize/4, squareSize/4 };
			final int[] oil3 = new int[] { 2*squareSize, 9*squareSize, squareSize/4, squareSize/4 };
			final int[] oil4 = new int[] { 11*squareSize, 5*squareSize, squareSize/4, squareSize/4 };
			final int[] oil5 = new int[] { 2*squareSize, 5*squareSize, squareSize/4, squareSize/4 };
			final int[] oil6 = new int[] { 2*squareSize, 2*squareSize, squareSize/4, squareSize/4 };
			final int[] conBlock1 = new int[] { 2*squareSize, 8*squareSize, squareSize/2, squareSize/2 };
			final int[] conBlock2 = new int[] { 27*squareSize/2, 13*squareSize/2, 6*squareSize, squareSize/2 };
			final int[] conBlock3 = new int[] { 11*squareSize, 11*squareSize, squareSize, squareSize/2 };
			obstacle.add(new CheckPoint(check1, 1, nrOfVerticesCheck));
			obstacle.add(new CheckPoint(check2, 2, nrOfVerticesCheck));
			obstacle.add(new CheckPoint(check3, 3, nrOfVerticesCheck));
			obstacle.add(new CheckPoint(check4, 4, nrOfVerticesCheck));
			final int nrOfVerticesOil = 1000;
			obstacle.add(new OilSpill(oil1, nrOfVerticesOil));
			obstacle.add(new OilSpill(oil2, nrOfVerticesOil));
			obstacle.add(new OilSpill(oil3, nrOfVerticesOil));
			obstacle.add(new OilSpill(oil4, nrOfVerticesOil));
			obstacle.add(new OilSpill(oil5, nrOfVerticesOil));
			obstacle.add(new OilSpill(oil6, nrOfVerticesOil));
			final int nrOfVerticesConBlock = 4;
			obstacle.add(new ConcreteBlock(conBlock1, nrOfVerticesConBlock));
			obstacle.add(new ConcreteBlock(conBlock2, nrOfVerticesConBlock));
			obstacle.add(new ConcreteBlock(conBlock3, nrOfVerticesConBlock));
		}
		else if (track == 2) 
		{
			final int[] check1 = new int[] {22*squareSize, 7*squareSize, 4*squareSize/3, squareSize/4, 2 };
			final int[] check2 = new int[] { 11*squareSize, 2*squareSize, 20, 4*squareSize/3, 1 };
			final int[] check3 = new int[] { 2*squareSize, 7*squareSize, 4*squareSize/3, squareSize/4, 3 };
			check4 = new int[] { 12*squareSize, 11*squareSize, squareSize/4, 4*squareSize/3, 4 };

			obstacle.add(new CheckPoint(check1, 1, nrOfVerticesCheck));
			obstacle.add(new CheckPoint(check2, 2, nrOfVerticesCheck));
			obstacle.add(new CheckPoint(check3, 3, nrOfVerticesCheck));
			obstacle.add(new CheckPoint(check4, 4, nrOfVerticesCheck));
		}
  }

  public int[] getCheck4() { return check4; }
}
