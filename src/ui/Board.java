
package ui;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Color;
import tictactoe.Drawable;
import tictactoe.Observer;

/**
 *
 * @author sashi
 */
public class Board implements Drawable, Observer
{
    public Square[][] squares;
    public Square[] crossedSquares;
    
    public int[] crossedLine;
    
    public Board()
    { 
       this(400,400);
    }
    
    public Board(int width, int height)
    {

        Images.resizeBoard(width, height);
        this.initSquares();
        this.crossedLine = new int[4];
        this.crossedSquares = null;
        for(int i=0; i<this.crossedLine.length; i++)
            this.crossedLine[i]=0;
       
    }
    
    
    private void initSquares()
    {
        this.squares = new Square[3][3];
        
        for(int i=0; i<squares.length; i++)
        {
            for(int j=0; j<squares.length; j++)
            {
                this.squares[i][j] = new Square();
            } 
        }
        
        Square.setPostions(squares);
    }
    
    public void draw(Object position, Graphics g, ImageObserver img) 
    {
        System.out.println("Board Draw ::");
        g.drawImage(Images.board, 0, 0, img);
        
        for(int i=0; i<this.squares.length; i++)
        {
            for(int j=0; j<this.squares.length; j++)
                this.squares[i][j].draw(null, g, img);
        }
        this.drawLine(g, img);
    }
    
    public void setCrossedSquares(Square[]square)
    {
        this.crossedSquares = square;
        this.updateLineCoordinates();
    }
    
    public void updateLineCoordinates()
    {

        this.crossedLine[0] = this.crossedSquares[0].getX()+(Images.xoWidth/3);
        this.crossedLine[1] = this.crossedSquares[0].getY()+(Images.xoHeight/3);
        
        this.crossedLine[2] = this.crossedSquares[2].getX()+(Images.xoWidth/3);
        this.crossedLine[3] = this.crossedSquares[2].getY()+(Images.xoHeight/3);
    }
    
    private void drawLine(Graphics g, ImageObserver img)
    {
        g.setColor(Color.red);
        g.drawLine(this.crossedLine[0], this.crossedLine[1], this.crossedLine[2], 
                this.crossedLine[3]);
        g.drawLine(this.crossedLine[0], this.crossedLine[1]+1, this.crossedLine[2], 
                this.crossedLine[3]+1);
        g.drawLine(this.crossedLine[0], this.crossedLine[1]+2, this.crossedLine[2], 
                this.crossedLine[3]+2);
        g.drawLine(this.crossedLine[0], this.crossedLine[1]-1, this.crossedLine[2], 
                this.crossedLine[3]-1);
        g.drawLine(this.crossedLine[0], this.crossedLine[1]-2, this.crossedLine[2], 
                this.crossedLine[3]-2);
        g.drawLine(this.crossedLine[0]+1, this.crossedLine[1], this.crossedLine[2]+1, 
                this.crossedLine[3]);
        g.drawLine(this.crossedLine[0]+2, this.crossedLine[1], this.crossedLine[2]+2, 
                this.crossedLine[3]);
        g.drawLine(this.crossedLine[0]-1, this.crossedLine[1], this.crossedLine[2]-1, 
                this.crossedLine[3]);
        g.drawLine(this.crossedLine[0]-2, this.crossedLine[1], this.crossedLine[2]-2, 
                this.crossedLine[3]);
    }

    @Override
    public void update(Object... objects) 
    {
        Square.setPostions(this.squares);
        if(this.crossedSquares!=null)this.updateLineCoordinates();
    }
   
}
