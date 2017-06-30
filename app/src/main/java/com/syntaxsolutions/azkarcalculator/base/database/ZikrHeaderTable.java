package com.syntaxsolutions.azkarcalculator.base.database;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by suhail.kk on 29-09-2016.
 */
@Table(tableName = "ZikrHeaderTable", databaseName = ApplicationDatabase.NAME)
public class ZikrHeaderTable extends BaseModel {


    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getTarget() {
        return target;
    }

    public void setTarget(long target) {
        this.target = target;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @PrimaryKey(autoincrement = true)
    @Column
    private long id;

    @Column
    private long eventId;

    @Column
    private String name;
    @Column

    private String startDate;

    @Column
    private String enddate;

    @Column(defaultValue = "0")
    private long target;

    @Column(defaultValue = "0")
    private long count;


}
