package com.example.autenticazione;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    EditText nome;
    EditText email;
    EditText password;
    EditText confermaPassword;

    //Firebase
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initUI();

    }
    public void initUI(){
        //ciao a tutti ok
         nome = (EditText) findViewById(R.id.et_nome);
         email = (EditText) findViewById(R.id.et_email);
         password = (EditText) findViewById(R.id.et_password);
         confermaPassword = (EditText) findViewById(R.id.et_confermaPassword);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
}
    public void registrati (View view) {
        Log.i(TAG, "Cliccato sul pulsante registrati");
        String nomeUtente = nome.getText().toString();
        String emailUtente = email.getText().toString();
        String passwordUtente = password.getText().toString();
        String confermaPasswordUtente = confermaPassword.getText().toString();


        Log.i(TAG, nomeUtente);
        Log.i(TAG, emailUtente);
        Log.i(TAG, passwordUtente);
        Log.i(TAG, confermaPasswordUtente);


        //controllo se i campi sono corretti
        if (!nomeValido(nomeUtente))
            Toast.makeText(this, "Inserire un nome di almeno 3 caratteri", Toast.LENGTH_SHORT).show();
        else if (!emailValida(emailUtente))
            Toast.makeText(this, "Inserire una e-mail corretta", Toast.LENGTH_SHORT).show();
        else if (!passwordValida(passwordUtente, confermaPasswordUtente))
            Toast.makeText(this, "Occhio alle password", Toast.LENGTH_SHORT).show();
        else
            createFirebaseUser(emailUtente,passwordUtente,nomeUtente);

    }


    private void createFirebaseUser(String email, String password, final String nome) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.i(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        setNome(nome);


                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.i(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        //updateUI(null);
                    }

                }
            });
    }

    private void setNome(String nome) {
        FirebaseUser user= mAuth.getCurrentUser();

        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                .setDisplayName(nome)
                .build();

        user.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                    Log.i(TAG, "completata con successo");
                else
                    Log.i(TAG, "errore");
            }
        });
    }

    private void updateUI(FirebaseUser user) {

    }


    public void login(View view) {
        Intent intent =new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private boolean nomeValido(String nome){
        if (nome.length()>3)
            return true;
        else
            return false;
    }

    private boolean emailValida(String email){
        if (email.contains("@") && email.contains("."))
            return true;
        else
            return false;
    }

    private boolean passwordValida(String password1, String password2){
        if (password1.compareTo(password2) == 0 && password1.length()>5)
            return true;
        else
            return false;
    }
}

