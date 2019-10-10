package com.example.comp90018;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity{

    private Button btnSignIn;
    private TextView txtSignUp;
    private EditText edtEmail,edtPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Sign In");
        btnSignIn=findViewById(R.id.btnSignin);
        edtEmail=findViewById(R.id.edtEmail);
        edtPassword=findViewById(R.id.edtPassword);
        txtSignUp=findViewById(R.id.txtSignup);

        mAuth = FirebaseAuth.getInstance();


        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);

            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hide();
                if (edtEmail.getText().toString().equals("")||edtPassword.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Email or Password can't be emoty", Toast.LENGTH_LONG).show();

                }else{
//                    Toast.makeText(MainActivity.this,"log in", Toast.LENGTH_LONG).show();
//                    ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
//                    progressDialog.setMessage("Log in");
//                    progressDialog.show();
                    signIn();
//                    progressDialog.dismiss();

                }

            }
        });
        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Hide();
                if(i==KeyEvent.KEYCODE_ENTER && keyEvent.getAction()==KeyEvent.ACTION_DOWN){
                    if (edtEmail.getText().toString().equals("")||edtPassword.getText().toString().equals("")){
                        Toast.makeText(MainActivity.this,"Email or Password can't be emoty", Toast.LENGTH_LONG).show();

                    }else{
                        signIn();
                    }
                }
                return false;
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
        if (currentUser!=null){
            //transition to next

            TransitionToMainPage();

        }
    }
    private void signIn(){
        mAuth.signInWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            TransitionToMainPage();

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(MainActivity.this,"The account is not exist",Toast.LENGTH_LONG).show();

                        }

                        // ...
                    }
                });

    }
    private void TransitionToMainPage(){
        Intent intent=new Intent(MainActivity.this,MainPageActivity.class);
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
