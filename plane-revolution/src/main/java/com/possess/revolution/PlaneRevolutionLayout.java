package com.possess.revolution;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.android.library.R;

/**
 * @author libinbin
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

    private Animator mLeftOutAnimator;
    private Animator mRightInAnimator;


    public PlaneRevolutionLayout(Context context) {
        this(context, null);
    }

    public PlaneRevolutionLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlaneRevolutionLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PlaneRevolutionLayout, defStyleAttr, 0);
        mOrientation = ta.getInt(R.styleable.PlaneRevolutionLayout_orientation, 0);
        mFrontClickViewResId = ta.getResourceId(R.styleable.PlaneRevolutionLayout_frontClickId, -1);
        mBackClickViewResId = ta.getResourceId(R.styleable.PlaneRevolutionLayout_backClickId, -1);
        ta.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
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
        setCameraDistance();
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
        if (mLeftOutAnimator == null)
            initDefaultLeftOutAnimator();
        if (mRightInAnimator == null)
            initDefaultRightInAnimator();
        if (mLocation == FRONT) {
            mLeftOutAnimator.setTarget(mSecondView);
            mRightInAnimator.setTarget(mFirstView);
            mLeftOutAnimator.start();
            mRightInAnimator.start();
            mLocation = BACK;
        } else {
            mLeftOutAnimator.setTarget(mFirstView);
            mRightInAnimator.setTarget(mSecondView);
            mLeftOutAnimator.start();
            mRightInAnimator.start();
            mLocation = FRONT;
        }
    }

    private void setCameraDistance() {
        float distance = getResources().getDisplayMetrics().density * 16000;
        mFirstView.setCameraDistance(distance);
        mSecondView.setCameraDistance(distance);
    }

    public void setLeftOutAnimator(Animator animator) {
        if (mLeftOutAnimator != null) {
            mLeftOutAnimator.removeListener(animatorStartListener);
        }
        mLeftOutAnimator = animator;
        if (mLeftOutAnimator != null) {
            mLeftOutAnimator.addListener(animatorStartListener);
        }
    }

    public void setRightInAnimator(Animator animator) {
        if (mRightInAnimator != null) {
            mRightInAnimator.removeListener(animatorEndListener);
        }
        mRightInAnimator = animator;
        if (mRightInAnimator != null) {
            mRightInAnimator.addListener(animatorEndListener);
        }
    }

    private AnimatorListenerAdapter animatorStartListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationStart(Animator animation) {
            if (mFrontClickView != null) mFrontClickView.setClickable(false);
            if (mBackClickView != null) mBackClickView.setClickable(false);
        }
    };

    private AnimatorListenerAdapter animatorEndListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            if (mFrontClickView != null) mFrontClickView.setClickable(true);
            if (mBackClickView != null) mBackClickView.setClickable(true);
        }
    };

    private void initDefaultLeftOutAnimator() {
        Animator leftInAnimator = AnimatorInflater.loadAnimator(getContext(), R.animator.verical_revolution_front);
        setLeftOutAnimator(leftInAnimator);

    }

    private void initDefaultRightInAnimator() {
        Animator rightInAnimator = AnimatorInflater.loadAnimator(getContext(), R.animator.verical_revolution_back);
        setRightInAnimator(rightInAnimator);
    }

}
