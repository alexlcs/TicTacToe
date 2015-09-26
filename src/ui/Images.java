
package ui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author sashi
 */
public class Images 
{
    public static Image board, originalBoard;
    public static Image X, originalX;
    public static Image O, original0;
    public static Image singlePlayer, multiPlayer;
    public static Image originalSinglePlayer, originalMultiPlayer;
    public static Image exit, originalExit;
    public static int xoWidth;
    public static int xoHeight;
    private static Images instance=null;
    
    private Images()
    {   
        try
        {
            Images.board = (Image)ImageIO.read(this.getClass().getClassLoader().getResource("resources/board.jpg"));
            Images.X = (Image)ImageIO.read(this.getClass().getClassLoader().getResource("resources/x.jpg"));
            Images.O = (Image)ImageIO.read(this.getClass().getClassLoader().getResource("resources/o.jpg"));
            Images.singlePlayer = (Image)ImageIO.read(this.getClass().getClassLoader().getResource("resources/singleP.png"));
            Images.multiPlayer = (Image)ImageIO.read(this.getClass().getClassLoader().getResource("resources/multP.png"));
            Images.exit = (Image)ImageIO.read(this.getClass().getClassLoader().getResource("resources/exit.png"));

            Images.originalBoard = Images.board;
            Images.originalX = Images.X;
            Images.original0 = Images.O;
            Images.originalSinglePlayer = Images.singlePlayer;
            Images.originalMultiPlayer = Images.multiPlayer;
            Images.originalExit = Images.exit;
        }
        catch(Exception ex)
        {
            System.out.println("Images Initialization ::\n"+ex.toString());

        }
    }
    
    public static void init()
    {
        if(instance ==null)
        {
            Images.instance = new Images();
        }
        
    }
    
    public static Image resizeImage(Image originalImage, int width, int height)
    {
        
	BufferedImage resizedImage;
        Graphics2D g;
        
      
        resizedImage = new BufferedImage(width, height,1);
	g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, width, height, null);
	g.dispose();
		
	//return originalImage.getScaledInstance(width, height, 1);
        return resizedImage;
    }
    
    public static void resizeBoard(int newWidth, int newHeight)
    {
        System.out.println("Resizing board...");
        Images.board = Images.resizeImage(Images.originalBoard, newWidth, newHeight);
        Images.xoWidth = (newWidth/3);
        Images.xoHeight = (newHeight/3);
        Images.O = Images.resizeImage(Images.original0, Images.xoWidth-40, Images.xoHeight-40);
        Images.X = Images.resizeImage(Images.originalX, Images.xoWidth-40, Images.xoHeight-40);
        Images.singlePlayer = Images.resizeImage(Images.originalSinglePlayer, newWidth/4, newHeight/5);
        Images.multiPlayer = Images.resizeImage(Images.originalMultiPlayer, newWidth/4, newHeight/5);
        Images.exit = Images.resizeImage(Images.originalExit, newWidth/4, newHeight/5);

    }

    
}
