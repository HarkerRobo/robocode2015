package org.harker.robotics.commands;

import org.harker.robotics.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives for a specific time (can't do distance as we have no encoders)
 * 
 * @author Vedaad Shakib
 */
public class RotateCommand extends Command {

	Drivetrain drivetrain;
	double startRot;
	double endRot;
	
	/**
	 * Constructor
	 * 
	 * @param time the time to drive in seconds
	 */
    public RotateCommand(double rotation) {
    	drivetrain = Drivetrain.getInstance();
    	this.startRot = drivetrain.getCurrentAbsoluteHeading();
    	this.endRot = startRot + rotation;
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (endRot > startRot)
    		drivetrain.drive(0, 0, .9);
    	else
    		drivetrain.drive(0, 0, -.9);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (endRot > startRot)
    		return endRot <= drivetrain.getCurrentAbsoluteHeading();
    	else
    		return endRot >= drivetrain.getCurrentAbsoluteHeading();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
