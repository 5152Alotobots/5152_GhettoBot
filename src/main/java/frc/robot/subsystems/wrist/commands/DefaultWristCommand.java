// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.wrist.commands;

import java.util.function.DoubleSupplier;


import frc.robot.subsystems.wrist.WristSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class DefaultWristCommand extends Command {
  private WristSubsystem wristSubsystem;
  private DoubleSupplier input;
  /** Creates a new DefaultCommand. */
  public DefaultWristCommand(WristSubsystem wristSubsystem, DoubleSupplier input) {
    this.wristSubsystem = wristSubsystem;
    this.input = input;
    //Motor Configs
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(wristSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    wristSubsystem.setWristOpenLoop(input.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    wristSubsystem.setWristOpenLoop(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
