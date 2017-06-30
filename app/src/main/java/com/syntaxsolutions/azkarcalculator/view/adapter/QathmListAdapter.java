package com.syntaxsolutions.azkarcalculator.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.syntaxsolutions.azkarcalculator.R;
import com.syntaxsolutions.azkarcalculator.base.database.QathmHeaderTable;
import com.syntaxsolutions.azkarcalculator.service.QathmService;
import com.syntaxsolutions.azkarcalculator.view.activity.QathmActivity;

import java.util.List;

/**
 * Created by suhail.kk on 18-11-2016.
 */
public class QathmListAdapter extends RecyclerView.Adapter<QathmListAdapter.ViewHolder> {

    private List<QathmHeaderTable> lstQathmHeader;
    private RecyclerView recyclerView;
    private Context context;


    public QathmListAdapter(List<QathmHeaderTable> lstQathmHeader, RecyclerView recyclerView, Context context) {
        this.lstQathmHeader = lstQathmHeader;
        this.recyclerView = recyclerView;
        this.context = context;

    }

    @Override
    public int getItemCount() {
        return lstQathmHeader.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder contactViewHolder, final int i) {
        final QathmHeaderTable item = lstQathmHeader.get(i);
        contactViewHolder.txtName.setText(item.getQathmName());
        contactViewHolder.txtSurath.setText("" + item.getSurathName());
        contactViewHolder.txtAyath.setText("" + item.getAyathNo());
        contactViewHolder.layEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((QathmActivity) context).onClickForEdit(i);
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.qathm_activity_row, viewGroup, false);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int pos = recyclerView.getChildAdapterPosition(v);
                ((QathmActivity) context).onClickCalled(pos);
            }
        });

        return new ViewHolder(itemView);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtName;
        protected TextView txtSurath;
        protected TextView txtAyath;
        protected RelativeLayout layEdit;


        public ViewHolder(View view) {
            super(view);
            this.txtName = (TextView) view.findViewById(R.id.txtEventname);
            this.txtSurath = (TextView) view.findViewById(R.id.txtSurath);
            this.txtAyath = (TextView) view.findViewById(R.id.txtAyath);
            this.layEdit = (RelativeLayout) view.findViewById(R.id.layEdit);

        }
    }

    public void remove(int position) {
        lstQathmHeader.get(position).delete();
        lstQathmHeader.remove(position);
        notifyItemRemoved(position);
    }

    public boolean onItemMove(int fromPosition, int toPosition) {

        return true;
    }
}

