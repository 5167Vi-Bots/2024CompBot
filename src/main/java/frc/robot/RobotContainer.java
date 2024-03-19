// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.HelperClasses.Constants.ControllerPorts;
import frc.robot.HelperClasses.MotorTelemetry;
import frc.robot.commands.AlignBotWithApriltag;
import frc.robot.commands.AlignBotWithColor;
import frc.robot.commands.AllianceLightCommand;
import frc.robot.commands.AmpIn;
import frc.robot.commands.AmpOut;
import frc.robot.commands.ArmsDown;
import frc.robot.commands.ArmsUp;
import frc.robot.commands.AutonAlignBotWithColor;
import frc.robot.commands.AutonIntakeHold;
import frc.robot.commands.AutonIntakeStop;
import frc.robot.commands.AutonIntakeUp;
import frc.robot.commands.AutonShootBack;
import frc.robot.commands.AutonShootStart;
import frc.robot.commands.AutonShootStop;
import frc.robot.commands.AutonWarmUpStart;
import frc.robot.commands.BadAuto;
import frc.robot.commands.BaseLightCommand;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.IntakeDown;
import frc.robot.commands.IntakeHold;
import frc.robot.commands.IntakeUp;
import frc.robot.commands.LeftArmDown;
import frc.robot.commands.NoteLoadedCommand;
import frc.robot.commands.ResetFieldDriveDirection;
import frc.robot.commands.RightArmDown;
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

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;

import com.ctre.phoenix6.Utils;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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
  private final CommandJoystick buttonBoard = new CommandJoystick(ControllerPorts.kOperatorControllerPort);
  // The robot's subsystems and commands are defined here...
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  ArmsSubsystem arms = new ArmsSubsystem();
  DriveSubsystem drive = TunerConstants.DriveTrain;
  LightsSubsystem lights = new LightsSubsystem();
  ShooterSubsystem shooty = new ShooterSubsystem();
  AmpSubsystem amp = new AmpSubsystem();
  IntakeSubsystem intake = new IntakeSubsystem();

	

  /* Setting up bindings for necessary control of the swerve drive platform */
  private final CommandPS4Controller joystick = new CommandPS4Controller(0); // My joystick
  private final DriveSubsystem drivetrain = TunerConstants.DriveTrain; // My drivetrain

  private final Telemetry logger = new Telemetry(drivetrain.MaxSpeed);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    registerPathPlannerCommands();
    registerAutons();
    configureGeneralTelemetry();
    
  }
