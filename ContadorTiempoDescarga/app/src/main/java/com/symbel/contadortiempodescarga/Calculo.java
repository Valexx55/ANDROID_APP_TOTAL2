package com.symbel.contadortiempodescarga;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.util.Calendar;

/**
 * Created by estefi on 02/09/2016.
 */
public class Calculo extends AsyncTask<String,Void,Bitmap> {


    private Context contextm;
    private Bitmap bitmap;
    private long tiempoInicial;
    private long tiempoFinal;

    public long getTiempoFinal() {
        return tiempoFinal;
    }

    public void setTiempoFinal(long tiempoFinal) {
        this.tiempoFinal = tiempoFinal;
    }

    /*  public Calculo(Context context) {
        this.contextm = context;
    }*/

    protected Bitmap doInBackground(String... params) {
      //  Calendar calendar_actual = Calendar.getInstance();
        tiempoInicial = System.currentTimeMillis();
        URL dirImagen = null ;
        HttpURLConnection httpURLConnection = null;

        try{
            dirImagen = new URL(params[0]);
            httpURLConnection = (HttpURLConnection)dirImagen.openConnection();
            InputStream input = httpURLConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);

        }catch (Throwable t){
            Log.d(getClass().getCanonicalName(), "Error al descargar imagen", t);
        }
        tiempoFinal = (System.currentTimeMillis() - tiempoInicial) ;
        Log.d(getClass().getCanonicalName(), "tiempo: " + tiempoFinal);
        //Calculamos los mb por segundo
        //TODO: No funciona
      //  Integer tamanoBitMap = bitmap.getByteCount()/1024;
     //  tiempoFinal = (tamanoBitMap.longValue() / (tiempoFinal/1000));


        return bitmap;

    }


//    @Override
//    public void onPostExecute(Bitmap bitmap) {
//        super.onPostExecute(bitmap);
//        MainActivity mainActivity = (MainActivity) contextm;
//        mainActivity.pintarResultado(bitmap,tiempoFinal);
//    }



}
