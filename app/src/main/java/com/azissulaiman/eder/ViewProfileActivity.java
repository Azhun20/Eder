package com.azissulaiman.eder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class ViewProfileActivity extends AppCompatActivity {
    private TextView txtFullName, txtEmail;
    private ImageView imgPhotoProfile;
    private ImageButton btnBackProfile;
    String fullName, email, phone;
    private ProgressBar progressBar;
    FirebaseAuth authProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_profile);

        txtFullName = findViewById(R.id.txtNamaUser);
        txtEmail = findViewById(R.id.txtEmailUser);
        imgPhotoProfile = findViewById(R.id.imgProfile);
        btnBackProfile = findViewById(R.id.btnBackProfile);
        progressBar = findViewById(R.id.progressBar2);

        authProfile = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfile.getCurrentUser();

        btnBackProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewProfileActivity.this, ProfileActivity.class));
                finish();
            }
        });

        if (firebaseUser == null){
            Toast.makeText(ViewProfileActivity.this,"Something Wrong!",Toast.LENGTH_SHORT).show();
        } else {
            progressBar.setVisibility(View.VISIBLE);
            showUserProfile(firebaseUser);
        }
    }

    private void showUserProfile(FirebaseUser firebaseUser) {
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
                Toast.makeText(ViewProfileActivity.this,"Somthing Wrong!", Toast.LENGTH_LONG).show();
            }
        });

    }
}