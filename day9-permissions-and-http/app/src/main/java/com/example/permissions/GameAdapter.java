package com.example.permissions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class GameAdapter extends BaseQuickAdapter<Game, BaseViewHolder> implements LoadMoreModule {

    private Context context;
    private List<Game> gameList;

    public GameAdapter(Context context, List<Game> gameList) {
        super(R.layout.item, gameList);
        this.context = context;
        this.gameList = gameList;
    }

    @Override
    protected void convert(BaseViewHolder holder, Game game) {
        ImageView gameImage = holder.getView(R.id.game_image);
        TextView gameName = holder.getView(R.id.game_name);
        TextView gameRating = holder.getView(R.id.game_rating);
        Button downloadButton = holder.getView(R.id.download_button);

        //gameImage.setImageResource(game.getGameImg());
        Glide.with(context)
                .load(game.getGameImg())
                .into(gameImage);
        gameName.setText(game.getGameName());
        gameRating.setText("Rating: " + game.getScore());

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理下载按钮点击事件
            }
        });
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        return createBaseViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }
}