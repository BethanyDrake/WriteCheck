package sycorax.writecheck;

import android.util.Log;

import java.io.Console;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static android.media.CamcorderProfile.get;

/**
 * Created by Bethany on 20/11/2017.
 */

public class Parser {


    public Parser()
    {

    }


    public String stripQuotes(String s)
    {

        int start = s.indexOf("\"");
        if (start == -1) return s;
        int end = s.indexOf("\"", start+1);
        if (end == -1) return s;
        return s.substring(start+1, end);
    }


    public String debugText = "";

    public ArrayList<String> blockify(String open, String close, String text)
    {


        ArrayList<String> blocks = new ArrayList<String>();

        int depth = 0;
        int currBlock = -1;

        while(text.length()>0)
        {
            debugText += "text: " + text+ " depth="+depth + "\n";


            int nextOpen = text.indexOf(open);
            int nextClose = text.indexOf(close);

            if (nextClose == -1 && nextOpen == -1) break;
            //next bit is an open
            if (nextClose == -1 || nextOpen != -1 && nextOpen < nextClose)
            {
                //add the bit we skipped
                if (currBlock != -1 && nextOpen != 0)
                {
                    blocks.set(currBlock, blocks.get(currBlock)+text.substring(0,nextOpen-1));
                }

                //cut off the open bracket and everything before it
                text = text.substring(nextOpen+open.length());

                depth++;
                //if depth is now one, open a new block
                if (depth ==1)
                {

                    blocks.add("");
                    currBlock = blocks.size()-1;
                    continue;
                }

            }

            if (nextOpen== -1 || nextClose != -1 && nextClose < nextOpen)
            {
                //add the bit we skipped
                if (currBlock != -1 && nextClose != 0)
                {
                    blocks.set(currBlock, blocks.get(currBlock)+text.substring(0,nextClose-1));
                }

                //cut off the open bracket and everything before it
                text = text.substring(nextClose+close.length());

                depth--;
                //if depth is now zero, we're not in any block
                if (depth == 0)
                {
                    currBlock = -1;
                    continue;
                }

            }

        }

        return blocks;
    }




    public String getUnicode(String string)
    {

        while(string.contains("\\u"))
        {
            int i = string.indexOf("\\u");
            try
            {
                String numString = string.substring(i+2,i+6);
                int num = Integer.parseInt(numString,16);
                debugText += "num: " + num + "\n";
                char c = (char) num;

                string = string.substring(0,i) + c + string.substring(i+6);

            }
            catch (Exception e)
            {

            }
        }

        return string;

    }

    public String stripNonNum(String numString) {

        String nums = "0123456789";
        String strippedString = numString + "";

        for (char c : numString.toCharArray())
        {
            int index;
            if (!nums.contains(c+"") &&(index = strippedString.indexOf(c)) != -1)
            {
                strippedString = strippedString.substring(0,index) + strippedString.substring(index+1);
            }
        }
        return strippedString;


    }


    public String getLine(String label, String lines)
    {
        StringTokenizer st = new StringTokenizer(lines, "\n");
        return getLine(label, st);
    }

    public String getLine(String label, StringTokenizer lines)
    {
        String line;
        while (lines.hasMoreTokens())
        {
            line = lines.nextToken();
            debugText += line + "\n";
            String[] parts = line.split(":",2);
            if (parts.length<2) continue;
            if (parts[0].endsWith("\""+label+"\""))
            {

                if (parts[1].contains("["))
                {
                    String multiline = "";
                    String tail = line+"\n";
                    while(lines.hasMoreTokens())
                    {
                        line = lines.nextToken();
                        tail+=line +"\n";
                       // if (line.contains("]")) break;
                       // multiline += line + "\n";
                    }

                    multiline= blockify("[", "]",tail).get(0);




                    return multiline;
                }
                return parts[1];
            }

        }

        return "not found";
    }




}
