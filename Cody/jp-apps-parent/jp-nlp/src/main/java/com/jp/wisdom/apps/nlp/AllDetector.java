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
package com.jp.wisdom.apps.nlp;

import java.io.IOException;
import java.util.Arrays;
import opennlp.tools.util.Span;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class AllDetector
{

    private static OpenNLPUtil extractor;

    public static void main(String[] args) throws IOException
    {
        AllDetector allDetector = new AllDetector();
        extractor = new OpenNLPUtil();
//        allDetector.testMoneyFinder();
        allDetector.testPartOfSpeech();

    }

    private void testMoneyFinder()
    {
        String document = "Red Batman tshirt €2 under $20";
        //  String document = "Red Batman tshirt under $20";
        //  String document = "ipod less than hundred dollars";

        for (String sentence : extractor.segmentSentences(document))
        {
            System.out.println("sentence: " + sentence);

            String[] tokens = extractor.tokenizeSentence(sentence);

            for (String token : tokens)
            {
                System.out.println(token);
            }

            Span[] spans = extractor.findMoney(tokens);

            for (Span span : spans)
            {

                System.out.print("Money: ");

                for (int i = span.getStart(); i < span.getEnd(); i++)
                {
                    System.out.print(tokens[i]);
                    if (i < span.getEnd())
                    {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }

    public void testPartOfSpeech() throws NullPointerException
    {
        String document = "Use the folding wheels on both vehicles for the perfect high-speed pursuit in the air or on land.";
        //String document = "mens black shoe size 10";

        for (String sentence : extractor.segmentSentences(document))
        {
            System.out.println("sentence: " + sentence);

            String[] tokens = extractor.tokenizeSentence(sentence);

            String[] tags = extractor.tagPartOfSpeech(tokens);
            double[] probs = extractor.getPartOfSpeechProbabilities();

            for (int i = 0; i < tokens.length; i++)
            {
                System.out.print("token: " + tokens[i]);
                System.out.print("\t");
                System.out.print("pos: " + tags[i] + " - " + extractor.posValue(tags[i]));
                System.out.print("\t");
                System.out.print("probability: " + probs[i]);
                System.out.println();
            }
        }
    }

    public void testEnglishSentences()
    {

        // Text from http://techcrunch.com/2013/04/25/strategy-analytics-q1-tablet-stats/ (© 2013 AOL Inc.)
//        String document =
        //           "Don’t write off Microsoft’s chances in mobile just yet. It may still be struggling to make itself count in the smartphone space but early signs are more promising for Windows plus tablets. Microsoft has gone from having no share of the global tablet OS market in Q1 last year to taking 7.4% one year later, with three million Windows 8 tablets shipped in Q1 2013, according to preliminary figures from Strategy Analytics‘ Global Tablet OS Market Share: Q1 2013 report.";
        String document = "DSLR cameras are fast-focusing, allow you to take multiple photos quickly, and compose sharp images in nearly any light. With a precision viewfinder and image sensors that are more than 8X larger than smartphone sensors, DSLR cameras let you take pictures that are more detailed and stay sharp when resized.";

        for (String sentence : extractor.segmentSentences(document))
        {
            System.out.println("sentence: " + sentence);
        }
    }

    //@Test
    public void testEnglishSegmentation()
    {
        // Text from http://techcrunch.com/2013/04/25/strategy-analytics-q1-tablet-stats/ (© 2013 AOL Inc.)
        String document
                = "Don’t write off Microsoft’s chances in mobile just yet. It may still be struggling to make itself count in the smartphone space but early signs are more promising for Windows plus tablets. Microsoft has gone from having no share of the global tablet OS market in Q1 last year to taking 7.4% one year later, with three million Windows 8 tablets shipped in Q1 2013, according to preliminary figures from Strategy Analytics‘ Global Tablet OS Market Share: Q1 2013 report.";

        for (String sentence : extractor.segmentSentences(document))
        {
            System.out.println("sentence: " + sentence);

            for (String token : extractor.tokenizeSentence(sentence))
            {
                System.out.println("\t" + token);
            }
        }
    }

    public void testEnglishNames()
    {
        // String document = "Adult White 'Murica USA 4th July America Independence Freedom T-Shirt Tee Product Description 5.3 Oz.,Pre-Shrunk High Quality 100% cotton Double-needle stitched neckline.";
        String document = "Steve Jobs T-shirt for Men by Levis - New For MEN Size Only";

        for (String sentence : extractor.segmentSentences(document))
        {
            System.out.println("sentence: " + sentence);

            String[] tokens = extractor.tokenizeSentence(sentence);

            Span[] spans = extractor.findNames(tokens);

            for (Span span : spans)
            {

                System.out.print("person: ");

                for (int i = span.getStart(); i < span.getEnd(); i++)
                {
                    System.out.print(tokens[i]);
                    if (i < span.getEnd())
                    {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }

    public void testEnglishPartOfSpeech()
    {
        //String document =
        //  "Microsoft, founded in 1975 by Bill Gates and Paul Allen, is a veteran software company, best known for its Microsoft Windows operating system and the Microsoft Office suite of productivity software.";

        //String document ="The technical specs which are a A6X Quad core processor";
        String document = "The whole package. In a smaller package.iPad mini features a beautiful 7.9-inch display, iSight and FaceTime cameras, the A5 chip, ultrafast wireless, and up to 10 hours of battery life. And over 275,000 apps on the App Store made for iPad also work with iPad mini. So it’s an iPad in every way, shape, and slightly smaller form. It is available in black & slate or white & silver.";
        for (String sentence : extractor.segmentSentences(document))
        {
            System.out.println("sentence: " + sentence);

            String[] tokens = extractor.tokenizeSentence(sentence);

            /* Span[] spans = extractor.findNames(tokens);
             * for (Span span : spans) {
             * System.out.print("person: ");
             * for (int i = span.getStart(); i < span.getEnd(); i++) {
             * System.out.print(tokens[i]);
             * if (i < span.getEnd()) {
             * System.out.print(" ");
             * }
             * }
             * System.out.println();
             * } */
            System.out.println("TTTTTTOKENS   " + Arrays.asList(tokens));
            String[] tags = extractor.tagPartOfSpeech(tokens);
            double[] probs = extractor.getPartOfSpeechProbabilities();

            for (int i = 0; i < tokens.length; i++)
            {
                System.out.print("token: " + tokens[i]);
                System.out.print("\t");
                System.out.print("pos: " + tags[i] + " - " + extractor.posValue(tags[i]));
                // System.out.print("\t");
                // System.out.print("probability: " + probs[i]);
                System.out.println();
            }
        }
    }
}
