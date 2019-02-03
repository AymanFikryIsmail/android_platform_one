package com.iti.mad.firstlab.list_view;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.iti.mad.firstlab.R;
import com.iti.mad.firstlab.fragment_lab.FirstFragment;
import com.iti.mad.firstlab.fragment_lab.SecondFragment;

public class FragmentListActivity extends AppCompatActivity {

    ListView listView;
    private  Cake[] cakes;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

           Cake cake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_list);


        if (savedInstanceState==null) {
            DetailFragment f1 = new DetailFragment();
            fm = getSupportFragmentManager();
            fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentId, f1, "DetailFragment");
            fragmentTransaction.commit();
        }
        cakes= new Cake[]{
                new Cake(R.drawable.img1, "cake 1", " cake desc") ,
                new Cake(R.drawable.img2, "cake 2", " cake desc") ,
                new Cake(R.drawable.img3, "cake 3", " cake desc") ,
                new Cake(R.drawable.img1, "cake 4", " cake desc") ,
                new Cake(R.drawable.img2, "cake 5", " cake desc") ,
                new Cake(R.drawable.img3, "cake 6", " cake desc") ,
                new Cake(R.drawable.img2, "cake 7", " cake desc") ,
        };
        listView=findViewById(R.id.listView);

        ListHolderAdapter arrayAdapter=new ListHolderAdapter(FragmentListActivity.this,  cakes);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                 cake= cakes[i];
                DetailFragment f3= (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentId);
                f3.setView(cake);
                listView.setVisibility(View.INVISIBLE);
                if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){

                    listView.setVisibility(View.VISIBLE);
                }
                else {
                    listView.setVisibility(View.GONE);

                }

            }
        });

//        if (savedInstanceState!=null){
//            String name =savedInstanceState.get("name")+"";
//            String desc =savedInstanceState.get("desc")+"";
//           int image= (Integer) savedInstanceState.get("image");
//
//            DetailFragment f3= (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentId);
//            f3.setView( new Cake(image , name , desc));
//        }
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String name =savedInstanceState.get("name")+"";
            String desc =savedInstanceState.get("desc")+"";
           int image= (Integer) savedInstanceState.get("image");

            DetailFragment f3= (DetailFragment) getSupportFragmentManager().findFragmentByTag("DetailFragment");
            f3.setView( new Cake(image , name , desc));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", cake.getCakeName());
        outState.putString("desc", cake.getCakeDesc());
        outState.putInt("image", cake.getImageCake());

    }
}
