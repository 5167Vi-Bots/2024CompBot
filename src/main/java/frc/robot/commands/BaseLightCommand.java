// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.HelperClasses.Constants.LightsSubsystemConstants;
import frc.robot.subsystems.LightsSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class BaseLightCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final LightsSubsystem m_subsystem;
  

  public Color GetColor()
  {
    return LightsSubsystemConstants.Orange;
  }
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public BaseLightCommand(LightsSubsystem subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.setColor(GetColor());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Color ColorToUse = GetColor(); 

      Double TimeLeft = DriverStation.getMatchTime();

      int TimeLeftInt = TimeLeft.intValue();

      if (TimeLeftInt <= 0 || TimeLeftInt > 30) //Time is greater than 30 or less than or equal to 0, meaning we dont need to blink
      {        
        m_subsystem.setColor(ColorToUse);
        return;
      }
      
      //Blinking Code. Just blink every second. If we want faster blinking times, 
      //we will need to work something else out here

      if (TimeLeftInt % 2 == 0)
        m_subsystem.setColor(Color.kBlack);
      else
        m_subsystem.setColor(ColorToUse);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.setColor(new Color(0,0,0));
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
