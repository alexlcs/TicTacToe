
package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.lang.reflect.Field;
import tictactoe.Drawable;

/**
 *
 * @author sashi
 */
public class Square implements Drawable
{
    private char value;
    private int x, y;
    
    public Square()
    {
       this(0,0); 
    }
    
    public Square(int x, int y)
    {
        this.value = ' ';
        this.x = x;
        this.y = y;
        
    }
    
    @Override
    public void draw(Object position, Graphics g, ImageObserver img)
    {
        if(this.value == ' ') return;
        g.drawImage(this.getValueImage(), x, y, img);
        //System.out.println("x="+x+", y="+y);
    }
    
    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void setValue(char value)
    {
        this.value = value;
    }
    
    public Image getValueImage()
    {
        Image valueImg;
        valueImg = null;
        try 
        {
            Field field = Images.class.getDeclaredField(Character.toString(value));
            valueImg = (Image)field.get(valueImg);
        } 
        catch (Exception ex) 
        {
            System.out.println(ex.toString());
        } 

        return valueImg;
    }
    
    public char getValue()
    {
        return this.value;
    }
    
    public int getX()
    {
        return this.x;
    }
    
    public int getY()
    {
        return this.y;
    }
        
    public static void setPostions(Square[][]squares)
    {
        for(int i=0; i<squares.length; i++)
        {
            for(int j=0; j<squares.length; j++)
            {
                squares[i][j].setPosition((j*Images.xoWidth+20), (i*Images.xoHeight+20));
            }
            
        }
    }
}
