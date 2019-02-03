package com.iti.mad.firstlab.thread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.iti.mad.firstlab.R;
import com.iti.mad.firstlab.list_view.Cake;
import com.iti.mad.firstlab.list_view.ListHolderAdapter;
import com.iti.mad.firstlab.list_view.ListViewActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HandlerActivity extends AppCompatActivity {

    ListView listView;
    private ArrayList<Cake> cakes;
    Handler handler;

    TextView names;
    TextView descText;
    ImageView image;
    Button nextBtn,prevBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        names = (TextView) findViewById(R.id.nameText);
        descText = (TextView) findViewById(R.id.descText);
        image = findViewById(R.id.imageId);
        nextBtn = findViewById(R.id.nextBtn);
        prevBtn = findViewById(R.id.prevBtn);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {

                HandlerListAdapter arrayAdapter = new HandlerListAdapter(HandlerActivity.this, cakes);
                listView.setAdapter(arrayAdapter);

                return false;
            }
        });

        cakes = new ArrayList<>();
        listView = findViewById(R.id.listView);
//        ListAdapter arrayAdapter=new ListAdapter(ListViewActivity.this,  cakes);
//        HandlerListAdapter arrayAdapter = new HandlerListAdapter(HandlerActivity.this, cakes);
//        listView.setAdapter(arrayAdapter);


        getCountries();

    }

    void getCountries() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                getData("http://www.androidbegin.com/tutorial/jsonparsetutorial.txt");

//                  Update the value background thread to UI thread
                handler.sendEmptyMessage(0);
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        //  mProgressBar.setProgress(currentProgressCount);
//                    }
//                });
            }
        }).start();
    }



    void  getData(String urls){
        HttpURLConnection urlConnection = null;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(urls);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            StringBuffer buffer = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String finalJson = buffer.toString();


            JSONObject jsono = new JSONObject(finalJson);
            JSONArray jarray = jsono.getJSONArray("worldpopulation");
            for (int i = 0; i < jarray.length(); i++) {

                JSONObject object = jarray.getJSONObject(i);

                Cake cake = new Cake(
                        object.getString("flag"),
                        object.getString("country"),
                        object.getString("population")
                );
                cakes.add(cake);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }


    }
}
