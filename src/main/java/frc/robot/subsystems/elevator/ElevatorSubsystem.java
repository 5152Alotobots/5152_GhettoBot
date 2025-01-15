package frc.robot.subsystems.elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.elevator.constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {
    private final TalonSRX motorOne = new TalonSRX(ElevatorConstants.MOTOR_ONE_ID);
    private final DigitalOutput downSensor = new DigitalOutput(ElevatorConstants.DOWN_SWITCH_ID);
    private final DigitalOutput upSensor = new DigitalOutput(ElevatorConstants.UP_SWITCH_ID);
//    private TalonSRX motorTwo = new TalonSRX(ElevatorConstants.MOTOR_TWO_ID);
    public ElevatorSubsystem() {
        var config = new TalonSRXConfiguration();
        config.openloopRamp = ElevatorConstants.OPEN_LOOP_RAMP;
        config.peakOutputForward = ElevatorConstants.OPEN_LOOP_MAX;
        config.peakOutputReverse = ElevatorConstants.OPEN_LOOP_MAX_REVERSE;
        motorOne.configAllSettings(config);
//        motorTwo.configAllSettings(config);

//        motorTwo.follow(motorOne);
        motorOne.setInverted(false);
//        motorTwo.setInverted(InvertType.FollowMaster);
    }

    public void setMotors(double speed) {
        motorOne.set(ControlMode.PercentOutput, speed);
    }

    public void setMotorsWithLimits(double speed) {
        if (downSensor.get()) {
            setMotors(Math.max(0, speed));
        }
        if (upSensor.get()) {
            setMotors(Math.min(0, speed));
        }
        if (!downSensor.get() && !upSensor.get()) {
            setMotors(speed);
        }
    }
}
