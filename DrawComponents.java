import javax.swing.*;
import java.awt.*;

/**Paints the track, obstacles and the cars*/
public class DrawComponents extends JComponent implements RaceTrackListeners
{
	private final RaceTrack raceTrack;
	private final int squaresize;
	private final Font font1, font2;

	public DrawComponents(final RaceTrack raceTrack) 
	{
		this.raceTrack = raceTrack;
		this.squaresize = raceTrack.getSquareSize();
		final int fontSize = 19;
		font1 = new Font("Arial", Font.BOLD, fontSize);
		font2 = new Font("Arial", Font.BOLD, fontSize);
  }

	private Color squareColor(int col, int row) 
	{
		if (raceTrack.getSquareType(col, row) == SquareTypes.ONROAD) 
		{
			return Color.DARK_GRAY;
		} 
		else if (raceTrack.getSquareType(col, row) == SquareTypes.OFFROAD)
		{
			return Color.GREEN;
		} 
		else if (raceTrack.getSquareType(col, row) == SquareTypes.OUTSIDE) 
		{
			return Color.magenta;
		}
		return Color.pink;
  }

	@Override public void updateRaceTrack() 
	{
		repaint();
  }

	@Override protected void paintComponent(final Graphics g) 
	{
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;

		for (int col = 0; col < raceTrack.getWidth(); col++) 
		{
			for (int row = 0; row < raceTrack.getHeight(); row++) 
			{
				g.setColor(squareColor(col, row));
				g.fillRect(col * squaresize, row * squaresize, squaresize, squaresize);
	    }
		}

		for (Obstacle obstacle: raceTrack.getObstacle())
		{
	    g2d.setColor(obstacle.getColor());
	    g2d.drawPolygon(obstacle.getObstacleX(), obstacle.getObstacleY(), obstacle.getNrOfVertices());
	    g2d.fillPolygon(obstacle.getObstacleX(), obstacle.getObstacleY(), obstacle.getNrOfVertices());
		}

		for (Car car : raceTrack.getCarList()) 
		{
	    g2d.setColor(car.getColor());
	    g2d.drawPolygon(car.getCarX(), car.getCarY(), car.getNrOfVertices());
	    g2d.fillPolygon(car.getCarX(), car.getCarY(), car.getNrOfVertices());
		}

		final int font1x = 0, font1y = 20, font2x = (raceTrack.getWidth() - 4) * squaresize + 40, font2y = 20;
		g2d.setFont(font1);
		g2d.setColor(Color.RED);
		g2d.drawString("Player1, Arrow-steering", font1x, font1y);
		g2d.setFont(font2);
		g2d.setColor(Color.BLUE);
		g2d.drawString("Player2, WASD-steering", font2x, font2y);
  }
}