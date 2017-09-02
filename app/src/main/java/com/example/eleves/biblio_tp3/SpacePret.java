package com.example.eleves.biblio_tp3;
/**
 * Created by Diana on 7/31/2017.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//  ----------------------------- Classe LivreExemplaire  -----------------------------
class LivreExemplaire {
    static public String livreEx;
}

//  ----------------------------- Classe SpacePret  -----------------------------
public class SpacePret extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_pret);
        setTitle("Livre: (" + Livros.livre.getId_titre()+")");

        TextView titre = (TextView) findViewById(R.id.titreLivre);
       TextView donnees = (TextView) findViewById(R.id.donnesLivre);
       titre.setText(Livros.livre.getId_titre().toString());
       LivreExemplaire.livreEx = Livros.livre.getId_titre().toString();

       donnees.setText(Livros.livre.toString());

       Button pretLivre = (Button) findViewById(R.id.pretButton);
       pretLivre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent = new Intent(SpacePret.this, SpaceDisponibiliteLivre.class);
                startActivity(intent);
            }
        });

    }



}
