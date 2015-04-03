package org.harker.robotics;

import org.harker.robotics.RobotMap;
import org.harker.robotics.commands.ToggleClampsCommand;
import org.harker.robotics.commands.ToggleBotBinClampCommand;
import org.harker.robotics.commands.ToggleLeftClampCommand;
import org.harker.robotics.commands.ToggleTopBinClampCommand;
import org.harker.robotics.commands.ToggleRightClampCommand;
import org.harker.robotics.harkerrobolib.wrappers.GamepadWrapper;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * 
 * @author Andrew Tierno
 * @author Vedaad Shakib
 * @author Manan Shah
 */
public class OI {
    public static Command manualDrive;
	private static OI oi; 
	public static GamepadWrapper gamepad;
	
	/**
	 * OI singleton constructor. Initializes the gamepad along with 
	 * the functionalities associated with buttons on the gamepad. 
	 */
	public OI() {
		gamepad = new GamepadWrapper(RobotMap.OI.GAMEPAD_PORT);
//		gamepad.getButtonB().whenPressed(new ToggleRelativeDrivingCommand());
		gamepad.getButtonA().whenPressed(new ToggleClampsCommand());
		gamepad.getButtonBumperLeft().whenPressed(new ToggleLeftClampCommand());
		gamepad.getButtonBumperRight().whenPressed(new ToggleRightClampCommand());
		gamepad.getButtonX().whenPressed(new ToggleBotBinClampCommand());
		gamepad.getButtonY().whenPressed(new ToggleTopBinClampCommand());
//		gamepad.getButtonX().whenPressed(new ResetElevatorCommand());
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