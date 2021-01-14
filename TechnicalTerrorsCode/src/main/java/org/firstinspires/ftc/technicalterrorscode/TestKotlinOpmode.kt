package org.firstinspires.ftc.technicalterrorscode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime

@TeleOp(name = "Basic: Kotlin OpMode", group = "Technical Terrors")
class TestKotlinOpmode : OpMode() {
    private val runtime: ElapsedTime = ElapsedTime()
    private var bot = QualifierOneBot()

    override fun init() {
        telemetry.addData("Status", "Initialized")
        bot.init(hardwareMap)
    }

    override fun init_loop() {}

    override fun start() {
        runtime.reset()
    }

    override fun loop() {
        // stolen code from some tutorial 🤷‍
        val y = -gamepad1.left_stick_y.toDouble()

        val x = gamepad1.left_stick_x.toDouble()
        val rx = gamepad1.right_stick_x.toDouble()

        bot.leftFrontWheel!!.power = y + x + rx
        bot.leftBackWheel!!.power = y - x + rx
        bot.rightFrontWheel!!.power = y - x - rx
        bot.rightBackWheel!!.power = y + x - rx

        telemetry.addData("Status", "Run Time: $runtime")
        telemetry.update()
    }

    override fun stop() {}
}