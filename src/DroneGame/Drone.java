package DroneGame;



import java.text.DecimalFormat;

import DroneGame.MyCanvas;
import javafx.scene.image.Image;
/**
 * @author chirag vijay 27009630
 * Class for normal drone
 */
public class Drone extends Object {
	
	private static DecimalFormat df2 = new DecimalFormat("##");
	
	
	public Drone() {};
	/**Set drone size s at x,y with speed sp and angle a
	 * @param x - x coord
	 * @param y - y coord
	 * @param s - size
	 * @param a - angle
	 * @param sp - speed
	 */
	public Drone(double x, double y, double s, double a, double sp) {
		super(x, y, s, sp);
		speed = sp;
		angle = a;
		size = s;
		I = new Image(this.getClass().getResourceAsStream("/resources/drone.png")); //grab image for drone
		
		
	
	}
	/**
	 * drawObject - draw the object
	 *  
	 * @param mc Mycanvas
	 */
	@Override
	public void drawObject(MyCanvas mc) {
		mc.drawImage(I, x/mc.getXCanvasSize(), y/mc.getYCanvasSize(), size); // draw using image, at x/canvas, y/canvas size . with size passed through
	}
	
	
	
	/**
	 * checkObject - change angle of travel if hitting wall or another ball
	 * delete the drone if it goes out of bounds. 
	 * @param b  DroneArena
	 */
	@Override
	protected void checkObject(DroneArena b) {
		if (b.checkHit( x , y , size*400, objectID,"Drone") == 1) {} //do nothing if drone doesn't need to bounce
		else if((this.x >= b.getX(b)-15) || (this.x <= 0+15) || (this.y >= b.getY(b)-15 || this.y <= +15)){ //ensure drone cannot go out of bounds
			
			b.remove(this.objectID); //if drone goes out of bounds just delete it 
			b.addDrone(); // respawn drone in random position
			
		}
		
		else {
			angle = b.CheckDroneAngle(x, y, size*400, angle, objectID, "Drone"); //check drone angle for bouncing otherwise 
		}
		
	}

	/**
	 * adjustObject - alter the x and y position of the drone depending on the angle 
	 */
	@Override
	protected void adjustObject() {
		double radAngle = angle*Math.PI/180;		// put angle in radians
		x += (speed * Math.cos(radAngle));		// new X position
		y += (speed * Math.sin(radAngle));		// new Y position
	
		
	}
	/**
	 * saveString - what is written into the file, format used so different entities can be read easily
	 */
	@Override
	public String saveString() {
	
		return "(M)\\"+df2.format(x)+"/["+df2.format(y)+"]{"+ speed + "}<" + df2.format(angle%360)+">";
	}
	/**
	 * //to string to say where drone is  and where its travelling
	 */
	public String toString() { 
		int ang = (int)angle;
		return "Drone is at " +  df2.format(x) + "," + df2.format(y) + " and is travelling in a " + ang%360
				+ " degree angle";

	}
		
}
