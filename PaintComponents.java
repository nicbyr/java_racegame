import javax.swing.JFrame;
import java.awt.*;

/**Paints the track and the getKeysPressed*/
public class PaintComponents extends JComponent implements RaceTrackListeners
{
	private final RaceTrack raceTrack;
	private final int squaresize;
	private final Font font1, font2;

	/**
	 * Takes the parameter raceTrack and initialize the raceTrack, the squaresize and getKeysPressed.
	 **/
	public PaintComponents(final RaceTrack raceTrack) {
	this.raceTrack = raceTrack;
	this.squaresize = raceTrack.getSQUARESIZE();
	final int fontSize = 19;
	font1 = new Font("Arial", Font.BOLD, fontSize);
	font2 = new Font("Arial", Font.BOLD, fontSize);
    }

	/** Sets the squaretype onroad to the color BLACK and the other squaretypes to green */
	private Color squareColor(int col, int row) {
	
		if (raceTrack.getSquareType(col, row) == SquareTypes.ONROAD) 
		{
				return Color.DARK_GRAY;
		} 
		
		else if (raceTrack.getSquareType(col, row) == SquareTypes.OFFROAD) 
		{
				return Color.GREEN;
		} else if (raceTrack.getSquareType(col, row) == SquareTypes.OUTSIDE) 
		{
				return Color.magenta;
		}
		return Color.pink;
  }

	@Override public void updateRaceTrack() 
	{
		repaint();
  }

	/** Prints the racetrack and the getKeysPressed */
	@Override protected void paintComponent(final Graphics g) 
	{
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;

		for (int col = 0; col < raceTrack.getWidth(); col++)
		{
			for (int row = 0; row < raceTrack.getHeight(); row++) 
			{
				g.setColor(squareColor(col, row));
				g.fillRect(col * squaresize, row * squaresize, squaresize - 1, squaresize - 1);
	  	}
		}

		for (Obstacle obstacle: raceTrack.getObstacle()) 
		{
				g2d.setColor(obstacle.getColor());
				g2d.drawPolygon(obstacle.getNewX(), obstacle.getNewY(), obstacle.getNrOfVertices());
				g2d.fillPolygon(obstacle.getNewX(), obstacle.getNewY(), obstacle.getNrOfVertices());
		}

		for (Car car : raceTrack.getCarList()) 
		{
				g2d.setColor(car.getColor());
				g2d.drawPolygon(car.getNewX(), car.getNewY(), car.getNrOfVertices());
				g2d.fillPolygon(car.getNewX(), car.getNewY(), car.getNrOfVertices());
		}

		final int font1x = 60, font1y = 100, font2x = (raceTrack.getWidth() -1) * squaresize, font2y = 100;
		g2d.setFont(font1);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Player1, Arrow-steering", font1x, font1y);
		g2d.setFont(font2);
		g2d.setColor(Color.BLUE);
		g2d.drawString("Player2, WASD-steering", font2x, font2y);
  }
}