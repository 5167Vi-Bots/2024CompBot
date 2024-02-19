// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

//Erin
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HelperClasses.Constants.ArmsSubsystemConstants;

public class ArmsSubsystem extends SubsystemBase { 
  private TalonSRX rightArm, leftArm;
  /** Creates a new ExampleSubsystem. */
  public ArmsSubsystem() {
rightArm = new TalonSRX(ArmsSubsystemConstants.RightArmMotorID);
leftArm = new TalonSRX(ArmsSubsystemConstants.LeftArmMotorID); 
}

public void armsStop(){
   leftArm.set(ControlMode.PercentOutput, 0);
   rightArm.set(ControlMode.PercentOutput, 0);
  }

  public void armsUp(){
    rightArm.set(ControlMode.PercentOutput, .5);
    leftArm.set(ControlMode.PercentOutput, .5);
  }
  
  public void armsDown(){
    rightArm.set(ControlMode.PercentOutput, -.5);
    leftArm.set(ControlMode.PercentOutput, -.5);
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

  public void ExtendArms()
  {
    //may not need to exist
  }

  public void RetractArms()
  {
    //may not need to exist
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
