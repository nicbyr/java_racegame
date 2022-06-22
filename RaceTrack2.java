import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Builds the RaceTrack from squares created by arrays. Specifies which squares are offroad or onroad. Creates a Controls object
 * and keeps track of the cars position.
 */
public class RaceTrack2
{
	/** The length of the side of a square of the Racetrack object in pixels. */
	private final static int SQUARESIZE = 80;
	private int width, height, realWidth, realHeight, nrOfPlayers = 0;
	private SquareTypes[][] squareType;
	private List<RaceTrackListeners> listener;
	private List<Car> carList;
	private List<Obstacle> obstacle;
	private List<Integer> endTime;
	private Controls controls;
	private boolean isGameOver = false;


	/** Sets the width and height in a squarecoordinate system and creates a new car object. */
	public RaceTrack2(int width, int height)
	{
		if (width <= 0 || height <= 0) 
		{
				throw new IllegalArgumentException("width and framHeight must be greater than zero");
		}

		this.width = width;
		this.height = height;
		this.realWidth = width + 2;
		this.realHeight = height + 2;
		obstacle = new ArrayList<>();
		carList = new ArrayList<>();
		endTime = new ArrayList<>();
		listener = new ArrayList<>();
		controls = new Controls(this);
		createRaceTrack();
		addObstacles();
		notifyListeners();
  }

	public void tick() 
	{
		for (Car car : carList) 
		{
	  	controls.car(car);
		}
		notifyListeners();
	}

	private boolean checkCollisionCars(RaceTrackObjects car1, RaceTrackObjects car2) 
	{
		int carPoints = 6;
		for (int i = 0; i < car1.getNewX().length; i++) 
		{
			int x = car1.getNewX()[i]; //RödBil
			int y = car1.getNewY()[i];
			if (isPointInsidePolynom(carPoints, car2.getNewX(), car2.getNewY(), x, y)) 
			{
				return true;
			}
		}
		return false;
  }

