import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Builds the RaceTrack from squares created by arrays. Specifies which squares are offroad or onroad. Creates a Controls object
 * and keeps track of the cars position.
 */
public class RaceTrack
{
	/** The length of the side of a square of the Racetrack object in pixels. */
	private int squareSize;
	private SquareTypes[][] squareType;
	private List<RaceTrackListeners> listener;
	private List<Car> carList;
	private List<Obstacle> obstacle;
	private int endTime;
	private Controls controls;
	private boolean isGameOver = false;
	private AddObstacle ao = null;
	/** We needed getters for WIDTH and HEIGHT, therefore they are final static */
	private final static int WIDTH = 24, HEIGHT = 13;

	/** Sets the WIDTH and height in a squarecoordinate system and creates a new getKeysPressed object. */
	public RaceTrack() 
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double frameWidth = screenSize.getWidth();
		this.squareSize = (int)frameWidth / WIDTH;
		squareType = new SquareTypes[WIDTH + 2][HEIGHT + 2];
		obstacle = new ArrayList<>();
		carList = new ArrayList<>();
		listener = new ArrayList<>();
		controls = new Controls(this);
		notifyListeners();
  }

	public void tick() 
	{
		for (Car car : carList) 
		{
	    controls.getKeysPressed(car);
			if (car.getCheckPoints()[car.getCheckPoints().length - 1])
			{
				endTime = car.getTimeInSec();
				isGameOver = true;
	    }
		}
		notifyListeners();
  }

	public void createTrack(int track) 
	{
		obstacle.clear();
		PlaceSquares ps = new PlaceSquares(WIDTH, HEIGHT, squareType);
		ao = new AddObstacle(obstacle);
		ps.placeSquares(track);
		ao.addObstacles(track, squareSize);
  }

	private boolean checkCollisionCars(Car car1, Car car2) 
	{
		int carPoints = 6;
		for (int i = 0; i < car1.getCarX().length; i++) 
		{
			int x = car1.getCarX()[i];
			int y = car1.getCarY()[i];
			if (isPointInsidePolynom(carPoints, car2.getCarX(), car2.getCarY(), x, y)) 
			{
				return true;
			}
		}
		return false;
  }

	/**Checks whether or not a point is inside a polygon. This algorithm is pretty and we
	 * couldn't find a good way to simpliy it. We have source to where we found it in our report.*/
	private boolean isPointInsidePolynom(int nrOfVertices, int[] polyX, int[] polyY, int pointX, int pointY) 
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

	public void createCars(int cars) 
	{
		controls.resetKeys();
		for (int i = 1; i <= cars; i++) 
		{
			if (i == 1) 
			{
				final int[] car1Coords = new int[] { ao.getCheck4()[0] - 60, ao.getCheck4()[1] };
				carList.add(new RaceCar(Color.RED, car1Coords));
			} 
			else if (i == 2) 
			{
				final int[] car2Coords = new int[] { ao.getCheck4()[0] - 60, ao.getCheck4()[1] - 50 };
				carList.add(new SpaceCar(Color.BLUE, car2Coords));
	    }
		}
  }

	public void moveCar(Move move, MaxSpeed maxSpeed, Car car) 
	{
		car.movement();
		if (!isOutSide(car.getCarX(), car.getCarY())) 
		{
	    boolean offRoad = isOffRoad(car.getCarX(), car.getCarY());
			if (move.direction == 0 || !isUnderMaxSpeed(move, maxSpeed, offRoad, car.getSpeed())) 
				{ car.slowDown(); } 
			else  
				{ car.direction(move.direction); }
	    car.turn(move.degree);
		} 
		else 
		{
	    car.slowDown();
	    car.setPrevCoords();
	    car.direction(-7 * move.direction);
	    car.turn(-2 * move.degree);
		}

		for (Car c : carList) 
		{
			if (checkCollisionCars(car, c) && !car.getColor().equals(c.getColor())) 
			{
				c.setPrevCoords();
				c.slowDown();
				c.direction(7 * c.getSpeed());
				car.setPrevCoords();
				car.slowDown();
				car.direction(-7 * move.direction);
				car.turn(-3 * move.degree);
			}
		}
		for (Obstacle obs : obstacle) 
			{ obs.checkCollision(car, move); }
  }

  public void clearList() { carList.clear(); }

	private boolean isUnderMaxSpeed(Move move, MaxSpeed maxSpeed, boolean offRoad, int curSpeed) 
	{
		int max = maxSpeed.onRoad;
		if (offRoad) { max = maxSpeed.offRoad; }
		if (move.direction > 0 && curSpeed < max || move.direction < 0 && curSpeed > max) 
			{ return true; }
		return false;
  }

	private boolean isOffRoad(int[] xCoord, int[] yCoord) 
	{
		for (int i = 0; i < xCoord.length; i++) 
		{
	    int xCar = xCoord[i] / squareSize;
	    int yCar = yCoord[i] / squareSize;
			if (squareType[xCar + 1][yCar + 1] == SquareTypes.OFFROAD) 
				{ return true; }
		}
		return false;
  }

	/** Checks if Controls is OUTSIDE or not */
	private boolean isOutSide(int[] xCoord, int[] yCoord) 
	{
		for (int i = 0; i < xCoord.length; i++) 
		{
	    int xCar = xCoord[i] / squareSize;
	    int yCar = yCoord[i] / squareSize;
			if (squareType[xCar + 1][yCar + 1] == SquareTypes.OUTSIDE || xCoord[i] <= 0 || yCoord[i] <= 0) 
				{ return true; }
		}
		return false;
  }

  public void setisGameOver() { isGameOver = false; }

	public void notifyListeners() 
	{
		for (RaceTrackListeners rl : listener) 
			{ rl.updateRaceTrack(); }
  }

	public void addBoardListener(RaceTrackListeners rl) 
		{ listener.add(rl); }

	public int getWidth() 
		{ return WIDTH; }

	public int getHeight() 
		{ return HEIGHT; }

	public Iterable<Obstacle> getObstacle() 
	 { return obstacle; }

	public int  getEndTime() 
		{ return endTime; }

	public boolean getIsGameOver() 
		{ return isGameOver; }

	public KeyListener getControls() 
		{ return controls; }

	public Iterable<Car> getCarList() 
		{ return carList; }

	public SquareTypes getSquareType(int col, int row) 
		{ return squareType[col + 1][row + 1];}

	public int getSquareSize() 
		{ return squareSize; }
}