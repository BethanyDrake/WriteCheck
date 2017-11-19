package sycorax.writecheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CanvasView canvasView;
    Network network;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        canvasView = (CanvasView) findViewById(R.id.canvas);
        textView = (TextView) findViewById(R.id.textView);

        network = new Network();


    }



    TextView textView;
    int i = 0;
    public void nextCard(View v)
    {
        i++;
        String t = "hi" + i;
        textView.setText(t);


    }

    public void prevCard(View v)
    {

        i--;
        String t = "hi" + i;
        textView.setText(t);

        try{
            network.execute(network.get1);
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    public void flipCard(View v)
    {
        String t = "嗨" + i;
        textView.setText(t);
    }

    public void clearCanvas(View v)
    {
        canvasView.clearCanvas();
        nextCard(v);
    }
}
