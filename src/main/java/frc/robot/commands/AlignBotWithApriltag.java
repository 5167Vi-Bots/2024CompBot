package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.LimelightHelpers;
import frc.robot.LimelightHelpers.LimelightResults;
import frc.robot.subsystems.DriveSubsystem;

public class AlignBotWithApriltag extends Command {
    DriveSubsystem drive;
    public AlignBotWithApriltag(DriveSubsystem driveSubsystem)
    {
        drive = driveSubsystem;
        addRequirements(drive);
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void execute() {
    LimelightResults llresults =  LimelightHelpers.getLatestResults("limelight-back");
    
    double kPx = .025;

    double kPy = .025;

    if (llresults.targetingResults.targets_Fiducials.length > 0)
    {
    double xErrorRate=llresults.targetingResults.targets_Fiducials[0].tx; 
    double yErrorRate=llresults.targetingResults.targets_Fiducials[0].ty;
System.out.println("xErrorRate: " + xErrorRate);
System.out.println("yErrorRate: " + yErrorRate);

    drive.MoveRobot(-yErrorRate*kPy, -kPx*xErrorRate, 0);

    Pose2d bc = llresults.targetingResults.getBotPose2d();
    
    SmartDashboard.putNumber("LL PoseX", bc.getX());
    SmartDashboard.putNumber("LL PoseY", bc.getY());
    }

    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drive.MoveRobot(0, 0, 0);
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
    
}
