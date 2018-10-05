package com.example.miku.test1;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentMain extends AppCompatActivity {
    private Button button1,button2,button3,button4;
    private Fragment page1,page2,page3,page4;
    private ViewPager viewPager;
    private ViewGroup container;
    private FragmentManager fmgr;
    private FragmentTransaction fragmentTransaction;
    private  ItemFragment itemFragment;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainfragment);
        fmgr = getFragmentManager();

        container = (ViewGroup) findViewById(R.id.container);
        button1 = (Button) findViewById(R.id.Page1);
        button2 = (Button) findViewById(R.id.Page2);
        button3 = (Button) findViewById(R.id.Page3);
        button4 = (Button) findViewById(R.id.Page4);

        itemFragment = new ItemFragment();
//        blankFragment = new BlankFragment();
        fragmentTransaction = fmgr.beginTransaction();
//
        fragmentTransaction.add(R.id.container,itemFragment);
        fragmentTransaction.commit();
        button1.setOnClickListener(b);
        button2.setOnClickListener(b);
        button3.setOnClickListener(b);
        button4.setOnClickListener(b);

    }

    private Button.OnClickListener b = new Button.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.Page1:
//                    fragmentTransaction = fmgr.beginTransaction();
//                    fragmentTransaction.replace(R.id.container,itemFragment);
//                    fragmentTransaction.commit();
                    break;
                case R.id.Page2:
//                    fragmentTransaction = fmgr.beginTransaction();
//                    fragmentTransaction.replace(R.id.container,blankFragment);
//                    fragmentTransaction.commit();
                    break;
                case R.id.Page3:
                    break;
                case R.id.Page4:
                    break;

            }
        }
    };

}

