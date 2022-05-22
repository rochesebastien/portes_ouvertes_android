package sio.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

import java.util.ArrayList;

public class ControleurVisiteur1 extends AppCompatActivity implements View.OnClickListener {

    private CountryCodePicker ccp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visiteur1);

        // Gestion navbar
        getSupportActionBar().hide();
        ImageButton home = findViewById(R.id.home);
        ImageButton visiteur = findViewById(R.id.visiteur);
        ImageButton directeur = findViewById(R.id.directeur);

        home.setOnClickListener(this);
        visiteur.setOnClickListener(this);
        directeur.setOnClickListener(this);

        Button suivant = findViewById(R.id.suivant);
        suivant.setOnClickListener(this);

        ccp = (CountryCodePicker) findViewById(R.id.country);
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
                TextView nom = findViewById(R.id.nom);
                TextView prenom = findViewById(R.id.prenom);
                TextView phone = findViewById(R.id.phone);

                boolean confirmation = true;

                if(nom.getText().toString().equals("") && prenom.getText().toString().equals("")){
                    Toast erreur = Toast.makeText(this, "Les champs doivent être complétés.", Toast.LENGTH_SHORT);
                    erreur.show();
                    confirmation = false;
                } else if(nom.getText().toString().equals("")){
                    Toast erreurNom = Toast.makeText(this, "Le nom doit être complété.", Toast.LENGTH_SHORT);
                    erreurNom.show();
                    confirmation = false;
                } else if(prenom.getText().toString().equals("")){
                    Toast erreurPrenom = Toast.makeText(this, "Le prénom doit être complété.", Toast.LENGTH_SHORT);
                    erreurPrenom.show();
                    confirmation = false;
                }

                // Verfication telephone et pays
                if(ccp.getSelectedCountryCode() == ""){
                    Toast erreurPrenom = Toast.makeText(this, "Un pays doit être selectionné.", Toast.LENGTH_SHORT);
                    erreurPrenom.show();
                    confirmation = false;
                }

                if(ccp.getSelectedCountryCode() == ""){
                    Toast erreurPays = Toast.makeText(this, "Un pays doit être selectionné.", Toast.LENGTH_SHORT);
                    erreurPays.show();
                    confirmation = false;
                }

                if(phone.getText().toString().equals("")){
                    Toast erreurPhone = Toast.makeText(this, "Le téléphone est obligatoire.", Toast.LENGTH_SHORT);
                    erreurPhone.show();
                    confirmation = false;
                }

                if(confirmation){
                    Intent controleurVisiteur2 = new Intent(this, ControleurVisiteur2.class);
                    controleurVisiteur2.putExtra("nom", nom.getText().toString());
                    controleurVisiteur2.putExtra("prenom", prenom.getText().toString());
                    controleurVisiteur2.putExtra("phone", phone.getText().toString());
                    startActivity(controleurVisiteur2);
                    finish();
                }
                break;
        }

    }
}