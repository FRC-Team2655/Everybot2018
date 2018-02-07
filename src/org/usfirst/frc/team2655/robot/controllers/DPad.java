package org.usfirst.frc.team2655.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;

public class DPad {
	private Joystick joystick;
	private int dpad;
	
	public DPad(Joystick joystick, int dpad) {
		this.joystick = joystick;
		this.dpad = dpad;
	}
	
	public int getDirection() {
		return joystick.getPOV(dpad);
	}
}
