package com.example.administrator.communitytest;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/5/26.
 */
public class DataSource{
    public static  ArrayList<String> getList1 (){
        ArrayList<String> getList1 = new ArrayList<String>();
        for(int i =0 ;i < 10;i++){
            getList1.add("home"+i);
        }
        return getList1;
    }
    public static  ArrayList<String> getList2 (){
        ArrayList<String> getList2 = new ArrayList<String>();
        for(int i =0 ;i < 10;i++){
            getList2.add("hot"+i);
        }
        return getList2;
    }
    public static  ArrayList<String> getList3 (){
        ArrayList<String> getList3 = new ArrayList<String>();
        for(int i =0 ;i < 10;i++){
            getList3.add("more"+i);
        }
        return getList3;
    }

}
