import java.awt.*;

/** Subclass to Obstacle. Used by AddObstacle */
public class ConcreteBlock extends Obstacle
{
	public ConcreteBlock(int[] conBlock, int numberOfVertices) 
	{
		super(numberOfVertices, Color.GRAY, conBlock);
  }

	@Override public void checkCollision(final Car car, final Move move) 
	{
		for (int i = 0; i < car.getCarX().length; i++) 
		{
			if (isPointInsidePolynom(getNrOfVertices(), getObstacleX(), getObstacleY(), car.getCarX()[i], car.getCarY()[i])) 
			{
			car.slowDown();
			car.setPrevCoords();
			car.direction(-5 * move.direction);
			car.turn(-2 * move.degree);
	    }
		}
  }
}