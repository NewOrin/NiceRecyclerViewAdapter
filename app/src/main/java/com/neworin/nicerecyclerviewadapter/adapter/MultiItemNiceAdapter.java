package com.neworin.nicerecyclerviewadapter.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.neworin.nicerecyclerviewadapter.i.MultiItemTypeSupport;
import com.neworin.nicerecyclerviewadapter.holder.NiceViewHolder;

import java.util.List;

/**
 * Created by NewOrin Zhang on 2016/9/6.
 * E-Mail : NewOrinZhang@Gmail.com
 */

public abstract class MultiItemNiceAdapter<T> extends NiceRecyclerViewAdapter<T> {

    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public MultiItemNiceAdapter(Context context, List<T> mDatas, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, -1, mDatas);
        this.mMultiItemTypeSupport = multiItemTypeSupport;
        this.mDatas = mDatas;
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position, mDatas.get(position));
    }

    @Override
    public NiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        NiceViewHolder holder = NiceViewHolder.get(mContext, parent, layoutId);
        return holder;
    }
}
