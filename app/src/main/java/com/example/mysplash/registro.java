package com.example.mysplash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.mysplash.CLUSTER.Cluster;
import com.example.mysplash.Service.DbUsuarios;
import com.example.mysplash.des.MyDesUtil;
import com.example.mysplash.json.MyData;
import com.example.mysplash.json.MyInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class registro extends AppCompatActivity{
    private Button conti;

    private EditText user,contrasena,correo,telef,fecha,edad;
    private RadioButton genm,genh;
    private CheckBox chec1, chec2;
    private Switch switch1;
    private static final String TAG = "Login";
    public static final String archivo = "archivo.json";
    String json = null;
    public static String usu,password,email,tel,dat,ed;
    public static int sw= 0;
    public static int tog= 0;
    public static int on;
    public static String box1s;
    public static String box2s;
    public static String[] chec = new String[2];
    public static List<MyInfo> list =new ArrayList<MyInfo>();
    public static List<MyData> lista;
    public static final String KEY = "+4xij6jQRSBdCymMxweza/uMYo+o0EUg";
    public MyDesUtil myDesUtil= new MyDesUtil().addStringKeyBase64(KEY);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        lista= new ArrayList<>();
        MyData myData=null;
        conti = findViewById(R.id.continuarlogid);
        Button inicio = findViewById(R.id.inicio);
        user = findViewById(R.id.usuariologid);
        contrasena = findViewById(R.id.contralogid);
        correo = findViewById(R.id.emailid);
        telef = findViewById(R.id.numeroid);
        fecha = findViewById(R.id.fechaid);
        edad = findViewById(R.id.edadid);
        chec1 = findViewById(R.id.polid);
        chec2 = findViewById(R.id.batizid );
        genm = findViewById(R.id.mujerid);
        genh = findViewById(R.id.hombreid);

        switch1 = findViewById(R.id.switchid);
        ToggleButton togglebutton1 = findViewById(R.id.felizid);

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registro.this, login_activity.class);
                startActivity(intent);
            }
        });

        conti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyInfo info= new MyInfo();

                usu= String.valueOf(user.getText());
                password = String.valueOf(contrasena.getText());
                email= String.valueOf(correo.getText());
                tel = String.valueOf(telef.getText());
                dat = String.valueOf(fecha.getText());
                ed = String.valueOf(edad.getText());

                if(chec1.isChecked()==true){
                    box1s="first";
                }else{
                    box1s="nanai";
                }
                if(chec2.isChecked()==true){
                    box2s="second";
                }else{
                    box2s="nanai";
                }
                if(genm.isChecked()==true){
                    on=1;
                }
                if(genh.isChecked()==true){
                    on=1;
                }
                if(switch1.isChecked()){
                    sw=1;
                }
                if(togglebutton1.isChecked()){
                    tog=1;
                }

                if(usu.equals("")||password.equals("")||email.equals("")){
                    Log.d(TAG,"vacio");
                    Log.d(TAG,usu);
                    Log.d(TAG,password);
                    Log.d(TAG,email);
                    Toast.makeText(getApplicationContext(), "Completa los campos ><", Toast.LENGTH_LONG).show();
                }else{
                    if(Cluster.validarEmail(email)){
                        Cluster.fillInfo(info);
                        DbUsuarios dbUsuarios = new DbUsuarios(registro.this);
                        long id=dbUsuarios.saveUser(info);
                        if (id > 0){
                            Toast.makeText(registro.this, "Con éxito",Toast.LENGTH_LONG).show();
                        }else{

                            Toast.makeText(registro.this, "Ha ocurrido un error",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Verifica el correo", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }

}