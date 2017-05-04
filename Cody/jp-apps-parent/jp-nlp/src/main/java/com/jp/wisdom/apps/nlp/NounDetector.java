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
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class NounDetector
{

    static String sentence = "Who is the author of The Call of the Wild?";

    static Set<String> nounPhrases = new HashSet<>();

    public static void main(String[] args)
    {
        NounDetector nounDetector = new NounDetector();
        nounDetector.run();

    }

    private void run()
    {
        InputStream modelInParse = null;
        try
        {
            //load chunking model
            modelInParse = getClass().getResourceAsStream("/en-parser-chunking.bin");
            ParserModel model = new ParserModel(modelInParse);

            //create parse tree
            Parser parser = ParserFactory.create(model);
            Parse topParses[] = ParserTool.parseLine(sentence, parser, 1);

            //call subroutine to extract noun phrases
            for (Parse p : topParses)
            {
                getNounPhrases(p);
            }

            //print noun phrases
            for (String s : nounPhrases)
            {
                System.out.println(s);
            }

            //The Call
            //the Wild?
            //The Call of the Wild? //punctuation remains on the end of sentence
            //the author of The Call of the Wild?
            //the author
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (modelInParse != null)
            {
                try
                {
                    modelInParse.close();
                }
                catch (IOException e)
                {
                }
            }
        }
    }

    //recursively loop through tree, extracting noun phrases
    public static void getNounPhrases(Parse p)
    {

        if (p.getType().equals("NP"))
        { //NP=noun phrase
            nounPhrases.add(p.getCoveredText());
        }
        for (Parse child : p.getChildren())
        {
            getNounPhrases(child);
        }
    }

}
