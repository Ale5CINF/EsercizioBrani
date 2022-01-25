package com.zproale.spotilite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Istanzo la classe che gestisce i brani
        GestioneBrani gest = new GestioneBrani();

        //Ottengo oggetto dove sta autore  il genere e il titolo
        EditText autore = (EditText) findViewById(R.id.inputAutore);
        EditText titolo = (EditText) findViewById(R.id.inputTitolo);

        Button add = (Button)findViewById(R.id.bottoneADD);
        Button find = (Button)findViewById(R.id.bottoneFIND);
        Button lista = (Button)findViewById(R.id.buttonLista);
        Spinner generiSpinnder = (Spinner) findViewById(R.id.spinnerGenere);

        String[] generi = {
                "Rap",
                "Trap",
                "Pop"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, generi);
        generiSpinnder.setAdapter(adapter);

        //Listner button add
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Controllo se i campi sono vuoti
                if(!gest.isEmptyString(titolo.getText().toString()) && !gest.isEmptyString(autore.getText().toString())) {
                    //Aggiungo il brano sul arraylist
                    Brano brtoadd = new Brano(titolo.getText().toString(), autore.getText().toString(), autore.getText().toString());
                    if(gest.addBrano(brtoadd)) {
                        //Creo un toast con avvenuta creazione del brano
                        Toast.makeText(getApplicationContext(), titolo.getText().toString().trim() + " aggiunto con successo", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //Input di aggiunta brano sono vuoti
                    Toast.makeText(getApplicationContext(), "input vuoti!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Ascolto il bottone
        find.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Controllo se le stringe sono vuote
                if(!gest.isEmptyString(titolo.getText().toString()) && !gest.isEmptyString(autore.getText().toString())) {
                    //Richiamo la funzione che va a cercare il brano
                    try {
                        JSONObject ciao = gest.findBrano(titolo.getText().toString().trim(), autore.getText().toString().trim());
                        //Controllo se lo stato della ricerca è true
                        if(ciao.getBoolean("status")){
                            //Printo il json con un toast
                            Toast.makeText(getApplicationContext(), ciao.toString(), Toast.LENGTH_SHORT).show();
                        } else {
                            //Nessun brano trovato
                            Toast.makeText(getApplicationContext(), "Brano non trovato!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        //Errore nella funziona di ricerca brano
                        e.printStackTrace();
                    }
                } else {
                    //Campi vuoti della ricerca
                    Toast.makeText(getApplicationContext(), "Campi di ricerca vuoti!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Tasto lista brani
        lista.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent listaInt = new Intent(MainActivity.this, ListaBrani.class);
                String prova = gest.listaSong();
                listaInt.putExtra("data", prova);
                MainActivity.this.startActivity(listaInt);
            }
        });
    }
}