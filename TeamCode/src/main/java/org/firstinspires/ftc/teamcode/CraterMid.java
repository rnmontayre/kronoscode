package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class CraterMid extends LinearOpMode {
    private DcMotor br;
    private DcMotor fr;
    private DcMotor bL;
    private DcMotor fL;
    private Servo lock;
    private DcMotor lift;
    private DcMotor bucket;


    @Override
    public void runOpMode()
    {
        br = hardwareMap.dcMotor.get("br");
        fr = hardwareMap.dcMotor.get("fr");
        bL = hardwareMap.dcMotor.get("bL");
        fL = hardwareMap.dcMotor.get("fL");
        lock = hardwareMap.servo.get("lock");
        lift = hardwareMap.dcMotor.get("lift");
        bucket = hardwareMap.dcMotor.get("bucket");

        br.setDirection(DcMotorSimple.Direction.REVERSE);
        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();
        /*lift.setPower(0.2);
        sleep(2250);
        lift.setPower(0);
        lock.setPosition(1);
        sleep(1000);
        fr.setPower(0);
        br.setPower(0);
        bL.setPower(0);
        fL.setPower(0);
        sleep(500);
        //read
        rotateCW(600);*/
        driveForward(2750);
        stopRobot(500);
        driveBack(1300);
        stopRobot(500);
        transL(1500);
        rotateCCW(150);
        driveForward(3250);
        rotateCW(1550);
        umiBack(2200);
        //driveBack(5250);
        stopRobot(500);
        bucket.setPower(.3);
        sleep(1000);
        bucket.setPower(-.3);
        sleep(750);
        bucket.setPower(.1);
        sleep(250);
        umiForward(2400);
        //driveForward(4500);
        rotateCCW(300);
        umiForward(100);
       // driveForward(800);
        stopRobot(1000);
    }

    public void rotateCW(long time)
    {
        br.setPower(-.3);
        fr.setPower(-.3);
        fL.setPower(.3);
        bL.setPower(.3);
        sleep(time);
    }
    public void rotateCCW(long time)
    {
        br.setPower(.3);
        fr.setPower(.3);
        fL.setPower(-.3);
        bL.setPower(-.3);
        sleep(time);
    }

    public void driveForward(long time)
    {
        br.setPower(.3);
        fr.setPower(.3);
        fL.setPower(.3);
        bL.setPower(.3);
        sleep(time);
    }
    public void umiBack(long time)
    {
        br.setPower(-.7);
        fr.setPower(-.7);
        fL.setPower(-.7);
        bL.setPower(-.7);
        sleep(time);
    }
    public void umiForward(long time)
    {
        br.setPower(.7);
        fr.setPower(.7);
        fL.setPower(.7);
        bL.setPower(.7);
        sleep(time);
    }
    public void driveBack(long time)
    {
        br.setPower(-.3);
        fr.setPower(-.3);
        fL.setPower(-.3);
        bL.setPower(-.3);
        sleep(time);
    }

    public void stopRobot(long time)
    {
        br.setPower(0);
        fr.setPower(0);
        fL.setPower(0);
        bL.setPower(0);
        sleep(time);
    }
    public void transR (long time)
    {
        fr.setPower(.3);
        br.setPower(-.3);
        bL.setPower(.3);
        fL.setPower(-.3);
        sleep(time);
    }
    public void transL (long time)
    {
        fr.setPower(-.3);
        br.setPower(.3);
        bL.setPower(-.3);
        fL.setPower(.3);
        sleep(time);
    }
}
