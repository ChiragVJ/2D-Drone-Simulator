package DroneGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 * @author chirag vijay 27009630
 * Class for canvas drone
 */
public class MyCanvas {
	int xCanvas = 600;
	int yCanvas = 600;
	GraphicsContext gc; 
	
	/**
	 * Set graphicscontext, and x and y sizes of the canvas
	 * @param graphics - graphics context
	 * @param xCanSize - x canvas size
	 * @param yCanSize - y canvas size
	 */
	public MyCanvas(GraphicsContext graphics, int xCanSize, int yCanSize) {
		gc = graphics;
		xCanvas = xCanSize;
		yCanvas = yCanSize;
	}
	
	 /**
     * get size in x of canvas
     * @return xsize
     */
    public int getXCanvasSize() {
    	return xCanvas;
    }
    /**
     * get size of xcanvas in y    
     * @return ysize
     */
    public int getYCanvasSize() {
    	return yCanvas;
    }
    /**
     * clear the canvas
     */
    public void clearCanvas() {
		gc.clearRect(0,  0,  xCanvas,  yCanvas);	// clear canvas
    }
    
    /**
     * drawIt ... draws object defined by given image at position and size
     * @param i		image
     * @param x		xposition	in range 0..1
     * @param y     yposition	in range 0..1
     * @param sz	size
     */
	public void drawImage (Image i, double x, double y, double sz) {
			// to draw centred at x,y, give top left position and x,y size
			// sizes/position in range 0..1, so scale to canvassize 
		gc.drawImage(i, xCanvas * (x - sz/2), yCanvas*(y - sz/2), xCanvas*sz, yCanvas*sz);
	}

	

	
	

}

