package kr.hs.emirim.parksodam.mirimbreadclock.Adapter;

/**
 * Created by parksodam on 2017-09-03.
 */

import android.graphics.drawable.Drawable;

public class MyItem {

    private Drawable icon;
    private String name;
    private String contents;
    private Drawable star;

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Drawable getStar() {return star;}

    public void setStar(Drawable star) {this.star = star;}
}