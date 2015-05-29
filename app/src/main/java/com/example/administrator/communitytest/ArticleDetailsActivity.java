package com.example.administrator.communitytest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EncodingUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class ArticleDetailsActivity extends Activity {

    private Document doc;
    private TextView textView;
    private Handler handler;
    private EditText showText;

    private CommunityListView articleList;
    private ArticleDetailsListAdapter mAdapter;
    private WebView web;
    private ArrayList<BBSArticleInfo> articleInfosList = new ArrayList<BBSArticleInfo>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        for(int i = 0;i <10;i++){
            BBSArticleInfo info = new BBSArticleInfo();
            info.setArticleContent("content"+i);
            info.setArticleTip("tip" + i);
            info.setArticleTitle("title" + i);
            info.setBoutique(true);
            info.setTop(true);
            info.setHot(true);
            info.setArticleIndex(i);
            articleInfosList.add(info);
        }

        initList();

    }
    private void initList(){
        articleList = (CommunityListView)findViewById(R.id.article_details_list);
        articleList.setPullLoadEnable(true);
        articleList.setPullRefreshEnable(false);
       // LayoutInflater layoutInflater = new La
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        View headView = layoutInflater.inflate(R.layout.article_details_head_item, null);

        web = (WebView)headView.findViewById(R.id.article_webview);

        web.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // if(forumWebviewCode!=0) {
                // LoadingHint.showLoadingHint(activity, "加载中");
                //Trace.d("失效url",webView.getUrl());
                //}

            }

            public void onProgressChanged(WebView view, int newProgress) {
                // activity的进度是0 to 10000 (both inclusive),所以要*100

            }

            public void onPageFinished(WebView view, String url) {

                //view.loadUrl("javascript:window.handler.getCode(document.body.innerHTML.toString());");
                // view.loadUrl("javascript:window.local_obj.showSource(document.body.innerHTML);");
                super.onPageFinished(view, url);
                //web.setMinimumHeight(web.getContentHeight());
                //  web.heigh
                LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) web.getLayoutParams(); //取控件textView当前的布局参数
                linearParams.height = web.getContentHeight();// 控件的高强制设成20
                web.setLayoutParams(linearParams);
            }
        });

        articleList.addHeaderView(headView);

        mAdapter = new ArticleDetailsListAdapter(this,articleInfosList);
        articleList.setAdapter(mAdapter);
        web.loadUrl("http://www.baidu.com");
    }
    protected void onResume(){
        super.onResume();
       // web.loadUrl("http://www.baidu.com");
    }
    class ArticleDetailsListAdapter extends BaseAdapter{
        LayoutInflater layoutInflater;
        String inflater = Context.LAYOUT_INFLATER_SERVICE;
        //private List<DetailRecordInfo> listInner = null;
        private List<BBSArticleInfo> listInner = null;
        public ArticleDetailsListAdapter(Context context)
        {}
        public ArticleDetailsListAdapter(Context context,List<BBSArticleInfo> list){
            layoutInflater = (LayoutInflater) context
                    .getSystemService(inflater);
            this.listInner = list;
        }
        public int getCount() {
            //通过此项决定ListView一共有多少Item
            return listInner.size();
        }
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return listInner.get(arg0);
        }
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public boolean isEnabled(int position) {
            return false;
        }

        //为每一项中的控件赋值,每一项显示的数据有它决定
        public View getView(int arg0, View arg1, ViewGroup arg2) {

            //得到模板布局文件对象 ,并为其中的控件赋值
            LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.article_details_item, null);
            ImageView imageHead = (ImageView) linearLayout.findViewById(R.id.article_item_head_img);
            TextView userName = (TextView) linearLayout.findViewById(R.id.article_item_user_name);
            TextView refreshTime = (TextView) linearLayout.findViewById(R.id.post_time);
            TextView floorIndex = (TextView) linearLayout.findViewById(R.id.floor_index);
            TextView articleContent = (TextView) linearLayout.findViewById(R.id.post_content);

            //最新一条消息变颜色

//            if(latest_date == 1 && arg0 == 0){
//                date.setTextColor(getResources().getColor(R.color.gjj_latestdata));
//                way.setTextColor(getResources().getColor(R.color.gjj_latestdata));
//                company.setTextColor(getResources().getColor(R.color.gjj_latestdata));
//                numleft.setTextColor(getResources().getColor(R.color.gjj_latestdata));
//                num.setTextColor(getResources().getColor(R.color.gjj_latestdata));
//
//            }

            BBSArticleInfo articleInfo = listInner.get(arg0);
            //linearLayout.setTag(a);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


            return linearLayout;
        }
    }
}
