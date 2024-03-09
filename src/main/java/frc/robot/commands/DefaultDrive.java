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

        double tmpx, tmpy, tmpz;
        double xOut, yOut, zOut;

        tmpx = x.getAsDouble();        
        tmpy = y.getAsDouble();
        tmpz = z.getAsDouble();


        //Cube Outputs


        xOut = tmpx*tmpx*tmpx;
        yOut = tmpy*tmpy*tmpy;
        zOut = tmpz*tmpz*tmpz;
/*
        xOut = tmpx;
        yOut = tmpy;
        zOut = tmpz;

        xOut = tmpx*tmpx*tmpx;
        yOut = tmpy*tmpy*tmpy;
        zOut = tmpz*tmpz*tmpz;*/

        driveSubsystem.MoveRobot(yOut, xOut, zOut);
    }

    @Override
    public void end(boolean interrupted) {
                driveSubsystem.MoveRobot(0, 0, 0);

    }
}