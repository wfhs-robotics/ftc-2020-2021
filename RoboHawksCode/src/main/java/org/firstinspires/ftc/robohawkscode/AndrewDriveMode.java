package org.firstinspires.ftc.robohawkscode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robohawkscode.AutonomousBase;

@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")
@Disabled
class AndrewDriveMode extends LinearOpMode {
    AutonomousBase aB = new AutonomousBase();

    @Override
    public void runOpMode() throws InterruptedException {

        aB.frontLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        aB.backLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        aB.frontRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        aB.backRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        while(opModeIsActive()){
            double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            final double v1 = r * Math.cos(robotAngle) + rightX;
            final double v2 = r * Math.sin(robotAngle) - rightX;
            final double v3 = r * Math.sin(robotAngle) + rightX;
            final double v4 = r * Math.cos(robotAngle) - rightX;

            aB.frontLeftDrive.setPower(v1);
            aB.frontRightDrive.setPower(v2);
            aB.backLeftDrive.setPower(v3);
            aB.backRightDrive.setPower(v4);

            double turn = gamepad1.right_stick_x;

            aB.frontLeftDrive.setPower(turn);
            aB.frontRightDrive.setPower(-turn);
            aB.backLeftDrive.setPower(turn);
            aB.backRightDrive.setPower(-turn);
        }
    }
}
