package frc.robot.subsystems;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HelperClasses.Constants.AmpSubsystemConstants;
 
public class AmpSubsystem extends SubsystemBase {

    private TalonFX ampMotor;
    public AmpSubsystem() {
       ampMotor = new TalonFX(AmpSubsystemConstants.ampMotorID);
    }

    public void ampOut(){
        ampMotor.set(1);

    }
     public void ampIn(){
        ampMotor.set(-.75);

    }

// test these later and make them accurate


}
