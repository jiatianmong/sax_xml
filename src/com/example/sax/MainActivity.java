package com.example.sax;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myutil.SaxService;


public class MainActivity extends Activity {

	private ListView lv;
	private Map<String,String> map;
	private List<Map<String,String>> listmap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		lv = (ListView) findViewById(R.id.lv);
		
		initListData();
		
	}
	public void initListData(){			

		new Thread() {
			

			@Override
			public void run() {
			// TODO Auto-generated method stub

			try {
				
				//  	http://news.qq.com/newsgn/rss_newsgn.xml  
				//		  http://192.168.1.222:8080/rss_newsgn.xml
				//		http://www.people.com.cn/rss/politics.xml
				//		http://192.168.1.222:8080/news.xml
				//		http://news.qq.com/newsgn/rss_newsgn.xml
				//10.15.114.126:
				System.out.println("进入获取http协议");
				String path = "http://news.qq.com/newsgn/rss_newsgn.xml";
				URL url = new URL(path);
				
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				
				connection.setRequestMethod("GET");
				connection.setConnectTimeout(5000);						
				int code = connection.getResponseCode();
				System.out.println("获取网络返回码");
				System.out.println("--->code= "+code);
				if (code == 200) {
					
				String item = "item";
					BufferedReader bufferedReader = new BufferedReader
                            (new InputStreamReader(connection.getInputStream(),"gb2312"));
                   

					
                    listmap = SaxService.saxReadXML (bufferedReader,item);
					runOnUiThread (new Runnable() {
						public void run() {
							
							try {
								lv.setAdapter(new Myadapter());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					});
					
					
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}			
		};}.start();
		
	}
		
	private class Myadapter extends BaseAdapter {
		
		 
		@Override
		public int getCount() {
			return listmap.size();
		}
	
		@Override
		public Object getItem(int position) {
	
			return null;
		}
	
		@Override
		public long getItemId(int position) {
			return 0;
		}
	
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View view;
			if(convertView == null){
				view = View.inflate(getApplicationContext(), R.layout.item, null);	
			}else{
				view = convertView;
			}
			
			ImageView iv_con = (ImageView) view.findViewById(R.id.iv_icon);
			TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
			TextView tv_pubDate = (TextView)view.findViewById(R.id.tv_pubDate);
			TextView tv_link = (TextView) view.findViewById(R.id.tv_link);			
			TextView tv_descri = (TextView)view.findViewById(R.id.tv_descri);
			
			
			Map<String, String> map = listmap.get(position);
			
			
			tv_title.setText(map.get("title").trim());		
			tv_descri.setText(map.get("description").trim());
			tv_link.setText(map.get("link").trim());
			tv_pubDate.setText(map.get("pubDate").trim());
			
			
			return view;
		}	
	
	}
	
}


