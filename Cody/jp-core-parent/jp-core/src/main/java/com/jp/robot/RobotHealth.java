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

import com.jp.robot.Robot.Color;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class RobotHealth extends BasicRobotHealth
{

    @Override
    public boolean canCarry(int weight)
    {
        if (weight > 10)
        {
            System.out.println("Overweight");
            System.out.println("************************************************");
            return false;
        }
        return true;
    }

    @Override
    public boolean powerCheck(Robot r, int requiredCharging)
    {
        int availbleCharging = r.getChargingStatus();
        if (availbleCharging < requiredCharging)
        {
            System.out.println("Insufficient Charging For Task");
            System.out.println("************************************************");
            return false;
        }
        else
        {
            int remaining = availbleCharging - requiredCharging;
            r.setChargingStatus(remaining);
            if (remaining < 15)
            {
                r.setHeadLightColor(Color.RED);
                System.out.println("*********************************************");
            }
            return true;
        }

    }
}
