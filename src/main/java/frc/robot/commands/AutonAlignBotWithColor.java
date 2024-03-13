package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.LimelightHelpers;
import frc.robot.LimelightHelpers.LimelightResults;
import frc.robot.subsystems.DriveSubsystem;

public class AutonAlignBotWithColor extends Command {
    DriveSubsystem drive;
    public AutonAlignBotWithColor(DriveSubsystem driveSubsystem)
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
    //LimelightResults llresults =  LimelightHelpers.getLatestResults("limelight-front");
    
    double kPx = .025;

    double kPy = .025;

    //if (llresults.targetingResults.targets_Retro.length > 0)
    //{
    //double xErrorRate=llresults.targetingResults.targets_Retro[0].tx; 
    //double yErrorRate=llresults.targetingResults.targets_Retro[0].ty;
//System.out.println("xErrorRate: " + xErrorRate);
//System.out.println("yErrorRate: " + yErrorRate);

double xErrorRate = NetworkTableInstance.getDefault().getTable("limelight-front").getEntry("tx").getDouble(0);

    drive.RobotDrive(0, 0 ,-kPx*xErrorRate);

    //Pose2d bc = llresults.targetingResults.getBotPose2d();
    
    //SmartDashboard.putNumber("LL PoseX", bc.getX());
    //SmartDashboard.putNumber("LL PoseY", bc.getY());
    //}

    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drive.RobotDrive(0, 0, 0);
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return NetworkTableInstance.getDefault().getTable("limelight-front").getEntry("tx").getDouble(0) == 0;
    }
    
}
