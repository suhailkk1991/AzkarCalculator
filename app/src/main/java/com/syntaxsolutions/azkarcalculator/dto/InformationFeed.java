package com.syntaxsolutions.azkarcalculator.dto;

/**
 * Created by Suhail k k on 25-04-2017.
 */

public class InformationFeed {
    private int feedId;
    private String imageName;
    private String head;

    public int getFeedId() {
        return feedId;
    }

    public void setFeedId(int feedId) {
        this.feedId = feedId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
