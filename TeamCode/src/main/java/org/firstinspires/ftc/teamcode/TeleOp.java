package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Map;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Teleop", group = "")
public class TeleOp extends LinearOpMode {
    private DcMotor br;
    private DcMotor fr;
    private DcMotor bL;
    private DcMotor fL;
    private Servo lock;
    private DcMotor lift;
    private DcMotor intake;
    private DcMotor bucket;
    private DcMotor intArm;
    private Servo openSesame;

    @Override
    public void runOpMode() {
       initRobot();
        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                if (gamepad1.right_bumper) {
                    if (gamepad1.y != true) {
                        while (true) {
                                telemetry.addData("Mode", "Fast");
                                telemetry.update();
                            //gamepad 1
                                double r = Math.hypot(gamepad1.left_stick_y, gamepad1.left_stick_x);
                                double robotAngle = Math.atan2(-gamepad1.left_stick_y, -gamepad1.left_stick_x) - Math.PI / 4;
                                double rotation = gamepad1.right_stick_x;
                                final double flP = r * Math.cos(robotAngle) + rotation;
                                final double frP = r * Math.sin(robotAngle) - rotation;
                                final double blP = r * Math.sin(robotAngle) + rotation;
                                final double brP = r * Math.cos(robotAngle) - rotation;

                                fL.setPower(flP);
                                fr.setPower(frP);
                                bL.setPower(blP);
                                br.setPower(brP);
                                //Lift Lock
                                if(gamepad1.dpad_left) {
                                    lock.setPosition(0);
                                }
                                if (gamepad1.dpad_right) {
                                    lock.setPosition(1);
                                }
                                //lift
                                if (gamepad1.dpad_up) {
                                    lift.setPower(1);
                                } else {
                                    lift.setPower(0);
                                }
                                if (gamepad1.dpad_down) {
                                    lift.setPower(-1);
                                } else {
                                    lift.setPower(0);
                                }
                                if(gamepad1.y){
                                    break;
                                }

                            //gamepad 2
                                if(gamepad2.left_bumper){
                                    intake.setPower(.6);
                                } else if(gamepad2.right_bumper){
                                    intake.setPower(-.6);
                                } else {
                                    intake.setPower(0);
                                }

                                if(gamepad2.y){
                                    openSesame.setPosition(.9);
                                }else{
                                    openSesame.setPosition(0);
                                }
                                if(gamepad2.a){
                                    bucket.setPower(.3);
                                }else{
                                    bucket.setPower(Math.min(Math.max(-gamepad2.right_stick_y, -0.25), 0.25));
                                }


                            intArm.setPower(Math.min(Math.max(-gamepad2.left_stick_y, -0.35), 0.35));
                        }
                    } else break;
                }
                else if (gamepad1.left_bumper) {
                    if (gamepad1.y != true) {
                        while (true) {
                            telemetry.addData("Mode", "Slow");
                            telemetry.update();
                        //gamepad 1
                            double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
                            double robotAngle = Math.atan2(-gamepad1.left_stick_y, -gamepad1.left_stick_x) - Math.PI / 4;
                            double rotation = gamepad1.right_stick_x;
                            final double flP = .675 * r * Math.cos(robotAngle) + rotation;
                            final double frP = .675 * r * Math.sin(robotAngle) - rotation;
                            final double blP = .35 * r * Math.sin(robotAngle) + rotation;
                            final double brP = .35 * r * Math.cos(robotAngle) - rotation;

                            fL.setPower (flP);
                            fr.setPower(frP);
                            bL.setPower(blP);
                            br.setPower(brP);
                            //Lift Lock
                            if(gamepad1.dpad_left) {
                                lock.setPosition(0);
                            }
                            if (gamepad1.dpad_right) {
                                lock.setPosition(1);
                            }
                            //lift
                            if (gamepad1.dpad_up) {
                                lift.setPower(1);
                            } else {
                                lift.setPower(0);
                            }
                            if (gamepad1.dpad_down) {
                                lift.setPower(-1);
                            } else {
                                lift.setPower(0);
                            }
                            if(gamepad1.y){
                                break;
                            }
                            //gamepad 2
                            if(gamepad2.left_bumper){
                                intake.setPower(.6);
                            } else if(gamepad2.right_bumper){
                                intake.setPower(-.6);
                            } else {
                                intake.setPower(0);
                            }

                            if(gamepad2.y){
                                openSesame.setPosition(.9);
                            }else{
                                openSesame.setPosition(0);
                            }

                            if(gamepad2.a){
                                bucket.setPower(.3);
                            }else{
                                bucket.setPower(Math.min(Math.max(-gamepad2.right_stick_y, -0.25), 0.25));
                            }
                            intArm.setPower(Math.min(Math.max(-gamepad2.left_stick_y, -0.35), 0.35));
                        }
                    }
                    else
                        break;
                }
            }
        }
    }
    public void initRobot()
    {
        br = hardwareMap.dcMotor.get("br");
        fr = hardwareMap.dcMotor.get("fr");
        bL = hardwareMap.dcMotor.get("bL");
        fL = hardwareMap.dcMotor.get("fL");
        lock = hardwareMap.servo.get("lock");
        lift = hardwareMap.dcMotor.get("lift");


        intake = hardwareMap.dcMotor.get("intake");
        bucket = hardwareMap.dcMotor.get("bucket");
        intArm = hardwareMap.dcMotor.get("intArm");
        openSesame = hardwareMap.servo.get("open");

        br.setDirection(DcMotorSimple.Direction.REVERSE);
        fr.setDirection(DcMotorSimple.Direction.REVERSE);

    }
}
