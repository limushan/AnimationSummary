package com.android.animation.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.animation.R;

public class PlaneRevolutionActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, PlaneRevolutionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plane_revolution);
    }
}
