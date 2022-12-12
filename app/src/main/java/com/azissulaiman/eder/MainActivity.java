package com.azissulaiman.eder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn1, btnQrCode, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btnmap);
        btnQrCode = findViewById(R.id.btnTakeQr);
        btnLogin = findViewById(R.id.btnLogin1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Map.class));
            }
        });

        btnQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TakeQRCode.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

 //       email.setAlpha(v);
//        pass.setAlpha(v);
//        btnLoginEmail.setAlpha(v);
//        clickSignUpEmail.setAlpha(v);
//        clickForgotPass.setAlpha(v);
//
//        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
//        pass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
//        btnLoginEmail.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
//        clickSignUpEmail.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
//        clickForgotPass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();

    }
}