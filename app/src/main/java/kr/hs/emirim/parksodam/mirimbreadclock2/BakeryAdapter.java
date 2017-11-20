//package kr.hs.emirim.parksodam.mirimbreadclock2;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
///**
// * Created by kim on 2017-11-20.
// */
//
//public class BakeryAdapter extends BaseAdapter {
//    private LayoutInflater inflater;
//    private ArrayList<BakeryInfo> data;
//    private int layout;
//
//    public BakeryAdapter(Context context, int layout, ArrayList<BakeryInfo> data){
//        this.inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        this.data=data;
//        this.layout=layout;
//    }
//
//    @Override
//    public int getCount() { //리스트 안 Item 개수 세기
//        return data.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return data.get(position).getName();
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int i, View convertView, ViewGroup parent) {
//        TextView name=(TextView)convertView.findViewById(R.id.tv_name);
//        name=setText(bakeryItem.getName());
//
//        TextView add=(TextView)convertView.findViewById(R.id.tv_contents);
//        add=setText(bakeryItem.getName());
//
//
//
//
//
//    }
