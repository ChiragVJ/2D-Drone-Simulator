package DroneGame;

import java.util.ArrayList;

import java.util.Random;
/**
 * @author chirag vijay 27009630
 * Class for Arena of objects
 */
public class DroneArena {

	private Random rgen = new Random();

	private double xSize, ySize, xRand, yRand, randAng; // create variables for the x and y size of the arena and random
	int counter; // x and y positions to place drones.
	private ArrayList<Object> objects;
	Random randGen = new Random(); // create a random number generator
	/**
	 * construct an arena
	 */
	DroneArena() {
		this(500, 400);
	}
	/**
	 * construct arena of size x by y
	 * @param x
	 * @param y
	 */
	public DroneArena(double x, double y) { // create drone arena constructor

		xSize = x; // arguments passed through are the size of the arena.
		ySize = y;
		objects = new ArrayList<Object>(); // create an arraylist of type drone called drones
		
	
		
	}
	
	

	/**
	 * addDrone - add a drone to a random position in the arena
	 */

	public void addDrone() { // method to add drone into an arena
		int xs = 20+(int)xSize-40;// cast double to int to be able to generate random int 
		int ys = 20+(int)ySize-40;
		xRand = rgen.nextInt(xs);// x pos is randomly generated depending on size of the arena 
		yRand = rgen.nextInt(ys);// y pos is randomly generated depending on size of the arena 
		randAng = rgen.nextInt(360); // generate rtandom angle between 0 and 360
		objects.add(new Drone(xRand, yRand, 0.05, randAng, 1));// add the drone to the array list 

	}
	/**
	 * addTree - add a tree to a random position in the arena
	 * @param d - x position
	 * @param e - y position
	 */
	public void addTree(double d, double e) {
		objects.add(new Tree(d,e, 0.1));
	}
/**
	 * addHunterDrone - add a hunter drone to a random position in the arena
	 */
	public void addHunterDrone() { // method to add hunter drone into an arena
		int xs = (int) xSize; // cast double to int to be able to generate random int 
		int ys = (int) ySize;
		xRand = rgen.nextInt(xs);// x pos is randomly generated depending on size of the arena 
		yRand = rgen.nextInt(ys);// y pos is randomly generated depending on size of the arena 
		randAng = rgen.nextInt(360); // generate rtandom angle between 0 and 360
		objects.add(new HunterDrone(xRand, yRand, 0.05, randAng, 1)); // add the hunter drone to the array list 

	}
	/**
	 * addSensorDrone - add a sensor drone to a random position in the arena
	 */
	public void addSensorDrone() { //method to add sensor drone into an arena
		int xs = 20+(int)xSize-40; // cast double to int to be able to generate random int 
		int ys = 20+(int)ySize-40;
		xRand = rgen.nextInt(xs);// x pos is randomly generated depending on size of the arena 
		yRand = rgen.nextInt(ys);// y pos is randomly generated depending on size of the arena 
		randAng = rgen.nextInt(360);// generate rtandom angle between 0 and 360
		objects.add(new SensorDrone(xRand, yRand, 0.05, randAng, 1.3));// add the sensor drone to the array list
	}/**
	 * addSpeedDebuff - add a speed debuff to a random position in the arena
	 */
	public void addSpeedDebuff() { // method to add speed debuff into an arena
		int xs = 20+(int)xSize-40;// cast double to int to be able to generate random int 
		int ys = 20+(int)ySize-40;
		xRand = rgen.nextInt(xs);// x pos is randomly generated depending on size of the arena 
		yRand = rgen.nextInt(ys);// y pos is randomly generated depending on size of the arena 
		objects.add(new SpeedDebuff(xRand, yRand, 0.05));// add the speed debuff  to the array list

	}
	/**
	 * addSpeedPowerup - add a speed powerup to a random position in the arena
	 */
	public void addSpeedPowerUp() { //  method to add speed power up into an arena
	
		int xs = 20+(int)xSize-40;// cast double to int to be able to generate random int 
		int ys = 20+(int)ySize-40;// 
		xRand = rgen.nextInt(xs);// //x pos is randomly generated depending on size of the arena 
		yRand = rgen.nextInt(ys);// y pos is randomly generated depending on size of the arena 
		objects.add(new SpeedPowerUp(xRand, yRand, 0.05)); // add the speed powerup to the array list

	}
	/**
	 * positionChange - scales the position of the objects to the correct size depending on ratio's provided
	 * @param ratioX - x scale ratio
	 * @param ratioY - y scale ratio
	 */
	public void positionChange(double ratioX, double ratioY) {
		for (int i = 0; i < objects.size(); i++) {
			Object b = objects.get(i);
			b.x = b.x/ratioX;
			b.y = b.y/ratioY;
			
		}
	}
	/**
	 * clear - removes all the objects from the arena
	 */
	public void clear() {
		objects.clear();
		Object.objectCounter = 0;
		
	}

