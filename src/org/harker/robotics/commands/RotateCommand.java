package org.harker.robotics.commands;

import org.harker.robotics.subsystems.Drivetrain;

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
    	this.startRot = drivetrain.getCurrentContinuousHeading();
    	this.endRot = startRot + rotation;
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speed = .3;
    	if (endRot > startRot)
    		drivetrain.rotate(speed);
    	else
    		drivetrain.rotate(-speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (endRot > startRot)
    		return endRot <= drivetrain.getCurrentContinuousHeading();
    	else
    		return endRot >= drivetrain.getCurrentContinuousHeading();
    }

    // Called once after isFinished returns true
    protected void end() {
    	drivetrain.rotate(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
