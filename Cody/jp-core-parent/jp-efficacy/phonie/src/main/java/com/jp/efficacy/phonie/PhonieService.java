/*
 * Copyright (c) Java Pathshala
 * All rights reserved.
 *
 * No parts of this source code can be reproduced without written consent from
 * Java Pathshala
 */
package com.jp.efficacy.phonie;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class PhonieService
{

    private static final String PHONE_EXPRESSION = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{0,4})$";

    /**
     * It accepts the following phone number format at lease 6 digits and maximum of 10 digits.
     * It does not contains any other alphabetic or special characters.
     * <p>
     * Formats: (xxx)xxx-xxxx; xxxxxxxxxx; xxx-xxx-xxxx, xxxxxx
     *
     * @param phoneNumber
     * @return boolean
     */
    public static boolean isPhoneNumberValid(String phoneNumber)
    {
        boolean isValid = false;
        //Initialize reg ex for phone number.   
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(PHONE_EXPRESSION);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())
        {
            isValid = true;
        }
        return isValid;
    }

    /**
     * Translate the given phone with region to required format - {@link PhoneNumberUtil.PhoneNumberFormat}
     *
     * @param number
     * @param format
     * @param region
     * @return
     * @throws NumberParseException
     */
    public static String translatePhoneFormat(String number, PhoneNumberUtil.PhoneNumberFormat format, String region) throws NumberParseException
    {
        //Get the Phone Number Utility Instance
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        PhoneNumber phone = phoneUtil.parse(number, region);
        return phoneUtil.format(phone, format);
    }
    
    /**
     * Get the complete phone number details in an array with country code {@link getCountryCode()},
     * area code & subscriber number
     *
     * @param phoneNumber
     * @return
     * @throws NumberParseException
     */
    public static String[] getPhoneNumberDetails(String phoneNumber) throws NumberParseException
    {

        String[] str = new String[3];
        str[0] = String.valueOf(getCountryCode(phoneNumber, phoneNumber));
        str[1] = getAreaCode(phoneNumber, phoneNumber);
        str[2] = getSubscriberNumber(phoneNumber, phoneNumber);
        return str;
    }

    /**
     *
     * @param number
     * @param region
     * @return
     * @throws NumberParseException
     */
    public static int getCountryCode(String number, String region) throws NumberParseException
    {
        PhoneNumber phone = getPhoneNumber(number, region);
        return phone.getCountryCode();
    }

    /**
     *
     * @param number
     * @param region
     * @return
     * @throws NumberParseException
     */
    private static PhoneNumber getPhoneNumber(String number, String region) throws NumberParseException
    {
        //Get the Phone Number Utility Instance
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        return phoneUtil.parse(number, region);
    }

    public static String getNationalSignificantNumber(String number, String region) throws NumberParseException
    {
        //Get the Phone Number Utility Instance
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        PhoneNumber phone = phoneUtil.parse(number, region);
        return phoneUtil.getNationalSignificantNumber(phone);
    }

    /**
     *
     * @param number
     * @param region
     * @return
     * @throws NumberParseException
     */
    public static String getAreaCode(String number, String region) throws NumberParseException
    {

        String nationalSignificantNumber = getNationalSignificantNumber(number, region);
        String areaCode;
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        PhoneNumber phone = phoneUtil.parse(number, region);
        int areaCodeLength = phoneUtil.getLengthOfGeographicalAreaCode(phone);
        if (areaCodeLength > 0)
        {
            areaCode = nationalSignificantNumber.substring(0, areaCodeLength);
        }
        else
        {
            areaCode = "";
        }
        return areaCode;
    }

    /**
     *
     * @param number
     * @param region
     * @return
     * @throws NumberParseException
     */
    public static String getSubscriberNumber(String number, String region) throws NumberParseException
    {
        String nationalSignificantNumber = getNationalSignificantNumber(number, region);
        String subscriberNumber;
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        PhoneNumber phone = phoneUtil.parse(number, region);
        int areaCodeLength = phoneUtil.getLengthOfGeographicalAreaCode(phone);
        if (areaCodeLength > 0)
        {
            subscriberNumber = nationalSignificantNumber.substring(areaCodeLength);
        }
        else
        {
            subscriberNumber = nationalSignificantNumber;
        }
        return subscriberNumber;
    }

}
