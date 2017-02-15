package com.android.animation.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.animation.R;
import com.android.animation.Translate;
import com.android.animation.WaveEvaluator;

/**
 * @author lbb
 *         <p>
 *         属性动画
 *         前言：补间动画有个致命的缺陷，动画只是改变了View的显示效果，没有改变View本身的属性，
 *         Android 3.0引入了属性动画。属性动画实际上是通过赋值来改变其属性实现动画的效果，使用上面的
 *         问题就不存在了，使用属性动画我们移动一个控件，实际就是改变了控件的位置
 *         <p>
 *         1.ValueAnimator类 属性的动画的核心类。负责计算属性初始值和结束值之间的过度，同时还负责管理
 *         动画的播放次数、播放模式、以及设置动画的监听等
 *         示例：（testValueAnimator()方法）
 *         <p>
 *         2.ObjectAnimator类--动画执行类。ValueAnimator只是实现值的过渡，
 *         ObjectAnimator对任意对象（控件或者非控件）的任意属性进行操作，例如：View的rotateX。
 *         ObjectAnimator是ValueAnimator的子类，还是通过ofFloat,ofInt,ofObject创建对象，
 *         不同的是创建时多传了两个参数，示例：ofFloat(Object target, String propertyName, float... values)
 *         第一个参数：传入需要进行动画操作的对象
 *         第二个参数：传入对对象的什么属性进行操作（例如：View的透明度："alpha",平面旋转："rotation",水平旋转："rotationX",
 *         垂直旋转："rotationY",水平移动："translationX"，"垂直移动：translationY",水平缩放："scaleX",垂直缩放："scaleY",
 *         等等有setter和getter方法的属性）
 *         <p>
 *         3.组合动画：AnimatorSet
 *         play(Animator anim) 播放传入的动画
 *         after(Animator anim) 将现有动画插入到传入的动画之后执行
 *         after(long delay) 将现有动画延迟指定的毫秒执行
 *         before(Animator anim) 将现有动画插入到传入的动画之前执行
 *         with(Animator anim) 现有动画和传入的动画同时执行
 *         <p>
 *         4.使用代码方式添加动画
 *         4.1.单个动画方法1：
 *         a.调用ObjectAnimator的ofFloat,ofInt,ofObject的方法传入动画执行目标，执行属性，以及属性的值变化
 *         b.设置ObjectAnimator对象的执行时间
 *         c.调用ObjectAnimator的start方法开启动画
 *         4.2.单个动画方法2:
 *         a.生成ValueAnimator类的对象
 *         b.调用addUpdateListener的addUpdateListener方法添加数值变化监听
 *         c.在监听的回调方法中手动改变动画执行对象的属性，实现动画效果
 *         4.3.组合动画方法1；
 *         a.new AnimatorSet类的对象
 *         b.调用ObjectAnimator的ofFloat,ofInt,ofObject的方法生成Animator对象
 *         c.调用AnimatorSet的对象的play,after等方法播放动画，以及设置动画播放的顺序
 *         4.4.组合动画方法2：
 *         同4.2，回调中改变多个属性即可实现组合动画的效果
 *         4.5.组合动画方法3：
 *         a.调用PropertyValuesHolder类的ofFloat等方法传入需要改变的属性以及数值的变化生成PropertyValuesHolder对象
 *         b.调用ObjectAnimator的ofPropertyValuesHolder方法传入动画执行对象，以及多个PropertyValuesHolder类的对象
 *         c.调用Animator的start方法开启动画
 *         <p>
 *         5.使有XML方式添加动画
 *         a.res资源下新增animator文件夹
 *         b.animator资源下新建Animator资源文件
 *         c.<animator>对应ValueAnimator ；<objectAnimator>对应ObjectAnimator 单个动画 ；<set>对应AnimatorSet 组合动画
 *         d.使用AnimatorInflater.laodAnimator方法解析XML文件生成Animator对象
 *         f.调用Animator的setTarget方法设置动画执行目标
 *         e.调用Animator的start方法开启动画
 *         <p>
 *         6.Animator监听器：AnimatorListener  ,  AnimatorListenerAdapter
 *         void onAnimationStart(Animator animation);
 *         void onAnimationEnd(Animator animation);
 *         void onAnimationCancel(Animator animation);
 *         void onAnimationRepeat(Animator animation);
 *         监听动画的开始，结束，取消，以及重复
 *         7.TypeEvaluator：控制动画系统数值之间的过度
 *         例：FloatEvaluator
 *         fraction标识动画的完成度，和设置的Interpolator有关,默认是AccelerateDecelerateInterpolator，两端减速中间加速改变动画完成度
 *         public Float evaluate(float fraction, Number startValue, Number endValue) {
 *         float startFloat = startValue.floatValue();
 *         return startFloat + fraction * (endValue.floatValue() - startFloat);
 *         8.自定义TypeEvaluator：处理任意对象，改变任意属性
 *         }
 */
