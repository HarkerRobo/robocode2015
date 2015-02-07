package org.harker.robotics.subsystems;

import org.harker.robotics.commands.ManualDriveCommand;
import org.harker.robotics.harkerrobolib.wrappers.TalonWrapper;
import org.harker.robotics.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The Drivetrain, which contains accessors for the Talons along the base as well as 
 * the gyroscope and encoders. 
 * 
 * @author Andrew Tierno
 * @author Manan Shah
 */

public class Drivetrain extends Subsystem {
	
	private static RobotDrive robotDrive;
	private static TalonWrapper leftBack, leftFront, rightBack, rightFront;
	
	//Deadzone constants
	private static double DZ_Y = 0.15;
	private static double DZ_T = 0.20;
	private static double DZ_X = 0.15;
	
	//Theta scale because we need to ensure we don't move theta too fast
	private static double T_SCALE = 0.2;
	
	// The static instance variable Drivetrain
	private static Drivetrain drivetrain;
	
	/**
	 * Drivetrain singleton constructor (private and empty)
	 */
	private Drivetrain() {
		leftBack = new TalonWrapper(RobotMap.Drivetrain.LEFT_FRONT_TALON_PORT);
		rightBack = new TalonWrapper(RobotMap.Drivetrain.LEFT_BACK_TALON_PORT);
		leftFront = new TalonWrapper(RobotMap.Drivetrain.RIGHT_FRONT_TALON_PORT);
		rightFront = new TalonWrapper(RobotMap.Drivetrain.RIGHT_BACK_TALON_PORT);
		robotDrive = new RobotDrive(leftBack, rightBack, leftFront, rightFront);
	}
	
	/**
	 * Gets an instance of the Drivetrain (needed for singleton)
	 * @return An instance of the drivetrain
	 */
	public static Drivetrain getInstance() {
		if (drivetrain == null) drivetrain = new Drivetrain();
		return drivetrain;
	}
	
	public void drive(double sx, double sy, double rotation, double gyroAngle) {
		robotDrive.mecanumDrive_Cartesian(sx, sy, rotation*T_SCALE, gyroAngle);
	}
	/**
	 * Sets the default command to which the subsystem reverts when 
	 * nothing else is being called. For the Drivetrain this is the 
	 * Manual Drive Command. 
	 */
    public void initDefaultCommand() {
    	setDefaultCommand(new ManualDriveCommand());
    }
}

