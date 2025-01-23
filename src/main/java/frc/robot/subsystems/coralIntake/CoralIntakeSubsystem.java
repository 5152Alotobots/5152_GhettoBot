package frc.robot.subsystems.coralIntake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix6.hardware.CANrange;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.coralIntake.constants.CoralIntakeConstants;

public class CoralIntakeSubsystem extends SubsystemBase {
  private final TalonSRX motorOne = new TalonSRX(CoralIntakeConstants.MOTOR_ONE_ID);
  private final CANrange intakeSensor = new CANrange(CoralIntakeConstants.SENSOR_ID);

  public CoralIntakeSubsystem() {
    var config = new TalonSRXConfiguration();
    config.openloopRamp = CoralIntakeConstants.OPEN_LOOP_RAMP;
    config.peakOutputForward = CoralIntakeConstants.OPEN_LOOP_MAX;
    config.peakOutputReverse = CoralIntakeConstants.OPEN_LOOP_MAX_REVERSE;

    motorOne.configAllSettings(config);

    motorOne.setInverted(false);
    
  }

  public boolean getSensorState() {
    return intakeSensor.getIsDetected().getValue();
  }

  public void setMotors(double speed) {
    motorOne.set(ControlMode.PercentOutput, speed);
  }

  public Command CoralIntake() {
    return this.startEnd(() -> setMotors(.2), () -> setMotors(0));
  }

  public Command CoralIntakeFast() {
    return this.startEnd(() -> setMotors(.5), () -> setMotors(0));
  }

  public Command CoralIntakeOut() {
    return this.startEnd(() -> setMotors(-.2), () -> setMotors(0));
  }
}
