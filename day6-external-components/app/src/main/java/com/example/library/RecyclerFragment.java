package com.example.library;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.example.library.Adapter.ColorAdapter;
import com.example.library.Adapter.item;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecyclerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerFragment extends Fragment {
    View view;
    private static final String TAG = RecyclerFragment.class.getSimpleName();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<item> mList;
    private ColorAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private boolean mIsLoadEnd = false;
    private boolean isLoading = false;
    public interface OnItemChangeListener {
        void onItemChanged(item updatedItem);
    }


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecyclerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecyclerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecyclerFragment newInstance(String param1, String param2) {
        RecyclerFragment fragment = new RecyclerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_recycler, container, false);
        //mList.add(new item("newitem",R.drawable.ic_launcher_background));
        mRecyclerView = view.findViewById(R.id.recycler);
        mAdapter = new ColorAdapter(mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //useItemAdapter();
        //加载更多2
        mAdapter.getLoadMoreModule().setAutoLoadMore(true);
        mAdapter.getLoadMoreModule().setEnableLoadMoreIfNotFullPage(true);
        mAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadMore();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        //3.监听刷新事件
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });


        mAdapter.setOnTextClickListener(new ColorAdapter.OnTextClickListener() {
            @Override
            public void onTextClick(item text) {
                // 处理获取到的文字信息
                // 创建一个TextClickEvent实例，并传递点击的文字信息
                TextClickEvent event = new TextClickEvent(text);
                // 使用EventBus发布事件
                EventBus.getDefault().post(event);
                Log.v("msg","start");

            }

        });
        mAdapter.setOnImageClickListener(new ColorAdapter.OnImageClickListener() {
            @Override
            public void onImageClicked(item imageId) {
                ImageClickedEvent event = new ImageClickedEvent(imageId);
                // 使用EventBus发布事件
                EventBus.getDefault().post(event);
                Log.v("msg","start");

            }
        });

        // 设置适配器
        mRecyclerView.setAdapter(mAdapter);

        // 注册EventBus
        EventBus.getDefault().register(this);

        return view;
    }

    private void loadMore(){
        Log.e(TAG,"loadmore");
        if(mIsLoadEnd){
            mAdapter.getLoadMoreModule().loadMoreEnd();
        }else{
            mRecyclerView.postDelayed(()->{
                Log.e(TAG,"loadmore success");
                mList.add(new item("newitem",R.drawable.ic_launcher_background));
                mAdapter.notifyDataSetChanged();
                mIsLoadEnd = true;
                mSwipeRefreshLayout.setRefreshing(false);
                mAdapter.getLoadMoreModule().loadMoreComplete();
            },2000);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    private void refreshData(){
        mSwipeRefreshLayout.setRefreshing(false);
        mList.add(new item("the new",R.drawable.celeste));
        mAdapter.notifyDataSetChanged();
    }

    public List<item> getList() {
        return mList;
    }

    // 接收事件来更新喜欢状态
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLikeEvent(LoveEvent event) {
        for (item myitem : mList) {
            if (myitem.getitemId() == event.getItemId()) {
                myitem.setLiked(event.isLiked());
                break;
            }
        }

        // 刷新列表显示
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMyItemUpdate(MyItemUpdateEvent event) {
        item updatedItem = event.getUpdatedItem();
        // 更新数据
        // 更新列表中的对应项
        for (item listItem : mList) {
            if (listItem.getitemId() == updatedItem.getitemId()) {
                listItem.setLiked(updatedItem.isLiked());
                break;
            }
        }
        boolean liked = updatedItem.isLiked();

        // 刷新列表显示
        mAdapter.notifyDataSetChanged();
        mAdapter.updateItemLikedStatus(0,liked);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RecyclerActivity) {
            RecyclerActivity activity = (RecyclerActivity) context;
            mList = activity.getMList();
        } else {
            throw new RuntimeException(context.toString()
                    + " must be an instance of YourActivity");
        }
    }

}