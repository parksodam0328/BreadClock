package kr.hs.emirim.parksodam.mirimbreadclock2.Adapter;

import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import kr.hs.emirim.parksodam.mirimbreadclock2.BaseFragment;

/**
 * Created by kim on 2017-07-29.
 */

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    List<BaseFragment> list;
    List<String> fragTitle;

    public FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        list = new ArrayList<>();
        fragTitle= new ArrayList<>();
    }

    public void addFragment(BaseFragment fragment, String title) {
        list.add(fragment); fragTitle.add(title);
    }


    @Override
    public BaseFragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }


    public CharSequence getPageTitle(int position) {
        return null;
    }

}
