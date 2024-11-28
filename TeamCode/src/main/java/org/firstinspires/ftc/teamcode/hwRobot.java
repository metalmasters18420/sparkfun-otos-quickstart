package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.VariablesArm.ARM_BAR;
import static org.firstinspires.ftc.teamcode.VariablesArm.ARM_BIN;
import static org.firstinspires.ftc.teamcode.VariablesArm.ARM_REST;
import static org.firstinspires.ftc.teamcode.VariablesArm.ARM_WALL;
import static org.firstinspires.ftc.teamcode.VariablesArm.OWRIST_BAR;
import static org.firstinspires.ftc.teamcode.VariablesArm.OWRIST_BIN;
import static org.firstinspires.ftc.teamcode.VariablesArm.OWRIST_INTAKE;
import static org.firstinspires.ftc.teamcode.VariablesArm.OWRIST_WALL;
import static org.firstinspires.ftc.teamcode.VariablesIntake.CLAW_CLOSED;
import static org.firstinspires.ftc.teamcode.VariablesIntake.CLAW_OPEN;
import static org.firstinspires.ftc.teamcode.VariablesIntake.FLIP_CLAW;
import static org.firstinspires.ftc.teamcode.VariablesIntake.HORIZ_RETRACT_POS;
import static org.firstinspires.ftc.teamcode.VariablesIntake.IWRIST_MIDDLE;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.RoadRunner.MecanumDrive;

public class hwRobot {
    HardwareMap hm = null;
//    public DcMotor LFDrive = null;
//    public DcMotor LBDrive = null;
//    public DcMotor RBDrive = null;
//    public DcMotor RFDrive = null;
    MecanumDrive drive = null;

    public DcMotor lLift = null;
    public DcMotor rLift = null;

    public Servo oclaw = null;
    public Servo owrist = null;
    public Servo arm = null;

    public Servo LHoriz = null;
    public Servo RHoriz = null;
    public Servo iclaw = null;
    public Servo iwrist = null;
    public Servo iflip = null;

    public RevColorSensorV3 colorSensor = null;
    float gain = 2;

    public HorizontalExtention HorExt = null;
    public Lift lift = null;

    public hwRobot() {}

    public void init(HardwareMap hmap) {
        hm = hmap;

//        LFDrive = hm.get(DcMotor.class, "LF"); //CH0 motor
//        LBDrive = hm.get(DcMotor.class, "LB"); //CH1 motor
//        RFDrive = hm.get(DcMotor.class, "RF"); //CH2 motor
//        RBDrive = hm.get(DcMotor.class, "RB"); //CH3 motor

        lLift = hm.get(DcMotor.class, "LL"); //EH2 motor
        rLift = hm.get(DcMotor.class, "RL"); //EH3 motor
        oclaw = hm.get(Servo.class, "OC"); //CH1
        iclaw = hm.get(Servo.class, "IC"); //CH5
        owrist = hm.get(Servo.class, "OW"); //CH0
        iwrist = hm.get(Servo.class,"IW"); //CH4
        arm = hm.get(Servo.class, "arm"); //CH2
        LHoriz = hm.get(Servo.class, "LH"); //EH0
        RHoriz = hm.get(Servo.class, "RH"); //EH1
        iflip = hm.get(Servo.class, "IF"); //CH3
        colorSensor = hm.get(RevColorSensorV3.class, "COLOR");


//        LFDrive.setDirection(DcMotorSimple.Direction.REVERSE);
//        LBDrive.setDirection(DcMotorSimple.Direction.REVERSE);
//        RFDrive.setDirection(DcMotorSimple.Direction.FORWARD);
//        RBDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        drive = new MecanumDrive(hmap,new Pose2d(0,0,0));

//        LFDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        LBDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        RFDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        RBDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//        LFDrive.setPower(0);
//        LBDrive.setPower(0);
//        RFDrive.setPower(0);
//        RBDrive.setPower(0);
//
//        LFDrive.setTargetPosition(0);
//        LBDrive.setTargetPosition(0);
//        RFDrive.setTargetPosition(0);
//        RBDrive.setTargetPosition(0);

        colorSensor.setGain(gain);

        oclaw.setPosition(CLAW_OPEN);
        oclaw.setDirection(Servo.Direction.REVERSE);

        owrist.setPosition(OWRIST_INTAKE);
        owrist.setDirection(Servo.Direction.FORWARD);

        iclaw.setPosition(CLAW_OPEN);
        iclaw.setDirection(Servo.Direction.REVERSE);

        iwrist.setPosition(IWRIST_MIDDLE);
        iwrist.setDirection(Servo.Direction.FORWARD);

        arm.setPosition(ARM_REST);
        arm.setDirection(Servo.Direction.FORWARD);

        LHoriz.setPosition(HORIZ_RETRACT_POS);
        RHoriz.setPosition(HORIZ_RETRACT_POS);

        iflip.setPosition(FLIP_CLAW);
        iflip.setDirection(Servo.Direction.FORWARD);

        HorExt = new HorizontalExtention(LHoriz, RHoriz);

            LHoriz.setPosition(0);
            RHoriz.setPosition(0);

        lift = new Lift(lLift,rLift);

    }

