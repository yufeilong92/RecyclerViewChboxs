package com.example.dell.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: RecyclerView
 * @Package com.example.dell.recyclerview
 * @Description: $todo$
 * @author: YFL
 * @date: 2017/7/13 20:44
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */


public class rlvAdapter extends RecyclerView.Adapter<rlvAdapter.ViewHolder> {


    private ArrayList mData;
    private Context mContext;
    private final LayoutInflater inflater;
    private TextView mTvTitle;

    private List<Integer> mList;
    private List<String> stringList;
    boolean isadd = true;

    public rlvAdapter(ArrayList mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        mList = new ArrayList<>();
        stringList =new ArrayList<>();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final BaseVo vo = (BaseVo) mData.get(position);
        holder.mTvTitle.setText(vo.getName());

        holder.mchb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!mList.contains(position)) {
                        mList.add(position);
                    }
                } else {
                    if (mList.contains(position)) {
                        int i = mList.indexOf(position);
                        mList.remove(i);
                    }
                    if (stringList.contains(vo.getName())) {
                        int i = stringList.indexOf(vo.getName());
                        stringList.remove(i);
                        
                    }
                }

            }
        });
        if (mList != null) {
            holder.mchb.setChecked(mList.contains(position));
        } else {
            holder.mchb.setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvTitle;
        public CheckBox mchb;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            this.mchb = (CheckBox) itemView.findViewById(R.id.chb);
        }
    }


}
