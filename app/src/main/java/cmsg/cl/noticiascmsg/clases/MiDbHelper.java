package cmsg.cl.noticiascmsg.clases;

/**
 * Created by ocantuarias on 25-05-2016.
 */


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import cmsg.cl.noticiascmsg.R;

/**
 * Created by jarriaran on 20-05-2015.
 */
@SuppressWarnings("SpellCheckingInspection")
public class MiDbHelper extends SQLiteOpenHelper {

    private static MiDbHelper mInstance = null;

    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "nombre_base_de_datos" ;

    private Context context;
    private Activity activity;
    // Identificacion de tablas


    private String mensajeDeError = "";

    private SQLiteDatabase db;

    public static final String tablaLogErrores = "log_errores";

    public static final String tablaNoticias = "noticias";

    String crearTablaLogErrores =
            "create table " + tablaLogErrores
            + " (id_log_errores integer primary key autoincrement"
            + ",fecha_hora datetime not null"
            + ",version_app varchar(10) not null"
            + ",mac varchar(20) not null"
            + ",descripcion text not null)";

    String crearTablaNoticias =
            "create table " + tablaNoticias
                    + ",fecha date not null"
                    + ",titulo varchar(50) not null"
                    + ",resumen varchar(50) not null"
                    + ",contenido varchar(250) not null"
                    + ",dir_foto varchar(250)"
                    + ",leido boolean"
                    + ",favorito boolean"
                    +", primary key (titulo_noticia, fecha,tipo_pacto))";




    public static MiDbHelper getInstance(Context ctx) {
        /**
         * use the application context as suggested by CommonsWare.
         * this will ensure that you dont accidentally leak an Activitys
         * context (see this article for more information:
         * http://android-developers.blogspot.nl/2009/01/avoiding-memory-leaks.html)
         */
        if (mInstance == null) {
            mInstance = new MiDbHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }

    /**
     * constructor should be private to prevent direct instantiation.
     * make call to static factory method "getInstance()" instead.
     */
    private MiDbHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;

    }

    @Override
// Cuando se crea la base de datos (primera vez que se instala)
    public void onCreate(SQLiteDatabase db){
        db.execSQL(crearTablaLogErrores);
        db.execSQL(crearTablaNoticias);
    }

    @Override
// Cuando se actualiza
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if(newVersion==1){
            db.execSQL("drop table "+tablaNoticias);
            db.execSQL(crearTablaNoticias);
        }
    }

    public String getMensajeDeError(){
        return mensajeDeError;
    }

/*
####################################
######## Seccion de consultas ######
####################################
  */
    // Devuelve todos los registros en una variable tipo Cursor
    public Cursor getLogErrores(){
        SQLiteDatabase db = getReadableDatabase();
        return db.query(tablaLogErrores, new String[]{"*"},null, null, null, null, null);
    }

    //Cuenta cantidad de errores y retorna un int
    public int CuentaErrores(){
        int error;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(tablaLogErrores, new String[]{"*"},null, null, null, null, null);
        error= cursor.getCount();
        cursor.close();
        return error;
    }

    // Muestra las noticias del periodo seleccionado
    public Cursor getNoticiasPorFecha(String fecha1, String fecha2){

        SQLiteDatabase db = getReadableDatabase();

       return db.query(tablaNoticias, new String[]{"*"},"fecha between ? and ?",new String[]{fecha1,fecha2}, null,null,"fecha");

    }

    public Cursor getNoticiaDetalle(String rut, String fecha, String tipo_pacto){ //Muestra detalle
        SQLiteDatabase db = getReadableDatabase();
        return db.query(tablaNoticias, new String[]{"*"},"titulo=? AND fecha=? AND tipo_pacto=?",new String[]{rut,fecha,tipo_pacto} , null, null, "fecha");

    }

    // Borra todos los registros con sus respectivos where
    public boolean deleteLogError(int idLogError){
        SQLiteDatabase db = getReadableDatabase();
        long resultado = -1;
        resultado = db.delete(tablaLogErrores,"id_log_errores=?",new String[]{String.valueOf(idLogError)});
        return (resultado >= 1);
    }

    // Borra TODAS las solicitudesa
//TODO BORRAR CUANDO SE APRUEBE!!! -----------------------------------------------------------------
    public boolean deletenoticiasALL(){
        SQLiteDatabase db = getReadableDatabase();
        long resultado = -1;
        resultado = db.delete(tablaNoticias,null,null);
        return (resultado >= 1);
    }
//TODO FIN BORRAR ----------------------------------------------------------------------------------

    //TODO QUITAR COMENTARIO CUANDO SE APRUEBE------------------------------------------------------
    // Borra todas las noticias obsoletas y deja las de este mes
    public boolean deleteNoticiasMensual(){
        SQLiteDatabase db = getReadableDatabase();
        long resultado = -1;
        resultado = db.delete(tablaNoticias, "fecha<=? " , new String[]{get30Dias()});
        return (resultado >= 1);
    }
    //TODO FIN QUITAR COMENTARIO -------------------------------------------------------------------


    // Borra todos los Logs de error
    public boolean deleteErrorLogALL(){
        SQLiteDatabase db = getReadableDatabase();
        long resultado = -1;
        resultado = db.delete(tablaLogErrores,null,null);
        return (resultado >= 1);
    }


    // Inserta log de error
    public boolean insertarLogError(String descripcion, String mac){
        db = getWritableDatabase();
     //   String mac= ValidacionConexion.getDireccionMAC();
        ContentValues campoValor = new ContentValues();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
        Date date = new Date();

        campoValor.put("fecha_hora", dateFormat.format(date));
        campoValor.put("descripcion", descripcion);
        campoValor.put("version_app", context.getString(R.string.version));
        campoValor.put("mac",mac);

        long resultado = db.insertOrThrow(tablaLogErrores, null, campoValor);
        return resultado >= 1;
    }

    //Insertar Noticias

    public boolean insertarNoticiaSINFOTO(String titulo, String resumen, String fecha, String contenido){
        db = getWritableDatabase();
        ContentValues campoValor = new ContentValues();
        campoValor.put("titulo", titulo);
        campoValor.put("fecha", fecha);
        campoValor.put("resumen", resumen);
        campoValor.put("contenido", contenido);
        long resultado = db.insertOrThrow(tablaNoticias, null, campoValor);
        return resultado >= 1;
    }
    public boolean insertarNoticiaCONFOTO(String titulo, String resumen, String fecha, String contenido,String dir_foto){
        db = getWritableDatabase();
        ContentValues campoValor = new ContentValues();
        campoValor.put("titulo", titulo);
        campoValor.put("fecha", fecha);
        campoValor.put("resumen", resumen);
        campoValor.put("contenido", contenido);
        campoValor.put("dir_foto", dir_foto);
        long resultado = db.insertOrThrow(tablaNoticias, null, campoValor);
        return resultado >= 1;
    }


    //Boleano que confirma si la noticia existe o no
    public boolean yaExiste (String titulo, String fecha){

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(tablaNoticias, new String[]{"*"},"titulo=? and fecha=? ",new String[]{titulo,fecha} , null, null, null);

        if(cursor==null){
            return false;
        }
        if(cursor.getCount()>0){
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    //Obtiene fecha 30 dias atras
    public String get30Dias (){

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -30);
        return dateFormat.format(cal.getTime());

    }
    public String get7Dias (){

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -7);
        return dateFormat.format(cal.getTime());

    }

    public String getHOY (){

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        return dateFormat.format(cal.getTime());

    }
}
