package sycorax.writecheck;

import java.util.ArrayList;

/**
 * Created by Bethany on 19/11/2017.
 */

public class CardSet {

    class Card{
        String front;
        String back;
        int strength;
    }

    ArrayList<Card> cards;


    public void updateStrength(Card card, int strength)
    {

    }

    private void moveToBack(Card card)
    {
        cards.remove(card);
        cards.add(card);
    }
    private void moveToPos(Card card, int pos)
    {
        cards.remove(card);
        cards.add(pos,card);

    }




    public void shuffle()
    {

    }

    public Card getNext()
    {
        return null;
    }

    public Card getPrev()
    {
        return null;
    }


}