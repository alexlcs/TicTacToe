
package tictactoe;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

/**
 * This can be implemented by any class that wants the ability to be drawn.
 * @author sashi
 */
public interface Drawable
{
    public void draw(Object position, Graphics g, ImageObserver img);
}
