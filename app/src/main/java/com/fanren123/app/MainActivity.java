package com.fanren123.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.fanren123.pulltorefresh.PullToRefreshView;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class MainActivity extends AppCompatActivity {

    PullToRefreshView ptr;
    TextView tvContent;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ptr= (PullToRefreshView) findViewById(R.id.ptr);
        tvContent= (TextView) findViewById(R.id.tv_content);
        ptr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

                ptr.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tvContent.setText("refresh*************"+(++i)+"***************");
                        ptr.refreshComplete();
                    }
                },2000);

            }
        });
    }
}
