package org.usfirst.frc.team2976.robot.subsystems;
import java.util.Timer;
import java.util.TimerTask;
/**
 * PID Controller Object
 * <p> Starts a runnable class PIDCompute that executes the PID algorithm 
 * <p> Method Signature: public PIDMain(Object pidsource,int setpoint, int sampleTime, double kp, double ki, double kd)
 *  @author NeilHazra
 */ 

public class PIDMain {
	private boolean enabled = true;	//allows the pid algorithm to stop computing
	private Timer pidtimer; //PID runs concurrently, timer class executes the algorithm uniformly
	private int sampleTime; //how quickly to sample and calculate
	private double outputMax = 1; 
	private double outputMin = -1;
	private PIDSource pidsource;
	private double output; //value to send to the motor
	
	/** The process error */
	private double error; 
	/** The input of the PIDController */
	private double input;
	private double setpoint;
	
	private double prevInput;
	
	private double proportional; //P term
	private double integral; //I term
	private double derivative;// D term
	
	private double kp, ki, kd; //tuning parameters, the hardest part of PID
	private int tolerance;
	/**
	 * 
	 * @param pidsource Object implementing PIDSource, contains method returning input 
	 * @param setpoint target value for PID controller
	 * @param sampleTime time between successive calculations
	 * @param kp proportional gain
	 * @param ki integral gain
	 * @param kd derivative gain
	 * @param tolerance maximum allowable error
	 */
	public PIDMain(Object pidsource,int setpoint, int sampleTime, double kp, double ki, double kd, int tolerance) {
		this.pidsource = (PIDSource) pidsource;
		this.setSampleTime(sampleTime);
		this.setpoint = setpoint;
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
		this.tolerance = tolerance;
		pidtimer = new Timer();//create the timer
		pidtimer.scheduleAtFixedRate(new PIDCompute(), 0, sampleTime);// set the timer
	}
	public void isEnabled(boolean enabled)	{
		this.enabled = enabled;
	}
	public double getOutput()	{
		return output;
	}
	
	public double map(double oldValue, double oldMin, double oldMax, double newMin, double newMax)	{	
		return (newMax-newMin)*(oldValue - oldMin)/(oldMax - oldMin)+newMin;
	}
	
	//For debug and tuning
	public boolean verify()	{
		if(error <= tolerance)	{
			return true;
		}
		else	{
			return false;
		}
	}
	public double getInput() {
		return input;
	}
	public double getError() {
		return error;
	}
	public double getSetpoint() {
		return setpoint;
	}
	public void setSetpoint(double setpoint) {
		this.setpoint = setpoint;
	}
	public void setOutputLimits(double min, double max)	{
		outputMax = max;
		outputMin = min;
	}
	/*
	 * No code to make use of sample time changes yet
	 */
	public int getSampleTime() {
		return sampleTime;
	}
	public void setSampleTime(int sampleTime) {
		this.sampleTime = sampleTime;
	}
	/**
	 * PID Algorithm calculates in this TimerTask created by PIDMain
	 * @author NeilHazra
	 *
	 */	
	private class PIDCompute extends TimerTask	{
		public void run()	{  
			if(!enabled) return;	
			    
				input = pidsource.getInput();
				error = input - setpoint; 
				proportional = kp *error;
				
				integral += ki *error;
					if(integral>outputMax)	integral = outputMax;
					if(integral<outputMin) integral = outputMin;
					
				derivative = kd*(input-prevInput);
				
				output = proportional + integral + derivative;
					if (output>outputMax) output = outputMax;
					if (output<outputMin) output = outputMin;
			prevInput = input;
			
		}				
	}
}
