package com.syntaxsolutions.azkarcalculator.base.database;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by suhail.kk on 29-09-2016.
 */
@Table(tableName = "EventHeader", databaseName = ApplicationDatabase.NAME)
public class EventHeaderTable extends BaseModel {
    @PrimaryKey
    @Column
    private long eventId;
    @Column
    private String eventName;

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
