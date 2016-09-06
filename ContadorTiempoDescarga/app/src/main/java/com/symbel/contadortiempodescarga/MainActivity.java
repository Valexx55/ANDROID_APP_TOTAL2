package com.symbel.contadortiempodescarga;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //public static String mURL = null;
    public static String mURL = "http://www.hrsanroque.com/galeria/slider/18.jpg";
    public static TextView textoTiempo;
    public static ImageView imageView;

    public void programarAvisoFinServicio()
    {
        //TODO: Asociar al broadcastReciever un IntentFilter
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("SERVICIO_TERMINADO");

        MyReciever br = new MyReciever(this);
        registerReceiver(br, intentFilter);

    }

    public void download (View view){

        Intent intentService = null;

        programarAvisoFinServicio();

        intentService = new Intent(this,MyService.class);
        startService(intentService);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(getClass().getCanonicalName(), " Main activity iniciado");

        textoTiempo = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public static void pintarResultado(Bitmap imagen, long tiempo){

        textoTiempo.setText(Long.toString(tiempo));
        imageView.setImageBitmap(imagen);
    }
}
