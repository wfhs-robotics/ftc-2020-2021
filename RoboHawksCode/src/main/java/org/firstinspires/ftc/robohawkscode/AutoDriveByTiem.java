package org.firstinspires.ftc.robohawkscode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robohawkscode.AndrewAutonomous;
import org.firstinspires.ftc.robohawkscode.AutonomousBase;

@TeleOp(name="Basic: Autonomous", group="Autonomous")
public class AutoDriveByTiem extends LinearOpMode {

    /* Declare OpMode members. */
    AutonomousBase         aB   = new AutonomousBase();   // Use a Pushbot's hardware
    private ElapsedTime     runtime = new ElapsedTime();


    static final double     FORWARD_SPEED = 0.6;
    static final double     TURN_SPEED    = 0.5;

    @Override
    public void runOpMode() {
        aB.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way

        // Step 1:  Drive forward for 3 seconds
        aB.frontLeftDrive.setPower(FORWARD_SPEED);
        aB.frontRightDrive.setPower(FORWARD_SPEED);
        aB.backLeftDrive.setPower(FORWARD_SPEED);
        aB.backRightDrive.setPower(FORWARD_SPEED);
        runtime.reset();
        sleep(1500);
        telemetry.addData("Say: ", "Job's done");
        aB.frontLeftDrive.setPower(0);
        aB.frontRightDrive.setPower(0);
        aB.backLeftDrive.setPower(0);
        aB.backRightDrive.setPower(0);
    }
}
