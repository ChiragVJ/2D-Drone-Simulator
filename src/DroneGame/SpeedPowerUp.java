package DroneGame;

import java.text.DecimalFormat;

import javafx.scene.image.Image;
/**
 * @author chirag vijay 27009630
 * Class for speed powerup
 */
public class SpeedPowerUp extends Object {
	private static DecimalFormat df2 = new DecimalFormat("#");
	
	;
	
	public SpeedPowerUp() {};
	/**Set Powerup size s at x,y
	 * @param x - x coord
	 * @param y - y coord
	 * @param s - size
	 */
	public SpeedPowerUp(double x, double y, double s) {
		super(x, y, s, 0);
		
		size = s;
		I = new Image(this.getClass().getResourceAsStream("/resources/SpeedPowerUp.png"));//grab image for powerup
		
	
	}
	/**
	 * drawObject - draw the object
	 *  
	 * @param mc - canvas
	 */
	@Override
	public void drawObject(MyCanvas mc) {
		mc.drawImage(I, x/mc.getXCanvasSize(), y/mc.getYCanvasSize(), size);
	}
	
	

	/**
	 * checkObject - change angle of travel if hitting wall or another ball
	 * delete the drone if it goes out of bounds. 
	 * @param b  DroneArena
	 */
	@Override
	protected void checkObject(DroneArena b) {
		if (b.checkHit(x , y , size*450, objectID,"SpeedPowerUp") == 1) {
		
		}
	}

	/**
	 * adjustObject 
	 */
	@Override
	protected void adjustObject() {
		
	}
	/**
	 * saveString - what is written into the file, format used so different entities can be read easily
	 */
	@Override
	public String saveString() {
		
		return "(F)\\"+df2.format(x)+"/["+df2.format(y)+ "]{"+ speed + "}<" + angle+">";
	}
	/**
	 * //to string to say where drone is  and where its travelling
	 */
	public String toString() { 
		//to string to say where drone is  and where its travelling
		return "Speed Power Up is at " + df2.format(x) + "," + df2.format(y);

	}
		
}
