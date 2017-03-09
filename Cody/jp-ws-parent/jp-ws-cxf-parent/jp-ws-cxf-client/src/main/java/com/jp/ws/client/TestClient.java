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
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class TestClient
{

    public static void main(String[] args)
    {
        TestClient testClient = new TestClient();
        testClient.invoke();
    }

    private void invoke()
    {
        MathEndPoint endPoint = MathEndpointFactory.create();

        List<Integer> numberList = new ArrayList();
        numberList.add(12);
        numberList.add(13);

        MathResponse response = endPoint.summation(numberList);
        System.out.println(response.toString());
    }

}
