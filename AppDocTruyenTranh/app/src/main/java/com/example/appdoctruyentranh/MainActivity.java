package com.example.appdoctruyentranh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.appdoctruyentranh.adapter.TruyenTranhAdapter;
import com.example.appdoctruyentranh.api.ApiLayTruyen;
import com.example.appdoctruyentranh.interfaces.LayTruyenVe;
import com.example.appdoctruyentranh.object.TruyenTranh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LayTruyenVe {
    GridView gdvDSTruyen;
    TruyenTranhAdapter adapter;
    ArrayList<TruyenTranh> truyenTranhArrayList;
    EditText edtTimKiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiLayTruyen(this).execute();
    }
    private void init(){
        truyenTranhArrayList = new ArrayList<>();
//        truyenTranhArrayList.add(new TruyenTranh("Doraemon","Chapter 1","https://st.nettruyenus.com/data/comics/139/san-dau-cua-thay-ma.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Conan","Chapter 1","https://st.nettruyenus.com/data/comics/32/vo-luyen-dinh-phong.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Shin-cậu bé bút chì","Chapter 24.2","https://st.nettruyenus.com/data/comics/32/vo-luyen-dinh-phong.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Đế chế","Chapter 24.2","https://st.nettruyenus.com/data/comics/32/vo-luyen-dinh-phong.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Tây du kí","Chapter 24.2","https://st.nettruyenus.com/data/comics/188/dai-quan-gia-la-ma-hoang-904.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Wibu","Chapter 24.2","https://static.tuoitre.vn/tto/i/s626/2011/08/23/kePw3hFl.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Wibu","Chapter 24.2","https://st.nettruyenus.com/data/comics/32/vo-luyen-dinh-phong.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Wibu","Chapter 24.2","https://st.nettruyenus.com/data/comics/188/dai-quan-gia-la-ma-hoang-904.jpg"));
//        truyenTranhArrayList.add(new TruyenTranh("Wibu","Chapter 24.2","https://st.nettruyenus.com/data/comics/32/vo-luyen-dinh-phong.jpg"));
        adapter = new TruyenTranhAdapter(this, 0, truyenTranhArrayList);
    }
    private void anhXa(){
        gdvDSTruyen = findViewById(R.id.gdvDSTruyen);
        edtTimKiem = findViewById(R.id.edtTimKiem);
    }
    private void setUp(){
        gdvDSTruyen.setAdapter(adapter);
    }
    private void setClick(){
        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String x = edtTimKiem.getText().toString().trim();
                adapter.sortTruyen(x);
            }
        });
    }

    @Override
    public void batDau() {
        Toast.makeText(this, "Loading ...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        try {
            truyenTranhArrayList.clear();
            JSONArray arr = new JSONArray(data);
            for (int i = 0; i< arr.length();i++){
                JSONObject o = arr.getJSONObject(i);
                truyenTranhArrayList.add(new TruyenTranh(o));
            }
            adapter = new TruyenTranhAdapter(this, 0, truyenTranhArrayList);
            gdvDSTruyen.setAdapter(adapter);
        }catch (JSONException e){
//            e.getMessage();
        }
    }

    @Override
    public void biLoi() {
        Toast.makeText(this, "Conect False !!!", Toast.LENGTH_SHORT).show();
    }
}