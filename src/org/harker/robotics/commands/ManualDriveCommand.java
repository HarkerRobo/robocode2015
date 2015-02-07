package org.harker.robotics.commands;

import org.harker.robotics.OI;
import org.harker.robotics.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command is responsible for taking user input from the Joystick and
 * instructing the drivetrain to drive with those values. 
 * 
 * @author Andrew Tierno
 */
public class ManualDriveCommand extends Command {

	private Drivetrain drivetrain;

	public ManualDriveCommand() {
    	drivetrain = Drivetrain.getInstance();
    	requires(drivetrain);
    }

    /**
     * Called just before this Command runs the first time. Unused. 
     */
    protected void initialize() {
    }

    /**
     * Invokes the Drivetrain's drive method with the inputs from the Joystick.
     * The x and y magnitudes of the left joystick are mapped to the translational
     * velocities of the robot and the x magnitude of the right joystick is 
     * mapped to the rotational velocity. 
     */
    protected void execute() {
    	drivetrain.drive(OI.gamepad.getLeftX(), OI.gamepad.getLeftY(), OI.gamepad.getRightX());
    }

    /**
     * This command never terminates, thus this never reports being finished. 
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     * Called once after isFinished returns true. Unused. 
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
