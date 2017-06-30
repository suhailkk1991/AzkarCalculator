package com.syntaxsolutions.azkarcalculator.view.activityInteractor;

import android.content.Context;

import com.syntaxsolutions.azkarcalculator.base.database.SwalathHeaderTable;
import com.syntaxsolutions.azkarcalculator.constants.Constants;

/**
 * Created by lenovo on 09-01-2017.
 */

public class SwalathCreationInteractor implements ActivityIntractable {
    private Context context;



    public SwalathCreationInteractor(final Context context) {
        this.context = context;

    }

    @Override
    public StringBuilder save(final String name, final String startDate, final String endDate, final String target) {
        StringBuilder responseMessage=new StringBuilder();
        if (validate(name, startDate, endDate, target,responseMessage)) {
            proceedSave(name, startDate, endDate, target);
        }
        return responseMessage;

    }

    public boolean validate(final String name, final String startDate, final String endDate, final String target,StringBuilder responseMessage) {
        if (name == null && name.length() == 0) {
            responseMessage=responseMessage.append("Invalid swalath name");
            return false;
        }
        if (startDate == null || startDate.length() == 0) {
            responseMessage=responseMessage.append("Invalid start date");
            return false;
        }
        if (endDate == null || endDate.length() == 0) {
            responseMessage=responseMessage.append("Invalid end date");
            return false;
        }
        if (target == null || target.length() == 0) {
            responseMessage=responseMessage.append("Invalid target");
            return false;
        }
        responseMessage=responseMessage.append("Data updated Successfully");
        return true;
    }
    public  void proceedSave(final String name, final String startDate, final String endDate, final String target)
    {
        SwalathHeaderTable swalathHeaderTable = new SwalathHeaderTable();
        swalathHeaderTable.setEventId(Constants.SWALATH_ID);
        swalathHeaderTable.setName(name);
        swalathHeaderTable.setStartDate(startDate);
        swalathHeaderTable.setEnddate(endDate);
        swalathHeaderTable.setTarget(Long.parseLong(target));
        swalathHeaderTable.setCount(0);
        swalathHeaderTable.save();

    }

}
