// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.Rev2mDistanceSensor;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HelperClasses.Constants.IntakeSubsystemConstants;
import frc.robot.HelperClasses.Constants.ShooterSubsystemConstants;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private VictorSPX intake1, intake2;
  private Rev2mDistanceSensor distanceSensor;
  
  public IntakeSubsystem() {
    intake1 = new VictorSPX(IntakeSubsystemConstants.IntakeMotor1ID); //1
    intake2 = new VictorSPX(IntakeSubsystemConstants.IntakeMotor2ID); //2
    intake1.setNeutralMode(NeutralMode.Brake);
    intake2.setNeutralMode(NeutralMode.Brake);
    distanceSensor = new Rev2mDistanceSensor(ShooterSubsystemConstants.distanceSensorPort);
  }

  
  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  public void IntakeUp() {
   intake1.set(ControlMode.PercentOutput, .7);
   intake2.set(ControlMode.PercentOutput, .7);  }

  public void IntakeDown() {
   intake1.set(ControlMode.PercentOutput, -.4);
   intake2.set(ControlMode.PercentOutput, -.4);
  }

  public void IntakeStop(){
   intake1.set(ControlMode.PercentOutput, 0);
   intake1.set(ControlMode.PercentOutput, 0);    
  }

  public boolean ringIn(){
    if (distanceSensor.GetRange() < 100){
      return true;
      //run IntakeUp
    } else {
      return false; 
      //run IntakeStop
    }
  }
  

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}