package com.example.eleves.biblio_tp3;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    Button btPassword;
    EditText user, password;
    int counter = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Titre de l'activite
        setTitle("Bibliothèque George-Étienne Cartier");

        btPassword = (Button)findViewById(R.id.btPassword);
        user = (EditText)findViewById(R.id.editText2);
        password = (EditText)findViewById(R.id.editText3);

        btPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(user.getText().toString().equals("a") && password.getText().toString().equals("a")){

                    Intent intent = new Intent(MainActivity.this, Accueil.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(getApplicationContext(), "Mot de passe ou Utilisateur incorrects",Toast.LENGTH_SHORT).show();
                    counter--;


                    if (counter == 0) {
                        btPassword.setEnabled(false);
                    }
                }
            }
        });

    }




}
