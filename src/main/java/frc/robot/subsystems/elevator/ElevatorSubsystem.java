package frc.robot.subsystems.elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.elevator.constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {
    private final TalonSRX motorOne = new TalonSRX(ElevatorConstants.MOTOR_ONE_ID);
    private final DigitalInput downSensor = new DigitalInput(ElevatorConstants.DOWN_SWITCH_ID);
    private final DigitalInput upSensor = new DigitalInput(ElevatorConstants.UP_SWITCH_ID);

    public ElevatorSubsystem() {
        var config = new TalonSRXConfiguration();
        config.openloopRamp = ElevatorConstants.OPEN_LOOP_RAMP;
        config.peakOutputForward = ElevatorConstants.OPEN_LOOP_MAX;
        config.peakOutputReverse = ElevatorConstants.OPEN_LOOP_MAX_REVERSE;
               
        motorOne.configAllSettings(config);
        motorOne.setNeutralMode(NeutralMode.Brake);
        motorOne.setInverted(false);
    }

    public void setMotors(double speed) {
        SmartDashboard.putNumber("Speed", speed);
        motorOne.set(ControlMode.PercentOutput, speed);
    }

    public void setMotorsWithLimits(double speed) {
        if (!downSensor.get()) {
            setMotors(Math.min(0, speed));
        }
        if (!upSensor.get()) {
            setMotors(Math.max(0, speed));
        }
        if (downSensor.get() && upSensor.get()) {
            setMotors(speed);
        }
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Bottom Switch", !downSensor.get());
        SmartDashboard.putBoolean("Up Switch", !upSensor.get());
    }
}
