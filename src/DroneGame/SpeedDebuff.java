package DroneGame;

import java.text.DecimalFormat;

import javafx.scene.image.Image;
/**
 * @author chirag vijay 27009630
 * Class for speeddebuff 
 */
public class SpeedDebuff extends Object {
	private static DecimalFormat df2 = new DecimalFormat("#");
	
	
	
	public SpeedDebuff() {};
	/**Set speeddebuff size s at x,y
	 * @param x - x coord
	 * @param y - y coord
	 * @param s - size
	 */
	public SpeedDebuff(double x, double y, double s) {
		super(x, y, s, 0);
		
		size = s;
		I = new Image(this.getClass().getResourceAsStream("/resources/SpeedDebuff.png"));
	
		
	
	}
	@Override
	public void drawObject(MyCanvas mc) {
		drawSystem(mc);
	}
	
	public void drawSystem (MyCanvas mc) {
		// first clear canvas 
		mc.drawImage(I, x/mc.getXCanvasSize(), y/mc.getYCanvasSize(), size);
			// draw Drone at 0,0
}
	

	@Override
	protected void checkObject(DroneArena b) {
		if (b.checkHit(x , y , size*450, objectID,"SpeedDebuff") == 1) {
		
		}
	}

	/* (non-Javadoc)
	 * 
	 */
	@Override
	protected void adjustObject() {
		
	}
	@Override
	public String saveString() {
		
		return "(S)\\"+df2.format(x)+"/["+df2.format(y)+ "]{"+ speed + "}<" + angle+">";
	}
	public String toString() { 
		//to string to say where drone is  and where its travelling
		return "Speed Debuff is at " + df2.format(x) + "," + df2.format(y);

	}
		
}
