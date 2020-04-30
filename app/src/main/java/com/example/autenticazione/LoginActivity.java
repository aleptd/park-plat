package com.example.autenticazione;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG ="LoginActivity";
    private FirebaseAuth mAuth;

    private EditText username;
    private EditText password;

    //Google sign-in
    SignInButton button;
    GoogleSignInClient googleSignInClient;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {

        if (currentUser != null) {

            // Name, email address, and profile photo Url
            String name = currentUser.getDisplayName();
            Log.i(TAG, "nome" + name);

            String email = currentUser.getEmail();
            Log.i(TAG, "email" + email);

            Uri photoUrl = currentUser.getPhotoUrl();


            // Check if user's email is verified
            boolean emailVerified = currentUser.isEmailVerified();
            Log.i(TAG, "email verificata" + emailVerified);

            // The user's ID, unique to the Firebase project.
            //FirebaseUser.getIdToken();
            String uid = currentUser.getUid();
            Log.i(TAG, "uid" + uid);
            Log.i(TAG, "connesso");

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUI();
    }

    private void initUI() {
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        username = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_password);

        // Configure Google Sign In
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        
        googleSignInClient= GoogleSignIn.getClient(this, options);
        button.setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View v) {
                signin();
            }
        });


    }

    private void signin() {
    }

    public void registrati(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);

    }


    public void login(View view) {
                String emailUtente = username.getText().toString();
                String passwordUtente = password.getText().toString();


                mAuth.signInWithEmailAndPassword(emailUtente, passwordUtente)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.i(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.i(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }

                            }
                        });
            }
        }




