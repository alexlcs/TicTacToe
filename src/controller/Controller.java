
package controller;

import tictactoe.*;
import model.*;
import helper.*;
import ui.Board;
import ui.Square;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import javax.swing.JApplet;

/**
 *
 * @author sashi
 */
public class Controller
{
    private Board board;
    private JApplet view;
    private Square recentlyUpdated;
    private ComputerAI ai;
    private Thread computerAi;
    //if gameSatus = 0 then game is still running
    //else if gameStatus = 1 then game has been won by a Player
    //else if gameStatus = 2 then game has been a tie
    public short gameStatus;
    public short turn;
    public Player [] players;
    // gameStatus and turn are public specially for the ComputerAI to access

    public Controller(Object view)
    {
        this.view = (JApplet)view;
        this.turn = (short)((this.turn+1)%2);
        this.recentlyUpdated = null;
        this.initPlayers();
    }
    
    public void setBoard(Board board)
    {
        this.board = board;
    }
    
    public void drawBoard(Graphics g, ImageObserver img)
    {
        this.board.draw(null, g, img);
    }
    
    public void click(int r, int c)
    {
        if(this.computerAi!=null && this.ai.player.turn == this.turn)
        {
            System.out.println("Not Your turn");
            return;
        }
        this.clicked(r, c);
        if(this.computerAi!=null && this.gameStatus==0) this.computerAi.resume();
        
    }

    public void computerClick(int r, int c)
    {
        this.clicked(r, c);
        this.computerAi.suspend();
    }
    
    public Board getBoard(Object ob)
    {
        if(ob instanceof MovementListener || ob instanceof ComputerAI)
            return this.board;
        return null;
    }

    public boolean gameTie()
    {
        return this.gameStatus==2;
    }
    
    public boolean gameWon()
    {
        return this.gameStatus==1;
    }
    
    public boolean gameOver()
    {
        return this.gameStatus!=0;
    }
    
    public void singlePlayer() 
    {
        
        this.ai = new ComputerAI();
        this.ai.cntr = this;
        this.ai.player = this.players[this.turn];
        this.ai.player.turn = this.turn;
        this.ai.cont = true;
        this.computerAi = new Thread(ai);
        this.computerAi.start();
    }
    
    
    private void initPlayers()
    {
        this.players = new Player[2];
        char []sign = {'X','O'};
        
        for(int i=0; i<this.players.length; i++)
        {
            this.players[i] = new Player();
            this.players[i].signId = sign[i];
            this.players[i].won = false;
        }
    }
    
    private void updateStatus()
    {
        Square[] crossedSquares;
        crossedSquares = new Square[3];
        if((crossedSquares=Rule.gameWon(this.board))!=null)
        {
            this.board.setCrossedSquares(crossedSquares);
           
            if(this.players[0].signId == crossedSquares[0].getValue())
                this.players[0].won = true;
            else this.players[1].won = true;
            ((TicTacToe)this.view).endGame();
            this.gameStatus = 2;
        }
        else if(Rule.gameEnd(this.board))
        {
           ((TicTacToe)this.view).endGame();
           this.gameStatus = 1;
           
        }
    }
    
    private void clicked(int r, int c)
    {
        System.out.println("clicked");
        if(this.gameStatus==0 && Rule.validMove(r, c, this.board))
        {

            System.out.println(r+" "+c);
            System.out.println("Valid click");
            //String test = new Scanner(System.in).next();
            this.board.squares[r][c].setValue(this.players[this.turn].signId);
            this.recentlyUpdated = this.board.squares[r][c];
            this.turn = (short)((this.turn+1)%2);
            this.updateStatus();
            ((TicTacToe)this.view).update();
        }
    
    }
    

   
}
