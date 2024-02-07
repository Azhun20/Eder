package com.azissulaiman.eder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class TiketBarcodeActivity extends AppCompatActivity {
    private TextView txtFullName, txtEmail;
    String fullName, email, phone;
    private ProgressBar progressBar;
    FirebaseAuth authProfileGetTiket;
    ImageView imgTiketCode;
    ImageButton backProfile;
    Bitmap bitmap;
    private static final String TAG = "TiketBarcodeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_tiket_barcode);
        final Bundle bundle = getIntent().getExtras();

        txtFullName = findViewById(R.id.txtNamaUserTiket);
        txtEmail = findViewById(R.id.txtEmailTiket);
        progressBar = findViewById(R.id.progressBar2);
        imgTiketCode = findViewById(R.id.imgTiketQrCode);
        backProfile = findViewById(R.id.backProfileBtn);

        backProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TiketBarcodeActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        authProfileGetTiket = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfileGetTiket.getCurrentUser();

        if (firebaseUser == null){
            Toast.makeText(TiketBarcodeActivity.this,"Something Wrong!",Toast.LENGTH_SHORT).show();
        } else {
            progressBar.setVisibility(View.VISIBLE);
            showTiketProfile(firebaseUser);
        }

        QRGEncoder qrgEncoder = new QRGEncoder(null, bundle, QRGContents.Type.CONTACT, 100);
        try {
            // Getting QR-Code as Bitmap
            bitmap = qrgEncoder.getBitmap();
            // Setting Bitmap to ImageView
            imgTiketCode.setImageBitmap(bitmap);
        } catch (Exception e) {
            Log.v(TAG, e.toString());
        }
    }

    private void showTiketProfile(FirebaseUser firebaseUser) {
        String userID = firebaseUser.getUid();
        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Users");
        referenceProfile.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReadwriteuserDetails details = snapshot.getValue(ReadwriteuserDetails.class);
                if (details != null){
                    email = firebaseUser.getEmail();
                    fullName = details.username;

                    txtFullName.setText(fullName);
                    txtEmail.setText(email);

                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TiketBarcodeActivity.this,"Somthing Wrong!", Toast.LENGTH_LONG).show();
            }
        });
    }


}