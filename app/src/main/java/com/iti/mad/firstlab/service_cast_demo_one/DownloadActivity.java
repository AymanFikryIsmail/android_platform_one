package com.iti.mad.firstlab.service_cast_demo_one;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iti.mad.firstlab.R;

public class DownloadActivity extends AppCompatActivity {


    Button downloadBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        downloadBtn=findViewById(R.id.downloadBtn);
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DownloadActivity.this, DownloadService.class);
                startService(intent);
            }
        });
    }
}
