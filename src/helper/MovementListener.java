
package helper;


import controller.Controller;
import ui.Images;
import tictactoe.Observer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This is a MovementListener class that extends MouseAdapter and is specifically built for Chess Game.
 * But, it can be used for any GUI that requires the account of a row an colum position in the frame.
 * @author sashi
 */
public class MovementListener extends MouseAdapter implements Observer 
{
    public int scalingWidth, scalingHeight;
    public Controller controller;
    
    
    public MovementListener(Controller ctr)
    {
       this.controller=ctr;
       this.scalingWidth=Images.xoWidth;
       this.scalingHeight=Images.xoHeight;
    }
    /**
     * 
     * @param ctr is a Controller object
     * @param w
     * @param h 
     */
    public MovementListener(Controller ctr, int w, int h)
    {
       this.controller=ctr;
       this.scalingWidth=w;
       this.scalingHeight=h;
    }
    

    
    @Override
    public void mouseClicked(MouseEvent me)
    {
        int row, colum;
        
        row=me.getY()/this.scalingHeight;
        colum=me.getX()/this.scalingWidth;
        System.out.printf("row = %d, colum=%d\n",row, colum);
        
        this.controller.click(row,colum);
    }

    @Override
    public void update(Object... objects) 
    {
        System.out.println("Listener update..");
        this.scalingWidth = Images.xoWidth;
        this.scalingHeight = Images.xoHeight;
    }
    
    
    
}