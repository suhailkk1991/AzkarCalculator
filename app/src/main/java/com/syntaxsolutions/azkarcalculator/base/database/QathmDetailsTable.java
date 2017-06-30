package com.syntaxsolutions.azkarcalculator.base.database;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by suhail.kk on 29-09-2016.
 */
@Table(tableName = "QathmDetailsTable", databaseName = ApplicationDatabase.NAME)
public class QathmDetailsTable extends BaseModel {
    public String getAyath() {
        return ayath;
    }

    public void setAyath(String ayath) {
        this.ayath = ayath;
    }

    public String getSurath() {
        return surath;
    }

    public void setSurath(String surath) {
        this.surath = surath;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getHeaderId() {
        return headerId;
    }

    public void setHeaderId(long headerId) {
        this.headerId = headerId;
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
    private long headerId;
    @Column
    private String date;
    @Column
    private String time;
    @Column
    private String surath;
    @Column
    private String ayath;
}
