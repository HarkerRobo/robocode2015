package org.harker.robotics.commands;

import org.harker.robotics.subsystems.Manipulator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveToHeightCommand extends Command {
	
	private double targetHeight, startHeight;
	private static double ACCURACY_THRESHOLD = 0.4;
	private Manipulator manipulator;
	
	/**
	 * Moves the elevator to the given height relative to the base of the robot in inches.
	 * @param height The height to travel to in inches relative to the base.
	 */
    public MoveToHeightCommand(double height) {
        this.targetHeight = height;
        manipulator = Manipulator.getInstance();
        startHeight = manipulator.getAverageElevatorHeight();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	manipulator.moveElevator(Math.signum(targetHeight - manipulator.getAverageElevatorHeight()));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if ((manipulator.isLowSwitchPressed() && targetHeight < startHeight) || 
    	    (manipulator.isHighSwitchPressed() && targetHeight > startHeight))
    		return true;
    	
    	if (targetHeight > startHeight) {
    		return manipulator.getAverageElevatorHeight() >= targetHeight;
    	} else {
    		return manipulator.getAverageElevatorHeight() <= targetHeight;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	manipulator.moveElevator(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
