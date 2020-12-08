package org.firstinspires.ftc.technicalterrorscode

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction
import com.qualcomm.robotcore.util.ElapsedTime
import com.qualcomm.robotcore.util.Range

@TeleOp(name = "Basic: Kotlin OpMode", group = "Technical Terrors")
class TestKotlinOpmode : OpMode() {
    private val runtime: ElapsedTime = ElapsedTime()
    private var leftDrive: DcMotor? = null
    private var rightDrive: DcMotor? = null

    var leftPower: Double = 0.00
    var rightPower: Double = 0.00

    override fun init() {
        telemetry.addData("Status", "Initialized")

        leftDrive = hardwareMap.get(DcMotor::class.java, "left_drive")
        rightDrive = hardwareMap.get(DcMotor::class.java, "right_drive")

        leftDrive!!.setDirection(Direction.FORWARD)
        rightDrive!!.setDirection(Direction.REVERSE)

        telemetry.addData("Status", "Initialized")
    }

    override fun init_loop() {}

    override fun start() {
        runtime.reset()
    }

    override fun loop() {
        leftPower  = (-gamepad1.left_stick_y).toDouble()
        rightPower = (-gamepad1.right_stick_y).toDouble()

        leftDrive!!.setPower(leftPower)
        rightDrive!!.setPower(rightPower)

        telemetry.addData("Status", "Run Time: $runtime")
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower)
    }

    override fun stop() {}
}