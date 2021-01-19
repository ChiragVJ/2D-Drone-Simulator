package DroneGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import DroneGame.DroneArena;
import DroneGame.MyCanvas;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author chirag vijay 27009630 Class for GUI
 */
public class GUI extends Application {

	private AnimationTimer timer; // create a timer for later use
	private MyCanvas mc;
	private VBox rtPane; // where the arena contents will be listed
	private DroneArena arena;
	JFileChooser chooser = new JFileChooser(); // create a Jfilechooser to read and write files later on

	/**
	 * showMessage - used to display message within a menu option
	 * 
	 * @param TStr- what the title of the alert says
	 * @param CStr  - what the alert says
	 */
	private void showMessage(String TStr, String CStr) {
		Alert alert = new Alert(AlertType.INFORMATION); // create a information alert
		alert.setTitle(TStr); // sets the title using the argument
		alert.setHeaderText(null); // no header text
		alert.setContentText(CStr); // sets the context using the argument

		alert.showAndWait(); // do nothing until user does something
	}

	/**
	 * showAbout - used to display the about message within a menu option
	 * 
	 * 
	 */
	private void showAbout() {
		showMessage("About", "Chirag's Drone Simulator");
	}

	/**
	 * showHelp - used to display the help message within a menu option
	 * 
	 * 
	 */
	private void showHelp() {
		showMessage("Help", "A program which simulates the drones" + "\n"
				+ "Click on the canvas to place a tree where the cursor is \nThe blue drones can get killed by the red drones\nThe green drones avoid all posible objects\nThe red drones die if they hit trees but the blue drones don't"
				+ "\nThe snail slows down either the red or blue drone\nSonic speeds up either the red or blue drone\nIf a drone goes out of bounds it will respawn back in a random position");
	}

	/**
	 * saveFile - used to save arena and its contents to a file
	 * 
	 * 
	 */
	private void saveFile() {
		JFrame parentFrame = new JFrame(); // create a JFrame to use GUI

		chooser.setDialogTitle("Specify a file to save"); // what the GUI says to the user

		int userSelection = chooser.showSaveDialog(parentFrame); // GUI for save dialog

		if (userSelection == JFileChooser.APPROVE_OPTION) { // if the save file is approved
			File fileToSave = chooser.getSelectedFile(); // get the selected file
			System.out.println("Save as file: " + fileToSave.getAbsolutePath()); // print to user the file that is being
																					// saved to
			try {
				PrintWriter file = new PrintWriter(new File(fileToSave.getAbsolutePath() + ".txt")); // create a
																										// printwriter
																										// named file
																										// taking the
																										// path of the
																										// file that
																										// everything
																										// will be saved
																										// to

				file.write(arena.saveString()); // write to the file the consolecanvas tostring which is the arena
												// itself
				file.close(); // close the file
			} catch (FileNotFoundException e) {

				System.out.println("invalid file name"); // if the file cannot be found then output this.
			}

		}

	}

	/**
	 * loadFile - loads the selected file into the arena
	 * 
	 * @throws IO Exception - IO
	 * 
	 */
	private void loadFile() throws IOException {
		int returnVal = chooser.showOpenDialog(null); // create chooser GUI
		if (returnVal == JFileChooser.APPROVE_OPTION) { // if the chooser
			File selFile = chooser.getSelectedFile();
			// System.out.println("You chose to open this file:" + selFile.getName());
			// //print to user which file was loaded
			if (selFile.isFile()) { // if the file exists
				try {

					BufferedReader sc = new BufferedReader(new FileReader(selFile.getAbsolutePath()));// create a
																										// buffered
																										// reader
					arena.clear(); // clear the arena
					mc.clearCanvas(); // clear the canvas
					String line; // create string to contain the contents of the line read
					char type; // holds type of object
					String x, y, speed, angle; // create strings to hold values

					while ((line = sc.readLine()) != null) { // until the end of the file
						type = line.charAt(1); // the type of object is between the first two brackets
						x = line.substring(line.indexOf("\\") + 1, line.lastIndexOf("/")); // the x coordinate lies
																							// between \ and /
						y = line.substring(line.indexOf("[") + 1, line.indexOf("]")); // the y coordinate lies between [
																						// and ]
						speed = line.substring(line.indexOf("{") + 1, line.indexOf("}")); // the speed lies between {
																							// and }
						angle = line.substring(line.indexOf("<") + 1, line.indexOf(">")); // the angle lies between <
																							// and >
						if (type == 'A') { // if the type is the arena
							mc.xCanvas = (int) Double.parseDouble(x); // alter the x canvas size to the size of the
																		// arena
							mc.yCanvas = (int) Double.parseDouble(y); // alter the y canvas size to the size of the
																		// arena
						}
						arena.loadFromFile(type, Double.parseDouble(x), Double.parseDouble(y),
								Double.parseDouble(speed), Double.parseDouble(angle)); // create the objects which have
																						// been read form the file
						drawWorld(); // draw the objects
						drawStatus(); // draw the strings on the right pane
						// System.out.println(type + " " + x + " " + y + " "+ speed + " " + angle);

					}
					sc.close(); // close the file

				} catch (FileNotFoundException e) {

					System.out.println("Invalid file name"); // if the file doesn't exist then output to the user
				}

			}

		}

	}

