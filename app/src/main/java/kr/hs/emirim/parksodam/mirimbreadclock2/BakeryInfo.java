package kr.hs.emirim.parksodam.mirimbreadclock2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by kim on 2017-11-16.
 */

public class BakeryInfo extends Activity{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bakeryinfo);
//
        TextView tvName=(TextView)findViewById(R.id.tv_bname);
//        TextView tvAdd=(TextView)findViewById(R.id.bakery_add);
//
        Intent intent = getIntent(); //보내온 Intent 얻기
        String tmpTitle = intent.getStringExtra("Title");
        Log.d( "AAA", tmpTitle );
        tvName.setText( tmpTitle);
//================================================================
//
//        Intent intent = getIntent(); //넘어온 Intent 얻기
//
//        String name = intent.getStringExtra("baker_name");
//        String add = intent.getStringExtra("baker_add");
//
//        TextView tvname = (TextView)findViewById(R.id.bakery_name);
//        tvname.setText(name);
//
//        TextView tvadd = (TextView)findViewById(R.id.bakery_add);
//        tvadd.setText(add);


        //Intent intent = getIntent();
        //ImageView img=(ImageView) findViewById(R.id.iv_bimg);
        //img.setImageResource(img);
        //TextView name=(TextView) findViewById(tv_bname);
        //name.setText("빵집이름");
        TextView add=(TextView) findViewById(R.id.tv_badd);
        add.setText("빵집주소");
        TextView tell =(TextView) findViewById(R.id.bakery_call);
        tell.setText("02-6104-2658");
        TextView time =(TextView) findViewById(R.id.bakery_time);
        time.setText("10:00 ~ 18:00");
        TextView info =(TextView) findViewById(R.id.bakery_info);
        info.setText("따뜻한 빵을 제공하기위해 최선을 다합니다.");



    }


}
