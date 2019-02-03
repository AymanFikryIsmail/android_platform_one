package com.iti.mad.firstlab.service_cast_demo_one;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.iti.mad.firstlab.R;

import java.io.File;
import java.io.FileInputStream;

public class ResultActivity extends AppCompatActivity {

    String imagePath;
    ImageView imageId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        imageId=findViewById(R.id.imageId);


        if(getIntent()!=null){
             imagePath=getIntent().getStringExtra("imagePath");

        }
       Bitmap bitmap= loadImageBitmap(imagePath);
        File imgFile = new File(imagePath);

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageId.setImageBitmap(myBitmap);

        }
        //imageId.setImageBitmap(bitmap);
    }

    public Bitmap loadImageBitmap( String imageName) {
        Bitmap bitmap = null;
        FileInputStream fiStream;
        try {
            fiStream    = openFileInput(imageName);
            bitmap      = BitmapFactory.decodeStream(fiStream);
            fiStream.close();
        } catch (Exception e) {
            Log.d("saveImage", "Exception 3, Something went wrong!");
            e.printStackTrace();
        }
        return bitmap;
    }
}
