package sycorax.writecheck;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Bethany on 20/11/2017.
 */

public class Parser {


    public Parser()
    {

    }


    private String stripQuotes(String s)
    {

        int start = s.indexOf("\"");
        if (start == -1) return s;
        int end = s.indexOf("\"", start+1);
        if (end == -1) return s;
        return s.substring(start+1, end);
    }

    public CardSet parseCardSet(String s)
    {


        StringTokenizer lines = new StringTokenizer(s, "\n");

        String title = getLine("title", lines);
        CardSet cardSet = new CardSet(title);

        String terms = getLine("terms",lines);
        ArrayList<String> splitTerms = blockify("{","}",terms);

        for(String termBlock : splitTerms)
        {
            StringTokenizer termLines = new StringTokenizer(termBlock, "\n");
            String front = getLine("term", termLines);
            String back = getLine("definition",termLines);

            cardSet.addCard(front, back);

        }

        return cardSet;
    }



    public ArrayList<String> blockify(String open, String close, String text)
    {


        ArrayList<String> blocks = new ArrayList<String>();

        int depth = 0;
        int currBlock = -1;
        boolean inblock = false;
        while(text.length()>0)
        {
            if (text.startsWith(open))
            {
                depth++;
                text.substring(open.length());
                //if depth is now one, open a new block
                if (depth ==1)
                {
                    currBlock++;
                    blocks.set(currBlock, "");
                    inblock = true;
                    continue;
                }

            }

            if (text.startsWith(close))
            {
                depth--;
                text.substring(close.length());
                //if depth is now zero, close that block
                if (depth == 0)
                {
                    inblock = false;
                    continue;
                }
            }

            if (inblock)
            {
                blocks.set(currBlock, blocks.get(currBlock) +text.substring(0,1));
                text = text.substring(1);
            }


        }

        return blocks;
    }


    private String getLine(String label, StringTokenizer lines)
    {
        String line;
        while (lines.hasMoreTokens())
        {
            line = lines.nextToken();
            String[] parts = line.split(":",2);
            if (parts.length<2) continue;
            if (parts[0].equals("\""+label+"\""))
            {

                if (parts[1].contains("["))
                {
                    String multiline = "";
                    while(lines.hasMoreTokens())
                    {
                        line = lines.nextToken();
                        if (line.contains("]")) break;
                        multiline += line;
                    }
                    return multiline;
                }
                return parts[1]; //don't want the space or the comma
            }

        }

        return "";
    }




}
