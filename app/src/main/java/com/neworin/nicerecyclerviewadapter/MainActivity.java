package com.neworin.nicerecyclerviewadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.neworin.nicerecyclerviewadapter.adapter.MultiItemNiceAdapter;
import com.neworin.nicerecyclerviewadapter.adapter.NiceRecyclerViewAdapter;
import com.neworin.nicerecyclerviewadapter.bean.Nice;
import com.neworin.nicerecyclerviewadapter.holder.NiceViewHolder;
import com.neworin.nicerecyclerviewadapter.i.MultiItemTypeSupport;
import com.neworin.nicerecyclerviewadapter.i.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mReyclcerview;
    private NiceRecyclerViewAdapter mNiceRecyclerViewAdapter;
    private MultiItemNiceAdapter mMultiItemNiceAdapter;
    private boolean isClick = false;
    private List<Nice> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        setView1();
        setView2();
    }

    private void setView2() {
        mMultiItemNiceAdapter = new MultiItemNiceAdapter<Nice>(this, mDatas, new MultiItemTypeSupport<Nice>() {
            @Override
            public int getLayoutId(int itemType) {
                if (itemType == 0 || itemType == mDatas.size() - 1) {
                    return R.layout.item_header_footer_view;
                } else if (itemType % 3 == 0) {
                    return R.layout.item_insertview;
                }
                return R.layout.item_recyclerview;
            }

            @Override
            public int getItemViewType(int position, Nice nice) {
                return nice.getNice_type();
            }
        }) {
            @Override
            public void convert(final NiceViewHolder holder, final Nice nice) {
                if (holder.getItemViewType() == 0 || holder.getItemViewType() == mDatas.size() - 1) {
                    holder.setText(R.id.tv_head_foot, holder.getItemViewType() == 0 ? "I am Header" : "I am Footer");
                } else if (holder.getItemViewType() % 3 == 0) {
                } else {
                    holder.setText(R.id.tv_title, nice.getNice_name() + "-" + holder.getLayoutPosition());
                    holder.setViewClickListener(R.id.btn_nice, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (isClick) {
                                holder.setText(R.id.tv_title, nice.getNice_name() + "-" + holder.getLayoutPosition());
                                isClick = false;
                            } else {
                                holder.setText(R.id.tv_title, "I am so nice!");
                                isClick = true;
                            }
                        }
                    });
                }
            }
        };
        mMultiItemNiceAdapter.setmOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {

            }

            @Override
            public void onLongClick(int position) {

            }
        });
        mReyclcerview.setLayoutManager(new LinearLayoutManager(this));
        mReyclcerview.setAdapter(mMultiItemNiceAdapter);
    }

    private void initView() {
        mReyclcerview = (RecyclerView) findViewById(R.id.reyclcerview);
        mDatas = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            mDatas.add(new Nice(i, "Nice"));
//        }
    }

    private void setView1() {
        mNiceRecyclerViewAdapter.setEmptyViewId(R.layout.item_emptyview);
        mNiceRecyclerViewAdapter = new NiceRecyclerViewAdapter<Nice>(this, R.layout.item_recyclerview, mDatas) {

            @Override
            public void convert(final NiceViewHolder holder, final Nice nice) {
                holder.setText(R.id.tv_title, nice.getNice_name() + "-" + holder.getLayoutPosition());
                holder.setViewClickListener(R.id.btn_nice, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isClick) {
                            holder.setText(R.id.tv_title, nice.getNice_name() + "-" + holder.getLayoutPosition());
                            isClick = false;
                        } else {
                            holder.setText(R.id.tv_title, "I am so nice!");
                            isClick = true;
                        }
                    }
                });
            }
        };
        mNiceRecyclerViewAdapter.setmOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                showToast("click " + position);
            }

            @Override
            public void onLongClick(int position) {
                showToast("long click " + position);
            }
        });
        mReyclcerview.setLayoutManager(new LinearLayoutManager(this));
        mReyclcerview.setAdapter(mNiceRecyclerViewAdapter);
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
