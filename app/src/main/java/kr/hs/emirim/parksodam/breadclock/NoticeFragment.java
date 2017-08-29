package kr.hs.emirim.parksodam.breadclock;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by kim on 2017-07-29.
 */

public class NoticeFragment extends BaseFragment {

    static final String[] LIST_MENU = {"LIST1", "LIST2", "LIST3"} ;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice, container, false);
        ListView listview;
        ListViewAdapter adapter;

        // Adapter 생성
        adapter = new ListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView)view.findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.breadclock_icon),
                "Box", "Account Box Black 36dp");
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.breadclock_icon),
                "Circle", "Account Circle Black 36dp");
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.breadclock_icon),
                "Ind", "Assignment Ind Black 36dp");

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);

                String titleStr = item.getTitle();
                String descStr = item.getDesc();
                Drawable iconDrawable = item.getIcon();
            }
        });


        return view;
    }



    @Override
    public String getTitle() {
        return "알람";
    }
}
