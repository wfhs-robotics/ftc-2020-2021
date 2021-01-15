package org.firstinspires.ftc.technicalterrorscode

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo

class QualifierOneBot {
    lateinit var leftBackWheel: DcMotor
    lateinit var rightBackWheel: DcMotor
    lateinit var leftFrontWheel: DcMotor
    lateinit var rightFrontWheel: DcMotor

    lateinit var leftArmServo: Servo
    lateinit var rightArmServo: Servo

    lateinit var collectorGear: Servo

    var armPosition: Double = 0.0
        set(value) {
            val valueFinal = value.coerceIn(BotConstants.ARM_SERVO_MIN..BotConstants.ARM_SERVO_MAX)

            this.leftArmServo.position = valueFinal
            this.rightArmServo.position = valueFinal

            field = valueFinal
        }

    private lateinit var hwMap: HardwareMap

    fun init(ahwMap: HardwareMap) {
        this.hwMap = ahwMap;

        this.leftBackWheel = this.hwMap.get(DcMotor::class.java, "left_back_wheel")
        this.rightBackWheel = this.hwMap.get(DcMotor::class.java, "right_back_wheel")
        this.leftFrontWheel = this.hwMap.get(DcMotor::class.java, "left_front_wheel")
        this.rightFrontWheel = this.hwMap.get(DcMotor::class.java, "right_front_wheel")

        this.leftBackWheel.power = 0.0
        this.rightBackWheel.power = 0.0

        this.leftFrontWheel.power = 0.0
        this.rightFrontWheel.power = 0.0

        this.leftArmServo = this.hwMap.get(Servo::class.java, "left_arm")
        this.rightArmServo = this.hwMap.get(Servo::class.java, "right_arm")
        this.collectorGear = this.hwMap.get(Servo::class.java, "collector_gear")

        this.leftArmServo.direction = Servo.Direction.REVERSE

        this.armPosition = BotConstants.INIT_ARM_SERVO_POS

        this.collectorGear.position = 0.0;
    }
}