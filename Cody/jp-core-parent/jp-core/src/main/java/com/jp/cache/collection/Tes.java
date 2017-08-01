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
package com.jp.cache.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class Tes
{

    public static void main(String[] args)
    {

        Random r = new Random();

        //Populate array with ten random elements
//        for(int i = 0 ; i < 4; i++){
//            numbers.add(r.nextInt());
//        }
        Integer[] array = new Integer[]
        {
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13
        };

//        Arrays.asList(array);
//
//        List<Integer> numbers = Arrays.asList(array);
//
//        List<Integer> delete = new ArrayList();
//
//        //   while (numbers.size() > 1)
//        //  {
//        for (int i = 1; i <= numbers.size(); i++)
//        {
//            if ((i % 3) == 0)
//            {//Every 3rd element should be true
//                //  numbers.remove(i);
//                delete.add(i);
//                System.out.println("removed");
//            }
//        }
//        numbers.removeAll(delete);
//        //  }
//        System.out.println(numbers.size());
        //System.out.println(numbers.get(0)[0]);
        Integer[] aa = replicate(array, 4);

        while (aa.length > 1)
        {
            aa = replicate(aa, 4);
            System.out.println(aa.length);
        }

        System.out.println(aa.toString());
    }

    public static Integer[] replicate(Integer[] array, int n)
    {
        Integer[] newArray = new Integer[array.length - array.length / n];
        Integer j = 0;
        for (int i = 0; i != array.length; i++)
        {
            if ((i + 1) % n != 0)
            {
                newArray[j++] = array[i];
            }
        }
        return newArray;
    }

//    public static void main(String[] args)
//    {
//        Tes tes = new Tes();
//        tes.process();
//    }
//
//    public int[] replicate(int[] array, int n)
//    {
//        int[] newArray = new int[array.length - array.length / n];
//        int j = 0;
//        for (int i = 0; i != array.length; i++)
//        {
//            if ((i + 1) % n != 0)
//            {
//                newArray[j++] = array[i];
//            }
//        }
//        return newArray;
//    }
//
//    private void process()
//    {
//        int[] array = new int[]
//        {
//            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13
//        };
//        System.out.println(replicate(array, 3));
////        List<Integer> position = Arrays.asList(persons);
////         int kpos = 3;
////        for (Integer p : position)
////        {
////            if (p==kpos){
////                position.remove(p);
////            }
////            if ()
////        }
////        
////        
////              
////               
////        int z=position.length;
////        int y=0;
////        for (int i : position)
////        {
////           if(y==kpos){
////               //remove fom rray
////               
////           }
////            y++;
////           
////            z--;
////            if (z==1){
////                break;
////            }
////        }
//
//    }
//
}
