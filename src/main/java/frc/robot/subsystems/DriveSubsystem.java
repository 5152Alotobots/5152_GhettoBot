// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.MecanumDriveOdometry;
import edu.wpi.first.math.kinematics.MecanumDriveWheelPositions;
import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  private final VictorSPX frontLeft = new VictorSPX(DriveConstants.kFrontLeftMotorPort);
  private final VictorSPX frontRight = new VictorSPX(DriveConstants.kFrontRightMotorPort);
  private final VictorSPX rearLeft = new VictorSPX(DriveConstants.kRearLeftMotorPort);
  private final VictorSPX rearRight = new VictorSPX(DriveConstants.kRearRightMotorPort);


  private void runMotor(VictorSPX motor, double output) {
    motor.set(VictorSPXControlMode.PercentOutput, output);
  }

  private final MecanumDrive m_drive = new MecanumDrive(
    (output) -> runMotor(frontLeft, output),
    (output) -> runMotor(rearLeft, output),
    (output) -> runMotor(frontRight, output),
    (output) -> runMotor(rearRight, output));

  /*  
  // The front-left-side drive encoder
  private final Encoder m_frontLeftEncoder =
      new Encoder(
          DriveConstants.kFrontLeftEncoderPorts[0],
          DriveConstants.kFrontLeftEncoderPorts[1],
          DriveConstants.kFrontLeftEncoderReversed);

  // The rear-left-side drive encoder
  private final Encoder m_rearLeftEncoder =
      new Encoder(
          DriveConstants.kRearLeftEncoderPorts[0],
          DriveConstants.kRearLeftEncoderPorts[1],
          DriveConstants.kRearLeftEncoderReversed);

  // The front-right--side drive encoder
  private final Encoder m_frontRightEncoder =
      new Encoder(
          DriveConstants.kFrontRightEncoderPorts[0],
          DriveConstants.kFrontRightEncoderPorts[1],
          DriveConstants.kFrontRightEncoderReversed);

  // The rear-right-side drive encoder
  private final Encoder m_rearRightEncoder =
      new Encoder(
          DriveConstants.kRearRightEncoderPorts[0],
          DriveConstants.kRearRightEncoderPorts[1],
          DriveConstants.kRearRightEncoderReversed);

  // The gyro sensor
  //private final ADXRS450_Gyro m_gyro = new ADXRS450_Gyro();

  // Odometry class for tracking robot pose
  MecanumDriveOdometry m_odometry =
      new MecanumDriveOdometry(
          DriveConstants.kDriveKinematics,
          m_gyro.getRotation2d(),
          new MecanumDriveWheelPositions());
*/
  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {

    SendableRegistry.addChild(m_drive, frontLeft);
    SendableRegistry.addChild(m_drive, rearLeft);
    SendableRegistry.addChild(m_drive, frontRight);
    SendableRegistry.addChild(m_drive, rearRight);

    // Sets the distance per pulse for the encoders
    // m_frontLeftEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
    // m_rearLeftEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
    // m_frontRightEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
    // m_rearRightEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
    // // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    //frontRight.setInverted(true);
    rearLeft.setInverted(true);
  }

  @Override
  public void periodic() {
    // Update the odometry in the periodic block
    //m_odometry.update(m_gyro.getRotation2d(), getCurrentWheelDistances());
  }

  /**
   * Returns the currently-estimated pose of the robot.
   *
   * @return The pose.
  //  */
  // public Pose2d getPose() {
  //   return m_odometry.getPoseMeters();
  // }

  /**
   * Resets the odometry to the specified pose.
   *
   * @param pose The pose to which to set the odometry.
   */
  // public void resetOdometry(Pose2d pose) {
  //   m_odometry.resetPosition(m_gyro.getRotation2d(), getCurrentWheelDistances(), pose);
  // }

  /**
   * Drives the robot at given x, y and theta speeds. Speeds range from [-1, 1] and the linear
   * speeds have no effect on the angular speed.
   *
   * @param xSpeed Speed of the robot in the x direction (forward/backwards).
   * @param ySpeed Speed of the robot in the y direction (sideways).
   * @param rot Angular rate of the robot.
   * @param fieldRelative Whether the provided x and y speeds are relative to the field.
   */
  public void drive(double xSpeed, double ySpeed, double rot, boolean fieldRelative) {
    if (fieldRelative) {
      // m_drive.driveCartesian(xSpeed, ySpeed, rot, m_gyro.getRotation2d());
    } else {
      m_drive.driveCartesian(xSpeed, ySpeed, rot);
    }
  }

  /** Sets the front left drive MotorController to a voltage. */
  // public void setDriveMotorControllersVolts(
  //     double frontLeftVoltage,
  //     double frontRightVoltage,
  //     double rearLeftVoltage,
  //     double rearRightVoltage) {
  //   m_frontLeft.setVoltage(frontLeftVoltage);
  //   m_rearLeft.setVoltage(rearLeftVoltage);
  //   m_frontRight.setVoltage(frontRightVoltage);
  //   m_rearRight.setVoltage(rearRightVoltage);
  // }

  /** Resets the drive encoders to currently read a position of 0. */
  // public void resetEncoders() {
  //   m_frontLeftEncoder.reset();
  //   m_rearLeftEncoder.reset();
  //   m_frontRightEncoder.reset();
  //   m_rearRightEncoder.reset();
  // }

  /**
   * Gets the current wheel speeds.
   *
   * @return the current wheel speeds in a MecanumDriveWheelSpeeds object.
   */
  // public MecanumDriveWheelSpeeds getCurrentWheelSpeeds() {
  //   return new MecanumDriveWheelSpeeds(
  //       m_frontLeftEncoder.getRate(),
  //       m_rearLeftEncoder.getRate(),
  //       m_frontRightEncoder.getRate(),
  //       m_rearRightEncoder.getRate());
  // }

  /**
   * Gets the current wheel distance measurements.
   *
   * @return the current wheel distance measurements in a MecanumDriveWheelPositions object.
   */
  // public MecanumDriveWheelPositions getCurrentWheelDistances() {
  //   return new MecanumDriveWheelPositions(
  //       m_frontLeftEncoder.getDistance(),
  //       m_rearLeftEncoder.getDistance(),
  //       m_frontRightEncoder.getDistance(),
  //       m_rearRightEncoder.getDistance());
  // }

  /**
   * Sets the max output of the drive. Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }

  /** Zeroes the heading of the robot. */
  // public void zeroHeading() {
  //   m_gyro.reset();
  // }

  /**
   * Returns the heading of the robot.
   *
   * @return the robot's heading in degrees, from -180 to 180
   */
  // public double getHeading() {
  //   return m_gyro.getRotation2d().getDegrees();
  // }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  // public double getTurnRate() {
  //   return -m_gyro.getRate();
  // }
}
