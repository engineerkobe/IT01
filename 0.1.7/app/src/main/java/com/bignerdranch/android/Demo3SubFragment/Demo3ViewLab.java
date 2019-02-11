package com.bignerdranch.android.Demo3SubFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.android.R;

import java.util.ArrayList;
import java.util.List;

public class Demo3ViewLab {

    private final Demo3Fragment_1 f1;
    private final Demo3Fragment_2 f2;
    private final Demo3Fragment_3 f3;
    private Context mContext;
    private ArrayList<View> mViewList;
    private Demo3ViewPagerAdapter mDemo3ViewPagerAdapter;
    private static Demo3ViewLab sDemo3ViewLab;

    private Demo3ViewLab(Context context, LayoutInflater inflater, ViewGroup container) {
        mContext = context;
        mViewList = new ArrayList<>();
        View v1 = inflater.inflate(R.layout.main_bookshelf_fragment_1,container,false);
        View v2 = inflater.inflate(R.layout.main_bookshelf_fragment_1,container,false);
        View v3 = inflater.inflate(R.layout.main_bookshelf_fragment_1,container,false);
        f1 = new Demo3Fragment_1(mContext,v1);
        f2 = new Demo3Fragment_2(mContext,v2);
        f3 = new Demo3Fragment_3(mContext,v3);
        mViewList.add(f1.Create());
        mViewList.add(f2.Create());
        mViewList.add(f3.Create());
        mDemo3ViewPagerAdapter = new Demo3ViewPagerAdapter(mViewList);
    }


    public static Demo3ViewLab get(Context context,LayoutInflater inflater, ViewGroup container) {
        if(sDemo3ViewLab == null)
            sDemo3ViewLab = new Demo3ViewLab(context,inflater,container);

        return sDemo3ViewLab;
    }
    public List<View> getViews() {
        return mViewList;
    }
    public Demo3ViewPagerAdapter getDemo3ViewPagerAdapter() {
        return mDemo3ViewPagerAdapter;
    }

    public void openDelStatus(boolean b) {
       f1.openDelstatus(b);


    }

    private class Demo3ViewPagerAdapter extends PagerAdapter {
        final private ArrayList<View> inViewList;

        private Demo3ViewPagerAdapter(ArrayList<View> viewList) {
            super();
            inViewList = viewList;
        }

        @Override
        public int getCount() {

            return inViewList.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(inViewList.get(position));
            return inViewList.get(position);
        }


        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(inViewList.get(position));
        }
    }

}