	/**
	 * changeSize - changes the size of the arena depending on user input
	 * 
	 * 
	 */
	public void changeSize() {
		timer.stop(); // stop the timer
		mc.clearCanvas(); // clear the canvas
		String stringX = "0"; // initialize string
		String stringY = "0"; // initialize string
		stringX = getDialog("Arena x size", "Please enter the arena x size", stringX); // ask user for x size
		stringY = getDialog("Arena x size", "Please enter the arena y size", stringY); // ask user for y size

		if ((stringX.matches("^[0-9]*$") && stringY.matches("^[0-9]*$") && ((stringX.isEmpty()) != true)
				&& ((stringY.isEmpty()) != true))) { // input must be a number

			double userX = Double.parseDouble(stringX); // change string value to double
			double userY = Double.parseDouble(stringY); // change string value to double
			if ((userX > 600) || (userY > 600) || (userX <= 0 || (userY <= 0))) { // if user inputs either too big or
																					// too small
				Alert alert = new Alert(AlertType.ERROR); // create an error alert
				alert.setTitle("Invalid Arena Size"); // set thte title of the alert to being invalid arena size
				alert.setContentText("Arena size must be between 1 and 600"); // set the context of the alert to being
																				// what the arena size should be
				alert.showAndWait(); // wait for user to perform an action
			} else { // otherwise
				double ratioX = mc.xCanvas / userX; // create a ratio of increase or decrease in arena size
				double ratioY = mc.yCanvas / userY; // create a ratio of increase or decrease in arena size
				// mc.xCanvas = (int) userX; // cast to int and assign new size to canvas size
				// mc.yCanvas = (int) userY; // cast to int and assign new size to canvas size
				arena.setX(userX); // assign new size to arena size
				arena.setY(userY);// assign new size to arena size
				arena.positionChange(ratioX, ratioY); // scale drones to new position
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR); // create an error alert
			alert.setTitle("Invalid Arena Size"); // set thte title of the alert to being invalid arena size
			alert.setContentText("Size must be a number"); // set the context of the alert to being what the arena size
															// should be
			alert.showAndWait(); // wait for user to perform an action
		}
	}

