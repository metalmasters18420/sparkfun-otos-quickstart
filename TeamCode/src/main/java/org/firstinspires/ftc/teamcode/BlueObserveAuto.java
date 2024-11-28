package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

// RR-specific imports

//import org.firstinspires.ftc.teamcode.AutoHardware.AutoArm;


@Config
@Autonomous(name="Blue Observe Auto", group = "Auto")
public class BlueObserveAuto extends LinearOpMode {
    @Override
    public void runOpMode() {
       // Pose2d RedBucketPose = new Pose2d(12, -60, Math.toRadians(90));
        //Pose2d RedObservePose = new Pose2d(-18, -60, Math.toRadians(90));
        //Pose2d BlueBucketPose = new Pose2d(12, 60, Math.toRadians(-90));
        Pose2d BlueObservePose = new Pose2d(-18, 60, Math.toRadians(-90));

        Vector2d DEEP_END_POINT_RED = new Vector2d(60,-60);
        Vector2d SHALLOW_END_POINT_RED = new Vector2d(48,-60);
        Vector2d DEEP_END_POINT_BLUE = new Vector2d(-60, 60);
        Vector2d SHALLOW_END_POINT_BLUE = new Vector2d(-48, 50);

        //MecanumDrive drive = new MecanumDrive(hardwareMap, BlueObservePose);
        //AutoArm arm = new AutoArm(hardwareMap);
        //AutoArm claw = new AutoArm(hardwareMap);
        hwRobot robot = new hwRobot();
        robot.init(hardwareMap);

        //Claw claw = new Claw(hardwareMap);
        //Lift lift = new Lift(hardwareMap);
        waitForStart();

        TrajectoryActionBuilder DriveToSubmersible = robot.drive.actionBuilder(BlueObservePose)//.setTangent(Math.toRadians(180))
                .strafeToSplineHeading(new Vector2d(11,-40),Math.toRadians(270))
                //.turn(Math.toRadians(180))
                .lineToY(-32.5)
        .endTrajectory();
        TrajectoryActionBuilder Observesample1part1 = DriveToSubmersible.fresh()//.setTangent(Math.toRadians(180))
                //.waitSeconds(2)
                .lineToY(-35)
                .setTangent(Math.toRadians(0))
                .strafeToSplineHeading(new Vector2d(28, -40),Math.toRadians(40))
                .endTrajectory();
        TrajectoryActionBuilder Observesample1part2 = DriveToSubmersible.fresh()//.setTangent(Math.toRadians(180))
                .turnTo(Math.toRadians(-40))
                .endTrajectory();
        TrajectoryActionBuilder Observesample2part1 = Observesample1part2.fresh()//.setTangent(Math.toRadians(180))
                .strafeToSplineHeading(new Vector2d(38, -40),Math.toRadians(40))
                .endTrajectory();
        TrajectoryActionBuilder Observesample2part2 = Observesample2part1.fresh()//.setTangent(Math.toRadians(180))
                .turnTo(Math.toRadians(-40))
                .endTrajectory();
        TrajectoryActionBuilder Observesample3part1 = Observesample2part2.fresh()//.setTangent(Math.toRadians(180))
                .strafeToSplineHeading(new Vector2d(48, -40),Math.toRadians(40))
                .endTrajectory();
        TrajectoryActionBuilder Observesample3part2 = Observesample3part1.fresh()//.setTangent(Math.toRadians(180))
                .turnTo(Math.toRadians(-40))
                .endTrajectory();
        TrajectoryActionBuilder Drivewall = Observesample3part2.fresh()//.setTangent(Math.toRadians(180))
                .strafeToSplineHeading(new Vector2d(47, SHALLOW_END_POINT_RED.y),Math.toRadians(90))
                .setTangent(Math.toRadians(90))
                .lineToY(SHALLOW_END_POINT_RED.y)
                .endTrajectory();
        TrajectoryActionBuilder Scorespecimen1 = Drivewall.fresh()//.setTangent(Math.toRadians(180))
                .strafeToSplineHeading(new Vector2d(11,-40),Math.toRadians(270))
                .setTangent(Math.toRadians(130))
                .lineToY(-32.5)
        .endTrajectory();
        TrajectoryActionBuilder Scorespecimen2part1 = Scorespecimen1.fresh()//.setTangent(Math.toRadians(180))
                .strafeToSplineHeading(new Vector2d(47, SHALLOW_END_POINT_RED.y),Math.toRadians(90))
                .endTrajectory();
        TrajectoryActionBuilder Scorespecimen2part2 = Scorespecimen2part1.fresh()//.setTangent(Math.toRadians(180))
                .strafeToSplineHeading(new Vector2d(11,-40),Math.toRadians(270))
                .setTangent(Math.toRadians(140))
                .lineToY(-32.5)
                .endTrajectory();
        TrajectoryActionBuilder Scorespecimen3part1 = Scorespecimen2part2.fresh()//.setTangent(Math.toRadians(180))
                .strafeToSplineHeading(new Vector2d(47, SHALLOW_END_POINT_RED.y),Math.toRadians(90))
                .endTrajectory();
        TrajectoryActionBuilder Scorespecimen3part2 = Scorespecimen3part1.fresh()//.setTangent(Math.toRadians(180))
                .strafeToSplineHeading(new Vector2d(11,-40),Math.toRadians(270))
                .setTangent(Math.toRadians(150))
                .lineToY(-32.5)
                .endTrajectory();
        TrajectoryActionBuilder Park = Scorespecimen3part2.fresh()
        .strafeToSplineHeading(new Vector2d(62, -62),Math.toRadians(270))
                .endTrajectory();

//        TrajectoryActionBuilder DriveToSubmerse = drive.actionBuilder(BlueObservePose)
//                .setTangent(Math.atan((26-60)/((-11)-(-18))))
//                .lineToXSplineHeading(-11, Math.toRadians(-90)); //sub

//        TrajectoryActionBuilder DriveToSpecimen = DriveToSubmerse.fresh()
//                .setTangent(Math.toRadians(90))
//                .lineToY(35)
//                .setTangent(Math.toRadians(-180))
//                .splineTo(new Vector2d(-40,5),Math.toRadians(270)) //start pushing
//                .setTangent(Math.toRadians(0))
//                .lineToX(-50)
//                .setTangent(Math.toRadians(90))
//                .lineToY(SHALLOW_END_POINT_BLUE.y)
//                .setTangent(Math.toRadians(90))
//                .lineToY(5)
//                .setTangent(Math.toRadians(0))
//                .lineToX(-60)
//                .setTangent(Math.toRadians(90))
//                .lineToY(SHALLOW_END_POINT_BLUE.y)   // psuh done
//                .setTangent(Math.toRadians(90))
//                .lineToY(48)
//                .turn(Math.toRadians(-90));
//                .lineToXSplineHeading(-60, Math.toRadians(90));
//                .waitSeconds(1);

//        TrajectoryActionBuilder DriveToWall = DriveToSpecimen.fresh()
//                .setTangent(Math.toRadians(90))
//                .lineToY(52);  //52,44

//                .turn(Math.toRadians(90));
//                .lineToYSplineHeading(55, Math.toRadians(90));
//                .setTangent(Math.toRadians(270))
//                .lineToY(55)               //     -58,48 -> -58,55
//                .turn(Math.toRadians(90));
//                .waitSeconds(3);


//        TrajectoryActionBuilder Cycle = DriveToWall.fresh()
//                .setTangent(-0.2449) //Math.atan((52-55)/((0)-(-60))))
//                .lineToXSplineHeading(0, Math.toRadians(-90))     // -60,55 -> -11,28
//                .setTangent(Math.toRadians(90))
//                .lineToY(28);


//        TrajectoryActionBuilder IntakeIt = Cycle.fresh()
//                .setTangent(Math.toRadians(90))
//                .lineToY(26)
//                .setTangent(Math.toRadians(90))
//                .lineToY(35)
//                .setTangent(Math.toRadians(0))
//                .lineToXSplineHeading(-60, Math.toRadians(90));
////                .waitSeconds(1);          // -11,35 -> -60,48
//
//
//        TrajectoryActionBuilder DriveToWall2 = IntakeIt.fresh()
//                .setTangent(Math.toRadians(90))
//                .lineToY(55);                    // -60,48 -> -60,55


//        TrajectoryActionBuilder Cycle2 = DriveToWall2.fresh()
//                .setTangent(-.32175)      //Math.atan((35-55)/((-11)-(-60))))
//                .lineToXSplineHeading(-11, Math.toRadians(-90))
//                .setTangent(Math.toRadians(90))
//                .lineToY(26);           // -60,55 -> -11,28


//        TrajectoryActionBuilder Park = Cycle.fresh()
//                .setTangent(Math.toRadians(90))
//                .lineToY(35)
////                .setTangent(-0.57)      //Math.atan((60-35)/((-50)-(-11))))
////                .lineToXSplineHeading(-50, Math.toRadians(-90));        // -11,28 -> -50,60
//                .splineTo(new Vector2d(-56,56), Math.toRadians(90));


        Action drivetosubmersible = DriveToSubmersible.build();

        Action observesample1part1 = Observesample1part1.build();

        Action observesample1part2 = Observesample1part2.build();

        Action observesample2part1 = Observesample2part1.build();

        Action observesample2part2 = Observesample2part2.build();

        Action observesample3part1 = Observesample3part1.build();

        Action observesample3part2 = Observesample3part2.build();

        Action drivewall = Drivewall.build();

        Action scorespecimen1 = Scorespecimen1.build();

        Action scorespecimen2part1 = Scorespecimen2part1.build();

        Action scorespecimen2part2 = Scorespecimen2part2.build();

        Action scorespecimen3part1 = Scorespecimen3part1.build();

        Action scorespecimen3part2 = Scorespecimen3part2.build();

        Action park = Park.build();


        Actions.runBlocking(
                new SequentialAction(
                        drivetosubmersible,
                        robot.preparetoscore(),
                        robot.scorespecimen(),
                        robot.clawopen(),
                        observesample1part1,
                        robot.horizextend(),
                        robot.iclawclose(),
                        observesample1part2,
                        robot.iclawopen(),
                        observesample2part1,
                        robot.iclawclose(),
                        observesample2part2,
                        robot.iclawopen(),
                        observesample3part1,
                        robot.iclawclose(),
                        observesample3part2,
                        robot.iclawopen(),
                        robot.horizretract(),
                        drivewall,
                        robot.wall(),
                        robot.clawclose(),
                        scorespecimen1,
                        robot.preparetoscore(),
                        robot.scorespecimen(),
                        robot.clawopen(),
                        scorespecimen2part1,
                        robot.wall(),
                        robot.clawclose(),
                        scorespecimen2part2,
                        robot.preparetoscore(),
                        robot.scorespecimen(),
                        robot.clawopen(),
                        scorespecimen3part1,
                        robot.wall(),
                        robot.clawclose(),
                        scorespecimen3part2,
                        robot.preparetoscore(),
                        robot.scorespecimen(),
                        robot.clawopen(),
                        park,
                        //claw.HBPost(),
                        //DriveSpecimen,
                        //claw.Wall(),
                        //new SleepAction(1),
                        //DriveWall,
                        //new SleepAction(.5),
                        //claw.CloseClaw(),
                        //new SleepAction(1),
                        //claw.HBPre(),
                        //claw.flipIn(),
                        //Cycle1,
                        //claw.HBPost(),
                        //intakeit1,
//                        claw.setToWall(),
//                        drivetowall3,
//                        claw.clawPickup(),
//                        claw.rotateUp(),
//                        cycle3,
//                        claw.scoreSpecimen(),
                        //Park1,
                        //claw.Resting(),               //set everything to teleop ready positions
                        new SleepAction(2)
                ));
//
//
//        TrajectoryActionBuilder DriveToSubmerse = drive.actionBuilder(BlueObservePose)
//                //.splineToLinearHeading(new Pose2d(-11,40,Math.toRadians(90)),Math.toRadians(-90))
//                .setTangent(-1.23412150741)
//                .lineToXSplineHeading(-11, Math.toRadians(-90))
//                //.turn(Math.toRadians(180))
//                .setTangent(Math.toRadians(90))
//                .lineToY(28);
//                //.waitSeconds(2)
//        TrajectoryActionBuilder DriveToSpecimen = DriveToSubmerse.fresh()
//                .setTangent(Math.toRadians(90))
//                .lineToY(35)
//                .setTangent(Math.toRadians(-180))
//                .splineTo(new Vector2d(-40,5),Math.toRadians(270))
//                //.turnTo(Math.toRadians(180))
//                .setTangent(Math.toRadians(0))
//                .lineToX(-50)
//                //.turnTo(Math.toRadians(270))
//                .setTangent(Math.toRadians(90))
//                .lineToY(SHALLOW_END_POINT_BLUE.y)
//                .setTangent(Math.toRadians(90))
//                .lineToY(5)
//                .setTangent(Math.toRadians(0))
//                .lineToX(-58)
//                .setTangent(Math.toRadians(90))
//                .lineToY(SHALLOW_END_POINT_BLUE.y);
                //.setTangent(-.53172067259)
                //.lineToXSplineHeading(-11, Math.toRadians(90));

//                .lineToY(35)
//                .setTangent(Math.toRadians(-180))
//                .splineTo(new Vector2d(-35,5),Math.toRadians(270))
//                .turnTo(Math.toRadians(180))
//                .setTangent(Math.toRadians(0))
//                .lineToX(-45)
//                //.turnTo(Math.toRadians(270))
//                .setTangent(Math.toRadians(90))
//                .lineToY(SHALLOW_END_POINT_BLUE.y)
//                .setTangent(Math.toRadians(90))
//                .lineToY(5)
//                .setTangent(Math.toRadians(0))
//                .lineToX(-58)
//                .setTangent(Math.toRadians(90))
//                .lineToY(SHALLOW_END_POINT_BLUE.y);
//
//
//                .setTangent(Math.toRadians(90))
//                .lineToY(5)
//                .setTangent(Math.toRadians(0))
//                .lineToX(-62)
//                .setTangent(Math.toRadians(90))
//                .lineToY(SHALLOW_END_POINT_BLUE.y);
//
//
//
//        Action DriveSubmersible;
//        DriveSubmersible = DriveToSubmerse.build();
//
//        Action DriveSpecimen;
//        DriveSpecimen = DriveToSpecimen.build();
//
//        waitForStart();
//
//         Actions.runBlocking(
//                new SequentialAction(
//                        //claw.rotateUp(),
//                        DriveSubmersible,
//                        claw.rotateUp(),
//                        claw.scoreSpecimen(),
//                        DriveSpecimen
//                ));
//
//
////        waitForStart();
////        Actions.runBlocking(
////                new SequentialAction(
////                        claw.rotateUp(),
////                        claw.armHook(),
////                        claw.clawDrop(),
////                        claw.rotateDown(),
////                        claw.clawRestore()
////
////                )
////        );
//
//

    }
}


