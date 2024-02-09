// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// devyn
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HelperClasses.Constants.TopIntakeSubsystemConstants;

public class TopIntakeSubsystem extends SubsystemBase {

  private TalonFX topIntake1, topIntake2;
  private Rev2mDistanceSensor distanceSensor; 

  public TopIntakeSubsystem() {
    topIntake1 = new TalonFX(TopIntakeSubsystemConstants.TopIntakeMotor1ID, TopIntakeSubsystemConstants.TopIntakeMotor1Can);
    topIntake2 = new TalonFX(TopIntakeSubsystemConstants.TopIntakeMotor2ID, TopIntakeSubsystemConstants.TopIntakeMotor2Can);
    topIntake1.setNeutralMode(NeutralModeValue.Brake);
    topIntake2.setNeutralMode(NeutralModeValue.Brake);
    distanceSensor = new Rev2mDistanceSensor(TopIntakeSubsystemConstants.distanceSensorPort);
    
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
      topIntake1.set(.8);
      topIntake2.set(.8);
    }

    public void IntakeDown() {
      topIntake1.set(.5);
      topIntake2.set(.5);
    }

    public void IntakeStop(){
      topIntake1.set(0);
      topIntake2.set(0);
    }

    public double getDistance(){
      return distanceSensor.GetRange();
    }

    //TODO test and correct value
    public boolean ringIn(){
      if (distanceSensor.GetRange() < 100){
        return true; 
      } else {
        return false; 
      }
    } //bismillahhhhhhhhhhh

  /**
   * 
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

  public void setSpeed(double speed){
  
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
