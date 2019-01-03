package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class craterAuto extends LinearOpMode {

    private DcMotor br;
    private DcMotor fr;
    private DcMotor bL;
    private DcMotor fL;
    private Servo lock;
    private DcMotor lift;
    private GoldAlignDetector detector;

    @Override
    public void runOpMode() {

        double middleLow = 545;
        double middleHigh = 625;
        double leftLow = 225;
        double leftHigh = 310;

        initRobot();

        waitForStart();
        lift.setPower(0.3);
        sleep(2500);
        lift.setPower(0);
        lock.setPosition(1);
        sleep(1000);
        fr.setPower(0);
        br.setPower(0);
        bL.setPower(0);
        fL.setPower(0);
        sleep(500);
        if (detector.getXPosition() >= middleLow && detector.getXPosition() <= middleHigh) {
            telemetry.addData("Order" , "Middle");
            telemetry.update();
            rotateCW(800);
            driveForward(2500);
            stopRobot(500);
            driveBack(1250);
            stopRobot(500);
            transL(1000);
            rotateCCW(680);
            driveForward(1000);
            rotateCCW(680);


        } else if (detector.getXPosition() >= leftLow && detector.getXPosition() <= leftHigh) {
            telemetry.addData("Order" , "Left");
            telemetry.update();
            rotateCW(850);

        } else if (detector.isFound() != true || detector.getXPosition() > 635) {
            telemetry.addData("Order" , "Right");
            telemetry.update();
            rotateCW(850);
        } else {
            telemetry.addData("Order" , "Default");
            telemetry.update();
            rotateCW(810);
            driveForward(2500);
            stopRobot(500);
            driveBack(1250);
            stopRobot(500);
            transL(1000);
            rotateCCW(680);
            driveForward(1000);
            rotateCCW(680);
        }
        detector.disable();
    }




    public void initRobot()
    {
        br = hardwareMap.dcMotor.get("br");
        fr = hardwareMap.dcMotor.get("fr");
        bL = hardwareMap.dcMotor.get("bL");
        fL = hardwareMap.dcMotor.get("fL");
        lock = hardwareMap.servo.get("lock");
        lift = hardwareMap.dcMotor.get("lift");

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

        detector = new GoldAlignDetector(); // Create the detector
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance()); // Initialize detector with app context and camera
        detector.useDefaults(); // Set detector to use default settings

        detector.downscale = 0.4; // How much to downscale the input frames

        // Optional tuning
        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.001;

        detector.ratioScorer.weight = 15;
        detector.ratioScorer.perfectRatio = 1.0;

        detector.enable();

        lock.setPosition(0);
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
