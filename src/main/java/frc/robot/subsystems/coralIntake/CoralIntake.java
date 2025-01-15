package frc.robot.subsystems.coralIntake;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class CoralIntake extends SubsystemBase {
    public CotralIntake() {
   var config = new TalonSRXConfiguration();
        config.openloopRamp = ElevatorConstants.OPEN_LOOP_MAX;
        config.openloopRamp = ElevatorConstants.OPEN_LOOP_RAMP;
        config.peakOutputForward = ElevatorConstants.OPEN_LOOP_MAX;
        config.peakOutputReverse = ElevatorConstants.OPEN_LOOP_MAX_REVERSE;
               
        motorOne.configAllSettings(config);

        motorOne.setInverted(false);

    }

    public Command CoralIntake() {
        return this.startEnd(() -> System.out.println(""), System.out.println(""))
   }

    public Command CoralIntakeFast() {
        return this.startEnd(() -> System.out.println(""), System.out.println(""))
   }

    public Command CoralIntakeOut() {
        return this.startEnd(() -> System.out.println(""), System.out.println(""))
   }
}
