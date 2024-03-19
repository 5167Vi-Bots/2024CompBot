import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class TimedDrive extends Command {

    DriveSubsystem drive;
    Timer timer = new Timer();
    double UpDown;
    double LeftRight;
    double Rotation;
    BooleanSupplier StopCondition;
    double Seconds;

    public TimedDrive(double Seconds, double UpDown, double LeftRight, double Rotation, DriveSubsystem driveSubsystem) {
        drive = driveSubsystem;
        addRequirements(drive);
        this.UpDown = UpDown;
        this.LeftRight = LeftRight;
        this.Rotation = Rotation;
        this.Seconds = Seconds;
        StopCondition = (()->false);
    }

        public TimedDrive(double Seconds, double UpDown, double LeftRight, double Rotation, BooleanSupplier StopCondition, DriveSubsystem driveSubsystem) {
        drive = driveSubsystem;
        addRequirements(drive);
        this.UpDown = UpDown;
        this.LeftRight = LeftRight;
        this.Rotation = Rotation;
        this.StopCondition = StopCondition;        
        this.Seconds = Seconds;

    }

    
    @Override
    public void initialize() {
        drive.RobotDrive(0, 0, 0);
        timer.start();
    }

    @Override
    public void execute() {
        drive.RobotDrive(UpDown, LeftRight, Rotation);
    }

    @Override
    public void end(boolean interrupted) {
                drive.RobotDrive(0, 0, 0);
    }

    @Override
    public boolean isFinished()
    {
        return StopCondition.getAsBoolean() || timer.hasElapsed(Seconds);
    }


    
}
