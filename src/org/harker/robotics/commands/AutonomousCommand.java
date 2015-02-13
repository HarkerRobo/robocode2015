package org.harker.robotics.commands;

import org.harker.robotics.subsystems.Drivetrain;
import org.harker.robotics.subsystems.Manipulator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Runs a series of commands in autonomous mode to pick up the crate and 
 * deposit it after a set distance.
 * 
 * @author Vedaad Shakib
 */
public class AutonomousCommand extends CommandGroup {
    
	Manipulator manipulator;
	Drivetrain drivetrain;
	
	public static final int TIME_TO_DRIVE = 3; // we don't have encoders anymore :(
	                                           // let's just assume that the battery is full voltage :)
	
    public  AutonomousCommand() {
    	manipulator = Manipulator.getInstance();
    	drivetrain = Drivetrain.getInstance();
    	
    	addSequential(new OpenClampsCommand());
    	addSequential(new ResetElevatorCommand());
    	addSequential(new CloseClampsCommand());
    	addParallel(new DriveForTimeCommand(TIME_TO_DRIVE));
        addSequential(new OpenClampsCommand());
    }
}
