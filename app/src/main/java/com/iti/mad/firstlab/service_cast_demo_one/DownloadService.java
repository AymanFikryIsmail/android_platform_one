package com.iti.mad.firstlab.service_cast_demo_one;

import android.app.Service;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DownloadService extends Service {
    public DownloadService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // may put register broadcast her
        // then unregister in on destroy instead of mainfeast
    }

    @Override
    public IBinder onBind(Intent intent) {

        return  null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new DownloadImageTask().execute("https://developer.android.com/images/activity_lifecycle.png");

        return Service.START_NOT_STICKY;
    }


    private class DownloadImageTask extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... urls) {

            String urldisplay = urls[0];
            Bitmap mIcon11 = null;

            String imagePath = null;

            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                imagePath=saveToInternalStorage(mIcon11,getApplicationContext() , "image");
            } catch (IOException e) {
                Log.e("Error", e.getMessage());
            }


            return imagePath;
        }

        protected void onPostExecute(String result){
            Intent broadCastIntent = new Intent();
            broadCastIntent.setAction("com.android.downloadimage");
            broadCastIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            broadCastIntent.putExtra("imagePath", result+"/image");
            sendBroadcast(broadCastIntent);

        }
    }



    public String saveToInternalStorage(Bitmap bitmapImage, Context context, String name){
        ContextWrapper cw = new ContextWrapper(context);
        // path to /data/data/yourapp/app_data/imageDir
        String name_="imageDownloded"; //Folder name in device android/data/
        File directory = cw.getDir(name_, Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,name);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("absolutepath ", directory.getAbsolutePath());//getName
        return directory.getAbsolutePath();
    }
}
