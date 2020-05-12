package com.example.autenticazione;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

public class ReservationsActivity extends AppCompatActivity {

    private ImageButton ibEditIncumbent;
    private ImageButton ibDeleteIncumbent;
    private ImageView ivDeleteRequest;
    private ImageView ivAcceptRequest;
    private ImageView ivEntrantCancelRequest;
    private ListView listEntrant;
    private ListView listIncumbent;
    private boolean status;

    private ImageButton ibBack;


    //a seconda dell'utente (se ha prenotato o ricevuto prenotazione, dovrebbero venire fuori 2 diverse activity (frame layout)//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);
        ibBack = (ImageButton)findViewById(R.id.ibBack);

    }

    public void goBackToMainActivity(View v) {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
    }

}