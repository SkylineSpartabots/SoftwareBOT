package org.usfirst.frc.team2976.robot.commands;
import org.usfirst.frc.team2976.robot.RobotMap;
import edu.wpi.first.wpilibj.Encoder;
/**
 *@author NeilHazra
 */
public class Motor2Encoder {
	public Encoder Motor2Encoder = new Encoder(RobotMap.encoder2Channel1, RobotMap.encoder2Channel2);
	public double motor2Rate;

	public double getMotor2Speed() {
		motor2Rate = Motor2Encoder.getRate();
		return motor2Rate;
	}
}
