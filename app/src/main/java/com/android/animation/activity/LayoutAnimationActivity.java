package com.android.animation.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.animation.R;

/**
 * @author lbb
 *         1.LayoutAnimationsController
 *         LayoutAnimationsController可以用来实现多个控件一个一个的显示
 *         a.LayoutAnimationsController用来为一个ViewGroup里的控件设置统一的动画效果
 *         b.控件的动画效果可以在不同的时间显示
 *         <p>
 *         2.在代码中添加
 *         a.new Animation对象
 *         b.new LayoutAnimationsController对象（传参Animation对象）
 *         c.设置LayoutAnimationsController对象的属性（setDelay动画间隔，setOrder显示顺序）
 *         d.调用父控件的setLayoutAnimation方法设置LayoutAnimation属性
 *         <p>
 *         3.在xml中添加
 *         a.在anim文件夹下新建根标签为layoutAnimation的资源文件
 *         b.设置layoutAnimation的属性（animation:子类动画；animationOrder：
 *         动画执行顺序【normal-正向，reverse-反向，random-随机】，delay:动画间隔时间）
 *         c.父控件设置layoutAnimation属性配置资源文件
 *
 */
public class LayoutAnimationActivity extends Activity {
    private ListView mLv;
    private String[] data = {"sssssssssss", "11111111111", "ddddddddddd", "eeeeeeeeeeee", "sssssssssss", "11111111111", "ddddddddddd", "eeeeeeeeeeee",
            "sssssssssss", "11111111111", "ddddddddddd", "eeeeeeeeeeee"};

    public static void start(Context context) {
        Intent intent = new Intent(context, LayoutAnimationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layoutanimation);
        mLv = (ListView) findViewById(R.id.lv);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_list);
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setDelay(1);
        controller.setOrder(LayoutAnimationController.ORDER_REVERSE);
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
