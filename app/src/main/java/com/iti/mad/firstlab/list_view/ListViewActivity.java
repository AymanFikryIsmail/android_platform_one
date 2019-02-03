package com.iti.mad.firstlab.list_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.iti.mad.firstlab.R;

public class ListViewActivity extends AppCompatActivity {

    ListView listView;
    private  Cake[] cakes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

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
//        ListAdapter arrayAdapter=new ListAdapter(ListViewActivity.this,  cakes);
        ListHolderAdapter arrayAdapter=new ListHolderAdapter(ListViewActivity.this,  cakes);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            }
        });
    }
}
