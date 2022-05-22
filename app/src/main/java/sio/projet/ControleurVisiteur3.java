package sio.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLData;

import plum.widget.ComboDialog;

public class ControleurVisiteur3 extends AppCompatActivity implements View.OnClickListener, ComboDialog.OnClickComboDialogListener {

    private ComboDialog comboSpecialite;
    private String value = "";
    private String item = "";
    private PorteOuverteSQLLite SQLData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visiteur3);

        // Gestion navbar
        getSupportActionBar().hide();
        ImageButton home = findViewById(R.id.home);
        ImageButton visiteur = findViewById(R.id.visiteur);
        ImageButton directeur = findViewById(R.id.directeur);

        home.setOnClickListener(this);
        visiteur.setOnClickListener(this);
        directeur.setOnClickListener(this);




        TextView specialite = findViewById(R.id.specialite);
        final CharSequence[] items = {"Commerce international","Commerce internet","Finance"};
        final CharSequence[] values = {"1","2","3"};

        comboSpecialite = new ComboDialog( "Choisir une spécialité",
                items,
                values,
                specialite,
                this );

        Button terminer = findViewById(R.id.terminer);
        terminer.setOnClickListener(this);

        comboSpecialite.setOnClickComboDialogListener(this);

        Log.i("Nom :",getIntent().getStringExtra("nom"));
        Log.i("Prenom :",getIntent().getStringExtra("prenom"));
        Log.i("Diplome :",getIntent().getStringExtra("diplome"));
        Log.i("Information :",getIntent().getStringExtra("information"));

        SQLData = new PorteOuverteSQLLite(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
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
            case R.id.terminer:
                if (!value.equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Inscription confirmée.",
                            Toast.LENGTH_SHORT);

                    Visiteur nouveau = new Visiteur();
                    nouveau.nom = getIntent().getStringExtra("nom");
                    nouveau.prenom = getIntent().getStringExtra("prenom");
                    nouveau.baccalaureat = getIntent().getStringExtra("diplome");
                    nouveau.etablissement = getIntent().getStringExtra("information");
//                    nouveau.tel = getIntent().getStringExtra("phone");
                    nouveau.specialite = item;
                    nouveau.avis = 0;

                    SQLData.putVisiteur(nouveau);

                    toast.show();

                    Intent retourAccueil = new Intent(this, ControleurAccueil.class);
                    startActivity(retourAccueil);
                    finish();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Merci de choisir une spécialisation.",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
        }
    }
    @Override
    public void onClickComboDialog(ComboDialog comboDialog) {
        value = (String) comboSpecialite.value( comboSpecialite.getIndexSelected());
        item = (String) comboSpecialite.item( comboSpecialite.getIndexSelected());
    }
}