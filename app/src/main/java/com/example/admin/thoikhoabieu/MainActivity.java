package com.example.admin.thoikhoabieu;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imgView;
    EditText edtTenDangNhap, edtPass;
    Button btnDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = edtTenDangNhap.getText().toString().trim();
                String password = edtPass.getText().toString().trim();

                if (username.equals("") || password.equals(""))
                    Toast.makeText(MainActivity.this, "Bạn chưa điền đầy đủ thông tin vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
                    startActivity(intent);
                }


            }
        });

    }

    public void anhXa(){

        imgView = findViewById(R.id.imgView);
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
        edtPass = findViewById(R.id.edtPass);
        btnDangNhap = findViewById(R.id.btnDangNhap);
    }
}
