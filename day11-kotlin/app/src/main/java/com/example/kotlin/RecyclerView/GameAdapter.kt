package com.example.kotlin.RecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.kotlin.R

class GameAdapter(val adapterContext: Context, private var gameList: List<Game>) :
    BaseQuickAdapter<Game, BaseViewHolder>(R.layout.item, gameList.toMutableList()), LoadMoreModule {
    private var searchActivity: SearchActivity? = null

    interface OnItemClickListener {
        fun onItemClick(game: Game)
    }

    private var onItemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    fun setSearchActivity(activity: SearchActivity) {
        searchActivity = activity
    }


    override fun convert(holder: BaseViewHolder, game: Game) {
        val gameImage = holder.getView<ImageView>(R.id.game_image)
        val gameName = holder.getView<TextView>(R.id.item_text)
        val gameLike = holder.getView<ImageView>(R.id.like_image)

        //gameImage.setImageResource(game.getGameImg());
        gameImage.setImageResource(game.gameImg)
        gameName.text = game.gameName

        Glide.with(adapterContext).load(game.gameImg).into(gameImage)
        gameName.text = game.gameName
        if (game.like) {
            gameLike.setImageResource(R.drawable.like)
        } else {
            gameLike.setImageResource(R.drawable.nolike)
        }

        // 设置点击事件
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(game)
        }

    }

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return createBaseViewHolder(
            LayoutInflater.from(adapterContext).inflate(R.layout.item, parent, false)
        )
    }

    fun updateGameList(newGameList: List<Game>) {
        gameList = newGameList
    }

}