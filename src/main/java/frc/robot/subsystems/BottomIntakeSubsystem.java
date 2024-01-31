// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix6.hardware.TalonFX;

//fritz
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HelperClasses.Constants.BottomIntakeSubsystemConstants;


public class BottomIntakeSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
      private TalonFX bottomIntake1, bottomIntake2;
  public BottomIntakeSubsystem() {
    bottomIntake1 = new TalonFX(BottomIntakeSubsystemConstants.BottomIntakeMotor1ID, BottomIntakeSubsystemConstants.BottomIntakeMotor1Can); //2
    bottomIntake2 = new TalonFX(BottomIntakeSubsystemConstants.BottomIntakeMotor2ID, BottomIntakeSubsystemConstants.BottomIntakeMotor2Can); //3
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
    bottomIntake1.set(.7);
    bottomIntake2.set(.7);
  }

  public void IntakeDown() {
    bottomIntake1.set(-.4);
    bottomIntake2.set(-.4);
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
