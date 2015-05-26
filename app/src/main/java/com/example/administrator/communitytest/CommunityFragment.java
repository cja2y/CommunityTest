package com.example.administrator.communitytest;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * <p/>
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class CommunityFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SlidingTabLayout mSlidingTabLayout;


    private ViewPager mViewPager;
    private String[] titlesList = {"1", "2", "3"};
    private CommunityListView mListView;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> items = new ArrayList<String>();
    //  private Handler mHandler;
    private int start = 0;
    private static int refreshCnt = 0;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private List<View> listViews = new ArrayList<View>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_community, container, false);
    }


    public void onViewCreated(View view, Bundle savedInstanceState) {
        // BEGIN_INCLUDE (setup_viewpager)
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        //initListview();

        if (mSectionsPagerAdapter == null) {
            mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager(), titlesList);
        }
        // mViewPager.setAdapter(new SamplePagerAdapter());
        // END_INCLUDE (setup_viewpager)
        mViewPager.setAdapter(mSectionsPagerAdapter);
        // BEGIN_INCLUDE (setup_slidingtablayout)
        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's PagerAdapter set.
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
        // END_INCLUDE (setup_slidingtablayout)
    }

    public void initListview() {
        geneItems();
        for (int i = 0; i < 8; i++) {
            final Handler mHandler = new Handler();
            final ArrayAdapter mAdapter = new ArrayAdapter<String>(getActivity(), R.layout.community_list_item, items);

            // final  CommunityListView communityListView = (CommunityListView)view.findViewById(R.id.community_listview_test);
            final CommunityListView communityListView = new CommunityListView(getActivity());
            communityListView.setPullLoadEnable(true);
            communityListView.setAdapter(mAdapter);
            communityListView.setXListViewListener(new CommunityListView.IXListViewListener() {
                @Override
                public void onRefresh() {
                    Log.d("intointo", "onrefreshsuccess1");
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("intointo", "onrefreshsuccess2");
                            start = ++refreshCnt;
                            items.clear();
                            // mAdapter.notifyDataSetChanged();
                            ArrayAdapter mAdapter1 = new ArrayAdapter<String>(getActivity(), R.layout.community_list_item, items);
                            communityListView.setAdapter(mAdapter1);
                            communityListView.stopRefresh();
                            communityListView.stopLoadMore();
                            communityListView.setRefreshTime("먼먼");
                        }
                    }, 2000);
                }

                @Override
                public void onLoadMore() {
                    Log.d("intointo", "onloadmodesuccess1");
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("intointo", "onloadmodesuccess2");
                            geneItems();
                            mAdapter.notifyDataSetChanged();
                            communityListView.stopRefresh();
                            communityListView.stopLoadMore();
                            communityListView.setRefreshTime("먼먼");
                        }
                    }, 2000);
                }
            });
            listViews.add(communityListView);
        }
    }

    class SamplePagerAdapter extends PagerAdapter {

        /**
         * @return the number of pages to display
         */
        @Override
        public int getCount() {
            return listViews.size();
        }


        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Item " + (position + 1);
        }
        // END_INCLUDE (pageradapter_getpagetitle)


        @Override
        public Object instantiateItem(ViewGroup container, int position) {


            // Inflate a new layout from our resources
//            View view = getActivity().getLayoutInflater().inflate(R.layout.pager_item,
//                    container, false);
            // Add the newly created View to the ViewPager
//            DropDownListView listView = (DropDownListView)view.findViewById(R.id.community_listview);
//            listView.addView(getActivity().getLayoutInflater().inflate(R.layout.community_list_item,
//                    container, false));
            // CommunityListView communityListView = new CommunityListView(getActivity());

//            container.addView(view);
//
//            // Retrieve a TextView from the inflated View, and update it's text
//            TextView title = (TextView) view.findViewById(R.id.item_title);
//            title.setText(String.valueOf(position + 1));


            try {
                if (listViews.get(position).getParent() == null)
                    ((ViewPager) container).addView(listViews.get(position), 0);
                else {
                    ((ViewGroup) listViews.get(position).getParent()).removeView(listViews.get(position));
                    ((ViewPager) container).addView(listViews.get(position), 0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return listViews.get(position);
            //return view;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    private void geneItems() {
        for (int i = 0; i < 2; i++) {
            items.add(i + "test" + start);
        }
    }

    public static class SectionsPagerAdapter extends FragmentPagerAdapter {
        String[] titles;
        int contentListMode;

        public SectionsPagerAdapter(FragmentManager fragmentManager,
                                    String[] titles) {
            super(fragmentManager);
            this.titles = titles;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Item " + (position + 1);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            BBSListFragment fragment = new BBSListFragment().newInstance(""+position,""+position);

            return fragment;
        }

        @Override
        public int getCount() {
            return titles.length;

        }
    }

}
