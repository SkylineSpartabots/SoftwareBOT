package org.usfirst.frc.team2976.robot.subsystems;
import org.usfirst.frc.team2976.robot.commands.Motor2Encoder;

public class Motor2PIDSource implements PIDSource {

	Motor2Encoder motor2encoder = new Motor2Encoder();
	
	public Motor2PIDSource() {
		// TODO Auto-generated constructor stub
		motor2encoder.Motor2Encoder.reset();
	}

	@Override
	public double getInput() {
		// TODO Auto-generated method stub
		return motor2encoder.getMotor2Speed();
	}

}
