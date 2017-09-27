package kr.hs.emirim.parksodam.mirimbreadclock;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.FirebaseDatabase;

import kr.hs.emirim.parksodam.mirimbreadclock.Adapter.FragmentPagerAdapter;
import kr.hs.emirim.parksodam.mirimbreadclock.bookmark.BookmarkFragment;
import kr.hs.emirim.parksodam.mirimbreadclock.map.MapFragment;
import kr.hs.emirim.parksodam.mirimbreadclock.notice.NoticeFragment;
import kr.hs.emirim.parksodam.mirimbreadclock.set.SetFragment;

public class BarActivity extends AppCompatActivity {

    ViewPager vp;
    public FirebaseDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(kr.hs.emirim.parksodam.mirimbreadclock.R.layout.activity_bar);
        //초기화
        Toolbar toolbar = (Toolbar) findViewById(kr.hs.emirim.parksodam.mirimbreadclock.R.id.my_toolbar);

        //툴바 설정
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.
        TabLayout layout = (TabLayout) findViewById(kr.hs.emirim.parksodam.mirimbreadclock.R.id.tl_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        vp = (ViewPager) findViewById(kr.hs.emirim.parksodam.mirimbreadclock.R.id.vp_main);
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MapFragment());
        adapter.addFragment(new BookmarkFragment());
        adapter.addFragment(new NoticeFragment());
        adapter.addFragment(new SetFragment());
        vp.setAdapter(adapter);
        layout.setupWithViewPager(vp);


        mDatabase = FirebaseDatabase.getInstance();
        //mDatabase.setPersistenceEnabled(true);

    }
}
