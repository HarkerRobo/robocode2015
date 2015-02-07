package org.harker.robotics;

import org.harker.robotics.harkerrobolib.wrappers.GamepadWrapper;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * 
 * @author Andrew Tierno
 */
public class OI {
	
	private static OI oi; 
	public static GamepadWrapper gamepad;
	
	private OI() {
		gamepad = new GamepadWrapper(RobotMap.OI.GAMEPAD_PORT);
	}
	
	/**
	 * Gets an instance of the current OI and initializes it if it is null. 
	 * @return An instance of the OI
	 */
	public static OI getInstance() {
		if (oi == null) oi = new OI();
		return oi;
	}
	
	/**
	 * Initializes the OI if it is null. 
	 */
	public static void initialize() {
		if (oi == null) 
			oi = new OI();
	}
}

