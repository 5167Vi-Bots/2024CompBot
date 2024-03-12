// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.HelperClasses.Constants.LightsSubsystemConstants;
import frc.robot.subsystems.LightsSubsystem;

import java.sql.Driver;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class AllianceLightCommand extends BaseLightCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  public Color GetColor()
  {
        Color ColorToUse; 
    var Alliance = DriverStation.getAlliance();
    if (Alliance.isPresent())
      if (Alliance.get() == DriverStation.Alliance.Red)
        ColorToUse = Color.kRed;
      else
        ColorToUse = Color.kBlue;
    else
      ColorToUse = super.GetColor();

    return ColorToUse;
  }
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AllianceLightCommand(LightsSubsystem subsystem) {
    super(subsystem);
  }


}
