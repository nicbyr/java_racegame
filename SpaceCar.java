import java.awt.*;

/** Subclass to Car. */
public class SpaceCar extends Car
{
	public SpaceCar(final Color color, final int[] startCoords) 
	{
		super(color, startCoords);
		setCarCoords();
	}

	private void setCarCoords() 
	{

		/**
		 * @param rotX x coordinate of the middlePoint of the car
		 * @param rotY Y coordinate of the middlePoint of the car
		 */

		final int rotX = 10, rotY = 10, carWidth = 40, carHeight = 20;

		/**@param flx ForwardLeft X-coordinate of the car*/
		int flx = rotX + carWidth / 2;
		/**@param flx ForwardLeft X-coordinate of the car*/
		int fly = rotY + 2;
		/**@param frx ForwardRight X-coordinate of the car*/
		int frx =  rotX + carWidth / 2;
		/**@param fry ForwardRight y coordinate of the car*/
		int fry = rotY - 2;
		/**@param brx BackwardRight X-coordinate of the car*/
		int brx = rotX - carWidth / 2;
		/**@param bry BackwardRight Y-coordinate of the car*/
		int bry = rotY - carHeight / 2;
		/**@param blx BackwLeft X-coordinate of the car*/
		int blx = rotX - carWidth / 2;
		/**@param bly BackwLeft Y-coordinate of the car*/
		int bly = rotY + carHeight / 2;
		/**@param mrx MiddleRight X-coordinate of the car*/
		int mrx =  rotX + 9;
		/**@param mly MiddleLeft X coordinate of the car*/
		int mly =  rotY + carHeight / 3;
		/**@param mlx MiddleLeft X-coordinate of the car*/
		int mlx =  rotX + 9;
		/**@param mry MiddleRight Y-coordinate of the car*/
		int mry =  rotY - carHeight / 3;

		int[] carX = new int[] { flx, frx, mrx, brx, blx, mlx };
		int[] carY = new int[] { fly, fry, mry, bry, bly, mly };

		setCarCoords(carX, carY);
	}
}