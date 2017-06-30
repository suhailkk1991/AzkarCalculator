package com.syntaxsolutions.azkarcalculator.view.adapter;

/**
 * Created by suhail.kk on 24-11-2016.
 */
public interface RecyclerViewAnimator {
    void remove(int position);

    boolean onItemMove(int fromPosition, int toPosition);

}
