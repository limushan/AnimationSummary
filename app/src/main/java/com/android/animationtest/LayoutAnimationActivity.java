package com.android.animationtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by lbb on 2016/7/14.
 */
public class LayoutAnimationActivity extends Activity {
    private ListView mLv;
    private String[] data = {"sssssssssss", "11111111111", "ddddddddddd", "eeeeeeeeeeee", "sssssssssss", "11111111111", "ddddddddddd", "eeeeeeeeeeee",
            "sssssssssss", "11111111111", "ddddddddddd", "eeeeeeeeeeee"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layoutanimation);
        mLv = (ListView) findViewById(R.id.lv);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_list);
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setDelay(1);
        controller.setOrder(LayoutAnimationController.ORDER_RANDOM);
        mLv.setLayoutAnimation(controller);
        mLv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return data.length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = View.inflate(LayoutAnimationActivity.this, R.layout.item, null);
                }
                TextView textView = (TextView) convertView.findViewById(R.id.tv);
                textView.setText(data[position]);
                return convertView;
            }
        });

    }

}
