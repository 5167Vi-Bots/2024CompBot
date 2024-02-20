// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

// public final class LineUp extends ParallelCommandGroup{
//   public LineUp(DriveSubsystem driveSub, ShooterSubsystem shootSub, LimelightSubsystem limeSub) {
//   addCommands(
//   shootSub.getPosition(),
//   driveSub.driveDistance(limeSub.getV()) //this will probably kill us all i dont know what im doing
//   );
//   }

//   /*private void Autos() {
//     throw new UnsupportedOperationException("This is a utility class!");
//   }*/
//   //idk what this is
// }