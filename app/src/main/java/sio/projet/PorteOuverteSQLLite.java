package sio.projet;
import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PorteOuverteSQLLite extends SQLiteOpenHelper{
    //--- Base de données

    private static String DATABASE_NAME = "PorteOuverte.db";
    private static int DATABASE_VERSION = 8;

    SQLiteDatabase db = null;
    //--- Table Visiteur

    private static String TABLE_VISITEUR = "visiteur";

    private static String COLUMN_ID = "id";
    private static String COLUMN_NOM = "nom";
    private static String COLUMN_PRENOM = "prenom";
    private static String COLUMN_TEL = "tel";
    private static String COLUMN_BACCALAUREAT = "baccalaureat";
    private static String COLUMN_ETABLISSEMENT = "etablissement";
    private static String COLUMN_SPECIALITE = "specialite";
    private static String COLUMN_AVIS = "avis";

    private static String COLUMNS_DEFINITION =
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NOM + " TEXT,"
                    + COLUMN_PRENOM + " TEXT,"
                    + COLUMN_TEL + " TEXT,"
                    + COLUMN_BACCALAUREAT + " TEXT,"
                    + COLUMN_ETABLISSEMENT + " TEXT,"
                    + COLUMN_SPECIALITE + " TEXT,"
                    + COLUMN_AVIS + " INTEGER";


    /*
     * constructeur : création ou déclaration de la base de données
     *
     */
    public PorteOuverteSQLLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
     * Appelé lorsque la base est créée pour la première fois
     *
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //--- create table Visiteur ---
        String sqlcreate = "create table " + TABLE_VISITEUR
                + " (" + COLUMNS_DEFINITION + ");";
        db.execSQL (sqlcreate);

        this.db=db;

        /*String[] tNom = {"Pasqualini", "Bogusz", "Techer", "Bourgeois", "Tournie"};
        String[] tPrenom = {"Claude", "Thierry", "Charles", "Agnes", "Alain"};
        String[] tBac = {"Bac général", "Bac technologique", "Bac professionnel", "Université", "Autre"};
        String[] tEtablissement = {"Suzanne Valadon", "Turgot", "Renoir"};
        String[] tSpecialite = {"Commerce international", "Commerce internet", "Finance"};
        int[] tAvis = {2, 1, 0};
        2=Bien,1=Moyen,0=Mauvais
        int ct = 20;
        for(int i = 0; i < ct ; i++){
            int inom= (int)(Math.random() * tNom.length);
            int iprenom = (int)(Math.random() * tPrenom.length);
            int ibac = (int) (Math.random() * tBac.length);
            int ietablissement = (int) (Math.random() * tEtablissement.length);
            int ispecialite = (int) (Math.random() * tSpecialite.length);
            int iavis = (int) (Math.random() * tAvis.length);
            Visiteur visiteur = new Visiteur();
            visiteur.nom = tNom[inom];
            visiteur.prenom = tPrenom[iprenom];
            visiteur.baccalaureat = tBac[ibac];
            visiteur.etablissement = tEtablissement[ietablissement];
            visiteur.specialite = tSpecialite[ispecialite];
            visiteur.avis = tAvis[iavis];
            Log.i("visiteur_nom:",visiteur.nom);
            putVisiteur(visiteur);
        }*/
    }

    /*
     * Appelé lorsque la base a besoin d'être modifiée
     * Il suffit de modifier DATABASE_VERSION !
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VISITEUR + ";");
        onCreate(db);
    }

    /*
     * retourne un ArrayList contenant les données de la table Visiteur
     */
    public ArrayList <Visiteur> getListeVisiteur(){

        ArrayList<Visiteur> ar = new ArrayList<Visiteur>();

       SQLiteDatabase db = this.getWritableDatabase();

        Log.i("getVisiteur","db ok");
        String columns[]={COLUMN_ID, COLUMN_NOM, COLUMN_PRENOM, COLUMN_TEL, COLUMN_BACCALAUREAT,
                COLUMN_ETABLISSEMENT, COLUMN_SPECIALITE, COLUMN_AVIS
        };
        Cursor cursor = db.query(TABLE_VISITEUR, columns ,null,null,null,null,null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Visiteur visiteur = new Visiteur();
            visiteur.id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            visiteur.nom = cursor.getString(cursor.getColumnIndex(COLUMN_NOM));
            visiteur.prenom = cursor.getString(cursor.getColumnIndex(COLUMN_PRENOM));
            visiteur.tel = cursor.getInt(cursor.getColumnIndex(COLUMN_AVIS));
            visiteur.baccalaureat = cursor.getString(cursor.getColumnIndex(COLUMN_BACCALAUREAT));
            visiteur.etablissement = cursor.getString(cursor.getColumnIndex(COLUMN_ETABLISSEMENT));
            visiteur.specialite = cursor.getString(cursor.getColumnIndex(COLUMN_SPECIALITE));
            visiteur.avis = cursor.getInt(cursor.getColumnIndex(COLUMN_AVIS));

            ar.add(visiteur);

            cursor.moveToNext();
        }

        return ar;
    }


    /*
     * Ajoute un visiteur
     * retourne son identifiant
     */
    public long putVisiteur( Visiteur visiteur){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOM, visiteur.nom);
        values.put(COLUMN_PRENOM, visiteur.prenom);
        values.put(COLUMN_AVIS, visiteur.tel);
        values.put(COLUMN_BACCALAUREAT, visiteur.baccalaureat);
        values.put(COLUMN_ETABLISSEMENT, visiteur.etablissement);
        values.put(COLUMN_SPECIALITE, visiteur.specialite);
        values.put(COLUMN_AVIS, visiteur.avis);

        SQLiteDatabase db;
        if(this.db==null){
            db = this.getWritableDatabase();}
        else{
            db=this.db;}



        long insertId = db.insert(TABLE_VISITEUR, null,values);

        return insertId;
    }

    public void updateVisiteur(int id, int avis){
        SQLiteDatabase db = this.getWritableDatabase();
        String update = "UPDATE VISITEUR SET avis = '"+ avis +"' WHERE ID = " + id;
        db.execSQL(update);
    }

}

