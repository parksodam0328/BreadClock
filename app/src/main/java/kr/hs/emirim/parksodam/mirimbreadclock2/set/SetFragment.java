package kr.hs.emirim.parksodam.mirimbreadclock2.set;

import android.content.Intent;
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
                        Intent intent = new Intent(getActivity(), NoticeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.button4:
                        Intent intent2 = new Intent(getActivity(), VersionActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.button5:
                        Intent intent3 = new Intent(getActivity(), HowToUseActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.button6:
                        Intent intent4 = new Intent(getActivity(), FAQActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.button7:
                        Intent intent5 = new Intent(getActivity(), ContactUsActivity.class);
                        startActivity(intent5);
                        break;
                    case R.id.button8:
                        Intent intent6 = new Intent(getActivity(), IntroduceActivity.class);
                        startActivity(intent6);
                        break;
                }
            }
        };
        Button button1 = (Button) view.findViewById(R.id.button3);
        button1.setOnClickListener(onClickListener);
        Button button2 = (Button) view.findViewById(R.id.button4);
        button2.setOnClickListener(onClickListener);
        Button button3 = (Button) view.findViewById(R.id.button5);
        button3.setOnClickListener(onClickListener);
        Button button4 = (Button) view.findViewById(R.id.button6);
        button4.setOnClickListener(onClickListener);
        Button button5 = (Button) view.findViewById(R.id.button7);
        button5.setOnClickListener(onClickListener);
        Button button6 = (Button) view.findViewById(R.id.button8);
        button6.setOnClickListener(onClickListener);

        return view;
    }


    @Override
    public String getTitle() {
        return "설정";
    }
}





