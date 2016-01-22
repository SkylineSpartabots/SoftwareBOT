package org.usfirst.frc.team2976.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2976.robot.OI;
import org.usfirst.frc.team2976.robot.subsystems.Shooter;
import org.usfirst.frc.team2976.robot.subsystems.Motor1PIDSource;
import org.usfirst.frc.team2976.robot.subsystems.Motor2PIDSource;

import org.usfirst.frc.team2976.robot.subsystems.PIDMain;
/**
 *
 */
public class Shoot extends Command {
	public Shooter shooter = new Shooter(); //Motor Subsystem
	
	public Motor1PIDSource motor1pidsource = new Motor1PIDSource(); //Implements PIDSource 
	public Motor2PIDSource motor2pidsource = new Motor2PIDSource(); //Implements PIDSource 
	
	long motorInertiaFlag; //Do not allow the user to shoot before the motor is at correct speed
	
	public boolean action = false; //toggle code logic
	final int BUTTON_PAUSE = 500; //Prevent successive toggles 	
	public long prevTime = 0; //Used to calculate time change
	
	/** Proportional gain */
	double kp = 0.0004; 
	/**Integral Gain */
	double ki = 0.0002; 
	/**Derivative Gain*/
	double kd = 0.00; 
	
	int motor1_setpoint = -10000;
	int motor2_setpoint = 10000;
	
	final int sampleTime = 100;
	final int tolerance = 5;
	
	public PIDMain shooterMotor1 = new PIDMain(motor1pidsource,motor1_setpoint,sampleTime,kp,ki,kd,tolerance); 
	//PID controller for Motor 1
	public PIDMain shooterMotor2 = new PIDMain(motor2pidsource,motor2_setpoint,sampleTime,kp,ki,kd,tolerance); 
	//PID controller for Motor 2
	public Shoot() {
    	requires(shooter);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    }
    // Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		SmartDashboard.putNumber("Motor1", shooterMotor1.getInput());
		SmartDashboard.putNumber("PIDOutput1",shooterMotor1.getOutput());
		shooter.shooter1.set(shooterMotor1.getOutput());
		SmartDashboard.putNumber("Motor2", shooterMotor2.getInput());
		SmartDashboard.putNumber("PIDOutput2",shooterMotor2.getOutput());
		shooter.shooter2.set(shooterMotor2.getOutput());
		//shooter.shooter2.set(.1);
		
		//SmartDashboard.putNumber("Motor2", shooterMotor2.getInput());
		/*
		if (OI.LeftJoyStick.getRawButton(OI.Button.X.getBtnNumber()) && (System.currentTimeMillis() - prevTime) > BUTTON_PAUSE) {
			action = !action;
			prevTime = System.currentTimeMillis();
		}
		if (action) {
			SmartDashboard.putBoolean("Action", true);

			shooterMotor1.isEnabled(true);
			shooterMotor2.isEnabled(true);   
			//shooter.shooter1.set(shooterMotor1.getOutput());
			//shooter.shooter2.set(shooterMotor2.getOutput());
			if(shooterMotor1.verify() && shooterMotor2.verify())	{
				//Shoot
				SmartDashboard.putBoolean("Is Ready?", true);
			} else	{
				SmartDashboard.putBoolean("Is Ready?", false);
			}
		} else {
			//Disable PID controller and turn motors off
			shooterMotor1.isEnabled(false);
			shooterMotor2.isEnabled(false);
			//shooter.shooter1.set(0);
			//shooter.shooter2.set(0);
		}
		*/
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
    // Called once after isFinished returns true
    protected void end() {
		/*
    	shooterMotor1.isEnabled(false);
		shooterMotor2.isEnabled(false);
    	*/
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {  	
    }
}
