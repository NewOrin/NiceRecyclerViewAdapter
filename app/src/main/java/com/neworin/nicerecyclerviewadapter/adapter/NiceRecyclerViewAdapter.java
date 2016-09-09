package com.neworin.nicerecyclerviewadapter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neworin.nicerecyclerviewadapter.holder.NiceViewHolder;
import com.neworin.nicerecyclerviewadapter.i.OnItemClickListener;

import java.util.List;

/**
 * Created by NewOrin Zhang on 2016/9/6.
 * E-Mail : NewOrinZhang@Gmail.com
 */
public abstract class NiceRecyclerViewAdapter<T> extends RecyclerView.Adapter<NiceViewHolder> {

    private static final String TAG = "NiceRecyclerViewAdapter";
    private LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    private int mLayoutId;
    private NiceViewHolder holder;
    private OnItemClickListener mOnItemClickListener;
    private int emptyViewId = 0;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public NiceRecyclerViewAdapter(Context context, int layoutId, List<T> mDatas) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
        this.mLayoutId = layoutId;
    }

    @Override
    public NiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return NiceViewHolder.get(mContext, parent, mLayoutId);
    }

    @Override
    public void onBindViewHolder(final NiceViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onClick(holder.getLayoutPosition());
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mOnItemClickListener.onLongClick(holder.getLayoutPosition());
                return false;
            }
        });
        convert(holder, mDatas.get(position));
    }

    public abstract void convert(NiceViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setEmptyViewId(int emptyViewId) {
        this.emptyViewId = emptyViewId;
        Log.d(TAG, "emptyId = " + emptyViewId);
    }
}
