// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.Rev2mDistanceSensor;

//fritz
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HelperClasses.Constants.BottomIntakeSubsystemConstants;
import frc.robot.HelperClasses.Constants.TopIntakeSubsystemConstants;

public class BottomIntakeSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private VictorSPX bottomIntake1, bottomIntake2;
  private Rev2mDistanceSensor distanceSensor;
  
  public BottomIntakeSubsystem() {
    bottomIntake1 = new VictorSPX(BottomIntakeSubsystemConstants.BottomIntakeMotor1ID); //1
    bottomIntake2 = new VictorSPX(BottomIntakeSubsystemConstants.BottomIntakeMotor2ID); //2
    bottomIntake1.setNeutralMode(NeutralMode.Brake);
    bottomIntake2.setNeutralMode(NeutralMode.Brake);
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
   bottomIntake1.set(ControlMode.PercentOutput, .7);
   bottomIntake2.set(ControlMode.PercentOutput, .7);  }

  public void IntakeDown() {
   bottomIntake1.set(ControlMode.PercentOutput, -.4);
   bottomIntake2.set(ControlMode.PercentOutput, -.4);
  }

  public void IntakeStop(){
   bottomIntake1.set(ControlMode.PercentOutput, 0);
   bottomIntake1.set(ControlMode.PercentOutput, 0);    
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
