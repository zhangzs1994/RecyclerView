package com.example.zzs_pc.recyclerviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PullBaseView.OnHeaderRefreshListener, PullBaseView.OnFooterRefreshListener {
    private PullRecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private List list = new ArrayList();
    private int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        initData();
        recyclerView = (PullRecyclerView) findViewById(R.id.recyclerView);
        //设置水平布局
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置下拉刷新监听
        recyclerView.setOnHeaderRefreshListener(this);
        //设置上拉加载监听
        recyclerView.setOnFooterRefreshListener(this);
        //设置自定义分割线
        recyclerView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        adapter = new RecyclerViewAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        for (int i = num; i < num + 5; i++) {
            list.add("条目" + (i + 1));
        }
        num = num + 5;
    }

    //下拉刷新数据
    @Override
    public void onHeaderRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //初始化加载数据
                num = 0;
                list = new ArrayList();
                initData();
                adapter = new RecyclerViewAdapter(MainActivity.this, list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                recyclerView.onHeaderRefreshComplete();
            }
        }, 2000);
    }

    //上拉加载数据
    @Override
    public void onFooterRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initData();
                adapter.notifyDataSetChanged();
                recyclerView.onFooterRefreshComplete();
            }
        }, 2000);
    }

}
