
package tictactoe;

import ui.Board;
import ui.Images;
import ui.Rescaler;
import ui.RestartButton;
import controller.Controller;
import helper.MovementListener;
import ui.Menu;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JApplet;

/**
 *
 * @author sashi
 */
public class TicTacToe extends JApplet 
{
    public Menu menu;
    public Controller gameController;
    public RestartButton restartButton;
    public Rescaler rescaler;
    public boolean gameStart;
   
            
    public void init()
    {
        this.init(400,400);
    }
    
    public void init(int w, int h)
    {
        Images.init();
        this.setSize(w, h);
        
        this.rescaler = new Rescaler();
        this.menu = new Menu(w,h,this);
        this.restartButton = new RestartButton(0,0,0,0);
        this.gameStart = false;

        this.addComponentListener(rescaler);
        this.addMouseListener(menu);
        this.addMouseMotionListener(menu);

        this.rescaler.addObserver(this.menu);
    }
  
    public void start()
    {
       System.out.println("start");
    }
    
    public void startGame(int type)
    {
        Board board;
        MovementListener listener;

        board = new Board(this.getWidth(),this.getHeight());
       
        this.gameController= new Controller(this);
        this.gameController.setBoard(board);
        if(type==1) this.gameController.singlePlayer();
        this.restartButton = new RestartButton(0,0,0,0);
        
        listener = new MovementListener(this.gameController);
        this.removeMouseListener(this.menu);
        this.removeMouseMotionListener(this.menu);
        this.addMouseListener((MovementListener)listener);
        this.addMouseListener(this.restartButton);
        this.rescaler.removeAllObservers();
        this.rescaler.addObserver((Observer)listener);
        this.rescaler.addObserver((Observer)board);
        this.gameStart=true;
        this.repaint();
    }
    
    public void endGame()
    {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        this.restartButton.activate(this);
        
        this.rescaler.addObserver(this.restartButton);

    }
    
    public void restart()
    {
        MouseListener []mouseListeners;
        MouseMotionListener [] mouseMotionListeners;
        mouseListeners = this.getMouseListeners();
        mouseMotionListeners = this.getMouseMotionListeners();
        for(int i=0; i<mouseListeners.length; i++)
            this.removeMouseListener((MouseListener) mouseListeners[i]);
        for(int i=0; i<mouseMotionListeners.length; i++)
            this.removeMouseMotionListener((MouseMotionListener) mouseMotionListeners[i]);
        this.removeAll();
        this.init(this.getWidth(),this.getHeight());
    
    }
   
    public void stop()
    {
        System.out.println("Stop");
    }
    
    public void exitGame()
    {
        System.exit(1);
    }
    
    @Override
    public void paint(Graphics g)
    {
        System.out.println("paint");
        if(!this.gameStart)
        {
            this.menu.draw(g, this);
            return;
        }
        this.gameController.drawBoard(g, this);
        if(this.gameController.gameStatus!=0)this.restartButton.drawImage(g, this);
        
           
               
    }


    public void update()
    {
        this.repaint();
    }
   

    /**
     * @param args the command line arguments
     */
   /* public static void main(String[] args) 
    {
        int w,h;
        JFrame f;
        TicTacToe gm;
        
        gm =  new TicTacToe();
        f = new JFrame("TicTacToe");
        w=600;
        h=600;
        
        gm.init(w,h);
        
        f.addWindowListener(new WindowAdapter() {});
        f.getContentPane().add("Center", gm);
        f.pack();
        f.setVisible(true);
        f.setSize(w, h+30);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gm.start();
    }
    */
}
