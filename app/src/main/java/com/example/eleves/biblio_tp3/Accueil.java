package com.example.eleves.biblio_tp3;
/**
 * Created by Diana on 7/31/2017.
 */

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.eleves.biblio_tp3.Livros.livres;
import static com.example.eleves.biblio_tp3.R.id.parent;

//  ----------------------------- Classe cliente-----------------------------
class cliente{
    static public  List<Client> client = new ArrayList<Client>();
    static public  Client cli = new Client();
}
//  ----------------------------- Classe Livros  -----------------------------
class Livros {
   static public  List<Livre> livres = new ArrayList<Livre>();
    static public  Livre livre;
}
//  ----------------------------- Classe  Accueil -----------------------------
public class Accueil extends AppCompatActivity {

    final String valeurChercher = " ";
    final String spinnerValue = "hello";
    String query;
    String info;
    int positionSpinner = 0;
    EditText text;
    Button client;
    Button recherche;
    ArrayAdapter<Livre> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        setTitle("Bibliothèque George-Étienne Cartier");

        connectMySQLClient();
        client = (Button)findViewById(R.id.spaceClient);
        recherche = (Button)findViewById(R.id.rechercherLivre);

        final ArrayList<Livre> listLivre = new ArrayList<>();

        //Spinner
        final Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        final ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, R.array.spinner_recherche, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapterSpinner);





        adapter = new ArrayAdapter<Livre>(this, android.R.layout.simple_list_item_1, livres) ;
        final ListView lv = (ListView) findViewById(R.id.listeAcceuil);

        adapter = new ArrayAdapter<Livre>(this, android.R.layout.simple_list_item_2, android.R.id.text1, livres)
        {
            public Livre getItem(int position){
                return livres.get(position);
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(livres.get(position).getId_titre());
                text2.setText(livres.get(position).getId_auteur());

                return view;
            }
        };
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Livros.livre = (Livre) lv.getItemAtPosition(i);
                Intent intent2 = new Intent(Accueil.this, SpacePret.class);


                startActivity(intent2);
            }
        });


        client.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                connectMySQLClient();
                Intent intent = new Intent(Accueil.this, SpaceClient.class);
                startActivity(intent);
            }
        });

        // List<Livre> livresRecherche;


        text = (EditText) findViewById(R.id.textFieldAccueil);
        // final List<Livre> finalLivresRecherche = livresRecherche;






        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (position == 1){
                  positionSpinner = 1;
                }
                else if (position ==2){
                    positionSpinner = 2;
                }
                else if (position ==3){
                    positionSpinner = 2;
                }
                else if (position ==4){
                    positionSpinner = 4;
                }
                else {
                    positionSpinner = 0;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here

            }

        });

        recherche.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if (positionSpinner == 1){
                    info = text.getText().toString();
                    query = "SELECT * FROM recherche WHERE titre LIKE '%" + info + "%'";

                    System.out.println("query: "+query);
                    System.out.println("info: "+info);

                }
                else if (positionSpinner ==2){
                    info = text.getText().toString();
                    query = "SELECT * FROM recherche WHERE auteur LIKE '%" + info + "%'";

                    System.out.println("query"+query);
                    System.out.println("info: "+info);
                }
                else if (positionSpinner ==3){
                    info = text.getText().toString();
                    query = "SELECT * FROM recherche WHERE genre LIKE '%" + info + "%'";

                    System.out.println("query"+query);
                    System.out.println("info: "+info);
                }
                else if (positionSpinner ==4){
                    info = text.getText().toString();
                    query = "SELECT * FROM recherche WHERE langue LIKE '%" + info + "%'";

                    System.out.println("query"+query);
                    System.out.println("info: "+info);
                }
                else {
                    query = "SELECT * FROM recherche ";

                }


                connectMySQL();
                adapter.clear();
            }
        });

        // listView.setAdapter(adapter);

    }




    void connectMySQL(){

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
                    System.out.println("URL: " + url);
                    System.out.println("Connection: " + con);
                    //
                    Statement stmt;
                    stmt = con.createStatement();


                    Livre  lv;



                    ResultSet rs = stmt.executeQuery(query);

                    while(rs.next()){

                        lv = new Livre();
                        lv.setISBN(rs.getString("isbn"));
                        lv.setRare(rs.getString("rare"));
                        lv.setId_auteur(rs.getString("auteur"));
                        lv.setId_genre(rs.getString("genre"));
                        lv.setId_editeur(rs.getString("editeur"));
                        lv.setId_titre(rs.getString("titre"));
                        lv.setLangue(rs.getString("langue"));

                        livres.add(lv);


                    }

                    rs.close();


                    stmt.close();
                    con.close();
                    System.out.println("Connection good");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            /*Toast.makeText(getApplicationContext(), "Connection good" ,
                                    Toast.LENGTH_LONG).show();*/

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            /*Toast.makeText(getApplicationContext(), "Error connecting to DB",
                                    Toast.LENGTH_SHORT).show();*/
                        }
                    });
                }
            }
        });
        thread.start();
    }

    void connectMySQLClient(){

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
                    Statement stmt;
                    stmt = con.createStatement();


                    //Client  cl = new Client();



                    ResultSet rs = stmt.executeQuery("SELECT * FROM client WHERE id_client = 20170001 ");

                    while(rs.next()){

                        cliente.cli = new Client();
                        cliente.cli.setId_client(rs.getString("id_client"));
                        cliente.cli.setPrenom(rs.getString("prenom"));
                        cliente.cli.setNom(rs.getString("nom"));
                        cliente.cli.setAdresse(rs.getString("adresse"));
                        cliente.cli.setTelephone(rs.getString("telephone"));
                        cliente.cli.setCourriel(rs.getString("courriel"));
                        cliente.cli.setDtNaissance(rs.getString("dtNaissance"));

                        cliente.client.add(cliente.cli);

                    }


                    rs.close();


                    stmt.close();
                    con.close();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(getApplicationContext(), "Connection good"+ cliente.cli, Toast.LENGTH_LONG).show();
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
