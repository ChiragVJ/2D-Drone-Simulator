package DroneGame;

import javafx.scene.image.Image;

/**
 * @author chirag
 * Abstract class
 */
public abstract class Object {
	protected double x, y, size, speed;
	protected double angle = 0.0;
	static int objectCounter = 0;
	protected int objectID;
	protected Image I;

	Object() {
		this(50, 50, 10, 0);

	}

	/**
	 * construct a object of r
	 *
	 * @param xPos - x coord
	 * @param yPos - y coord
	 * @param s  -  (Size)
	 * @param sp -  speed
	 */
	Object(double xPos, double yPos, double s, double sp) {
		x = xPos;
		y = yPos;
		size = s;
		speed = sp;
		objectID = objectCounter++;

	}

	public void drawObject(MyCanvas mc) {
		
	}
	/**
	 * return x position
	 * 
	 * @return x - x coord
	 */
	public double getX() {
		return x;
	}
	public int getID() {return objectID; }
	/**
	 * return y position
	 * 
	 * @return y - y coord
	 */
	public double getY() {
		return y;
	}

	/**
	 * return size of drone
	 * 
	 * @return
	 */
	public double getSize() {
		return size;
	}

	/**
	 * set the drone at position nx,ny
	 * 
	 * @param nx - x coord
	 * @param ny - y coord
	 */
	public void setXY(double nx, double ny) {
		x = nx;
		y = ny;
	}
	/**
	 * adjust drone position
	 * 
	 * 
	 * 
	 */
	protected void adjustObject() {
		
	}
	/**
	 * check drone position
	 * 
	 * 
	 * @param b - arena
	 */
	
	protected void checkObject(DroneArena b) {
		
	}
	
	/**
	 * is drone at ox,oy size or hitting this ball
	 * @param ox - x coord
	 * @param oy - y coord
	 * @param or - size
	 * @return true if hitting
	 */
	public boolean hitting(double ox, double oy, double or) {
		return (ox-x)*(ox-x) + (oy-y)*(oy-y) < (or/+size/2)*(or+size/2);
	}		// hitting if dist between ball and ox,oy < ist rad + or
	
	/** is ball hitting the other ball
	 * 
	 * @param oDrone - the other ball
	 * @return true if hitting
	 */
	public boolean hitting (Object oDrone) {
		return hitting(oDrone.x, oDrone.y, oDrone.size);
	}
	
	
	
	/**saveString
	 * @return - string to be saved 
	 */
	public String saveString() {
	return "";
	}
}


