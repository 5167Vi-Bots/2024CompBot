// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.util.Color;
//Aiden
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HelperClasses.Constants;

import com.ctre.phoenix.led.*;



public class LightsSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public LightsSubsystem() {
    candle = new CANdle(Constants.LightsSubsystemConstants.CandleLightID, Constants.LightsSubsystemConstants.CandleLightCan);
  }
    private CANdle candle;
    private int toInt (double input) {
      return (int)input * 255;
    }
    public void setOrange()
    {
      //double current = DriverStation.getMatchTime();
      //DriverStation.getAlliance();
      candle.setLEDs(toInt(Constants.LightsSubsystemConstants.Orange.red), toInt(Constants.LightsSubsystemConstants.Orange.green), toInt(Constants.LightsSubsystemConstants.Orange.blue));
    }


        public void setColor(Color InColor)
    {
      //double current = DriverStation.getMatchTime();
      //DriverStation.getAlliance();
      candle.setLEDs(toInt(InColor.red), toInt(InColor.green), toInt(InColor.blue));
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
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
