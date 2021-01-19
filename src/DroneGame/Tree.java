package DroneGame;

import java.text.DecimalFormat;

import javafx.scene.image.Image;
/**
 * @author chirag vijay 27009630
 * Class for tree
 */
public class Tree extends Object {

	
	private static DecimalFormat df2 = new DecimalFormat("#");
	public Tree(double x, double y, double s) {
		super(x, y, s,0);
		size = s;
		I = new Image(this.getClass().getResourceAsStream("/resources/tree.png")); //grab image for tree
		
		
	
	}
	@Override
	public void drawObject(MyCanvas mc) {
		mc.drawImage(I, x/mc.getXCanvasSize(), y/mc.getYCanvasSize(), size);// draw using image, at x/canvas, y/canvas size . with size passed through
	}
	
	/**
	 * //to string to say where drone is  and where its travelling
	 */
	public String toString() { 
		
		return "Tree is at " + df2.format(x) + "," + df2.format(y);

	}
	@Override
	protected void checkObject(DroneArena b) {
		
	}
	/**
	 * saveString - what is written into the file, format used so different entities can be read easily
	 */
	@Override
	public String saveString() {
		
		return "(T)\\"+df2.format(x)+"/["+df2.format(y)+"]{"+ speed + "}<" + angle+">";
	}
}
