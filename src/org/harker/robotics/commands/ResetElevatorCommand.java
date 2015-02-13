package org.harker.robotics.commands;

import org.harker.robotics.subsystems.Manipulator;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Resets the elevator to the lowest position
 * 
 * @author Vedaad Shakib
 */
public class ResetElevatorCommand extends Command {

	Manipulator manipulator;
	
    public ResetElevatorCommand() {
        manipulator.getInstance();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	manipulator.moveElevator(-1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return manipulator.isLowSwitchPressed();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
