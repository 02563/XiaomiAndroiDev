package com.example.androidui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private List<GameBean> data;

    public GameAdapter(List<GameBean> data){
        this.data = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mGameIcon;
        TextView mGameName;
        Button mGameButton;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            mGameIcon = itemView.findViewById(R.id.game_icon);
            mGameName = itemView.findViewById(R.id.game_name);
            mGameButton = itemView.findViewById(R.id.game_button);

        }
    }

    @NonNull
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list_item, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        GameBean game = data.get(position);
        holder.mGameIcon.setImageResource(game.getGameIcon());
        holder.mGameName.setText("Game " + game.getGameName());
        holder.mGameButton.setText(game.getGameStatus());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.size();
    }

    // 当有新项添加到列表时，使用这个方法来通知适配器
    public void addItem(int position,GameBean item) {
        data.add(position,item);
        notifyItemInserted(position);
    }

}
