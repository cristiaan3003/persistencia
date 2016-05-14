package com.cristian.persistencia.pojo.model;

/**
 * Created by asusn56 on 01/05/16.
 */
public class Mascota implements Comparable {

   //CREO LA TABLA
    public  static final String TABLE="tMascota";//TABLE_NAME
    //Label tables columns names
    public  static final String KEY_MascotaID="id_mascota";//FILED
    public  static final String KEY_MascotaName="nombre";//FIELD
    public  static final String KEY_MascotaRating="rating";//FIELD
    public static final String KEY_MascotaImagen="imagen";//FIELD
    public static final String CREATE_DB_TABLE_MASCOTA="CREATE TABLE "+ TABLE +" ( "+
            KEY_MascotaID +" integer primary key autoincrement, "+
            KEY_MascotaName + " text,  "+
            KEY_MascotaRating + " integer default 0,  "+
            KEY_MascotaImagen + " integer "
            + " ) ;";

 //------------__FIN TABLA--------------------------------------------------

    int id_mascota;
    String nombre;
    int ranting;
    int imagen;


    public Mascota() {

    }

    public Mascota(String nombre, int ranting, int imagen) {

        this.nombre = nombre;
        this.ranting = ranting;
        this.imagen=imagen;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRanting() {
        return ranting;
    }

    public void setRanting(int ranting) {        this.ranting = ranting;    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getId() {     return id_mascota;    }

    public void setId(int id_mascota) { this.id_mascota = id_mascota;    }


    @Override
    public int compareTo(Object another) { //sobrecarga del metodos compareTo
        //http://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/

        int compareRating= ((Mascota)another).getRanting();

        //orden ascendente
        //return this.ranting-compareRating;

        //orden decendente
        return compareRating-this.ranting;
    }
}
