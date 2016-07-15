package com.android.animationtest;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView mTestIv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTestIv = (ImageView) findViewById(R.id.test_iv);
        mTestIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "dianji", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1) {
                AnimationDrawable ad = (AnimationDrawable) mTestIv.getDrawable();
                ad.stop();
            }

            return false;
        }
    });


    private void setAnimationUseXML() {
        /**
         * pivotY
         * “50” 绝对位置
         * “50%” 相对于自己
         * “50%p”相对于父控件
         */
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_set);
        animation.setDuration(10000);
        animation.setInterpolator(new AccelerateInterpolator());
        mTestIv.startAnimation(animation);


    }

    private void setAnimationUseCode() {
        AnimationSet as = new AnimationSet(true);
        /**
         * 缩放
         * x起始缩放
         * x终止缩放
         * y起始缩放
         * y终止缩放
         * 缩放相对于自己/parent
         * x轴缩放位置
         * 缩放相对于自己/parent
         * y轴缩放位置
         */
        ScaleAnimation sa = new ScaleAnimation(0, 3f, 0, 3f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(5000);
        /**
         * 平移
         * 起始位置
         * 终止位置
         * 相对于自己
         */
        TranslateAnimation ta = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
        ta.setDuration(500);
        /**
         * 透明度
         */
        AlphaAnimation aa = new AlphaAnimation(1, 0.2f);
        aa.setDuration(1000);
        /**
         * 旋转
         * 3-6中心点
         */
        RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(3000);
        ta.setRepeatMode(Animation.REVERSE);
        as.addAnimation(sa);
        as.addAnimation(aa);
        as.addAnimation(ta);
        as.addAnimation(ra);
        as.setFillAfter(true);
        as.setStartTime(3000);
        mTestIv.startAnimation(as);
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

    public void OnUseCode(View view) {
        setAnimationUseCode();

    }

    public void OnUseXml(View view) {
        setAnimationUseXML();
    }

    public void OnUse(View view) {
        AnimationDrawable ad = (AnimationDrawable) mTestIv.getDrawable();
        ad.start();
        handler.sendEmptyMessageDelayed(1, 5000);
    }

    public void OnLayoutAnimation(View view) {
        startActivity(new Intent(this, LayoutAnimationActivity.class));
    }
    public void OnOpenObjectAnimator(View view) {
        startActivity(new Intent(this, ValueAnimationActivity.class));
    }
}
