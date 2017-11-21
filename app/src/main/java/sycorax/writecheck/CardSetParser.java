package sycorax.writecheck;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Bethany on 21/11/2017.
 */

public class CardSetParser extends Parser {

    public CardSet parseCardSet(String s)
    {
        StringTokenizer lines = new StringTokenizer(s, "\n");

        String title = getLine("title", lines);
        title = stripQuotes(title);


        CardSet cardSet = new CardSet(title);

        String terms = getLine("terms",lines);
        ArrayList<String> splitTerms = blockify("{","}",terms);

        for(String termBlock : splitTerms)
        {
            StringTokenizer termLines = new StringTokenizer(termBlock, "\n");
            String front = getUnicode(stripQuotes(getLine("term", termLines)));
            String back = getUnicode(stripQuotes(getLine("definition",termLines)));

            cardSet.addCard(front, back);

        }

        return cardSet;
    }
}
