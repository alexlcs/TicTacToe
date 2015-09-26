
package ui;

import tictactoe.Observable;
import tictactoe.Observer;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.LinkedList;
import javax.swing.JApplet;

/**
 * Rescales Images to fit the given frames size
 * @author sashi
 */
public class Rescaler implements Observable,  ComponentListener
{
    private LinkedList<Observer> observers;

    public Rescaler()
    {
        this.observers = new LinkedList();
    }
    
    
    @Override
    public void componentResized(ComponentEvent e) 
    {
        System.out.println("Component Resized ..");
        Integer newWidth, newHeight;
        JApplet apl;
        
        apl = (JApplet)e.getComponent();
        newWidth = apl.getWidth();
        newHeight = apl.getHeight();
        
        Images.resizeBoard( newWidth, newHeight); 
        this.notifyObservers();
    }

    @Override
    public void componentMoved(ComponentEvent e) 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentShown(ComponentEvent e) 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentHidden(ComponentEvent e) 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addObserver(Observer obs)
    {
        this.observers.add(obs);
    }

    @Override
    public void notifyObservers() 
    {
        for(int i=0; i<this.observers.size(); i++)
           this.observers.get(i).update();
    }
   
    
    public void removeAllObservers()
    {
        this.observers.clear();
    }
}
