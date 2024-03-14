package Gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Display.Game;

public class Menu extends State implements Statemethods {

    public Menu(Game game) {
        super(game);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
        Font boldFont = new Font("Arial", Font.BOLD, 20);
        g.setFont(boldFont);
    
        FontMetrics fm = g.getFontMetrics();
        int x = (Game.screenWidth - fm.stringWidth("PRESS ENTER TO START")) / 2;
        int y = (Game.screenHeight - fm.getHeight()) / 2 + fm.getAscent() - 40;
    
        g.setColor(Color.BLACK);
        g.drawString("PRESS ENTER TO START", x, y);
    
        y += fm.getHeight();  // Move down for the second line
        x = (Game.screenWidth - fm.stringWidth("PRESS ESC TO RETURN BACK")) / 2;
        g.drawString("PRESS ESC TO RETURN BACK", x, y);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Gamestate.state = Gamestate.PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
