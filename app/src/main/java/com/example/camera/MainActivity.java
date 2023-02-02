package com.example.camera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    // Я не знаю зачем это
    private static final int NORM = 1;
    private static final int REQUEST_TAKE_PHOTO = 1;

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iv = (ImageView) findViewById(R.id.preview);
        // Buttons
        setContentView(R.layout.activity_main);
        Button bt = (Button) findViewById(R.id.button);
        Button bt1 = (Button) findViewById(R.id.cancel);
        Button bt2 = (Button) findViewById(R.id.recognize);
        bt.setOnClickListener(view -> {
            Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            try{
                startActivityForResult(takePhotoIntent, REQUEST_TAKE_PHOTO);
            }catch (ActivityNotFoundException e){
                e.printStackTrace();
            }

        });
        bt1.setOnClickListener(view -> {
            // delete foto
            iv.setImageDrawable(null);
        });
        bt2.setOnClickListener(view -> {
            // todo Next Activity (ML)
        });
    }
    // after doing foto
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        iv = (ImageView) findViewById(R.id.preview);
        if (requestCode == NORM){Bundle extras = data.getExtras();
            Bitmap thumbnailBitmap = (Bitmap) extras.get("data");
            iv.setImageBitmap(thumbnailBitmap);

        }
    }

}