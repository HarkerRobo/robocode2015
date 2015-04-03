package org.harker.robotics.commands;
import org.harker.robotics.OI;
import org.harker.robotics.subsystems.Manipulator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Moves the elevator.
 * 
 * @author Vedaad Shakib
 */
public class ManualElevatorCommand extends Command {
	Manipulator manipulator;
	public double prevTime;
	
    public ManualElevatorCommand() {
        manipulator = Manipulator.getInstance();
        requires(manipulator);
        prevTime = Timer.getFPGATimestamp();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//The triggers move in a range of 0 to 1. In order to simulate a range of -1 to 1 the difference
    	//of the two triggers is taken.
    	double val = OI.gamepad.getRightTrigger() - OI.gamepad.getLeftTrigger();
    	manipulator.moveElevator(val);
    	manipulator.getAverageElevatorHeight();
    	
    	double curTime = Timer.getFPGATimestamp();
    	SmartDashboard.putNumber("Loop time", curTime - prevTime);
    	prevTime = curTime;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
