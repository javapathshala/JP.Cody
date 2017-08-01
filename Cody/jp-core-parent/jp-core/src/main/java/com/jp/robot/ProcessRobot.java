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

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class ProcessRobot
{

    public static void main(String[] args)
    {
        RobotService service = new PrototypeRobot();
        service.walk(5);
        service.carry(5);
        service.walkAndCarry(1, 13);
        service.setCharging(70);
        service.walkAndCarry(3, 2);
        service.displayPrice(18);

    }

}
