package frc.robot.subsystems;

import java.io.File;
import java.io.IOException;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import swervelib.SwerveDrive;
import swervelib.parser.SwerveParser;
import swervelib.math.SwerveMath;

public class SwerveDriveSubsystem extends SubsystemBase
{
    public SwerveDrive swerveDrive;

    public SwerveDriveSubsystem() 
    {
        // Swerve drive initiation according to YAGSL
        try
        {
            double maximumSpeed = Units.feetToMeters(Constants.maxSpeedMps);
            File swerveJsonDirectory = new File(Filesystem.getDeployDirectory(), "swerve");
            double driveConversionFactor = SwerveMath.calculateMetersPerRotation(Units.inchesToMeters(Constants.wheelDiameter),Constants.gearRatioDrive);
            double steeringConversionFactor = SwerveMath.calculateDegreesPerSteeringRotation(Constants.gearRatioSteer, Constants.steeringEncoderResolution);
            swerveDrive = new SwerveParser(swerveJsonDirectory).createSwerveDrive(maximumSpeed, steeringConversionFactor, driveConversionFactor);
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }

    public void drive(Translation2d translation, double rotation, boolean fieldRelative)
    {
        // Open loop is disabled since it shouldn't be used most of the time.
        swerveDrive.drive(translation, rotation, fieldRelative, false);

    }

    // Drive the robot given a chassis field oriented velocity.
    public void driveFieldOriented(ChassisSpeeds velocity)
    {
        swerveDrive.driveFieldOriented(velocity);
    }

    // Drive according to the chassis robot oriented velocity.
    public void drive(ChassisSpeeds velocity)
    {
        swerveDrive.drive(velocity);
    }

}
