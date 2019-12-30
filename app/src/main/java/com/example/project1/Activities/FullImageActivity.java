package com.example.project1.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.project1.Adapters.ImageAdapter;
import com.example.project1.R;
import com.github.chrisbanes.photoview.PhotoView;

public class FullImageActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);

        // get intent data
        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("id");
        ImageAdapter imageAdapter = new ImageAdapter(this);

        PhotoView photoView = (PhotoView) findViewById(R.id.full_image_view);
        photoView.setImageResource(imageAdapter.mThumbIds[position]);

    }
}
