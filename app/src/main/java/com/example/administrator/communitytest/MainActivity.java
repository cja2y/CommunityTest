package com.example.administrator.communitytest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;


public class MainActivity extends FragmentActivity implements CommunityListView.IXListViewListener {
    private CommunityListView mListView;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> items = new ArrayList<String>();
    private Handler mHandler;
    private int start = 0;
    private static int refreshCnt = 0;
    private String[]             mStrings  = { "Aaaaaa", "Bbbbbb", "Cccccc", "Dddddd", "Eeeeee",
            "Ffffff", "Gggggg", "Hhhhhh", "Iiiiii", "Jjjjjj", "Kkkkkk", "Llllll", "Mmmmmm",
            "Nnnnnn",                     };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        CommunityFragment fragment = new CommunityFragment();
        transaction.replace(R.id.sample_content_fragment, fragment);
        transaction.commit();

//        mListView = (CommunityListView) findViewById(R.id.test_listview);
//        mListView.setPullLoadEnable(true);
//
//        mAdapter = new ArrayAdapter<String>(this, R.layout.community_list_item, items);
//        mListView.setAdapter(mAdapter);
////		mListView.setPullLoadEnable(false);
////		mListView.setPullRefreshEnable(false);
//        mListView.setXListViewListener(this);
//        mHandler = new Handler();
    }

    private void geneItems(){
        for(int i = 0; i<10;i++){
            items.add(i+"test"+start);
        }
    }
    @Override
    public void onRefresh() {
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                start = ++refreshCnt;
//                items.clear();
//
//                // mAdapter.notifyDataSetChanged();
//                mAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.community_list_item, items);
//                mListView.setAdapter(mAdapter);
//                onLoad();
//            }
//        }, 2000);
    }

    @Override
    public void onLoadMore() {
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                geneItems();
//                mAdapter.notifyDataSetChanged();
//                onLoad();
//            }
//        }, 2000);
    }
    private void onLoad(){
//        mListView.stopRefresh();
//        mListView.stopLoadMore();
//        mListView.setRefreshTime("¸Õ¸Õ");
    }
}
