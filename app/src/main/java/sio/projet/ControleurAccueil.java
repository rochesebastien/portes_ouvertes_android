package sio.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ControleurAccueil extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);
        getSupportActionBar().hide();

        // Configuration des boutons ToolBar
        ImageButton home = findViewById(R.id.home);
        ImageButton visiteur = findViewById(R.id.visiteur);
        ImageButton directeur = findViewById(R.id.directeur);

        home.setOnClickListener(this);
        visiteur.setOnClickListener(this);
        directeur.setOnClickListener(this);



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
