package com.example.dell.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<BaseVo> list;
    private RecyclerView mRlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setAdapter();
    }

    private void setAdapter() {
//        rlvAdapter rlvAdapter = new rlvAdapter(list, this);
        rlvHaspMapAdapter adapter = new rlvHaspMapAdapter(list, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRlv.setLayoutManager(manager);
        mRlv.setAdapter(adapter);
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BaseVo vo = new BaseVo();
            vo.setName("虾米"+i);
            list.add(vo);
        }

    }

    private void initView() {
        mRlv = (RecyclerView) findViewById(R.id.rlv);
    }
}
