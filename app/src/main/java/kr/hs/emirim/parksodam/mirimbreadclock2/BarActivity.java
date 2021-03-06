package kr.hs.emirim.parksodam.mirimbreadclock2;

import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import kr.hs.emirim.parksodam.mirimbreadclock2.Adapter.FragmentPagerAdapter;
import kr.hs.emirim.parksodam.mirimbreadclock2.map.MapFragment;
import kr.hs.emirim.parksodam.mirimbreadclock2.notice.NoticeFragment;
import kr.hs.emirim.parksodam.mirimbreadclock2.set.SetFragment;

public class BarActivity extends AppCompatActivity {

    public ViewPager vp;
    public TabLayout layout;
    public FirebaseDatabase mDatabase;
    private String TAG ="myreceiver : ";
    private Toolbar toolbar;
    private TextView myTitleText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        //툴바 설정
        //FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager());
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myTitleText = (TextView) findViewById(R.id.title);
        myTitleText.setText("지도");
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.getSupportActionBar().setTitle("지도");
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        vp = (ViewPager) findViewById(R.id.vp_main);
        setupViewPager(vp);
        layout = (TabLayout) findViewById(R.id.tl_main);
        layout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF")); // 밑줄색
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(layout));

        layout.setupWithViewPager(vp);

        for (int i = 0; i < layout.getTabCount(); i++) {
                if(i==0) {
                    layout.getTabAt(i).setText("지도").setIcon(R.drawable.map_on);
                    getSupportActionBar().setTitle("지도");
                }
            else if(i==1) {
                    layout.getTabAt(i).setText("알람").setIcon(R.drawable.alarm_off);
                    getSupportActionBar().setTitle("알람");
                }
            else if(i==2) {
                    layout.getTabAt(i).setText("도움말").setIcon(R.drawable.settings_off);
                    getSupportActionBar().setTitle("지도");
                }
        }
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    myTitleText.setText("지도");
                } else if (position == 1)
                {
                    myTitleText.setText("알람");
                }
                else if (position == 2)
                {
                    myTitleText.setText("도움말");
               }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getPosition()==0) {
                    myTitleText.setText("지도");
                    tab.setIcon(R.drawable.map_on);
                    layout.setTabTextColors(Color.parseColor("#666666"), Color.parseColor("#f38722"));
                }

                else if(tab.getPosition()==1) {
                    myTitleText.setText("알람");
                    tab.setIcon(R.drawable.alarm_on);
                    layout.setTabTextColors(Color.parseColor("#666666"), Color.parseColor("#f38722"));
                }
                else if(tab.getPosition()==2) {
                    myTitleText.setText("도움말");
                    tab.setIcon(R.drawable.settings_on);
                    layout.setTabTextColors(Color.parseColor("#666666"), Color.parseColor("#f38722"));
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if(tab.getPosition()==0) {
                    myTitleText.setText("지도");
                    tab.setIcon(R.drawable.map_off);
                    layout.setTabTextColors(Color.parseColor("#f38722"), Color.parseColor("#666666"));
                }
                else if(tab.getPosition()==1) {
                    myTitleText.setText("알람");
                    tab.setIcon(R.drawable.alarm_off);
                    layout.setTabTextColors(Color.parseColor("#f38722"), Color.parseColor("#666666"));
                }
                else if(tab.getPosition()==2) {
                    myTitleText.setText("도움말");
                    tab.setIcon(R.drawable.settings_off);
                    layout.setTabTextColors(Color.parseColor("#f38722"), Color.parseColor("#666666"));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition() != vp.getCurrentItem()) {
                    vp.setCurrentItem(tab.getPosition());
                }
            }
        });


        mDatabase = FirebaseDatabase.getInstance();
        //mDatabase.setPersistenceEnabled(true);

        IntentFilter filter = new IntentFilter("fcm");
        kr.hs.emirim.parksodam.mirimbreadclock2.notice.MyReceiver receiver = new kr.hs.emirim.parksodam.mirimbreadclock2.notice.MyReceiver();
        registerReceiver(receiver, filter);

    }

    private void setupViewPager(ViewPager vp) {
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MapFragment(), "지도");
        adapter.addFragment(new NoticeFragment(), "알람");
        adapter.addFragment(new SetFragment(), "도움말");
        vp.setAdapter(adapter);
    }
}
