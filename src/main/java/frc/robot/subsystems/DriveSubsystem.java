// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//CTR
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HelperClasses.Constants;
import frc.robot.HelperClasses.Constants.DriveSubsystemConstants;

import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;

import com.ctre.phoenix.sensors.PigeonIMU; //pigeon3
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule;

public class DriveSubsystem extends SubsystemBase {
  public SwerveDriveOdometry swerveOdometry;
  public SwerveModule[] mSwerveMods;
  public PigeonIMU Pigeon;
  public DriveSubsystem() {
        Pigeon = new PigeonIMU(DriveSubsystemConstants.PigeonID);
        Pigeon.setYaw(0);

       /* mSwerveMods = new SwerveModule[]{
            new SwerveModule(0, DriveSubsystemConstants.Mod0.constants),
            new SwerveModule(1, DriveSubsystemConstants.Mod1.constants),
            new SwerveModule(2, DriveSubsystemConstants.Mod2.constants),
            new SwerveModule(3, DriveSubsystemConstants.Mod3.constants)
        }; */
  }

  public void driveTrain(){
    //basic controller input
  }
  
  public void driveDistance(double distance) {
    //use encoders or sum on the drive train B)
  }
  
  public void fieldCentric(){
    //switch to a field-centric drive (auto)
  }

  public void robotCentric(){
    //switch to a robot-centric drive (teleop)
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
  public double getPitch() {
    return Pigeon.getPitch();
  }

  public double getRoll() {
    return Pigeon.getRoll();
  }

  public double getYaw() {
    return Pigeon.getYaw();
  }



  @Override
  public void periodic() {
    SmartDashboard.putNumber("Pitch", getPitch());
    SmartDashboard.putNumber("Roll", getRoll());
    SmartDashboard.putNumber("Yaw", getYaw());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }


}
