
package ui;

/**
 *
 * @author sashi
 */

import tictactoe.Observer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.logging.Level;
import java.util.logging.Logger;
import tictactoe.TicTacToe;

/**
 * This is RestartButton class that extends MouseAdapter. 
 * This is basically a button that is built for restarting processes like games.
 * @author thapaliya
 */
public class RestartButton extends MouseAdapter implements Observer
{
    public Rectangle button;
    public String text;
    public short textFontSize;
    public Object app;
    
    /**
     * 
     * @param x is the horizontal position of this button
     * @param y is the vertical position of this button
     * @param w is the width of this button
     * @param h is the height of this button
     */
    public RestartButton(int x, int y, int w, int h)
    {
        button = new Rectangle(x,y,w,h);
        this.text = "";
        this.textFontSize = 16;
    }
    
    /**
     * Draws this restart Button
     * @param g is the graphics object
     * @param img is the ImageObserver object
     */
    public void drawImage(Graphics g, ImageObserver img)
    {
        g.setColor(Color.BLUE);
        g.drawRect(button.x, button.y, button.width, button.height);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(button.x+1, button.y+1, button.width-2, button.height-2);
        g.setColor(Color.MAGENTA);
        g.setFont(new Font("Serif", Font.BOLD, this.textFontSize));
        g.drawString(text, button.x+10, button.y+20);

    }
    
    public void activate(Object app)
    {
        this.text = "Back to Main Menu";
        
        this.button.width = 200;
        this.button.height = 30;
        this.button.x = Images.board.getWidth(null)/4;
        this.button.y = Images.board.getHeight(null)/6;
        this.app = app;
                System.out.println("Restart Update................");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RestartButton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent me)
    {
        if(me.getX()>= button.x && me.getX()<= (button.x+button.width))
        {
            if(me.getY()>= button.y && me.getY()<=(button.y+button.height))
            {
                System.out.println("Restart...");
                ((TicTacToe)(this.app)).restart();
                //restart
                //this.restart.restart();
            }
        
        }
    }

    @Override
    public void update(Object... objects) 
    {
        System.out.println("Restart Update................");

        //this.button.width = Images.singlePlayer.getWidth(null)+100;
        //this.button.height = Images.singlePlayer.getHeight(null)-50;
        this.button.x = Images.board.getWidth(null)/4;
        this.button.y = Images.board.getHeight(null)/6;
        
        if(this.button.width<200) this.button.width =200;
        if(this.button.height<30) this.button.height= 30;
    }
    
}
