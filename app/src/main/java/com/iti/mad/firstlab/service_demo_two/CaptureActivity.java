package com.iti.mad.firstlab.service_demo_two;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.iti.mad.firstlab.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CaptureActivity extends AppCompatActivity {

    Button capture;
    ImageView imageView1;
    Uri file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        capture = findViewById(R.id.captureId);
        imageView1 = findViewById(R.id.imageView1);

        if (Intent.ACTION_VIEW.equals(getIntent().getAction())) {
            final Uri imageUri = getIntent().getData();
            InputStream imageStream = null;
            try {
                imageStream = getContentResolver().openInputStream(imageUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            imageView1.setImageBitmap(selectedImage);

//            Uri uri = getIntent().getData();
//            Uri selectedImageUri = getIntent().getData();
//            imageView1.setImageURI(selectedImageUri);
//
//            String  filestring = selectedImageUri.getPath();
//
//            Bitmap thumbnail = BitmapFactory.decodeFile(filestring);

           // imageView1.setImageBitmap(thumbnail);

            //  Uri imgUri=Uri.parse("android.resource://my.package.name/"+R.drawable.image);
//            imageView1.setImageURI(null);
//            imageView1.setImageURI(uri);

        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // takePictureButton.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                file = Uri.fromFile(getOutputMediaFile());
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
//                startActivityForResult(intent, 111);

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 111);
            }
        });
//
//        Intent waIntent = new Intent(Intent.ACTION_SEND);
//        waIntent.setType("text/plain");
//        waIntent.putExtra(Intent.EXTRA_STREAM, "");
//        // Check if the device has an email client
//        if (waIntent.resolveActivity(getPackageManager()) != null) {
//            startActivity(Intent.createChooser(waIntent, "share"));
//        } else {
//            // Inform the user that no email clients are installed or provide an alternative
//        }
    }

    private File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpg");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 111 && resultCode == RESULT_OK) {
            if (requestCode == 111) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
               // imageView1.setImageBitmap(photo);


                Bitmap icon = photo;

                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "title");
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/*");
                Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        values);


//                OutputStream outstream;
//                try {
//                    outstream = getContentResolver().openOutputStream(uri);
//                    icon.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
//                    outstream.close();
//                } catch (Exception e) {
//                    System.err.println(e.toString());
//                }

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setDataAndType(uri, "image/*");
                intent.putExtra("mimeType", "image/*");
                this.startActivity(Intent.createChooser(intent, "Set as:"));
            }
        }
    }

}
