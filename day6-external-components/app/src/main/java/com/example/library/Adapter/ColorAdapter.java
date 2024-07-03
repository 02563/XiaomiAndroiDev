package com.example.library.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.library.LoveEvent;
import com.example.library.MyItemUpdateEvent;
import com.example.library.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class ColorAdapter extends BaseQuickAdapter<item, BaseViewHolder> implements LoadMoreModule {
    private List<item> mData;
    private OnTextClickListener mTextClickListener;
    private OnImageClickListener mOnImageClickListener;
    private OnLoveClickListener mOnLoveClickListener;

    //BaseViewHolder baseViewHolder =new BaseViewHolder(R.layout.item_layout);
    //mData.add(new item("Item 1", R.drawable.celeste));
    //mData.add(new item("Item 2", R.drawable.celeste));


    public interface OnTextClickListener {
        void onTextClick(item myitem);
    }
    public interface OnImageClickListener {
        void onImageClicked(item imageId);
    }
    public interface OnLoveClickListener {
        void onLoveClicked(Boolean love);
    }

    public void setOnTextClickListener(OnTextClickListener listener) {
        mTextClickListener = listener;
    }
    public void setOnImageClickListener(OnImageClickListener listener) {
        mOnImageClickListener = listener;
    }
    public void setOnLoveClickListener(OnLoveClickListener listener) {
        mOnLoveClickListener = listener;
    }


    public ColorAdapter(List<item> data){
        super(R.layout.item_layout,data);
        mData = data;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, item myitem){
        ImageView imageView = baseViewHolder.getView(R.id.item_image);
        TextView textView = baseViewHolder.getView((R.id.item_text));
        ImageView likeimage = baseViewHolder.getView(R.id.likeImageView);

        if (myitem.isLiked()) {
            baseViewHolder.setImageResource(R.id.likeImageView, R.drawable.like);
        } else {
            baseViewHolder.setImageResource(R.id.likeImageView, R.drawable.nolike);
        }
        //likeimage.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        // 切换喜欢状态
        //        myitem.setLiked(!myitem.isLiked());
//
        //        // 更新喜欢图标的状态
        //        if (myitem.isLiked()) {
        //            baseViewHolder.setImageResource(R.id.likeImageView, R.drawable.like);
        //        } else {
        //            baseViewHolder.setImageResource(R.id.likeImageView, R.drawable.nolike);
        //        }
//
        //        // 发送事件来更新喜欢状态
        //        EventBus.getDefault().post(new LoveEvent(myitem.getitemId(), myitem.isLiked()));
        //    }
        //});


        Glide.with(imageView.getContext())
                .load(myitem.getitemId())
                .into(imageView);
        textView.setText(myitem.getItemName());

        // 设置点击事件监听器
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTextClickListener != null) {
                    mTextClickListener.onTextClick(myitem);
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnImageClickListener != null) {
                    mOnImageClickListener.onImageClicked(myitem);
                }
            }
        });

    }
   // @Override
   // public int getItemCount() {
   //     return mData.size();
   // }

    //public void setData(List<item> data) {
    //    mData = data;
    //    notifyDataSetChanged();
    //}
    public void updateData(List<item> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void updateItemLikedStatus(int position, boolean liked) {
        mData.get(position).setLiked(liked);
        notifyItemChanged(position);
    }
}
