package com.azissulaiman.eder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    Button btnSignUp;
    TextView ClickIntentSignIn;
    EditText edtRegUsername, edtRegEmail, edtRegPhone, edtRegPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtRegUsername = (EditText) findViewById(R.id.edtUsernameReg) ;
        edtRegEmail = (EditText) findViewById(R.id.edtEmailReg);
        edtRegPhone = (EditText) findViewById(R.id.edtPhoneReg);
        edtRegPass = (EditText) findViewById(R.id.edtPassReg);

        btnSignUp = (Button) findViewById(R.id.btn_signUp_email);
        ClickIntentSignIn = (TextView) findViewById(R.id.click_signInEmail);

    }
}