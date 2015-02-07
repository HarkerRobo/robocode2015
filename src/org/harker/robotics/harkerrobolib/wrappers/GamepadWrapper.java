package org.harker.robotics.harkerrobolib.wrappers;

import edu.wpi.first.wpilibj.Joystick;

/**
 * A Joystick wrapper for gamepads that include more accurate/useful names for the analogue stick axes
 * @author neymikajain
 * @author atierno
 */
public class GamepadWrapper extends Joystick {
    private final JoystickButtonWrapper buttonA;
    private final JoystickButtonWrapper buttonB;
    private final JoystickButtonWrapper buttonX;
    private final JoystickButtonWrapper buttonY;
    private final JoystickButtonWrapper buttonStart;
    private final JoystickButtonWrapper buttonSelect;
    private final JoystickButtonWrapper buttonStickLeft;
    private final JoystickButtonWrapper buttonStickRight;
    private final JoystickButtonWrapper buttonBumperLeft;
    private final JoystickButtonWrapper buttonBumperRight;
    
    public static final int BUTTON_A_PORT = 2;
    public static final int BUTTON_B_PORT = 3;
    public static final int BUTTON_X_PORT = 1;
    public static final int BUTTON_Y_PORT = 4;
    public static final int BUTTON_SELECT_PORT = 9;
    public static final int BUTTON_START_PORT = 10;

    public static final int BUTTON_STICK_LEFT_PORT = 11;
    public static final int BUTTON_STICK_RIGHT_PORT = 12;

    public static final int BUTTON_BUMPER_LEFT_PORT = 5;
    public static final int BUTTON_BUMPER_RIGHT_PORT = 6;

    public static final int BUTTON_TRIGGER_LEFT_AXIS = 7;
    public static final int BUTTON_TRIGGER_RIGHT_PORT = 8;

    public static final int AXIS_LEFT_X = 0;
    public static final int AXIS_LEFT_Y = 1;
    public static final int AXIS_RIGHT_X = 4;
    public static final int AXIS_RIGHT_Y = 5;
    public static final int AXIS_TRIGGER_LEFT = 2;
    public static final int AXIS_TRIGGER_RIGHT = 3;
    
    public GamepadWrapper(int port) {
    	super(port);
        buttonA = new JoystickButtonWrapper(this, BUTTON_A_PORT);
        buttonB = new JoystickButtonWrapper(this, BUTTON_B_PORT);
        buttonX = new JoystickButtonWrapper(this, BUTTON_X_PORT);
        buttonY = new JoystickButtonWrapper(this, BUTTON_Y_PORT);
        buttonStart = new JoystickButtonWrapper(this, BUTTON_START_PORT);
        buttonSelect = new JoystickButtonWrapper(this, BUTTON_SELECT_PORT);
        buttonStickLeft = new JoystickButtonWrapper(this, BUTTON_STICK_LEFT_PORT);
        buttonStickRight = new JoystickButtonWrapper(this, BUTTON_STICK_RIGHT_PORT);
        buttonBumperLeft = new JoystickButtonWrapper(this, BUTTON_BUMPER_LEFT_PORT);
        buttonBumperRight = new JoystickButtonWrapper(this, BUTTON_BUMPER_RIGHT_PORT);
    }

    /**
     * Gets the x-axis magnitude of the left joystick. 
     * @return The x-value of the left joystick.
     */
    public double getLeftX() {
    	return getRawAxis(AXIS_LEFT_X);
    }

    /**
     * Gets the y-axis magnitude of the left joystick. 
     * @return The y-value of the left joystick.
     */
    public double getLeftY() {
    	return -getRawAxis(AXIS_LEFT_Y); //by default, forward returns a negative number, which is unintuitive
    }

    /**
     * Gets the x-axis magnitude of the right joystick. 
     * @return The x-value of the right joystick.
     */
    public double getRightX() {
    	return getRawAxis(AXIS_RIGHT_X);
    }

    /**
     * Gets the y-axis magnitude of the right joystick. 
     * @return The x-value of the right joystick.
     */
    public double getRightY() {
    	return -getRawAxis(AXIS_RIGHT_Y); //by default, forward returns a negative number, which is unintuitive
    }
    
