package kr.hs.emirim.parksodam.mirimbreadclock2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import kr.hs.emirim.parksodam.mirimbreadclock2.model.BakeryInformation;

/**
 * Created by kim on 2017-11-16.
 */

public class BakeryInfoActivity extends AppCompatActivity {

    private TextView breadname1, breadname2, breadname3, breadname4, bakeryName, tel, location, time, intro;
    private ImageView bread1, bread2, bread3, bread4, telIcon, locationIcon, timeIcon, introIcon, picture;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseChild1, mDatabaseChild2, mDatabaseChild3, mDatabaseChild4;
    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_bakery);
        //툴바 설정
        //FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager());
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.getSupportActionBar().setTitle("지도");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ImageView backimg = (ImageView) findViewById(R.id.back_icon);
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Intent intent = getIntent();
        String BakeryName = intent.getStringExtra("Title");
        String Address = intent.getStringExtra("Address");
        bakeryName = (TextView) findViewById(R.id.bakery_name);
        bakeryName.setText(BakeryName);
        tel = (TextView) findViewById(R.id.bakery_call);
        location = (TextView) findViewById(R.id.bakery_add);
        location.setText(Address);
        time = (TextView) findViewById(R.id.bakery_time);
        intro = (TextView) findViewById(R.id.bakery_info);
        picture = (ImageView)findViewById(R.id.iv_bimg);
        bread1 = (ImageView) findViewById(R.id.bread);
        bread2 = (ImageView) findViewById(R.id.bread2);
        bread3 = (ImageView) findViewById(R.id.bread3);
        bread4 = (ImageView) findViewById(R.id.bread4);
        Log.e("설마",BakeryName);



        mDatabase= FirebaseDatabase.getInstance().getReference("BreadClockWeb/Bakeries/BasicInfo/" + "베이크팡" + "/DetailInfo");
        mDatabaseChild1 = mDatabase.child("p_bread1");
        mDatabaseChild2 = mDatabase.child("p_bread2");
        mDatabaseChild3 = mDatabase.child("p_bread3");
        mDatabaseChild4 = mDatabase.child("p_bread4");

        mDatabaseChild1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                BakeryInformation bb = dataSnapshot.getValue(BakeryInformation.class);
                Picasso.with(getApplication())
                        .load(bb.getImg())
                        .into(bread1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("안알랴쥼", "onCancelled", databaseError.toException());
            }
        });

        mDatabaseChild2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                BakeryInformation bb = dataSnapshot.getValue(BakeryInformation.class);
                Picasso.with(getApplication())
                        .load(bb.getImg())
                        .into(bread2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("안알랴쥼", "onCancelled", databaseError.toException());
            }
        });

        mDatabaseChild3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                BakeryInformation bb = dataSnapshot.getValue(BakeryInformation.class);
                Picasso.with(getApplication())
                        .load(bb.getImg())
                        .into(bread3);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("안알랴쥼", "onCancelled", databaseError.toException());
            }
        });

        mDatabaseChild4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                BakeryInformation bb = dataSnapshot.getValue(BakeryInformation.class);
                Picasso.with(getApplication())
                        .load(bb.getImg())
                        .into(bread4);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("안알랴쥼", "onCancelled", databaseError.toException());
            }
        });

        ValueEventListener userBakeryInformationListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    BakeryInformation bb = dataSnapshot.getValue(BakeryInformation.class);
                    Log.e("결과 : ",bb.getPicture());
                    Picasso.with(getApplication())
                        .load(bb.getPicture())
                        .into(picture);
                    tel.setText(bb.getPhone_number());
                    intro.setText(bb.getDescription());
                    time.setText("매일 "+bb.getOpen()+":00 - "+bb.getClose()+":00");
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabase.addValueEventListener(userBakeryInformationListener);

    }

}