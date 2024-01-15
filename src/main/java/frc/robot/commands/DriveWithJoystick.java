package frc.robot.commands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class DriveWithJoystick extends Command
{
    private final SwerveDriveSubsystem swerveDriveSubsystem;
    private final Joystick driverController;

    public DriveWithJoystick(SwerveDriveSubsystem swerveDriveSubsystem, Joystick driverController, boolean isFieldRelative)
    {
        this.swerveDriveSubsystem = swerveDriveSubsystem;
        this.driverController = driverController;

        // Specify subsystem dependencies (if any)
        addRequirements(swerveDriveSubsystem);
    }

    @Override
    public void initialize()
    {
        // Perform any initialization if needed
    }

    @Override
    public void execute()
    {
        // Get joystick inputs
        double joystickX = driverController.getX();
        double joystickY = -driverController.getY(); // Invert Y axis if needed
        double joystickRotation = driverController.getTwist();

        // Apply deadband and sensitivity adjustments
        joystickX = applyDeadbandAndSensitivity(joystickX);
        joystickY = applyDeadbandAndSensitivity(joystickY);
        joystickRotation = applyDeadbandAndSensitivity(joystickRotation);

        // Drives according to linear speed, rotational speed, and if field is relative (true for now)
        Translation2d translationSpeed = new Translation2d(joystickX, joystickY);
        swerveDriveSubsystem.drive(translationSpeed, joystickRotation, Constants.isFieldRelative);
    }

    private double applyDeadbandAndSensitivity(double input) {
        // Example: return (Math.abs(input) < 0.1) ? 0.0 : sensitivity * Math.pow(input, 3);
        return input;
    }

    @Override
    public void end(boolean interrupted)
    {
        // Perform any actions when the command ends
    }

    @Override
    public boolean isFinished()
    {
        // This command is intended to run continuously during teleop
        return false;
    }
}
