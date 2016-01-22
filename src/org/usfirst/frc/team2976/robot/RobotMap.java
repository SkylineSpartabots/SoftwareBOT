package org.usfirst.frc.team2976.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static final int
         frontLeftMotor 	= 0, //PWM 4
         rearLeftMotor 		= 0, //PWM 2
         frontRightMotor 	= 0, //PWM 3
         rearRightMotor 	= 0, //PWM 1
         encoder1Channel1   = 8, //Right Encoder
         encoder1Channel2   = 9, 
         encoder2Channel1   = 6,
         encoder2Channel2   = 7,
         shooter1           = 4, //PWM 5
         shooter2           = 3; //PWM 5
}
