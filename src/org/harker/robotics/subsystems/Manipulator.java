package org.harker.robotics.subsystems;

import org.harker.robotics.RobotMap;
import org.harker.robotics.commands.ManualElevatorCommand;
import org.harker.robotics.harkerrobolib.wrappers.TalonWrapper;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The Manipulator, which contains methods to manipulate the elevator talons, 
 * solenoid clamps, limit switches, and range finder, represents the manipulator that 
 * controls the stacking and manipulation of totes. 
 * 
 * @author Vedaad Shakib
 * @author Andrew Tierno
 */
public class Manipulator extends Subsystem {
    
	// Singleton instance
	private static Manipulator manipulator;
	
	// Solenoids that control the clamps
	private Solenoid leftClamp;
	private Solenoid rightClamp;
	
	// Talons that lift the elevator
	private TalonWrapper elevatorTalon;
	
	// Limit switches that detect if the elevator is too high or low
	private DigitalInput limitSwitchLow;
	private DigitalInput limitSwitchHigh;
	
	// The open and closed states of the Solenoids
	public boolean CLAMP_OPEN_STATE = false;
	public boolean CLAMP_CLOSED_STATE = true;
	
	/**
	 * Creates a new Manipulator instance by initializing all of the elements of the manipulator.
	 */
	private Manipulator() {
		leftClamp = new Solenoid(RobotMap.Manipulator.LEFT_CLAMP_PORT);
		rightClamp = new Solenoid(RobotMap.Manipulator.RIGHT_CLAMP_PORT);
		elevatorTalon = new TalonWrapper(RobotMap.Manipulator.ELEVATOR_TALON_PORT);
		limitSwitchLow = new DigitalInput(RobotMap.Manipulator.LIMIT_SWITCH_LOW_PORT);
		limitSwitchHigh = new DigitalInput(RobotMap.Manipulator.LIMIT_SWITCH_HIGH_PORT);
	}
	
	public static void initialize() {
		if (manipulator == null)
			 manipulator = new Manipulator();
	}
	
	/**
	 * Gets an instance of the Manipulator (needed for singleton)
	 * @return An instance of the manipulator
	 */
	public static Manipulator getInstance() {
		if (manipulator == null) manipulator = new Manipulator();
		return manipulator;
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new ManualElevatorCommand());
    }
    
    /**
     * Moves the elevator according to a specified speed. However, if 
     * a limit switch is pressed and the desired speed would move the
     * elevator further in that direction, the speed will then be set
     * to zero.
     * 
     * @param speed the speed to be set in the range [-1, 1]
     */
    public void moveElevator(double speed) {
    	double spd = speed;
    	if (isHighSwitchPressed() && spd > 0)
    		spd = 0;
    	else if (isLowSwitchPressed() && spd < 0)
    		spd = 0;
    	elevatorTalon.set(spd);
    }
    
    /**
     * Returns whether the elevator has reached its maximum height.
     * 
     * @return whether the elevator has reached its maximum height
     */
    public boolean isHighSwitchPressed() {
    	return ! limitSwitchHigh.get();
    }
    
    /**
     * Returns whether the elevator has reached its minimum height.
     * 
     * @return whether the elevator has reached its minimum height
     */
    public boolean isLowSwitchPressed() {
    	return ! limitSwitchLow.get();
    }
    
    /**
     * Toggles the left clamp.
     */
    public void toggleLeftClamp() {
    	setLeftClamp(!leftClamp.get());
    }
    
    /**
     * Toggles the right clamp.
     */
    public void toggleRightClamp() {
    	setRightClamp(!rightClamp.get());
    }
    
    /**
     * Opens the right clamp.
     */
    public void openRightClamp() {
    	setRightClamp(CLAMP_OPEN_STATE);
    }
    
    /**
     * Opens the left clamp.
     */
    public void openLeftClamp() {
    	setLeftClamp(CLAMP_OPEN_STATE);
    }
    
    /**
     * Opens both the left and right clamp.
     */
    public void openClamps() {
    	openLeftClamp();
    	openRightClamp();
    }
    
    /**
     * Closes the right clamp.
     */
    public void closeRightClamp() {
    	setRightClamp(CLAMP_CLOSED_STATE);
    }
    
    /**
     * Closes the left clamp.
     */
    public void closeLeftClamp() {
    	setLeftClamp(CLAMP_CLOSED_STATE);
    }
    
    /**
     * Closes both the left and right clamps.
     */
    public void closeClamps() {
    	closeRightClamp();
    	closeLeftClamp();
    }
    
    /**
     * Set the state of the right clamp based on a specified state.
     * 
     * @param state the state to be set																																												
     */
    private void setRightClamp(boolean state) {
    	rightClamp.set(state);
    }
    
    /**
     * Set the state of the left clamp based on a specified state.
     * 
     * @param state the state to be set
     */
    private void setLeftClamp(boolean state) {
    	leftClamp.set(state);
    }

    /**
     * Set the state of the both the left and right clamps based on a specified state.
     * 
     * @param state the state to be state
     */
    public void setClamps(boolean state) {
    	setRightClamp(state);
    	setLeftClamp(state);
    }
}
