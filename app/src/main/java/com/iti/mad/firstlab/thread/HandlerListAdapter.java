package com.iti.mad.firstlab.thread;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iti.mad.firstlab.R;
import com.iti.mad.firstlab.list_view.Cake;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by ayman on 2019-01-31.
 */

public class HandlerListAdapter extends ArrayAdapter {
    Context context;
    ArrayList<Cake> cakes;
    LayoutInflater inflter;
    public HandlerListAdapter(@NonNull Context context,  ArrayList<Cake> cakes) {
        super(context, R.layout.list_row,cakes);
        this.context=context;
        this.cakes=cakes;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        View viewRow=view;
        if (view==null){
            inflter= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewRow = inflter.inflate(R.layout.list_row, null);

            viewHolder=new ViewHolder(viewRow);
            viewRow.setTag(viewHolder);

        }else {
            viewHolder= (ViewHolder) viewRow.getTag();
        }

        viewHolder.bind(position);

        return viewRow;


    }





    public  class ViewHolder{
        TextView names;
        TextView descText;
        ImageView image;
        public ViewHolder(View view){
            names = (TextView) view.findViewById(R.id.nameText);
            descText = (TextView) view.findViewById(R.id.descText);
            image = view.findViewById(R.id.imageId);


        }
        void bind(final int position){
            names.setText(cakes.get(position).getCakeName());
            descText.setText(cakes.get(position).getCakeDesc());
            image.setImageResource(cakes.get(position).getImageCake());
            new DownloadImageTask(image).execute(cakes.get(position).getImage());
//            image.setImageBitmap(decodeImageFromFiles(context.getResources(), cakes[position].getImageCake(), 50 ,50));

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    cakes.get(position);
//                    Intent intent =new Intent(context, CountryDetailActivity.class);
//                    intent.putExtra("country",cakes.get(position) );
//                    context.startActivity(intent);
                }
            });

        }



    }


    private class DownloadImageTask extends AsyncTask<String,Void,Bitmap> {
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

        protected void onPostExecute(Bitmap result){
            bmImage.setImageBitmap(result);
        }
    }
}
