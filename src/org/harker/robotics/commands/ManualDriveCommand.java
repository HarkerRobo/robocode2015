package org.harker.robotics.commands;

import org.harker.robotics.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command is reponsible for taking user input from the Joystick and
 * instructing the drivetrain to drive with those values. 
 * 
 * @author Andrew Tierno
 */
public class ManualDriveCommand extends Command {

    public ManualDriveCommand() {
        requires(Drivetrain.getInstance());
    }

    /**
     * Called just before this Command runs the first time. Unused. 
     */
    protected void initialize() {
    }

    /**
     * Invokes the Drivetrain's drive method with the inputs from the Joystick. 
     */
    protected void execute() {
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
