// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;

import java.io.IOException;

import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.CameraConstants;
import frc.robot.commands.DriveWithGamepad;
// import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class RobotContainer 
{
  SendableChooser<Boolean> driveOrientationChooser = new SendableChooser<>();
  SendableChooser<Command> autoChooser = new SendableChooser<>();
  private final SwerveDriveSubsystem swerveDriveSubsystem = new SwerveDriveSubsystem();
  // private final Joystick joystick = new Joystick(0);
  private final XboxController gamepad = new XboxController(2);
  private final String gameData;
  

  public RobotContainer() throws IOException
  {
    // Configure default command for the swerve drive subsystem
    swerveDriveSubsystem.setDefaultCommand(new DriveWithGamepad(swerveDriveSubsystem, 
                                            gamepad, driveOrientationChooser));
    configureBindings();
    configureSmartDashboard();
    gameData = DriverStation.getGameSpecificMessage().toLowerCase();
    SmartDashboard.putString(gameData, gameData);
    if (gameData.contains("-cam-")|| gameData.isBlank()){
      var camera = CameraServer.startAutomaticCapture();
      camera.setFPS(CameraConstants.fps);
      camera.setResolution(CameraConstants.width, CameraConstants.height);
    }

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

    autoChooser.setDefaultOption("Leave",  new PathPlannerAuto("Leave"));
    autoChooser.addOption("Return",  new PathPlannerAuto("Return"));
    SmartDashboard.putData("Auto Selection", autoChooser);
     
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
    return autoChooser.getSelected();
  }
  
}
