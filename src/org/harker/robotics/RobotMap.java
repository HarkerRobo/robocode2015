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
	    public static final int LEFT_FRONT_TALON_PORT = 0;
	    public static final int LEFT_BACK_TALON_PORT = 2;
	    public static final int RIGHT_FRONT_TALON_PORT = 3;
	    public static final int RIGHT_BACK_TALON_PORT = 1;
	    
	    public static final int GYRO_PORT = 0;
	}
	
	/**
	 * Constants pertaining specifically to the Manipulator.
	 */
	public class Manipulator {
		public static final int LEFT_CLAMP_PORT = 4;
		public static final int RIGHT_CLAMP_PORT = 5;
		public static final int LEFT_BIN_CLAMP_PORT = 6;
		public static final int RIGHT_BIN_CLAMP_PORT = 7;
		public static final int ELEVATOR_TALON_PORT = 4;
		public static final int LIMIT_SWITCH_HIGH_PORT = 8;
		public static final int LIMIT_SWITCH_LOW_PORT = 9;
	}
	
	/**
	 * Constants pertaining specifically to the OI.
	 */
	public class OI {
		public static final int GAMEPAD_PORT = 0;
	}
}
