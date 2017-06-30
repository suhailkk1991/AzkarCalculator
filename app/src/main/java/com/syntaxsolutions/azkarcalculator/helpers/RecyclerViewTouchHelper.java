package com.syntaxsolutions.azkarcalculator.helpers;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.syntaxsolutions.azkarcalculator.view.adapter.QathmDetailsListAdapter;
import com.syntaxsolutions.azkarcalculator.view.adapter.QathmListAdapter;
import com.syntaxsolutions.azkarcalculator.view.adapter.SwalathDetailsListAdapter;
import com.syntaxsolutions.azkarcalculator.view.adapter.SwalathListAdapter;
import com.syntaxsolutions.azkarcalculator.view.adapter.ZikrDetailsListAdapter;
import com.syntaxsolutions.azkarcalculator.view.adapter.ZikrListAdapter;

/**
 * Created by suhail.kk on 24-11-2016.
 */
public class RecyclerViewTouchHelper extends ItemTouchHelper.SimpleCallback {
    private QathmDetailsListAdapter mQathmDetailsListAdapter;
    private QathmListAdapter mQathmListAdapter;
    private ZikrDetailsListAdapter mZikrDetailsListAdapter;
    private ZikrListAdapter mZikrListAdapter;
    private SwalathListAdapter mSwalathListAdapter;
    private SwalathDetailsListAdapter mSwalathDetailsListAdapter;
    private String mADAPTER_NAME = "";

    public RecyclerViewTouchHelper(QathmDetailsListAdapter mQathmDetailsListAdapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.mQathmDetailsListAdapter = mQathmDetailsListAdapter;
        mADAPTER_NAME = "qathmDetailsAdapter";
    }

    public RecyclerViewTouchHelper(QathmListAdapter mQathmListAdapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.mQathmListAdapter = mQathmListAdapter;
        mADAPTER_NAME = "qathmAdapter";
    }

    public RecyclerViewTouchHelper(ZikrListAdapter mZikrListAdapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.mZikrListAdapter = mZikrListAdapter;
        mADAPTER_NAME = "zikrAdapter";
    }

    public RecyclerViewTouchHelper(ZikrDetailsListAdapter mZikrDetailsListAdapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.mZikrDetailsListAdapter = mZikrDetailsListAdapter;
        mADAPTER_NAME = "zikrDetailsAdapter";
    }

    public RecyclerViewTouchHelper(SwalathListAdapter mSwalathListAdapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.mSwalathListAdapter = mSwalathListAdapter;
        mADAPTER_NAME = "swalathAdapter";
    }

    public RecyclerViewTouchHelper(SwalathDetailsListAdapter mSwalathDetailsListAdapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.mSwalathDetailsListAdapter = mSwalathDetailsListAdapter;
        mADAPTER_NAME = "swalathDetailsAdapter";
    }


    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //TODO: Not implemented here
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        switch (mADAPTER_NAME) {
            case "qathmAdapter":
                mQathmListAdapter.remove(viewHolder.getAdapterPosition());
                break;
            case "qathmDetailsAdapter":
                mQathmDetailsListAdapter.remove(viewHolder.getAdapterPosition());
                break;
            case "zikrAdapter":
                mZikrListAdapter.remove(viewHolder.getAdapterPosition());
                break;
            case "zikrDetailsAdapter":
                mZikrDetailsListAdapter.remove(viewHolder.getAdapterPosition());
                break;
            case "swalathAdapter":
                mSwalathListAdapter.remove(viewHolder.getAdapterPosition());
                break;
            case "swalathDetailsAdapter":
                mSwalathDetailsListAdapter.remove(viewHolder.getAdapterPosition());
                break;
        }

    }
}
