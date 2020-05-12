//qui ci sarà la mappa

package com.example.autenticazione;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MapActivity extends AppCompatActivity {

    private ImageButton ibBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initUI();

    }

    public void initUI(){
        ibBack=findViewById(R.id.ibBack);
    }

    public void goBackToMainActivity (View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
