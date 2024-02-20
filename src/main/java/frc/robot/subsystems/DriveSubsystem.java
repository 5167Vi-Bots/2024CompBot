// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.io.FileReader;
import java.util.function.Supplier;

import com.ctre.phoenix6.Utils;
import com.ctre.phoenix6.mechanisms.swerve.SwerveDrivetrain;
import com.ctre.phoenix6.mechanisms.swerve.SwerveDrivetrainConstants;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstants;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;

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
    }
    public DriveSubsystem(SwerveDrivetrainConstants driveTrainConstants, SwerveModuleConstants... modules) {
        super(driveTrainConstants, modules);
        if (Utils.isSimulation()) {
            startSimThread();
        }
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

      if (!FieldOrentedControl) {
        System.out.println("Field Control X: " + UpDown);       
         System.out.println("MaxSpeed: " + MaxSpeed);

            applyRequest(() -> Fielddrive.withVelocityX(-UpDown * MaxSpeed) // Drive forward with
                                                                                           // negative Y (forward)
            .withVelocityY(-LeftRight * MaxSpeed) // Drive left with negative X (left)
            .withRotationalRate(-Rotate * MaxAngularRate) // Drive counterclockwise with negative X (left)
        );
      }
      else
      {
          RobotDrive(UpDown, LeftRight, Rotate);
      }
    }

    public void RobotDrive(double UpDown, double LeftRight, double Rotate) {
                applyRequest(() -> Botdrive.withVelocityX(-UpDown * MaxSpeed) // Drive forward with
                                                                                           // negative Y (forward)
            .withVelocityY(-LeftRight * MaxSpeed) // Drive left with negative X (left)
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
}
