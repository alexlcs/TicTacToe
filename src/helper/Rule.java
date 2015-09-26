
package helper;

import ui.Board;
import ui.Square;

/**
 *
 * @author sashi
 */
public class Rule 
{
    // 00 01 02
    // 00 10 20
    // 00 11 22
    // 01 11 21
    // 02 12 22
    // 02 11 20
    // 10 11 12
    // 20 21 22  
    private static short [][] winMoves = 
    {{0,0, 0,1, 0,2},
     {0,0, 1,0, 2,0},
     {0,0, 1,1, 2,2},
     {0,1, 1,1, 2,1},
     {0,2, 1,2, 2,2},
     {0,2, 1,1, 2,0},
     {1,0, 1,1, 1,2},
     {2,0, 2,1, 2,2},
    };
    
    public static boolean validMove(int r, int c, Board board)
    {
        boolean valid;
        valid = true;
        
        if(board.squares[r][c].getValue() != ' ') valid = false;
        return valid;
    }
    
    public static Square[] gameWon(Board board)
    {
        Square []squares;
        squares = null;
        
        for(int i=0; i<Rule.winMoves.length; i++)
        {
            if(board.squares[Rule.winMoves[i][0]][Rule.winMoves[i][1]].getValue()!=' ' 
               && 
               board.squares[Rule.winMoves[i][0]][Rule.winMoves[i][1]].getValue()==
               board.squares[Rule.winMoves[i][2]][Rule.winMoves[i][3]].getValue() && 
               board.squares[Rule.winMoves[i][2]][Rule.winMoves[i][3]].getValue() == 
               board.squares[Rule.winMoves[i][4]][Rule.winMoves[i][5]].getValue()
              )
            {
                squares = new Square[3];
                squares[0] = board.squares[Rule.winMoves[i][0]][Rule.winMoves[i][1]];
                squares[1] = board.squares[Rule.winMoves[i][2]][Rule.winMoves[i][3]];
                squares[2] = board.squares[Rule.winMoves[i][4]][Rule.winMoves[i][5]];
                
                break;
            }
        }

        return squares;
    }
    
    public static boolean gameEnd(Board board)
    {
        for(int i=0; i<board.squares.length; i++)
        {
            for(int j=0; j<board.squares[0].length; j++)
            {
                if(board.squares[i][j].getValue() == ' ')
                    return false;
            }
        }
        
        return true;
    }
    
    public static short[][] getWinMoves()
    {
        return Rule.winMoves.clone();
    }
}
