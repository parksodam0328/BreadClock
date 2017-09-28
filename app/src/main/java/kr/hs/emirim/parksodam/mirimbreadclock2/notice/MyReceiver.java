package kr.hs.emirim.parksodam.mirimbreadclock2.notice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

/**
 * Created by progh2 on 2017-09-28.
 */

public class MyReceiver extends BroadcastReceiver {

    public MyReceiver(){

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "우아", Toast.LENGTH_SHORT ).show();
        String t = intent.getStringExtra("title");
        String b = intent.getStringExtra("body");

        AlertDialog.Builder builder = new AlertDialog.Builder(context);     // 여기서 this는 Activity의 this

// 여기서 부터는 알림창의 속성 설정
        builder.setTitle(t)        // 제목 설정
                .setMessage(b)        // 메세지 설정
                .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                .setPositiveButton("확인", new DialogInterface.OnClickListener(){
                    // 확인 버튼 클릭시 설정
                    public void onClick(DialogInterface dialog, int whichButton){
                        //finish();
                    }
                });
//                .setNegativeButton("취소", new DialogInterface.OnClickListener(){
//                    // 취소 버튼 클릭시 설정
//                    public void onClick(DialogInterface dialog, int whichButton){
//                        dialog.cancel();
//                    }
        //     });

        AlertDialog dialog =  builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기


    }
}