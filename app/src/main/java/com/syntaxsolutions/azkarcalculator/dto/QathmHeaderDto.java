package com.syntaxsolutions.azkarcalculator.dto;

/**
 * Created by suhail.kk on 18-11-2016.
 */
public class QathmHeaderDto {

    private long id;


    private String name;


    private String startDate;


    private String enddate;

    private String surath;

    private long ayath;

    public long getAyath() {
        return ayath;
    }

    public void setAyath(long ayath) {
        this.ayath = ayath;
    }

    public String getSurath() {
        return surath;
    }

    public void setSurath(String surath) {
        this.surath = surath;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
