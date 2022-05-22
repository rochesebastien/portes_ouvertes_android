package sio.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import plum.widget.ComboDialog;

public class ControleurVisiteur2 extends AppCompatActivity implements ComboDialog.OnClickComboDialogListener, View.OnClickListener {

    private ComboDialog comboEpreuve;
    private EditText informationBac;
    private String diplome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visiteur2);

        // Gestion navbar
        getSupportActionBar().hide();
        ImageButton home = findViewById(R.id.home);
        ImageButton visiteur = findViewById(R.id.visiteur);
        ImageButton directeur = findViewById(R.id.directeur);

        home.setOnClickListener(this);
        visiteur.setOnClickListener(this);
        directeur.setOnClickListener(this);


        // Combo dialog
        TextView epreuve = findViewById(R.id.epreuve);
        final CharSequence[] items = {"Bac général","Bac technologique","Bac professionnel", "Université", "Autre"};
        final CharSequence[] values = {"1","2","3","4","5"};

        comboEpreuve = new ComboDialog( "Diplôme d'origine",
                items,
                values,
                epreuve,
                this );

        comboEpreuve.setOnClickComboDialogListener(this);

        informationBac = findViewById(R.id.information);
        informationBac.setVisibility(View.GONE);

        Button suivant = findViewById(R.id.suivant);
        suivant.setOnClickListener(this);


    }

    @Override
    public void onClickComboDialog(ComboDialog comboDialog) {
        String value = (String) comboDialog.value( comboDialog.getIndexSelected());
        String item = (String) comboDialog.item( comboDialog.getIndexSelected());
        diplome = item;
        if(value.equals("5")){
            informationBac.setHint("Précisez...");
            informationBac.setVisibility(View.VISIBLE);
        } else{
            informationBac.setVisibility(View.VISIBLE);
            informationBac.setHint("Nom établissement...");
        }
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.home:
                Intent controleurAccueil = new Intent(this, ControleurAccueil.class);
                startActivity(controleurAccueil);
                finish();
                break;
            case R.id.directeur:
                Intent controleurDirecteur = new Intent(this, ControleurDirecteur1.class);
                startActivity(controleurDirecteur);
                finish();
                break;
            case R.id.visiteur:
                Intent controleurVisiteur = new Intent(this, ControleurVisiteur1.class);
                startActivity(controleurVisiteur);
                finish();
                break;
            case R.id.suivant:
                if(informationBac.getText().toString().equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Veuillez compléter le formulaire.",
                            Toast.LENGTH_SHORT);
                    toast.show();
                } else{
                    Intent controleurVisiteur3 = new Intent(this, ControleurVisiteur3.class);
                    controleurVisiteur3.putExtra("nom",getIntent().getStringExtra("nom"));
                    controleurVisiteur3.putExtra("prenom", getIntent().getStringExtra("prenom"));
                    controleurVisiteur3.putExtra("diplome", diplome);
                    controleurVisiteur3.putExtra("information", informationBac.getText().toString());
                    controleurVisiteur3.putExtra("phone", getIntent().getStringExtra("phone"));
                    startActivity(controleurVisiteur3);
                    finish();
                }
                break;
        }
    }
}