package org.firstinspires.ftc.robohawkscode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class AutonomousBase {

    public DcMotor frontLeftDrive   = null;
    public DcMotor frontRightDrive  = null;
    public DcMotor backLeftDrive   = null;
    public DcMotor backRightDrive  = null;

    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    AutonomousBase() {

    }

    public void init(HardwareMap ahwMap){
        hwMap = ahwMap;

        frontLeftDrive  = hwMap.get(DcMotor.class, "left_drive");
        frontRightDrive = hwMap.get(DcMotor.class, "right_drive");
        backLeftDrive.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }
}
