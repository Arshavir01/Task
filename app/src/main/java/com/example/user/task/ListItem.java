package com.example.user.task;

/**
 * Created by user on 12.11.2017.
 */

public class ListItem {

    private String head;
    private String desc;
    private String imageURL;
    private String rate;

    public ListItem(String head, String desc, String imageURL, String rate) {
        this.head = head;
        this.desc = desc;
        this.imageURL = imageURL;
        this.rate = rate;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getRate() {
        return rate;
    }
}
