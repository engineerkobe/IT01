package com.bignerdranch.android;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private ActionBar mActionBar;
    private RadioGroup mMainRadioGroup;
    private FragmentManager mFragmentManager;
    private static final String MAINRADIOGROUP = "MAINRADIOGROUP";
    private Demo mHomeFragment;
    private Demo3 mNotImplementFragment;
    private Demo4 mAccountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        closeActionBar();

    }
    /*
     *  初始化所有view
     *
     */
    private void initView() {
        mMainRadioGroup = (RadioGroup) findViewById(R.id.main_menu_radiogroup);
        mMainRadioGroup.setOnCheckedChangeListener(mMenuOnchedChabgeListener);
//        mMainRadioGroup.set
        mFragmentManager =  getSupportFragmentManager();
        Fragment fragment = mFragmentManager.findFragmentById(R.id.main_fragment_container);
        if(fragment == null) {
            fragment = new Demo();
            mFragmentManager.beginTransaction().add(R.id.main_fragment_container,fragment).commit();
        }
    }

    /**
     * radiogtoup 開關
     * @param b
     */
    public void RadioGroupsetVisibility(boolean b) {
        if(!b)
            mMainRadioGroup.setVisibility(View.VISIBLE);
        else
            mMainRadioGroup.setVisibility(View.GONE);
    }
    /*
     *關閉ActionBar
     */
    private void closeActionBar() {
        getSupportActionBar().hide();
    }

    RadioGroup.OnCheckedChangeListener mMenuOnchedChabgeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch(i) {
                case R.id.main_menu_home:
                    Log.d(MAINRADIOGROUP,"home radio被點擊");
                    chanageHomeFragment();
                    //mFragmentManager.beginTransaction().replace(R.id.main_fragment_container,new Demo()).commit();
                    break;
                case R.id.main_menu_not_implement:
                    Log.d(MAINRADIOGROUP,"not implement被點擊");
                    break;
                case R.id.main_menu_borrow_book:
                    Log.d(MAINRADIOGROUP,"book radio被點擊");
                    chanageBookFragment();
                    break;
                case R.id.main_menu_account:
                    Log.d(MAINRADIOGROUP,"account radio被點擊");
                    changeAccountFragment();
                    break;
            }
        }

    };


    private void chanageHomeFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(mHomeFragment == null)
            mHomeFragment = new Demo();
        fragmentTransaction.replace(R.id.main_fragment_container,mHomeFragment).commit();
    }

    private void chanageBookFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(mNotImplementFragment == null)
            mNotImplementFragment = new Demo3();
        fragmentTransaction.replace(R.id.main_fragment_container,mNotImplementFragment).commit();
    }

    private void changeAccountFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(mAccountFragment == null)
            mAccountFragment = new Demo4();
        fragmentTransaction.replace(R.id.main_fragment_container,mAccountFragment).commit();
    }

}
