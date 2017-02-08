/*
 * File: HazelcastMathService.java Date: 08-May-2015 This source code is part of
 * Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.math.impl.two;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.jp.ws.api.MathService;
import com.jp.ws.api.response.MathResponse;
import com.jp.ws.api.response.ResponseReason;
import com.jp.ws.api.response.ResponseStatus;

/**
 * @author Dimit Chadha
 */
public class HazelcastMathService implements MathService
{

    private static final Logger LOG = LoggerFactory.getLogger(HazelcastMathService.class);

    private IMap<Integer, String> calculatedResult;

    public void setHazelcastInstance(HazelcastInstance hazelcast)
    {
        this.calculatedResult = hazelcast.getMap("calculatedResult");
    }

    @Override
    public MathResponse multiple(List<Integer> numbers)
    {
        LOG.info("In MathResponse multiple(List<Integer> numbers)");
        int index = numbers.size();
        LOG.info("Index is {}", index);
        if (getResult(index) == null)
        {
            LOG.info("OK");
            LOG.info("New for Cache........");
            int mul = 1;
            for (Integer num : numbers)
            {
                mul *= num;
            }
            calculatedResult.put(index, "" + mul * 100);
            return new MathResponse(ResponseStatus.SUCCESS, ResponseReason.OK, "Fresh Hazel Wala", getResult(index));
        }
        else
        {
            LOG.info("From cache......");
            return new MathResponse(ResponseStatus.SUCCESS, ResponseReason.OK, "From Hazel Wala", getResult(index));
        }

    }

    /**
     * @return
     */
    private String getResult(int index)
    {
        LOG.info("size is ###### {} ###", calculatedResult.size());
        return calculatedResult.get(index);

    }

    /*
     * (non-Javadoc)
     * @see com.jp.ws.api.InquiryService#summation(java.util.List)
     */
    @Override
    public MathResponse summation(List<Integer> numbers)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see com.jp.ws.api.StorageService#record(java.lang.String)
     */
    @Override
    public void record(String party)
    {

    }

}
