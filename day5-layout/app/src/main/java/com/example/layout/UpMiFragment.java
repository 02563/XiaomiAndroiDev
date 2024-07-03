package com.example.layout;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpMiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpMiFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpMiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpMiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpMiFragment newInstance(String param1, String param2) {
        UpMiFragment fragment = new UpMiFragment();
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
        View view = inflater.inflate(R.layout.fragment_up_mi, container, false);

        // 获取按钮引用
        Button button = view.findViewById(R.id.language);
        Context context = getActivity();

        // 为按钮设置点击事件监听器
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context,button);
                popup.getMenuInflater().inflate(R.menu.menu_language, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        //switch (item.getItemId()){
                        //    case R.id.menu_item_english:
                        //        Toast.makeText(context,"切换英文~",
                        //                Toast.LENGTH_SHORT).show();
                        //        break;
                        //    case R.id.menu_item_chinese:
                        //        Toast.makeText(context,"切换中文~",
                        //                Toast.LENGTH_SHORT).show();
                        //        break;
                        //}
                        return true;
                    }
                });
                popup.show();
            }

        });

        return inflater.inflate(R.layout.fragment_up_mi, container, false);
    }
}