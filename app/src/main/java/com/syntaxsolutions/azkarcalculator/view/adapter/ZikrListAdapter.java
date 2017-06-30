package com.syntaxsolutions.azkarcalculator.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.syntaxsolutions.azkarcalculator.R;
import com.syntaxsolutions.azkarcalculator.base.database.ZikrHeaderTable;
import com.syntaxsolutions.azkarcalculator.constants.Constants;
import com.syntaxsolutions.azkarcalculator.dto.EventDto;
import com.syntaxsolutions.azkarcalculator.service.ZikrActivityService;
import com.syntaxsolutions.azkarcalculator.view.activity.ZikrCountActivity;
import com.syntaxsolutions.azkarcalculator.view.activity.ZikrActivity;

import java.util.List;

/**
 * Created by suhail.kk on 03-10-2016.
 */
public class ZikrListAdapter extends RecyclerView.Adapter<ZikrListAdapter.ViewHolder> {

    private List<ZikrHeaderTable> lstZikrHeader;
    private RecyclerView recyclerView;
    private Context context;


    public ZikrListAdapter(List<ZikrHeaderTable> lstZikrHeader, RecyclerView recyclerView, Context context) {
        this.lstZikrHeader = lstZikrHeader;
        this.recyclerView = recyclerView;
        this.context = context;

    }

    @Override
    public int getItemCount() {
        return lstZikrHeader.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder contactViewHolder, int i) {
        final ZikrHeaderTable item = lstZikrHeader.get(i);
        contactViewHolder.txtName.setText(item.getName());
        contactViewHolder.txtTotalCount.setText("" + item.getCount());
        contactViewHolder.txtTarget.setText("" + item.getTarget());
        contactViewHolder.imgStartCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ZikrCountActivity.class);
                EventDto eventDto = new EventDto();
                eventDto.setCurrentCount(Long.parseLong(contactViewHolder.txtTotalCount.getText().toString()));
                eventDto.setId(item.getId());
                eventDto.setFromScreen(Constants.ZIKR_SCREEN_NAME);
                eventDto.setName(item.getName());
                Gson gson = new Gson();
                String json = gson.toJson(eventDto);
                intent.putExtra("navigationData", json);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.zikr_activity_row, viewGroup, false);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int pos = recyclerView.getChildAdapterPosition(v);
                ((ZikrActivity) context).onClickCalled(pos);
            }
        });

        return new ViewHolder(itemView);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtName;
        protected TextView txtTarget;
        protected TextView txtTotalCount;
        protected ImageView imgStartCount;


        public ViewHolder(View view) {
            super(view);
            this.txtName = (TextView) view.findViewById(R.id.txtEventname);
            this.txtTotalCount = (TextView) view.findViewById(R.id.txtTotalCount);
            this.txtTarget = (TextView) view.findViewById(R.id.txtTarget);
            this.imgStartCount = (ImageView) view.findViewById(R.id.imgStartCount);

        }
    }

    public void remove(int position) {
        lstZikrHeader.get(position).delete();
        lstZikrHeader.remove(position);
        notifyItemRemoved(position);
    }

}
