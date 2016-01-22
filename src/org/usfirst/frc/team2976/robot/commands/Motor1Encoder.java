package org.usfirst.frc.team2976.robot.commands;
import org.usfirst.frc.team2976.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
/**
 *@author NeilHazra 
 */
public class Motor1Encoder {
    public Encoder Motor1Encoder = new Encoder(RobotMap.encoder1Channel1, RobotMap.encoder1Channel2);	
    double motor1Rate;
    
    public double getMotor1Speed()	{    	
    	motor1Rate = Motor1Encoder.getRate();
    	return motor1Rate;	
    }
}

