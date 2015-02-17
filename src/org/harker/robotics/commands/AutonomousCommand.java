package org.harker.robotics.commands;

import org.harker.robotics.subsystems.Drivetrain;
import org.harker.robotics.subsystems.Manipulator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	private static final double TIME_TO_SCORING = .8;
	private static final double TIME_TO_TOTE = 2;
	private static final double TOTE_HEIGHT = 20;
	private static final double BIN_HEIGHT = 20;
	
    public  AutonomousCommand() {
    	manipulator = Manipulator.getInstance();
    	drivetrain = Drivetrain.getInstance();
    	
		addSequential(new OpenClampsCommand());
		addSequential(new ResetElevatorCommand());
				
		String mode = SmartDashboard.getString("Autonomous mode");

    	if (mode.equals("Bin")) {
	    	addSequential(new CloseClampsCommand());
	    	addSequential(new MoveToHeightCommand(BIN_HEIGHT));
//	    	addSequential(new RotateCommand(-90));
	    	addSequential(new DriveForTimeCommand(TIME_TO_SCORING));
	    	addSequential(new ResetElevatorCommand());
	        addSequential(new OpenClampsCommand());
    	} else if (mode.equals("Tote")) {
	    	addSequential(new CloseClampsCommand());
	    	Timer.delay(.1);
	    	addSequential(new MoveToHeightCommand(TOTE_HEIGHT));
//	    	addSequential(new RotateCommand(90));
	    	addSequential(new DriveForTimeCommand(TIME_TO_SCORING));
	    	addSequential(new ResetElevatorCommand());
	        addSequential(new OpenClampsCommand());
    	} else if (mode.equals("Test")) {
    		addSequential(new MoveToHeightCommand(30));
    		Timer.delay(1);
    		addSequential(new MoveToHeightCommand(20));
    		Timer.delay(1);
    		addSequential(new MoveToHeightCommand(40));
    		Timer.delay(1);
    		addSequential(new ResetElevatorCommand());
    	} else {
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
