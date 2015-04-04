package org.harker.robotics.commands;

import org.harker.robotics.subsystems.Drivetrain;
import org.harker.robotics.subsystems.Manipulator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Picks up bin with bin mechanism.
 * 
 * @author Vedaad Shakib
 */
public class PickupBinCommand extends CommandGroup {
    
	Manipulator manipulator;
	Drivetrain drivetrain;
	
	private static final double TIME_TO_SCORING = 1.8;
	
    public  PickupBinCommand() {
       drivetrain = Drivetrain.getInstance();
       manipulator = Manipulator.getInstance();
       
       addSequential(new ToggleBotBinClampCommand());
       addSequential(new DriveForTimeCommand(0.5, -0.5));
       addSequential(new ToggleTopBinClampCommand());
       addSequential(new DriveForTimeCommand(TIME_TO_SCORING-0.1, -1));
    }
}
