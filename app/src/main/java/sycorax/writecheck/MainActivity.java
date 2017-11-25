package sycorax.writecheck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CanvasView canvasView;

    CardSet cardSet;
    CardSet.Card currCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        canvasView = (CanvasView) findViewById(R.id.canvas);
        textView = (TextView) findViewById(R.id.textView);

        //convert the fetched data into something useful
        CardSetParser p = new CardSetParser();

        String cardSetString = getIntent().getStringExtra("cardSetString");
        cardSet = p.parseCardSet(cardSetString);
        nextCard(null);

    }



    TextView textView;

    public void nextCard(View v)
    {

        currCard= cardSet.getNext();
        String t = currCard.front;
        textView.setText(t);
        flipped = false;

    }

    public void prevCard(View v)
    {

        currCard= cardSet.getPrev();
        String t = currCard.front;
        textView.setText(t);
        flipped = false;


    }


    public boolean flipped;
    public void flipCard(View v)
    {
        flipped = !flipped;
        if (flipped)
        {
            String t = currCard.back;
            textView.setText(t);
        }

        else
        {
            String t = currCard.front;
            textView.setText(t);

        }

    }

    public void clearCanvas(View v)
    {
        canvasView.clearCanvas();

    }

    public void back(View v)
    {
        Intent intent = new Intent(this, SelectSetActivity.class);
        startActivity(intent);
    }

    public void openSettings(View v)
    {
        //TODO implement settings menu
    }

}