    /**
     * Gets the magnitude of the left trigger. This will be a value from 0 to 1
     * where 1 is fully depressed. 
     * @return The magnitude of the left trigger
     */
    public double getLeftTrigger() {
    	return getRawAxis(AXIS_TRIGGER_LEFT);
    }
    
    /**
     * Gets the magnitude of the right trigger. This will be a value from 0 to 1
     * where 1 is fully depressed. 
     * @return The magnitude of the right trigger
     */
    public double getRightTrigger() {
    	return getRawAxis(AXIS_TRIGGER_RIGHT);
    }
    
    /**
     * Gets whether or not Button A is pressed
     * @return The state of the button
     */
    public boolean getButtonAState() {
        return buttonA.get();
    }
    
    /**
     * Gets whether or not Button B is pressed
     * @return The state of the button
     */
    public boolean getButtonBState() {
        return buttonB.get();
    }
    
    /**
     * Gets whether or not Button X is pressed
     * @return The state of the button
     */
    public boolean getButtonXState() {
        return buttonX.get();
    }
    
    /**
     * Gets whether or not Button Y is pressed
     * @return The state of the button
     */
    public boolean getButtonYState() {
        return buttonY.get();
    }
    
    /**
     * Gets whether or not the Start Button is pressed
     * @return The state of the button
     */
    public boolean getButtonStartState() {
        return buttonStart.get();
    }
    
    /**
     * Gets whether or not the Select Button is pressed
     * @return The state of the button
     */
    public boolean getButtonSelectState() {
        return buttonSelect.get();
    }
    
    /**
     * Gets whether or not the Left Stick Button is pressed
     * @return The state of the button
     */
    public boolean getButtonStickLeftState() {
        return buttonStickLeft.get();
    }
    
    /**
     * Gets whether or not the Right Stick Button is pressed
     * @return The state of the button
     */
    public boolean getButtonStickRightState() {
        return buttonStickRight.get();
    }
    
    /**
     * Gets whether or not the Left Bumper is pressed
     * @return The state of the button
     */
    public boolean getButtonBumperLeftState() {
        return buttonBumperLeft.get();
    }
    
    /**
     * Gets whether or not the Right Bumper is pressed
     * @return The state of the button
     */
    public boolean getButtonBumperRightState() {
        return buttonBumperRight.get();
    }
    
    /**
     * Gets an instance of Button A
     * @return An instance of the button
     */
    public JoystickButtonWrapper getButtonA() {
        return buttonA;
    }
    
    /**
     * Gets an instance of Button B
     * @return An instance of the button
     */
    public JoystickButtonWrapper getButtonB() {
        return buttonB;
    }
    
    /**
     * Gets an instance of Button X
     * @return An instance of the button
     */
    public JoystickButtonWrapper getButtonX() {
        return buttonX;
    }
    
    /**
     * Gets an instance of Button Y
     * @return An instance of the button
     */
    public JoystickButtonWrapper getButtonY() {
        return buttonY;
    }
    
    /**
     * Gets an instance of the Start Button
     * @return An instance of the button
     */
    public JoystickButtonWrapper getButtonStart() {
        return buttonStart;
    }
    
    /**
     * Gets an instance of the Select Button
     * @return An instance of the button
     */
    public JoystickButtonWrapper getButtonSelect() {
        return buttonSelect;
    }
    
    /**
     * Gets an instance of the Left Stick Button
     * @return An instance of the button
     */
    public JoystickButtonWrapper getButtonStickLeft() {
        return buttonStickLeft;
    }
    
    /**
     * Gets an instance of the Right Stick Button
     * @return An instance of the button
     */
    public JoystickButtonWrapper getButtonStickRight() {
        return buttonStickRight;
    }
    
    /**
     * Gets an instance of the Left Bumper
     * @return An instance of the button
     */
    public JoystickButtonWrapper getButtonBumperLeft() {
        return buttonBumperLeft;
    }
    
    /**
     * Gets an instance of the Right Bumper
     * @return An instance of the button
     */
    public JoystickButtonWrapper getButtonBumperRight() {
        return buttonBumperRight;
    }
}
