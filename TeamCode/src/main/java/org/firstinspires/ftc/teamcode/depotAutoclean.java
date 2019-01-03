package org.firstinspires.ftc.teamcode;


import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@Disabled
public class depotAutoclean extends LinearOpMode {

    private DcMotor br;
    private DcMotor fr;
    private DcMotor bL;
    private DcMotor fL;
    private SamplingOrderDetector detector;
    private Servo teamMarker;
    private Servo lock;
    private DcMotor lift;

    @Override
    public void runOpMode() {
        br = hardwareMap.dcMotor.get("br");
        fr = hardwareMap.dcMotor.get("fr");
        bL = hardwareMap.dcMotor.get("bL");
        fL = hardwareMap.dcMotor.get("fL");
        teamMarker = hardwareMap.servo.get("tM");
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

        detector = new SamplingOrderDetector(); // Create the detector
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

        initRobot();
        initDetect();
        waitForStart();
        lift.setPower(.3);
        sleep(2000);
        lock.setPosition(1);
        sleep(1500);
        BackwardDrive(1, 3000);
        if(detector.getLastOrder() == SamplingOrderDetector.GoldLocation.CENTER){
            fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //drive forward
            ForwardDrive(1, 20000);
            fr.setPower(0.3);
            br.setPower(0.3);
            fL.setPower(0.3);
            bL.setPower(0.3);
            fr.setTargetPosition(2880);
            br.setTargetPosition(2880);
            fL.setTargetPosition(2880);
            bL.setTargetPosition(2880);
            sleep(5500);
            Stop(500);
            teamMarker.setPosition(1);

        }
    }
    //methods
    public void initRobot()
    {
        br = hardwareMap.dcMotor.get("br");
        fr = hardwareMap.dcMotor.get("fr");
        bL = hardwareMap.dcMotor.get("bL");
        fL = hardwareMap.dcMotor.get("fL");
        teamMarker = hardwareMap.servo.get("tM");
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
        lock.setPosition(0);
    }

    public void initDetect()
    {
        detector = new SamplingOrderDetector(); // Create the detector
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
    }

    public void ForwardDrive(double power, long time)
    {
        br.setPower(power);
        fr.setPower(power);
        bL.setPower(power);
        fL.setPower(power);
        sleep(time);
    }
    public void BackwardDrive (double power, long time)
    {
        br.setPower(-power);
        fr.setPower(-power);
        bL.setPower(-power);
        fL.setPower(-power);
        sleep(time);
    }

    public void Stop(long time)
    {
        br.setPower(0);
        fr.setPower(0);
        bL.setPower(0);
        fL.setPower(0);
        sleep(time);
    }
}

