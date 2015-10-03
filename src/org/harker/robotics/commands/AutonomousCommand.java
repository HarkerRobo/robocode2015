package org.harker.robotics.commands;

import org.harker.robotics.subsystems.Drivetrain;
import org.harker.robotics.subsystems.Manipulator;

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
	
	private static final double TIME_TO_SCORING = 1.8;
	private static final double WAIT_TIME = 1;
	private static final double TIME_TO_TOTE = 2;
	private static final double TOTE_HEIGHT = 27;
	private static final double BIN_HEIGHT = 20;
	
    public  AutonomousCommand() {
    	manipulator = Manipulator.getInstance();
    	drivetrain = Drivetrain.getInstance();
    	drivetrain.disable();
    	
    	addSequential(new OpenClampsCommand());
//		addSequential(new ResetElevatorCommand());
				
//		String mode = SmartDashboard.getString("Autonomous mode");
    	String mode = "Backup";
		System.out.println("Mode: " + mode);

		try {
    	if (mode.equalsIgnoreCase("Bin")) {
	    	addSequential(new CloseClampsCommand());
//	    	addSequential(new MoveToHeightCommand(BIN_HEIGHT));
//	    	addSequential(new RotateCommand(-90));
	    	addSequential(new DriveForTimeCommand(TIME_TO_SCORING, 0.6));
//	    	addSequential(new ResetElevatorCommand());
	        addSequential(new OpenClampsCommand());
    	} else if (mode.equalsIgnoreCase("Tote")) {
    		System.out.println("Start tote");
	    	addSequential(new CloseClampsCommand());
	    	addSequential(new WaitForTimeCommand(WAIT_TIME));
	    	addSequential(new DriveForTimeCommand(0.2, -0.3));
	    	addSequential(new ElevForTimeCommand(0.5, 1));
	    	
//	    	addSequential(new MoveForTimeCommand(.3));
//	    	addSequential(new RotateCommand(-90));
	    	addSequential(new RotateForTimeCommand(2, 0.3));
	    	addSequential(new DriveForTimeCommand(1.5, 0.7));
//	    	addSequential(new ResetElevatorCommand());
	        addSequential(new OpenClampsCommand());
    	} else if (mode.equalsIgnoreCase("Backup")) {
    		addSequential(new CloseClampsCommand());
    		addSequential(new ElevForTimeCommand(0.4, 1.6));
//    		addSequential(new StrafeForTimeCommand(SmartDashboard.getNumber("Strafe Time"), SmartDashboard.getNumber("Strafe Speed")));
//    		addSequential(new DriveForTimeCommand(SmartDashboard.getNumber("Move Time"), SmartDashboard.getNumber("Auto speed")));
    		addSequential(new DriveForTimeCommand(2.1, 0.5));
    		addSequential(new WaitForTimeCommand(1));
    		addSequential(new OpenClampsCommand());
//    		addSequential(new ElevForTimeCommand(-0.4, 2.5));
    	} else if (mode.equalsIgnoreCase("Proto")) {
//    		addParallel(new ElevForTimeCommand(-1, 6));
    		
    		addSequential(new DriveForTimeCommand(4.1, -0.5));
    		addSequential(new WaitForTimeCommand(1));
    		addSequential(new ToggleTopBinClampCommand());
    		addSequential(new WaitForTimeCommand(2));
    		addSequential(new ToggleBotBinClampCommand());
    		addSequential(new WaitForTimeCommand(2));
    		
    		addSequential(new ToggleTopBinClampCommand());
    		addSequential(new WaitForTimeCommand(0.5));
    		
    		addParallel(new RotateForTimeCommand(0.5, 0.1));
    		addSequential(new DriveForTimeCommand(1.1, 1));
    		addSequential(new ToggleBotBinClampCommand());
    		addSequential(new WaitForTimeCommand(1));
    	} else {
    		addSequential(new CloseClampsCommand());
//    		addSequential(new MoveToHeightCommand(TOTE_HEIGHT+BIN_HEIGHT));
    		addSequential(new DriveForTimeCommand(TIME_TO_TOTE, 0.6));
    		addSequential(new OpenClampsCommand());
//    		addSequential(new ResetElevatorCommand());
    		addSequential(new CloseClampsCommand());
//    		addSequential(new MoveToHeightCommand(TOTE_HEIGHT));
    		addSequential(new RotateCommand(-90));
	    	addSequential(new DriveForTimeCommand(TIME_TO_SCORING, 0.6));
//	    	addSequential(new ResetElevatorCommand());
	        addSequential(new OpenClampsCommand());
    	}    	
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	Drivetrain.getInstance().enable();
    }
}
