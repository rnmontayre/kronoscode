package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class OooBaby extends LinearOpMode {
    long time = 100;
    private DcMotor br;
    private DcMotor fr;
    private DcMotor bL;
    private DcMotor fL;

    @Override
    public void runOpMode() {

        while(true){
        if (time <= 2500) {
            while (time<= 2500) {
                br = hardwareMap.dcMotor.get("br");
                fr = hardwareMap.dcMotor.get("fr");
                bL = hardwareMap.dcMotor.get("bL");
                fL = hardwareMap.dcMotor.get("fL");
                br.setDirection(DcMotorSimple.Direction.REVERSE);
                fr.setDirection(DcMotorSimple.Direction.REVERSE);
                br.setPower(.1);
                fL.setPower(.1);
                fr.setPower(.1);
                bL.setPower(.1);
                sleep(time);
                br.setPower(-.1);
                fL.setPower(-.1);
                fr.setPower(-.1);
                bL.setPower(-.1);
                sleep(time);
                time = time + 100;
            }
        } else{
            time = 100;
        }
    }
}
}
