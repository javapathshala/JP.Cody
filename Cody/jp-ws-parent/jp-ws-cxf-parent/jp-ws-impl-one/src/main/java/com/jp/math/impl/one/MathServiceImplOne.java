/*
 * File: MathServiceImplOne.java Date: 01-May-2015 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.math.impl.one;

import java.util.List;

import com.jp.ws.api.MathService;
import com.jp.ws.api.response.MathResponse;
import com.jp.ws.api.response.ResponseReason;
import com.jp.ws.api.response.ResponseStatus;

/**
 * @author Dimit Chadha
 */
public class MathServiceImplOne implements MathService
{

    @Override
    public MathResponse summation(List<Integer> numbers)
    {
        int sum = 0;
        for (Integer num : numbers)
        {
            sum += num;
        }
        return new MathResponse(ResponseStatus.SUCCESS, ResponseReason.OK, sum + "");
    }

    @Override
    public MathResponse multiple(List<Integer> numbers)
    {
        int mul = 1;
        for (Integer num : numbers)
        {
            mul *= num;
        }
        return new MathResponse(ResponseStatus.SUCCESS, ResponseReason.OK, mul + "");
    }

    @Override
    public void record(String party)
    {
        System.out.println("Party name : " + party);
    }

}
