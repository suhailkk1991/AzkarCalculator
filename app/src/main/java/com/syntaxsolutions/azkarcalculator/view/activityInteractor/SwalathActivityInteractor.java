package com.syntaxsolutions.azkarcalculator.view.activityInteractor;

import android.app.Activity;
import android.content.Intent;

import com.google.gson.Gson;
import com.syntaxsolutions.azkarcalculator.base.database.SwalathHeaderTable;
import com.syntaxsolutions.azkarcalculator.dto.ZikrHeaderDto;
import com.syntaxsolutions.azkarcalculator.view.activity.SwalathDetailsActivity;

/**
 * Created by lenovo on 16-01-2017.
 */

public class SwalathActivityInteractor {
    private Activity activity;

    public SwalathActivityInteractor(Activity activity) {
        this.activity = activity;
    }

    public void navigateToListingScreen(final SwalathHeaderTable swalathHeaderTable) {
        Intent intent = new Intent(activity, SwalathDetailsActivity.class);
        ZikrHeaderDto zikrHeaderDto = new ZikrHeaderDto();
        zikrHeaderDto.setId(swalathHeaderTable.getId());
        zikrHeaderDto.setName(swalathHeaderTable.getName());
        zikrHeaderDto.setStartDate(swalathHeaderTable.getStartDate());
        zikrHeaderDto.setEnddate(swalathHeaderTable.getEnddate());
        zikrHeaderDto.setCount(swalathHeaderTable.getCount());
        zikrHeaderDto.setTarget(swalathHeaderTable.getTarget());
        Gson gson = new Gson();
        String json = gson.toJson(zikrHeaderDto);
        intent.putExtra("navigationData", json);
        activity.startActivity(intent);
    }


}
