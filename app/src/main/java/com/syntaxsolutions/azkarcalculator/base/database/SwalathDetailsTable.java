package com.syntaxsolutions.azkarcalculator.base.database;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by suhail.kk on 29-09-2016.
 */
@Table(tableName = "SwalathDetailsTable", databaseName = ApplicationDatabase.NAME)
public class SwalathDetailsTable extends BaseModel {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getHeaderId() {
        return headerId;
    }

    public void setHeaderId(long headerId) {
        this.headerId = headerId;
    }

    @PrimaryKey(autoincrement = true)
    @Column
    private long id;
    @Column
    private long headerId;
    @Column
    private String date;
    @Column
    private String time;
    @Column
    private long count;
}
