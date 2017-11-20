package sycorax.writecheck;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class SelectSetActivity extends AppCompatActivity implements NetworkListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_set);


    }

    public void onButton(View v)
    {
        try{
            Network network = new Network();
            network.networkListener = this;
            network.execute(network.setURL(213978524));
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

    }



    public void viewCards(String cardSetSring)
    {


        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("cardSetString", cardSetSring);
        startActivity(intent);

    }


    @Override
    public void onPostExecute(String result) {

        Log.d("select", "finished network");
        viewCards(result);

        //Log.d("select", "parsing network");

        /*
        Log.d("select", "title: " + cardSet.title);

        for (CardSet.Card card : cardSet.cards)
        {
            Log.d("select", card.front + ", " + card.back);
        }


        Log.d("select" , p.debugText);
        */


    }
}
