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

import java.io.InputStream;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class NameDetector
{

    public static void main(String[] args) throws Exception
    {
        NameDetector sentenceDetector = new NameDetector();
        sentenceDetector.run();
    }

    private void run() throws Exception
    {
        String sentence2 = "Jack London is the author of what novel?";
        String sentence = "Jack London is the author of what novel? Hi. How are you? This is Lester Graham";

        //1. convert sentence into tokens
        InputStream modelInToken = getClass().getResourceAsStream("/en-token.bin");
        TokenizerModel modelToken = new TokenizerModel(modelInToken);
        Tokenizer tokenizer = new TokenizerME(modelToken);
        String tokens[] = tokenizer.tokenize(sentence);

// always start with a model, a model is learned from training data
        InputStream is = getClass().getResourceAsStream("/en-ner-person.bin");
        TokenNameFinderModel model = new TokenNameFinderModel(is);
        NameFinderME nameFinder = new NameFinderME(model);

        Span nameSpans[] = nameFinder.find(tokens);

        for (Span nameSpan : nameSpans)
        {
            System.out.println("Span: " + nameSpan.toString());
            System.out.println("Covered text is: " + tokens[nameSpan.getStart()] + " " + tokens[nameSpan.getStart() + 1]);
        }
        is.close();
    }
}
