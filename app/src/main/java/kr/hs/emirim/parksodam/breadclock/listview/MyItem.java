package kr.hs.emirim.parksodam.breadclock.listview;

/**
 * Created by parksodam on 2017-09-03.
 */

import android.graphics.drawable.Drawable;

public class MyItem {

    private Drawable icon;
    private String name;
    private String contents;
    private Drawable image;

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name; }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Drawable getImage() { return image; }

    public void setImage(Drawable image) { this.image = image;}
}