package kr.hs.emirim.parksodam.mirimbreadclock2.bookmark;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.iamhabib.easy_preference.EasyPreference;

import java.util.ArrayList;

import kr.hs.emirim.parksodam.mirimbreadclock2.BarActivity;
import kr.hs.emirim.parksodam.mirimbreadclock2.BaseFragment;
import kr.hs.emirim.parksodam.mirimbreadclock2.LoginActivity;
import kr.hs.emirim.parksodam.mirimbreadclock2.R;
import kr.hs.emirim.parksodam.mirimbreadclock2.model.BookmarkBakery;
import noman.googleplaces.Place;


/**
 * Created by kim on 2017-07-29.
 */


public class BookmarkFragment extends BaseFragment {
    private static String name;
    private static String location;
    ArrayList<BookmarkBakery> seachedBakeris = new ArrayList<>();
    MyInfoAdapter mMyInfoAdapter;
    private ListView mListView;
    private FirebaseAuth mAuth;
    private String TAG = "where is uid??";
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String bakery;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);
        Log.e(TAG, "성공");
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

        mListView = (ListView) view.findViewById(R.id.listView);
        final Place place = new Place();
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("즐겨찾기");
                builder.setMessage("즐겨찾기를 삭제하시겠습니까?");
                builder.setCancelable(true);
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        BookmarkBakery bb = seachedBakeris.get(position);
                        DatabaseReference bookmarkRef = ((BarActivity) getActivity()).mDatabase.getReference("users/" + mAuth.getCurrentUser().getUid() + "/bookmarks/" + bb.uid);
                        Log.e(TAG, "빵집 삭제 : " + bb.uid);
                        bookmarkRef.setValue(null);
                        //Log.e(TAG,bb.uid);
                        EasyPreference.with(getActivity())
                                .addString(name, place.getName())
                                .save();

                        EasyPreference.with(getActivity())
                                .addString(location, place.getVicinity())
                                .save();

                        Log.d(TAG, "이름 저장");
                        Log.d(TAG, "위치 저장");
                    }
                });
                builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
                return true;
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("알람");
                builder.setMessage("알람을 추가하시겠습니까?");
                builder.setCancelable(true);
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        BookmarkBakery bb = seachedBakeris.get(position);
                        DatabaseReference bookmarkRef = ((BarActivity) getActivity()).mDatabase.getReference("users/" + mAuth.getCurrentUser().getUid() + "/alarms/" + bb.uid);
                        Log.e(TAG, "알람 추가 : " + bb.uid);
                        bookmarkRef.setValue(bb);

                        //FirebaseMessaging.getInstance().subscribeToTopic(place.getPlaceId());
                        //Log.e(TAG,bb.uid);
                        EasyPreference.with(getActivity())
                                .addString(name, place.getName())
                                .save();

                        EasyPreference.with(getActivity())
                                .addString(location, place.getVicinity())
                                .save();

                        Log.d(TAG, "이름 저장");
                        Log.d(TAG, "위치 저장");
                    }
                });
                builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }
        });
        DatabaseReference userBookmarkRef = ((BarActivity) getActivity()).mDatabase.getReference("users/" + mAuth.getCurrentUser().getUid() + "/bookmarks");
        ValueEventListener userBookmarkListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                seachedBakeris.clear();
                for (DataSnapshot bookmarkBakerySnapshot : dataSnapshot.getChildren()) {
                    BookmarkBakery bb = bookmarkBakerySnapshot.getValue(BookmarkBakery.class);
                    seachedBakeris.add(bb);
                    Log.e(TAG, "내가 좋아하는 빵집은요 : " + bb.name);
                }
                mMyInfoAdapter = new MyInfoAdapter(seachedBakeris);
                mListView.setAdapter(mMyInfoAdapter);
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
//                MyAlarmItem item = (MyAlarmItem) parent.getItemAtPosition(position);
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
            Log.e(TAG, "현재 uid : " + user.getUid());
            mAuth = FirebaseAuth.getInstance();
//            FirebaseDatabase database = FirebaseDatabase.getInstance();
//            bakery = "베이크팡";
//            DatabaseReference myRef = database.getReference("BreadClockWeb/Bakeries/BasicInfo/"+bakery+"/Favorites/"+user.getUid());
//            myRef.setValue(user.getEmail());
        } else {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }

    private void dataSetting() {


//        for (int i = 0; i < 8; i++) {
//            mMyAdapter.addItem(ContextCompat.getDrawable(getActivity(), R.mipmap.basicimg), "빵집 이름", "빵 이름", ContextCompat.getDrawable(getActivity(), R.drawable.star_select));
//        }


        /* 리스트뷰에 어댑터 등록 */
        mListView.setAdapter(mMyInfoAdapter);

    }


    @Override
    public String getTitle() {
        return "즐겨찾기";
    }
}