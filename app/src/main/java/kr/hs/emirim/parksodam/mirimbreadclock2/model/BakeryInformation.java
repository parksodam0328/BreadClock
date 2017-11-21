package kr.hs.emirim.parksodam.mirimbreadclock2.model;

/**
 * Created by parksodam on 2017-11-20.
 */

public class BakeryInformation {
    public String bakeryName;
    public String description;
    public String address;
    public long close;
    public long open;
    public String picture;
    public String phone_number;
    public String breadname;
    public String img;
    public String name;

    public BakeryInformation() {
    }

    public BakeryInformation(String bakeryName, String description, String address, long close, long open, String picture, String phone_number, String breadname, String img, String name) {
        this.bakeryName = bakeryName;
        this.description = description;
        this.address = address;
        this.close = close;
        this.open = open;
        this.picture = picture;
        this.phone_number = phone_number;
        this.breadname = breadname;
        this.img = img;
        this.name = name;
    }

    public String getBakeryName() {
        return bakeryName;
    }

    public void setBakeryName(String bakeryName) {
        this.bakeryName = bakeryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getClose() {
        return close;
    }

    public void setClose(long close) {
        this.close = close;
    }

    public long getOpen() {
        return open;
    }

    public void setOpen(long open) {
        this.open = open;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getBreadname() {
        return breadname;
    }

    public void setBreadname(String breadname) {
        this.breadname = breadname;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}