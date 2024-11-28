package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;

@Config

public class VariablesLift {

    //max is 2450

    public static int LIFTLBARPRE = 1;
    public static int LIFTLBARPOST = 2;
    public static int LIFTHBARPRE = 2;
    public static int LIFTHBARPOST = 2;
    public static int LIFTLBIN = 1;
    public static int LIFTHBIN = 2500;
    public static int LIFTWALL = 5;
    public static int LIFTHANG1 = 1;
    public static int LIFTHANG2 = 2;
    public static int LIFTREST = 0;
    public static int CountsPerin = 10;

    public static double kP = 0.01; //.01
    public static double kI = 0; //.01
    public static double kD = 0;
    public static double kF = 0;
    public static double kG = 0;
    public static int threshold = 0;
    public static int summax = 100;

    public static int lowpos = 0;
    public static int highpos = 3000;
    public static int targetpos = lowpos;

}
