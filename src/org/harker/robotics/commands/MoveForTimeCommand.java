package org.harker.robotics.commands;

import org.harker.robotics.subsystems.Manipulator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveForTimeCommand extends Command {
	
	private double endTime;
	private Manipulator manipulator;
	
	/**
	 * Moves the elevator to the given height relative to the base of the robot in seconds.
	 * @param time The time to move the manipulator
	 */
    public MoveForTimeCommand(double time) {
        manipulator = Manipulator.getInstance();
        endTime = Timer.getFPGATimestamp() + time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	manipulator.moveElevator(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	if (manipulator.isLowSwitchPressed() || manipulator.isHighSwitchPressed())
//    		return true;
    	
    	return Timer.getFPGATimestamp() > endTime;
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
