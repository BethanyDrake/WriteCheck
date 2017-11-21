package sycorax.writecheck;

import android.widget.Button;

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

    public ArrayList<Result> parseSearchResult(String s)
    {
        StringTokenizer lines = new StringTokenizer(s, "\n");

        String setsString = getLine("sets", lines);
        ArrayList<String> sets = blockify("{","}",setsString);

        ArrayList<Result> results = new ArrayList<>();
        for(String setString : sets)
        {
            Result result = new Result();
            result.creator = stripQuotes(getLine("created_by", setString));
            result.title = stripQuotes(getLine("title", setString));
            result.numCards = Integer.parseInt(stripNonNum(getLine("term_count", setString)));
            result.id = Integer.parseInt(stripNonNum(getLine("id", setString)));

            results.add(result);

        }

        return results;
    }


}
