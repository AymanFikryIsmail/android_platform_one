package com.iti.mad.firstlab.thread;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.iti.mad.firstlab.R;
import com.iti.mad.firstlab.fragment_lab.FragmentInterface;
import com.iti.mad.firstlab.list_view.Cake;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CountryListFragment extends Fragment {


    public CountryListFragment() {
        // Required empty public constructor
    }


    ListView listView;
    private ArrayList<Cake> cakes;
    Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    FragmentInterface fragmentInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_country_list, container, false);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {

                HandlerListAdapter arrayAdapter = new HandlerListAdapter(getContext(), cakes);
                listView.setAdapter(arrayAdapter);

                return false;
            }
        });

        cakes = new ArrayList<>();
        listView = view.findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                fragmentInterface= (FragmentInterface) getActivity();
              ((FragmentInterface) getActivity()).redirect(cakes.get(i));
            }
        });

        getCountries();
        return  view;
    }


    void getCountries() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                getData("http://www.androidbegin.com/tutorial/jsonparsetutorial.txt");

//                  Update the value background thread to UI thread
                handler.sendEmptyMessage(0);
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
