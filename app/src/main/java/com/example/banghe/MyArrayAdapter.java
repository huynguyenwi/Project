package com.example.banghe;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Chair> {
    Activity context;
    int IdLayout;
    ArrayList<Chair> mylist;
    TabHost mytab;

    public MyArrayAdapter(Activity context, int idLayout, ArrayList<Chair> mylist) {
        super(context, idLayout, mylist);
        this.context = context;
        IdLayout = idLayout;
        this.mylist = mylist;
    }

    // Sắp xếp dữ liệu

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Tạo đế chứa layout
        LayoutInflater myflater = context.getLayoutInflater();
        //Đặt idlayout lên đế để tạo thành 1 đối tượng view
        convertView = myflater.inflate(IdLayout,null);
        //Lấy 1 phần tử trong mảng
        Chair mychair = mylist.get(position);
        //Khai báo, tham chiếu Id và hiển thị ảnh lên ImageView
        ImageView img_chair = convertView.findViewById(R.id.img_chair);
        img_chair.setImageResource(mychair.getImage());
        //khai báo, tham chiếu id và hiển thị tên ghế và giá lên TextView
        TextView txt_chair = convertView.findViewById(R.id.txt_chair);
        txt_chair.setText(mychair.getName());
        TextView txt_cost = convertView.findViewById(R.id.txt_cost);
        txt_cost.setText(mychair.getCost());
        return convertView;
    }
}
