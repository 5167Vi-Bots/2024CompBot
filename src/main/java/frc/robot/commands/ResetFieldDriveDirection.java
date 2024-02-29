package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class ResetFieldDriveDirection extends Command {

    DriveSubsystem m_Drive; 
    public ResetFieldDriveDirection(DriveSubsystem drive)
    {
        m_Drive = drive;
        addRequirements(drive);
    }
      @Override
    public void execute() {
        m_Drive.ResetFieldDirection();
    }

    @Override
    public boolean isFinished() {
                return true;

    }
    
}
