package Display;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

/**
 * The GameWindow class represents the main window of the game.
 * It creates a JFrame and adds a GamePanel to it.
 * It also handles window focus events.
 */
public class GameWindow {
    private JFrame jframe;

    /**
     * Constructs a GameWindow object with the specified GamePanel.
     * @param gamePanel the GamePanel to be added to the window
     */
    public GameWindow(GamePanel gamePanel) {
        jframe = new JFrame();

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.setVisible(true);
        jframe.setTitle(Game.gameTitle);
		jframe.addWindowFocusListener(new WindowFocusListener() {
			@Override
			public void windowLostFocus(WindowEvent e) {
				gamePanel.getGame().windowFocusLost();
			}

			@Override
			public void windowGainedFocus(WindowEvent e) {
			}
		});
    }
}
