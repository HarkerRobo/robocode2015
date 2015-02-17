package org.harker.robotics.subsystems;

import org.harker.robotics.RobotMap;
import org.harker.robotics.commands.ManualElevatorCommand;
import org.harker.robotics.commands.UpdateElevatorHeightCommand;
import org.harker.robotics.harkerrobolib.wrappers.TalonWrapper;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The Manipulator, which contains methods to manipulate the elevator talons, 
 * solenoid clamps, limit switches, and range finder, represents the manipulator that 
 * controls the stacking and manipulation of totes. 
 * 
 * @author Vedaad Shakib
 * @author Neymika J
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
	
	private AnalogInput rangeFinder;
	
	// The open and closed states of the Solenoids
	public final boolean CLAMP_OPEN_STATE = false;
	public final boolean CLAMP_CLOSED_STATE = true;
	
	//The inches of distance reported by the range finder per volt
	public final double INCHES_PER_VOLT = 512 / 5;
	
	//The speed to which the elevator slows when near the ends
	public final double ELEVATOR_SPEED_LIMIT = 0.3;
	
	//The distance in inches before limiting takes place
	public final double ELEVATOR_LIMIT_THRESHOLD = 3;
	
	//Fields for calculating the average height, to avoid random noise
	private double averageElevatorHeight;
	private double[] instantHeightValues;
	private int nDataPoints;
	
	//The sample size used for averaging
	private static final int DATA_POINTS_PER_CALC = 10;
	
	//Distance from top or bottom when to start decellerating
	public static final double MIN_HEIGHT = 15.5;
	public static final double TOP_HEIGHT = 73;
	
	//Decelleration Constant
	private static final double DECEL_PROP = 30;
	
	//Rangefinder offset
	private static final double RANGE_FINDER_OFFSET = 8.5;
		
	/**
	 * Creates a new Manipulator instance by initializing all of the elements of the manipulator.
	 */
	private Manipulator() {
		leftClamp = new Solenoid(RobotMap.Manipulator.LEFT_CLAMP_PORT);
		rightClamp = new Solenoid(RobotMap.Manipulator.RIGHT_CLAMP_PORT);
		
		elevatorTalon = new TalonWrapper(RobotMap.Manipulator.ELEVATOR_TALON_PORT);
		
		limitSwitchLow = new DigitalInput(RobotMap.Manipulator.LIMIT_SWITCH_LOW_PORT);
		limitSwitchHigh = new DigitalInput(RobotMap.Manipulator.LIMIT_SWITCH_HIGH_PORT);
		
		rangeFinder = new AnalogInput(RobotMap.Manipulator.RANGE_FINDER_PORT);
		
		averageElevatorHeight = getInstantElevatorHeight();
		instantHeightValues = new double[DATA_POINTS_PER_CALC];
		nDataPoints = 0;
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
     * @param spd the speed to be set in the range [-1, 1]
     */
    public void moveElevator(double spd) {
    	SmartDashboard.putNumber("Manipulator Height", averageElevatorHeight);
    	
    	SmartDashboard.putBoolean("Decellerating", 
    			(nearBottom() && spd < 0) || (nearTop() && spd > 0));
    	
    	if (nearBottom() && spd < 0) {
    		spd *= getAverageElevatorHeight() / DECEL_PROP;
    	}
    	else if (nearTop() && spd > 0){
    		spd *= (TOP_HEIGHT - getAverageElevatorHeight()) / DECEL_PROP;
    	}
    	
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
    	SmartDashboard.putBoolean("HIGH LIM", !limitSwitchHigh.get());
    	return ! limitSwitchHigh.get();
    }
    
    /**
     * Returns whether the elevator has reached its minimum height.
     * 
     * @return whether the elevator has reached its minimum height
     */
    public boolean isLowSwitchPressed() {
    	SmartDashboard.putBoolean("LOW LIM", !limitSwitchLow.get());
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
    
    /**
     * Gives the average elevator height, in inches, determined over time.
     * @return
     */
    public double getAverageElevatorHeight() {
    	return averageElevatorHeight;
    }
    
    /**
     * Updates the average elevator height by summing the previous 10 values and discarding the 
     * highest and lowest of them.
     */
    public void updateElevatorHeight() {
    	instantHeightValues[nDataPoints] = getInstantElevatorHeight();
    	nDataPoints++;
    	if (nDataPoints >= DATA_POINTS_PER_CALC) {
    		double sumValues = sum(instantHeightValues);
    		sumValues -= instantHeightValues[findLowestIdx(instantHeightValues)];
    		sumValues -= instantHeightValues[findHighestIdx(instantHeightValues)];
    		averageElevatorHeight = sumValues / (nDataPoints - 2);
    		nDataPoints = 0;
    	}
    }
    
    /**
     * Finds the index of the lowest value within the array.
     * @param arr The array to search
     * @return The index of the lowest value
     */
    private int findLowestIdx(double[] arr) {
    	int lowIdx = 0;
    	for (int i = 1; i < arr.length; i++)
    	{
    		if (arr[i] < arr[lowIdx])
    			lowIdx = i;
    	}
    	
    	return lowIdx;
    }
    
    /**
     * Finds the index of the highest value within the array.
     * @param arr The array to search
     * @return The index of the highest value
     */
    private int findHighestIdx(double[] arr) {
    	int highIdx = 0;
    	for (int i = 1; i < arr.length; i++)
    	{
    		if (arr[i] > arr[highIdx])
    			highIdx = i;
    	}
    	
    	return highIdx;
    }
    
    /**
     * Finds the sum of the values within the array.
     * @param arr The values 
     * @return The sum of the given values
     */
    private double sum(double[] arr) {
    	double sumArr = 0;
    	for (double d : arr)
    		sumArr += d;
    	return sumArr;
    }
    
    /**
     * Determines the current elevation of the elevator in inches.
     * @return
     */
    private double getInstantElevatorHeight() {
    	return rangeFinder.getVoltage() * INCHES_PER_VOLT + RANGE_FINDER_OFFSET;
    }
    
    private boolean nearBottom() {
    	return getAverageElevatorHeight() <= MIN_HEIGHT;
    }
    
    private boolean nearTop() {
    	return getAverageElevatorHeight() >= TOP_HEIGHT;
    }
}
