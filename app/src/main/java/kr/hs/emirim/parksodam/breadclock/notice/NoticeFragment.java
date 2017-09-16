package kr.hs.emirim.parksodam.breadclock.notice;

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
import kr.hs.emirim.parksodam.breadclock.listview.MyAdapter;
import kr.hs.emirim.parksodam.breadclock.listview.MyItem;
import kr.hs.emirim.parksodam.breadclock.R;


/**
 * Created by kim on 2017-07-29.
 */


public class NoticeFragment extends BaseFragment {
    private ListView mListView;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice, container, false);

        mListView = (ListView)view.findViewById(R.id.listView);
        dataSetting();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                MyItem item = (MyItem) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), BreadInformation.class);
                String titleStr = item.getName();
                String descStr = item.getContents();
                Drawable iconDrawable = item.getIcon();

                startActivity(intent);
            }
        }) ;
        return view;
    }

    private void dataSetting(){

        MyAdapter mMyAdapter = new MyAdapter();


        for (int i=0; i<10; i++) {
            mMyAdapter.addItem(ContextCompat.getDrawable(getActivity(), R.mipmap.ic_launcher), "빵집 이름", "빵 이름" ,ContextCompat.getDrawable(getActivity(),R.drawable.toggle_off));

        }

        /* 리스트뷰에 어댑터 등록 */
        mListView.setAdapter(mMyAdapter);
    }


    @Override
    public String getTitle() {
        return "알람";
    }
}
