package frc.robot.commands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class DriveWithGamepad extends Command
{
    private final SwerveDriveSubsystem swerveDriveSubsystem;
    private final XboxController driverController;
    private final SendableChooser<Boolean> driveOrientationChooser;

    public DriveWithGamepad(SwerveDriveSubsystem swerveDriveSubsystem, XboxController driverController, SendableChooser<Boolean> driveOrientationChooser)
    {
        this.swerveDriveSubsystem = swerveDriveSubsystem;
        this.driverController = driverController;
        this.driveOrientationChooser = driveOrientationChooser;

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
        // Get gamePad inputs
        double leftX = driverController.getLeftX();
        double leftY = -driverController.getLeftY();
        double rightX = driverController.getRightX();
        double rightY = -driverController.getRightY();
       

        // Apply deadband and sensitivity adjustments
        leftX = applyDeadbandAndSensitivity(leftX);
        leftY = applyDeadbandAndSensitivity(leftY);
        rightX = applyDeadbandAndSensitivity(rightX);
        rightY = applyDeadbandAndSensitivity(rightY);

        // Drives according to linear speed, rotational speed, and if field is relative (true for now)
        double speedX = leftY * Constants.maximumLinearVelocityMps;
        double speedY = -leftX * Constants.maximumLinearVelocityMps;
        double rotationRate = -rightX * Constants.maximumRotationRateRps;
        Translation2d translationSpeed = new Translation2d(speedX, speedY);
        swerveDriveSubsystem.drive(translationSpeed, rotationRate, driveOrientationChooser.getSelected());
    }

    private double applyDeadbandAndSensitivity(double input) {
        return (Math.abs(input) < Constants.gamepadDeadband) ? 0.0 : Math.pow(input, 2) * Math.signum(input);
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
