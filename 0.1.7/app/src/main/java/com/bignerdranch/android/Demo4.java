package com.bignerdranch.android;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bignerdranch.android.Util.ImageUtil;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.transform.Result;

import static com.bignerdranch.android.Util.DensityUtil.dip2px;


public class Demo4 extends Fragment {
    private RecyclerView mRecyclerView;
    private Bitmap mPhotoSticker;
    private View mHeaderView;
    private static final int SELECTMYPHONE = 1;
    private static final int CROP_PICTURE = 2;

    private static final String IMAGE_FILE_LOCATION = "file:///" + Environment.getExternalStorageDirectory().getPath() + "/temp.jpg";
    private Uri imageUri = Uri.parse(IMAGE_FILE_LOCATION);
    private ImageView Head;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhotoSticker =BitmapFactory.decodeResource(getResources(),R.drawable.ic_action_name_main_menu_borrow_book_clicked);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_account_list,container,false);
        //頂部
        mRecyclerView = (RecyclerView) v.findViewById(R.id.main_menu_account_recyclerview) ;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mHeaderView = instanceListHeadView(inflater,container);
        updataUI(mHeaderView);
        return v;
    }

    /**
     * 實體化頭部
     * @param inflater
     * @param container
     * @return
     */
    private View instanceListHeadView(LayoutInflater inflater, ViewGroup container) {
        View headerView = inflater.inflate(R.layout.main_account_list_item_header, container,false);
        Head = (ImageView)headerView.findViewById(R.id.main_menu_account_header_image);
        Head.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,SELECTMYPHONE);

            }
        });
        return headerView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK)
            return;
        if(data!=null)
            switch (requestCode) {
                case SELECTMYPHONE:
                    //Uri selectedImage = data.getData();
                    startPhotoZoom(data.getData());//拿到所选图片的Uri
                    break;
                case CROP_PICTURE: // 取得裁剪后的图片
                    try {
                        Head.setImageBitmap(BitmapFactory.decodeStream(
                                getActivity().getContentResolver().openInputStream(imageUri)));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
            }
    }

    /**
     *
     * @param uri 選取到的相片
     *
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        //该参数可以不设定用来规定裁剪区的宽高比
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //该参数设定为你的imageView的大小
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("scale", true);
        //是否返回bitmap对象
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//输出图片的格式
        intent.putExtra("noFaceDetection", true); // 头像识别
        startActivityForResult(intent, CROP_PICTURE);
    }



    private void updataUI(View headerView) {
        DemoAdapter adapter = new DemoAdapter();
        adapter.addHeaderView(headerView);
        mRecyclerView.addItemDecoration(new DemoItemDecoration());
        mRecyclerView.setAdapter(adapter);
    }

    private class DemoItemDecoration extends RecyclerView.ItemDecoration {
        Integer [] itemtmp = {4,8,11};
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int pos = parent.getChildLayoutPosition(view);

            ArrayList<Integer> item = new ArrayList<>(Arrays.asList(itemtmp));
                if (item.contains(pos))
                    outRect.bottom = dip2px(getActivity(),10);
                else if (pos == 0)
                    outRect.bottom = 0;
                else
                    outRect.bottom = 2;
        }

        @Override
        public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
        }
    }

    private class DemoAdapter extends RecyclerView.Adapter<AccountHolder> {

        private View mHeaderView;
        private View mNormalView;

        public DemoAdapter() {
        }
        public void addHeaderView(View view) {
            mHeaderView = view;
            notifyItemInserted(0);
        }

        @Override
        public int getItemViewType(int position) {
            if(mHeaderView != null && position==0)
                return ITEM.HEADER;
            return super.getItemViewType(position);
        }
        @NonNull
        @Override
        public AccountHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewtype) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            if(viewtype == ITEM.HEADER) {
                return new AccountHolder(mHeaderView, viewtype);
            }
            mNormalView = inflater.inflate(R.layout.main_account_list_item, viewGroup,false);
            return new AccountHolder(mNormalView,viewtype);
        }

        @Override
        public void onBindViewHolder(@NonNull AccountHolder accountHolder, int i) {

        }

        @Override
        public int getItemCount() {
            int count = 10;
            //如果有投的話

            if(mHeaderView != null)
                count++;
            return count;
        }

    }

    private class AccountHolder extends RecyclerView.ViewHolder{
        public AccountHolder(View v, int viewType) {
            super(v);
        }
    }

    private static class ITEM{
        private static final int NORMAL = 0;
        private static final int HEADER = 1;
    }
}
