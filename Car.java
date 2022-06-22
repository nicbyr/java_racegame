import java.awt.*;

/**Super class to RaceCar and SpaceCar. Keeps of the position of the car on the racetrack and which
 * checkpoints the car has passed.
 */
public class Car
{
	private Color color;
	private int speed, lap = 1;
	private double rotX, rotY, prevRotX, prevRotY, degree;
	private int[] carY, carX, startX, startY, prevCarX, prevCarY;
	private boolean[] checkPoints;
	/**MINUTE has the length of a minute in seconds. NR_OF_VERTICES is the number of points
	 * of the car that we use when drawing the car as a polygon and to check collisions.*/
	private final static int MINUTE = 60, NR_OF_VERTICES = 6;
	private long startTime = System.currentTimeMillis();

	protected Car(Color color, int[] startCoords) 
	{
		this.degree = 0;
		this.speed = 0;
		this.color = color;
		carX = new int[NR_OF_VERTICES];
		carY = new int[NR_OF_VERTICES];
		prevCarX = new int[NR_OF_VERTICES];
		prevCarY = new int[NR_OF_VERTICES];
		startX = new int[NR_OF_VERTICES];
		startY = new int[NR_OF_VERTICES];
		/** @param rotX is centerpoint of the car, which the other points rotate around*/
		rotX = 10;
		/** @param rotY is centerpoint of the car, which the other points rotate around*/
		rotY = 10;
		rotX += startCoords[0];
		rotY += startCoords[1];
		checkPoints = new boolean[] { false, false, false, false };
  }

	protected void setCarCoords(int[] newtX, int[]newY)
	{
		for (int i = 0; i < newtX.length; i++) 
		{
			this.startX[i] = newtX[i];
			this.startY[i] = newY[i];
		}
  }

	public void resetCPoints() 
	{
		for (int i = 0; i < checkPoints.length ; i++) 
		{
			checkPoints[i] = false;
		}
  }

	public void slowDown() 
	{
		if (speed > 0) { speed--; } 
		else if (speed < 0) { speed++; }
  }

	public void direction(int direction) 
	{
		this.speed += direction;
  }

	public void turn(double degree) 
	{
		this.degree += degree;
  }

	public void movement() 
	{
		prevRotX = rotX;
		prevRotY = rotY;
		double radians = Math.toRadians(degree);
		rotX += Math.cos(radians) * speed;
		rotY += Math.sin(radians) * speed;

		for (int i = 0; i < carX.length ; i++) 
		{
			prevCarX[i] = carX[i];
			prevCarY[i] = carY[i];
			carX[i] = (int) (Math.cos(radians) * startX[i] - Math.sin(radians) * startY[i] + rotX);
			carY[i] = (int) (Math.sin(radians) * startX[i] + Math.cos(radians) * startY[i] + rotY);
		}
  }

	public void setPrevCoords()
	{
		speed = 0;
		rotX = prevRotX - speed;
		rotY = prevRotY - speed;
		for (int i = 0; i < carX.length; i++) 
		{
			carX[i] = prevCarX[i];
			carY[i] = prevCarY[i];
		}
  }

	public boolean[] getCheckPoints() 
	{
		return checkPoints;
  }

	public void incrementLap() { lap++; }

  public int getLap() { return lap; }

	public int getNrOfVertices() 
	{
		return NR_OF_VERTICES;
  }

	public int[] getCarY() 
	{
		return carY;
  }

	public int[] getCarX() 
	{
		return carX;
  }

	public int getSpeed() 
	{
		return speed;
  }

	public Color getColor() 
	{
		return color;
  }

	/**If you would like to see your time in seconds while playing you could use this method */
	private int currentSeconds()
	{
		return (int)((System.currentTimeMillis() - startTime) /1000) % MINUTE;
  }
	/**If you would like to see your time in minutes while playing you could use this method */
	private int currentMin()
	{
		return currentSeconds() / MINUTE;
  }

	public int getTimeInSec()
	{
		int getTiminSec = currentMin() * MINUTE + currentSeconds();
		return getTiminSec;
  }
	public void setCheckPoint(int iD) 
	{
		checkPoints[iD-1] = true;
  }
}