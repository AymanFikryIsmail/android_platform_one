package com.iti.mad.firstlab.list_view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iti.mad.firstlab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    TextView names;
    TextView descText;
    ImageView image;
    public DetailFragment() {
        // Required empty public constructor
    }

    Cake cake;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_detail, container, false);
        names = (TextView) view.findViewById(R.id.nameText);
        descText = (TextView) view.findViewById(R.id.descText);
        image = view.findViewById(R.id.imageId);

//        if (savedInstanceState!=null){
//            names.setText(savedInstanceState.get("name")+"");
//            descText.setText(savedInstanceState.get("desc")+"");
//           // image.setImageResource((Integer) savedInstanceState.get("image"));
//        }
        return view;
    }

    void setView( Cake cake){
        this.cake=cake;
        names.setText(cake.getCakeName());
        descText.setText(cake.getCakeDesc());
            image.setImageResource(cake.getImageCake());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", cake.getCakeName());
        outState.putString("desc", cake.getCakeDesc());
        outState.putInt("image", cake.getImageCake());

    }

}
