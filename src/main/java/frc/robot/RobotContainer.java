// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import java.io.IOException;

import edu.wpi.first.wpilibj.Joystick;
// import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class RobotContainer 
{
  private final SwerveDriveSubsystem swerveDriveSubsystem = new SwerveDriveSubsystem();
  private final Joystick driverController = new Joystick(0);
  

  public RobotContainer() throws IOException
  {
    // Configure default command for the swerve drive subsystem
    swerveDriveSubsystem.setDefaultCommand(new DriveWithJoystick(swerveDriveSubsystem, 
                                            driverController, Constants.isFieldRelative));
    configureBindings();

  }

  public SwerveDriveSubsystem getSwerveDriveSubsystem()
  {
    return swerveDriveSubsystem;
  }

  private void configureBindings()
  {
    /*
     * Bind commands to buttons (example)
     * JoystickButton exampleButton = new JoystickButton(driverController, 1);
     * exampleButton.whenPressed(new ExampleCommand()); // Replace with your actual command
     */
    // Additional configuration...
  }

  public Command getAutonomousCommand()
  {
    return Commands.print("No autonomous command configured");
  }
}
