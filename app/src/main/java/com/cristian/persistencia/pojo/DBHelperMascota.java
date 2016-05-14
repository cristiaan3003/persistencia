package com.cristian.persistencia.pojo;


        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import com.cristian.persistencia.pojo.model.Mascota;

        import java.util.ArrayList;

public class DBHelperMascota extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "mascota.db";
    private SQLiteDatabase db;

    public DBHelperMascota(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();// la primera vez, si no esta creada la crea, si esta retorna la instancia

    }

    private ContentValues generarValores(Mascota mascota){
        ContentValues valores= new  ContentValues();
        valores.put(Mascota.KEY_MascotaName,mascota.getNombre());
        valores.put(Mascota.KEY_MascotaRating, mascota.getRanting());
        valores.put(Mascota.KEY_MascotaImagen,mascota.getImagen());

        return  valores;
    }

    public  void insertarMascota(Mascota mascota){
        db.insert(Mascota.TABLE, null, generarValores(mascota));
    }

   //retorna todas las mascotas guardadas en la bd
    public ArrayList<Mascota>getMascotas(){
        ArrayList<Mascota> masc= new ArrayList<Mascota>();
        String columnas[]={Mascota.KEY_MascotaID,Mascota.KEY_MascotaName,Mascota.KEY_MascotaRating,Mascota.KEY_MascotaImagen};
        Cursor c= db.query(Mascota.TABLE,columnas,null,null,null,null,null);//retorna una consulta a la base de datos en un cursor
        //retorna todas las mascotas,  sin condicion where.
        if(c.moveToFirst()){//para asegurarnos que el cursor tiene al menos un elemento
            do{
                Mascota p=new Mascota();
                //Log.i("id c", String.valueOf(c.getInt(0)));
                p.setId(c.getInt(0)); //id
                p.setNombre(c.getString(1));//nombre mascota
                p.setRanting(c.getInt(2)); //likes
                p.setImagen(c.getInt(3));//imagen
                masc.add(p);
                Log.i("id masc", String.valueOf(p.getId()));
                Log.i("id masc name", String.valueOf(p.getNombre()));
                Log.i("id masc rating", String.valueOf(p.getRanting()));
                Log.i("id masc imagen", String.valueOf(p.getImagen()));
                Log.i("-------------", "----------------------------");

            }while (c.moveToNext());
        }

        return  masc;
    }

    public void updateMascota(Mascota mascota){

        Log.i("tagg","UPDATE MASCOTA");
        Log.i("Nombre",mascota.getNombre());
        Log.i("Rating",String.valueOf(mascota.getRanting()));
        Log.i("id",String.valueOf(mascota.getId()));
        Log.i("KEY_MascotaID", Mascota.KEY_MascotaID);
        db.update(Mascota.TABLE, generarValores(mascota), Mascota.KEY_MascotaID + " = ? ", new String[]{String.valueOf(mascota.getId())});
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here
        db.execSQL(Mascota.CREATE_DB_TABLE_MASCOTA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Mascota.TABLE);

        // Create tables again
        onCreate(db);

    }

}