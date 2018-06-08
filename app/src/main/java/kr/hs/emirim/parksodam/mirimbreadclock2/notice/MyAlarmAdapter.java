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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kr.hs.emirim.parksodam.mirimbreadclock2.BarActivity;
import kr.hs.emirim.parksodam.mirimbreadclock2.R;
import kr.hs.emirim.parksodam.mirimbreadclock2.model.BookmarkBakery;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

public class MyAlarmAdapter extends BaseAdapter{
    public final String PREFERENCE = "kr.hs.emirim.parksodam.mirimbreadclock2.notice";
    /* 아이템을 세트로 담기 위한 어레이 */
    private ArrayList<BookmarkBakery> mItems = new ArrayList<>();
    private Context theContext;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
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
            iv_image.setImageResource(R.mipmap.toggle_on);
            DatabaseReference bookmarkRef = ((BarActivity) theContext).mDatabase.getInstance().getReference("users/" + mAuth.getCurrentUser().getUid() + "/alarms/" + bookmarkBakery.uid);
            Log.e(TAG, "알람 삭제 : " + bookmarkBakery.uid);
            bookmarkRef.setValue(null);
            notifyDataSetChanged();
        }
        else{
            iv_image.setImageResource(R.mipmap.toggle_off);
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
             if (bookmarkBakery.checked) {

                 //iv_image.setImageResource(R.mipmap.toggle_on);
                 setPreference(bookmarkBakery.name, bookmarkBakery.checked);

                 Log.e("TAAAAA", position+String.valueOf(getPreferenceBoolean(bookmarkBakery.name,bookmarkBakery.checked)));
                 bookmarkBakery.checked = false;

             } else {
                 setPreference(bookmarkBakery.name, bookmarkBakery.checked);

                 Log.e("TAAAAA",  position+String.valueOf(getPreferenceBoolean(bookmarkBakery.name,bookmarkBakery.checked)));
                 bookmarkBakery.checked = true;

             }
             if(getPreferenceBoolean(bookmarkBakery.name,bookmarkBakery.checked)){
                 iv_image.setImageResource(R.mipmap.toggle_on);
                 notifyDataSetChanged();
             }
             else{
                 iv_image.setImageResource(R.mipmap.toggle_off);
                 notifyDataSetChanged();
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
