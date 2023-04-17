package com.example.mysplash.Permisos;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.Serializable;

public class Permisos implements Serializable {

    public boolean tPC = false, tPI= false;
    public static final int CODIGO_PERMISOS_CAMARA = 1, CODIGO_PERMISOS_INTERNET=2;
    public void vypi(Context context, Activity activity) {
        int estadoDePermiso = ContextCompat.checkSelfPermission(context, Manifest.permission.INTERNET);
        if (estadoDePermiso == PackageManager.PERMISSION_GRANTED) {
            pisi(context);
        } else {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.INTERNET},
                    CODIGO_PERMISOS_INTERNET);
        }
    }
    public void vypc(Context context, Activity activity) {
        int estadoDePermiso = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        if (estadoDePermiso == PackageManager.PERMISSION_GRANTED) {
            pcsi(context);
        } else {

            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CAMERA},
                    CODIGO_PERMISOS_CAMARA);
        }
    }
    public void pcsi(Context context) {
        Toast.makeText(context, "Cámara permitida", Toast.LENGTH_SHORT).show();
        tPC = true;
    }

    public void pcno(Context context) {
        Toast.makeText(context, "Cámara no permitida", Toast.LENGTH_SHORT).show();
    }
    public void pisi(Context context) {
        Toast.makeText(context, "Internet permitido", Toast.LENGTH_SHORT).show();
        tPI = true;
    }

    public void pino(Context context) {
        Toast.makeText(context, "Internet no permitido", Toast.LENGTH_SHORT).show();
    }
}