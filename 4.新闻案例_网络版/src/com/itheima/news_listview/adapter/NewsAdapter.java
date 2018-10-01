package com.itheima.news_listview.adapter;

import java.util.ArrayList;

import com.itheima.news_listview.R;
import com.itheima.news_listview.bean.NewsBean;
import com.itheima.news_listview.view.MyImageView;
import com.loopj.android.image.SmartImageView;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NewsAdapter extends BaseAdapter {
	
	private ArrayList<NewsBean> list;
	private Context context;

	//通过构造方法接受要显示的新闻数据集合
	public NewsAdapter(Context context,ArrayList<NewsBean> list){
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		//1.复用converView优化listview,创建一个view作为getview的返回值用来显示一个条目
		if(convertView != null){
			view = convertView;
		}else {
			//context:上下文, resource:要转换成view对象的layout的id, root:将layout用root(ViewGroup)包一层作为codify的返回值,一般传null
//			view = View.inflate(context, R.layout.item_news_layout, null);//将一个布局文件转换成一个view对象
			
			//通过LayoutInflater将布局转换成view对象
//			view =  LayoutInflater.from(context).inflate(R.layout.item_news_layout, null);
			
			//通过context获取系统服务得到一个LayoutInflater，通过LayoutInflater将一个布局转换为view对象
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.item_news_layout, null);
			
		}
		//2.获取view上的子控件对象
		MyImageView item_img_icon = (MyImageView) view.findViewById(R.id.item_img_icon);
		TextView item_tv_des = (TextView) view.findViewById(R.id.item_tv_des);
		TextView item_tv_title = (TextView) view.findViewById(R.id.item_tv_title);
		TextView item_tv_comment = (TextView) view.findViewById(R.id.item_tv_comment);
		TextView item_tv_type = (TextView) view.findViewById(R.id.item_tv_type);
		
		
		//3.获取postion位置条目对应的list集合中的新闻数据，Bean对象
		NewsBean newsBean = list.get(position);
		//4.将数据设置给这些子控件做显示
		item_img_icon.setImageUrl(newsBean.icon_url);
		item_tv_title.setText(newsBean.title);
		item_tv_des.setText(newsBean.des);
		item_tv_comment.setText("评论："+newsBean.comment);
		
		//0 ：头条 1 ：娱乐 2.体育
		switch (newsBean.type) {
		case 0:
			item_tv_type.setText("头条");
			break;
		case 1:
			item_tv_type.setText("娱乐 ");
			break;
		case 2:
			item_tv_type.setText("体育");
			break;
		default:
			break;
		}

		
		
		return view;
	}

}
