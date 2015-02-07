package org.harker.robotics.subsystems;

import org.harker.robotics.commands.ManualDriveCommand;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The Drivetrain, which contains accessors for the Talons along the base as well as 
 * the gyroscope and encoders. 
 * 
 * @author Andrew Tierno
 */
public class Drivetrain extends Subsystem {
	
	private static Drivetrain drivetrain;
	
	private Drivetrain() {
		
	}
	
	/**
	 * Gets an instance of the robot's drivetrain and initializes it 
	 * if it was previously null. 
	 * @return An instance of the drivetrain
	 */
	public static Drivetrain getInstance() {
		if (drivetrain == null) drivetrain = new Drivetrain();
		return drivetrain;
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

