// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//Erin
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HelperClasses.Constants.ArmsSubsystemConstants;

public class ArmsSubsystem extends SubsystemBase { 
  private TalonFX rightArm, leftArm;
  private double leftBottom, rightBottom, leftTop, rightTop;
  /** Creates a new ExampleSubsystem. */
  public ArmsSubsystem() {
  rightArm = new TalonFX(ArmsSubsystemConstants.RightArmMotorID);
  leftArm = new TalonFX(ArmsSubsystemConstants.LeftArmMotorID); 
  //reset the encoders to 0 on initialization
  leftBottom = -125; //wherever it ends up
  rightBottom = 125;
  leftTop = 0;
  rightTop = 0;
  }

public void armsStop(){
   leftArm.setControl(new DutyCycleOut(0)); //lock it in to where is currently is??
   rightArm.setControl(new DutyCycleOut(0));
  }

  public void armsUp(){
    rightArm.setControl(new PositionDutyCycle(rightTop)); //make it negative?
    leftArm.setControl(new PositionDutyCycle(leftTop));
  }
  
  public void armsDown(){
    rightArm.setControl(new PositionDutyCycle(rightBottom));
    leftArm.setControl(new PositionDutyCycle(leftBottom)); //make it negative?
  }

  public void leftArmDown()
  {
    leftArm.setControl(new DutyCycleOut(.5));
  }
    public void rightArmDown()
  {
    rightArm.setControl(new DutyCycleOut(.5));
  }

  public void leftStop()
  {
        leftArm.setControl(new DutyCycleOut(0));
  }
    public void rightStop()
  {
        rightArm.setControl(new DutyCycleOut(0));

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
    SmartDashboard.putNumber("RightArm", rightArm.getPosition().getValueAsDouble());
    ;    SmartDashboard.putNumber("LeftArm", leftArm.getPosition().getValueAsDouble());

  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
