# Recyclerview chbox 选择复用
##主要代码测试

   @Override
     public void onBindViewHolder(final ViewHolder holder, final int position) {
         BaseVo vo = (BaseVo) mData.get(position);
         holder.mTvTitle.setText(vo.getName());

         holder.mchb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (!mList.contains(position)){
                        mList.add(position);
                    }
                }else {
                    if (mList.contains(position)){
                        int i = mList.indexOf(position);
                        mList.remove(i);
                    }
                }

             }
         });
         if (mList!=null){
             holder.mchb.setChecked(mList.contains(position));
         }else {
             holder.mchb.setChecked(false);
         }
     }