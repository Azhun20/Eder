package com.azissulaiman.eder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    ImageButton imgBtnBackHome;
    LinearLayout txtClickViewProfile, txtClickTiketCode;
    TextView clickLogout, txtSetName,txtSetEmail;
    String email, fullName;
    FirebaseAuth authProfileTiket;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);

        imgBtnBackHome = findViewById(R.id.imgBackHome);
        txtClickViewProfile = findViewById(R.id.viewProfile);
        txtSetEmail = findViewById(R.id.txtSetEmail);
        txtSetName = findViewById(R.id.txtSetName);


        txtClickTiketCode = findViewById(R.id.viewTiketBarcode);
        clickLogout = findViewById(R.id.txtClickLogout);
        progressBar = findViewById(R.id.progressBar3);

        authProfileTiket = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfileTiket.getCurrentUser();

        imgBtnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        txtClickViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        txtClickTiketCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showQrCodeTiket(firebaseUser);
            }
        });

        clickLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showQrCodeTiket(FirebaseUser firebaseUser) {
        String userID = firebaseUser.getUid();
        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Users");
        referenceProfile.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReadwriteuserDetails details = snapshot.getValue(ReadwriteuserDetails.class);
                if (details != null){
                    email = firebaseUser.getEmail();
                    fullName = details.username;

                    txtSetName.setText(fullName);
                    txtSetEmail.setText(email);
                    txtSetEmail.setVisibility(View.GONE);
                    txtSetName.setVisibility(View.GONE);

                    Intent intent = new Intent(getApplicationContext(), TiketBarcodeActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", txtSetName.getText().toString());
                    bundle.putString("email", txtSetEmail.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}