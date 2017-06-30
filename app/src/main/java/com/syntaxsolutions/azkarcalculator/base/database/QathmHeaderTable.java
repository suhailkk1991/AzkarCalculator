package com.syntaxsolutions.azkarcalculator.base.database;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by suhail.kk on 29-09-2016.
 */
@Table(tableName = "QathmHeaderTable", databaseName = ApplicationDatabase.NAME)
public class QathmHeaderTable extends BaseModel {


    public long getAyathNo() {
        return ayathNo;
    }

    public void setAyathNo(long ayathNo) {
        this.ayathNo = ayathNo;
    }

    public String getSurathName() {
        return surathName;
    }

    public void setSurathName(String surathName) {
        this.surathName = surathName;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getQathmName() {
        return qathmName;
    }

    public void setQathmName(String qathmName) {
        this.qathmName = qathmName;
    }

    public long getEventid() {
        return eventid;
    }

    public void setEventid(long eventid) {
        this.eventid = eventid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column
    @PrimaryKey(autoincrement = true)
    private long id;
    @Column
    private long eventid;
    @Column
    private String qathmName;
    @Column
    String startDate;
    @Column
    String endDate;
    @Column
    String surathName;
    @Column
    long ayathNo;
}
