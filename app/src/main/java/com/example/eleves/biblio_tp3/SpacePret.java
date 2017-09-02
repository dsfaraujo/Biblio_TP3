package com.example.eleves.biblio_tp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

class LivreExemplaire {
    static public String livreEx;
}

public class SpacePret extends AppCompatActivity {
    //List<Livre> livres = new ArrayList<Livre>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_pret);
        //Intent intent = getIntent();
        //Livre livre = (Livre) intent.getSerializableExtra("Livre");

        setTitle("Livre: (" + Livros.livre.getId_titre()+")");

        TextView titre = (TextView) findViewById(R.id.titreLivre);
       TextView donnees = (TextView) findViewById(R.id.donnesLivre);
       titre.setText(Livros.livre.getId_titre().toString());
       LivreExemplaire.livreEx = Livros.livre.getId_titre().toString();



       // titre.setText("teste");
       donnees.setText(Livros.livre.toString());

       Button pretLivre = (Button) findViewById(R.id.pretButton);
       pretLivre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //defineIdLocation();
               // ajouterLocation();


               Intent intent = new Intent(SpacePret.this, SpaceDisponibiliteLivre.class);
                startActivity(intent);
            }
        });

    }



}
