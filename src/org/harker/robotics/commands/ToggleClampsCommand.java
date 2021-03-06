package org.harker.robotics.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.harker.robotics.subsystems.Manipulator;

/**
 * Toggles both clamps
 * 
 * @author Vedaad Shakib
 */
public class ToggleClampsCommand extends Command {
	
	Manipulator manipulator;

    public ToggleClampsCommand() {
        manipulator = Manipulator.getInstance();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Executed tcc");
    	manipulator.toggleLeftClamp();
    	manipulator.toggleRightClamp();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
