package kr.hs.emirim.parksodam.breadclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kim on 2017-07-29.
 */

public class SetFragment extends BaseFragment implements View.OnClickListener {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set, container, false);
        return view;
    }

    @Override
    public String getTitle() {
        return "설정";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button3:
                Intent intent = new Intent(getActivity(), NoticeActivity.class);
                startActivity(intent);
                break;
            case R.id.button4:
                Intent intent2 = new Intent(getActivity(), NoticeActivity.class);
                startActivity(intent2);
                break;
            case R.id.button5:
                Intent intent3 = new Intent(getActivity(), NoticeActivity.class);
                startActivity(intent3);
                break;
            case R.id.button6:
                Intent intent4 = new Intent(getActivity(), NoticeActivity.class);
                startActivity(intent4);
                break;
            case R.id.button7:
                Intent intent5 = new Intent(getActivity(), NoticeActivity.class);
                startActivity(intent5);
                break;
            case R.id.button8:
                Intent intent6 = new Intent(getActivity(), NoticeActivity.class);
                startActivity(intent6);
                break;
            case R.id.button9:
                Intent intent7 = new Intent(getActivity(), NoticeActivity.class);
                startActivity(intent7);
                break;
        }
    }
}





