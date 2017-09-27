package kr.hs.emirim.parksodam.mirimbreadclock.notice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import kr.hs.emirim.parksodam.mirimbreadclock.Adapter.MyAdapter;
import kr.hs.emirim.parksodam.mirimbreadclock.BarActivity;
import kr.hs.emirim.parksodam.mirimbreadclock.BaseFragment;
import kr.hs.emirim.parksodam.mirimbreadclock.LoginActivity;
import kr.hs.emirim.parksodam.mirimbreadclock.R;
import kr.hs.emirim.parksodam.mirimbreadclock.model.BookmarkBakery;


/**
 * Created by kim on 2017-07-29.
 */


public class NoticeFragment extends BaseFragment {
    private ListView mListView;
    private FirebaseAuth mAuth;
    private String TAG ="where is uid??";
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String bakery;

    ArrayList<BookmarkBakery> seachedBakeris = new ArrayList<>();
    MyAdapter mMyAdapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        View view = inflater.inflate(R.layout.fragment_notice, container, false);
        Log.e(TAG,"성공");
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

        DatabaseReference userBookmarkRef = ((BarActivity) getActivity()).mDatabase.getReference("users/"+ mAuth.getCurrentUser().getUid()+"/bookmarks");
        ValueEventListener userBookmarkListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                seachedBakeris.clear();
                for(DataSnapshot bookmarkBakerySnapshot:dataSnapshot.getChildren()){
                    BookmarkBakery bb =  bookmarkBakerySnapshot.getValue(BookmarkBakery.class);
                    seachedBakeris.add(bb);
                    Log.e(TAG, "내가 좋아하는 빵집은요 : " + bb.name );
                }
                mMyAdapter = new MyAdapter(seachedBakeris);
                mListView.setAdapter(mMyAdapter);
                //mListView.invalidate();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        userBookmarkRef.addValueEventListener(userBookmarkListener);

        // dataSetting();
//
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView parent, View v, int position, long id) {
//                // get item
//                MyItem item = (MyItem) parent.getItemAtPosition(position);
//                Intent intent = new Intent(getActivity(), BookmarkInformation.class);
//                String titleStr = item.getName();
//                String descStr = item.getContents();
//                Drawable iconDrawable = item.getIcon();
//
//                startActivity(intent);
//            }
//        }) ;
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Log.e(TAG,"현재 uid : "+user.getUid());
            mAuth = FirebaseAuth.getInstance();
//            FirebaseDatabase database = FirebaseDatabase.getInstance();
//            bakery = "베이크팡";
//            DatabaseReference myRef = database.getReference("BreadClockWeb/Bakeries/BasicInfo/"+bakery+"/Favorites/"+user.getUid());
//            myRef.setValue(user.getEmail());
        } else {
            Intent intent = new Intent(getActivity(),LoginActivity.class);
            startActivity(intent);
        }
    }
    private void dataSetting() {




//        for (int i = 0; i < 8; i++) {
//            mMyAdapter.addItem(ContextCompat.getDrawable(getActivity(), R.mipmap.basicimg), "빵집 이름", "빵 이름", ContextCompat.getDrawable(getActivity(), R.drawable.star_select));
//        }


        /* 리스트뷰에 어댑터 등록 */
        mListView.setAdapter(mMyAdapter);

    }
    public String getTitle() {
        return "알람";
    }
}
