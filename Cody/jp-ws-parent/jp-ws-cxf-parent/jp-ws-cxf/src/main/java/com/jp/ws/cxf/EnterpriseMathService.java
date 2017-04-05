/*
 * File: EnterpriseMathService.java Date: 30-Apr-2015 This source code is part
 * of Java Pathshala-Wisdom Being Shared. This program is protected by copyright
 * law but you are authorise to learn & gain ideas from it. Its unauthorised use
 * is explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.ws.cxf;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.jp.ws.api.InquiryService;
import com.jp.ws.api.MathService;
import com.jp.ws.api.StorageService;
import com.jp.ws.api.exceptions.ServiceException;
import com.jp.ws.api.response.MathResponse;
import javax.xml.ws.Action;
import javax.xml.ws.soap.Addressing;

/**
 * @author Dimit Chadha
 */
@WebService(serviceName = "MathService", portName = "MathPort", name = "MathEndPoint", targetNamespace = "http://jp.com/math/ws")
@Addressing
//@Addressing(enabled = true, required = true)
public class EnterpriseMathService implements MathService
{

    private InquiryService inquiryDelegate;

    private StorageService storageDelegate;

    /**
     * @param inquiryDelegate
     *                        the inquiryDelegate to set
     */
    @WebMethod(exclude = true)
    public void setInquiryDelegate(InquiryService inquiryDelegate)
    {
        this.inquiryDelegate = inquiryDelegate;
    }

    /**
     * @param storageDelegate
     *                        the storageDelegate to set
     */
    @WebMethod(exclude = true)
    public void setStorageDelegate(StorageService storageDelegate)
    {
        this.storageDelegate = storageDelegate;
    }

    // Inquiry Methods
    @Override
    @Action(input = "http://jp.com/math/ws/input",output ="http://jp.com/math/ws/output")
    public MathResponse summation(@WebParam(name = "numberList") List<Integer> numbers) throws ServiceException
    {
        return inquiryDelegate.summation(numbers);
    }

    @Override
    public MathResponse multiple(List<Integer> numbers)
    {
        return storageDelegate.multiple(numbers);
    }

    // Storage Method
    @Override
    public void record(String party)
    {
        storageDelegate.record(party);
    }

}
