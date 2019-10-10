package com.example.comp90018;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    private Button btnSignup;
    private EditText edtEmailSU,edtPasswordSU;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Sign Up");
        btnSignup=findViewById(R.id.btnSignUp);
        edtEmailSU=findViewById(R.id.edtEmailSign);
        edtPasswordSU=findViewById(R.id.edtPasswordSign);
        mAuth = FirebaseAuth.getInstance();
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hide();
                if (edtEmailSU.getText().toString().equals("")||edtPasswordSU.getText().toString().equals("")){
                    Toast.makeText(SignUpActivity.this,"Email or Password can't be empty",Toast.LENGTH_LONG).show();
                }else {
                    SignUp();
                }
            }
        });
        edtPasswordSU.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Hide();
                if(i==KeyEvent.KEYCODE_ENTER && keyEvent.getAction()==KeyEvent.ACTION_DOWN){
                    if (edtEmailSU.getText().toString().equals("")||edtPasswordSU.getText().toString().equals("")){
                        Toast.makeText(SignUpActivity.this,"Email or Password can't be empty", Toast.LENGTH_LONG).show();

                    }else{
                        SignUp();
                    }
                }
                return false;
            }
        });
    }
    private void SignUp(){
        mAuth.createUserWithEmailAndPassword(edtEmailSU.getText().toString(), edtPasswordSU.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            TransitionToMainPage();

                        } else {
                            // If sign in fails, display a message to the user.
                            String errorMsg = task.getException().getMessage();
                            Toast.makeText(SignUpActivity.this, errorMsg, Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });
    }
    private void TransitionToMainPage(){
        Intent intent=new Intent(SignUpActivity.this,MainPageActivity.class);
        startActivity(intent);
        finish();
    }
    public void HiddenSoftKeyboard(View view){
        try {

            Hide();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void Hide(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
}
