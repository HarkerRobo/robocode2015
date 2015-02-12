package org.harker.robotics.subsystems;

import org.harker.robotics.RobotMap;
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
 */
public class Manipulator extends Subsystem {
    
	// Singleton instance
	private static Manipulator manipulator;
	
	// Solenoids that control the clamps
	private Solenoid leftClamp;
	private Solenoid rightClamp;
	
	// Talons that lift the elevator
	private TalonWrapper elevatorTalon1;
	private TalonWrapper elevatorTalon2;
	
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
		elevatorTalon1 = new TalonWrapper(RobotMap.Manipulator.ELEVATOR_TALON_1_PORT);
		elevatorTalon2 = new TalonWrapper(RobotMap.Manipulator.ELEVATOR_TALON_2_PORT);
		limitSwitchLow = new DigitalInput(RobotMap.Manipulator.LIMIT_SWITCH_LOW_PORT);
		limitSwitchHigh = new DigitalInput(RobotMap.Manipulator.LIMIT_SWITCH_HIGH_PORT);
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
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Moves the elevator according to a specified speed.
     * 
     * @param speed the speed to be set in the range [-1, 1]
     */
    public void moveElevator(double speed) {
    	elevatorTalon1.set(speed);
    	elevatorTalon2.set(speed);
    }
    
    /**
     * Returns whether the elevator has reached its maximum height.
     * 
     * @return whether the elevator has reached its maximum height
     */
    public boolean isHighSwitchPressed() {
    	return limitSwitchHigh.get();
    }
    
    /**
     * Returns whether the elevator has reached its minimum height.
     * 
     * @return whether the elevator has reached its minimum height
     */
    public boolean isLowSwitchPressed() {
    	return limitSwitchLow.get();
    }
    
    /**
     * Toggles the left clamp.
     */
    public void toggleLeftClamp() {
    	leftClamp.set(!leftClamp.get());
    }
    
    /**
     * Toggles the right clamp.
     */
    public void toggleRightClamp() {
    	rightClamp.set(!rightClamp.get());
    }
    
    /**
     * Opens the right clamp.
     */
    public void openRightClamp() {
    	rightClamp.set(CLAMP_OPEN_STATE);
    }
    
    /**
     * Opens the left clamp.
     */
    public void openLeftClamp() {
    	leftClamp.set(CLAMP_OPEN_STATE);
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
    	rightClamp.set(CLAMP_CLOSED_STATE);
    }
    
    /**
     * Closes the left clamp.
     */
    public void closeLeftClamp() {
    	leftClamp.set(CLAMP_CLOSED_STATE);
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
    public void setRightClamp(boolean state) {
    	rightClamp.set(state);
    }
    
    /**
     * Set the state of the left clamp based on a specified state.
     * 
     * @param state the state to be set
     */
    public void setLeftClamp(boolean state) {
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
