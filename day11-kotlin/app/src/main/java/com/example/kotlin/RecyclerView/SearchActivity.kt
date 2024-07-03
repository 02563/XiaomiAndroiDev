package com.example.kotlin.RecyclerView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class SearchActivity : AppCompatActivity() , OnGameListRefreshListener{

    lateinit var gameList: List<Game>

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)



        // 初始化gameList
        gameList = ArrayList()
        (gameList as ArrayList<Game>).add(Game("Game1", R.drawable.game_image, false))
        (gameList as ArrayList<Game>).add(Game("Game2", R.drawable.game_image, false))
        (gameList as ArrayList<Game>).add(Game("Game3", R.drawable.game_image, false))
        (gameList as ArrayList<Game>).add(Game("Game4", R.drawable.game_image, false))
        (gameList as ArrayList<Game>).add(Game("Game5", R.drawable.game_image, false))
        (gameList as ArrayList<Game>).add(Game("Game6", R.drawable.game_image, false))
        (gameList as ArrayList<Game>).add(Game("Game7", R.drawable.game_image, false))
        (gameList as ArrayList<Game>).add(Game("Game8", R.drawable.game_image, false))
        (gameList as ArrayList<Game>).add(Game("Game9", R.drawable.game_image, false))

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_home -> {
                    // 切换到首页（列表页面）
                    // 使用FragmentTransaction将列表Fragment添加到fragment_container中
                    val homeFragment = ListFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, homeFragment)
                        .commit()
                    true
                }

                R.id.action_profile -> {
                    // 切换到我的页面
                    // 使用FragmentTransaction将我的Fragment添加到fragment_container中
                    val profileFragment = ProfileFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, profileFragment)
                        .commit()
                    true
                }

                else -> false
            }

        }
        val gameAdapter = GameAdapter(this, gameList)
        gameAdapter.setSearchActivity(this)
        // 默认加载主页的Fragment
        val homeFragment = ListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, homeFragment)
            .commit()
    }

    fun onItemClick(game: Game) {
        showDetailFragment(game)
    }

//    fun showDetailFragment(game: Game) {
//        //val detailFragment = DetailFragment()
//        val detailFragment = DetailFragment.newInstance(game.gameImg, game.gameName,game.like)
//        //detailFragment.favoriteClickListener =
//
//
//        val bundle = Bundle()
//        bundle.putInt("gameImg", game.gameImg)
//        bundle.putString("gameName", game.gameName)
//        bundle.putBoolean("like", game.like)
//        detailFragment.arguments = bundle
//
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container, detailFragment)
//            .addToBackStack(null)
//            .commit()
//    }


    fun showDetailFragment(game: Game) {
        val detailFragment = DetailFragment.newInstance(game.gameImg,game.gameName,game.like)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailFragment)
            .addToBackStack(null)
            .commit()
    }
    override fun onResume() {
        super.onResume()
        EventBus.getDefault().register(this)
    }
    override fun onPause() {
        super.onPause()
        EventBus.getDefault().unregister(this)
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onDetailClickEvent(event: DetailFragment.DetailClickEvent) {
        // 在这里处理点击事件，更新gameList
        //val game = event.game
        // 更新gameList的逻辑
        for(game in gameList){
            if (game.gameName==event.gameName){
                game.like = event.gamelike
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
    override fun onRefreshGameList() {
        // Implement logic to fetch the latest game list
        val latestGameList =gameList // Fetch the latest game list from wherever you get it

        // Update the fragment with the latest game list
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as? ListFragment
        fragment?.updateGameList(latestGameList)
    }

}
