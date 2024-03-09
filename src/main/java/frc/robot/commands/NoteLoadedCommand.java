package frc.robot.commands;

import edu.wpi.first.wpilibj.util.Color;
import frc.robot.HelperClasses.Constants.LightsSubsystemConstants;
import frc.robot.subsystems.LightsSubsystem;

public class NoteLoadedCommand extends BaseLightCommand {

    public NoteLoadedCommand(LightsSubsystem lights){
        super(lights);
    }
      
     
    @Override
      public Color GetColor()
      {
        return new Color(0,255,0);
      }

}