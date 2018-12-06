package com.fanren123.pulltorefresh;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * @Author: Created wang He on 2018/12/5.
 * @Description:
 */

public class RefreshHeaderView extends FrameLayout implements PtrUIHandler {

    private static final String COMPLETED = "更新成功";
    private static final String PULL = "下拉更新...";
    private static final String UP = "松手更新...";
    private static final String UPDATING = "更新中...";


    public RefreshHeaderView(@NonNull Context context) {
        super(context);
        initView(context);
    }


    public RefreshHeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RefreshHeaderView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);

    }

    ImageView ivArrow;
    ImageView indicator;
    TextView tvContent;
    boolean isStart;

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_refresh, this, true);
        ivArrow = (ImageView) view.findViewById(R.id.iv_arrow);
        indicator = (ImageView) view.findViewById(R.id.indicator);
        tvContent = (TextView) view.findViewById(R.id.tv_content);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {

        ivArrow.setVisibility(GONE);
        indicator.setVisibility(GONE);
        tvContent.setVisibility(GONE);

    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

        ivArrow.setVisibility(VISIBLE);
        indicator.setVisibility(GONE);
        tvContent.setVisibility(VISIBLE);
        isStart = false;
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        ivArrow.setVisibility(GONE);
        indicator.setVisibility(VISIBLE);
        tvContent.setVisibility(VISIBLE);
        tvContent.setText(UPDATING);

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.anim);
        LinearInterpolator lin = new LinearInterpolator();//设置动画匀速运动
        animation.setInterpolator(lin);
        indicator.startAnimation(animation);


        isStart = true;
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        ivArrow.setVisibility(GONE);
        indicator.setVisibility(GONE);
        tvContent.setVisibility(VISIBLE);
        tvContent.setText(COMPLETED);
        indicator.clearAnimation();
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        float currentPercent = ptrIndicator.getCurrentPercent();
        if (!isStart)
            if (currentPercent < 1.2) {
                tvContent.setText(PULL);
                ivArrow.setRotationX(ivArrow.getX() / 2);
                ivArrow.setRotationY(ivArrow.getY() / 2);
                ivArrow.setRotation(180);
            } else {
                tvContent.setText(UP);
                ivArrow.setRotationX(ivArrow.getX() / 2);
                ivArrow.setRotationY(ivArrow.getY() / 2);
                ivArrow.setRotation(0);
            }
    }
}
