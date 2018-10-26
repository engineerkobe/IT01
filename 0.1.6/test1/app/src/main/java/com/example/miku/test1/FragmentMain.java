package com.example.miku.test1;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.miku.test1.FragmentSpinner.ItemFragment;
import com.example.miku.test1.R;
import com.example.miku.test1.bean.Book;
import com.example.miku.test1.util.NewsUtils;
import com.example.miku.test1.util.UserInfoUtil;

import java.net.URL;

public class FragmentMain extends AppCompatActivity {
    private Button button1,button2,button3,button4;
    private Fragment page1,page2,page3,page4;
    private ViewPager viewPager;
    private ViewGroup container;
    private FragmentManager fmgr;
    private FragmentTransaction fragmentTransaction;
    private ItemFragment itemFragment;
    private PlusOneFragment plusOneFragment;
    private NfcAdapter nfcAdapter;
    private PendingIntent mPendingIntent;
    private String uid;
    private Context mContext;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainfragment);
        fmgr = getFragmentManager();

        container = (ViewGroup) findViewById(R.id.container);
        button1 = (Button) findViewById(R.id.Page1);
        button2 = (Button) findViewById(R.id.Page2);
        button3 = (Button) findViewById(R.id.Page3);
        button4 = (Button) findViewById(R.id.Page4);
        mContext = this;
        itemFragment = new ItemFragment();
        plusOneFragment = new PlusOneFragment();
//        blankFragment = new BlankFragment();
        fragmentTransaction = fmgr.beginTransaction();
        fragmentTransaction.add(R.id.container,itemFragment);
        //fragmentTransaction.add(R.id.container,plusOneFragment);
//
        fragmentTransaction.commit();
        button1.setOnClickListener(b);
        button2.setOnClickListener(b);
        button3.setOnClickListener(b);
        button4.setOnClickListener(b);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

    }

    private Button.OnClickListener b = new Button.OnClickListener(){
        @Override
        public void onClick(View view) {
            fragmentTransaction = fmgr.beginTransaction();
            switch (view.getId()){
                case R.id.Page1:
                    fragmentTransaction.replace(R.id.container,itemFragment);
                    fragmentTransaction.commit();
                    break;
                case R.id.Page2:
                    fragmentTransaction.replace(R.id.container,plusOneFragment);
                    fragmentTransaction.commit();
                    break;
                case R.id.Page3:
                    break;
                case R.id.Page4:
                    break;

            }
        }
    };
    protected void onResume() {
        super.onResume();

        nfcAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {
            final String uid = ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID)) ;
            Toast.makeText(mContext,uid , Toast.LENGTH_LONG).show();
            this.uid = uid;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Book tmp = NewsUtils.BorrowingBooksForNetWork("null",uid);
                    runOnUiThread(new Runnable() {
                        public void run()
                        {
                            if(tmp == null)
                                Toast.makeText(mContext,"沒這本書" , Toast.LENGTH_LONG).show();

                        }
                    });
                }
            }).start();
        }
    }

    private String ByteArrayToHexString(byte[] inarray) {
        int i, j, in;
        String[] hex = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
                "B", "C", "D", "E", "F" };
        String out = "";
        for (j = 0; j < inarray.length; ++j) {
            in = (int) inarray[j] & 0xff;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
        }
        return out;
    }

}

