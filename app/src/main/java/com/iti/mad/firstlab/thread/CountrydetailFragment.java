package com.iti.mad.firstlab.thread;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iti.mad.firstlab.R;
import com.iti.mad.firstlab.list_view.Cake;

import java.io.IOException;
import java.io.InputStream;

public class CountrydetailFragment extends Fragment {

    public CountrydetailFragment() {
        // Required empty public constructor
    }

    TextView names;
    TextView descText;
    ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_countrydetail, container, false);
        names = view.findViewById(R.id.nameText);
        descText = view.findViewById(R.id.descText);
        image = view.findViewById(R.id.imageId);

        return view;
    }


    void changeCountry(Cake country) {

        names.setText(country.getCakeName());
        descText.setText(country.getCakeDesc());
        new DownloadImageTask(image).execute(country.getImage());
    }





    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {

            String urldisplay = urls[0];
            Bitmap mIcon11 = null;


            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (IOException e) {
                Log.e("Error", e.getMessage());
            }


            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}