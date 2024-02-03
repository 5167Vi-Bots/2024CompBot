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
        ampMotor.setPosition(30);

    }
     public void ampIn(){
        ampMotor.setPosition(-30);

    }

// test these later and make them accurate


}
