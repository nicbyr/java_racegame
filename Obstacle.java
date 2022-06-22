import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/** Super class of obstacles. Includes methods for setting coordinates of the obstacles and
 * to check if they have a car inside of them.*/
public abstract class Obstacle
{
	protected Color color;
	protected int nrOfVertices;
	protected int[] obstacleX, obstacleY, coords;

	protected Obstacle(int nrOfVertices, Color color, int[] coords) 
	{
		this.nrOfVertices = nrOfVertices;
		this.color = color;
		this.coords = coords;
		setCoordinates();
  }

	/**Sets the corners of the polygon relative to origoX and origoY, which is coords[0] and coords[1],
	 * at the distance defined by the formula.
	 * */
	private void setCoordinates() 
	{
		final List<Integer> xCoords = new ArrayList<>();
		final List<Integer> yCoords = new ArrayList<>();
		double slice = 2 * Math.PI / nrOfVertices;
		for (int i = 0; i < nrOfVertices; i++) 
		{
				double angle = slice * i + Math.PI / 4;
				xCoords.add((int) (coords[0] + coords[2] * Math.cos(angle)));
				yCoords.add((int) (coords[1] + coords[3] * Math.sin(angle)));
		}
		obstacleX = new int[xCoords.size()];
		obstacleY = new int[yCoords.size()];
		for (int i = 0; i < xCoords.size(); i++) 
		{
				obstacleX[i] = xCoords.get(i);
				obstacleY[i] = yCoords.get(i);
		}
  }

	/**Checks whether or not a point is inside a polygon. This algorithm is pretty and we
	 * couldn't find a good way to simpliy it. We have a source to where we found it in our report in section 6.3.*/
	public boolean isPointInsidePolynom(int nrOfVertices, int[] polyX, int[] polyY, int pointX, int pointY) 
	{
		boolean c = false;
		for (int i = 0, j = nrOfVertices - 1; i < nrOfVertices; j = i++) 
		{
			if (((polyY[i] > pointY) != (polyY[j] > pointY)) &&
			(pointX < (polyX[j] - polyX[i]) * (pointY - polyY[i]) / (polyY[j] - polyY[i]) + polyX[i])) 
			{
				c = !c;
			}
		}
		return c;
  }

	public abstract void checkCollision(Car car, Move move);

	public int getNrOfVertices() 
	{
		return nrOfVertices;
  }

	public Color getColor() 
	{
		return color;
  }

	public int[] getObstacleX() 
	{
		return obstacleX;
  }

	public int[] getObstacleY() 
	{
		return obstacleY;
	}
}
