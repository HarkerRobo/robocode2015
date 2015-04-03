package org.harker.robotics.commands;

import org.harker.robotics.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Logs the rotation error of vx in the range [-1...1] such that a correlation can be found with an external software.
 * 
 * @author Vedaad Shakib
 */
public class LogErrorCommand extends Command {

	Drivetrain drivetrain;
	double currVX = 0;
	double end;
	double error;
	double currTime;
	
    public LogErrorCommand() {
        drivetrain = Drivetrain.getInstance();
        requires(drivetrain);
        currTime = System.currentTimeMillis();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drivetrain.drive(currVX, 0, 0);
    	error = 0;
    	if ((System.currentTimeMillis() - currTime) > 100) {
        	System.out.println(currVX+", "+error);
    		currVX += 0.01;
    		currTime = System.currentTimeMillis();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return currVX >= 1;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
