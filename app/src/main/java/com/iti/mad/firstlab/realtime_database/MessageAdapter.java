package com.iti.mad.firstlab.realtime_database;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iti.mad.firstlab.R;
import com.iti.mad.firstlab.list_view.Cake;
import com.iti.mad.firstlab.list_view.ListHolderAdapter;

import java.util.ArrayList;

/**
 * Created by ayman on 2019-02-06.
 */

public class MessageAdapter  extends ArrayAdapter {
    Context context;
    ArrayList<MessageClass> messageClassArrayList=new ArrayList<>();
    LayoutInflater inflter;
    public MessageAdapter(@NonNull Context context,ArrayList<MessageClass> messageClassArrayList) {
        super(context, R.layout.list_row,messageClassArrayList);
        this.context=context;
        this.messageClassArrayList=messageClassArrayList;
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

    public void clearData() {
      //  messageClassArrayList.clear();
    }

    public void addData(MessageClass data) {
        messageClassArrayList.add(data);
    }
    public  class ViewHolder{
        TextView names;
        TextView descText;
        public ViewHolder(View view){
            names = (TextView) view.findViewById(R.id.nameText);
            descText = (TextView) view.findViewById(R.id.descText);
        }
        void bind(int position){
            names.setText(messageClassArrayList.get(position).getText());
            descText.setText(messageClassArrayList.get(position).getName());
        }


    }
}
