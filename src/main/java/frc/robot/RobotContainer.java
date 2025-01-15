// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.coralIntake.CoralIntakeSubsystem;
import frc.robot.subsystems.elevator.ElevatorSubsystem;
import frc.robot.subsystems.elevator.commands.DefaultElevatorCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.MecanumControllerCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import java.util.List;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
  private final CoralIntakeSubsystem coralIntakeSubsystem = new CoralIntakeSubsystem();

  // The driver's controller
  private final XboxController driverController = new XboxController(OIConstants.DRIVER_CONTROLLER_PORT);
  private final XboxController auxController = new XboxController(OIConstants.AUX_CONTROLLER_PORT);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    driveSubsystem.setDefaultCommand(
        new RunCommand(
            () -> driveSubsystem.drive(
                -driverController.getLeftY(),
                -driverController.getRightX(),
                -driverController.getLeftX(),
                false),
            driveSubsystem));
    elevatorSubsystem.setDefaultCommand(
      new DefaultElevatorCommand(elevatorSubsystem, () -> auxController.getLeftY()));
  }

  private void configureButtonBindings() {
    // Drive at half speed when the right bumper is held
    new JoystickButton(driverController, Button.kRightBumper.value)
        .onTrue(new InstantCommand(() -> driveSubsystem.setMaxOutput(0.5)))
        .onFalse(new InstantCommand(() -> driveSubsystem.setMaxOutput(1)));
    new JoystickButton(auxController, Button.kA.value).whileTrue(coralIntakeSubsystem.CoralIntake());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
    // // Create config for trajectory
    // TrajectoryConfig config =
    //     new TrajectoryConfig(
    //             AutoConstants.kMaxSpeedMetersPerSecond,
    //             AutoConstants.kMaxAccelerationMetersPerSecondSquared)
    //         // Add kinematics to ensure max speed is actually obeyed
    //         .setKinematics(DriveConstants.kDriveKinematics);

    // // An example trajectory to follow. All units in meters.
    // Trajectory exampleTrajectory =
    //     TrajectoryGenerator.generateTrajectory(
    //         // Start at the origin facing the +X direction
    //         Pose2d.kZero,
    //         // Pass through these two interior waypoints, making an 's' curve path
    //         List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
    //         // End 3 meters straight ahead of where we started, facing forward
    //         new Pose2d(3, 0, Rotation2d.kZero),
    //         config);

    // MecanumControllerCommand mecanumControllerCommand =
    //     new MecanumControllerCommand(
    //         exampleTrajectory,
    //         m_robotDrive::getPose,
    //         DriveConstants.kFeedforward,
    //         DriveConstants.kDriveKinematics,

    //         // Position controllers
    //         new PIDController(AutoConstants.kPXController, 0, 0),
    //         new PIDController(AutoConstants.kPYController, 0, 0),
    //         new ProfiledPIDController(
    //             AutoConstants.kPThetaController, 0, 0, AutoConstants.kThetaControllerConstraints),

    //         // Needed for normalizing wheel speeds
    //         AutoConstants.kMaxSpeedMetersPerSecond,

    //         // Velocity PID's
    //         new PIDController(DriveConstants.kPFrontLeftVel, 0, 0),
    //         new PIDController(DriveConstants.kPRearLeftVel, 0, 0),
    //         new PIDController(DriveConstants.kPFrontRightVel, 0, 0),
    //         new PIDController(DriveConstants.kPRearRightVel, 0, 0),
    //         m_robotDrive::getCurrentWheelSpeeds,
    //         m_robotDrive::setDriveMotorControllersVolts, // Consumer for the output motor voltages
    //         m_robotDrive);

    // Reset odometry to the initial pose of the trajectory, run path following
    // command, then stop at the end.
    // return Commands.sequence(
    //     new InstantCommand(() -> m_robotDrive.resetOdometry(exampleTrajectory.getInitialPose())),
    //     mecanumControllerCommand,
    //     new InstantCommand(() -> m_robotDrive.drive(0, 0, 0, false)));
  }
}
