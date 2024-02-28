// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.HelperClasses.Constants.ControllerPorts;
import frc.robot.commands.AlignBotWithApriltag;
import frc.robot.commands.AmpIn;
import frc.robot.commands.AmpOut;
import frc.robot.commands.ArmsDown;
import frc.robot.commands.ArmsUp;
import frc.robot.commands.BadAuto;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.IntakeDown;
import frc.robot.commands.IntakeHold;
import frc.robot.commands.IntakeUp;
import frc.robot.commands.RotateCommand;
import frc.robot.commands.ShootForward;
import frc.robot.commands.StrafeCommand;
import frc.robot.commands.WarmUp;
import frc.robot.commands.ShootBack;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.FieldCentricSwitch;
import frc.robot.subsystems.AmpSubsystem;
import frc.robot.subsystems.ArmsSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LightsSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;

import com.ctre.phoenix6.Utils;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private final CommandPS4Controller driverController = new CommandPS4Controller(ControllerPorts.kDriverControllerPort);
  private final CommandJoystick buttonBoard = new CommandJoystick(ControllerPorts.kOperatorControllerPort);
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  ArmsSubsystem arms = new ArmsSubsystem();
  DriveSubsystem drive = TunerConstants.DriveTrain;
  LightsSubsystem lights = new LightsSubsystem();
  ShooterSubsystem shooty = new ShooterSubsystem();
  AmpSubsystem amp = new AmpSubsystem();
  IntakeSubsystem intake = new IntakeSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  public double MaxSpeed = TunerConstants.kSpeedAt12VoltsMps; // kSpeedAt12VoltsMps desired top speed
  private double MaxAngularRate = 1.5 * Math.PI; // 3/4 of a rotation per second max angular velocity

	

  /* Setting up bindings for necessary control of the swerve drive platform */
  private final CommandPS4Controller joystick = new CommandPS4Controller(0); // My joystick
  private final DriveSubsystem drivetrain = TunerConstants.DriveTrain; // My drivetrain

  private final Telemetry logger = new Telemetry(drivetrain.MaxSpeed);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  private final SwerveRequest.RobotCentric robotCentricDrive = new SwerveRequest.RobotCentric()
  .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
  .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // I want field-centric
                                                           // driving in open loop


  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));
       buttonBoard.button(11).whileTrue(new IntakeUp(intake)); //intake
       buttonBoard.button(12).toggleOnTrue(Commands.parallel( new ShootBack(shooty), new IntakeDown(intake))); //full out
       buttonBoard.button(3).toggleOnTrue(Commands.parallel(new ShootBack(shooty), new IntakeHold(intake))); //hold

       buttonBoard.button(8).whileTrue(new AmpIn(amp)); //amp grab      

       buttonBoard.button(7).whileTrue(new AmpOut(amp)); //amp dispense

       buttonBoard.button(2).whileTrue(new WarmUp(shooty)); //priming shoot motors
       buttonBoard.button(1).whileTrue(new ShootForward(shooty)); //feeders on, actually fires


       buttonBoard.button(6).whileTrue(new ArmsUp(arms)); //arms up
       buttonBoard.button(4).whileTrue(new ArmsDown(arms)); //arms down
       //buttonBoard.button(6).whileTrue(new ArmsDown(arms));
       //buttonBoard.button(4).whileTrue(new ArmsUp(arms));
      // buttonBoard.button(6).whileTrue(new ArmsUp(arms)); //arms up (old)
       //buttonBoard.button(4).whileTrue(new ArmsDown(arms)); //arms down (old)
       //buttonBoard.button(5).toggleOnTrue(null); balance

    //joystick.leftBumper().onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldRelative()));
    
    joystick.L1().whileTrue(new StrafeCommand(drive, () -> -.3));    
    joystick.R1().whileTrue(new StrafeCommand(drive, () -> .3));

    joystick.L2().whileTrue(new RotateCommand(drive, () -> -.3));    
    joystick.R2().whileTrue(new RotateCommand(drive, () -> .3));



    joystick.cross().whileTrue(new AlignBotWithApriltag(drive));
    joystick.triangle().whileTrue(new FieldCentricSwitch(drive));
    drive.setDefaultCommand(new DefaultDrive( drive, () -> joystick.getLeftY(),  () -> joystick.getLeftX(),  () -> joystick.getRightX(), 1));

    // drivetrain.setDefaultCommand( // Drivetrain will execute this command periodically
    // drivetrain.applyRequest(() -> robotCentricDrive.withVelocityX(-joystick.getLeftY() * MaxSpeed) // Drive forward with
    //                                                                                    // negative Y (forward)
    //     .withVelocityY(-joystick.getLeftX() * MaxSpeed) // Drive left with negative X (left)
    //     //.withRotationalRate(0) // Drive counterclockwise with negative X (left)
    //     .withRotationalRate(-joystick.getRightX() * MaxAngularRate) // Drive counterclockwise with negative X (left)

    // ));



    if (Utils.isSimulation()) {
      drivetrain.seedFieldRelative(new Pose2d(new Translation2d(), Rotation2d.fromDegrees(90)));
    }
    drivetrain.registerTelemetry(logger::telemeterize);
	
  }
  

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  /*public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return BadAuto.BadAuto(m_exampleSubsystem); //help i dont know how to fix this grahggrhiaushdnakwjlnfgilajksfdnkjse,ngaiwlsekj,ndfkajsdfmnaskjdfnasdkjfn
  }*/
}
