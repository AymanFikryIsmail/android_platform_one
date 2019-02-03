package com.iti.mad.firstlab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    public final static String mobile="mobile";
    public final static String message="message";

    Button nextBtn;
    Button closeBtn;
    EditText mobileText;
    EditText msgText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nextBtn=findViewById(R.id.nextBtn);
        closeBtn=findViewById(R.id.closeBtn);
        mobileText=findViewById(R.id.mobileId);
        msgText=findViewById(R.id.messageId);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this, DetailsActivity.class);
                i.putExtra(mobile,mobileText.getText().toString());
                i.putExtra(message,msgText.getText().toString());
                startActivity(i);
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              finish();
            }
        });
    }
}
