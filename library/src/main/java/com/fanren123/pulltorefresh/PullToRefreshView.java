package com.fanren123.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * @Author: Created by He on 2018/12/5.
 * @Description:
 */

public class PullToRefreshView extends PtrFrameLayout {


    public PullToRefreshView(Context context) {
        this(context,null);
    }

    public PullToRefreshView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PullToRefreshView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        RefreshHeaderView view =new RefreshHeaderView(context);
        addPtrUIHandler(view);
        setHeaderView(view);
    }
}