	/**
	 * loadFromFile - adds objects into arena depending on paramaters
	 * @param c - character which defines what type of object it is
	 * @param x - x coordinate
	 * @param y - coordinate
	 * @param sp - speed
	 * @param ang - angle
	 */
	public void loadFromFile(char c, double x, double y, double sp, double ang) {
		
		if (c == 'A') { // if the type is arena then alter the arena size 
			xSize = x;
			ySize = y;
		}
		else if (c == 'D') {
			objects.add(new Drone(x,y,0.05, ang, sp)); // create drone using arguments
		}
		else if (c == 'H') {
			objects.add(new HunterDrone(x,y,0.05,ang,sp)); // create hunter drone using arguments
			
		}
		else if (c == 'T') {
			objects.add(new Tree(x,y ,0.1)); // create tree using arguments 
			
		}
		else if (c == 'S') {
			objects.add(new SpeedDebuff(x,y, 0.05)); // create speed debuff using arguments
		}
		else if (c == 'M') {
			objects.add(new SensorDrone(x,y, 0.05, ang, sp)); //create sensordrone using arguments
			
		}
		else if (c == 'F') {
			objects.add(new SpeedPowerUp(x,y, 0.05)); // create speed powerup uising arguments
		}
		
	}
	/**
	 * //to string to say where all the objects are
	 * @return info - string which stores the locations and types of the objects in the arena
	 */
	public String toString() { // to string of drone arena which returns the size of the arena and the drones
								// inside of it
		String info = "";

		for (Object c : objects)
			info += c.toString() + "\n";

		return info;

	}
/**
	 * checkObjects - checks the object of every object in the arena
	 * (a traditional for loop was used to avoid a concurrect modification exception that was present when using the advanced one)
	 */
	public void checkObjects() {

		for (int i = 0; i < objects.size(); i++) { // for every object in the arena call check object
			Object b = objects.get(i);
			b.checkObject(this); 
		}
	}
/**
	 * adjustObjects - adjusts the object of every object in the arena
	 */
	public void adjustObjects() {
		for (Object b : objects)  // for every object in the arena call adjust object
			b.adjustObject();

	}
/**
	 * drawArena - draws all the objects into the arena 
	 * @param mc - the canvas in which objects are drawn
	 */
	public void drawArena(MyCanvas mc) {
		for (Object b : objects)
			b.drawObject(mc); // draw all objects
	}
/**
	 * saveString - what is written into the file, format used so different entities can be read easily
	 * @return info - contains information of objects in arena
	 */
	public String saveString() {
		String info = "";
		info += "(A)\\"+ xSize + "/[" + ySize + "]{0.0}<0.0>\n";
		for (Object c : objects) {
			info += c.saveString() + "\n";
		}
	return info;
		
	}
	/**
	 * CheckDroneAngle - alters the angle of the drone to produce an effect
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param rad - size
	 * @param ang - angle
	 * @param notID - id
	 * @param type - type of object
	 * @return ang - returns new calculated angle
	 */
	public double CheckDroneAngle(double x, double y, double rad, double ang, int notID, String type) {
		double ans = ang;
		randAng = rgen.nextInt(360);
		if (x < rad*1.5 || x > xSize - rad*1.5)
			ans = 180 - ans;
		// if ball hit (tried to go through) left or right walls, set mirror angle,
		// being 180-angle
		if (y < rad*1.5 || y > ySize - rad*1.5)
			ans = -ans;
		// if try to go off top or bottom, set mirror angle
		
		for (Object b : objects)
			if (type == "SensorDrone") { // if the drone is a sensor drone 
				if (b.getID() != notID && b.hitting(x, y, rad*1.5)) {
					
					ans = 180* Math.atan2(y - b.getY(), x - b.getX()) /Math.PI; // change the angle depending on how close other object is
							
					
			}}
			else if (b.getID() != notID && b.hitting(x, y, rad*0.75))
				ans = 180 * Math.atan2(y - b.getY(), x - b.getX()) / Math.PI;
		// check all drones except one with given id
		// if hitting, return angle between the other drones and this one.
		return ans; // return the angle
	}
/**
	 * return x position
	 * @param g - arena
	 * @return x 
	 */
	public double getX(DroneArena g) { // get the xsize of an arena

		return xSize;
	}
/**
	 * return y position
	 * @param g - arena
	 * @return y
	 */
	public double getY(DroneArena g) { // get the ysize of an arena

		return ySize;
	}
	
