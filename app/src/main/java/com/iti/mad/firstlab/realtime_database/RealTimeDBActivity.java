package com.iti.mad.firstlab.realtime_database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iti.mad.firstlab.MainActivity;
import com.iti.mad.firstlab.R;

import java.util.ArrayList;

public class RealTimeDBActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ListView msgList;
    private  EditText msgText;
    MessageAdapter messageAdapter;
    private Button sendMsg;
    ArrayList<MessageClass> messageClassArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_time_db);
         msgText=findViewById(R.id.msgEditTxt);
        msgList=findViewById(R.id.messageList);

       String uid= getIntent().getStringExtra("uid");
        sendMsg=findViewById(R.id.sendMsg);
//        messageAdapter=new MessageAdapter(this);
//        msgList.setAdapter(messageAdapter);
        database= FirebaseDatabase.getInstance();
        myRef = database.getReference().child("MessageClass");
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MessageClass msg=new MessageClass("title",msgText.getText().toString());
                myRef.child(myRef.push().getKey()).equalTo("").getRef().setValue(msg);
            }
        });



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("CHAT","SUCCESS!");
               // messageAdapter.clearData();
                messageClassArrayList.clear();
                for(DataSnapshot item : dataSnapshot.getChildren()) {
                    MessageClass data = item.getValue(MessageClass.class);
//                    Toast.makeText(RealTimeDBActivity.this,""+item.getValue(MessageClass.class),Toast.LENGTH_LONG).show();

//                    messageAdapter.addData(data);
                    messageClassArrayList.add(data);
                }
                messageAdapter=new MessageAdapter(RealTimeDBActivity.this, messageClassArrayList);
                msgList.setAdapter(messageAdapter);
//                messageAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("CHAT","ERROR: " + databaseError.getMessage());
            }
        });
    }
}
