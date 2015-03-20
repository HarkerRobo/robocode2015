package org.harker.robotics.commands;

import org.harker.robotics.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateForTimeCommand extends Command {
	
	Drivetrain drivetrain;
	double speed;

	/**
	 * Constructor
	 * 
	 * @param time the time to drive in seconds
	 */
     public RotateForTimeCommand(double seconds, double speed) {
    	drivetrain = Drivetrain.getInstance();
        requires(drivetrain);
        setTimeout(seconds);
//        this.time = Timer.getFPGATimestamp() + seconds;
//        System.out.println("End: " + time);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drivetrain.rotate(speed);
//    	if (speed < 0.6) speed += 0.05;
//    	System.out.println("Executed Drive for time");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	System.out.println(Timer.getFPGATimestamp());
//        return Timer.getFPGATimestamp() > time;
    	return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	drivetrain.rotate(0);
    	System.out.println("Finished Rotate for Time");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
