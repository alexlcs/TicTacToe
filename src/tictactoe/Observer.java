
package tictactoe;

/**
 * This interface can be implemented by any class not wants to observe over another 
 * observable class.
 * 
 * @author sashi
 */
public interface Observer 
{
    public void update(Object... objects);
    
}