private void configureGeneralTelemetry() {
  Shuffleboard.getTab("Testing").addDouble("Battery Voltage", (()-> RobotController.getBatteryVoltage()));

  /*
  var DriveTab = Shuffleboard.getTab("DriveSubsystem");

    MotorTelemetry.AddMotorTelemetry(DriveTab, "Module 1", drive.getModule(0));    
    MotorTelemetry.AddMotorTelemetry(DriveTab, "Module 2", drive.getModule(1));
    MotorTelemetry.AddMotorTelemetry(DriveTab, "Module 3", drive.getModule(2));    
    MotorTelemetry.AddMotorTelemetry(DriveTab, "Module 4", drive.getModule(3));  
*/

    var DSTab = Shuffleboard.getTab("DriverStation");

    DSTab.addString("Event Name", (()-> DriverStation.getEventName()));
    DSTab.addInteger("Match Number", (() -> DriverStation.getMatchNumber()));
    DSTab.addString("Match Type", (() -> DriverStation.getMatchType().toString()));
    DSTab.addString("Alliance", (() -> DriverStation.getAlliance().toString()));
    DSTab.addString("Location", (() -> DriverStation.getLocation().toString()));
    DSTab.addString("Game Specific Message", (()-> DriverStation.getGameSpecificMessage()));
    DSTab.addBoolean("Is Teleop", (() -> DriverStation.isTeleop()));
    DSTab.addBoolean("Teleop Enabled", (() -> DriverStation.isTeleopEnabled()));
    DSTab.addBoolean("Is Auton", (() -> DriverStation.isAutonomous()));
    DSTab.addBoolean("Auton Enabled", (() -> DriverStation.isAutonomousEnabled()));
    DSTab.addBoolean("Is Disabled", (() -> DriverStation.isDisabled()));
    DSTab.addBoolean("Is EStopped", (() -> DriverStation.isEStopped()));
    DSTab.addBoolean("Is Enabled", (() -> DriverStation.isEnabled()));
    DSTab.addBoolean("FMS Attached", (() -> DriverStation.isFMSAttached()));
    DSTab.addBoolean("Driver station Attached", (() -> DriverStation.isDSAttached()));
    
    SetupControllerTelemetry(DSTab, 0); 
    SetupControllerTelemetry(DSTab, 1);
    SetupControllerTelemetry(DSTab, 2);
    SetupControllerTelemetry(DSTab, 3);
    SetupControllerTelemetry(DSTab, 4);
    SetupControllerTelemetry(DSTab, 5);



  
  }

  public void SetupControllerTelemetry(ShuffleboardTab tab, int ControllerID)
  {
    String prefix = "Controller " + ControllerID + " ";
    tab.addBoolean(prefix + "Is Connected", (() -> DriverStation.isJoystickConnected(ControllerID)));

    if (!DriverStation.isJoystickConnected(ControllerID))
      return;

    int LoopCount = DriverStation.getStickButtonCount(ControllerID);
    String LoopPrefix = prefix + "Button ";
    int i = -1;
    for (i = 0; i <= LoopCount; i++)
    {
      tab.addBoolean(LoopPrefix + i + " Pressed", new StickButtonSupplier(ControllerID, i).Supplier);
    }

    LoopCount = DriverStation.getStickAxisCount(ControllerID);
    LoopPrefix = prefix + "Axis ";
    for (i = 0; i <= LoopCount; i++)
    {
      tab.addDouble(LoopPrefix + i + " Axis Value", new StickAxisSupplier(ControllerID, i).Supplier);
    }

    LoopCount = DriverStation.getStickPOVCount(ControllerID);
    LoopPrefix = prefix + "POV ";
    for (i = 0; i <= LoopCount; i++)
    {
      tab.addInteger(LoopPrefix + i + " Value", new StickPOVSupplier(ControllerID, i).Supplier);
    }
    
    
  }

  private class StickButtonSupplier
  {
    private int id = -1;     
    private int ctrlid = -1; 

    public StickButtonSupplier (int ControllerID, int newid)
    {
      id = newid;
      ctrlid = ControllerID;
    }

    public BooleanSupplier Supplier = (() -> DriverStation.getStickButton(ctrlid, id));
  }

    private class StickAxisSupplier
  {
    private int id = -1;     
    private int ctrlid = -1; 

    public StickAxisSupplier (int ControllerID, int newid)
    {
      id = newid;
      ctrlid = ControllerID;
    }

    public DoubleSupplier Supplier = (() -> DriverStation.getStickAxis(ctrlid, id));
  }


  private class StickPOVSupplier
  {
    private int id = -1;     
    private int ctrlid = -1; 

    public StickPOVSupplier (int ControllerID, int newid)
    {
      id = newid;
      ctrlid = ControllerID;
    }

    public LongSupplier Supplier = (() -> DriverStation.getStickPOV(ctrlid, id));
  }