    public void ArmRest(){
        lift.Lrest();
        oclaw.setPosition(CLAW_OPEN);
        owrist.setPosition(OWRIST_INTAKE);
        arm.setPosition(ARM_REST);
    }
    public void InRest(){
        iwrist.setPosition(IWRIST_MIDDLE);
        HorExt.HRetract();
    }
    public void HBPre(){
        lift.Lhbarpre();
        arm.setPosition(ARM_BAR);
        owrist.setPosition(OWRIST_BAR);
    }
    public void HBPost(){
        lift.Llbarpost();
    }
    public void Hbin(){
        lift.Lhbin();
        arm.setPosition(ARM_BIN);
        owrist.setPosition(OWRIST_BIN);
    }
    public void Lbin(){
        lift.Llbin();
        arm.setPosition(ARM_BIN);
        owrist.setPosition(OWRIST_BIN);
    }
    public void LBPre(){
        lift.Llbarpre();
        arm.setPosition(ARM_BAR);
        owrist.setPosition(OWRIST_BAR);
    }
    public void LBPost(){
        lift.Llbarpost();
    }
    public void Wall(){
        lift.Lwall();
        arm.setPosition(ARM_WALL);
        owrist.setPosition(OWRIST_WALL);
    }
//    public void PreIntake(){
//        HorExt.HExtend();
//        iflip.setPosition(FLIP_RAISED);
//        iclaw.setPosition(CLAW_OPEN);
//    }
//    public void Intaking(){
//        HorExt.HExtend();
//        iflip.setPosition(FLIP_RAISED);
//
//    }


//    public void liftLbin(){
//        lift.Llowbin();
//    }
//
//    public void liftHbin(){
//        lift.Lhighbin();
//    }
//
//    public void liftHbar(){
//        lift.Lhighbar();
//    }
//
//    public void liftLbar(){
//        lift.Llowbar();
//    }
//
//    public void liftwall(){
//        lift.Lwall();
//    }
//
//    public void liftrest(){
//        lift.Lrest();
//    }
//
//    public void liftLhang(){
//        lift.Lhang1();
//    }
//
//    public void liftHhang(){
//        lift.Lhang2();
//    }
    //public Action armRest(){
    //    return new InstantAction(()->ArmRest());
    //}

    public Action preparetoscore(){
        return new InstantAction(()->HBPre());
    }
    public Action scorespecimen(){
        return new InstantAction(()->HBPost());
    }
    public Action highbin(){
        return new InstantAction(()->Hbin());
    }
    public Action wall(){
        return new InstantAction(()->Wall());
    }
    public Action clawclose(){return new InstantAction(()-> oclaw.setPosition(CLAW_CLOSED));}
    public Action clawopen(){return new InstantAction(()-> oclaw.setPosition(CLAW_OPEN));}
    public Action iclawopen(){return new InstantAction(()-> iclaw.setPosition(CLAW_OPEN));}
    public Action iclawclose(){return new InstantAction(()-> iclaw.setPosition(CLAW_CLOSED));}
    public Action horizextend(){return new InstantAction(()->HorExt.HExtend());}
    public Action horizretract(){return new InstantAction(()->HorExt.HRetract());}
}