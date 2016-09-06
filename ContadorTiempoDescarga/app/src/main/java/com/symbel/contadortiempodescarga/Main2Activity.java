package com.symbel.contadortiempodescarga;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.d(getClass().getCanonicalName(), " Main activity 2 iniciado");


        //Recibimos los datos del intent
        Intent i=getIntent();

       // Bitmap imagen = i.getExtras().getParcelable("BITMAP");
        Long time = i.getLongExtra("TIME",0);
        byte [] img_zip = i.getExtras().getByteArray("BITMAP");

        Bitmap bitmap  = BitmapFactory.decodeByteArray(img_zip, 0, img_zip.length);

        //Referencias a los objetos
        TextView tiempoLBL = (TextView) findViewById(R.id.textView);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        //Asignamos valor a los objetos
        tiempoLBL.setText(time.toString());
        imageView.setImageBitmap(bitmap);
    }
}
