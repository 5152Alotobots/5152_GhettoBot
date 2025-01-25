package frc.robot.subsystems.elevator.constants;

public final class ElevatorConstants {
    public static final int MOTOR_ONE_ID = 10;
//    public static final int MOTOR_TWO_ID = 11;
    public static final int DOWN_SWITCH_ID = 8;
    public static final int UP_SWITCH_ID = 6;

    //Time to get to max speed in seconds
    public static final double OPEN_LOOP_RAMP = .5;
    //Max speed of motors in open loop control; 
    public static final double OPEN_LOOP_MAX = 1;
    public static final double OPEN_LOOP_MAX_REVERSE = -1;
}
