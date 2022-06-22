import java.awt.*;

/** Subclass to Obstacle. Used by AddObstacle */
public class OilSpill extends Obstacle
{

	public OilSpill(int[] coords, int numberOfVertices) 
	{
		super(numberOfVertices, Color.black, coords);
  }

	@Override public void checkCollision(Car car, Move move) 
	{
		int[] obsX = this.getObstacleX();
		int[] obsY = this.getObstacleY();

		for (int i = 0; i < car.getCarX().length; i++) 
		{
			if (isPointInsidePolynom(this.getNrOfVertices(), obsX, obsY, car.getCarX()[i], car.getCarY()[i])) 
			{
				car.turn(-4 * move.degree);
	    }
		}
  }
}

