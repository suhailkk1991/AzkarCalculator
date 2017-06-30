package com.syntaxsolutions.azkarcalculator.view.activityInteractor;

import android.content.Context;

import com.syntaxsolutions.azkarcalculator.base.database.ZikrHeaderTable;
import com.syntaxsolutions.azkarcalculator.constants.Constants;

/**
 * Created by lenovo on 10-01-2017.
 */

public class ZikrCreationInteractor implements ActivityIntractable {
    private Context context;


    public ZikrCreationInteractor(final Context context) {
        this.context = context;

    }

    public StringBuilder save(final String name, final String startDate, final String endDate, final String target) {
        StringBuilder responseMessage = new StringBuilder();
        if (validate(name, startDate, endDate, target, responseMessage)) {
            proceedSave(name, startDate, endDate, target);
        }
        return responseMessage;

    }

    public boolean validate(final String name, final String startDate, final String endDate, final String target, StringBuilder responseMessage) {
        if (name == null && name.length() == 0) {
            responseMessage = responseMessage.append("Invalid swalath name");
            return false;
        }
        if (startDate == null || startDate.length() == 0) {
            responseMessage = responseMessage.append("Invalid start date");
            return false;
        }
        if (endDate == null || endDate.length() == 0) {
            responseMessage = responseMessage.append("Invalid end date");
            return false;
        }
        if (target == null || target.length() == 0) {
            responseMessage = responseMessage.append("Invalid target");
            return false;
        }
        responseMessage = responseMessage.append("Data updated Successfully");
        return true;
    }

    public void proceedSave(final String name, final String startDate, final String endDate, final String target) {
        ZikrHeaderTable zikrHeaderTable = new ZikrHeaderTable();
        zikrHeaderTable.setEventId(Constants.ZIKR_ID);
        zikrHeaderTable.setName(name);
        zikrHeaderTable.setStartDate(startDate);
        zikrHeaderTable.setEnddate(endDate);
        zikrHeaderTable.setTarget(Long.parseLong(target));
        zikrHeaderTable.setCount(0);
        zikrHeaderTable.save();

    }

}


