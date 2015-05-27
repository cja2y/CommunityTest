package com.example.administrator.communitytest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private ArrayList<BBSArticleInfo> articleInfosList = new ArrayList<BBSArticleInfo>();
    private ArrayList<String> items = new ArrayList<String>();
    int listMode;
    //private SimpleAdapter mAdapter;
    private CommunityListAdapter mAdapter;
    final android.os.Handler mHandler = new android.os.Handler();
    ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

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
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage1", R.drawable.ic_hot);//����ͼƬ            map.put("ItemTitle", "��"+i+"��");
            map.put("ItemImage2", R.drawable.ic_hot);
            map.put("ItemText", "内容" + i + "");
            map.put("ItemTitle", "标题" + i);
            map.put("ItemTip", "#tip下标" + i);
            listItem.add(map);
        }
        initList();
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
        View headerview = inflater.inflate(R.layout.community_top_item, null);
        list.addHeaderView(headerview);

//        mAdapter = new SimpleAdapter(getActivity(), listItem,
//                R.layout.community_list_item, new String[]{"ItemImage1", "ItemImage2", "ItemText", "ItemTitle", "ItemTip"}, new int[]{R.id.community_item_image1,
//                R.id.community_item_image2, R.id.community_item_content, R.id.item_title, R.id.community_item_tip});
        list.setPullLoadEnable(true);
//        list.setAdapter(mAdapter);
//        list.setXListViewListener(this);
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int selectedPosition = parent.getSelectedItemPosition();
//                Log.d("message", id + "id" + "position" + position);
//
//            }
//        });
        mAdapter = new CommunityListAdapter(getActivity(),articleInfosList);
        list.setAdapter(mAdapter);
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

    private void loadData(Boolean isLoad, Boolean isRefresh) {
        for (int i = 0; i < 2; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage1", R.drawable.ic_hot);//����ͼƬ            map.put("ItemTitle", "��"+i+"��");
            map.put("ItemImage2", R.drawable.ic_hot);
            map.put("ItemText", "内容" + i + "");
            map.put("ItemTitle", "标题" + i);
            map.put("ItemTip", "#tip下标" + i);
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
//                mAdapter = new SimpleAdapter(getActivity(), listItem,//��Ҫ�󶨵�����
//                        R.layout.community_list_item, new String[]{"ItemImage1", "ItemImage2", "ItemText", "ItemTitle", "ItemTip"}, new int[]{R.id.community_item_image1,
//                        R.id.community_item_image2, R.id.community_item_content, R.id.item_title, R.id.community_item_tip});
                mAdapter = new CommunityListAdapter(getActivity(),articleInfosList);
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
              //  loadData(true, true);
                mAdapter.notifyDataSetChanged();
                list.stopRefresh();
                list.stopLoadMore();
                list.setRefreshTime("刚刚");
            }
        }, 2000);
    }


    class CommunityListAdapter extends BaseAdapter
    {
        LayoutInflater layoutInflater;
        String inflater = Context.LAYOUT_INFLATER_SERVICE;
        //private List<DetailRecordInfo> listInner = null;
        private List<BBSArticleInfo> listInner = null;
        public CommunityListAdapter(Context context)
        {}
        public CommunityListAdapter(Context context,List<BBSArticleInfo> list){
            layoutInflater = (LayoutInflater) context
                    .getSystemService(inflater);
            this.listInner = list;
        }
        public int getCount() {
            //通过此项决定ListView一共有多少Item
            return listInner.size();
        }
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return listInner.get(arg0);
        }
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public boolean isEnabled(int position) {
            return false;
        }

        //为每一项中的控件赋值,每一项显示的数据有它决定
        public View getView(int arg0, View arg1, ViewGroup arg2) {

            //得到模板布局文件对象 ,并为其中的控件赋值
            LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.community_list_item, null);
            ImageView image1 = (ImageView) linearLayout.findViewById(R.id.community_item_image1);
            ImageView image2 = (ImageView) linearLayout.findViewById(R.id.community_item_image2);
            TextView tip = (TextView) linearLayout.findViewById(R.id.community_item_tip);
            TextView content = (TextView) linearLayout.findViewById(R.id.community_item_content);
            TextView title = (TextView) linearLayout.findViewById(R.id.community_item_title);

            //最新一条消息变颜色

//            if(latest_date == 1 && arg0 == 0){
//                date.setTextColor(getResources().getColor(R.color.gjj_latestdata));
//                way.setTextColor(getResources().getColor(R.color.gjj_latestdata));
//                company.setTextColor(getResources().getColor(R.color.gjj_latestdata));
//                numleft.setTextColor(getResources().getColor(R.color.gjj_latestdata));
//                num.setTextColor(getResources().getColor(R.color.gjj_latestdata));
//
//            }

            BBSArticleInfo articleInfo = listInner.get(arg0);
            //linearLayout.setTag(a);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(),ArticleDetailsActivity.class));
                }
            });
                tip.setText(articleInfo.getArticleTip());
                content.setText(articleInfo.getArticleContent());
                title.setText(articleInfo.getArticleTitle());
//            String companyname = mdetail.getCompany();
//            if(companyname.length()>8){
//                companyname = companyname.substring(0,8)+"...";
//            }
//            String wayfund = mdetail.getOp_type()+mdetail.getRecord_month();
//            if(wayfund.length()>8){
//                wayfund = wayfund.substring(0,8)+"...";
//            }
//
//            String balance = mdetail.getBalance();
//            if (balance.equals("0")){
//                balance = "--";
//            }
//
//            String mdate = mdetail.getRecord_date().substring(5);
//            date.setText(mdate);
//
//
//            way.setText(wayfund);
//            company.setText(companyname);
//            num.setText(mdetail.getAmount());
//            numleft.setText(balance);
//
//            ImageView fundpic = (ImageView)linearLayout.findViewById(R.id.fundpic);
//            int fundpic_visible = mdetail.getGjj_type();
//            if (fundpic_visible==0){
//                fundpic.setVisibility(View.INVISIBLE);
//            }


            return linearLayout;
        }

    }

    private void initList(){
        for(int i = 0;i <10;i++){
            BBSArticleInfo info = new BBSArticleInfo();
            info.setArticleContent("content"+i);
            info.setArticleTip("tip" + i);
            info.setArticleTitle("title" + i);
            info.setBoutique(true);
            info.setTop(true);
            info.setHot(true);
            info.setArticleIndex(i);
            articleInfosList.add(info);
        }
    }
}


