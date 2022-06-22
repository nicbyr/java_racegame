import java.awt.*;

public class Car2 implements RaceTrackObjects
{
	private Color color;
	private int speed;
	private double degree;
	private int flx, fly, frx, fry, blx, bly, brx, bry, mrx, mry, mlx, mly;
	private int lap = 1;
	private double prevFlxr, prevFlyr, prevFrxr, prevFryr, prevBlxr, prevBlyr, prevBrxr, prevBryr;
	private double prevMrxr, prevMryr, prevMlxr, prevMlyr;
	private double flxr, flyr, frxr, fryr, blxr, blyr, brxr, bryr, mrxr, mryr, mlxr, mlyr;
	private double rotX, rotY, prevRotX, prevRotY;
	private int[] newY, newX;
	private boolean[] checkPoints;
	private final static int CARWIDTH = 40, CARHEIGHT = 20, MINUTE = 60;
	private long startTime = System.currentTimeMillis();

	public Car2(Color color, int[] startCoords) 
	{
		this.degree = 0;
		this.speed = 0;
		this.color = color;
		rotX = 10;
		rotY = 10;
		flx = (int)rotX + CARWIDTH / 2;
		fly = (int)rotY + CARHEIGHT / 2;
		frx = (int)rotX + CARWIDTH / 2;
		fry = (int)rotY - CARHEIGHT / 2;
		brx = (int)rotX - CARWIDTH / 2;
		bry = (int)rotY - CARHEIGHT / 2;
		blx = (int)rotX - CARWIDTH / 2;
		bly = (int)rotY + CARHEIGHT / 2;
		mrx = (int)rotX;
		mly = (int)rotY + CARHEIGHT / 2;
		mlx = (int)rotX;
		mry = (int)rotY - CARHEIGHT / 2;
		rotX += startCoords[0];
		rotY += startCoords[1];
		newX = new int[] { flx, frx, mrx, brx, blx, mlx };
		newY = new int[] { fly, fry, mry, bry, bly, mly };
		checkPoints = new boolean[] { false, false, false, false };
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
		if (speed > 0) 
		{
	    speed--;
		} 
		else if (speed < 0) 
		{
	    speed++;
		}
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
		prevFlxr = flxr;
		prevFlyr = flyr;
		prevFrxr = frxr;
		prevFryr = fryr;
		prevMrxr = mrxr;
		prevMryr = mryr;
		prevBrxr = brxr;
		prevBryr = bryr;
		prevBlxr = blxr;
		prevBlyr = blyr;
		prevMlxr = mlxr;
		prevMlyr = mlyr;

		double radians = Math.toRadians(degree);
		flxr = Math.cos(radians) * flx - Math.sin(radians) * fly;
		flyr = Math.sin(radians) * flx + Math.cos(radians) * fly;
		frxr = Math.cos(radians) * frx - Math.sin(radians) * fry;
		fryr = Math.sin(radians) * frx + Math.cos(radians) * fry;
		mrxr = Math.cos(radians) * mrx - Math.sin(radians) * mry;
		mryr = Math.sin(radians) * mrx + Math.cos(radians) * mry;
		brxr = Math.cos(radians) * brx - Math.sin(radians) * bry;
		bryr = Math.sin(radians) * brx + Math.cos(radians) * bry;
		blxr = Math.cos(radians) * blx - Math.sin(radians) * bly;
		blyr = Math.sin(radians) * blx + Math.cos(radians) * bly;
		mlxr = Math.cos(radians) * mlx - Math.sin(radians) * mly;
		mlyr = Math.sin(radians) * mlx + Math.cos(radians) * mly;
		rotX += Math.cos(radians) * speed;
		rotY += Math.sin(radians) * speed;
		flxr += rotX;
		flyr += rotY;
		frxr += rotX;
		fryr += rotY;
		mrxr += rotX;
		mryr += rotY;
		brxr += rotX;
		bryr += rotY;
		blxr += rotX;
		blyr += rotY;
		mlxr += rotX;
		mlyr += rotY;
		newX = new int[] { (int) flxr, (int) frxr, (int) mrxr, (int) brxr, (int) blxr, (int) mlxr };
		newY = new int[] { (int) flyr, (int) fryr, (int) mryr, (int) bryr, (int) blyr, (int) mlyr };
  }

	public void setPrevCoords()
	{
		speed = 0;
		//	degree -= prevDegree;
		rotX = prevRotX - speed;
		rotY = prevRotY - speed;
		newX = new int[] { (int) prevFlxr, (int) prevFrxr, (int) prevMrxr ,(int) prevBrxr, (int) prevBlxr, (int) prevMlxr};
		newY = new int[] { (int) prevFlyr, (int) prevFryr, (int) prevMryr, (int) prevBryr, (int) prevBlyr, (int) prevMlyr};
  }

	public boolean[] getCheckPoints() 
	{
		return checkPoints;
  }

	public void incrementLap() 
	{
		lap++;
  }

	public int getLap() 
	{
		return lap;
  }
	public int getNrOfVertices() 
	{
		final int nrOfVertices = 6;
		return nrOfVertices;
  }

	public int[] getNewY() 
	{
		return newY;
  }

	public int[] getNewX() 
	{
		return newX;
  }

	public int getSpeed() 
	{
		return speed;
  }

	public Color getColor() 
	{
		return color;
  }

	public int getCurrentseconds()
	{
		return (int)((System.currentTimeMillis() - startTime) /1000) % MINUTE;
  }
	
	public int getCurrentMin()
	{
		return getCurrentseconds() / MINUTE;
  }
		
	public int getTimeInSec()
	{
		int getTiminSec = getCurrentMin()*MINUTE + getCurrentseconds();
		return getTiminSec;
  }
	
	public void setCheckPoint(int iD)
	{
		checkPoints[iD-1] = true;
  }
}