	private boolean isPointInsidePolynom(int nrOfVertices, int[] polyX, int[] polyY, int pointX, int pointY) 
	{
		// http://stackoverflow.com/questions/217578/how-can-i-determine-whether-a-2d-point-is-within-a-pol
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

	private boolean checkCollision(RaceTrackObjects car, RaceTrackObjects obs) 
	{
		int[] obsX = obs.getNewX();
		int[] obsY = obs.getNewY();

		for (int i = 0; i < car.getNewX().length; i++) 
		{
			if (isPointInsidePolynom(obs.getNrOfVertices(), obsX, obsY, car.getNewX()[i], car.getNewY()[i])) 
			{
				return true;
			}
		}
		return false;
  }

	private void obsCollision(Car car, Obstacle obs, Move move) 
	{
		switch (obs.getType()) 
		{
	    case "Oilspill":
				car.turn(-4 * move.degree); break;
	    case "Concreteblock":
				car.slowDown();
				car.setPrevCoords();
				car.direction(-move.direction);
				car.turn(-2 * move.degree);
				break;
	    case "CheckPoint":
				CheckPoint check = (CheckPoint) obs;

			if (check.getIdentification() == 1) 
			{
					car.setCheckPoint(1);
			} 
			else if (check.getIdentification() == 2 && car.getCheckPoints()[0]) 
			{
					car.setCheckPoint(2);
			} 
			else if (check.getIdentification() == 3 && car.getCheckPoints()[1]) 
			{
					car.setCheckPoint(3);
			} 
			else if (check.getIdentification() == 4 && car.getCheckPoints()[2]) 
			{
				if (car.getLap() < 1) 
				{
					car.resetCPoints();
					car.incrementLap();
				} 
				else if (car.getLap() == 1) 
				{
					car.incrementLap();
					car.setCheckPoint(4);
					endTime.add(car.getTimeInSec());
					isGameOver = true;
				}
			}
		}
  }

	private void addObstacles() 
	{
		final int[] check1 = new int[] { 880, 200, 112, 20, 1 };
		//	final int[] check1 = new int[] { 1760, 600, 112, 20, 1 };
		final int[] check2 = new int[] {1760, 600, 112, 20, 2 };
		//	final int[] check2 = new int[] { 900, 880, 20, 112, 2 };
		final int[] check3 = new int[] { 160, 600, 112, 20, 3 };
		//	final int[] check3 = new int[] { 160, 600, 112, 20, 3 };
		final int[] check4 = new int[] { 1000, 640, 20, 112, 4 };
		//	final int[] check4 = new int[] { 880, 200, 112, 20, 4 };

		final int[] oil1 = new int[] { 1720, 400, 20, 20 };
		final int[] oil2 = new int[] { 1770, 550, 20, 20 };
		final int[] oil3 = new int[] { 160, 700, 20, 20 };
		final int[] oil4 = new int[] { 900, 400, 20, 20 };
		final int[] oil5 = new int[] { 900, 520, 20, 20 };
		final int[] oil6 = new int[] { 100, 400, 20, 20 };
		final int[] oil7 = new int[] { 200, 200, 20, 20 };

		final int[] conBlock1 = new int[] { 160, 650, 30, 30 };
		final int[] conBlock2 = new int[] { 1080, 520, 500, 30 };
		final int[] conBlock3 = new int[] { 900, 880, 100, 30 };

		obstacle.add(new CheckPoint(check1, 1));
		obstacle.add(new CheckPoint(check2, 2));
		obstacle.add(new CheckPoint(check3, 3));
		obstacle.add(new CheckPoint(check4, 4));

		obstacle.add(new OilSpill(oil1));
		obstacle.add(new OilSpill(oil2));
		obstacle.add(new OilSpill(oil3));
		obstacle.add(new OilSpill(oil4));
		obstacle.add(new OilSpill(oil5));
		obstacle.add(new OilSpill(oil6));
		obstacle.add(new OilSpill(oil7));
		obstacle.add(new ConcreteBlock(conBlock1));
		obstacle.add(new ConcreteBlock(conBlock2));
		obstacle.add(new ConcreteBlock(conBlock3));
  }

	public void createCars(int cars) 
	{
		controls.resetKeys();
		for (int i = 1; i <= cars; i++) 
		{
			if (i == 1) 
			{
				final int[] car1Coords = new int[] { 940, 640 };
				carList.add(new Car(Color.red, car1Coords));
			} 
			else if (i == 2) 
			{
				final int[] car2Coords = new int[] { 940, 590 };
				carList.add(new Car(Color.BLUE, car2Coords));
			} 
			else if (i == 3) 
			{
				final int[] car3Coords = new int[] { 170, 170 };
				carList.add(new Car(Color.ORANGE, car3Coords));
			} 
			else if (i == 4) {
				final int[] car4Coords = new int[] { 210, 210 };
				carList.add(new Car(Color.PINK, car4Coords));
			}
		}
  }

	/** Sets every square in the RaceTrack to the value OFFROAD or ONROAD. */
	private void createRaceTrack() 
	{
		squareType = new SquareTypes[realWidth][realHeight];

		for (int col = 0; col < realWidth; col++) 
		{
			for (int row = 0; row < realHeight; row++) 
			{
				if (setOutSide(col, row)) 
				{
					squareType[col][row] = SquareTypes.OUTSIDE;
				} 
				else if (setOuterOffRoad(col, row) || setInnerOffRoad(col, row)) 
				{
					squareType[col][row] = SquareTypes.OFFROAD;
				} 
				else 
				{
					squareType[col][row] = SquareTypes.ONROAD;
				}
			}
		}
		changeSquares();
  }

	public void changeSquares() 
	{
		for (int col = 7; col < 21; col++) 
		{
			for (int row = 5; row < 10; row++) 
			{
				if (col == 7 && row > 6 && row < 10) 
				{
					squareType[col][row] = SquareTypes.OFFROAD;
				}
				else if (col > 9 && col < 19 && row == 7) 
				{
					squareType[col][row] = SquareTypes.OFFROAD;
				}
				else 
				{
					squareType[col][row]  = SquareTypes.ONROAD;
				}
			}
		}
		for (int col = 9; col < 16; col++) 
		{
			for (int row = 1; row < 6; row++) 
			{
				if (col == 9 || col == 10 && row > 0 && row < 6) 
				{
					squareType[col][row] = SquareTypes.OFFROAD;
				}
				else if (col > 10 && col < 17 && row == 1) 
				{
					squareType[col][row] = SquareTypes.ONROAD;
				}
	    }
		}
		squareType[10][6] = SquareTypes.OFFROAD;
		squareType[13][3] = SquareTypes.OFFROAD;
		squareType[14][3] = SquareTypes.OFFROAD;
		squareType[7][4] = SquareTypes.ONROAD;
		squareType[8][4] = SquareTypes.ONROAD;
		squareType[11][4] = SquareTypes.ONROAD;
		squareType[12][4] = SquareTypes.ONROAD;
  }

	public void moveCar(Move move, MaxSpeed maxSpeed, Car car) 
	{
		car.movement();
		if (!isOutSide(car.getNewX(), car.getNewY())) 
		{
			boolean offRoad = isOffRoad(car.getNewX(), car.getNewY());
			if (move.direction == 0 || !isUnderMaxSpeed(move, maxSpeed, offRoad, car.getSpeed())) 
			{
				car.slowDown();
			} 
			else 
			{
			car.direction(move.direction);
			}
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
			if (checkCollisionCars(car, c) && !Objects.equals(car, c)) 
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
		{
			if (checkCollision(car, obs)) 
			{
				obsCollision(car, obs, move);
	    }
		}
  }

	public void clearList() 
	{
		carList.clear();
		endTime.clear();
  }

	private boolean isUnderMaxSpeed(Move move, MaxSpeed maxSpeed, boolean offRoad, int curSpeed) 
	{
		int max = maxSpeed.onRoad;
		if (offRoad) 
		{
	    max = maxSpeed.offRoad;
		}
		if (move.direction > 0 && curSpeed < max || move.direction < 0 && curSpeed > max) 
		{
	    return true;
		}
		return false;
  }

	/** Checks if Controls is OFFROAD or not */
	private boolean isOffRoad(int[] xCoord, int[] yCoord) 
	{
		for (int i = 0; i < xCoord.length; i++) 
		{
	    int xCar = xCoord[i] / SQUARESIZE;
	    int yCar = yCoord[i] / SQUARESIZE;
			if (squareType[xCar + 1][yCar + 1] == SquareTypes.OFFROAD)
			{
				return true;
	    }
		}
		return false;
  }

	/** Checks if Controls is OUTSIDE or not */
	private boolean isOutSide(int[] xCoord, int[] yCoord) 
	{
		for (int i = 0; i < xCoord.length; i++) 
		{
	    int xCar = xCoord[i] / SQUARESIZE;
	    int yCar = yCoord[i] / SQUARESIZE;
			if (squareType[xCar + 1][yCar + 1] == SquareTypes.OUTSIDE || xCoord[i] <= 0 || yCoord[i] <= 0) 
			{
				return true;
	    }
		}
		return false;
  }

	public void restart() 
	{
		isGameOver = false;
  }

	/** Sets the boundaries for the outer offroad area */
	private boolean setOuterOffRoad(int col, int row) 
	{
		return (col == 1 || col == realWidth - 2) || (row == 1 || row == realHeight - 2);
  }

	/** Sets teh boundaries for the inner offroad area */
	private boolean setInnerOffRoad(int col, int row) 
	{
		return (col > 3 && col < realWidth - 4) && (row > 3 && row < realHeight - 4);
  }

	private boolean setOutSide(int col, int row) 
	{
		return col == 0 || col == realWidth - 1 || row == 0 || row == realHeight - 1;
  }

	public void notifyListeners() 
	{
		for (RaceTrackListeners rl : listener) 
		{
	    rl.updateRaceTrack();
		}
  }

	public void addBoardListener(RaceTrackListeners rl) 
	{
		listener.add(rl);
  }

	public int getWidth() 
	{
		return width;
  }

	public int getHeight() 
	{
		return height;
  }

	public List<Obstacle> getObstacle() 
	{
		return obstacle;
  }

	public List<Integer> getEndTime() 
	{
		return endTime;
  }

	public boolean getIsGameOver() 
	{
		return isGameOver;
  }

	public KeyListener getControls()
	{
		return controls;
  }

	public Iterable<Car> getCarList() 
	{
		return carList;
  }

	public SquareTypes getSquareType(int col, int row) 
	{
		return squareType[col + 1][row + 1];
  }

	public int getSQUARESIZE() 
	{
		return SQUARESIZE;
  }

	public void setNrOfPlayers(final int nrOfPlayers) 
	{
		this.nrOfPlayers = nrOfPlayers;
  }
}