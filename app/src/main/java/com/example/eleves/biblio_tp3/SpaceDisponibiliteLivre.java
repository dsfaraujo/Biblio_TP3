package com.example.eleves.biblio_tp3;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static com.example.eleves.biblio_tp3.Livros.livres;


public class SpaceDisponibiliteLivre extends AppCompatActivity {
    ArrayAdapter<LivreExPret> adapter;
    List<LivreExPret> livreExemplaire = new ArrayList<>();
   LivreExPret numExemplaire = new LivreExPret();
    Button fairePret;


    public static Date date;
    public static Date dateFin;
    public static SimpleDateFormat formatDate;
    public static int joursPret = (86400 * 14 * 1000);
    public static int joursExtension = (86400 * 14 * 1000);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_disponibilite_livre);
        chercherExemplaires();

        TextView titreLivreEx = (TextView) findViewById(R.id.titreLivreEx);
        titreLivreEx.setText("Séléctionner l'exemplaire de "+ LivreExemplaire.livreEx +" que vous voulez prêter");
        date = new Date(System.currentTimeMillis());
        formatDate = new SimpleDateFormat("yyyy-MM-dd");
        dateFin = new Date(System.currentTimeMillis() + joursPret );
        fairePret = (Button)findViewById(R.id.buttonConfirmerPret);






        adapter = new ArrayAdapter<LivreExPret>(this, android.R.layout.simple_list_item_1, livreExemplaire) ;
        final ListView lv = (ListView) findViewById(R.id.listExemplaires);

        adapter = new ArrayAdapter<LivreExPret>(this, android.R.layout.simple_list_item_2, android.R.id.text1, livreExemplaire)
        {

            public LivreExPret getItem(int position){
                return livreExemplaire.get(position);
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText("Livre: "+livreExemplaire.get(position).getTitre());
                text2.setText("Exemplaire: "+ livreExemplaire.get(position).getNum_exemplaire());

                if (livreExemplaire.get(position).getTitre() == null){
                    fairePret.setText("Réséerver Livre");

                }
                else{
                    fairePret.setText("Confirmer Prêt");

                }




                return view;
            }
        };
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              numExemplaire = (LivreExPret) lv.getItemAtPosition(i);
                view.setSelected(true);
                for (int j = 0; j < adapterView.getChildCount(); j++)
                    adapterView.getChildAt(j).setBackgroundColor(Color.TRANSPARENT);

                // change the background color of the selected element
                view.setBackgroundColor(Color.LTGRAY);
               //Intent intent2 = new Intent(Accueil.this, SpacePret.class);
                //startActivity(intent2);
            }
        });

       fairePret.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dateFin = new Date(System.currentTimeMillis() + joursPret);
                if (livreExemplaire == null){
                    fairePret.setText("Réséerver Livre");

                }
                else{
                    defineIdLocation();
                    ajouterLocation();
                    Toast.makeText(getApplicationContext(), "Action effectué avec success" ,
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SpaceDisponibiliteLivre.this, Accueil.class);
                    startActivity(intent);
                }

                //Intent intent = new Intent(Accueil.this, SpaceClient.class);
                //startActivity(intent);
            }
        });


    }

    void chercherExemplaires(){

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


                   LivreExPret lv;



                    ResultSet rs = stmt.executeQuery("SELECT * FROM livrelocation WHERE titre = '" + LivreExemplaire.livreEx +
                            "'");

                    while(rs.next()){

                        lv = new LivreExPret();
                        lv.setIsbn(rs.getString("isbn"));
                        lv.setTitre(rs.getString("titre"));
                        lv.setAdresse_ex(rs.getString("adresse_ex"));
                        lv.setNum_exemplaire(rs.getString("num_exemplaire"));


                        livreExemplaire.add(lv);


                    }

                    rs.close();


                    stmt.close();
                    con.close();
                    System.out.println("Connection good");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            /*Toast.makeText(getApplicationContext(), "Connection good" + livreExemplaire ,
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



    private int defineIdLocation() {
        int idLocation = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/biblio";
            Connection con = DriverManager.getConnection(url, "root", "root");

            Statement stmt;
            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT DISTINCT id_location FROM location ORDER BY id_location ASC");

            while(rs.next()){
                if(Integer.parseInt(rs.getString(1)) == idLocation) {
                    idLocation++;
                }
            }
            rs.close();
            stmt.close();
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return idLocation;
    }


    public void ajouterLocation() {
        String queryAjouterClient = "INSERT INTO location (id_location, dtDebut, id_client, num_ex, dtFin) " +
                "VALUES ('" + defineIdLocation() +"', '"+ date.toString() +"', '"+ "20170001" +"', "
                + "'"+ numExemplaire.getNum_exemplaire()+"', '"+ dateFin.toString() +"')";
        try{
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/biblio";
            Connection con = DriverManager.getConnection(url, "root", "root");

            //====================================
            System.out.println("URL: " + url);
            System.out.println("Connection: " + con);
            //====================================

            con.setAutoCommit(false);
            Statement stmt;
            stmt = con.createStatement();
            stmt.executeUpdate( queryAjouterClient );
            System.out.print(queryAjouterClient);
            stmt.close();
            con.commit();
            con.close();
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
