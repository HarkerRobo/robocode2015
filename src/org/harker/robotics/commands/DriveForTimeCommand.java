package org.harker.robotics.commands;

import org.harker.robotics.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives for a specific time (can't do distance as we have no encoders)
 * 
 * @author Vedaad Shakib
 */
public class DriveForTimeCommand extends Command {

	Drivetrain drivetrain;
	double startTime;
	double time;
	
	/**
	 * Constructor
	 * 
	 * @param time the time to drive in seconds
	 */
    public DriveForTimeCommand(double time) {
    	this.time = time;
    	startTime = Timer.getFPGATimestamp();
    	drivetrain = Drivetrain.getInstance();
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drivetrain.drive(0, 1, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - startTime > time;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
