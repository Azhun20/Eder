package com.azissulaiman.eder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    Button btnSignUp;
    TextView ClickIntentSignIn;
    EditText edtRegUsername, edtRegEmail, edtRegPhone, edtRegPass;
    ProgressBar progressBar;
    private static final String TAG="RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressBar = findViewById(R.id.progressBar);
        edtRegUsername = (EditText) findViewById(R.id.edtUsernameReg) ;
        edtRegEmail = (EditText) findViewById(R.id.edtEmailReg);
        edtRegPhone = (EditText) findViewById(R.id.edtPhoneReg);
        edtRegPass = (EditText) findViewById(R.id.edtPassReg);

        btnSignUp = (Button) findViewById(R.id.btn_signUp_email);
        ClickIntentSignIn = (TextView) findViewById(R.id.click_signInEmail);

        ClickIntentSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textUsername = edtRegUsername.getText().toString();
                String textEmail = edtRegEmail.getText().toString();
                String textPhone = edtRegPhone.getText().toString();
                String textPass = edtRegPass.getText().toString();

                //Validate Mobile
//                String mobileRegex = "[6-9][0-9]{9}";
//                Matcher mobileMatcher;
//                Pattern mobilPattern = Pattern.compile(mobileRegex);
//                mobileMatcher = mobilPattern.matcher(textPhone);

                if (TextUtils.isEmpty(textUsername)){
                    Toast.makeText(RegisterActivity.this, "Masukan Username anda", Toast.LENGTH_LONG).show();
                    edtRegUsername.setError("Username Tidak Boleh Kosong");
                    edtRegUsername.requestFocus();
                } else if (TextUtils.isEmpty(textEmail)){
                    Toast.makeText(RegisterActivity.this, "Masukan Email anda", Toast.LENGTH_LONG).show();
                    edtRegEmail.setError("Email Tidak Boleh Kosong");
                    edtRegEmail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()){
                    Toast.makeText(RegisterActivity.this, "Masukan Kemabil Email anda", Toast.LENGTH_LONG).show();
                    edtRegEmail.setError("Email Tidak Valid");
                    edtRegEmail.requestFocus();
                } else if (TextUtils.isEmpty(textPhone)) {
                    Toast.makeText(RegisterActivity.this, "Masukan No Telepon anda", Toast.LENGTH_LONG).show();
                    edtRegPhone.setError("No Telepon Tidak Boleh Kosong");
                    edtRegPhone.requestFocus();
                } else if (textPhone.length() > 12){
                    Toast.makeText(RegisterActivity.this, "Masukan Kemabil No Telepon anda", Toast.LENGTH_LONG).show();
                    edtRegPhone.setError("No Telepon Tidak boleh lebih dari 12");
                    edtRegPhone.requestFocus();
//                } else if (!mobileMatcher.find()){
//                    Toast.makeText(RegisterActivity.this, "Masukan Kemabil No Telepon anda", Toast.LENGTH_LONG).show();
//                    edtRegPhone.setError("No Telepon Tidak boleh lebih dari 12");
//                    edtRegPhone.requestFocus();
                } else if (TextUtils.isEmpty(textPass)){
                    Toast.makeText(RegisterActivity.this, "Masukan Password anda", Toast.LENGTH_LONG).show();
                    edtRegPhone.setError("Password Tidak Boleh Kosong");
                    edtRegPhone.requestFocus();
                } else if (textPass.length() < 6){
                    Toast.makeText(RegisterActivity.this, "Masukan password lebih dari 6 digits", Toast.LENGTH_LONG).show();
                    edtRegPhone.setError("Password kurang bagus");
                    edtRegPhone.requestFocus();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    registerUser(textUsername,textEmail,textPhone,textPass);
                }
            }
        });

    }

    private void registerUser(String textUsername, String textEmail, String textPhone, String textPass) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        //Create User Profile
        auth.createUserWithEmailAndPassword(textEmail, textPass).addOnCompleteListener(RegisterActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            FirebaseUser firebaseUser = auth.getCurrentUser();

                            //Enter User Data In Realtime Database
                            ReadwriteuserDetails readwriteuserDetails = new ReadwriteuserDetails(textUsername,textPhone);

                            DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Users");

                            referenceProfile.child(firebaseUser.getUid()).setValue(readwriteuserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        firebaseUser.sendEmailVerification();
                                        Toast.makeText(RegisterActivity.this, "User Berhasil Register", Toast.LENGTH_LONG).show();
                                        //Open User Dashboard
                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        Toast.makeText(RegisterActivity.this, "User Gagal Register", Toast.LENGTH_LONG).show();

                                    }
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e){
                                edtRegPass.setError("Your Password is too weak. Kindly use a mix of alphabeths, number and special characters");
                                edtRegPass.requestFocus();
                            } catch (FirebaseAuthInvalidCredentialsException e){
                                edtRegEmail.setError("Your email is invalid or already in use. Kindly re- enter.");
                                edtRegEmail.requestFocus();
                            } catch (FirebaseAuthUserCollisionException e) {
                                edtRegEmail.setError("User is already registered with this email. Use another email.");
                                edtRegEmail.requestFocus();
                            } catch (Exception e){
                                Log.e(TAG, e.getMessage());
                                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

                            }
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}