public class ValueAnimationActivity extends Activity {
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animation);
        mImageView = (ImageView) findViewById(R.id.iv);
        testValueAnimator();
    }

    /**
     * ValueAnimator的简单功能，值从0-100-10-1000在1秒内平滑过度
     * ValueAnimator没有操作属性的方法，因此不需要操作的对象的属性一定要有setter和getter方法，可以在监听中自定义属性的变化
     * addUpdateListener添加监听器，在动画执行的工程中会不断的回调
     * ValueAnimator常用的方法ofFloat,ofInt
     * <p>
     * 打印结果：
     * value :: 0.0
     * value :: 0.0
     * value :: 0.2138704
     *  value :: 0.8548826
     * .....
     *  value :: 994.25836
     *  value :: 999.11346
     *  value :: 1000.0
     */
    private void testValueAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(-500, 0, 1000, 0);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Object animatedValue = animation.getAnimatedValue();
                Log.e("current value :", "" + animatedValue);
                mImageView.setTranslationY((Float) animatedValue);
            }
        });
        valueAnimator.start();
    }

    /**
     * 属性动画是通过不断改变View的属性值进行操作实现的
     * 初始和结束值是由ValueAnimator 负责计算的，内部循环计算值与值的动画过渡
     */
    public void startByCode(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mImageView, "alpha", 1f, 0f, 1f);
        animator.setDuration(1000);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mImageView, "translationX", -100f, 0, 100f);
        animator1.setDuration(1000);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mImageView, "rotation", 0, 360, 0);
        animator2.setDuration(2000);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mImageView, "translationY", -500f, 0, 500f);
        animator3.setDuration(5000);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(mImageView, "scaleY", 1, 10, 5, 10, 1);
        animator4.setDuration(10000);
        ObjectAnimator animator5 = ObjectAnimator.ofFloat(mImageView, "scaleX", 1, 10, 5, 10, 1);
        animator5.setDuration(10000);
        /**
         * 动画组合
         */
        AnimatorSet set = new AnimatorSet();
        set.play(animator).with(animator1).after(animator2).before(animator4).before(animator5);
        set.start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Toast.makeText(ValueAnimationActivity.this, "开始了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(ValueAnimationActivity.this, "结束了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }
        });
    }

    public void startByXml(View view) {
        /**
         * USE XML
         */
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.value_anim_set);
        animator.setTarget(mImageView);
        animator.start();
        /**
         * 自定义属性动画
         */
    }

    public void typeEvaluator(View view) {
        ObjectAnimator animator1 = ObjectAnimator.ofObject(view, "translate", new WaveEvaluator(), new Translate(0, 0), new Translate(1, 0));
        animator1.setDuration(10000);
        animator1.setInterpolator(new LinearInterpolator());
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Translate translate = (Translate) animation.getAnimatedValue();
                mImageView.setTranslationX(translate.x);
                mImageView.setTranslationY(translate.y);
            }
        });
        animator1.start();
    }

    public void planeRevolution(View view) {
        PlaneRevolutionActivity.start(this);
    }
}
