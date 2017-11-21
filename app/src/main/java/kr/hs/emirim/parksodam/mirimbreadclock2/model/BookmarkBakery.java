package kr.hs.emirim.parksodam.mirimbreadclock2.model;

/**
 * Created by kim on 2017-09-25.
 */

public class BookmarkBakery {
    public String uid;
    public String name;
    public String vicinity;
    public String photo;
    public boolean checked;
    public BookmarkBakery() {
    }

    public BookmarkBakery(String uid, String name, String vicinity, String photo, boolean checked) {
        this.uid = uid;
        this.name = name;
        this.vicinity = vicinity;
        this.photo = photo;
        this.checked=checked;
    }

    public boolean isChecked(){
        return checked;
    }
}
