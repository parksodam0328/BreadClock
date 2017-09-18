package kr.hs.emirim.parksodam.breadclock;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import kr.hs.emirim.parksodam.breadclock.Adapter.FragmentPagerAdapter;
import kr.hs.emirim.parksodam.breadclock.bookmark.BookmarkFragment;
import kr.hs.emirim.parksodam.breadclock.map.MapFragment;
import kr.hs.emirim.parksodam.breadclock.notice.NoticeFragment;
import kr.hs.emirim.parksodam.breadclock.set.SetFragment;

public class BarActivity extends AppCompatActivity {

    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        //초기화
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);

        //툴바 설정
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.
        TabLayout layout = (TabLayout) findViewById(R.id.tl_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        vp = (ViewPager) findViewById(R.id.vp_main);
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MapFragment());
        adapter.addFragment(new BookmarkFragment());
        adapter.addFragment(new NoticeFragment());
        adapter.addFragment(new SetFragment());
        vp.setAdapter(adapter);
        layout.setupWithViewPager(vp);
    }
}
