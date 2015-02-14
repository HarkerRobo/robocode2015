package org.harker.robotics.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PersistentCommands extends CommandGroup {
    
    public  PersistentCommands() {
        addParallel(new UpdateElevatorHeightCommand());
    }
}
