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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class SelectSetActivity extends AppCompatActivity implements NetworkListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_set);


    }

    public void search(View v)
    {
        //first, show that you're searching, and disable stuff
        Button button = (Button) findViewById(R.id.search_button);
        button.setText("Searching");
        button.setEnabled(false);

        //get the search feilds
        String query = ((TextView) findViewById(R.id.query_input)).getText().toString();
        String author = ((TextView) findViewById(R.id.author_input)).getText().toString();
        Network network = new Network();
        String url = network.searchQueryURL(query, author,1);

        network.networkListener = new NetworkListener() {
            @Override
            public void onPostExecute(String result) {
                onSearchComplete(result);
            }
        };
        network.execute(url);

    }


    public void onSearchComplete(String result)
    {
        Log.d("select", "search complete");
        SearchParser p = new SearchParser();

        ArrayList<SearchParser.Result> results = p.parseSearchResult(result);
        Log.d("select", results.size() + "results found");
        for (SearchParser.Result item : results)
        {
            addResultButton(item);
        }

    }


    public ArrayList<Button> searchItemButtons = new ArrayList<>();

    public void addResultButton(SearchParser.Result item)
    {
        LinearLayout layout = (LinearLayout) findViewById(R.id.linear_layout);


        Button b = new Button(this);
        Log.d("select", "adding button: " + item.title);
        b.setText(item.title + "    ("+ item.numCards + "\n" + item.creator);
        b.setId(item.id);
        layout.addView(b);
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
