package com.example.parsler.pycman.Components.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parsler.pycman.Commons.StaticConstants;
import com.example.parsler.pycman.Commons.StringConstants;
import com.example.parsler.pycman.R;

public class SlidingTabsBasicFragment extends Fragment {

    static final String[] tabheader = new String[]{
            StringConstants.TAB_DASHBOARD_STEP_1,
            StringConstants.TAB_DASHBOARD_STEP_2,
            StringConstants.TAB_DASHBOARD_STEP_3};

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;



    public SlidingTabsBasicFragment() {
        // Default constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.home_tab_fragment, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SamplePagerAdapter());
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(mViewPager);
    }


    class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return StaticConstants.DASHBOARD_TAB_COUNT;
        }


        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return tabheader[position];
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = null;
            if(tabheader[position].equalsIgnoreCase(StringConstants.TAB_DASHBOARD_STEP_1)) {

                //view = getActivity().getLayoutInflater().inflate(R.layout.tab_choose_item, container, false);

            } else if (tabheader[position].equalsIgnoreCase(StringConstants.TAB_DASHBOARD_STEP_2)) {

                view = getActivity().getLayoutInflater().inflate(R.layout.fragment_add_location, container, false);

            } else if (tabheader[position].equalsIgnoreCase(StringConstants.TAB_DASHBOARD_STEP_3)) {

                //view = getActivity().getLayoutInflater().inflate(R.layout.tab_select_service, container, false);
            }

            container.addView(view);
            return view;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
