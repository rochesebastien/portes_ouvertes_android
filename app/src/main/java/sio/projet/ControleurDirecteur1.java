package sio.projet;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;


import androidx.appcompat.app.AppCompatActivity;

import plum.widget.ComboDialog;

public class ControleurDirecteur1 extends AppCompatActivity implements AdapterView.OnItemClickListener, ComboDialog.OnClickComboDialogListener, AdapterView.OnItemSelectedListener, View.OnClickListener {
    private ListView lv;
    private VisiteurArrayAdapter arrayAdapterVisiteur;
    private PorteOuverteSQLLite SQLData;
    private ComboDialog comboAvis;
    private String item = "";
    private String valeur = "";
    private Visiteur leVisiteur;
    private Spinner spinner;
    private ArrayList<Visiteur> lesVisiteurs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.directeur1);

        // Gestion navbar
        getSupportActionBar().hide();
        ImageButton home = findViewById(R.id.home);
        ImageButton visiteur = findViewById(R.id.visiteur);
        ImageButton directeur = findViewById(R.id.directeur);

        
        home.setOnClickListener(this);
        visiteur.setOnClickListener(this);
        directeur.setOnClickListener(this);

        SQLData = new PorteOuverteSQLLite(this);
        lesVisiteurs = SQLData.getListeVisiteur();
        arrayAdapterVisiteur = new VisiteurArrayAdapter(this, lesVisiteurs);

        spinner = findViewById(R.id.spinner);
        //String size = spinner.getSelectedItem().toString();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.selection, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        // setAdapter attache l'adaptateur à la vue
        lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(arrayAdapterVisiteur);
        lv.setOnItemClickListener(this);

        final CharSequence[] items = {"Neutre","Favorable","Défavorable"};
        final CharSequence[] values = {"0","1","2"};

         comboAvis = new ComboDialog( "Choisir un avis",
                items,
                values,
                null,
                this );

         comboAvis.setOnClickComboDialogListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        comboAvis.show();
        leVisiteur = (Visiteur) adapterView.getItemAtPosition(i);

    }

    @Override
    public void onClickComboDialog(ComboDialog comboDialog) {
        valeur = (String) comboDialog.value( comboDialog.getIndexSelected());
        item = (String) comboDialog.item( comboDialog.getIndexSelected());

        leVisiteur.avis = Integer.parseInt(valeur);
        SQLData.updateVisiteur(leVisiteur.id,leVisiteur.avis);
        arrayAdapterVisiteur.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String filtre = spinner.getSelectedItem().toString();

        Log.i("Selected spinner !", filtre);
        SQLData = new PorteOuverteSQLLite(this);
        lesVisiteurs = SQLData.getListeVisiteur();

       ArrayList<Visiteur> visiteurTemp = new ArrayList<>();

        switch(filtre){
            case "➤ Tous":
                visiteurTemp = lesVisiteurs;
                break;
            case "⚬ Bac général":

                for (Visiteur v : lesVisiteurs) {
                    if(v.baccalaureat.equals("Bac général")){
                        visiteurTemp.add(v);
                    }
                }
                break;
            case "⚬ Bac technologique":
                for (Visiteur v : lesVisiteurs) {
                    if(v.baccalaureat.equals("Bac technologique")){
                        visiteurTemp.add(v);
                    }
                }
                break;
            case "⚬ Bac professionnel":
                for (Visiteur v : lesVisiteurs) {
                    if(v.baccalaureat.equals("Bac professionnel")){
                        visiteurTemp.add(v);
                    }
                }
                break;
            case "⚬ Université":
                for (Visiteur v : lesVisiteurs) {
                    if(v.baccalaureat.equals("Université")){
                        visiteurTemp.add(v);
                    }
                }
                break;
            case "⚬ Autre":
                for (Visiteur v : lesVisiteurs) {
                    if(v.baccalaureat.equals("Autre")){
                        visiteurTemp.add(v);
                    }
                }
                break;
            case "⚬ Commerce internationnal":
                for (Visiteur v : lesVisiteurs) {
                    if(v.specialite.equals("Commerce international")){
                        visiteurTemp.add(v);
                    }
                }
                break;
            case "⚬ Commerce internet":
                for (Visiteur v : lesVisiteurs) {
                    if(v.specialite.equals("Commerce internet")){
                        visiteurTemp.add(v);
                    }
                }
                break;
            case "⚬ Finance":
                for (Visiteur v : lesVisiteurs) {
                    if(v.specialite.equals("Finance")){
                        visiteurTemp.add(v);
                    }
                }
                break;
            default:
                visiteurTemp = lesVisiteurs;

        }

        lesVisiteurs = visiteurTemp;

        for(Visiteur v : lesVisiteurs){
            Log.i("sortie", v.nom);
        }
        arrayAdapterVisiteur = new VisiteurArrayAdapter(this, lesVisiteurs);
        lv.setAdapter(arrayAdapterVisiteur);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.home:
                Intent controleurAccueil = new Intent(this, ControleurAccueil.class);
                startActivity(controleurAccueil);
                break;
            case R.id.directeur:
                Intent controleurDirecteur = new Intent(this, ControleurDirecteur1.class);
                startActivity(controleurDirecteur);

                break;
            case R.id.visiteur:
                Intent controleurVisiteur = new Intent(this, ControleurVisiteur1.class);
                startActivity(controleurVisiteur);
                break;
        }
        finish();
    }
}