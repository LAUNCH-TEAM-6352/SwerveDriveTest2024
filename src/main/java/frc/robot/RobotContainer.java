// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import java.io.IOException;
import java.util.Optional;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveWithGamepad;
// import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class RobotContainer 
{
  SendableChooser<Boolean> driveOrientationChooser = new SendableChooser<>();
  private final SwerveDriveSubsystem swerveDriveSubsystem = new SwerveDriveSubsystem();
  // private final Joystick joystick = new Joystick(0);
  private final XboxController gamepad = new XboxController(2);
  

  public RobotContainer() throws IOException
  {
    // Configure default command for the swerve drive subsystem
    swerveDriveSubsystem.setDefaultCommand(new DriveWithGamepad(swerveDriveSubsystem, 
                                            gamepad, driveOrientationChooser));
    configureBindings();
    configureSmartDashboard();

  }

  public SwerveDriveSubsystem getSwerveDriveSubsystem()
  {
    return swerveDriveSubsystem;
  }

  private void configureSmartDashboard()
  {
    driveOrientationChooser.setDefaultOption("Field Relative", Boolean.TRUE);
    driveOrientationChooser.addOption("Robot Relative", Boolean.FALSE);
    SmartDashboard.putData("Drive Orientation", driveOrientationChooser);
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
