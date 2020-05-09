package com.hl.news.ui.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.hl.news.R;
import com.hl.news.ui.fragment.MainNewFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, MainNewFragment.newInstance()).commit();
    }
}
