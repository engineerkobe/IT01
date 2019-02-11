package com.bignerdranch.android;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ActionBarContextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bignerdranch.android.Demo3SubFragment.Demo3ViewLab;

import java.util.List;

public class Demo3 extends Fragment {
    private ViewPager mViewPager;
    // Tab titles
    private String[] mTabTitles = { "Top Rated", "Games", "Movies" };
    private List<View> mFragmentTeam;
    private ActionBarContextView mActionBar;
    private TabLayout mTabLayout;
    private CheckBox mDelCheckBox;
    private TextView mTextView;
    private CompoundButton.OnCheckedChangeListener mListener;
    public boolean mDelModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_bookshelf_list,container,false);
        mTabLayout = (TabLayout)v.findViewById(R.id.main_book_tablayout);
        mViewPager = (ViewPager)v.findViewById(R.id.main_book_viewpager);
        mDelCheckBox = (CheckBox) v.findViewById(R.id.main_book_del_button);
        final Demo3ViewLab demo3ViewLab = Demo3ViewLab.get(getActivity(),inflater,container);
        for(String tmp: mTabTitles)
            mTabLayout.addTab((mTabLayout.newTab()).setText(tmp));

        //viewpager 跟 tablayout綁定
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mFragmentTeam = Demo3ViewLab.get(getActivity(),inflater,container).getViews();

        mViewPager.addOnPageChangeListener(new Demo3PageChangeListener());
        mViewPager.setAdapter(demo3ViewLab.getDemo3ViewPagerAdapter());
        mDelCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                demo3ViewLab.openDelStatus(b);
                ((MainActivity)getActivity()).RadioGroupsetVisibility(b);
                //mTextView = (TextView)mFragmentTeam.get(mViewPager.getCurrentItem()).findViewById(R.id.main_demo3fragment_3_textview);
                //mTextView.setText(String.valueOf(b));
            }
        });

        return v;
    }

    private class Demo3PageChangeListener implements ViewPager.OnPageChangeListener {
        /**
         * page發生改變時 刪除模式解除
         */
        private int i = 0;
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            if(this.i != i)
                mDelCheckBox.setChecked(false);
            this.i = i;

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

}
