package com.syntaxsolutions.azkarcalculator.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.syntaxsolutions.azkarcalculator.R;
import com.syntaxsolutions.azkarcalculator.base.database.QathmDetailsTable;
import com.syntaxsolutions.azkarcalculator.service.QathmDetailsService;
import com.syntaxsolutions.azkarcalculator.service.QathmService;

import java.util.List;

/**
 * Created by suhail.kk on 18-11-2016.
 */
public class QathmDetailsListAdapter extends RecyclerView.Adapter<QathmDetailsListAdapter.ViewHolder> {
    private List<QathmDetailsTable> lstQathmDetails;
    private RecyclerView recyclerView;
    private Context context;
    int lastPosition = -1;

    public QathmDetailsListAdapter(List<QathmDetailsTable> lstQathmDetails, RecyclerView recyclerView, Context context) {
        this.lstQathmDetails = lstQathmDetails;
        this.recyclerView = recyclerView;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return lstQathmDetails.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder contactViewHolder, int position) {
        final QathmDetailsTable item = lstQathmDetails.get(position);
        contactViewHolder.txtdate.setText(item.getDate());
        contactViewHolder.txtTime.setText("" + item.getTime());
        contactViewHolder.txtAyathAndSurath.setText("" + item.getSurath() + ":" + item.getAyath());
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context,
                    R.anim.up_from_bottom);
            contactViewHolder.itemView.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.zikr_details_activity_row, viewGroup, false);
        return new ViewHolder(itemView);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtdate;
        protected TextView txtTime;
        protected TextView txtAyathAndSurath;


        public ViewHolder(View view) {
            super(view);
            this.txtdate = (TextView) view.findViewById(R.id.txtCountDate);
            this.txtTime = (TextView) view.findViewById(R.id.txtCountTime);
            this.txtAyathAndSurath = (TextView) view.findViewById(R.id.txtCount);

        }
    }


    public void remove(int position) {
        lstQathmDetails.get(position).delete();
        lstQathmDetails.remove(position);
        notifyItemRemoved(position);
    }

    public boolean onItemMove(int fromPosition, int toPosition) {
        return true;
    }
}

