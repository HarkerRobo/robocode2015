package org.harker.robotics.commands;

import org.harker.robotics.subsystems.Drivetrain;
import org.harker.robotics.subsystems.Manipulator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Runs a series of commands in autonomous mode. Has two modes based on the SmartDashboard option "Autonomous Mode":
 * 1. Simple: Picks up the recycling bin, turns 90 degrees, drives forward into the scoring zone, and deposits bin.
 * 2. Complex: Picks up the recycling bin, deposits bin on the tote, picks up the tote, turns 90 degrees,
 * drives forward into the scoring zone, and deposits bin and tote.
 * 3. TOTEMODE (not written yet): (Starts from other side) Simple but picks up tote instead. 
 * 
 * @author Vedaad Shakib
 */
public class AutonomousCommand extends CommandGroup {
    
	Manipulator manipulator;
	Drivetrain drivetrain;
	
	private static final double TIME_TO_SCORING = 1; // we don't have encoders anymore :(
	                                             // let's just assume that the battery is full voltage :)
	private static final double TIME_TO_TOTE = 0.1;
	private static final double TOTE_HEIGHT = 5;
	private static final double BIN_HEIGHT = 20;
	
    public  AutonomousCommand(String mode) {
    	manipulator = Manipulator.getInstance();
    	drivetrain = Drivetrain.getInstance();
    	
    	if (mode.equals("Bin")) {
    		addSequential(new OpenClampsCommand());
	    	addSequential(new ResetElevatorCommand());
	    	addSequential(new CloseClampsCommand());
	    	addSequential(new MoveToHeightCommand(BIN_HEIGHT));
	    	addSequential(new RotateCommand(-90));
	    	addSequential(new DriveForTimeCommand(TIME_TO_SCORING));
	    	addSequential(new ResetElevatorCommand());
	        addSequential(new OpenClampsCommand());
    	} else if (mode.equals("Tote")) {
    		addSequential(new OpenClampsCommand());
	    	addSequential(new ResetElevatorCommand());
	    	addSequential(new CloseClampsCommand());
	    	addSequential(new MoveToHeightCommand(TOTE_HEIGHT));
	    	addSequential(new RotateCommand(90));
	    	addSequential(new DriveForTimeCommand(TIME_TO_SCORING));
	    	addSequential(new ResetElevatorCommand());
	        addSequential(new OpenClampsCommand());
    	} else {
    		addSequential(new OpenClampsCommand());
    		addSequential(new ResetElevatorCommand());
    		addSequential(new CloseClampsCommand());
    		addSequential(new MoveToHeightCommand(TOTE_HEIGHT+BIN_HEIGHT));
    		addSequential(new DriveForTimeCommand(TIME_TO_TOTE));
    		addSequential(new OpenClampsCommand());
    		addSequential(new ResetElevatorCommand());
    		addSequential(new CloseClampsCommand());
    		addSequential(new MoveToHeightCommand(TOTE_HEIGHT));
    		addSequential(new RotateCommand(-90));
	    	addSequential(new DriveForTimeCommand(TIME_TO_SCORING));
	    	addSequential(new ResetElevatorCommand());
	        addSequential(new OpenClampsCommand());
    	}
    }
}
