/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jp.ws.api.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author dimit
 */
//@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "numberRequest", propOrder = {
//    "numberOne",
//    "numberTwo",
//    "num3D",
//    "num4D",
//    "num5B",
//    "num6B",
//    "data"
//})
//@XmlRootElement(name = "numberRequest")
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "numberRequest", propOrder = {
//    "numberOne",
//    "numberTwo",
//    "num3D",
//    "num4D",
//    "num5B",
//    "num6B",
//    "data"
//})
public class NumberRequest implements Serializable {

    public NumberRequest() {
    }

//    @XmlElement(required = true)
    private int numberOne;
//    @XmlElement(required = true)
    private int numberTwo;

//    @XmlElement(required = true)
    private double num3d;
//    @XmlElement(required = true)
    private double num4d;

//    @XmlElement(required = true)
    private BigDecimal num5b;

//    @XmlElement(required = true)
    private BigDecimal num6b;

//    @XmlElement(required = true)
    private Date date;

    public int getNumberOne() {
        return numberOne;
    }

    public void setNumberOne(int numberOne) {
        this.numberOne = numberOne;
    }

    public int getNumberTwo() {
        return numberTwo;
    }

    public void setNumberTwo(int numberTwo) {
        this.numberTwo = numberTwo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    

    public double getNum3d() {
        return num3d;
    }

    public void setNum3d(double num3d) {
        this.num3d = num3d;
    }

    public double getNum4d() {
        return num4d;
    }

    public void setNum4d(double num4d) {
        this.num4d = num4d;
    }

    public BigDecimal getNum5b() {
        return num5b;
    }

    public void setNum5b(BigDecimal num5b) {
        this.num5b = num5b;
    }

    public BigDecimal getNum6b() {
        return num6b;
    }

    public void setNum6b(BigDecimal num6b) {
        this.num6b = num6b;
    }

}
