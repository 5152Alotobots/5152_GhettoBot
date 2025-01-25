package frc.robot.subsystems.wrist;

import static edu.wpi.first.units.Units.Degree;
import static edu.wpi.first.units.Units.Radian;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionTorqueCurrentFOC;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.ParentDevice;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.wrist.constants.WristConstants;

public class WristSubsystem extends SubsystemBase {
   private final TalonFX wristMotor;

   private final PositionTorqueCurrentFOC positionTorqueCurrentFOC = new PositionTorqueCurrentFOC(Angle.ofBaseUnits(0, Degree));
   private final PositionVoltage positionVoltage = new PositionVoltage(Angle.ofBaseUnits(0, Degree));

   private final Debouncer wristConnectedDebouncer = new Debouncer(0.5);

   private final StatusSignal<Integer> currentPidSlot;
   private final StatusSignal<Voltage> wristAppliedVoltage;
   private final StatusSignal<Current> wristAppliedCurrent;
   private final StatusSignal<AngularVelocity> wristVelocity;
   private final StatusSignal<Angle> wristPosition;
   private final StatusSignal<Boolean> topSoftLimit;
   private final StatusSignal<Boolean> bottomSoftLimit;

   public WristSubsystem() {
      wristMotor = new TalonFX(WristConstants.WRIST_MOTOR_ID);

      var config = new TalonFXConfiguration();
      var positionSlot = new Slot0Configs();

      positionSlot.kG = 0.0;
      positionSlot.kP = 0.0;
      positionSlot.kI = 0.0;
      positionSlot.kD = 0.0;
      
      config.Slot0 = positionSlot;

      // Apply config wrist
      wristMotor.getConfigurator().apply(config, 0.25);

      currentPidSlot = wristMotor.getClosedLoopSlot();
      wristAppliedVoltage = wristMotor.getMotorVoltage();
      wristAppliedCurrent = wristMotor.getStatorCurrent();
      wristVelocity = wristMotor.getVelocity();
      wristPosition = wristMotor.getPosition();
      topSoftLimit = wristMotor.getFault_ForwardSoftLimit();
      bottomSoftLimit = wristMotor.getFault_ReverseSoftLimit();

      BaseStatusSignal.setUpdateFrequencyForAll(
            50,
            currentPidSlot,
            wristAppliedVoltage,
            wristAppliedCurrent,
            wristVelocity,
            wristPosition,
            topSoftLimit,
            bottomSoftLimit);

      ParentDevice.optimizeBusUtilizationForAll(wristMotor);
   }

   @Override
   public void periodic() {
      BaseStatusSignal.refreshAll(
            currentPidSlot,
            wristAppliedVoltage,
            wristAppliedCurrent,
            wristVelocity,
            wristPosition,
            topSoftLimit,
            bottomSoftLimit);
   }

    public void setWristPositionVoltage(Angle rotation, int pidSlot) {
      wristMotor.setControl(positionVoltage.withPosition(rotation).withSlot(pidSlot));
   }  
   
   public void setWristPositionTorque(Angle rotation, int pidSlot) {
      wristMotor.setControl(positionTorqueCurrentFOC.withPosition(rotation).withSlot(pidSlot));
   }

   public void setWristOpenLoop(double percentOutput) {
      wristMotor.set(percentOutput);
   }

   public void stop() {
      wristMotor.stopMotor();
   }
}
