import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
Keeps track of which buttons are pressed and which movement should be done
 */
public class Controls extends KeyAdapter
{
	private boolean isUpPressed, isDownPressed, isLeftPressed, isRightPressed;
	private boolean isWPressed, isAPressed, isSPressed, isDPressed;
	private RaceTrack raceTrack;

	public Controls(RaceTrack raceTrack) 
	{
		this.raceTrack = raceTrack;
		resetKeys();
  }

	public void resetKeys() 
	{
		isUpPressed = false;
		isDownPressed = false;
		isLeftPressed = false;
		isRightPressed = false;
		isWPressed = false;
		isAPressed = false;
		isSPressed = false;
		isDPressed = false;
  }

	public void getKeysPressed(Car car) 
	{
		if (car.getColor().equals(Color.RED)) 
		{
	    getKeysPressedCar1(car);
		}
		if (car.getColor().equals(Color.BLUE)) 
		{
	  	getKeysPressedCar2(car);
		}
  }

	/** At this point we haven't found any better way to bind the keys contained by a specific car to the
	 * the different movements than this, and it contains code duplication.*/
	private void getKeysPressedCar1(Car car)
	{
		if (isUpPressed && isRightPressed) 
		{
		  raceTrack.moveCar(Move.FORWARDRIGHT, MaxSpeed.FORWARD, car);
		} 
		else if (isDownPressed && isLeftPressed) 
		{
		  raceTrack.moveCar(Move.BACKWARDLEFT, MaxSpeed.BACKWARD, car);
		} 
		else if (isUpPressed && isLeftPressed) 
		{
		  raceTrack.moveCar(Move.FORWARDLEFT, MaxSpeed.FORWARD, car);
		} 
		else if (isDownPressed && isRightPressed) 
		{
		  raceTrack.moveCar(Move.BACKWARDRIGHT, MaxSpeed.BACKWARD, car);
		} 
		else if (isUpPressed) 
		{
		  raceTrack.moveCar(Move.FORWARD, MaxSpeed.FORWARD, car);
		} 
		else if (isDownPressed) 
		{
		  raceTrack.moveCar(Move.BACKWARD, MaxSpeed.BACKWARD, car);
		} 
		else
		{
		  raceTrack.moveCar(Move.PARKED, MaxSpeed.FORWARD, car);
		}
	}

	/** At this point we haven't found any better way to bind the keys contained by a specific car to the
	 * the different movements than this, and it contains code duplication.*/
	private void getKeysPressedCar2(Car car) 
	{
		if (isWPressed && isDPressed) 
		{
			raceTrack.moveCar(Move.FORWARDRIGHT, MaxSpeed.FORWARD, car);
		} 
		else if (isSPressed && isAPressed) 
		{
			raceTrack.moveCar(Move.BACKWARDLEFT, MaxSpeed.BACKWARD, car);
		} 
		else if (isWPressed && isAPressed) 
		{
			raceTrack.moveCar(Move.FORWARDLEFT, MaxSpeed.FORWARD, car);
		} 
		else if (isSPressed && isDPressed) 
		{
			raceTrack.moveCar(Move.BACKWARDRIGHT, MaxSpeed.BACKWARD, car);
		} 
		else if (isWPressed) 
		{
			raceTrack.moveCar(Move.FORWARD, MaxSpeed.FORWARD, car);
		} 
		else if (isSPressed) 
		{
			raceTrack.moveCar(Move.BACKWARD, MaxSpeed.BACKWARD, car);
		} 
		else 
		{
			raceTrack.moveCar(Move.PARKED, MaxSpeed.FORWARD, car);
	  }
	}

	/** Checks if the up, down, left or right key is pressed. */
	@Override public void keyPressed(final KeyEvent ke) 
	{
		switch (ke.getKeyCode()) 
		{
	    case KeyEvent.VK_UP: isUpPressed = true; break;
	    case KeyEvent.VK_LEFT: isLeftPressed = true; break;
	    case KeyEvent.VK_RIGHT: isRightPressed = true; break;
	    case KeyEvent.VK_DOWN: isDownPressed = true; break;
	    case KeyEvent.VK_W: isWPressed = true; break;
	    case KeyEvent.VK_A: isAPressed = true; break;
	    case KeyEvent.VK_D: isDPressed = true; break;
	    case KeyEvent.VK_S: isSPressed = true; break;
		}
  }

	/** Checks if the up, down, left or right key is released. */
	@Override public void keyReleased(final KeyEvent ke) 
	{
		switch (ke.getKeyCode()) 
		{
	    case KeyEvent.VK_UP: isUpPressed = false; break;
	    case KeyEvent.VK_DOWN: isDownPressed = false; break;
	    case KeyEvent.VK_LEFT: isLeftPressed = false; break;
	    case KeyEvent.VK_RIGHT: isRightPressed = false; break;
	    case KeyEvent.VK_W: isWPressed = false; break;
	    case KeyEvent.VK_S: isSPressed = false; break;
	    case KeyEvent.VK_A: isAPressed = false; break;
	    case KeyEvent.VK_D: isDPressed = false; break;
		}
  }
}