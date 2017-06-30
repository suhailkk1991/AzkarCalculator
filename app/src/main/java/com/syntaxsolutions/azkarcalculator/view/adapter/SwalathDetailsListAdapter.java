package com.syntaxsolutions.azkarcalculator.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syntaxsolutions.azkarcalculator.R;
import com.syntaxsolutions.azkarcalculator.base.database.SwalathDetailsTable;
import com.syntaxsolutions.azkarcalculator.service.SwalathDetailsService;

import java.util.List;

/**
 * Created by suhail.kk on 17-11-2016.
 */
public class SwalathDetailsListAdapter extends RecyclerView.Adapter<SwalathDetailsListAdapter.ViewHolder> {
    private List<SwalathDetailsTable> lstSwalathDetail;
    private RecyclerView recyclerView;
    private Context context;


    public SwalathDetailsListAdapter(List<SwalathDetailsTable> lstSwalathDetail, RecyclerView recyclerView, Context context) {
        this.lstSwalathDetail = lstSwalathDetail;
        this.recyclerView = recyclerView;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return lstSwalathDetail.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder contactViewHolder, int i) {
        final SwalathDetailsTable item = lstSwalathDetail.get(i);
        contactViewHolder.txtdate.setText(item.getDate());
        contactViewHolder.txtTime.setText("" + item.getTime());
        contactViewHolder.txtCount.setText("" + item.getCount());
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
        protected TextView txtCount;


        public ViewHolder(View view) {
            super(view);
            this.txtdate = (TextView) view.findViewById(R.id.txtCountDate);
            this.txtTime = (TextView) view.findViewById(R.id.txtCountTime);
            this.txtCount = (TextView) view.findViewById(R.id.txtCount);

        }
    }

    public void remove(int position) {
        lstSwalathDetail.get(position).delete();
        lstSwalathDetail.remove(position);
        notifyItemRemoved(position);
    }

    public boolean onItemMove(int fromPosition, int toPosition) {

        return true;
    }
}

