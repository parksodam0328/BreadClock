package kr.hs.emirim.parksodam.breadclock.map;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import kr.hs.emirim.parksodam.breadclock.BaseFragment;
import kr.hs.emirim.parksodam.breadclock.R;
import kr.hs.emirim.parksodam.breadclock.bookmark.BookmarkInformation;
import kr.hs.emirim.parksodam.breadclock.listview.MyAdapter;
import noman.googleplaces.NRPlaces;
import noman.googleplaces.Place;
import noman.googleplaces.PlaceType;
import noman.googleplaces.PlacesException;
import noman.googleplaces.PlacesListener;

import static android.content.Context.LOCATION_SERVICE;

public class MapFragment extends BaseFragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,
        PlacesListener {

    LatLng currentPosition = null;
    List<Marker> previous_marker = null;

    private GoogleApiClient mGoogleApiClient = null;
    private GoogleMap mGoogleMap = null;
    private Marker currentMarker = null;

    //디폴트 위치, Seoul
    private static final LatLng DEFAULT_LOCATION = new LatLng(37.56, 126.97);
    private static final String TAG = "googlemap_example";
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 2002;
    private static final int UPDATE_INTERVAL_MS = 1000;  // 3분
    private static final int FASTEST_UPDATE_INTERVAL_MS = 500; // 3분

    boolean askPermissionOnceAgain = false;
    private View view;
    private ListView mListView;
    private ImageView iv_bookmark;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            view = inflater.inflate(R.layout.fragment_map, container, false);

            FrameLayout fl = (FrameLayout) view.findViewById(R.id.fl_content);
            RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.rl_contents);
            com.google.android.gms.maps.MapFragment fragment = new com.google.android.gms.maps.MapFragment();

            SupportMapFragment map = SupportMapFragment.newInstance();
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();

            previous_marker = new ArrayList<Marker>();

            map.getMapAsync(this);
            ft.add(R.id.map, map);
            ft.commit();

        }catch (InflateException e) { }
        Button button = (Button) view.findViewById(R.id.button);
        mListView = (ListView) view.findViewById(R.id.listView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPlaceInformation(currentPosition);
                Log.d(TAG,"연결 성공");
                ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if (mobile.isConnected() || wifi.isConnected()) {
                    dataSetting();

                    // if (sendInformation() != null) {
                    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getActivity(), BookmarkInformation.class); // 다음넘어갈 화면

                            Log.d(TAG, "빵집 이름");
                            Log.d(TAG, "위치");
                            Log.d(TAG, "빵집 사진");
                            Log.d(TAG, "즐겨찾기 버튼");
                            startActivity(intent);

                        }
                    });
                }


            }
        });
        return view;
    }


    private void dataSetting(){

        MyAdapter mMyAdapter = new MyAdapter();
        Log.e(TAG,"==========================빵집리스트 목록 갱신====================");
        for(Marker m : previous_marker){
            Log.e(TAG,"빵집 추가 : "+m.getTitle()+"/ 빵 : ");

            mMyAdapter.addItem(ContextCompat.getDrawable(getActivity(),R.mipmap.basicimg), m.getTitle(), m.getSnippet(),ContextCompat.getDrawable(getActivity(),R.drawable.bookmark_select));
        }

        /* 리스트뷰에 어댑터 등록 */
        mListView.setAdapter(mMyAdapter);
    }


    @Override
    public String getTitle() {
        return "지도";
    }

    @Override
    public void onPlacesFailure(PlacesException e) {

    }

    @Override
    public void onPlacesStart() {

    }

    @Override
    public void onPlacesSuccess(final List<Place> places) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (noman.googleplaces.Place place : places) {
                    Log.e(TAG,"아이콘? "+place.getIcon());
                    LatLng latLng
                            = new LatLng(place.getLatitude(), place.getLongitude());
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title(place.getName());
                    markerOptions.snippet(place.getVicinity());
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.breadmarker));
                    Marker item = mGoogleMap.addMarker(markerOptions);
                    previous_marker.add(item);

                }

                //중복 마커 제거
                HashSet<Marker> hashSet = new HashSet<Marker>();
                hashSet.addAll(previous_marker);
                previous_marker.clear();
                previous_marker.addAll(hashSet);
                for(Marker m: hashSet) {
                    Log.e("빵빵:MapFrag", "가져온 정보 : " + m.getTitle());
                }
                dataSetting();



            }
        });

    }

    public void showPlaceInformation(LatLng location) {
        Log.d(TAG,"연결 성공");
        ConnectivityManager manager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(location!=null) {
            mGoogleMap.clear();//지도 클리어

            if (previous_marker != null)
                previous_marker.clear();//지역정보 마커 클리어

            new NRPlaces.Builder()
                    .listener(this)
                    .key("AIzaSyAocCFlcpTitCBLcc2Dtl8iY2mT7XrhvAk")
                    .latlng(location.latitude, location.longitude)//현재 위치
                    .radius(1000) //1 킬로미터 내에서 검색
                    .type(PlaceType.BAKERY) //음식점
               //    .language("kor","kor_KO")
                    .build().execute();
        }
        else{
            if(!mobile.isConnected() && !wifi.isConnected()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("네트워크 오류");
                builder.setMessage("네트워크에 연결되어 있지 않아 동기화를 진행할 수 없습니다. 통신 상태를 확인해주세요." );
                builder.setCancelable(true);
                builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent callNetworkSettingIntent
                                = new Intent(Settings.ACTION_WIFI_SETTINGS);
                        startActivity(callNetworkSettingIntent);
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }
        }
    }

    @Override
    public void onPlacesFinished() {

    }

    @Override
    public void onResume() {
        super.onResume();

        if (mGoogleApiClient!=null)
            mGoogleApiClient.connect();

        //앱 정보에서 퍼미션을 허가했는지를 다시 검사해봐야 한다.
        if (askPermissionOnceAgain) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                askPermissionOnceAgain = false;

                checkPermissions();
            }
        }
    }

    @Override
    public void onStop() {

        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }


    @Override
    public void onPause() {

        //위치 업데이트 중지
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {

            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);

            mGoogleApiClient.disconnect();
        }

        super.onPause();
    }

    @Override
    public void onDestroy() {

        if (mGoogleApiClient != null) {
            mGoogleApiClient.unregisterConnectionCallbacks(this);
            mGoogleApiClient.unregisterConnectionFailedListener(this);

            if (mGoogleApiClient.isConnected()) {
                LocationServices.FusedLocationApi
                        .removeLocationUpdates(mGoogleApiClient, this);
                mGoogleApiClient.disconnect();
            }

        }

        super.onDestroy();
    }



    @Override
    public void onMapReady(GoogleMap map) {

        Log.d(TAG, "onMapReady");
        mGoogleMap = map;

        //런타임 퍼미션 요청 대화상자나 GPS 활성 요청 대화상자 보이기전에
        //지도의 초기위치를 서울로 이동
        setCurrentLocation(null, "위치정보 가져올 수 없음",  "위치 퍼미션과 GPS 활성 요부 확인하세요");

        mGoogleMap.getUiSettings().setCompassEnabled(true); //나침반
        map.moveCamera(CameraUpdateFactory.newLatLng(DEFAULT_LOCATION));
        //mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //API 23 이상이면 런타임 퍼미션 처리 필요

            int hasFineLocationPermission = ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION);

            if (hasFineLocationPermission == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions( getActivity(),
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

            } else {

                if (mGoogleApiClient == null) {
                    buildGoogleApiClient();
                }

                if (ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {

                    mGoogleMap.setMyLocationEnabled(true);
                }

            }
        } else {

            if (mGoogleApiClient == null) {
                buildGoogleApiClient();
            }


            mGoogleMap.setMyLocationEnabled(true);

        }

    }


    protected synchronized void buildGoogleApiClient() {

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();

    }

    @Override
    public void onConnected(Bundle connectionHint) {

        Log.d(TAG,"onConnected");
        if (!checkLocationServicesStatus()) {
            showDialogForLocationServiceSetting();
        }

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(UPDATE_INTERVAL_MS);
        locationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_MS);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                LocationServices.FusedLocationApi
                        .requestLocationUpdates(mGoogleApiClient, locationRequest, this);

            }
        }
        else{

            Log.d(TAG,"onConnected : call FusedLocationApi");
            LocationServices.FusedLocationApi
                    .requestLocationUpdates(mGoogleApiClient, locationRequest, this);

            mGoogleMap.getUiSettings().setCompassEnabled(true);
            //mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        }
    }
    @Override
    public void onLocationChanged(Location location) {

        currentPosition
                = new LatLng( location.getLatitude(), location.getLongitude());

        Log.d( TAG, "onLocationChanged");
        String markerTitle = getCurrentAddress(location);
        String markerSnippet = String.valueOf(location.getLatitude())
                + String.valueOf(location.getLongitude());

        //현재 위치에 마커 생성
        setCurrentLocation(location, markerTitle, markerSnippet);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }




    @Override
    public void onConnectionSuspended(int cause) {
        if ( cause ==  CAUSE_NETWORK_LOST )
            Log.e(TAG, "onConnectionSuspended(): Google Play services " +
                    "connection lost.  Cause: network lost.");
        else if (cause == CAUSE_SERVICE_DISCONNECTED )
            Log.e(TAG,"onConnectionSuspended():  Google Play services " +
                    "connection lost.  Cause: service disconnected");
    }


    public String getCurrentAddress(Location location){

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    1);
        } catch (IOException ioException) {
            //네트워크 문제
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("네트워크 오류");
            builder.setMessage("네트워크에 연결되어 있지 않아 동기화를 진행할 수 없습니다. 통신 상태를 확인해주세요." );
            builder.setCancelable(true);
            builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Intent callNetworkSettingIntent
                            = new Intent(Settings.ACTION_WIFI_SETTINGS);
                    startActivity(callNetworkSettingIntent);
                }
            });
            builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id){
                    dialog.cancel();
                }
            });
            builder.create().show();
            return "네트워크 연결 오류";
        } catch (IllegalArgumentException illegalArgumentException) {
            showDialogForLocationServiceSetting();
            return "GPS 연결 오류";

        }


        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(getActivity(), "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        } else {
            Address address = addresses.get(0);
            return address.getAddressLine(0).toString();
        }

    }


    public boolean checkLocationServicesStatus(){
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public void setCurrentLocation(Location location, String markerTitle, String markerSnippet) {

        if (currentMarker != null) currentMarker.remove();


        if (location != null) {
            LatLng currentLocation = new LatLng( location.getLatitude(), location.getLongitude());

            //마커를 원하는 이미지로 변경해줘야함
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(currentLocation);
            markerOptions.title(markerTitle);
            markerOptions.draggable(true);
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.locationmarker));
            currentMarker = mGoogleMap.addMarker(markerOptions);

            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
            return;
        }

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);
        markerOptions.title(markerTitle);
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.breadmarker));
        currentMarker = mGoogleMap.addMarker(markerOptions);

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(DEFAULT_LOCATION));

        return;
    }


    //여기부터는 런타임 퍼미션 처리을 위한 메소드들
    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissions() {
        boolean fineLocationRationale = ActivityCompat
                .shouldShowRequestPermissionRationale(getActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION);
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION);

        if (hasFineLocationPermission == PackageManager
                .PERMISSION_DENIED && fineLocationRationale )
            showDialogForPermission("앱을 실행하려면 퍼미션을 허가하셔야합니다.");

        else if (hasFineLocationPermission
                == PackageManager.PERMISSION_DENIED && !fineLocationRationale ) {
            showDialogForPermissionSetting("퍼미션 거부 + Don't ask again(다시 묻지 않음) " +
                    "체크 박스를 설정한 경우로 설정에서 퍼미션 허가해야합니다.");
        }

        else if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED) {

            if (mGoogleApiClient == null) {
                buildGoogleApiClient();
            }

            mGoogleMap.setMyLocationEnabled(true);


        }
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (permsRequestCode
                == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION && grantResults.length > 0) {

            boolean permissionAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

            if (permissionAccepted) {

                if (mGoogleApiClient == null) {
                    buildGoogleApiClient();
                }

                if (ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {

                    mGoogleMap.setMyLocationEnabled(true);
                }


            }
            else {

                checkPermissions();
            }
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    private void showDialogForPermission(String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("알림");
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ActivityCompat.requestPermissions( getActivity(),
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                getActivity().finish();
            }
        });
        builder.create().show();
    }

    private void showDialogForPermissionSetting(String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("알림");
        builder.setMessage(msg);
        builder.setCancelable(true);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                askPermissionOnceAgain = true;

                Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getActivity().getPackageName()));
                myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
                myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(myAppSettings);
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                getActivity().finish();
            }
        });
        builder.create().show();
    }


    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?" );
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id){
                dialog.cancel();
            }
        });
        builder.create().show();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (ActivityCompat.checkSelfPermission(getActivity(),
                                    Manifest.permission.ACCESS_FINE_LOCATION)
                                    == PackageManager.PERMISSION_GRANTED) {

                                mGoogleMap.setMyLocationEnabled(true);
                            }
                        }
                        else  mGoogleMap.setMyLocationEnabled(true);

                        return;
                    }
                }
                else{
                    setCurrentLocation(null, "위치정보 가져올 수 없음", "위치 퍼미션과 GPS 활성 요부 확인하세요");
                }

                break;
        }
    }
}