	/**
	 * 
	 * @param x - x coord
	 */
	public void setX(double x) {
		xSize = x;
	}
	/**
	 * 
	 * @param y - y coord
	 */
	public void setY(double y) {
		ySize = y;
	}
	
	
	
	/**
	 * remove -  remove a specific object from the arena 
	 * @param ID - id of object
	 */
	public void remove(int ID) {
		objects.remove(ID); //  remove the object
		Object.objectCounter--; // reduce the object counter by 1
		for (int counter = ID; counter < objects.size(); counter++) {
			objects.get(counter).objectID--; //for the objects infront of the deleted object, reduce their object counter by 1
			
	}
	}
	/**
	 * checkHit - checks if two objects have collided
	 * @param x - x coord
	 * @param y - y coord
	 * @param size - size
	 * @param ID - object ID
	 * @param type - type of object
	 * @return int - if hit or not
	 */
	public int checkHit(double x, double y, double size, int ID, String type) {
		if (type == "HunterDrone") { //if the type is hunterdrone
			for (int i = 0; i < objects.size(); i++) { // for every object
				Object b = objects.get(i);
				if (b instanceof Tree && b.hitting(x, y, size)) { // if the object is a tree and hitting the hunterdrone
					objects.remove(ID); //remove the hunterdrone
					Object.objectCounter--; //reduce the counter by 1 
					for (int counter = ID; counter < objects.size(); counter++) {
						objects.get(counter).objectID--; //for the objects infront of the deleted object, reduce their object counter by 1
					}
					return 1; 
				}

			}

		} else if (type == "SpeedDebuff") { //if type is debuff
			for (int i = 0; i < objects.size(); i++) { // for every object
				Object b = objects.get(i);
				if (((b instanceof Drone) || (b instanceof HunterDrone)) && b.hitting(x, y, size)) { // if the object is a drone or hunterdrone and hitting the speeddebuff
					b.speed = b.speed/1.5; // reduce the speed
					objects.remove(ID); //remove the speed debuff
					Object.objectCounter--; //reduce the counter by 1 
					
					for (int counter = ID; counter < objects.size(); counter++) {
						objects.get(counter).objectID--;   //for the objects infront of the deleted object, reduce their object counter by 1
					}
					return 1;
				}

			}

		}
		
		else if (type == "SpeedPowerUp") { //if type if powerup
			for (int i = 0; i < objects.size(); i++) {  // for every object
				Object b = objects.get(i);
				if (((b instanceof Drone) || (b instanceof HunterDrone)) && b.hitting(x, y, size)) {   
					b.speed = b.speed*1.5; // increase the speed
					objects.remove(ID); //remove the speedpowerup
					Object.objectCounter--; //reduce the counter by 1 
					
					for (int counter = ID; counter < objects.size(); counter++) {
						objects.get(counter).objectID--; //for the objects infront of the deleted object, reduce their object counter by 1
					}
					return 1;
				}

			}

		}
		else if (type == "Drone") { //if type is drone 
			for (int i = 0; i < objects.size(); i++) {  // for every object
				Object b = objects.get(i);
				if (b instanceof HunterDrone && b.hitting(x, y, size)) {// if the object is a hunterdrone and hitting the drone
					objects.remove(ID);  //remove the drone
					Object.objectCounter--;  //reduce the counter by 1 
					for (int counter = ID; counter < objects.size(); counter++) {
						objects.get(counter).objectID--; //for the objects infront of the deleted object, reduce their object counter by 1
							 
					}
					return 1;
				}
			}
		}
		
		return 0; //else return 0 
	}
}
