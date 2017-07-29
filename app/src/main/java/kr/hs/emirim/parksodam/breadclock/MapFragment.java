package kr.hs.emirim.parksodam.breadclock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by kim on 2017-07-29.
 */

public class MapFragment extends BaseFragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        return view;
    }

    @Override
    public String getTitle() {
        return "지도";
    }
}
