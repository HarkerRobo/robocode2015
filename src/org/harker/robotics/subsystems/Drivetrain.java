package org.harker.robotics.subsystems;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

import org.harker.robotics.commands.ManualDriveCommand;
import org.harker.robotics.harkerrobolib.wrappers.EncoderWrapper;
import org.harker.robotics.harkerrobolib.wrappers.TalonWrapper;
import org.harker.robotics.OI;
import org.harker.robotics.RobotMap;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The Drivetrain, which contains accessors for the Talons along the base as well as 
 * the gyroscope and encoders. 
 * 
 * @author Andrew Tierno
 * @author Manan Shah
 * @author Vedaad Shakib
 * @author Neymika
 */

public class Drivetrain extends PIDSubsystem {
	
	//Drive related components
	private static RobotDrive robotDrive;
	private static TalonWrapper leftBack, leftFront, rightBack, rightFront;
	private static Gyro gyro;
	
	//Deadzone constants
	private static double DZ_Y = 0.15;
	private static double DZ_T = 0.20;
	private static double DZ_X = 0.15;
	
	//Theta scale because we need to ensure we don't move theta too fast
	private static double T_SCALE = 0.8;
	private static double X_SCALE = 1;
	private static double Y_SCALE = 1;
	
	//Relative driving boolean
	private static boolean isRelative = false;
	
	// The static instance variable Drivetrain
	private static Drivetrain drivetrain;
	
	//Constants for acceleration
	private static double MAX_ACCEL_X = 0.1;
	private static double MAX_ACCEL_Y = 0.1;
	private static double MAX_ACCEL_T = 0.1;
	
	//PID Constants
	private static final double P = 3.6;
	private static final double I = 0.0;
	private static final double D = 0.0;
	//The time between calculations in seconds
	private static final double PERIOD = 1.0;
	
	//A reference to previous speeds to use for acceleration
	private double prevX;
	private double prevY;
	private double prevT;
	private double prevE;
	
	//A reference to the current setpoint values
	private double targetX;
	private double targetY;
	private double targetT;
	
	//Calibration factor for the gyro
	private double voltsPerDegreePerSecond = (12.5e-3);
	
	//The maximum rotational speed (corresponds to '1')
	public final double MAX_ROTATIONAL_RATE = 0.20;
	
	/**
	 * Drivetrain singleton constructor. Initializes the various components 
	 * of the robot along with the internal RobotDrive handler. Also initializes
	 * the internal PID loop.
	 */
	private Drivetrain() {
		super(P, I, D, PERIOD);
		leftBack = new TalonWrapper(RobotMap.Drivetrain.LEFT_FRONT_TALON_PORT);
		rightBack = new TalonWrapper(RobotMap.Drivetrain.LEFT_BACK_TALON_PORT);
		leftFront = new TalonWrapper(RobotMap.Drivetrain.RIGHT_FRONT_TALON_PORT);
		rightFront = new TalonWrapper(RobotMap.Drivetrain.RIGHT_BACK_TALON_PORT);
		
		gyro = new Gyro(RobotMap.Drivetrain.GYRO_PORT);
		gyro.setSensitivity(voltsPerDegreePerSecond);
		gyro.reset();
		
		
		robotDrive = new RobotDrive(leftBack, rightBack, leftFront, rightFront);
		robotDrive.setSafetyEnabled(false);
		robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
		robotDrive.setInvertedMotor(MotorType.kRearRight, true);
		
		targetX = targetY = targetT = 0; 
		prevX = prevY = prevT = 0;
		this.enable();
	}
	
	/**
	 * Sets the default command to which the subsystem reverts when 
	 * nothing else is being called. For the Drivetrain this is the 
	 * Manual Drive Command. 
	 */
    public void initDefaultCommand() {
    	setDefaultCommand(new ManualDriveCommand());
    }
    
    /**
     * Initializes the Drivetrain singleton if it has not yet previously
     * done so. 
     */
    public static void initialize() {
    	if (drivetrain == null)
    		drivetrain = new Drivetrain();
    }
	
	/**
	 * Gets an instance of the Drivetrain (needed for singleton)
	 * @return An instance of the drivetrain
	 */
	public static Drivetrain getInstance() {
		if (drivetrain == null) drivetrain = new Drivetrain();
		return drivetrain;
	}
	
