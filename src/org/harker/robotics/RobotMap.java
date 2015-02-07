package org.harker.robotics;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @author Andrew Tierno
 */
public class RobotMap {
	
	/**
	 * Constants pertaining specifically to the Drivetrain. 
	 */
	public class Drivetrain {
	    public static final int LEFT_FRONT_TALON_PORT = 3;
	    public static final int LEFT_BACK_TALON_PORT = 2;
	    public static final int RIGHT_FRONT_TALON_PORT = 0;
	    public static final int RIGHT_BACK_TALON_PORT = 1;
	    
	    public static final int GYRO_PORT = 0;
	}
	
	/**
	 * Constants pertaining specifically to the OI. 
	 */
	public class OI {
		public static final int GAMEPAD_PORT = 0;
	}
}
