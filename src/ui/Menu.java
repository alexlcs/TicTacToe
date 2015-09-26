
package ui;

import tictactoe.*;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import javax.swing.JApplet;

/**
 * This a Menu class that extends MouseAdapter.
 * @author thapaliya
 */
public class Menu extends MouseAdapter implements Observer
{
    
    Rectangle singlePlayer, multiPlayer,playGame, exit;
    JApplet frame;
    boolean insideStart, insideSingle, insideMulti, insideExit;
    
    public Menu(int w, int h, JApplet aplet)
    {
        
        Images.resizeBoard(w, h);

        this.singlePlayer = new Rectangle();
        this.multiPlayer = new Rectangle();
        this.exit = new Rectangle();
        this.updateButtons();
        this.frame=aplet;
        this.insideSingle = false;
        this.insideMulti = false;
        this.insideExit = false;
        

    }
    
    public void draw(Graphics g, ImageObserver img)
    {
            
        if(this.insideSingle)
        {
            g.drawImage(Images.singlePlayer,this.singlePlayer.x,this.singlePlayer.y,
                    this.singlePlayer.width+50, this.singlePlayer.height+20, img);
            return;
        }
        else if(this.insideMulti)
        {
            g.drawImage(Images.multiPlayer,this.multiPlayer.x,this.multiPlayer.y,
                    this.multiPlayer.width+50, this.multiPlayer.height+20, img);
            return;
        }
        else if(this.insideExit)
        {
            g.drawImage(Images.exit,this.exit.x,this.exit.y,this.exit.width+50, 
                    this.exit.height+20,img);  
            return;
        }
       
        g.drawImage(Images.board, 0, 0, img);
        g.drawImage(Images.singlePlayer,this.singlePlayer.x,this.singlePlayer.y,
                this.singlePlayer.width, this.singlePlayer.height, img);
        g.drawImage(Images.multiPlayer,this.multiPlayer.x,this.multiPlayer.y,
                this.multiPlayer.width, this.multiPlayer.height, img);
        g.drawImage(Images.exit,this.exit.x,this.exit.y,this.exit.width, this.exit.height,img);
            
    }
    
    
    
    @Override
    public void mouseMoved(MouseEvent me)
    {
        System.out.println("Menu Mouse moved...");
         if(hooveredOverStart(me));
         else if(hooveredOverExit(me));
    }
    
    @Override
    public void mouseClicked(MouseEvent me)
    {
        System.out.println("Menu Mouse clicked");
        if(this.insideSingle)
        {
            ((TicTacToe)this.frame).startGame(1);
         
        }
        else if(this.insideMulti)
        {
            ((TicTacToe)this.frame).startGame(2);
        }
        else if (this.insideExit)
        {
            ((TicTacToe)this.frame).exitGame();
            //((Game)this.frame).exitGame();
        }
    }
    
    @Override
    public void update(Object... objects)
    {
        System.out.println("Update menu..");
        Images.resizeBoard(this.frame.getWidth(), this.frame.getHeight());
        this.updateButtons(); 
    }
    
    
    private boolean hooveredOverStart(MouseEvent me)
    {
        System.out.println("Menu Hoovered Start check..");
        if(this.hooveredOverRectangle(me, this.singlePlayer))
        {
            System.out.println("entered");
            frame.repaint();
            this.insideSingle=true;
            return true;
        }
        else if(this.hooveredOverRectangle(me, this.multiPlayer))
        {
            System.out.println("Menu entered");
            frame.repaint();
            this.insideMulti=true;
            return true;
        }
        else if(this.insideSingle) 
        {
            this.insideSingle=false;
        }
        else if(this.insideMulti) 
        {
            this.insideMulti=false;
        }
        frame.repaint();
        
        return false;
    }
    
    private boolean hooveredOverExit(MouseEvent me)
    {
         
        if(this.hooveredOverRectangle(me, this.exit))
        {
            System.out.println("Menu entered");
            frame.repaint();
            this.insideExit=true;
            return true;
        }
        else if(this.insideExit) 
        {
            this.insideExit=false;
            frame.repaint();
        }

        return false;
    
    }
    
    private boolean hooveredOverRectangle(MouseEvent me, Rectangle rec)
    {
        if(me.getX()>=rec.x && me.getX()<= (rec.x+rec.width))
        {
             if(me.getY()>= rec.y && me.getY()<=(rec.y+rec.height))
             {
                 return true;
             }
         }
        return false;
    }
    
    private void updateButtons()
    {
        this.singlePlayer.width = Images.singlePlayer.getWidth(null);
        this.singlePlayer.height = Images.singlePlayer.getHeight(null);
        this.multiPlayer.height = Images.multiPlayer.getHeight(null);
        this.multiPlayer.width = Images.multiPlayer.getWidth(null);
        this.exit.width = Images.exit.getWidth(null);
        this.exit.height = Images.exit.getHeight(null);
        
        this.singlePlayer.x = Images.board.getWidth(null)/4;
        this.singlePlayer.y = Images.board.getHeight(null)/6;
        this.multiPlayer.x = this.singlePlayer.x;
        this.multiPlayer.y = this.singlePlayer.y+this.singlePlayer.height+20;
        this.exit.x = this.singlePlayer.x;
        this.exit.y = this.multiPlayer.y+this.multiPlayer.height+20;
    
    }

}