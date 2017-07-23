package kr.hs.emirim.parksodam.breadclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NotificationMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_main);
        Intent intent = new Intent(this,NotificationSomethings.class);
        startActivity(intent);
    }
}
