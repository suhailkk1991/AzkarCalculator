package com.syntaxsolutions.azkarcalculator.dto;

/**
 * Created by suhail.kk on 21-10-2016.
 */
public class EventDto {
    private long id;
    private String fromScreen;
    private long currentCount;
    private String name;
    int a=0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventDto() {
        id = -1;
        fromScreen = "";
        currentCount = 0;
        name = "";
    }

    public long getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(long currentCount) {
        this.currentCount = currentCount;
    }

    public String getFromScreen() {
        return fromScreen;
    }

    public void setFromScreen(String fromScreen) {
        this.fromScreen = fromScreen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
