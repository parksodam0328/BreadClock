package kr.hs.emirim.parksodam.breadclock.notice;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import kr.hs.emirim.parksodam.breadclock.R;

public final class AlarmLandingPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //초기화
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);

        //툴바 설정
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.
        ImageView backimg = (ImageView)findViewById(R.id.back_icon);
        if(backimg!=null) {
            backimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        else{
            Toast toast = Toast.makeText(this, "잘못클릭하셨습니다.", Toast.LENGTH_SHORT);
            toast.show();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_landing_page);

    }

}
