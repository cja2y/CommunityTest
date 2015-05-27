package com.example.administrator.communitytest;

/**
 * Created by Administrator on 2015/5/27.
 */
public class BBSArticleInfo {
    private boolean isBoutique;
    private boolean isHot;
    private boolean isTop;
    private String articleTitle;
    private String articleContent;
    private String articleTip;
    private int articleIndex;
    public  void  setBoutique(boolean b){
            this.isBoutique = b;
    }
    public void setHot(boolean b){
            this.isHot = b;
    }
    public void setTop(boolean b){
            this.isTop = b;
    }
    public void setArticleContent(String content){
            this.articleContent = content;
    }
    public void setArticleTitle(String title){
            this.articleTitle = title;
    }
    public void setArticleTip(String tip){
            this.articleTitle = tip;
    }
    public void setArticleIndex(int index){
        this.articleIndex = index;
    }
    public boolean getBoutique(){
        return this.isBoutique;
    }
    public boolean getHot(){
        return this.isHot;
    }
    public boolean getTop(){
        return this.isTop;
    }
    public String getArticleTitle(){
        return this.articleTitle;
    }
    public String getArticleContent(){
        return this.articleContent;
    }
    public String getArticleTip(){
        return this.articleTip;
    }
    public int getArticleIndex(){
        return this.articleIndex;
    }
}
