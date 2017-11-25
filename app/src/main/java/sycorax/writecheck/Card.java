package sycorax.writecheck;

import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.SystemClock;
import android.util.Log;

import java.util.Random;

import static android.R.attr.max;
import static android.graphics.Color.argb;


/**
 * Created by Bethany on 25/11/2017.
 */

class Card{
    String front;
    String back;
    float strength; // [0,1]

    long timeLastViewed;
    long timeToForget; //estimated time that the use will rembember for

    Color color;

    public Card(String front, String back)
    {
        this.front=front;
        this.back = back;
        strength = 0;
        timeToForget = 10*1000; //10 seconds
        timeLastViewed = System.currentTimeMillis();
    }

    /**
     *
     * @param confidence [0,1]
     * @param timeViewed
     */
    public void updateStrength(float confidence, long timeViewed)
    {
        long timeSinceViewed = (timeViewed - timeLastViewed);

        Log.d("color", "time last viewed: " + timeLastViewed +
                ", timeviewed: " + timeViewed + ", diff: "+ timeSinceViewed/1000);


        if (confidence >= 0.5 && timeSinceViewed > timeToForget )
        {
            strength =  Math.min(strength+0.1f, 1);
            timeToForget = Math.max(timeSinceViewed, 2* timeToForget);

        }


        else if (confidence <0.5)
        {
            strength = Math.max(strength-0.1f, 0);
            timeToForget = timeToForget/2;
        }

        timeLastViewed = timeViewed;
    }


    public void setRandomStrength()
    {
        this.strength = (System.currentTimeMillis()%11)*0.1f;
    }

    public int getColor(long timeViewed)
    {
        if ((timeLastViewed - timeViewed) > timeToForget)
        {
            Log.d("color", "no time to forget");
            return (calcColor(strength-0.5f));
        }
        return calcColor(strength);
    }

    private int calcColor(float strength) {

        Log.d("color", "getting color, strength="+strength);
        int green = 200;
        int red = 200;
        int blue = 255;
        if (strength < 0)
        {

            return (new Color()).argb(255, red,green,blue);
        }

        int bonus = (int)(strength * 200);
        red = red-bonus;
        blue = blue - bonus;
        Log.d("color", "r="+red+" g="+green+ " b="+blue);
        return (new Color()).argb(255, red,green,blue);





    }


}
