/*
 * File: InquiryService.java Date: 30-Apr-2015 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.ws.api;

import com.jp.ws.api.exceptions.ServiceException;
import com.jp.ws.api.request.NumberRequest;
import java.util.List;

import com.jp.ws.api.response.MathResponse;

/**
 * @author Dimit Chadha
 */
public interface InquiryService
{

//    MathResponse summation(List<Integer> numbers) throws ServiceException;
    MathResponse summation(NumberRequest numberRequest) throws ServiceException;

}
