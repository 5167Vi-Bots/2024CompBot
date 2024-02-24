// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.io.FileReader;
import java.util.function.Supplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix6.Utils;
import com.ctre.phoenix6.mechanisms.swerve.SwerveDrivetrain;
import com.ctre.phoenix6.mechanisms.swerve.SwerveDrivetrainConstants;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.ReplanningConfig;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstants;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HelperClasses.Constants;

import frc.robot.generated.TunerConstants;

public class DriveSubsystem extends SwerveDrivetrain implements Subsystem {


  

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
    return 0;//Pigeon.getPitch().getValue();
  }

  public double getRoll() {
    return 0;//Pigeon.getRoll().getValue();
  }

  public double getYaw() {
    return 0;//Pigeon.getYaw().getValue();
  }

public void setPosition(int position) {
      RobotDrive(100, 0, 0);
    }

    // public double getPosition() {
    //     return (frontLeft.getSelectedSensorPosition() + frontRight.getSelectedSensorPosition()) / 2;
    // }

//   public void driveForward(int inches) {
//     int position = inches * ticksPerInch;
//     setPosition(position);
// }

  @Override
  public void periodic() {
    //SmartDashboard.putNumber("Pitch", getPitch());
    //SmartDashboard.putNumber("Roll", getRoll());
    //SmartDashboard.putNumber("Yaw", getYaw());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
  
    private static final double kSimLoopPeriod = 0.005; // 5 ms
    private Notifier m_simNotifier = null;
    private double m_lastSimTime;
    private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
    private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();
    public double MaxSpeed = TunerConstants.kSpeedAt12VoltsMps; // kSpeedAt12VoltsMps desired top speed
      private double MaxAngularRate = 1.5 * Math.PI; // 3/4 of a rotation per second max angular velocity


    public DriveSubsystem(SwerveDrivetrainConstants driveTrainConstants, double OdometryUpdateFrequency, SwerveModuleConstants... modules) {
        super(driveTrainConstants, OdometryUpdateFrequency, modules);
        if (Utils.isSimulation()) {
            startSimThread();
        }
                configurePathPlanner();

    }
    public DriveSubsystem(SwerveDrivetrainConstants driveTrainConstants, SwerveModuleConstants... modules) {
        super(driveTrainConstants, modules);
        if (Utils.isSimulation()) {
            startSimThread();
        }
              configurePathPlanner();

    }
  private boolean FieldOrentedControl = true;

  private final SwerveRequest.FieldCentric Fielddrive = new SwerveRequest.FieldCentric()
      .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
      .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // I want field-centric
                                                               // driving in open loop

 private final SwerveRequest.RobotCentric Botdrive = new SwerveRequest.RobotCentric()
      .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
      .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // I want field-centric
                                                               // driving in open loop


    public void MoveRobot(double UpDown, double LeftRight, double Rotate)
    {

      if (FieldOrentedControl) {

         this.setControl(Fielddrive.withVelocityX(-LeftRight * MaxSpeed) // Drive forward with
                                                                                           // negative Y (forward)
            .withVelocityY(-UpDown * MaxSpeed) // Drive left with negative X (left)
            .withRotationalRate(-Rotate * MaxAngularRate) // Drive counterclockwise with negative X (left)
            );

      }
      else
      {
          RobotDrive(UpDown, LeftRight, Rotate);
      }
    }

    public void RobotDrive(double UpDown, double LeftRight, double Rotate) {
                this.setControl(Botdrive.withVelocityX(-LeftRight * MaxSpeed) // Drive forward with
                                                                                           // negative Y (forward)
            .withVelocityY(-UpDown * MaxSpeed) // Drive left with negative X (left)
            .withRotationalRate(-Rotate * MaxAngularRate) // Drive counterclockwise with negative X (left)
        );
    }

    public void SwitchBetweenFieldAndRobotOriented()
    {
      FieldOrentedControl = !FieldOrentedControl;
    }

    public Command applyRequest(Supplier<SwerveRequest> requestSupplier) {
        return run(() -> this.setControl(requestSupplier.get()));
    }
    private final SwerveRequest.ApplyChassisSpeeds AutoRequest = new SwerveRequest.ApplyChassisSpeeds();
    private void startSimThread() {
        m_lastSimTime = Utils.getCurrentTimeSeconds();

        /* Run simulation at a faster rate so PID gains behave more reasonably */
        m_simNotifier = new Notifier(() -> {
            final double currentTime = Utils.getCurrentTimeSeconds();
            double deltaTime = currentTime - m_lastSimTime;
            m_lastSimTime = currentTime;

            /* use the measured time delta, get battery voltage from WPILib */
            updateSimState(deltaTime, RobotController.getBatteryVoltage());
        });
        m_simNotifier.startPeriodic(kSimLoopPeriod);
    }

        private void configurePathPlanner() {
        double driveBaseRadius = 0;
        for (var moduleLocation : m_moduleLocations) {
            driveBaseRadius = Math.max(driveBaseRadius, moduleLocation.getNorm());
        }

        AutoBuilder.configureHolonomic(
            ()->this.getState().Pose, // Supplier of current robot pose
            this::seedFieldRelative,  // Consumer for seeding pose against auto
            this::getCurrentRobotChassisSpeeds,
            (speeds)->this.setControl(AutoRequest.withSpeeds(speeds)), // Consumer of ChassisSpeeds to drive the robot
            new HolonomicPathFollowerConfig(new PIDConstants(10, 0, 0),
                                            new PIDConstants(10, 0, 0),
                                            TunerConstants.kSpeedAt12VoltsMps,
                                            driveBaseRadius,
                                            new ReplanningConfig()),
            ()->false, // Change this if the path needs to be flipped on red vs blue
            this); // Subsystem for requirements
    }


    public ChassisSpeeds getCurrentRobotChassisSpeeds() {
        return m_kinematics.toChassisSpeeds(getState().ModuleStates);
    }
}
