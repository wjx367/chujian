package com.viewwang.chujian;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/1/26.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private Context context;
    private List<ModelBean> list;
    private Resources res;
//    private OnItemClickListener listener;
    public RecyclerAdapter(Context context, List<ModelBean> list) {
        this.context = context;
        this.list = list;
//        res = context.getResources();
        mInflater=mInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_card_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ModelBean bean = list.get(position);
        holder.title.setText(bean.getTitle());
        holder.frameLayout.setBackgroundResource(bean.getResId());
        holder.linearLayout1.setAlpha((float)0.2);
        holder.frameLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent=new Intent();
                intent.setClass(context,WebActivity.class);
                v.getContext().startActivity(intent);
                return false;
            }
        });
        holder.linearLayout1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent=new Intent();
                intent.setClass(context,WebActivity.class);
                v.getContext().startActivity(intent);
                return false;
            }
        });
        holder.linearLayout1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                        Intent intent=new Intent();
                        intent.setClass(context,WebActivity.class);
                        v.getContext().startActivity(intent);
                return false;
            }
        });
        holder.linearLayout1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        v.setVisibility(View.GONE);
                        holder.title.setVisibility(View.VISIBLE);
                        break;
                    case MotionEvent.ACTION_HOVER_MOVE:
                        v.setVisibility(View.VISIBLE);
                        holder.title.setVisibility(View.VISIBLE);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        v.setVisibility(View.VISIBLE);
                        holder.title.setVisibility(View.VISIBLE);
                        break;
                    case MotionEvent.ACTION_UP:
                        v.setVisibility(View.VISIBLE);
                        holder.title.setVisibility(View.VISIBLE);
                        break;
                }
                return false;
            }
        });

    }





//    /**
//     * 内部接口回调方法
//     */
//    public interface OnItemClickListener {
//        void onItemClick(int position, Object object);
//    }
//
//    /**
//     * 设置监听方法
//     *
//     * @param listener
//     */
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.listener = listener;
//    }




    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private FrameLayout frameLayout;
        private LinearLayout linearLayout1;
        private TextView title;

        public MyViewHolder(View view) {
            super(view);
            frameLayout = (FrameLayout) view.findViewById(R.id.ll);
            linearLayout1= (LinearLayout) view.findViewById(R.id.ll_tv);
            title = (TextView) view.findViewById(R.id.name);
        }
    }
}