	MenuBar setMenu() {
		MenuBar menuBar = new MenuBar(); // create menu

		Menu mHelp = new Menu("Help"); // have entry for help
		// then add sub menus for About and Help
		// add the item and then the action to perform
		MenuItem mAbout = new MenuItem("About");
		mAbout.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				showAbout(); // show the about message
			}
		});
		MenuItem help = new MenuItem("Help");
		help.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				showHelp();
			}
		});
		mHelp.getItems().addAll(mAbout, help); // add submenus to Help

		Menu mFile = new Menu("File");
		MenuItem mExit = new MenuItem("Exit");
		mExit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				System.exit(0); // quit program
			}
		});
		MenuItem mLoad = new MenuItem("Load"); // load item in file menu
		mLoad.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				try {
					timer.stop(); // stop the timer
					loadFile(); // call loadfile
				} catch (IOException e) { // catch exception if file not found

					e.printStackTrace();
				}
			}
		});
		MenuItem mSave = new MenuItem("Save"); // Save item in file menu
		mSave.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				timer.stop(); // stop timer
				saveFile(); // call save file
			}
		});
		mFile.getItems().addAll(mExit, mLoad, mSave); // add items to file menu

		menuBar.getMenus().addAll(mFile, mHelp); // menu has File and Help

		return menuBar;
	} // return the menu, so can be added

	private HBox setButtons() {
		// create button
		Button btnBottom = new Button("Drone");

		btnBottom.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { // if clicked

				arena.addDrone(); // add drone to random coordinates in arena
				drawWorld(); // draw all objects
				drawStatus(); // update right pane
			}

		});

		Button btnBottom1 = new Button("Hunter Drone"); // add random hunter drone button

		btnBottom1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { // if clicked

				arena.addHunterDrone(); // . add hunter drone to random coordinates in the arena
				drawWorld(); // draw all objects
				drawStatus(); // update right pane

			}

		});
		Button btnSensor = new Button("Sensor Drone"); // add random sensor drone button

		btnSensor.setOnAction(new EventHandler<ActionEvent>() { // if clicked
			@Override
			public void handle(ActionEvent event) {

				arena.addSensorDrone(); // add sensor drone to random coordinates in the arena
				drawWorld(); // draw all objects
				drawStatus(); // update right pane

			}

		});
		Button speedde = new Button("Speed Debuff"); // add random speed debuff button

		speedde.setOnAction(new EventHandler<ActionEvent>() { // if clicked
			@Override
			public void handle(ActionEvent event) {

				arena.addSpeedDebuff(); // add speed debuff to ranadom coordinates in the arena
				drawWorld(); // draw the objects
				drawStatus(); // update right pane
			}

		});
		Button speedPu = new Button("Speed PowerUp"); // add rnadom speed powerup button

		speedPu.setOnAction(new EventHandler<ActionEvent>() { // if clicked
			@Override
			public void handle(ActionEvent event) {

				arena.addSpeedPowerUp(); // add speed powerup to random coordinates in the arena
				drawWorld(); // draw the objects
				drawStatus(); // update right pane
			}

		});
		Button btnAnimOn = new Button("Start"); // start button

		btnAnimOn.setOnAction(new EventHandler<ActionEvent>() { // if clicked
			@Override
			public void handle(ActionEvent event) {
				timer.start(); // start the timer

			}
		});
		Button btnAnimOff = new Button("Stop"); // stop button
		// now add handler
		btnAnimOff.setOnAction(new EventHandler<ActionEvent>() { // if clicked
			@Override
			public void handle(ActionEvent event) {
				timer.stop(); // stop the timer
			}
		});
		Button changeCan = new Button("Arena Size"); // change arena size button

		changeCan.setOnAction(new EventHandler<ActionEvent>() { // if clicked
			@Override
			public void handle(ActionEvent event) {
				changeSize();
				drawWorld(); // draw all objects
				drawStatus(); // update right pane
			}
		});
		Button clear = new Button("Clear"); // clear button which clears the canvas

		clear.setOnAction(new EventHandler<ActionEvent>() { // if clicked
			@Override
			public void handle(ActionEvent event) {
				timer.stop(); // stop the timer
				arena.clear(); // clear the arena
				mc.clearCanvas(); // clear the canvas
				drawWorld(); // draw all the objects
				drawStatus(); // update righ tpane

			}
		});
		return new HBox(new Label("    Add:    "), btnBottom, btnBottom1, btnSensor, speedde, speedPu,
				new Label("  Options:  "), btnAnimOn, btnAnimOff, changeCan, clear); // add buttons to interface
	}

	/**
	 * drawWorld- draws all objects
	 * 
	 **/
	public void drawWorld() {
		mc.clearCanvas();// clear cnavas
		arena.drawArena(mc); // draw all objects
	}

	/**
	 * drawStatus - update right pane
	 * 
	 **/
	public void drawStatus() {
		rtPane.getChildren().clear(); // clear the right pane
		Label info = new Label(arena.toString());// get label which has information on system
		rtPane.getChildren().add(info);// add label to rtPane
	}

	private void setMouseEvents(Canvas canvas) { // mouse click handle
		canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() { // if mouse is clicked
			public void handle(MouseEvent e) {
				arena.addTree(e.getX(), e.getY()); // add a tree to the position in which the click occured
				drawWorld(); // draw all objects
				drawStatus(); // update right pane
			}
		});
	}

	/**
	 * getDialog - create dialog
	 * 
	 * @param TStr  - title string
	 * @param PStr  - header string
	 * @param userX - user input
	 * @return String - user input
	 */
	private String getDialog(String TStr, String PStr, String userX) {
		TextInputDialog dialog = new TextInputDialog(userX); // create textinput dialog for user to input text
		dialog.setTitle(TStr); // set title
		dialog.setHeaderText(PStr); // set header
		Optional<String> result = dialog.showAndWait(); // do nothing and wait for action
		if (result.isPresent())
			return result.get();
		else
			return userX; // return user input

	}

	public void start(Stage stagePrimary) throws Exception {

		stagePrimary.setTitle("Chirag Vijay - Drone Simulation - 27009630");

		BorderPane bp = new BorderPane(); // create border pane
		bp.setPadding(new Insets(10, 20, 10, 20));
		bp.setTop(setMenu()); // create menu, add to top

		Group root = new Group(); // create group
		Canvas canvas = new Canvas(600, 600);
		// and canvas to draw in
		root.getChildren().add(canvas);
		bp.setLeft(root);// and add canvas to group
		mc = new MyCanvas(canvas.getGraphicsContext2D(), 600, 600);
		// create MyCanvas passing context on canvas onto which images put

		setMouseEvents(canvas); // set mouse handler
		arena = new DroneArena(mc.getXCanvasSize(), mc.getYCanvasSize());
		drawWorld();

		timer = new AnimationTimer() { // create timer
			public void handle(long currentNanoTime) {
				arena.checkObjects(); // every time the timer goes up check objects
				arena.adjustObjects(); // then adjust them
				drawWorld(); // draw all the objects
				drawStatus(); // update right pane
			}
		};
		rtPane = new VBox(); // set vBox on right to list items
		rtPane.setAlignment(Pos.TOP_LEFT); // set alignment
		rtPane.setPadding(new Insets(5, 75, 75, 5)); // padding
		bp.setRight(rtPane); // add rtPane to borderpane right

		bp.setBottom(setButtons()); // set bottom pane with buttons

		// set overall scene
		Scene scene = new Scene(bp, mc.getXCanvasSize() * 1.65, mc.getYCanvasSize() * 1.2);
		bp.setStyle("-fx-background-color: rgba(148, 218, 181, 0.7)"); // set background colour

		stagePrimary.setScene(scene);
		stagePrimary.show();

	}

	public static void main(String[] args) {
		Application.launch(args);

	}
}
