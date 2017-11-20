package sycorax.writecheck;

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
    }


    public void addCard(String front, String back)
    {
        Card card = new Card(front, back);
        cards.add(card);

    }

    class Card{
        String front;
        String back;
        int strength;
        public Card(String front, String back)
        {
            this.front=front;
            this.back = back;
            strength = 0;
        }
    }




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
