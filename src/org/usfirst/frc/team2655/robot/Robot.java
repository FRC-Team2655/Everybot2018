/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2655.robot;

import org.usfirst.frc.team2655.robot.controllers.IController;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
		
	// Drive stuff
	Talon right = new Talon(0);
	Talon left = new Talon(1);
	Talon bucket = new Talon(2);
	DifferentialDrive robotDrive = new DifferentialDrive(left, right);
	
	// Controller Selector
	public static SendableChooser<IController> controllerSelect = new SendableChooser<IController>();
	
	@Override
	public void robotInit() {
		
		// Setup controllers
		for(IController c : OI.controllers) {
			controllerSelect.addObject(c.getName(), c);
		}
		controllerSelect.addDefault(OI.controllers.get(0).getName(), OI.controllers.get(0));
		OI.selectController(OI.controllers.get(0));
		
		left.setInverted(true);
		right.setInverted(true);
		SmartDashboard.putBoolean(Values.DRIVE_CUBIC, true);
		SmartDashboard.putBoolean(Values.ROTATE_CUBIC, false);	
		SmartDashboard.putData(Values.CONTROLLER_SELECT, controllerSelect);
	}

	
	
	@Override
	public void robotPeriodic() {
		super.robotPeriodic();
		// Update controller
		IController selected = controllerSelect.getSelected();
		if(selected != OI.selectedController) {
			OI.selectController(selected);
		}
	}

	@Override
	public void teleopPeriodic() {
		boolean driveCubic = SmartDashboard.getBoolean(Values.DRIVE_CUBIC, true);
		boolean rotateCubic = SmartDashboard.getBoolean(Values.ROTATE_CUBIC, true);
		
		double power =  driveCubic ? OI.driveAxis.getValue() : OI.driveAxis.getValueLinear();
		double rotation = -1 * (rotateCubic ? OI.rotateAxis.getValue() : OI.rotateAxis.getValueLinear());
		
		// Square keeping sign to reduce sensitivity
		rotation *= .75;
		
		robotDrive.arcadeDrive(power, rotation);
				
		double bucketSpeed = 0;
		if(OI.bucketDpad.getDirection() == 0)
			bucketSpeed = 0.85;
		else if(OI.bucketDpad.getDirection() == 180)
			bucketSpeed = -0.45;
		
		bucket.set(bucketSpeed);
	}
}
