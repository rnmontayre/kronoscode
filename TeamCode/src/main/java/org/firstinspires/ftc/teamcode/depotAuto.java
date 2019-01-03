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
public class depotAuto extends LinearOpMode {

    private DcMotor br;
    private DcMotor fr;
    private DcMotor bL;
    private DcMotor fL;
    private Servo lock;
    private DcMotor lift;
    private GoldAlignDetector detector;

    @Override
    public void runOpMode() {
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
        double middleLow = 306;
        double middleHigh= 590;
        double rightLow = 0;
        double rightHigh = 200;
        waitForStart();
        lift.setPower(0.3);
        sleep(2500);
        lift.setPower(0);
        lock.setPosition(1);
        sleep(1000);
        for (int count = 0; count < 1; count++) {
            fr.setPower(-0.3);
            br.setPower(-0.3);
            bL.setPower(0.3);
            fL.setPower(0.3);
        }
        sleep(940);
        fr.setPower(0);
        br.setPower(0);
        bL.setPower(0);
        fL.setPower(0);
        sleep(500);
        if (detector.getXPosition() >= middleLow && detector.getXPosition() < middleHigh && detector.isFound() == true) {
            telemetry.addData("Order" , "Middle");
            telemetry.update();
            fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //drive forward
            fr.setPower(0.3);
            br.setPower(0.3);
            fL.setPower(0.3);
            bL.setPower(0.3);
            fr.setTargetPosition(2880);
            br.setTargetPosition(2880);
            fL.setTargetPosition(2880);
            bL.setTargetPosition(2880);
            sleep(5500);
            //stop
            fr.setPower(0);
            br.setPower(0);
            fL.setPower(0);
            bL.setPower(0);
            sleep(500);
            //drop marker
            sleep(1000);
            //turn around
            fr.setPower(-0.3);
            br.setPower(-0.3);
            fL.setPower(0.3);
            bL.setPower(0.3);
            sleep(1500);
            //drive to wall
            fr.setPower(-0.3);
            br.setPower(-0.3);
            fL.setPower(-0.3);
            bL.setPower(-0.3);
            sleep(2000);
            //turn again
            fr.setPower(0.3);
            br.setPower(0.3);
            fL.setPower(-0.3);
            bL.setPower(-0.3);
            sleep(850);
            //drive to crater
            fr.setPower(-0.3);
            br.setPower(-0.3);
            fL.setPower(-0.3);
            bL.setPower(-0.3);
            sleep(6000);
        } else if (detector.getXPosition() >= rightLow && detector.getXPosition() < rightHigh && detector.isFound() == true) {
            telemetry.addData("Order" , "Left");
            telemetry.update();
            fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //drive forward
            fr.setPower(0.3);
            br.setPower(0.3);
            fL.setPower(0.3);
            bL.setPower(0.3);
            fr.setTargetPosition(2880);
            br.setTargetPosition(2880);
            fL.setTargetPosition(2880);
            bL.setTargetPosition(2880);
            sleep(1750);
            //stop
            fr.setPower(0);
            br.setPower(0);
            fL.setPower(0);
            bL.setPower(0);
            sleep(500);
            //translate left
            fr.setPower(-0.3);
            br.setPower(0.3);
            fL.setPower(0.3);
            bL.setPower(-0.3);
            sleep(1100);
            //drive forward
            fr.setPower(0.3);
            br.setPower(0.3);
            fL.setPower(0.3);
            bL.setPower(0.3);
            sleep(2000);
            //turn
            fr.setPower(-0.3);
            br.setPower(-0.3);
            fL.setPower(0.3);
            bL.setPower(0.3);
            sleep(750);
            //drive to depot
            fr.setPower(0.3);
            br.setPower(0.3);
            fL.setPower(0.3);
            bL.setPower(0.3);
            sleep(2000);
            //stop
            fr.setPower(0);
            br.setPower(0);
            fL.setPower(0);
            bL.setPower(0);
            sleep(1000);
            //drop team marker
            sleep(1000);
            //turn
            fr.setPower(-0.3);
            br.setPower(-0.3);
            fL.setPower(0.3);
            bL.setPower(0.3);
            sleep(490);
            //drive to crater
            fr.setPower(-0.3);
            br.setPower(-0.3);
            fL.setPower(-0.3);
            bL.setPower(-0.3);
            sleep(7000);
        } else if (detector.isFound() != true || detector.getXPosition() > 600) {
            telemetry.addData("Order" , "Right");
            telemetry.update();
            //drive forward
            fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fr.setPower(0.3);
            br.setPower(0.3);
            fL.setPower(0.3);
            bL.setPower(0.3);
            sleep(1800);
            //stop
            fr.setPower(0);
            br.setPower(0);
            fL.setPower(0);
            bL.setPower(0);
            sleep(500);
            //translate right
            fr.setPower(0.3);
            br.setPower(-0.3);
            fL.setPower(-0.3);
            bL.setPower(0.3);
            sleep(750);
            //drive forward
            fr.setPower(0.3);
            br.setPower(0.3);
            fL.setPower(0.3);
            bL.setPower(0.3);
            sleep(2400);
            //turn to depot
            fr.setPower(0.3);
            br.setPower(0.3);
            fL.setPower(-0.3);
            bL.setPower(-0.3);
            sleep(1000);
            //drive to depot
            fr.setPower(0.3);
            br.setPower(0.3);
            fL.setPower(0.3);
            bL.setPower(0.3);
            sleep(3000);
            //drop team marker
            fr.setPower(0);
            br.setPower(0);
            fL.setPower(0);
            bL.setPower(0);
            sleep(1000);
            //back up
            fr.setPower(-0.3);
            br.setPower(-0.3);
            fL.setPower(-0.3);
            bL.setPower(-0.3);
            sleep(1000);
            //turn
            fr.setPower(0.3);
            br.setPower(0.3);
            fL.setPower(-0.3);
            bL.setPower(-0.3);
            sleep(500);
            //drive to wall
            fr.setPower(0.3);
            br.setPower(0.3);
            fL.setPower(0.3);
            bL.setPower(0.3);
            sleep(2000);
            ///turn towards crater
            fr.setPower(0.3);
            br.setPower(0.3);
            fL.setPower(-0.3);
            bL.setPower(-0.3);
            sleep(750);
            //drive to crater
            fr.setPower(0.3);
            br.setPower(0.3);
            fL.setPower(0.3);
            bL.setPower(0.3);
            sleep(6000);
        } else {
            telemetry.addData("Order" , "Default");
            telemetry.update();
            fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            fr.setPower(0.3);
            br.setPower(0.3);
            fL.setPower(0.3);
            bL.setPower(0.3);
            fr.setTargetPosition(2880);
            br.setTargetPosition(2880);
            fL.setTargetPosition(2880);
            bL.setTargetPosition(2880);
            sleep(5500);
            fr.setPower(0);
            br.setPower(0);
            fL.setPower(0);
            bL.setPower(0);
            sleep(500);
            sleep(1000);
            fr.setPower(-0.3);
            br.setPower(-0.3);
            fL.setPower(0.3);
            bL.setPower(0.3);
            sleep(1500);
            fr.setPower(-0.3);
            br.setPower(-0.3);
            fL.setPower(-0.3);
            bL.setPower(-0.3);
            sleep(2000);
            fr.setPower(0.3);
            br.setPower(0.3);
            fL.setPower(-0.3);
            bL.setPower(-0.3);
            sleep(850);
            fr.setPower(-0.3);
            br.setPower(-0.3);
            fL.setPower(-0.3);
            bL.setPower(-0.3);
            sleep(6000);

        }
        detector.disable();
    }
}
