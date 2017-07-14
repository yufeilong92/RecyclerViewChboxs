package com.example.dell.recyclerview;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


public class rlvHaspMapAdapter extends RecyclerView.Adapter<rlvHaspMapAdapter.ViewHolder> {


    private ArrayList mData;
    private Context mContext;
    private final LayoutInflater inflater;
    private TextView mTvTitle;

    private List<Integer> mSelectList;
    private List<String> stringList;
    public static Map<Integer, Boolean> isSelected;
    private boolean isClick=false;

    public rlvHaspMapAdapter(ArrayList mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        mSelectList = new ArrayList<>();
        stringList = new ArrayList<>();
        initHaspMap();
    }

    private void initHaspMap() {
        isSelected = new HashMap<>();
        for (int i = 0; i < mData.size(); i++) {
            isSelected.put(i, false);
        }
    }

    private OnRecyclerItemCheckBoxListener mCheckBoxListener = null;

    public interface OnRecyclerItemCheckBoxListener {
        void OnItemCheckBoxListener(List<String> lists, int position);
    }

    public OnRecyclerItemCheckBoxListener getmCheckBoxListener() {
        return mCheckBoxListener;
    }

    public void setmCheckBoxListener(OnRecyclerItemCheckBoxListener mCheckBoxListener) {
        this.mCheckBoxListener = mCheckBoxListener;
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

        holder.mchb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mSelectList.contains(position))
                            return false;
                        if (isClick) {
                            int size = mSelectList.size();
                            if (size > 2) {
                                showDailog();
                                return true;
                            }
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        holder.mchb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isSelected.put(position, isChecked);
                if (isChecked) {
                    if (!mSelectList.contains(position)) {
                        mSelectList.add(position);
                        isClick=true;
                    }

                } else {
                    if (mSelectList.contains(position)) {
                        int i = mSelectList.indexOf(position);
                        mSelectList.remove(i);
                        isClick=true;
                    }
                }

            }
        });
        holder.mchb.setChecked(isSelected.get(position));
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

    public void showDailog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("温馨提示");
        builder.setMessage("每天只能选择三个");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int size = mSelectList.size();
                if (size <= 2) {
                    isClick = false;
                } else {
                    isClick = true;
                }
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int size = mSelectList.size();
                if (size <=2) {
                    isClick = false;
                } else {
                    isClick = true;
                }
            }
        });
        builder.create().show();
    }

}
