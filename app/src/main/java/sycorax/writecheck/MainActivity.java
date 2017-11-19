package sycorax.writecheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CanvasView canvasView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        canvasView = (CanvasView) findViewById(R.id.canvas);
        textView = (TextView) findViewById(R.id.textView);
    }



    TextView textView;
    int i = 0;
    public void nextCard(View v)
    {
        i++;
        String t = "hi" + i;
        textView.setText(t);

    }

    public void flipCard(View v)
    {

    }

    public void clearCanvas(View v)
    {
        canvasView.clearCanvas();
        nextCard(v);
    }
}
