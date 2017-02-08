package com.android.animation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.android.animation.R;

/**
 * Created by lbb on 2016/7/14.
 * Android 动画
 * 1.逐帧动画
 * 2.补间动画（+LayoutAnimationsController）
 * 3.属性动画
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void gotoTweenAnimation(View view) {
        TweenActivity.start(this);
    }


    public void gotoFrameByFrameAnimation(View view) {
        FrameByFrameActivity.start(this);
    }

    public void OnLayoutAnimation(View view) {
        LayoutAnimationActivity.start(this);
    }

    public void OnOpenObjectAnimator(View view) {
        startActivity(new Intent(this, ValueAnimationActivity.class));
    }
}
