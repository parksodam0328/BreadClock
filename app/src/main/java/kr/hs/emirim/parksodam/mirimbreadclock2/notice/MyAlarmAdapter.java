package kr.hs.emirim.parksodam.mirimbreadclock2.notice;

/**
 * Created by kim on 2017-09-25.
 */


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.iamhabib.easy_preference.EasyPreference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kr.hs.emirim.parksodam.mirimbreadclock2.BarActivity;
import kr.hs.emirim.parksodam.mirimbreadclock2.R;
import kr.hs.emirim.parksodam.mirimbreadclock2.model.BookmarkBakery;
import noman.googleplaces.Place;

import static android.content.Context.MODE_PRIVATE;

public class MyAlarmAdapter extends BaseAdapter{
    public final String PREFERENCE = "kr.hs.emirim.parksodam.mirimbreadclock2.notice";
    /* 아이템을 세트로 담기 위한 어레이 */
    private ArrayList<BookmarkBakery> mItems = new ArrayList<>();
    private Context theContext;
    boolean check=true;
    private static String name;
    private static String location;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    final Place place = new Place();

    public MyAlarmAdapter(Context context, ArrayList<BookmarkBakery> mItems) {

        this.mItems = mItems;
        theContext = context;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public BookmarkBakery getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        /* 'listview_info_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_alarm_custom, parent, false);
        }
         /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        final BookmarkBakery bookmarkBakery = getItem(position);
        /* 'listview_info_custom'에 정의된 위젯에 대한 참조 획득 */
        ImageView iv_img = (ImageView) convertView.findViewById(R.id.iv_img);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_contents = (TextView) convertView.findViewById(R.id.tv_contents);
        final ImageView iv_image = (ImageView) convertView.findViewById(R.id.toggle_on);

        if(getPreferenceBoolean(bookmarkBakery.name,bookmarkBakery.checked)){
            DatabaseReference bookmarkRef = ((BarActivity) theContext).mDatabase.getInstance().getReference("users/" + mAuth.getCurrentUser().getUid() + "/alarms/" + bookmarkBakery.uid);
            bookmarkBakery.checked = true;
            iv_image.setImageResource(R.mipmap.toggle_on);
            bookmarkRef.setValue(bookmarkBakery);
            notifyDataSetChanged();
            Log.e("hello", "알람 상태 확인 : " + bookmarkBakery.checked);

                FirebaseMessaging.getInstance().subscribeToTopic(bookmarkBakery.uid);
                //FirebaseMessaging.getInstance().unsubscribeFromTopic();
                EasyPreference.with(theContext)
                        .addString(name, place.getName())
                        .save();

                EasyPreference.with(theContext)
                        .addString(location, place.getVicinity())
                        .save();


        }
        else{
            iv_image.setImageResource(R.mipmap.toggle_off);
            DatabaseReference bookmarkRef = ((BarActivity) theContext).mDatabase.getInstance().getReference("users/" + mAuth.getCurrentUser().getUid() + "/alarms/" + bookmarkBakery.uid);
            bookmarkBakery.checked = false;
            bookmarkRef.setValue(bookmarkBakery);
            FirebaseMessaging.getInstance().unsubscribeFromTopic(bookmarkBakery.uid);
           // Log.e(TAG, "알람 체크여부 : " + bookmarkRef.getKey().);
            //bookmarkRef.setValue(null);
            notifyDataSetChanged();
        }







        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        try {
            Picasso.with(context)
                    .load(bookmarkBakery.photo)
                    .placeholder(R.drawable.bakeryimg)
                    .error(R.drawable.bakeryimg)
                    .resize(50, 50)
                    .centerCrop()
                    .into(iv_img);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tv_name.setText(bookmarkBakery.name);
        tv_contents.setText(bookmarkBakery.vicinity);

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다) */
        //bookmarkBakery.checked = true;
     iv_image.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if (check) {
                 iv_image.setImageResource(R.mipmap.toggle_on);
                 bookmarkBakery.checked = true;
                 setPreference(bookmarkBakery.name, bookmarkBakery.checked);
                 check=false;
                 Log.e("TAAAAA", position+String.valueOf(getPreferenceBoolean(bookmarkBakery.name,bookmarkBakery.checked)));


             } else {
                 iv_image.setImageResource(R.mipmap.toggle_off);
                 bookmarkBakery.checked = false;
                 setPreference(bookmarkBakery.name, bookmarkBakery.checked);
                 check=true;
                 Log.e("TAAAAA",  position+String.valueOf(getPreferenceBoolean(bookmarkBakery.name,bookmarkBakery.checked)));


             }
         }
     });
        return convertView;

    }
    public void setPreference(String key, boolean value){
        SharedPreferences pref = theContext.getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
    // 데이터 불러오기 함수
    public boolean getPreferenceBoolean(String key, boolean value){
        SharedPreferences pref = theContext.getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        return pref.getBoolean(key, value);
    }
}
