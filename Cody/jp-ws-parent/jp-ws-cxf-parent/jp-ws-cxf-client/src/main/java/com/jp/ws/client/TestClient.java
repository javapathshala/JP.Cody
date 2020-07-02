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
package com.jp.ws.client;

import com.jp.math.ws.MathEndPoint;
import com.jp.math.ws.MathResponse;
import com.jp.math.ws.NumberRequest;
import com.jp.math.ws.ServiceExceptionDetails;
import com.jp.math.ws.ServiceException_Exception;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class TestClient {

    public static void main(String[] args) throws DatatypeConfigurationException, ParseException {
        TestClient testClient = new TestClient();
        testClient.invoke();
    }

    private void invoke() throws DatatypeConfigurationException, ParseException {
        MathEndPoint endPoint = MathEndpointFactory.create();

        List<Integer> numberList = new ArrayList();
        numberList.add(67);
        numberList.add(13);

        NumberRequest numberRequest = new NumberRequest();
        numberRequest.setNumberOne(12);
        numberRequest.setNumberTwo(13);

        numberRequest.setNum3D(35.35);
        numberRequest.setNum4D(35.35);

        numberRequest.setNum5B(new BigDecimal(8459));

        numberRequest.setNum6B(new BigDecimal(3259));
        System.out.println(Calendar.getInstance().toString());
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
//        Date date = Calendar.getInstance().getTime();

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        String aa = format.format(cal.getTime());
        System.out.println(aa);
        Date dd = format.parse(aa);

        cal.setTime(dd);
        numberRequest.setDate(cal);
//        String xx = formatDate(date, "Asia/Kolkata", "dd-MM-yyyy hh:mm:ss.SSSa");
////        2020-07-01T06:26:06.965Z

//        GregorianCalendar c = new GregorianCalendar();
//        c.setTime(date);
//        XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
//        numberRequest.setData(xmlGregorianCalendar);
        try {
            MathResponse response = endPoint.summation(numberRequest);
            System.out.println(response.toString());
            System.out.println(response.getAnswer());
        } catch (ServiceException_Exception e) {
            List<ServiceExceptionDetails> faultDetails = e.getFaultInfo().getFaultDetails();
            for (ServiceExceptionDetails serviceExceptionDetails : faultDetails) {
                System.out.println("Fault code = " + serviceExceptionDetails.getFaultCode() + "\nFault message = "
                        + serviceExceptionDetails.getFaultMessage());
            }
        }
    }

    public static String formatDate(Date date, String timeZone, String dateFormat) {
        DateFormat format = new SimpleDateFormat(dateFormat);
        TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
        return format.format(date);
    }

}
