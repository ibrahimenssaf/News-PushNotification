package com.example.user.gridl_cardview.HospitalNews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.gridl_cardview.Info;
import com.example.user.gridl_cardview.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by User on 04/03/2018.
 */

public class AdapterHos_News extends RecyclerView.Adapter<AdapterHos_News.News>{

    ArrayList<ProductNews> listNews = new ArrayList<>();
    Context context;

    AdapterHos_News(ArrayList<ProductNews> listNews, Context context){
        this.listNews = listNews;
        this.context = context;
    }

    @Override
    public AdapterHos_News.News onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.hospital_news, parent, false);
        return new News(v);
    }

    @Override
    public void onBindViewHolder(AdapterHos_News.News holder, int position) {

        String url = "http://192.168.0.7/department/hospitalnews/";
        String photo = url + listNews.get(position).imagenews.replace(" ","%20");
        holder.nameNews.setText(listNews.get(position).namenews);
        holder.newscomment.setText(listNews.get(position).news);
        Picasso.with(context).load(photo).into(holder.iv_news);
    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }


    class News extends RecyclerView.ViewHolder{

        ImageView iv_news;
        TextView nameNews, newscomment;
        public News(View itemView) {
            super(itemView);

            iv_news = (ImageView)itemView.findViewById(R.id.imageView_news);
            nameNews = (TextView)itemView.findViewById(R.id.tv_writernews);
            newscomment = (TextView)itemView.findViewById(R.id.tv_news);
        }
    }
}
