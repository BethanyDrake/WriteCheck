package sycorax.writecheck;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static android.R.attr.src;
import static org.junit.Assert.*;

/**
 * Created by Bethany on 20/11/2017.
 */
public class ParserTest {


    @Test
    public void getUnicode() throws Exception {
        Parser p = new Parser();
        String res = p.getUnicode("\\u006C");
        if (! res.equals("l"))
        {
            fail(res);
        }

    }

    @Test
    public void getLine() throws Exception {

        StringTokenizer st = new StringTokenizer(getTestString(), "\n");
        Parser p = new Parser();
        String line = p.getLine("title", st);
        fail("output: " + line+"\n debug text:\n" + p.debugText);




    }


    public String getTestString()
    {

        String doc = "";
        try {
            FileReader file = new FileReader("C:\\Users\\Bethany\\AndroidStudioProjects\\WriteCheck\\app\\src\\test\\java\\sycorax\\writecheck\\testString");
            BufferedReader bf = new BufferedReader(file);
            String line;
            while((line = bf.readLine())!=null)
            {
                doc += line + "\n";
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc;

    }


    @Test
    public void readfile()
    {
        if (getTestString() == null) fail("null");
        if (getTestString().length() <= 0) fail("no length");
    }

    @Test
    public void parseCardSet() throws Exception {
        Parser p = new Parser();
        CardSet cardSet = p.parseCardSet(getTestString());
        if (!cardSet.title.equals("french animals 3.0")) fail("title: " +cardSet.title);
        if (cardSet.cards.size() != 3) fail("numcards: " + cardSet.cards.size() );



    }

    @Test
    public void blockify() throws Exception {
        Parser p = new Parser();
        ArrayList<String> blocks = p.blockify("(",")", "()()()(())");
        if (blocks.size() != 4) fail("size: " +blocks.size() + "first block: "+ blocks.get(0) + "\n" + p.debugText);
    }


    @Test
    public void blockify2() throws Exception {
        Parser p = new Parser();
        String s = getTestString();
        ArrayList<String> blocks = p.blockify("{","}",s);
        if (blocks.size() != 4) fail("size: " +blocks.size() + "first block: "+ blocks.get(0) + "\n" + p.debugText);
    }

}