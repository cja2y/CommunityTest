package com.example.administrator.communitytest;

/**
 * Created by Administrator on 2015/5/26.
 */
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {
    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    protected String getPageTitle() {
        return this.getClass().getSimpleName();
    }

}