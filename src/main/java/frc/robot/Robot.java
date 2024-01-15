// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.IOException;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class Robot extends TimedRobot
{
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  private SwerveDriveSubsystem swerveDrive;
  private Joystick driverController;

  @Override
  public void robotInit()
  {
    try
    {
      m_robotContainer = new RobotContainer();
    } catch (IOException exception)
    {
      exception.printStackTrace();
    }
  }

  @Override
  public void robotPeriodic()
  {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit()
  {
  }

  @Override
  public void disabledPeriodic()
  {
  }

  @Override
  public void disabledExit()
  {
  }

  @Override
  public void autonomousInit()
  {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null)
    {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic()
  {
  }

  @Override
  public void autonomousExit()
  {
  }

  @Override
  public void teleopInit()
  {
    if (m_autonomousCommand != null)
    {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic()
  {
    // Drive the robot during teleop
    double forward = -driverController.getY();
    double strafe = driverController.getX();
    double rotation = driverController.getTwist(); // Adjust as needed for your controller

    // Drive with linear speed and rotation
    Translation2d linearSpeed = new Translation2d(forward, strafe);
    swerveDrive.drive(linearSpeed, rotation, Constants.isFieldRelative);
  }

  @Override
  public void teleopExit()
  {
  }

  @Override
  public void testInit()
  {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic()
  {
  }

  @Override
  public void testExit()
  {
  }
}