	/**
	 * Drives all four motors at the given speed.
	 * @param speed The speed at which to drive all four motors
	 */
	public void driveRaw(double speed) {
		leftBack.set(speed);
		leftFront.set(speed);
		rightBack.set(speed);
		rightFront.set(speed);
	}
	
	/**
	 * Sets the robot to the given target values using a Cartesian style mecanum drive with the 
	 * supplied x, y, and rotational velocities. 
	 * @param sx The x-velocity
	 * @param sy The y-velocity
	 * @param rotation The rotational velocity
	 */
	public void drive(double sx, double sy, double rotation) {
		//Applying deadzone
		targetX = (Math.abs(sx) > DZ_X) ? -sx : 0; 
		targetY = (Math.abs(sy) > DZ_Y) ? sy : 0;
		targetT = (Math.abs(rotation) > DZ_T) ? -rotation * T_SCALE : 0;
	}
	
	/**
	 * Updates the speed at which the drivetrain moves, applying acceleration and updating 
	 * the previous values for acceleration.
	 * @param thetaOffset The output calculated by the PID Controller which is added to 
	 * 						the current rotational velocity
	 */
	public void updateDrive(double thetaOffset) {
		double vX = targetX;
		double vY = targetY;
		double vT = targetT + thetaOffset;
		double heading = (isRelative) ? getCurrentAbsoluteHeading() : 0;
		
		//Apply accelerations
		if (Math.abs(targetX - prevX) > MAX_ACCEL_X)
			vX = prevX + Math.signum(vX - prevX) * MAX_ACCEL_X;
		if (Math.abs(vY - prevY) > MAX_ACCEL_Y)
			vY = prevY + Math.signum(vY - prevY) * MAX_ACCEL_Y;
		if (Math.abs(vT - prevT) > MAX_ACCEL_T)
			vT = prevT + Math.signum(vT - prevT) * MAX_ACCEL_T;
		
		//Update old values
		prevX = vX;
		prevY = vY;
		prevT = vT;
		
		robotDrive.mecanumDrive_Cartesian(vX, vY, vT, heading);
	}
	
	/**
	 * Determines the rate at which the robot is currently spinning in degrees
	 * per second. 
	 * @return The rotational rate in degrees per second
	 */
	public double getRotationalRate() {
		return (-gyro.getRate());
//		return (Math.abs(gyro.getRate()) < 0.05) ? 0 : -gyro.getRate();
	}
	
	/**
	 * Finds the current heading of the robot relative to its initial position. The angle returned
	 * is continuous - that is, it goes from 0 -> 360 -> 720 -> ...
	 * @return The angle which the robot is facing relative to the robot's original position
	 */
	public double getCurrentContinuousHeading() {
		return gyro.getAngle();
	}
	
	/**
	 * Returns the current heading of the robot as an absolute value ranging from 0 -> 360.
	 * @return The angle which the robot is facing mod 360
	 */
	public double getCurrentAbsoluteHeading() {
		return gyro.getAngle() % 360;
	}
	
	/**
	 * Resets the value of the gyro such that the robot's current heading is zero.
	 */
	public void resetGyro() {
		gyro.reset();
	}
	
	/**
	 * Sets whether or not driving should be relative to the player or
	 * absolute to the field. 
	 * @param flag
	 */
	public void setRelative(boolean flag) {
		isRelative = flag;
	}
	
	/**
	 * Toggles whether or not relative driving should be used. 
	 */
	public void toggleRelative() {
		isRelative = !isRelative;
	}

	/**
	 * Calculates the error term for the PID Controller as the difference between 
	 * target rotational speed and the true rotational speed.
	 */
	protected double returnPIDInput() {
		double actualRate = getRotationalRate() / MAX_ROTATIONAL_RATE;
		if (Math.abs(actualRate) > 1) actualRate = Math.signum(actualRate);
		double error = (targetT - actualRate);
		return error;
	}

	/**
	 * Uses the offset calculated by the PID Controller in the update drive method
	 * to affect the speed of the RobotDrive. 
	 */
	protected void usePIDOutput(double output) {
		updateDrive(output);
	}
}

