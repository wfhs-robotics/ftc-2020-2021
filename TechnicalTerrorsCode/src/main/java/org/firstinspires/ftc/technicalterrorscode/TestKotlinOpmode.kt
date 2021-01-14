package org.firstinspires.ftc.technicalterrorscode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime

@TeleOp(name = "Basic: Kotlin OpMode", group = "Technical Terrors")
class TestKotlinOpmode : OpMode() {
    private val runtime: ElapsedTime = ElapsedTime()
    private var bot = QualifierOneBot()

    private var buttonUpBounced = false
    private var buttonDownBounced = false

    override fun init() {
        bot.init(hardwareMap)
        telemetry.addData("Status", "Initialized ${bot.VERSION}")
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

        if (gamepad1.dpad_up && !buttonUpBounced) {
            bot.armPosition += 0.05
            buttonUpBounced = true
        }
        if (!gamepad1.dpad_up) {
            buttonUpBounced = false
        }

        if (gamepad1.dpad_down && !buttonDownBounced) {
            bot.armPosition -= 0.05
            buttonDownBounced = true
        }
        if (!gamepad1.dpad_down) {
            buttonDownBounced = false
        }

        telemetry.addData("Status", "Run Time: $runtime")
        telemetry.addData("Servos", "Position: ${bot.armPosition}")
        telemetry.update()
    }

    override fun stop() {}
}