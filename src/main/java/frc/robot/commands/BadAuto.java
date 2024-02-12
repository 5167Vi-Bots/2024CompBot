// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public final class BadAuto extends SequentialCommandGroup{
  public BadAuto(DriveSubsystem driveSub, IntakeSubsystem intakeSub, ShooterSubsystem shootSub) {
  addCommands(
  //put commands here
  //pathplanner should exist at some point
  //drive forward
  //run intake
  //turn round
  //shoot
  //more?
  );
  }

  private void Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
