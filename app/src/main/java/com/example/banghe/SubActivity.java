package com.example.banghe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SubActivity extends AppCompatActivity {
    TextView txt_buyName, txt_buyCost;
    ImageView img;
    private int img1;
    Button btn_back, btn_accept, btn_clear;
    TabHost mytab;
    //khai báo listview
    ListView lv1;
    ArrayList<Chair> myList;
    MyArrayAdapter myadapter;

    DatabaseHelper databaseHelper;

    //Khai báo button Call, sms
    ImageButton btnsms, btncall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        txt_buyName = findViewById(R.id.txt_buyName);
        txt_buyCost = findViewById(R.id.txt_buyCost);
        img = findViewById(R.id.image_chair);
        //Nhận intent
        Intent myintent = getIntent();
        String nameChair = myintent.getStringExtra("name");
        String costChair = myintent.getStringExtra("cost");
        img1 = myintent.getIntExtra("image", 0);
        //Hiển thị textview
        txt_buyName.setText(nameChair);
        txt_buyCost.setText(costChair);
        img.setImageResource(img1);

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addControl();
        addEvent();


        //SMS
        btnsms = findViewById(R.id.btnsms);
        btnsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Khai báo intent ẩn
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
                smsIntent.setData(Uri.parse("smsto:" + Uri.encode("012345670123")));
                startActivity(smsIntent);
            }
        });

        //CALL
        btncall = findViewById(R.id.btncall);
        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Khai báo intent ẩn
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + Uri.encode("012345670123")));
                //Yêu cầu sự đồng ý của người dùng
                if (ActivityCompat.checkSelfPermission(SubActivity.this, android.Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SubActivity.this, new String[]
                            {android.Manifest.permission.CALL_PHONE}, 1);
                    return;
                }
                startActivity(callIntent);
            }
        });

        //Clear
        btn_clear = findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myList.clear(); // Xóa toàn bộ dữ liệu trong danh sách
                myadapter.notifyDataSetChanged(); // Cập nhật giao diện
            }
        });

        // Khôi phục trạng thái của tab được chọn nếu có
        if (savedInstanceState != null) {
            int selectedTab = savedInstanceState.getInt("selectedTab", 0); // 0 là tab mặc định
            mytab.setCurrentTab(selectedTab);
        }

    }


    private void addEvent(){
        databaseHelper = new DatabaseHelper(this);
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String buyName = txt_buyName.getText().toString();
                String buyCost = txt_buyCost.getText().toString();
                databaseHelper.insertData(buyName,buyCost);
                Chair newChair = new Chair(img1,buyName, buyCost);
                myList.add(newChair);
                myadapter.notifyDataSetChanged();
            }
        });
    }

    private void addControl(){
        btn_accept = findViewById(R.id.btn_accept);

        //Xử lý listView
        lv1 = findViewById(R.id.lv1);
        myList = new ArrayList<Chair>();
        myadapter = new MyArrayAdapter(SubActivity.this,R.layout.layout_item,myList);
        lv1.setAdapter(myadapter);

        //Xử lý TabHost
        mytab = findViewById(R.id.mytab);
        mytab.setup();
        //Khai báo tab con
        TabHost.TabSpec spec1, spec2;

        //Tab 1
        spec1 = mytab.newTabSpec("t1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("",getResources().getDrawable(R.drawable.muahang));
        mytab.addTab(spec1);

        //Tab 2
        spec2 = mytab.newTabSpec("t2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("",getResources().getDrawable(R.drawable.lichsu));
        mytab.addTab(spec2);
    }
}
