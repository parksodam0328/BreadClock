package kr.hs.emirim.parksodam.breadclock.bookmark;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import kr.hs.emirim.parksodam.breadclock.R;

public class MainActivity extends AppCompatActivity {

    GridView gridView;

    String letterList[]={"쿠키", "샌드위치", "식빵", "바게트", "햄버거"};

    int lettersIcon[]={R.drawable.bread1, R.drawable.bread2, R.drawable.bread3, R.drawable.bread4, R.drawable.bread5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);
        GridAdapter adapter=new GridAdapter(MainActivity.this,lettersIcon , letterList);

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Toast.makeText(MainActivity.this, "Clicked Letter : " + letterList[position], Toast.LENGTH_SHORT).show();
            }
        });

    }
}
