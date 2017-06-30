package com.syntaxsolutions.azkarcalculator.view.activityInteractor;

import android.app.Activity;
import android.content.Intent;

import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Update;
import com.raizlabs.android.dbflow.sql.language.Where;
import com.syntaxsolutions.azkarcalculator.base.database.SwalathDetailsTable;
import com.syntaxsolutions.azkarcalculator.base.database.SwalathHeaderTable;
import com.syntaxsolutions.azkarcalculator.base.database.SwalathHeaderTable$Table;
import com.syntaxsolutions.azkarcalculator.base.database.ZikrDetailsTable;
import com.syntaxsolutions.azkarcalculator.base.database.ZikrHeaderTable;
import com.syntaxsolutions.azkarcalculator.base.database.ZikrHeaderTable$Table;
import com.syntaxsolutions.azkarcalculator.view.activity.SwalathActivity;
import com.syntaxsolutions.azkarcalculator.view.activity.ZikrActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lenovo on 17-01-2017.
 */

public class ZikrCountInteractor {
    Activity activity;

    public ZikrCountInteractor(Activity activity) {
        this.activity = activity;
    }

    public void saveZikrDetails(long zikrId, long totalZikrInTheCurrentContext) {
        ZikrDetailsTable zikrDetailsTable = new ZikrDetailsTable();
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
        Date currentDate = new Date();
        Calendar calander = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyy");
        zikrDetailsTable.setHeaderId(zikrId);
        zikrDetailsTable.setCount(totalZikrInTheCurrentContext);
        zikrDetailsTable.setDate(simpleDateFormat.format(currentDate));
        zikrDetailsTable.setTime(simpleTimeFormat.format(calander.getTime()));
        zikrDetailsTable.save();
    }

    public void updateZikrHeaderTable(long zikrId, Long count) {
        ZikrHeaderTable zikrHeaderTable = new ZikrHeaderTable();
        zikrHeaderTable.setCount(count);
        Where update = new Update<>(ZikrHeaderTable.class).
                set(Condition.column(ZikrHeaderTable$Table.COUNT).eq(zikrHeaderTable.getCount()))
                .where(Condition.column(ZikrHeaderTable$Table.ID).is(zikrId));
        update.queryClose();
    }

    public void proceedNavigationToZikr() {
        Intent intent = new Intent(activity, ZikrActivity.class);
        activity.startActivity(intent);
    }

    public void saveSwalathDetails(long eventId, long totalZikrInTheCurrentContext) {
        SwalathDetailsTable swalathDetailsTable = new SwalathDetailsTable();
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
        Date currentDate = new Date();
        Calendar calander = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyy");
        swalathDetailsTable.setHeaderId(eventId);
        swalathDetailsTable.setCount(totalZikrInTheCurrentContext);
        swalathDetailsTable.setDate(simpleDateFormat.format(currentDate));
        swalathDetailsTable.setTime(simpleTimeFormat.format(calander.getTime()));
        swalathDetailsTable.save();
    }

    public void updateSwalathHeaderTable(long eventId, long counter) {
        SwalathHeaderTable swalathHeaderTable = new SwalathHeaderTable();
        swalathHeaderTable.setCount(counter);
        Where update = new Update<>(SwalathHeaderTable.class).
                set(Condition.column(SwalathHeaderTable$Table.COUNT).eq(swalathHeaderTable.getCount()))
                .where(Condition.column(SwalathHeaderTable$Table.ID).is(eventId));
        update.queryClose();
    }
    public void proceedNavigationToSwalath() {
        Intent intent = new Intent(activity, SwalathActivity.class);
        activity.startActivity(intent);
    }

}
