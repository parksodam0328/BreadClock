package kr.hs.emirim.parksodam.breadclock;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kim on 2017-07-29.
 */

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter{

    List<BaseFragment> list;

    public FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        list=new ArrayList<>();
    }

    public void addFragment(BaseFragment fragment){
        list.add(fragment);
    }


    @Override
    public BaseFragment getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }


    public CharSequence getPageTitle(int position){
        return getItem(position).getTitle();
    }

}
