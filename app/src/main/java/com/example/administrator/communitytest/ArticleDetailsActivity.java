package com.example.administrator.communitytest;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
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


public class ArticleDetailsActivity extends Activity {

    private Document doc;
    private TextView textView;
    private Handler handler;
    private EditText showText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        textView = (TextView)findViewById(R.id.html_text);
        showText = (EditText)findViewById(R.id.show_text);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                showText.setText(doc + "");
            }
        };

        new Thread() {
            public void run() {

                // 1.直接从字符串中输入HTML文档
                // String html =
                // "<html><head><title> 测试html的加载 </title></head>"
                // + "<body><p> 这是一篇使用jsoup来加载html的文章 </p></body></html>";
                // doc = Jsoup.parse(html);
                // handler.sendEmptyMessage(0);

               //  2.1 从 URL直接加载 HTML文档
                 try {
                 doc = Jsoup.connect("http://blog.csdn.net/u010142437").get();
                 handler.sendEmptyMessage(0);
                 } catch (IOException e) {
                 e.printStackTrace();
                 }

                // 2.2 从 URL直接加载 HTML文档
                // try {
                // doc = Jsoup.connect("http://blog.csdn.net/u010142437")
                // .data("query", "Java") // 请求参数
                // .userAgent("I’m jsoup") // 设置 User-Agent
                // .cookie("auth", "token") // 设置 cookie
                // .timeout(5000) // 设置连接超时时间
                // .post(); // 使用 POST方法访问 URL
                // handler.sendEmptyMessage(0);
                // } catch (IOException e) {
                // e.printStackTrace();
                // }

                // 2.3从 URL直接加载 HTML文档
                // try {
                // doc = Jsoup.parse(new URL(
                // "http://blog.csdn.net/u010142437"), 5000);
                // handler.sendEmptyMessage(0);
                // } catch (MalformedURLException e) {
                // e.printStackTrace();
                // } catch (IOException e) {
                // e.printStackTrace();
                // }

                // 2.4从 URL直接加载 HTML文档：先使用流读取html，然后使用Jsoup转换成Document文档
                // String html =
                // getHtmlString("http://blog.csdn.net/u010142437");
                // // 再使用第一种方式
                // doc = Jsoup.parse(html);
                // handler.sendEmptyMessage(0);

//                // 3.从sd卡文件中加载 HTML文档
//                File file = new File("/mnt/sdcard/test.html");
//                try {
//                    // 第三个参数是baseURL，当 HTML文档使用相对路径方式引用外部文件时，jsoup会自动为这些
//                    // URL加上baseURL这个前缀 。
//                    doc = Jsoup.parse(file, "UTF-8",
//                            "http://blog.csdn.net/");
//                    handler.sendEmptyMessage(0);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        }.start();

    }



    private String getHtmlString(String urlString) {
        try {
            URL url = new URL(urlString);
            URLConnection ucon = url.openConnection();
            InputStream instr = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(instr);
            ByteArrayBuffer bau = new ByteArrayBuffer(500);
            int current = 0;
            while ((current = bis.read()) != -1) {
                bau.append((byte) current);
            }
            return EncodingUtils.getString(bau.toByteArray(), "utf_8");
        } catch (Exception e) {
            return "";
        }
    }

    // 1.直接从字符串中输入HTML文档
    // String html =
    // "<html><head><title> 测试html的加载 </title></head>"
    // + "<body><p> 这是一篇使用jsoup来加载html的文章 </p></body></html>";
    // doc = Jsoup.parse(html);
    // handler.sendEmptyMessage(0);



    // 2.2 从 URL直接加载 HTML文档
    // try {
    // doc = Jsoup.connect("http://blog.csdn.net/u010142437")
    // .data("query", "Java") // 请求参数
    // .userAgent("I’m jsoup") // 设置 User-Agent
    // .cookie("auth", "token") // 设置 cookie
    // .timeout(5000) // 设置连接超时时间
    // .post(); // 使用 POST方法访问 URL
    // handler.sendEmptyMessage(0);
    // } catch (IOException e) {
    // e.printStackTrace();
    // }

    // 2.3从 URL直接加载 HTML文档
    // try {
    // doc = Jsoup.parse(new URL(
    // "http://blog.csdn.net/u010142437"), 5000);
    // handler.sendEmptyMessage(0);
    // } catch (MalformedURLException e) {
    // e.printStackTrace();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }

    // 2.4从 URL直接加载 HTML文档：先使用流读取html，然后使用Jsoup转换成Document文档
    // String html =
    // getHtmlString("http://blog.csdn.net/u010142437");
    // // 再使用第一种方式
    // doc = Jsoup.parse(html);
    // handler.sendEmptyMessage(0);
}
