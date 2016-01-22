package org.usfirst.frc.team2976.robot.subsystems;
import org.usfirst.frc.team2976.robot.commands.Motor1Encoder;

public class Motor1PIDSource implements PIDSource {
	
	Motor1Encoder motor1encoder = new Motor1Encoder();
	
	public Motor1PIDSource() {
		// TODO Auto-generated constructor stub
		motor1encoder.Motor1Encoder.reset();
	}

	@Override
	public double getInput() {
		// TODO Auto-generated method stub
		return motor1encoder.getMotor1Speed();
	}
}
