package org.usfirst.frc.team2655.robot;

public class RobotProperties {
	public static final double WHEEL_DIAMETER = 5; // Inches
	public static final double MIN_MOVE_POWER = 0.0; // Power needed to start moving the robot
	public static final double MID_MOVE_POWER = 0.5; // The medium power for the cubic funtion of an axis
	
	// Talon SRX config
	public static final int TALON_CL_MODE = 0; // Normal Closed loop
	public static final int TALON_TIMEOUT = 0; // Do not timeout or wait for errors
}
