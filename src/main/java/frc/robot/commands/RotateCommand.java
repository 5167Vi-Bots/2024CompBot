package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class RotateCommand extends Command {
    private DriveSubsystem driveSubsystem;
    private DoubleSupplier x, y, z;

    private double speedLimit;
    public RotateCommand(DriveSubsystem driveSubsystem, DoubleSupplier x) {
        this.driveSubsystem = driveSubsystem;
        this.x = x;
        addRequirements(driveSubsystem);
    }
    

    @Override
    public void initialize() {
        driveSubsystem.RobotDrive(0, 0, 0);
    }

    @Override
    public void execute() {
        // driveSubsystem.drive(x.getAsDouble()*-speedLimit, SquareInputs(z.getAsDouble() *speedLimit)*-1);
        //System.out.println("The X Axis is " + x.getAsDouble());
        driveSubsystem.RobotDrive(0, 0, x.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
                driveSubsystem.RobotDrive(0, 0, 0);

    }
}