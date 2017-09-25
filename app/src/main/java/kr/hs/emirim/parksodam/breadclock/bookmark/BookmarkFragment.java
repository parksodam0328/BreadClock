package kr.hs.emirim.parksodam.breadclock.bookmark;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import kr.hs.emirim.parksodam.breadclock.Adapter.MyAdapter;
import kr.hs.emirim.parksodam.breadclock.Adapter.MyItem;
import kr.hs.emirim.parksodam.breadclock.BaseFragment;
import kr.hs.emirim.parksodam.breadclock.LoginActivity;
import kr.hs.emirim.parksodam.breadclock.R;


/**
 * Created by kim on 2017-07-29.
 */


public class BookmarkFragment extends BaseFragment {
    private ListView mListView;
    private FirebaseAuth mAuth;
    private String TAG ="where is uid??";
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String bakery;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                // Write a message to the database
                if (mAuth != null) {


                } else {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                if (mAuthListener != null) {
                    mAuth.removeAuthStateListener(mAuthListener);
                }
            }
        };

        mListView = (ListView)view.findViewById(R.id.listView);
        dataSetting();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                MyItem item = (MyItem) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), BookmarkInformation.class);
                String titleStr = item.getName();
                String descStr = item.getContents();
                Drawable iconDrawable = item.getIcon();

                startActivity(intent);
            }
        }) ;
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            Toast toast = Toast.makeText(getActivity(),"로그인에 실패하였습니다. 다시 로그인 해주세요.",Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
        else {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            bakery = "베이크팡";
            DatabaseReference myRef = database.getReference("BreadClockWeb/Bakeries/BasicInfo/" + bakery + "/Favorites/" + user.getUid());
            myRef.setValue(user.getDisplayName());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAuth=null;
        mAuth.signOut();
        }

    private void dataSetting() {

        MyAdapter mMyAdapter = new MyAdapter();


        for (int i = 0; i < 8; i++) {
            mMyAdapter.addItem(ContextCompat.getDrawable(getActivity(), R.mipmap.basicimg), "빵집 이름", "빵 이름", ContextCompat.getDrawable(getActivity(), R.drawable.star_select));
        }


        /* 리스트뷰에 어댑터 등록 */
        mListView.setAdapter(mMyAdapter);

    }



    @Override
    public String getTitle() {
        return "즐겨찾기";
    }
}

