package kr.hs.emirim.parksodam.breadclock.bookmark;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import kr.hs.emirim.parksodam.breadclock.R;


public class BookmarkInformation extends AppCompatActivity {

    GridView gridView;

    String letterList[]={"쿠키", "샌드위치", "식빵", "바게트", "햄버거"};

    int lettersIcon[]={R.drawable.bread1, R.drawable.bread2, R.drawable.bread3, R.drawable.bread4, R.drawable.bread5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);
        GridAdapter adapter=new GridAdapter(BookmarkInformation.this,lettersIcon , letterList);

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                //Toast.makeText(BookmarkInformation.this, "Clicked Letter : " + letterList[position], Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(BookmarkInformation.this);     // 여기서 this는 Activity의 this

                // 여기서 부터는 알림창의 속성 설정
                builder.setTitle("빵 즐겨찾기")        // 제목 설정
                        .setMessage("빵 알림에 추가하시겠습니까?")        // 메세지 설정
                        .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                        .setPositiveButton("네", new DialogInterface.OnClickListener(){
                            // 확인 버튼 클릭시 설정
                            public void onClick(DialogInterface dialog, int whichButton){
                                finish();
                            }
                        })
                        .setNegativeButton("아니요", new DialogInterface.OnClickListener(){
                            // 취소 버튼 클릭시 설정
                            public void onClick(DialogInterface dialog, int whichButton){
                                dialog.cancel();
                            }
                        }
                        );

                AlertDialog dialog = builder.create();    // 알림창 객체 생성
                dialog.show();    // 알림창 띄우기


            }
            }
        );

    }
}
