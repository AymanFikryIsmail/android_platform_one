package com.iti.mad.firstlab.retrofit;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iti.mad.firstlab.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ayman on 2019-02-04.
 */

public class RetrofitAdapter extends RecyclerView.Adapter<RetrofitAdapter.MyViewHolder>{

    private Context context;
    private List<CountryClass> countryList =  new ArrayList<>();

    public RetrofitAdapter(Context context,List<CountryClass> countryList){
        this.context = context;
        this.countryList = countryList;
    }

    @Override
    public RetrofitAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);
        return new RetrofitAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(countryList.get(position));
    }


    @Override
    public int getItemCount() {
        return countryList.size();
    }


    //----------------------------------View Holder Class-------------------------------------------
    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView nameText , descText ;
        public ImageView imageId  ;
        public CardView gridCardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.nameText);
            imageId = itemView.findViewById(R.id.imageId);
            descText = itemView.findViewById(R.id.descText);
        }

        public void bind(final CountryClass countryList){

            nameText.setText(countryList.getCountry());
            descText.setText(countryList.getPopulation());
            Glide.with(context).load(countryList.getFlag()).into(imageId);//

        }
    }

}
