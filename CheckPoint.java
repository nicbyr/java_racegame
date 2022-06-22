import java.awt.*;

/**Subclass to Obstacle. Checkpoint objects is used in AddObstacle*/
public class CheckPoint extends Obstacle
{
    private int identification;

    /**
     *Takes an array of origoX, origoY, width, heigh and a int as identification
     */
    public CheckPoint(int[] coords, int identification, int numberOfVertices)
    {
			super(numberOfVertices, Color.yellow, coords);
			this.identification = identification;
    }

	@Override public void checkCollision(final Car car, final Move move) 
	{
		for (int i = 0; i < car.getCarX().length; i++) 
		{
			if (isPointInsidePolynom(this.getNrOfVertices(), getObstacleX(), getObstacleY(), car.getCarX()[i], car.getCarY()[i]))
			{
				if (identification == 1) 
				{
					car.setCheckPoint(1);
				} 
				else if (identification == 2 && car.getCheckPoints()[0]) 
				{
					car.setCheckPoint(2);
				} 
				else if (identification == 3 && car.getCheckPoints()[1]) 
				{
						car.setCheckPoint(3);

				} 
				else if (identification == 4 && car.getCheckPoints()[2]) 
				{
		    	final int nrOfLaps = 1;

					if (car.getLap() < nrOfLaps) 
					{
						car.resetCPoints();
						car.incrementLap();
					} 
					else if (car.getLap() == nrOfLaps) 
					{
						car.incrementLap();
						car.setCheckPoint(4);
		    	}
				}
	    }
		}
  }
}