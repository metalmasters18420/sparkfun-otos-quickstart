package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTHANG1;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTHANG2;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTHBARPOST;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTHBARPRE;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTHBIN;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTLBARPOST;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTLBARPRE;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTLBIN;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTREST;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTWALL;
import static org.firstinspires.ftc.teamcode.VariablesLift.kD;
import static org.firstinspires.ftc.teamcode.VariablesLift.kG;
import static org.firstinspires.ftc.teamcode.VariablesLift.kI;
import static org.firstinspires.ftc.teamcode.VariablesLift.kP;
import static org.firstinspires.ftc.teamcode.VariablesLift.summax;
import static org.firstinspires.ftc.teamcode.VariablesLift.targetpos;
import static org.firstinspires.ftc.teamcode.VariablesLift.threshold;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Lift {

    private ElapsedTime timer = new ElapsedTime();
    private DcMotor left;
    private DcMotor right;
    int target = 0;
    double lastError = 0;
    double sum = 0;

    public Lift(DcMotor ll, DcMotor rl) {
        this.left = ll;
        this.right = rl;

//        right = hardwareMap.get(DcMotor.class, "rl");
        right.setDirection(DcMotorSimple.Direction.FORWARD);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setPower(0);

//        left = hardwareMap.get(DcMotor.class, "ll");
        left.setDirection(DcMotorSimple.Direction.REVERSE);
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left.setPower(0);
    }

    public void Lhbin(){
        this.setPosition(LIFTHBIN);
    }
    public void Llbin(){
        this.setPosition(LIFTLBIN);
    }
    public void Lhbarpre(){
        this.setPosition(LIFTHBARPRE);
    }
    public void Lhbarpost(){
        this.setPosition(LIFTHBARPOST);
    }
    public void Llbarpre(){
        this.setPosition(LIFTLBARPRE);
    }
    public void Llbarpost(){
        this.setPosition(LIFTLBARPOST);
    }
    public void Lwall(){
        this.setPosition(LIFTWALL);
    }
    public void Lhang1(){
        this.setPosition(LIFTHANG1);
    }
    public void Lhang2(){
        this.setPosition(LIFTHANG2);
    }
    public void Lrest(){
        this.setPosition(LIFTREST);
    }

    private void setPosition(int target){
        this.target = target;
        int currentpos = left.getCurrentPosition();

        double output = liftControl(target, currentpos ,threshold) + kG;
        left.setPower(output);
        right.setPower(output);
    }

    private double liftControl(int target, int current, int thresh){

        int error = target - current;
        double deriv = (error - lastError) / timer.seconds();
        sum += error;

        if (sum > summax) {
            sum = summax;
        }
        if (sum < summax) {
            sum = -summax;
        }

        lastError = error;
        timer.reset();
        double output = kP * error + kD * deriv + kI * sum;

        if (Math.abs(error) < thresh) {
            return 0;
        } else {
            return output;
        }
    }

    public void update(){
        setPosition(this.target);
    }
}
