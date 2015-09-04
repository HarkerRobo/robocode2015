package org.harker.robotics.harkerrobolib.wrappers;

import edu.wpi.first.wpilibj.Joystick;

/**
 * A Joystick wrapper for gamepads that include more accurate/useful names for the analogue stick axes
 * @author neymikajain
 * @author atierno
 */
public class GamepadWrapper extends Joystick {
	public static final int SETTING_XBOX = 0;
	public static final int SETTING_LOGITECH = 1;
	
	/************************************************
	 * XBOX SETTINGS                                *
	 ************************************************/
    public static final int XBOX_A_PORT = 1;
    public static final int XBOX_B_PORT = 2;
    public static final int XBOX_X_PORT = 3;
    public static final int XBOX_Y_PORT = 4;
    public static final int XBOX_SELECT_PORT = 7;
    public static final int XBOX_START_PORT = 8;

    public static final int XBOX_STICK_LEFT_PORT = 9;
    public static final int XBOX_STICK_RIGHT_PORT = 10;

    public static final int XBOX_BUMPER_LEFT_PORT = 5;
    public static final int XBOX_BUMPER_RIGHT_PORT = 6;
    
    public static final int XBOX_AXIS_LEFT_X = 0;
    public static final int XBOX_AXIS_LEFT_Y = 1;
    public static final int XBOX_AXIS_RIGHT_X = 4;
    public static final int XBOX_AXIS_RIGHT_Y = 5;
    public static final int XBOX_AXIS_TRIGGER_LEFT = 2;
    public static final int XBOX_AXIS_TRIGGER_RIGHT = 3;
    
    /************************************************
	 * LOGITECH SETTINGS                            *
	 ************************************************/
    public static final int LOGITECH_A_PORT = 2;
    public static final int LOGITECH_B_PORT = 3;
    public static final int LOGITECH_X_PORT = 1;
    public static final int LOGITECH_Y_PORT = 4;
    public static final int LOGITECH_SELECT_PORT = 9;
    public static final int LOGITECH_START_PORT = 10;

    public static final int LOGITECH_STICK_LEFT_PORT = 11;
    public static final int LOGITECH_STICK_RIGHT_PORT = 12;

    public static final int LOGITECH_BUMPER_LEFT_PORT = 5;
    public static final int LOGITECH_BUMPER_RIGHT_PORT = 6;
    
    public static final int LOGITECH_AXIS_LEFT_X = 0;
    public static final int LOGITECH_AXIS_LEFT_Y = 1;
    public static final int LOGITECH_AXIS_RIGHT_X = 2;
    public static final int LOGITECH_AXIS_RIGHT_Y = 3;
    public static final int LOGITECH_TRIGGER_LEFT = 7;
    public static final int LOGITECH_TRIGGER_RIGHT = 8;
    
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
    
    private int setting;
    
    public GamepadWrapper(int port) {
    	super(port);
        buttonA = new JoystickButtonWrapper(this, XBOX_A_PORT);
        buttonB = new JoystickButtonWrapper(this, XBOX_B_PORT);
        buttonX = new JoystickButtonWrapper(this, XBOX_X_PORT);
        buttonY = new JoystickButtonWrapper(this, XBOX_Y_PORT);
        buttonStart = new JoystickButtonWrapper(this, XBOX_START_PORT);
        buttonSelect = new JoystickButtonWrapper(this, XBOX_SELECT_PORT);
        buttonStickLeft = new JoystickButtonWrapper(this, XBOX_STICK_LEFT_PORT);
        buttonStickRight = new JoystickButtonWrapper(this, XBOX_STICK_RIGHT_PORT);
        buttonBumperLeft = new JoystickButtonWrapper(this, XBOX_BUMPER_LEFT_PORT);
        buttonBumperRight = new JoystickButtonWrapper(this, XBOX_BUMPER_RIGHT_PORT);
        
        this.setting = SETTING_XBOX;
    }
    
    public GamepadWrapper(int port, int setting) {
    	super(port);
    	if (setting == SETTING_XBOX) {
    		buttonA = new JoystickButtonWrapper(this, XBOX_A_PORT);
            buttonB = new JoystickButtonWrapper(this, XBOX_B_PORT);
            buttonX = new JoystickButtonWrapper(this, XBOX_X_PORT);
            buttonY = new JoystickButtonWrapper(this, XBOX_Y_PORT);
            buttonStart = new JoystickButtonWrapper(this, XBOX_START_PORT);
            buttonSelect = new JoystickButtonWrapper(this, XBOX_SELECT_PORT);
            buttonStickLeft = new JoystickButtonWrapper(this, XBOX_STICK_LEFT_PORT);
            buttonStickRight = new JoystickButtonWrapper(this, XBOX_STICK_RIGHT_PORT);
            buttonBumperLeft = new JoystickButtonWrapper(this, XBOX_BUMPER_LEFT_PORT);
            buttonBumperRight = new JoystickButtonWrapper(this, XBOX_BUMPER_RIGHT_PORT);
    	}
    	else if (setting == SETTING_LOGITECH) {
    		buttonA = new JoystickButtonWrapper(this, LOGITECH_A_PORT);
            buttonB = new JoystickButtonWrapper(this, LOGITECH_B_PORT);
            buttonX = new JoystickButtonWrapper(this, LOGITECH_X_PORT);
            buttonY = new JoystickButtonWrapper(this, LOGITECH_Y_PORT);
            buttonStart = new JoystickButtonWrapper(this, LOGITECH_START_PORT);
            buttonSelect = new JoystickButtonWrapper(this, LOGITECH_SELECT_PORT);
            buttonStickLeft = new JoystickButtonWrapper(this, LOGITECH_STICK_LEFT_PORT);
            buttonStickRight = new JoystickButtonWrapper(this, LOGITECH_STICK_RIGHT_PORT);
            buttonBumperLeft = new JoystickButtonWrapper(this, LOGITECH_BUMPER_LEFT_PORT);
            buttonBumperRight = new JoystickButtonWrapper(this, LOGITECH_BUMPER_RIGHT_PORT);
    	}
    	else {
    		throw new RuntimeException("Error, invalid setting given");
    	}
    	this.setting = setting;
    }

    public double getLeftX() {
    	return getRawAxis(XBOX_AXIS_LEFT_X);
    }

    public double getLeftY() {
    	return -getRawAxis(XBOX_AXIS_LEFT_Y); //by default, forward returns a negative number, which is unintuitive
    }

    public double getRightX() {
    	return getRawAxis(XBOX_AXIS_RIGHT_X);
    }

    public double getRightY() {
    	return -getRawAxis(XBOX_AXIS_RIGHT_Y); //by default, forward returns a negative number, which is unintuitive
    }
    
    public double getRightTrigger() {
    	if (setting == SETTING_LOGITECH) {
    		return (getRawButton(LOGITECH_TRIGGER_RIGHT) == true) ? 1 : 0;
    	}
    	return getRawAxis(XBOX_AXIS_TRIGGER_RIGHT);
    }
    
    public double getLeftTrigger() {
    	if (setting == SETTING_LOGITECH) {
    		return (getRawButton(LOGITECH_TRIGGER_LEFT) == true) ? 1 : 0;
    	}
    	return getRawAxis(XBOX_AXIS_TRIGGER_LEFT);
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
