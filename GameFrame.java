import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creates the frame, menues, panel, timers and creates the Highscore
 */
public class GameFrame extends JFrame
{
	private int nrOfPlayers, squareSize, width, height;
	private RaceTrack raceTrack;
	private JPanel panel;
	private JButton onePButton, twoPButton, trackOneButton, trackTwoButton;
	private JMenuItem quitButton, restartButton;
	private ActionListener tick, refresh;
	private Timer checkCarTimer, rePaintTimer;
	private HighScoreList highScoreList;
	private boolean playersChosen = false;

	public GameFrame(RaceTrack raceTrack) {
		super("CarGame");
		this.raceTrack = raceTrack;
		this.width = raceTrack.getWidth();
		this.height = raceTrack.getHeight();
		squareSize = raceTrack.getSquareSize();
		highScoreList = HighScoreList.getInstance();
		createActionListeners();
		createTimers();
		createMenues();
		createFrame();
		createListeners();
  }

	private void createActionListeners() 
	{
		tick = new ActionListener()
		{
			@Override public void actionPerformed(final ActionEvent e) 
			{
				raceTrack.tick();
			}
		};

		refresh = new ActionListener()
		{
			@Override public void actionPerformed(final ActionEvent e) 
			{
				raceTrack.notifyListeners();
				if (raceTrack.getIsGameOver()) 
				{
					gameOver();
				}
			}
		};
  }

	private void createTimers() 
	{
		final int checkCarPositionDelay = 40, repaintDelay = 100;
		rePaintTimer = new Timer(repaintDelay, refresh);
		rePaintTimer.setCoalesce(true);
		rePaintTimer.start();

		checkCarTimer = new Timer(checkCarPositionDelay, tick);
		checkCarTimer.setCoalesce(true);
		checkCarTimer.start();
  }

	private void gameOver() 
	{
		checkCarTimer.stop();
		String name = JOptionPane.showInputDialog(this, "Highscore", "Write your name");
		final HighScore highScore = new HighScore(raceTrack.getEndTime(), name);
		highScoreList.addHighScore(highScore);
		String list = highScoreList.printHighScore();
		JOptionPane.showMessageDialog(this, list, "Highscore", JOptionPane.PLAIN_MESSAGE);
		rePaintTimer.stop();
		raceTrack.setisGameOver();
  }

	private void createListeners() 
	{
		onePButton.addActionListener(new ActionListener()
		{
			@Override public void actionPerformed(final ActionEvent e) 
			{
				nrOfPlayers = 1;
				playersChosen = true;
	  	}
		});

		twoPButton.addActionListener(new ActionListener()
		{
			@Override public void actionPerformed(final ActionEvent e) 
			{
				nrOfPlayers = 2;
				playersChosen = true;
			}
		});

		trackOneButton.addActionListener(new ActionListener()
			{
				@Override public void actionPerformed(final ActionEvent e) 
				{
					raceTrack.createTrack(1);
					if (playersChosen) 
					{
						raceTrack.createCars(nrOfPlayers);
						panel.setVisible(false);
						setFocus();
					}
				}
			});

		trackTwoButton.addActionListener(new ActionListener()
		{
			@Override public void actionPerformed(final ActionEvent e)
			{
				raceTrack.createTrack(2);
				if (playersChosen) 
				{
					raceTrack.createCars(nrOfPlayers);
					panel.setVisible(false);
					setFocus();
				}
			}
		});

		quitButton.addActionListener(new ActionListener()
		{
			@Override public void actionPerformed(final ActionEvent e) 
			{
				quit();
			}
		});

		restartButton.addActionListener(new ActionListener()
		{
			@Override public void actionPerformed(final ActionEvent e) 
			{
				raceTrack.clearList();
				panel.setVisible(true);
				rePaintTimer.start();
				checkCarTimer.start();
				playersChosen = false;
			}
		});
	}

	private void setFocus() 
	{
		this.requestFocus();
  }

	/** Creates the frame for the game. */
	private void createFrame() 
	{
		final int fontSize = 70, titleX = width * squareSize / 3 + 100, titleY = 0;
		final int titleWidth = 500, titleHeight = 100, setBoundsWidth = 200, setBoundsHeight = 50;
		final int setBoundsX = width * squareSize / 3, setBoundsY = 180;
		final int setBoundsXTwo = width * squareSize / 2;
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		panel = new JPanel();
		onePButton = new JButton("One Player");
		twoPButton = new JButton("Two Player");
		trackOneButton = new JButton("Track One");
		trackTwoButton = new JButton("Track Two");
		JLabel title = new JLabel("Racegame");
		title.setFont(new Font("Italic", Font.BOLD, fontSize));
		panel.add(onePButton);
		panel.add(twoPButton);
		panel.add(trackOneButton);
		panel.add(trackTwoButton);
		panel.add(title);
		panel.setLayout(new BorderLayout());
		title.setBounds(titleX, titleY, titleWidth, titleHeight);
		onePButton.setBounds(setBoundsX, setBoundsY, setBoundsWidth, setBoundsHeight);
		twoPButton.setBounds(setBoundsXTwo, setBoundsY, setBoundsWidth, setBoundsHeight);
		trackOneButton.setBounds(setBoundsX, 2 * setBoundsY, setBoundsWidth, setBoundsHeight);
		trackTwoButton.setBounds(setBoundsXTwo, 2 * setBoundsY, setBoundsWidth, setBoundsHeight);
		panel.setBackground(Color.CYAN);
		this.add(panel);
		panel.setVisible(true);
		this.setVisible(true);
		this.pack();
  }

	private void createMenues() 
	{
		final JMenuBar menuBar = new JMenuBar();
		final JMenu file = new JMenu("File");
		quitButton = new JMenuItem("Quit", 'Q');
		restartButton = new JMenuItem("Restart", 'R');
		file.add(restartButton);
		file.add(quitButton);
		menuBar.add(file);
		this.setJMenuBar(menuBar);
		this.pack();
  }

	private void quit() 
	{
		int answer = JOptionPane.showConfirmDialog(this, "Do you really want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.YES_OPTION) 
		{
				System.exit(0);
		}
  }

	@Override public Dimension getPreferredSize() 
	{
		final int frameAdjust = raceTrack.getSquareSize()/2;
		Dimension dimension = new Dimension(width * squareSize, height * squareSize + frameAdjust);
		return dimension;
  }
}