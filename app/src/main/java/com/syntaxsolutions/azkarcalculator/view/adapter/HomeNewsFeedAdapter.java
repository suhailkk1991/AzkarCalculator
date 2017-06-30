package com.syntaxsolutions.azkarcalculator.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.syntaxsolutions.azkarcalculator.R;
import com.syntaxsolutions.azkarcalculator.base.database.QathmDetailsTable;
import com.syntaxsolutions.azkarcalculator.dto.InformationFeed;
import com.syntaxsolutions.azkarcalculator.view.activity.HomeActivity;

import java.util.List;

/**
 * Created by Suhail k k on 26-04-2017.
 */

public class HomeNewsFeedAdapter extends RecyclerView.Adapter<HomeNewsFeedAdapter.ViewHolder> {
    private List<InformationFeed> lstInfirmationFeeds;
    private RecyclerView recyclerView;
    private Context context;
    int lastPosition = -1;

    public HomeNewsFeedAdapter(List<InformationFeed> lstInformationFeeds, RecyclerView recyclerView, Context context) {
        this.lstInfirmationFeeds = lstInformationFeeds;
        this.recyclerView = recyclerView;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return lstInfirmationFeeds.size();
    }

    @Override
    public void onBindViewHolder(final HomeNewsFeedAdapter.ViewHolder contactViewHolder, int position) {
        final InformationFeed item = lstInfirmationFeeds.get(position);
        contactViewHolder.txtTitle.setText(item.getHead());
        ((HomeActivity) (context)).downloadImage(item.getImageName(), contactViewHolder.infirmationImage, contactViewHolder.progressbar);

    }

    @Override
    public HomeNewsFeedAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.zikr_home_row, viewGroup, false);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int pos = recyclerView.getChildAdapterPosition(v);
                ((HomeActivity) context).onClickCalled(pos);
            }
        });

        return new HomeNewsFeedAdapter.ViewHolder(itemView);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtTitle;
        protected ImageView infirmationImage;
        protected ProgressBar progressbar;


        public ViewHolder(View view) {
            super(view);
            this.txtTitle = (TextView) view.findViewById(R.id.txtItem);
            this.infirmationImage = (ImageView) view.findViewById(R.id.image);
            this.progressbar = (ProgressBar) view.findViewById(R.id.progressbar);

        }
    }


    public boolean onItemMove(int fromPosition, int toPosition) {
        return true;
    }
}

