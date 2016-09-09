package com.neworin.nicerecyclerviewadapter.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by NewOrin Zhang on 2016/9/6.
 * E-Mail : NewOrinZhang@Gmail.com
 */
public class NiceViewHolder extends RecyclerView.ViewHolder {
    //那么既然是通用的View，那么对于不同的ItemType肯定没有办法确定创建哪些成员变量View，取而代之的只能是个集合来存储了
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;

    public NiceViewHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
        this.mConvertView = itemView;
        this.mViews = new SparseArray<>();
    }

    public static NiceViewHolder get(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new NiceViewHolder(context, itemView);
    }

    /**
     * 内部通过SparseArray来缓存我们itemView内部的子View，从而得到一个通用的ViewHolder。每次需要创建ViewHolder只需要传入我们的layoutId即可。
     * <p/>
     * ok，有了通用的ViewHolder之后，我们的通用的Adapter分分钟就出来了。
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 设置TextView
     *
     * @param viewId
     * @param text
     * @return
     */
    public NiceViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public NiceViewHolder setViewClickListener(int viewId, View.OnClickListener onClickListener) {
        View view = getView(viewId);
        view.setOnClickListener(onClickListener);
        return this;
    }
}
