import java.awt.*;

/** Subclass to Car. */
public class RaceCar extends Car
{
    public RaceCar(final Color color, final int[] startCoords) {
	super(color, startCoords);
	setCarCoords();
    }

    private void setCarCoords() {

	/**
	 * @param rotX x coordinate of the middlePoint of the car
	 * @param rotY Y coordinate of the middlePoint of the car
	 */
	final int rotX = 10, rotY = 10, carWidth = 40, carHeight = 20;
	/**@param flx ForwardLeft X-coordinate of the car*/
	int flx = rotX + carWidth / 2;
	/**@param flx ForwardLeft X-coordinate of the car*/
	int fly = rotY + carHeight / 2;
	/**@param frx ForwardRight X-coordinate of the car*/
	int frx =  rotX + carWidth / 2;
	/**@param fry ForwardRight y coordinate of the car*/
	int fry = rotY  - carHeight / 2;
	/**@param brx BackwardRight X-coordinate of the car*/
	int brx = rotX - carWidth / 2;
	/**@param bry BackwardRight Y-coordinate of the car*/
	int bry = rotY - carHeight / 2;
	/**@param blx BackwLeft X-coordinate of the car*/
	int blx = rotX - carWidth / 2;
	/**@param bly BackwLeft Y-coordinate of the car*/
	int bly = rotY + carHeight / 2;
	/**@param mrx MiddleRight X-coordinate of the car*/
	/**@param mly MiddleLeft X coordinate of the car*/
	int mly =  rotY + carHeight / 2;
	/**@param mlx MiddleLeft X-coordinate of the car*/
	/**@param mry MiddleRight Y-coordinate of the car*/
	int mry =  rotY - carHeight / 2;

	int[] carX = new int[] { flx, frx, rotX, brx, blx, rotX };
	int[] carY = new int[] { fly, fry, mry, bry, bly, mly };

	setCarCoords(carX, carY);
    }
}