public SendableChooser<String> AutonChooser = new SendableChooser<String>();
//public SendableChooser<Command> AutonChooser = new SendableChooser<Command>();

  private void registerAutons() {
    
    //Create Shuffleboard Tab
    var tab = Shuffleboard.getTab("Auton");

    //Register Auton modes
    AutonChooser.addOption("Drive Forward", "DriveForward");
    AutonChooser.addOption("ShootAuton","shootAuton");
    AutonChooser.addOption("Multipiece", "MultipieceSkeleton");
    
    //Set the default Auton
    AutonChooser.setDefaultOption("Multipiece","MultipieceSkeleton");
    
    //Add to shuffleboard
    tab.add(AutonChooser);    
    //tab.add(AutoBuilder.buildAutoChooser());

  }

  private void registerPathPlannerCommands() {

    NamedCommands.registerCommand("IntakeUp", new AutonIntakeUp(intake));
    NamedCommands.registerCommand("IntakeStop", new AutonIntakeStop(intake));
    NamedCommands.registerCommand("WarmUp", new AutonWarmUpStart(shooty));
    NamedCommands.registerCommand("Shoot", new AutonShootStart(shooty));
    NamedCommands.registerCommand("ShootStop", new AutonShootStop(shooty));
    NamedCommands.registerCommand("Hold",(Commands.parallel(new AutonShootBack(shooty), new AutonIntakeUp(intake))));   
    NamedCommands.registerCommand("NoteAlign",new AutonAlignBotWithColor(drive));

  }

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
       buttonBoard.button(11).whileTrue(new IntakeUp(intake)); //intake
       buttonBoard.button(12).toggleOnTrue(Commands.parallel( new ShootBack(shooty), new IntakeDown(intake))); //full out
       buttonBoard.button(3).toggleOnTrue(Commands.parallel(new ShootBack(shooty), new IntakeHold(intake))); //hold

       buttonBoard.button(8).whileTrue(new AmpIn(amp)); //amp grab      

       buttonBoard.button(7).whileTrue(new AmpOut(amp)); //amp dispense

       buttonBoard.button(2).whileTrue(new WarmUp(shooty)); //priming shoot motors
       buttonBoard.button(1).whileTrue(new ShootForward(shooty)); //feeders on, actually fires


       buttonBoard.button(6).whileTrue(new ArmsUp(arms)); //arms up
       buttonBoard.button(4).whileTrue(new ArmsDown(arms)); //arms down

       buttonBoard.button(9).whileTrue(new LeftArmDown(arms));      
       buttonBoard.button(10).whileTrue(new RightArmDown(arms));

       buttonBoard.button(5).whileTrue(new AutonAlignBotWithColor(drive));

       //buttonBoard.button(6).whileTrue(new ArmsDown(arms));
       //buttonBoard.button(4).whileTrue(new ArmsUp(arms));
      // buttonBoard.button(6).whileTrue(new ArmsUp(arms)); //arms up (old)
       //buttonBoard.button(4).whileTrue(new ArmsDown(arms)); //arms down (old)
       //buttonBoard.button(5).toggleOnTrue(null); bala\nce

    //joystick.leftBumper().onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldRelative()));
    
    joystick.L1().whileTrue(new StrafeCommand(drive, () -> -.3));    
    joystick.R1().whileTrue(new StrafeCommand(drive, () -> .3));

    joystick.L2().whileTrue(new RotateCommand(drive, () -> -.3));    
    joystick.R2().whileTrue(new RotateCommand(drive, () -> .3));
    joystick.square().whileTrue(new AlignBotWithColor(drive));

    joystick.circle().whileTrue(new ResetFieldDriveDirection(drive));
    joystick.cross().whileTrue(new AlignBotWithApriltag(drive));
    joystick.triangle().whileTrue(new FieldCentricSwitch(drive));
    drive.setDefaultCommand(new DefaultDrive( drive, () -> joystick.getLeftY(),  () -> joystick.getLeftX(),  () -> joystick.getRightX(), 1));

    Trigger NoteLoadedTrigger = new Trigger(()->intake.ringIn());

    NoteLoadedTrigger.whileTrue(new NoteLoadedCommand(lights));

    lights.setDefaultCommand(new AllianceLightCommand(lights));



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
  public Command getAutonomousCommand() {
    return new PathPlannerAuto(AutonChooser.getSelected());
  }
}
