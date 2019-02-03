package com.iti.mad.firstlab.bluetooth;

import android.bluetooth.BluetoothSocket;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.iti.mad.firstlab.R;

import java.io.IOException;

public class ClientBluetoothActivity extends AppCompatActivity {


    TextView receivedTxt;
    Button clientBtn;
    BluetoothSocket btSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_bluetooth);
        receivedTxt=findViewById(R.id.textid);
        clientBtn=findViewById(R.id.serverBtn);
       // btSocket= btDevice. createRfcommSocketToServiceRecord(UUID);
        try {
            btSocket.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
