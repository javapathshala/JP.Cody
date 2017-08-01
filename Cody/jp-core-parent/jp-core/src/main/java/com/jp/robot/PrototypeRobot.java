/*
 * Copyright (c) Java Pathshala.
 * All rights reserved.
 *
 * This program is protected by copyright law but you are authorise to learn
 * & gain ideas from it. Its unauthorised use is explicitly prohibited &
 * any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction.
 * www.javapathshala.com
 */
package com.jp.robot;

import java.util.Random;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class PrototypeRobot implements RobotService
{

    private Robot r;
    private RobotHealth rh;
    static int availbleCharging;

    public PrototypeRobot()
    {
        this.r = new Robot();
        this.rh = new RobotHealth();
    }

    @Override
    public boolean walk(double distance)
    {
        availbleCharging = r.getChargingStatus();
        int requiredCharging = (int) (distance * 1000 / 50);
        if (rh.powerCheck(r, requiredCharging))
        {
            System.out.println("Walked " + distance + " km | Charging Used " + requiredCharging + "% | Remaining  " + r.getChargingStatus() + "%");
            System.out.println("Robot HeadLight Color " + r.getHeadLightColor());
            System.out.println("************************************************");
            return true;
        }
        return false;
    }

    @Override
    public boolean walkAndCarry(double dist, int weight)
    {
        if (rh.canCarry(weight))
        {
            int requiredCharging = (int) (dist * 1000 / 50) + 2 * weight;
            availbleCharging = r.getChargingStatus();
            if (rh.powerCheck(r, requiredCharging))
            {
                System.out.println("Walked " + dist + "km and Carried " + weight + "kg | Charging Used " + requiredCharging + "% | Remaining  " + r.getChargingStatus() + "%");
                System.out.println("Robot HeadLight Color " + r.getHeadLightColor());
                System.out.println("************************************************");
                return true;
            }
        }
        return false;
    }

    @Override
    public void displayPrice(int barCode)
    {
        if (barCode % 2 == 0)
        {
            System.out.println("Price is " + new Random().nextInt(100));
        }
        else
        {
            System.out.println("Scan Failure");
        }
    }

    @Override
    public boolean carry(int weight)
    {
        if (rh.canCarry(weight))
        {
            int requiredCharging = 2 * weight;
            availbleCharging = r.getChargingStatus();
            if (rh.powerCheck(r, requiredCharging))
            {
                System.out.println("Carried " + weight + "kg | Charging Used " + requiredCharging + "% | Remaining  " + r.getChargingStatus() + "%");
                System.out.println("Robot HeadLight Color " + r.getHeadLightColor());
                System.out.println("************************************************");
                return true;
            }
        }
        return false;
    }

    @Override
    public void setCharging(int percentage)
    {
        int currentCharging = r.getChargingStatus();
        if (currentCharging + percentage > 100)
        {
            r.setChargingStatus(100);
        }
        else
        {
            r.setChargingStatus(currentCharging + percentage);
        }
    }
}
