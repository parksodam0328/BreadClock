package kr.hs.emirim.parksodam.mirimbreadclock2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by kim on 2017-11-16.
 */

public class BakeryInfo extends Activity{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bakeryinfo);
//
//        TextView tvName=(TextView)findViewById(R.id.bakery_name);
//        TextView tvAdd=(TextView)findViewById(R.id.bakery_add);
//
//        Intent intent = getIntent(); //보내온 Intent 얻기
//        tvName.setText(intent.getStringExtra("name"));
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


        Intent intent = getIntent();

        TextView name=(TextView) findViewById(R.id.tv_bname);
        name.setText("name");
        TextView add=(TextView) findViewById(R.id.tv_badd);
        add.setText("add");


    }


}
