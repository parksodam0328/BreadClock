package kr.hs.emirim.parksodam.breadclock;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BarActivity extends AppCompatActivity {

    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);

        TabLayout layout=(TabLayout) findViewById(R.id.tl_main);


        vp=(ViewPager) findViewById(R.id.vp_main);
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MapFragment());
        adapter.addFragment(new BookmarkFragment());
        adapter.addFragment(new NoticeFragment());
        adapter.addFragment(new SetFragment());
        vp.setAdapter(adapter);
        layout.setupWithViewPager(vp);
    }
}
