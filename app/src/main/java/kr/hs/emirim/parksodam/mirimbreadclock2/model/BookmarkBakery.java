package kr.hs.emirim.parksodam.mirimbreadclock2.model;

/**
 * Created by kim on 2017-09-25.
 */

public class BookmarkBakery {
    public String uid;
    public String name;
    public String vicinity;
    public String photo;
//<<<<<<< HEAD
//    public boolean isChecked;
//
//    public BookmarkBakery() {
//    }
//
//    public BookmarkBakery(String uid, String name, String vicinity, String photo, boolean isChecked) {
//=======
    public boolean checked;
    public BookmarkBakery() {
    }

    public BookmarkBakery(String uid, String name, String vicinity, String photo, boolean checked) {
//>>>>>>> origin/master
        this.uid = uid;
        this.name = name;
        this.vicinity = vicinity;
        this.photo = photo;
//<<<<<<< HEAD
//        this.isChecked = isChecked;
//=======
        this.checked=checked;
    }

    public boolean isChecked(){
        return checked;
//>>>>>>> origin/master
    }
}
