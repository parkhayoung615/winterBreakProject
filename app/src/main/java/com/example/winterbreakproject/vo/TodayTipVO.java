package com.example.winterbreakproject.vo;
public class TodayTipVO {
    int id;
    String title;
    String contents;

    public TodayTipVO() {
    }

    public TodayTipVO(int id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}