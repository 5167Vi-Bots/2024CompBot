package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DefaultDrive extends CommandBase {
    private DriveSubsystem driveSubsystem;
    private DoubleSupplier x, y, z;

    private double speedLimit;
    public DefaultDrive(DriveSubsystem driveSubsystem, DoubleSupplier x, DoubleSupplier y, DoubleSupplier z, double speedLimit) {
        this.driveSubsystem = driveSubsystem;
        this.x = x;
        this.y = y;
        this.z = z;
        this.speedLimit = speedLimit;
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        driveSubsystem.MoveRobot(0, 0, 0);
    }

    @Override
    public void execute() {
        // driveSubsystem.drive(x.getAsDouble()*-speedLimit, SquareInputs(z.getAsDouble() *speedLimit)*-1);
        driveSubsystem.MoveRobot(x.getAsDouble()*-speedLimit, y.getAsDouble()*-speedLimit, z.getAsDouble()*-speedLimit);
    }

    @Override
    public void end(boolean interrupted) {
                driveSubsystem.MoveRobot(0, 0, 0);

    }
}