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

import com.jp.efficacy.ws.ResponseReason;
import com.jp.efficacy.ws.ResponseStatus;
import java.util.List;

import com.jp.ws.api.MathService;
import com.jp.ws.api.exceptions.ServiceException;
import com.jp.ws.api.exceptions.ServiceExceptionDetails;
import com.jp.ws.api.response.MathResponse;

/**
 * @author Dimit Chadha
 */
public class MathServiceImplOne implements MathService
{

    @Override
    public MathResponse summation(List<Integer> numbers) throws ServiceException
    {
        int sum = 0;
        for (Integer num : numbers)
        {
            if (num == 0)
            {
                ServiceExceptionDetails ServiceExceptionDetailsArray[] = new ServiceExceptionDetails[1];
                ServiceExceptionDetails serviceExceptionDetails = new ServiceExceptionDetails();
                serviceExceptionDetails.setFaultCode("100");
                serviceExceptionDetails.setFaultMessage("Number should not be Zero");
                ServiceExceptionDetailsArray[0] = serviceExceptionDetails;
                throw new ServiceException("Fault Message", ServiceExceptionDetailsArray);
            }
            sum += num;
        }
        return new MathResponse(ResponseStatus.SUCCESS, ResponseReason.OK, "Sum is " + sum, String.valueOf(sum));
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
