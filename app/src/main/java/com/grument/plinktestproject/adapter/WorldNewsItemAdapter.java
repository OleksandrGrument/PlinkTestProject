package com.grument.plinktestproject.adapter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.grument.plinktestproject.R;
import com.grument.plinktestproject.model.News;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorldNewsItemAdapter extends RecyclerView.Adapter<WorldNewsItemAdapter.ViewHolder> {


    public WorldNewsItemAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    private Context context;

    private List<News> newsList;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_rv_world_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        News news = newsList.get(position);

        Glide.with(context)
                .load(news.getThumbnailUrl())
                .into(holder.thumbnailImageView);

        holder.titleTextView.setText(news.getTitle());

        PopupMenu popup = new PopupMenu(context, holder.openMenuImageView);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_item_world_news, popup.getMenu());
        popup.setOnMenuItemClickListener(new WorldNewsMenuItemClickListener(news.getNewsArticleUrl()));

        holder.openMenuImageView.setOnClickListener(v -> popup.show());

    }


    public void notifyAndShow() {
        notifyItemInserted(getItemCount());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_thumbnail)
        ImageView thumbnailImageView;

        @BindView(R.id.tv_title)
        TextView titleTextView;

        @BindView(R.id.iv_open_menu)
        ImageView openMenuImageView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private class WorldNewsMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        private String newsArticleUrl;

        private WorldNewsMenuItemClickListener(String newsArticleUrl) {
            this.newsArticleUrl = newsArticleUrl;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_jump_to_source:
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(newsArticleUrl)));
                    return true;
                default:
            }
            return false;
        }
    }

}
