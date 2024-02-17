// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.Rev2mDistanceSensor;

//Caleb
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HelperClasses.Constants.ShooterSubsystemConstants;

public class ShooterSubsystem extends SubsystemBase {
  private VictorSPX shooterIntake1, shooterIntake2;
  private TalonFX shooter1, shooter2; 
  private Rev2mDistanceSensor distanceSensor;
  /** Creates a new ExampleSubsystem. */
  public ShooterSubsystem() {
    shooterIntake1 = new VictorSPX(ShooterSubsystemConstants.ShooterIntakeMotor1ID);  
    shooterIntake2 = new VictorSPX(ShooterSubsystemConstants.ShooterIntakeMotor2ID);  
    shooter1 = new TalonFX(ShooterSubsystemConstants.ShooterMotor1ID, ShooterSubsystemConstants.ShooterMotor1CAN);
    shooter2 = new TalonFX(ShooterSubsystemConstants.ShooterMotor2ID, ShooterSubsystemConstants.ShooterMotor2CAN);
    shooterIntake1.setNeutralMode(NeutralMode.Brake);
    shooterIntake2.setNeutralMode(NeutralMode.Brake);
    shooter1.setNeutralMode(NeutralModeValue.Brake);
    shooter2.setNeutralMode(NeutralModeValue.Brake);
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

  public void shootForward(){
    shooterIntake1.set(VictorSPXControlMode.PercentOutput, -1);
    shooterIntake2.set(VictorSPXControlMode.PercentOutput, -.75);
    }

  public void shootBack(){
    shooter1.set(-.7);
    shooter2.set(-.7);  
    shooterIntake1.set(VictorSPXControlMode.PercentOutput, .5);
    shooterIntake2.set(VictorSPXControlMode.PercentOutput, .375);
  }

  public void warmUp(){
    shooter1.set(1);
    shooter2.set(1);
  }

  public void warmDown(){
    //a memorial
  }

  public void shootStop(){
    shooterIntake1.set(VictorSPXControlMode.PercentOutput, 0);
    shooterIntake2.set(VictorSPXControlMode.PercentOutput, 0);
    shooter1.set(0);
    shooter2.set(0);
  }

  public void getPosition(){
    //use limelight to find our position
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
