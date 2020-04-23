package com.hl.lib_news.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hl.lib_news.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, MainNewsFragment.newInstance()).commit();
    }
}
