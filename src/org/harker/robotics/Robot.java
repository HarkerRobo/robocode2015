
package org.harker.robotics;

import org.harker.robotics.OI;
import org.harker.robotics.commands.AutonomousCommand;
import org.harker.robotics.commands.MoveToHeightCommand;
import org.harker.robotics.commands.PersistentCommands;
import org.harker.robotics.commands.UpdateElevatorHeightCommand;
import org.harker.robotics.subsystems.Drivetrain;
import org.harker.robotics.subsystems.Manipulator;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.hal.PDPJNI;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * 
 * @author Andrew Tierno
 * @author Vedaad Shakib
 */
public class Robot extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
	PersistentCommands persistentCommands;
	CameraServer server;
	
    public void robotInit() {
    	Drivetrain.initialize();
    	Manipulator.initialize();
		OI.initialize();

		persistentCommands = new PersistentCommands();
		
		SmartDashboard.putNumber("Desired height", Manipulator.MIN_HEIGHT + 5);
		SmartDashboard.putString("Autonomous mode", "Tote");
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    	persistentCommands.start();
    	(new AutonomousCommand()).start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	persistentCommands.start();
    	
    	server = CameraServer.getInstance();
		server.startAutomaticCapture("cam2");
		server.setQuality(50);
		
		System.out.println("Camera initialized");
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
