package sycorax.writecheck;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Bethany on 21/11/2017.
 */

public class SearchParser extends Parser {


    public class Result
    {
        String creator;
        String title;
        int numCards;
        int id;
    }

    public ArrayList<Result> parseSearchResult(String s, int max)
    {
        StringTokenizer lines = new StringTokenizer(s, "\n");

        String title = getLine("title", lines);
        title = stripQuotes(title);


        CardSet cardSet = new CardSet(title);

        String terms = getLine("terms",lines);
        ArrayList<String> splitTerms = blockify("{","}",terms);

        for(String termBlock : splitTerms)
        {


        }

        return null;
    }
}
