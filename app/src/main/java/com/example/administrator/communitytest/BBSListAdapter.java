package com.example.administrator.communitytest;

import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2015/5/26.
 */
public class BBSListAdapter extends BaseAdapter {
    List<Content> contents;
    LayoutInflater inflater;
    int mode;
    public BBSListAdapter(LayoutInflater inflater, List<Content> contents, int listMode) {
        this.contents = contents == null ? new ArrayList<Content>() : contents;
        this.inflater = inflater;
        this.mode = listMode;
    }
    @Override
    public int getCount() {
        return contents.size();
    }
    public void addData(List<Content> contents){
        this.contents.addAll(contents);
    }
    @Override
    public Content getItem(int position) {
        return contents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewHolder holder;
        if(convertView == null){
            if(mode <3){
                convertView = inflater.inflate(R.layout.article_list_item, parent,false);
            }else{
                convertView = inflater.inflate(R.layout.rank_list_item, parent,false);
            }
            holder = new ListViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.article_item_title);
            holder.postTime = (TextView) convertView.findViewById(R.id.article_item_post_time);
            holder.comments = (TextView) convertView.findViewById(R.id.article_desc);
            convertView.setTag(holder);
        }
        holder = (ListViewHolder) convertView.getTag();
        Content art = getItem(position);
        holder.title.setText(art.getTitle());
        if(!TextUtils.isEmpty(art.description)){
            CharSequence text = null;
            try{
                text = Html.fromHtml(art.description);
                // catch java.io.IOException: Pushback buffer full
                //  at android.text.HtmlToSpannedConverter.convert(Html.java:438)
                //  at android.text.Html.fromHtml(Html.java:138)
                //  at android.text.Html.fromHtml(Html.java:101)
            }catch(Exception e){
                text = art.description;
            }
            holder.comments.setText(text);
        }
        else{
            holder.comments.setText("No description…");
        }

        if (mode < 3) {
            View tagHot = convertView.findViewById(R.id.item_tag);
//            if (ArticleApi.isRecommendedArticle(art)) {
//                tagHot.setVisibility(View.VISIBLE);
//                ((ImageView) tagHot).setImageResource(Theme.isNightMode() ? R.drawable.ic_recommended_dark : R.drawable.ic_recommended);
//            } else if (ArticleApi.isHotArticle(art)) {
//                tagHot.setVisibility(View.VISIBLE);
//                ((ImageView) tagHot).setImageResource(Theme.isNightMode() ? R.drawable.ic_whats_hot_dark : R.drawable.ic_whats_hot);
//            } else
                tagHot.setVisibility(View.GONE);
        } else {
            TextView rank = (TextView) convertView.findViewById(R.id.rank);
            if (position < 10) {
                rank.setVisibility(View.VISIBLE);
                rank.setText(String.valueOf(position + 1));
                int rankColorIndex = position > 3? 3:position;
                rank.setBackgroundColor(rankColors[rankColorIndex]);
            } else
                rank.setVisibility(View.GONE);
        }
        String tip = String.format(Locale.CHINA, " %s / %d条评论，%d人围观", art.releaseDate,art.comments,art.views);
//            holder.postTime.setText(AcApp.getPubDate(art.releaseDate));
        holder.postTime.setText(tip);
        return convertView;
    }
    int rankColors[] = {0xffcc0000,0xffff4444,0xffff8800,0xffffbb33};

}
 class ListViewHolder{
    TextView comments,
            postTime,
            channel,
            views,
            title;
    ImageView titleImage;

}