package com.iti.mad.firstlab;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iti.mad.firstlab.data_base.DatabaseAdapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static com.iti.mad.firstlab.LoginActivity.message;
import static com.iti.mad.firstlab.LoginActivity.mobile;

public class DetailsActivity extends AppCompatActivity {

    EditText mobileText;
    EditText msgText;
    Button closeBtn ,nextBtn , writeSharedBtn , readSharedBtn , writeISBtn , readISBtn , writeSQLBtn ,readSQLBtn

            ,writeExternaldBtn , readExternalBtn;
    SharedPreferences pref;
    SharedPreferences.Editor editor;


    FileInputStream fileIn= null;
    FileOutputStream fOut=null;

    DatabaseAdapter databaseAdapter;


    private static final int REQUEST_ID_READ_PERMISSION = 100;
    private static final int REQUEST_ID_WRITE_PERMISSION = 200;

    private final String fileName = "note.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mobileText=findViewById(R.id.mobileId);
        msgText=findViewById(R.id.messageId);

        writeExternaldBtn=findViewById(R.id.writeExternaldBtn);
        readExternalBtn=findViewById(R.id.readExternalBtn);


        writeExternaldBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                askPermissionAndWriteFile();
            }
        });


        readExternalBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                askPermissionAndReadFile();
            }
        });



        writeSharedBtn=findViewById(R.id.writeSharedBtn);
        readSharedBtn=findViewById(R.id.readSharedBtn);
        writeISBtn=findViewById(R.id.writeISBtn);
        readISBtn=findViewById(R.id.readISBtn);
        writeSQLBtn=findViewById(R.id.writeSQLBtn);
        readSQLBtn=findViewById(R.id.readSQLBtn);

        nextBtn=findViewById(R.id.nextBtn);
        closeBtn=findViewById(R.id.closeBtn);

        Intent i =getIntent();
        mobileText.setText(i.getStringExtra(mobile));
        msgText.setText(i.getStringExtra(message));

         pref = getApplicationContext().getSharedPreferences("Mobile", Context.MODE_PRIVATE); // 0 - for private mode
        editor = pref.edit();


         databaseAdapter= new DatabaseAdapter(DetailsActivity.this);

        writeSharedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putString("mobile", mobileText.getText().toString()); // Storing boolean - true/false
                editor.putString("message",  msgText.getText().toString()); // Storing string
                editor.commit();
                mobileText.setText("");
                msgText.setText("");
            }
        });

        readSharedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mobileText.setText(pref.getString("mobile", ""));
                msgText.setText(pref.getString("message", ""));
            }
        });





        writeISBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    fOut = openFileOutput("myFile.txt",Context.MODE_PRIVATE);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                String mobileTextStr =  mobileText.getText().toString();
                String msgTextStr =  msgText.getText().toString();

                try {
                    fOut.write(mobileTextStr.getBytes());
                    fOut.write("\n".getBytes());
                    fOut.write(msgTextStr.getBytes());

                } catch (IOException e) {
                    e.printStackTrace();
                }
//                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fOut);
//
//                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
//
//                try {
//                    bufferedWriter.write(mobileTextStr);
//                    bufferedWriter.write("\n");
//
//                    bufferedWriter.write(msgTextStr);
//                    bufferedWriter.flush();
//                    bufferedWriter.close();
//                    outputStreamWriter.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                mobileText.setText("");
                msgText.setText("");
            }
        });
        readISBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//
                try {
                    fileIn = openFileInput("myFile.txt");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                    if (fileIn != null) {
                        int c;
                        String temp="";
                        try {
                            while( (c = fileIn.read()) != -1){
                                temp = temp + Character.toString((char)c);
                            }
                            mobileText.setText(temp.split(",")[0]);
                            msgText.setText(temp.split(",")[1]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            try {
                                fileIn.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                    }
//                        InputStreamReader inputStreamReader = new InputStreamReader(fileIn);
//                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//
//                        String lineData = bufferedReader.readLine();
//                        while (lineData != null) {
//                            mobileText.setText(lineData);
//                           lineData = bufferedReader.readLine();
//                            msgText.setText(lineData);
//                            lineData = bufferedReader.readLine();
//                        }
//                    }


            }
        });
        writeSQLBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseAdapter.createRow(1,mobileText.getText().toString() , msgText.getText().toString());
                mobileText.setText("");
                msgText.setText("");

            }
        });

        readSQLBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String row =databaseAdapter.RetrieveRow();

                mobileText.setText(row.split(" ")[0]);
                msgText.setText(row.split(" ")[1]);
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(DetailsActivity.this, CunterActivity.class);
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


    private void askPermissionAndWriteFile() {
        boolean canWrite = this.askPermission(REQUEST_ID_WRITE_PERMISSION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (canWrite) {
            this.writeFile();
        }
    }

    private void askPermissionAndReadFile() {
        boolean canRead = this.askPermission(REQUEST_ID_READ_PERMISSION,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        //
        if (canRead) {
            this.readFile();
        }
    }

    private boolean askPermission(int requestId, String permissionName) {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            // Check if we have permission
            int permission = ActivityCompat.checkSelfPermission(this, permissionName);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // If don't have permission so prompt the user.
                this.requestPermissions( new String[]{permissionName}, requestId );
                return false;
            }
        }
        return true;
    }


    // When you have the request results
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //
        // Note: If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0) {
            switch (requestCode) {
                case REQUEST_ID_READ_PERMISSION: {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        readFile();
                    }
                }
                case REQUEST_ID_WRITE_PERMISSION: {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        writeFile();
                    }
                }
            }
        } else {
            Toast.makeText(getApplicationContext(), "Permission Cancelled!", Toast.LENGTH_SHORT).show();
        }
    }


    private void writeFile() {

        File extStore = Environment.getExternalStorageDirectory();
        // ==> /storage/emulated/0/note.txt
        String path = extStore.getAbsolutePath() + "/" + fileName;
        Log.i("ExternalStorageDemo", "Save to: " + path);

        String data = mobileText.getText().toString();

        try {
            File myFile = new File(path);
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(data);
            myOutWriter.close();
            fOut.close();

            Toast.makeText(getApplicationContext(), fileName + " saved", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readFile() {

        File extStore = Environment.getExternalStorageDirectory();
        // ==> /storage/emulated/0/note.txt
        String path = extStore.getAbsolutePath() + "/" + fileName;
        Log.i("ExternalStorageDemo", "Read file: " + path);

        String s = "";
        String fileContent = "";
        try {
            File myFile = new File(path);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(
                    new InputStreamReader(fIn));

            while ((s = myReader.readLine()) != null) {
                fileContent += s + "\n";
            }
            myReader.close();

            this.mobileText.setText(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), fileContent, Toast.LENGTH_LONG).show();
    }


}
