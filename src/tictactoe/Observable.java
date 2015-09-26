
package tictactoe;

/**
 * This interface can be implemented by any class wanting to be observed by an 
 * Observer.
 * Implementing class needs to create at least one field for an Observer type.
 * 
 * @author sashi
 */
public interface Observable
{
    public void addObserver(Observer obs);
    
    /**
     * Notifies Observers of this object.
     * 
     */
    public void notifyObservers();
}
