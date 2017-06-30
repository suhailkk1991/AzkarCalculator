package com.syntaxsolutions.azkarcalculator.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syntaxsolutions.azkarcalculator.R;
import com.syntaxsolutions.azkarcalculator.base.database.ZikrDetailsTable;
import com.syntaxsolutions.azkarcalculator.service.ZikrDetailsService;

import java.util.List;

/**
 * Created by suhail.kk on 17-11-2016.
 */
public class ZikrDetailsListAdapter extends RecyclerView.Adapter<ZikrDetailsListAdapter.ViewHolder> {
    private List<ZikrDetailsTable> lstZikrDetail;
    private RecyclerView recyclerView;
    private Context context;


    public ZikrDetailsListAdapter(List<ZikrDetailsTable> lstZikrDetail, RecyclerView recyclerView, Context context) {
        this.lstZikrDetail = lstZikrDetail;
        this.recyclerView = recyclerView;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return lstZikrDetail.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder contactViewHolder, int i) {
        final ZikrDetailsTable item = lstZikrDetail.get(i);
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
        lstZikrDetail.get(position).delete();
        lstZikrDetail.remove(position);
        notifyItemRemoved(position);
    }

    public boolean onItemMove(int fromPosition, int toPosition) {

        return true;
    }
}

