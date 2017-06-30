package com.syntaxsolutions.azkarcalculator.view.activityInteractor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.syntaxsolutions.azkarcalculator.constants.Constants;
import com.syntaxsolutions.azkarcalculator.dto.EventDto;
import com.syntaxsolutions.azkarcalculator.dto.ZikrHeaderDto;
import com.syntaxsolutions.azkarcalculator.view.activity.ZikrCountActivity;

/**
 * Created by lenovo on 14-01-2017.
 */

public class ZikrDetailsInteractor {
    private Activity activity;

    public ZikrDetailsInteractor(Activity activity) {
        this.activity = activity;
    }

    public void startZikrCounting(final ZikrHeaderDto zikrHeaderDto) {
        Intent intent = new Intent(activity, ZikrCountActivity.class);
        EventDto eventDto = new EventDto();
        Gson gson = new Gson();
        String json;

        eventDto.setCurrentCount(zikrHeaderDto.getCount());
        eventDto.setId(zikrHeaderDto.getId());
        eventDto.setFromScreen(Constants.ZIKR_SCREEN_NAME);
        eventDto.setName(zikrHeaderDto.getName());
        json = gson.toJson(eventDto);
        intent.putExtra("navigationData", json);
        activity.startActivity(intent);
    }

}
