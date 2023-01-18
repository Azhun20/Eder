package com.azissulaiman.eder.fragments;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.azissulaiman.eder.DashboardActivity;
import com.azissulaiman.eder.LoginActivity;
import com.azissulaiman.eder.R;
import com.azissulaiman.eder.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmailLoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmailLoginFragment extends Fragment {
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    TextView edtEmailLogin, edtPassLogin;
    private static final String TAG = "EmailLoginFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EmailLoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EmailLoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EmailLoginFragment newInstance(String param1, String param2) {
        EmailLoginFragment fragment = new EmailLoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_email_login, container, false);

        //Declartaion
        TextView clickSignUpEmail = view.findViewById(R.id.click_signUpEmail);
        TextView btnSignInEmail = view.findViewById(R.id.btn_signIn_email);
        edtEmailLogin = view.findViewById(R.id.edtEmailLogin);
        edtPassLogin = view.findViewById(R.id.edtPassLogin);
        progressBar = view.findViewById(R.id.progressBarLogin);


        //
        firebaseAuth = FirebaseAuth.getInstance();

        clickSignUpEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegisterActivity.class);
                getActivity().finish();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        
        btnSignInEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmailLogin.getText().toString();
                String pass = edtPassLogin.getText().toString();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getActivity(),"Masukan email anda", Toast.LENGTH_LONG).show();
                    edtEmailLogin.setError("Email harus di isi");
                    edtEmailLogin.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(getActivity(),"Masukan email anda sekali lagi", Toast.LENGTH_LONG).show();
                    edtEmailLogin.setError("Masukan Email yang benar");
                    edtEmailLogin.requestFocus();
                } else if (TextUtils.isEmpty(pass)){
                    Toast.makeText(getActivity(),"Masukan password anda", Toast.LENGTH_LONG).show();
                    edtEmailLogin.setError("Password harus di isi");
                    edtEmailLogin.requestFocus();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    initLoginEmail(email,pass);
                }
            }
        });
        return view;
    }

    private void initLoginEmail(String email, String pass) {
        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener( getActivity() ,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    startActivity(new Intent(getActivity(),DashboardActivity.class));
                    getActivity().finish();

                    Toast.makeText(getActivity(),"You are logged in now", Toast.LENGTH_SHORT).show();
                } else {
//                    try {
//                        throw task.getException();
//                    } catch (FirebaseAuthInvalidUserException e){
//                        edtEmailLogin.setError("User does not exist or is no longer valid. Please login again");
//                        edtEmailLogin.requestFocus();
//                    } catch (FirebaseAuthInvalidCredentialsException e) {
//                        edtEmailLogin.setError("Invalid Credentials. Kindly , check and re enter");
//                        edtEmailLogin.requestFocus();
//                    } catch (Exception e){
//                        Log.e(TAG, e.getMessage());
//                        Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
//                    }
                    Toast.makeText(getActivity(),"Something went wrong !", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser() !=null){
            Toast.makeText(getActivity(),"Already Logged In", Toast.LENGTH_SHORT).show();

            // Start the Dashboard Activity

        } else {
            Toast.makeText(getActivity(),"You can login now!", Toast.LENGTH_SHORT).show();
        }
    }
}