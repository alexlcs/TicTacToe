
package controller;

import model.Player;
import helper.Rule;
import ui.Board;
import ui.Square;



/**
 *
 * @author sashi
 */
public class ComputerAI
{
    public Player player;
    public Square [][] squares;
    public Controller cntr;
    public static short[][] winMoves;
    public boolean cont;
    
    public void run() 
    {
        ComputerAI.winMoves = Rule.getWinMoves();
      
            if(this.cntr.turn == this.player.turn)
            {
                this.makeMove();
            }
            try
            {
               // Thread.sleep(100);
            } 
            catch (Exception ex) 
            {
                System.out.println(ex.toString());
            }
        System.out.print("AI Thread exiting ...\n>>>");
       // String test = new java.util.Scanner(System.in).next();
    }
    
    
    private void makeMove()
    {
        int [] position;
        position = null;
        if((position=this.closeToMatch(this.player.signId))!=null)
        {
            System.out.println("Winning move ----------------"+this.player.signId);
            this.cntr.computerClick(position[0], position[1]);
            return;
        }
        else if((position=this.closeToMatch(this.cntr.players[((this.cntr.turn+1)%2)].signId))!=null)
        {
           System.out.println("Defending move ----------------");
           this.cntr.computerClick(position[0], position[1]);
           return;
        }
        this.selectPosition();
        
    }
    
    private void selectPosition()
    {
        int[] position;
        position = new int[2];
    
        do
        {
            System.out.println("selecting Position...");
            
            position[0] = (int)(Math.round(Math.random()*2));
            position[1] = (int)(Math.round(Math.random()*2));
            System.out.printf("r=%d, c=%d\n",position[0],position[1]);
        
        }while(!Rule.validMove(position[0], position[1], this.cntr.getBoard(this)));
        this.cntr.computerClick(position[0], position[1]);
    }

   
    private int[] closeToMatch(char playerSign)
    {
        Board board;
        int[] position;
        
        board = this.cntr.getBoard(this);
        position = null;
        
        for(int i=0; i<ComputerAI.winMoves.length; i++)
        {
            if((board.squares[ComputerAI.winMoves[i][0]][ComputerAI.winMoves[i][1]].getValue()==playerSign 
               && 
               board.squares[ComputerAI.winMoves[i][0]][ComputerAI.winMoves[i][1]].getValue()==
               board.squares[ComputerAI.winMoves[i][2]][ComputerAI.winMoves[i][3]].getValue() 
                 && board.squares[ComputerAI.winMoves[i][4]][ComputerAI.winMoves[i][5]].getValue()==' '
               ))
            {
                position = new int[2];
                position[0] = ComputerAI.winMoves[i][4];
                position[1] = ComputerAI.winMoves[i][5];
                break;
            }
            if((board.squares[ComputerAI.winMoves[i][2]][ComputerAI.winMoves[i][3]].getValue()==playerSign
                &&
               board.squares[ComputerAI.winMoves[i][2]][ComputerAI.winMoves[i][3]].getValue() == 
               board.squares[ComputerAI.winMoves[i][4]][ComputerAI.winMoves[i][5]].getValue()
                && board.squares[ComputerAI.winMoves[i][0]][ComputerAI.winMoves[i][1]].getValue()==' '
               ))
            {
                position = new int[2];
                position[0] = ComputerAI.winMoves[i][0];
                position[1] = ComputerAI.winMoves[i][1];
                break;
            }
            if((board.squares[ComputerAI.winMoves[i][0]][ComputerAI.winMoves[i][1]].getValue()==playerSign
                &&
               board.squares[ComputerAI.winMoves[i][0]][ComputerAI.winMoves[i][1]].getValue() == 
               board.squares[ComputerAI.winMoves[i][4]][ComputerAI.winMoves[i][5]].getValue()
                && board.squares[ComputerAI.winMoves[i][2]][ComputerAI.winMoves[i][3]].getValue()==' '
              ))
            {
                position = new int[2];
                position[0] = ComputerAI.winMoves[i][2];
                position[1] = ComputerAI.winMoves[i][3];
                break;
            }
             
        }
        
        return position;
    }
    
}
