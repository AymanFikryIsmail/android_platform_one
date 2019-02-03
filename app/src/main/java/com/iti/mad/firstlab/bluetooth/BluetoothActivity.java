package com.iti.mad.firstlab.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.iti.mad.firstlab.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

public class BluetoothActivity extends AppCompatActivity {

    public BluetoothAdapter mBluetoothAdapter;
    private BluetoothServerSocket mmServerSocket;
    private BluetoothSocket mBluetoothSocket;
    InputStream mInputStream;
    BufferedReader mBufferedReader;
    private static final UUID MY_UUID_SECURE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    TextView text;
    Button serverBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        text=findViewById(R.id.textid);
        serverBtn=findViewById(R.id.serverBtn);




        final Thread connect = new Thread(new Runnable() {
            @Override
            public void run() {
                BluetoothServerSocket serverSocket = null;

                BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                BluetoothSocket bluetoothSocket = null;
                try {

                    serverSocket = bluetoothAdapter.listenUsingRfcommWithServiceRecord("server", MY_UUID_SECURE);
                    bluetoothSocket = serverSocket.accept(); // blocking call, until a connection is established.
                    Log.i("TAG", "serverSocket accept");

                } catch (IOException e) {
                    Log.e("TAG", "IOException");
                }

                // If a connection was accepted
                if (bluetoothSocket != null) {
                    // Do work to manage the connection (in a separate thread)
                    try {
                        mInputStream=bluetoothSocket.getInputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //if(mInputStream.available()>0){
                    mBufferedReader=new BufferedReader(new InputStreamReader(mInputStream));
                    String  data = null;
                    try {
                        data = mBufferedReader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    text.setText(data);
                }


                try {
                    bluetoothSocket.close();
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        connect.start();



















        serverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (mBluetoothAdapter == null) {
                    text.setText("Does not support bluetooth");
                    return;
                }

                mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
                try {
                    mmServerSocket=mBluetoothAdapter.listenUsingRfcommWithServiceRecord("server",MY_UUID_SECURE);
                    mBluetoothAdapter.cancelDiscovery();
                    mBluetoothSocket=mmServerSocket.accept();
                    mInputStream=mBluetoothSocket.getInputStream();
                    //if(mInputStream.available()>0){
                    mBufferedReader=new BufferedReader(new InputStreamReader(mInputStream));
                  String  data = mBufferedReader.readLine();
                    text.setText(data);
                    //}
//                    if(mInputStream.available()>0){
//                        data=mBufferedReader.readLine();
//                        tv2.setText(data);
//                        x++;
//                    }

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });

    }
}
