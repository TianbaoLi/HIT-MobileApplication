package com.example.sinaRSS;

import android.os.AsyncTask;
import android.view.LayoutInflater;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class NetworkAsyncTask extends AsyncTask<Integer, Integer, News[]> {
	private MyScrollView myScrollView = null;
	private MainActivity activity = null;
	private LayoutInflater inflater = null;
	private HttpClient hClient = null;
	private static String url = "http://api.sina.cn/sinago/list.json?channel=news_toutiao";//RSS源地址
	private int mode;
	
	public NetworkAsyncTask(MyScrollView msv,MainActivity act,LayoutInflater li){
		super();
		myScrollView = msv;
		activity = act;
		inflater = li;
		mode = 0;
	}
	
	public static void setUrl(int index)
	{
		switch(index)
		{
			case 1:
				url="http://api.sina.cn/sinago/list.json?channel=news_toutiao";
				break;
			case 2:
				url="http://api.sina.cn/sinago/list.json?channel=news_auto";
				break;
			case 3:
				url="http://api.sina.cn/sinago/list.json?channel=news_ent";
				break;
			case 4:
				url="http://api.sina.cn/sinago/list.json?channel=news_sports";
				break;
			case 5:
				url="http://api.sina.cn/sinago/list.json?channel=news_finance";
				break;
			case 6:
				url="http://api.sina.cn/sinago/list.json?channel=news_tech";
				break;
			case 7:
				url="http://api.sina.cn/sinago/list.json?channel=news_funny";
				break;
			case 8:
				url="http://api.sina.cn/sinago/list.json?channel=hdpic_toutiao";
				break;
			case 9:
				url="http://api.sina.cn/sinago/list.json?channel=hdpic_funny";
				break;
			case 10:
				url="http://api.sina.cn/sinago/list.json?channel=hdpic_pretty";
				break;
			case 11:
				url="http://api.sina.cn/sinago/list.json?channel=hdpic_story";
				break;
			case 12:
				url="http://api.sina.cn/sinago/list.json?channel=video_video";
				break;
			case 13:
				url="http://api.sina.cn/sinago/list.json?channel=video_highlights";
				break;
			case 14:
				url="http://api.sina.cn/sinago/list.json?channel=video_scene";
				break;
			case 15:
				url="http://api.sina.cn/sinago/list.json?channel=video_funny";
				break;
			default:
				url="http://api.sina.cn/sinago/list.json?channel=news_toutiao";
				break;
		}
	}

	@Override
	protected News[] doInBackground(Integer... params) {
		// TODO Auto-generated method stub
		InputStream is = null;
		News[] results;
		ArrayList<News> alResultNews = new ArrayList<News>();
		try {
			hClient = new DefaultHttpClient();
			HttpGet hGet = new HttpGet(url);
			HttpResponse hResponse = hClient.execute(hGet);
			HttpEntity hEntity = hResponse.getEntity();
			is = hEntity.getContent();
			BufferedReader bReader = new BufferedReader(new InputStreamReader(is,"utf-8"));
			StringBuilder sBuilder = new StringBuilder();
			
	        String content = "";
            String line = "";

            while ((line = bReader.readLine()) != null)
                content += line;
            //Log.i("content", content);
            JSONTokener jsonParser = new JSONTokener(content);
            JSONObject jsonContent = (JSONObject) jsonParser.nextValue();
            JSONObject data = jsonContent.getJSONObject("data");
            JSONArray news = data.getJSONArray("list");
            for(int i=0;i<news.length();i++) {
                //Log.i("NEWS", news.getJSONObject(i).toString());
                JSONObject n = news.getJSONObject(i);
                String titleString = n.getString("long_title");
                String contentString = n.getString("intro");
                String urlString = n.getString("link");
                News current = new News(titleString, contentString, urlString);
                alResultNews.add(current);
            }

			/*while ((line = bReader.readLine()) != null) {//通过HTTP请求读取
				if(line.indexOf("<item>") == -1)
					continue;
				String titleString,contentString,urlString,itemString = null;
				while(line.indexOf("</item>") == -1){
					itemString += line;
					line = bReader.readLine();
				}
				titleString = itemString.substring(itemString.indexOf("<title>")+"<title>".length(),itemString.indexOf("</title>"));//整理新闻格式
				titleString = titleString.substring(titleString.indexOf("CDATA[")+"CDATA[".length(),titleString.lastIndexOf("]") - 1);
				contentString = itemString.substring(itemString.indexOf("<description>")+"<description>".length(),itemString.indexOf("</description>"));
				contentString = contentString.substring(contentString.indexOf("CDATA[")+"CDATA[".length(),contentString.lastIndexOf("]") - 1);
				urlString = itemString.substring(itemString.indexOf("<link>")+"<link>".length(),itemString.indexOf("</link>"));
				urlString = urlString.substring(urlString.indexOf("url=")+"url=".length());
				News news = new News(titleString, contentString, urlString);
				alResultNews.add(news);
	    }*/
	        is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			alResultNews.add(new News("network failed.", "network failed.", ""));
			e.printStackTrace();
		}
		results = new News[alResultNews.size()];
		Iterator<News> iterator = alResultNews.iterator();
		for(int i = 0;i < results.length;++i){
			results[i] = iterator.next();
		}
		return results;
	}
	
	@Override
	protected void onPostExecute(News[] results){
		if(mode == 0){
			myScrollView.setActivity(activity);
			myScrollView.setInflater(inflater);
			for (News news : results) {
				myScrollView.addNews(news,0);
			}
		}
	}

}
