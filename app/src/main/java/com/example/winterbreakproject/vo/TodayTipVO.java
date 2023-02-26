package com.example.winterbreakproject.vo;
public class TodayTipVO {
    int id;
    String title;
    String contents;
    String image;
    public TodayTipVO() {
    }
    public TodayTipVO(int id, String title, String contents, String image) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}