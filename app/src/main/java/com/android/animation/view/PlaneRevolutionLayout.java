package com.android.animation.view;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.android.animation.R;

/**
 * @author 李彬彬
 *         2017/2/14
 */

public class PlaneRevolutionLayout extends FrameLayout {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public static final int FRONT = 0;
    public static final int BACK = 1;

    private int mOrientation;
    private int mLocation;
    private View mFrontClickView;
    private View mBackClickView;
    private View mFirstView;
    private View mSecondView;

    private int mFrontClickViewResId = -1;
    private int mBackClickViewResId = -1;

    private Animator mFrontAnimator;
    private Animator mBackAnimator;


    public PlaneRevolutionLayout(Context context) {
        this(context, null);
    }

    public PlaneRevolutionLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlaneRevolutionLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public PlaneRevolutionLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PlaneRevolutionLayout, defStyleAttr, defStyleRes);
        mOrientation = ta.getInt(R.styleable.PlaneRevolutionLayout_orientation, 0);
        mFrontClickViewResId = ta.getInt(R.styleable.PlaneRevolutionLayout_frontClickId, -1);
        mBackClickViewResId = ta.getInt(R.styleable.PlaneRevolutionLayout_backClickId, -1);
        ta.recycle();

        initAnimator();
    }

    private void initAnimator() {
        mFrontAnimator = AnimatorInflater.loadAnimator(getContext(), R.animator.verical_revolution_front);
        mBackAnimator = AnimatorInflater.loadAnimator(getContext(), R.animator.verical_revolution_back);
        mFrontAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                setClickable(false);
            }
        });

        mBackAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                setClickable(true);
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        int childCount = getChildCount();
        if (childCount != 2)
            throw new IllegalStateException("this layout must have exactly 2 children!");
        mFirstView = getChildAt(0);
        mSecondView = getChildAt(1);
        if (mFrontClickViewResId != -1) {
            setFrontClickView(findViewById(mFrontClickViewResId));
        } else {
            setFrontClickView(mFirstView);
        }
        if (mBackClickViewResId != -1) {
            setBackClickView(findViewById(mBackClickViewResId));
        } else {
            setBackClickView(mSecondView);
        }
    }

    private void setFrontClickView(View frontClickView) {
        if (mFrontClickView != null) {
            mFrontClickView.setOnClickListener(null);
        }
        mFrontClickView = frontClickView;
        if (mFrontClickView != null) {
            mFrontClickView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    startRotate();
                }
            });
        }
    }

    public void setBackClickView(View backClickView) {
        if (mBackClickView != null) {
            mBackClickView.setOnClickListener(null);
        }
        mBackClickView = backClickView;
        if (mBackClickView != null) {
            mBackClickView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    startRotate();
                }
            });
        }
    }

    /**
     * start switch view
     */
    private void startRotate() {
        if (mLocation == FRONT) {
            mFrontAnimator.setTarget(mSecondView);
            mBackAnimator.setTarget(mFirstView);
            mFrontAnimator.start();
            mBackAnimator.start();
            mLocation = BACK;
        } else {
            mFrontAnimator.setTarget(mFirstView);
            mBackAnimator.setTarget(mSecondView);
            mFrontAnimator.start();
            mBackAnimator.start();
            //setCameraDistance();
            mLocation = FRONT;
        }
        setCameraDistance();

    }

    private void setCameraDistance() {
        float distance = getContext().getResources().getDisplayMetrics().density * 16000;
        mFirstView.setCameraDistance(distance);
        mSecondView.setCameraDistance(distance);
    }
}
