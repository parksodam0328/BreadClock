package kr.hs.emirim.parksodam.mirimbreadclock2.notice;

/**
 * Created by kim on 2017-09-25.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kr.hs.emirim.parksodam.mirimbreadclock2.R;
import kr.hs.emirim.parksodam.mirimbreadclock2.model.BookmarkBakery;

public class MyAlarmAdapter extends BaseAdapter{
    /* 아이템을 세트로 담기 위한 어레이 */
    private ArrayList<BookmarkBakery> mItems = new ArrayList<>();
    ArrayList<BookmarkBakery> seachedBakeris = new ArrayList<>();
    private FirebaseAuth mAuth;

    public MyAlarmAdapter(ArrayList<BookmarkBakery> mItems) {
        this.mItems = mItems;
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
    public boolean isChecked(int position) {
        return seachedBakeris.get(position).checked;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        /* 'listview_info_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_alarm_custom, parent, false);
        }

        /* 'listview_info_custom'에 정의된 위젯에 대한 참조 획득 */
        ImageView iv_img = (ImageView) convertView.findViewById(R.id.iv_img);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_contents = (TextView) convertView.findViewById(R.id.tv_contents);
        ImageView iv_image = (ImageView) convertView.findViewById(R.id.iv_image);


        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        final BookmarkBakery bookmarkBakery = getItem(position);

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

//<<<<<<< HEAD
//        try {
//            Picasso.with(context)
//                    .load(bookmarkBakery.photo)
//                    .placeholder(R.mipmap.toggle_on)
//                    .error(R.mipmap.toggle_on)
//                    .resize(50, 50)
//                    .centerCrop()
//                    .into(iv_image);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        iv_image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(bookmarkBakery.isChecked==true){
//                    iv_image.setImageResource(R.mipmap.toggle_on);
//                }
//                else {
//                    iv_image.setImageResource(R.mipmap.toggle_off);
//
//                }
//            }
//        });
//=======
//>>>>>>> origin/master




        tv_name.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Toast.makeText(context, "이름 선택", Toast.LENGTH_SHORT).show();
                                       }
                                   });

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다) */
//
//        iv_star.
//            @Override
//            public void onClick(View v) {
//                if(check==true) {
//                    iv_star.setImageResource(R.mipmap.star);
//                    check=false;
//                }
//                else {
//                    iv_star.setImageResource(R.mipmap.unstar);
//                    check=true;
//                }
//            }
//        });

        return convertView;
//
//    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
//    public void addItem(Drawable img, String name, String contents, Drawable star) {
//
//        MyAlarmItem mItem = new MyAlarmItem();
//
//        /* MyItem에 아이템을 setting한다. */
//        mItem.setIcon(img);
//        mItem.setName(name);
//        mItem.setContents(contents);
//        mItem.setStar(star);
//        /* mItems에 MyItem을 추가한다. */
//        mItems.add(mItem);
//
    }
}
