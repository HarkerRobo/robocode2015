package org.harker.robotics.commands;

import org.harker.robotics.subsystems.Manipulator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevForTimeCommand extends Command {
	
//	private double targetHeight, startHeight;
	private Manipulator manipulator;
	double speed;
	
	/**
	 * Moves the elevator to the given height relative to the base of the robot in inches.
	 * @param time Time
	 * @param speed Speed
	 */
    public ElevForTimeCommand(double time, double speed) {
//        this.targetHeight = height;
    	setTimeout(time);
    	this.speed = speed;
        manipulator = Manipulator.getInstance();
        startHeight = manipulator.getAverageElevatorHeight();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Started Move to Height");

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	manipulator.moveElevator(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (manipulator.isHighSwitchPressed())
    		return true;
    	
    	return isTimedOut();
    	
//    	if (targetHeight > startHeight) {
//    		return manipulator.getAverageElevatorHeight() >= targetHeight;
//    	} else {
//    		return manipulator.getAverageElevatorHeight() <= targetHeight;
//    	}
    }
    
    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Finished MoveToHeight");
    	manipulator.moveElevator(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
