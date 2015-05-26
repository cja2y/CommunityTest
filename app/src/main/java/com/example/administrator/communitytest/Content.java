package com.example.administrator.communitytest;

/**
 * Created by Administrator on 2015/5/26.
 */
public class Content {

    public String title;
    public String titleImg;
    public String description;
    public String username;
    public long userId;
    public long views;
    public int aid;
    public int channelId;
    public int comments;
    public long releaseDate;
    public int stows;
    public boolean isRecommend;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getComments() {
        return comments;
    }


    public void setComments(int comments) {
        this.comments = comments;
    }



}
