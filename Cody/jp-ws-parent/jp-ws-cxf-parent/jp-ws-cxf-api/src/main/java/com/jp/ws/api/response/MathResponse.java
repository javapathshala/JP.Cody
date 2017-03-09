/*
 * File: MathResponse.java Date: 05-May-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.ws.api.response;

import com.jp.efficacy.ws.AbstractResponse;
import com.jp.efficacy.ws.ResponseReason;
import com.jp.efficacy.ws.ResponseStatus;

/**
 * @author Dimit Chadha
 */
public class MathResponse extends AbstractResponse
{

    private String answer;

    public MathResponse()
    {
        super();
    }

    public MathResponse(ResponseStatus status, ResponseReason reason, String answer)
    {
        this(status, reason, null, answer);
    }

    public MathResponse(ResponseStatus status, ResponseReason reason, String message, String answer)
    {
        super(status, reason, message);
        this.answer = answer;
    }

    /**
     * @return the answer
     */
    public String getAnswer()
    {
        return answer;
    }

    /**
     * @param answer
     *               the answer to set
     */
    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    @Override
    public String toString()
    {
        return "MathResponse{" + "Abstract Response --> " + super.toString() + "    answer=" + answer + '}';
    }

}
