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
public class Robot
{

    public enum Color
    {
        GREEN, RED
    }
    private int chargingStatus;
    private Color headLightColor;
    private int weightCapcity;
    private int walkCapcity;
    private static final int MAX_WEIGHT_CAPCITY = 10;

    public Robot()
    {
        this.chargingStatus = 100;
        this.walkCapcity = MAX_WEIGHT_CAPCITY;
        this.weightCapcity = 10;
        this.headLightColor = Color.GREEN;
    }

    public int getChargingStatus()
    {
        return chargingStatus;
    }

    public void setChargingStatus(int chargingStatus)
    {
        this.chargingStatus = chargingStatus;
    }

    public Color getHeadLightColor()
    {
        return headLightColor;
    }

    public void setHeadLightColor(Color headLightColor)
    {
        this.headLightColor = headLightColor;
    }

    public int getWeightCapcity()
    {
        return weightCapcity;
    }

    public void setWeightCapcity(int weightCapcity)
    {
        this.weightCapcity = weightCapcity;
    }

    public int getWalkCapcity()
    {
        return walkCapcity;
    }

    public void setWalkCapcity(int walkCapcity)
    {
        this.walkCapcity = walkCapcity;
    }
    
    
}
