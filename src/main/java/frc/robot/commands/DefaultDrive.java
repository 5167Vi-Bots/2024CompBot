package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class DefaultDrive extends Command {
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
        //System.out.println("The X Axis is " + x.getAsDouble());
        driveSubsystem.MoveRobot(x.getAsDouble(), y.getAsDouble(), z.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
                driveSubsystem.MoveRobot(0, 0, 0);

    }
}