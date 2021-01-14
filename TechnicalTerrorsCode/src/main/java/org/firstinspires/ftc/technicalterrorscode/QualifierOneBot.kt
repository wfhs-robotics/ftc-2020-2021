package org.firstinspires.ftc.technicalterrorscode

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo

class QualifierOneBot {
    var leftBackWheel: DcMotor? = null
    var rightBackWheel: DcMotor? = null
    var leftFrontWheel: DcMotor? = null
    var rightFrontWheel: DcMotor? = null

    var leftArmServo: Servo? = null
    var rightArmServo: Servo? = null

    var collectorGear: Servo? = null

    private final val INIT_SERVO_POS = 0.5

    private var hwMap: HardwareMap? = null

    fun init(ahwMap: HardwareMap) {
        this.hwMap = ahwMap;

        this.leftBackWheel = this.hwMap!!.get(DcMotor::class.java, "left_back_wheel")
        this.rightBackWheel = this.hwMap!!.get(DcMotor::class.java, "right_back_wheel")
        this.leftFrontWheel = this.hwMap!!.get(DcMotor::class.java, "left_front_wheel")
        this.rightFrontWheel = this.hwMap!!.get(DcMotor::class.java, "right_front_wheel")

        this.leftBackWheel?.power = 0.0
        this.rightBackWheel?.power = 0.0

        this.leftFrontWheel?.power = 0.0
        this.rightFrontWheel?.power = 0.0

        this.leftArmServo = this.hwMap!!.get(Servo::class.java, "left_arm")
        this.rightArmServo = this.hwMap!!.get(Servo::class.java, "right_arm")
        this.collectorGear = this.hwMap!!.get(Servo::class.java, "collector_gear")

        this.set_arm_position(INIT_SERVO_POS)

        this.collectorGear?.position = 0.0;
    }

    fun set_arm_position(position: Double) {
        this.leftArmServo?.position = position;
        this.rightArmServo?.position = position;
    }
}