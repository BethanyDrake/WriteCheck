package sycorax.writecheck;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Bethany on 19/11/2017.
 */

public class CardSet {

    public String title;
    ArrayList<Card> cards;


    public CardSet(String title)
    {
        this.title = title;
        cards = new ArrayList<>();
    }


    public void addCard(String front, String back)
    {
        Card card = new Card(front, back);
        cards.add(card);

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
        Card card = cards.get(0);
        moveToBack(card);
        return card;

    }

    public Card getPrev()
    {
        Card card = cards.get(cards.size()-1);
        moveToPos(card, 0);
        return card;

    }






}
