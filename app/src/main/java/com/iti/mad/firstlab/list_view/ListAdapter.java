package com.iti.mad.firstlab.list_view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iti.mad.firstlab.R;

/**
 * Created by ayman on 2019-01-30.
 */

public class ListAdapter extends ArrayAdapter {

    Context context;
    Cake[] cakes;
    LayoutInflater inflter;
    public ListAdapter(@NonNull Context context,  Cake[] cakes) {
        super(context, R.layout.list_row,cakes);
        this.context=context;
        this.cakes=cakes;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        inflter= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflter.inflate(R.layout.list_row, null);
        TextView names = (TextView) view.findViewById(R.id.nameText);
        TextView descText = (TextView) view.findViewById(R.id.descText);
        ImageView image = view.findViewById(R.id.imageId);

        names.setText(cakes[position].getCakeName());
        descText.setText(cakes[position].getCakeDesc());
        image.setImageResource(cakes[position].getImageCake());

        return view;


    }
}
