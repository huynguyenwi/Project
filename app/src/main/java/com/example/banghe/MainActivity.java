package com.example.banghe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Khai báo data, adapter, listview
    int image[] = {R.drawable.bala,R.drawable.batieu,R.drawable.chinlan,
                R.drawable.hailangang,R.drawable.taivoi, R.drawable.tholech,
                R.drawable.tuacong, R.drawable.ghebo};
    String name[] = {"Ghế Ba Lá", "Ghế Ba Tiêu", "Ghế chín lan", "Ghế Hai Lá Ngang",
                    "Ghế Tai Voi", "Ghế Thọ Lệch", "Ghế Tựa Cong", "Ghế Bộ"};
    String cost[] = {"Giá bán: 1000000", "Giá bán: 2000000", "Giá bán: 2500000",
                    "Giá bán: 1200000", "Giá bán: 3000000", "Giá bán: 3300000",
                    "Giá bán: 5000000", "Giá bán: 12000000"};
    ArrayList<Chair> myList;
    MyArrayAdapter myadapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);
        myList = new ArrayList<>();
        //Add 3 mảng con là image, name, cost vào mảng chính mylist
        for (int i = 0; i < cost.length; i++){
            myList.add(new Chair(image[i], name[i], cost[i]));
        }
        //tạo mới adapter
        myadapter = new MyArrayAdapter(MainActivity.this,R.layout.layout_item,myList);
        lv.setAdapter(myadapter);

        //Xử lý click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Sử dụng intent công khai
                Intent myintent = new Intent(MainActivity.this, SubActivity.class);
                myintent.putExtra("name", name[i]);
                myintent.putExtra("cost", cost[i]);
                myintent.putExtra("image",image[i]);
                startActivity(myintent);
            }
        });
    }


    //Dialog thoát ứng dụng
    @Override
    public void onBackPressed() {
        //Tạo dialog
        AlertDialog.Builder myDialog = new AlertDialog.Builder(MainActivity.this);
        myDialog.setTitle("Thoát ứng dụng");
        myDialog.setMessage("Bạn có chắc là muốn thoát chương trình không ?");
        myDialog.setIcon(R.drawable.home);
        myDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        myDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        myDialog.create().show(); //hiển thị dialog
    }

}