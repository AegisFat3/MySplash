package com.example.mysplash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mysplash.Service.DbUsuarios;
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

public class login_activity extends AppCompatActivity {
    public static final String KEY = "+4xij6jQRSBdCymMxweza/uMYo+o0EUg";
    private String testClaro = "Hola mundo";
    private String testDesCifrado;

    public String correo;
    public String mensaje;
    public static List<MyInfo> list;
    public static String TAG = "mensaje";
    public static String TOG = "error";
    public static String json = null;
    public static String usr,pswd;
    private Button button1, button2, button3;
    public MyDesUtil myDesUtil= new MyDesUtil().addStringKeyBase64(KEY);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button2 = findViewById(R.id.registroid);
        button1 = findViewById(R.id.accesarid);
        button3 = findViewById(R.id.olvidoid);
        EditText usuario = findViewById(R.id.userNameid);
        EditText pswds = findViewById(R.id.editTextTextPassword);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usr = String.valueOf(usuario.getText());
                pswd = String.valueOf(pswds.getText());
                acceso(usr,pswd);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_activity.this, registro.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_activity.this, olvidar.class);
                startActivity(intent);
            }
        });
    }

    public void acceso(String usr , String pswd){
        if(usr.equals("")||pswd.equals("")){
            Toast.makeText(getApplicationContext(), "Llena los campos", Toast.LENGTH_LONG).show();
        }else{
            DbUsuarios dbUsuarios = new DbUsuarios(login_activity.this);
            MyInfo myInfo = dbUsuarios.GetUsuario(usr);
            if(myInfo!=null){
                if(myInfo.getContrasena().equals(pswd)){
                    Toast.makeText(getApplicationContext(), "Inicio de sesión exitoso", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(login_activity.this, principal.class);
                    intent.putExtra("Objeto", myInfo);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_LONG).show();
            }
            }else{
                Toast.makeText(getApplicationContext(), "Usuario NOT FOUND", Toast.LENGTH_LONG).show();

            }
        }
    }
}