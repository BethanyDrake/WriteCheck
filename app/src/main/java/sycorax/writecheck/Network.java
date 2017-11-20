package sycorax.writecheck;

import android.os.AsyncTask;
import android.os.Debug;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Bethany on 19/11/2017.
 */

public class Network extends AsyncTask<String, Void, String>{

    public String authorizeUrl = "https://quizlet.com/authorize?client_id=EmuvkFpAYY&response_type=code&scope=read%20write_set";
    public String tokenUrl = "https://api.quizlet.com/oauth/token";
    public String get1 = "https://api.quizlet.com/2.0/sets/415?client_id=EmuvkFpAYY&whitespace=1";
    public NetworkListener networkListener = null;


    public String setURL(int setID)
    {
        String s = "https://api.quizlet.com/2.0/sets/";
        s = s + setID;
        s = s + "?client_id=EmuvkFpAYY&whitespace=1";
        return s;

    }




    public Network() {


    }

    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        try {
            Log.d("network", "started");
            URL url = new URL(urls[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            Log.d("network", "connected");
            InputStream is = urlConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bf = new BufferedReader(isr);
            String line;
            while((line = bf.readLine()) != null)
            {
                Log.d("output", line);
                result += line+ "\n";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("network", "finished");
        return result;
    }

    @Override
    protected void onPostExecute(String result)
    {
        if (networkListener != null)
        {
            networkListener.onPostExecute(result);
        }

    }
}
