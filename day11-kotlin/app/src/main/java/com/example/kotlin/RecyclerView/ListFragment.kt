package com.example.kotlin.RecyclerView

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.kotlin.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
interface OnGameListRefreshListener {
    fun onRefreshGameList()
}


class ListFragment : Fragment(), GameAdapter.OnItemClickListener{
    private lateinit var gameList: List<Game>
    private var gameAdapter: GameAdapter? = null
    private var gameRecyclerView: RecyclerView? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var isRefreshing = false
    private var isLoadingMore = false
    private lateinit var searchActivity: SearchActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        searchActivity = context as SearchActivity
    }


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        gameList = searchActivity.gameList

        gameAdapter = GameAdapter(requireActivity() as SearchActivity, gameList as ArrayList<Game>)
        gameAdapter!!.setOnItemClickListener(this)

        val view = inflater.inflate(R.layout.fragment_list, container, false)
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout)
        gameRecyclerView = view.findViewById(R.id.rv_search_results)
        gameRecyclerView?.adapter = gameAdapter
        gameRecyclerView?.layoutManager = LinearLayoutManager(context)

        swipeRefreshLayout?.setOnRefreshListener {
            if (!isRefreshing) {
                isRefreshing = true
                // 执行刷新操作，例如重新加载游戏列表
                refreshGameList()
            }
        }
        gameRecyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (dy > 0 && visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    // Reached the end of the list while scrolling up
                    isLoadingMore = true
                    // Load more items
                    loadMoreItems()
                    recreateFragment()
                }
            }
        })




        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onItemClick(game: Game) {
        // 创建详情Fragment实例，并传递游戏信息
        val detailFragment = DetailFragment.newInstance(game.gameImg, game.gameName,game.like)

        // 跳转到详情Fragment
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun loadMoreItems() {
        // Here you can implement the logic to load more items
        // For example, if you have a method to fetch the next page of data, you can call it here
        // After fetching the data, add it to your existing gameList and notify the adapter
        // For demonstration purposes, let's assume we're adding dummy items
        val newItemPosition = gameList.size // 获取新项的位置
        (gameList as ArrayList<Game>).add(Game("New Game1", R.drawable.game_image, false))
        (gameList as ArrayList<Game>).add(Game("New Game2", R.drawable.game_image, false))
        (gameList as ArrayList<Game>).add(Game("New Game3", R.drawable.game_image, false))
        (gameList as ArrayList<Game>).add(Game("New Game4", R.drawable.game_image, false))
        (gameList as ArrayList<Game>).add(Game("New Game5", R.drawable.game_image, false))
        (gameList as ArrayList<Game>).add(Game("New Game6", R.drawable.game_image, false))
        (gameList as ArrayList<Game>).add(Game("New Game7", R.drawable.game_image, false))
        (gameList as ArrayList<Game>).add(Game("New Game8", R.drawable.game_image, false))
        (gameList as ArrayList<Game>).add(Game("New Game9", R.drawable.game_image, false))


        // 通知适配器有新项插入
        gameAdapter?.updateGameList(gameList)
        gameAdapter?.notifyItemInserted(newItemPosition)
        isLoadingMore = false
    }
    fun updateGameList(newGameList: List<Game>) {
        gameList = newGameList
    }
    private fun refreshGameList() {
        // Call the method in the activity to fetch the latest game list
        if (activity is OnGameListRefreshListener) {
            (activity as OnGameListRefreshListener).onRefreshGameList()
        }

        swipeRefreshLayout?.isRefreshing = false
        isRefreshing = false
    }
    fun recreateFragment() {
        val parentFragmentManager = requireActivity().supportFragmentManager
        val transaction = parentFragmentManager.beginTransaction()

        // 移除当前的Fragment
        transaction.remove(this)

        // 创建一个新的Fragment实例
        val newFragment = ListFragment()

        // 添加新的Fragment
        transaction.replace(R.id.fragment_container, newFragment)

        // 提交事务
        transaction.commit()
    }
}


