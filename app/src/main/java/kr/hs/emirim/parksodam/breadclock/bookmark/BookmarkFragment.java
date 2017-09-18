package kr.hs.emirim.parksodam.breadclock.bookmark;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import kr.hs.emirim.parksodam.breadclock.BaseFragment;
import kr.hs.emirim.parksodam.breadclock.R;
import kr.hs.emirim.parksodam.breadclock.listview.MyAdapter;
import kr.hs.emirim.parksodam.breadclock.listview.MyItem;

/**
 * Created by kim on 2017-07-29.
 */


public class BookmarkFragment extends BaseFragment {
    private ListView mListView;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);

        mListView = (ListView)view.findViewById(R.id.listView);
        dataSetting();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                MyItem item = (MyItem) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), BookmarkInformation.class);
                String titleStr = item.getName();
                String descStr = item.getContents();
                Drawable iconDrawable = item.getIcon();

                startActivity(intent);
            }
        }) ;
        return view;
    }

    private void dataSetting() {

        MyAdapter mMyAdapter = new MyAdapter();


        for (int i = 0; i < 8; i++) {
            mMyAdapter.addItem(ContextCompat.getDrawable(getActivity(), R.mipmap.basicimg), "빵집 이름", "빵 이름", ContextCompat.getDrawable(getActivity(), R.drawable.star_select));
        }


        /* 리스트뷰에 어댑터 등록 */
        mListView.setAdapter(mMyAdapter);

    }



    @Override
    public String getTitle() {
        return "즐겨찾기";
    }
}
