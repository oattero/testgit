package com.code.test.myapplication1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    public static final String URL = "http://blog.teamtreehouse.com/api/get_recent_summary/";
    //public static final String URL = "http://tobekaset.iig.in.th/TOBE/index.php/Activity/getall/json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new SimpleTask().execute(URL);
    }

    private class SimpleTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            // Create Show ProgressBar
        }

        protected String doInBackground(String... urls)   {
            String result = "";
            try {

                HttpGet hGet = new HttpGet(urls[0]);
                HttpClient client = new DefaultHttpClient();

                HttpResponse response = client.execute(hGet);

                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200) {
                    InputStream inputStream = response.getEntity().getContent();
                    BufferedReader reader = new BufferedReader
                            (new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result += line;
                    }
                }

            } catch (ClientProtocolException e) {

            } catch (IOException e) {

            }
            return result;
        }

        protected void onPostExecute(String jsonString)  {
            // Dismiss ProgressBar
            showData(jsonString);
        }
    }

    private void showData(String jsonString) {

        Gson gson = new Gson();

        //Deserialization
        Blog blog = gson.fromJson(jsonString, Blog.class);

        StringBuilder builder = new StringBuilder();
        builder.setLength(0);

        List<Post> posts = blog.getPosts();

        for (Post post : posts) {
            builder.append(post.getTitle());
            builder.append("\n\n");
        }

        //Show by select
        Toast.makeText(this, builder.toString(), Toast.LENGTH_LONG).show();

        //Show All
        //Toast.makeText(this, jsonString, Toast.LENGTH_LONG).show();
    }
}