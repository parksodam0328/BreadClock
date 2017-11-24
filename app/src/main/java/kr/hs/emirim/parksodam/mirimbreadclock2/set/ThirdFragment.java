package kr.hs.emirim.parksodam.mirimbreadclock2.set;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import kr.hs.emirim.parksodam.mirimbreadclock2.R;

/**
 * Created by parksodam on 2017-11-25.
 */

public class ThirdFragment extends Fragment {
    public ThirdFragment()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_third, container, false);
        return layout;
    }
}
