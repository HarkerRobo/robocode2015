package org.harker.robotics.subsystems;

import org.harker.robotics.commands.ManualDriveCommand;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The Drivetrain, which contains accessors for the Talons along the base as well as 
 * the gyroscope and encoders. 
 * 
 * @author Andrew Tierno
 * @author Manan Shah
 */

public class Drivetrain extends Subsystem {
	
	//Deadzone constants
	public static double DZ_Y = 0.15;
	public static double DZ_T = 0.20;
	public static double DZ_X = 0.15;
	
	//Theta scale because we need to ensure we don't move theta too fast
	public static double T_SCALE = 0.2;
	
	// The static instance variable Drivetrain
	private static Drivetrain drivetrain;
	
	/**
	 * Drivetrain singleton constructor (private and empty)
	 */
	private Drivetrain() {
		
	}
	
	/**
	 * Gets an instance of the Drivetrain (needed for singleton)
	 * @return An instance of the drivetrain
	 */
	public static Drivetrain getInstance() {
		if (drivetrain == null) drivetrain = new Drivetrain();
		return drivetrain;
	}
	
}

