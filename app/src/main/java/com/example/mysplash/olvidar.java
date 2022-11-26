package com.example.mysplash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mysplash.des.MyDesUtil;
import com.example.mysplash.json.MyInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class olvidar extends AppCompatActivity {
    public static List<MyInfo> list;
    public static String json = null;
    public static String TAG = "mensaje";
    public static String cadena= null;
    public MyDesUtil myDesUtil= new MyDesUtil().addStringKeyBase64(registro.KEY);
    public String usr=null;
    public String correo,mensaje;
    EditText usuario,email;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvidar);
        usuario= findViewById(R.id.clid);

        button = findViewById(R.id.recid);
        list=login_activity.list;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usr = String.valueOf(usuario.getText());
                if(usr.equals("")){
                    Toast.makeText(getApplicationContext(), "Llena el campo de Usuario", Toast.LENGTH_LONG).show();
                }else{
                    int i=0;
                    for(MyInfo inf : list){
                        if(inf.getUser().equals(usr)){
                            correo=inf.getCorreo();
                            mensaje="<html><h1><center>Este correo se ha enviado para que puedas recuperar tu contraseña</center></h1></html>";
                            correo=myDesUtil.cifrar(correo);
                            mensaje=myDesUtil.cifrar(mensaje);
                            i=1;
                        }
                    }
                    if(i==1){
                        Log.i(TAG,usr);
                        Log.i(TAG,correo);
                        Log.i(TAG,mensaje);
                        if( sendInfo( correo,mensaje ) )
                        {
                            Toast.makeText(getBaseContext() , "Se ha enviado" , Toast.LENGTH_LONG );
                            return;
                        }
                        Toast.makeText(getBaseContext() , "Ha ocurrido un error" , Toast.LENGTH_LONG );
                    }else{
                        if(i==0){
                            Log.i(TAG,"no hay usuarios");
                            Toast.makeText(getBaseContext() , "Usuario no encontrado" , Toast.LENGTH_LONG );
                            return;
                        }
                    }
                }
            }
        });


    }
    public boolean sendInfo( String correo ,String mensaje)
    {
        JsonObjectRequest jsonObjectRequest = null;
        JSONObject jsonObject = null;
        String url = "https://us-central1-nemidesarrollo.cloudfunctions.net/function-test";
        RequestQueue requestQueue = null;
        if( correo == null || correo.length() == 0 )
        {
            return false;
        }
        jsonObject = new JSONObject( );
        try
        {
            jsonObject.put("correo" , correo );
            jsonObject.put("mensaje", mensaje);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                Log.i(TAG, response.toString());
            }
        } , new  Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e  (TAG, error.toString());
            }
        } );
        requestQueue = Volley.newRequestQueue( getBaseContext() );
        requestQueue.add(jsonObjectRequest);

        return true;
    }
}