package kr.hs.emirim.parksodam.breadclock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;


/**
 * Created by kim on 2017-07-29.
 */
public class MapFragment extends BaseFragment{

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        FrameLayout fl = (FrameLayout) view.findViewById(R.id.fl_content);

        com.google.android.gms.maps.MapFragment fragment = new com.google.android.gms.maps.MapFragment();

        SupportMapFragment map = SupportMapFragment.newInstance();
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.add(R.id.fl_content, map);
        ft.commit();

        return view;
    }

    public String getTitle() {
        return "지도";
    }

}
