package com.hl.video.ui.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.hl.video.R;
import com.hl.video.ui.fragment.MainVideoFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, MainVideoFragment.newInstance()).commit();
    }
}
