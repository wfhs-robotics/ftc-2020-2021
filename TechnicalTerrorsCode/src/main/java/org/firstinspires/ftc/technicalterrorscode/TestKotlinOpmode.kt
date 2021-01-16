package org.firstinspires.ftc.technicalterrorscode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.util.ElapsedTime
import kotlin.reflect.KMutableProperty0

@TeleOp(name = "Basic: Kotlin OpMode", group = "Technical Terrors")
class TestKotlinOpmode : OpMode() {
    companion object {
        const val SERVO_MOVE_INTERVAL = 0.05
    }

    private val runtime: ElapsedTime = ElapsedTime()
    private var bot = QualifierOneBot()

    private var buttonBounces: HashMap<
            KMutableProperty0<Boolean>, Boolean> = HashMap()

    private fun detectButton(button: KMutableProperty0<Boolean>, runFunction: () -> Unit) {
        if (button.get()) {
            if (buttonBounces[button] != true) {
                runFunction()
                buttonBounces[button] = true
            }
        } else {
            buttonBounces[button] = false
        }
    }

    override fun init() {
        bot.init(hardwareMap)
        telemetry.addData("Status", "Initialized {0x%08X}", BotConstants.VERSION)
        telemetry.update()
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

        bot.leftFrontWheel.power = y + x + rx
        bot.leftBackWheel.power = y - x + rx
        bot.rightFrontWheel.power = y - x - rx
        bot.rightBackWheel.power = y + x - rx

        detectButton(gamepad1::dpad_up, {
            bot.armPosition += SERVO_MOVE_INTERVAL
        })

        detectButton(gamepad1::dpad_down, {
            bot.armPosition -= SERVO_MOVE_INTERVAL
        })

        telemetry.addData("Status", "Run Time: $runtime")
        telemetry.addData("Servos", "Position: ${bot.armPosition}")
        telemetry.update()
    }

    override fun stop() {}
}