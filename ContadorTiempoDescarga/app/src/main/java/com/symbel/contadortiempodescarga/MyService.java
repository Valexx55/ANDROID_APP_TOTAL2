package com.symbel.contadortiempodescarga;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutionException;

public class MyService extends Service {

    private static Bitmap bitmap = null;

    private Calculo calculo;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(getClass().getCanonicalName(), " Servicio MyService iniciado");

        calculo = new Calculo();
        try
        {
            bitmap = calculo.execute(MainActivity.mURL).get();
            stopSelf();//finalizo
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return Service.START_REDELIVER_INTENT; //Si el servicio se destruye se reinicia el intent original
    }


    private byte [] comprimirBitMap(Bitmap bitmap)

    {
        byte [] imag_zip;

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            imag_zip = baos.toByteArray();


        return  imag_zip;

    }


    @Override
    public void onDestroy() {
        try

        {
            super.onDestroy();

            Log.d(getClass().getCanonicalName(), " Servicio Destruido");


            Intent intent_reciver = new Intent("SERVICIO_TERMINADO");
            intent_reciver.putExtra("TIME", calculo.getTiempoFinal());

            byte[] foto_array = comprimirBitMap(bitmap);
            intent_reciver.putExtra("BITMAP", foto_array);

            sendBroadcast(intent_reciver);

            Log.d(getClass().getCanonicalName(), " BroadcastLanzado");

        } catch (Throwable t)
        {
            Log.e(getClass().getCanonicalName(), "EERRRROAZO", t);
        }


    }
}
