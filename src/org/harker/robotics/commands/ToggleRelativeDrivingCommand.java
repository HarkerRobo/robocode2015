package org.harker.robotics.commands;

import org.harker.robotics.Robot;
import org.harker.robotics.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Toggles the state of the Drivetrain as to whether driving should be relative, 
 * that is, directions are set relative to the user's position or absolute, where
 * directions are set given the field's position. 
 * 
 * @author Andrew Tierno
 */
public class ToggleRelativeDrivingCommand extends Command {

	private Drivetrain driveTrain;
	/**
	 * Creates a new Command. Empty constructor. 
	 */
    public ToggleRelativeDrivingCommand() {
    	driveTrain = Drivetrain.getInstance();
    	requires(driveTrain);
    }

    /**
     * Called just before this Command runs the first time. Unused. 
     */
    protected void initialize() {
    }

    /**
     * Toggles the isRelative flag in Drivetrain
     */
    protected void execute() {
    	Drivetrain.getInstance().toggleRelative();
    }

    /**
     * This method always returns true as in immediately terminates. 
     */
    protected boolean isFinished() {
        return true;
    }

    /**
     * Called once after isFinished returns true. unused. 
     */
    protected void end() {
    }

    /**
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run. Unused. 
     */
    protected void interrupted() {
    }
}
