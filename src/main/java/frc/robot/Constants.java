package frc.robot;

import com.pathplanner.lib.util.PIDConstants;

public final class Constants
{
    public static double wheelDiameter = 4;
    public static double maximumLinearVelocityMps = 5.0;
    public static double maximumRotationRateRps = Math.PI;

    // Don't mess with this!
    public static double maxModuleSpeedMps = 4.5;

    public static double joystickDeadband = 0.1;
    public static double gamepadDeadband = 0.05;
    public static boolean isFieldRelative = true;
    public static double gearRatioDrive = 8.14;
    public static double gearRatioSteer = 21.43;

    public static final class AutonConstants
    {
        public static final PIDConstants TRANSLATION_PID = new PIDConstants(0.7, 0, 0);
        public static final PIDConstants ANGLE_PID = new PIDConstants(0.4, 0, 0.01);
    }
    public static final class CameraConstants{
        public static final int fps = 10;
        public static final int width = 320;
        public static final int height = 240;

    }
}
