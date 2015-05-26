package com.example.administrator.communitytest;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Handler;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link BBSListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BBSListFragment extends BaseFragment implements CommunityListView.IXListViewListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private BBSListAdapter adapter;
    private String mParam1;
    private String mParam2;
    LayoutInflater mInflater;
    CommunityListView list;
    private ArrayList<String> items = new ArrayList<String>();
    int listMode;
    private SimpleAdapter mAdapter;
    final android.os.Handler mHandler = new android.os.Handler();
    ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,     Object>>();
    public static BBSListFragment newInstance(String param1, String param2) {
        BBSListFragment fragment = new BBSListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public BBSListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        if (mParam1.equals("0")) {
            items = DataSource.getList1();
        } else if (mParam1.equals("1")) {
            items = DataSource.getList2();
        } else if (mParam1.equals("2")) {
            items = DataSource.getList3();
        }

        listItem.clear();
        for(int i=0;i<10;i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage1", R.drawable.ic_hot);//����ͼƬ            map.put("ItemTitle", "��"+i+"��");
            map.put("ItemImage2",R.drawable.ic_hot);
            map.put("ItemText", "内容"+i+"");
            map.put("ItemTitle","标题"+i);
            map.put("ItemTip","#tip下标"+i);
            listItem.add(map);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_bbslist, container, false);
        View rootView = inflater.inflate(R.layout.fragment_bbslist, container, false);
        mInflater = inflater;
        list = (CommunityListView) rootView.findViewById(R.id.bbs_list);
        geneItems();


        //mAdapter = new ArrayAdapter<String>(getActivity(), R.layout.community_list_item, items);
        // listItem = new ArrayList<HashMap<String,     Object>>();/*�������д������*/


         mAdapter = new SimpleAdapter(getActivity(),listItem,//��Ҫ�󶨵�����
                R.layout.community_list_item,new String[]{"ItemImage1","ItemImage2", "ItemText","ItemTitle","ItemTip"},new int[] {R.id.community_item_image1,
                R.id.community_item_image2,R.id.community_item_content,R.id.item_title,R.id.community_item_tip});


       list.setPullLoadEnable(true);
        list.setAdapter(mAdapter);
        list.setXListViewListener(this);
        return rootView;
    }


    protected void initPage(boolean newData) {
//        //timeOut.setVisibility(View.GONE);
//        TextView text = (TextView) footView.findViewById(R.id.list_footview_text);
//        text.setText(R.string.loading);
//        footView.findViewById(R.id.list_footview_progress).setVisibility(View.VISIBLE);
//        footView.setOnClickListener(null);
//
//        page = newData ? 1 : page + 1;
    }

    private void geneItems() {
//        for(int i = 0; i<2;i++){
//            items.add(i+"test");
//        }
    }

    private void loadData(Boolean isLoad,Boolean isRefresh) {
        for(int i=0;i<2;i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage1", R.drawable.ic_hot);//����ͼƬ            map.put("ItemTitle", "��"+i+"��");
            map.put("ItemImage2",R.drawable.ic_hot);
            map.put("ItemText", "内容"+i+"");
            map.put("ItemTitle","标题"+i);
            map.put("ItemTip","#tip下标"+i);
            listItem.add(map);
        }
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("intointo", "onrefreshsuccess2");
                // start = ++refreshCnt;
                items.clear();
                // mAdapter.notifyDataSetChanged();
                mAdapter = new SimpleAdapter(getActivity(),listItem,//��Ҫ�󶨵�����
                        R.layout.community_list_item,new String[]{"ItemImage1","ItemImage2", "ItemText","ItemTitle","ItemTip"},new int[] {R.id.community_item_image1,
                        R.id.community_item_image2,R.id.community_item_content,R.id.item_title,R.id.community_item_tip});
                list.setAdapter(mAdapter);
                list.stopRefresh();
                list.stopLoadMore();
                list.setRefreshTime("刚刚");
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("intointo", "onloadmodesuccess2");
                loadData(true,true);
                mAdapter.notifyDataSetChanged();
                list.stopRefresh();
                list.stopLoadMore();
                list.setRefreshTime("刚刚");
            }
        }, 2000);
    }

}


