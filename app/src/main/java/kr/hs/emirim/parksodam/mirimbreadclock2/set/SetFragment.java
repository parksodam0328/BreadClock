package kr.hs.emirim.parksodam.mirimbreadclock2.set;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import kr.hs.emirim.parksodam.mirimbreadclock2.BaseFragment;
import kr.hs.emirim.parksodam.mirimbreadclock2.R;

/**
 * Created by kim on 2017-07-29.
 */

public class SetFragment extends BaseFragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set, container, false);
        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button3:
                        Intent intent3 = new Intent(getActivity(), HowToUseActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.button4:
                        Intent intent4 = new Intent(Intent.ACTION_VIEW);
                        Uri url = Uri.parse("https://play.google.com/store/apps/details?id=kr.hs.emirim.parksodam.mirimbreadclock2");
                        intent4.setData(url);
                        startActivity(intent4);
                        break;
                }
            }
        };
        Button button3 = (Button) view.findViewById(R.id.button3);
        button3.setOnClickListener(onClickListener);
        Button button4 = (Button) view.findViewById(R.id.button4);
        button4.setOnClickListener(onClickListener);
        return view;
    }


    @Override
    public String getTitle() {
        return "설정";
    }
}





