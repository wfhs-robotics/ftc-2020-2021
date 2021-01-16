 package org.firstinspires.ftc.robohawkscode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robohawkscode.AutonomousBase;

@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")

public class AndrewDriveMode extends OpMode {
    AutonomousBase aB = new AutonomousBase();

    public final int DEFTIMEOUT = 2000;

    boolean upright = true;

    float angleRatio = 288/360;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        aB.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        //aB.wheelMotor.setPower(1);

        telemetry.addData("ArmPos: ", aB.armMotor.getCurrentPosition());
        int currArmPos = aB.armMotor.getCurrentPosition() - 30;

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

        if(gamepad1.a == true)
            moveArm();

        if(aB.armMotor.isBusy() && DEFTIMEOUT < runtime.milliseconds() && upright && aB.armMotor.getCurrentPosition() <= 60){
            //aB.armMotor.setPower(Math.sin(angleRatio* aB.armMotor.getCurrentPosition()));
            aB.armMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            aB.armMotor.setPower(1);
        }
        else if(aB.armMotor.isBusy() && DEFTIMEOUT < runtime.milliseconds() && upright && aB.armMotor.getCurrentPosition() > 60){
            aB.armMotor.setPower(.8);
        }
        if(aB.armMotor.isBusy() && DEFTIMEOUT < runtime.milliseconds() && !upright && aB.armMotor.getCurrentPosition() >= 40){
            //aB.armMotor.setPower(Math.cos(angleRatio* aB.armMotor.getCurrentPosition()));
            aB.armMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            aB.armMotor.setPower(-1);
        }
        else if(aB.armMotor.isBusy() && DEFTIMEOUT < runtime.milliseconds() && !upright && aB.armMotor.getCurrentPosition() < 40){
            aB.armMotor.setPower(-.2);
        }

        if(DEFTIMEOUT >= runtime.milliseconds())
            aB.armMotor.setPower(0);
        aB.frontLeftDrive.setPower(turn);
        aB.frontRightDrive.setPower(-turn);
        aB.backLeftDrive.setPower(turn);
        aB.backRightDrive.setPower(-turn);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

    public void moveArm(){
        if(upright && !aB.armMotor.isBusy()){
            aB.armMotor.setTargetPosition(aB.armMotor.getCurrentPosition() + 112);
            aB.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            upright = false;
        }
        else if(!upright && !aB.armMotor.isBusy()){
            aB.armMotor.setTargetPosition(0);
            aB.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            upright = true;
        }
        runtime.reset();
    }
}
