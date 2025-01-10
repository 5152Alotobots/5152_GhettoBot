package frc.robot.subsystems.elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.elevator.constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {
    private TalonSRX motorOne = new TalonSRX(ElevatorConstants.MOTOR_ONE_ID);
    private TalonSRX motorTwo = new TalonSRX(ElevatorConstants.MOTOR_TWO_ID);
    public ElevatorSubsystem() {
        var config = new TalonSRXConfiguration();
        config.openloopRamp = ElevatorConstants.OPEN_LOOP_MAX;
        config.openloopRamp = ElevatorConstants.OPEN_LOOP_RAMP;
        config.peakOutputForward = ElevatorConstants.OPEN_LOOP_MAX;
        config.peakOutputReverse = ElevatorConstants.OPEN_LOOP_MAX_REVERSE;
               
        motorOne.configAllSettings(config);
        motorTwo.configAllSettings(config);

        motorTwo.follow(motorOne);
        motorOne.setInverted(false);
        motorTwo.setInverted(InvertType.FollowMaster);
    }

    public void setMotors(double speed) {
        motorOne.set(ControlMode.PercentOutput, speed);
    }
}
