package sio.projet;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import plum.widget.ComboDialog;


public class VisiteurArrayAdapter extends ArrayAdapter<Visiteur> {
    private PorteOuverteSQLLite SQLData;

    public VisiteurArrayAdapter(Context context, ArrayList<Visiteur> visiteurs) {
        super(context, 0, visiteurs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Visiteur visiteur = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_visiteur,
                    parent,
                    false);
        }

        // Lookup view for data population
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageAvis);
        TextView txtNom = (TextView) convertView.findViewById(R.id.NomVisiteur);
        TextView txtPrénom = (TextView) convertView.findViewById(R.id.PrenomVisiteur);
        TextView txtBac = (TextView) convertView.findViewById(R.id.bac);
        TextView txtEtablissement = (TextView) convertView.findViewById(R.id.etablissement);
        TextView txtSpecialite = (TextView) convertView.findViewById(R.id.specialite);
        TextView txtTel = (TextView) convertView.findViewById(R.id.tel);


        // Populate the data into the template view using the data object

            switch (visiteur.avis){
                case 0:
                    imageView.setImageResource(R.mipmap.neutre1);
                    break;
                case 1:
                    imageView.setImageResource(R.mipmap.favorable1);
                    break;
                case 2:
                    imageView.setImageResource(R.mipmap.defavorable1);
                    break;
                default:
                    imageView.setImageResource(R.mipmap.neutre1);
            }
            txtNom.setText("Nom : "+visiteur.nom);
            txtPrénom.setText("Prénom : "+visiteur.prenom);
            txtBac.setText("Diplome : "+visiteur.baccalaureat);
            txtEtablissement.setText("Etablissement : "+visiteur.etablissement);
            txtSpecialite.setText("Spécialité : "+visiteur.specialite);
            txtTel.setText("Spécialité : "+visiteur.tel);

        // Return the completed view to render on screen
        return convertView;
    }
}
