package com.example.eleves.biblio_tp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class SpaceClient extends AppCompatActivity {
    String updateQuery;
    Button modifierClient, annulerAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_client);


        TextView textView = (TextView) findViewById(R.id.idClient);
        textView.setText(cliente.cli.getId_client());

        final EditText editText1 = (EditText) findViewById(R.id.prenomClient);
        editText1.setText(cliente.cli.getPrenom());
        final EditText editText2 = (EditText) findViewById(R.id.nomClient);
        editText2.setText(cliente.cli.getNom());
        final EditText editText3 = (EditText) findViewById(R.id.courrielClient);
        editText3.setText(cliente.cli.getCourriel());
        final EditText editText4 = (EditText) findViewById(R.id.dtNaissanceClient);
        editText4.setText(cliente.cli.getDtNaissance());
        final EditText editText5 = (EditText) findViewById(R.id.telClient);
        editText5.setText(cliente.cli.getTelephone());
        final EditText editText6 = (EditText) findViewById(R.id.adresseClient);
        editText6.setText(cliente.cli.getAdresse());

        //AutoCompleteTextView ac1 = (AutoCompleteTextView)findViewById(R.id.prenomClient);
        //AutoCompleteTextView ac2 = (AutoCompleteTextView)findViewById(R.id.nomClient);
        //ac1.setText(cli.getPrenom());

        modifierClient = (Button) findViewById(R.id.buttonModifierClient);
        annulerAction = (Button) findViewById(R.id.buttonAnnuler);
        annulerAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(SpaceClient.this, Accueil.class);
                startActivity(intent);
            }
        });

        modifierClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UPDATE client SET prenom = 'Diana1', nom = 'Soares1', courriel = 'dsoares"email.com1',
                // dtNaissance = '1991-06-27', telephone = '514-438111111', adresse = '11111 Berri' WHERE id_client = 20170001

                updateQuery = "UPDATE client SET prenom = '" + editText1.getText() + "', nom = '" + editText2.getText()
                        + "', courriel = '" + editText3.getText() + "', dtNaissance = '" + editText4.getText() + "', telephone = '" + editText5.getText()
                        + "', adresse = '" + editText6.getText() + "' WHERE id_client = " + cliente.cli.getId_client().toString();
            System.out.println(updateQuery);
                connectSQLUpdate();


                Intent intent = new Intent(SpaceClient.this, Accueil.class);
                startActivity(intent);

            }
        });

    }


    void connectSQLUpdate(){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        //
                        Class.forName("com.mysql.jdbc.Driver");
                        //
                        String url = "jdbc:mysql://10.0.2.2:3306/biblio";
                        Connection con = DriverManager.getConnection(
                                url, "root", "root");
                        //
                        con.setAutoCommit(false);
                        Statement stmt;
                        stmt = con.createStatement();

                        stmt.executeUpdate(updateQuery);
                        con.commit();

                        stmt.close();
                        con.close();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Update OK!", Toast.LENGTH_LONG).show();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Error connecting to DB",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
            thread.start();
        }



}