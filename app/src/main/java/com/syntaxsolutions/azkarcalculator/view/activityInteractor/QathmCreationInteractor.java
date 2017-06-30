package com.syntaxsolutions.azkarcalculator.view.activityInteractor;

import android.content.Context;

import com.syntaxsolutions.azkarcalculator.base.database.QathmHeaderTable;
import com.syntaxsolutions.azkarcalculator.constants.Constants;

/**
 * Created by lenovo on 10-01-2017.
 */

public class QathmCreationInteractor implements ActivityIntractable.QathmCreationInteractrable {
    private Context context;

    public QathmCreationInteractor(Context context) {
        this.context = context;
    }

    public StringBuilder save(final String name, final String startDate, final String endDate) {
        StringBuilder responseMessage = new StringBuilder();
        if (validate(name, startDate, endDate, responseMessage)) {
            proceedSave(name, startDate, endDate);
        }
        return responseMessage;

    }
    public  void proceedSave(final String name, final String startDate, final String endDate)
    {
        QathmHeaderTable qathmHeaderTable = new QathmHeaderTable();
        qathmHeaderTable.setEventid(Constants.QATHM_ID);
        qathmHeaderTable.setQathmName(name);
        qathmHeaderTable.setStartDate(startDate);
        qathmHeaderTable.setEndDate(endDate);
        qathmHeaderTable.setSurathName("Fathiha");
        qathmHeaderTable.setAyathNo(0);
        qathmHeaderTable.save();

    }

    public boolean validate(final String name, final String startDate, final String endDate, StringBuilder responseMessage) {
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
        responseMessage = responseMessage.append("Data updated Successfully");
        return true;
    }
}
