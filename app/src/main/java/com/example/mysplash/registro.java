package com.example.mysplash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;
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
import java.util.Arrays;
import java.util.List;


public class registro extends AppCompatActivity{
    private Button conti;

    private EditText user,contrasena,correo,telef,fecha,edad;
    private RadioButton genm,genh;
    private CheckBox chec1, chec2;
    private Switch switch1;
    private static final String TAG = "MainActivity";
    public static final String archivo = "archivo.json";
    String json = null;
    public static String usu,password,email,tel,dat,ed;
    public static boolean sw= false;
    public static boolean tog= false;
    public static boolean on;
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
        Read();
        json2List(json);
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
                    chec[0]="first";
                }else{
                    chec[0]="nanai";
                }
                if(chec2.isChecked()==true){
                    chec[1]="second";
                }else{
                    chec[1]="nanai";
                }
                if(genm.isChecked()==true){
                    on=true;
                }
                if(genh.isChecked()==true){
                    on=true;
                }
                if(switch1.isChecked()){
                    sw= true;
                }
                if(togglebutton1.isChecked()){
                    tog= true;
                }

                if(usu.equals("")||password.equals("")||email.equals("")){
                    Log.d(TAG,"vacio");
                    Log.d(TAG,usu);
                    Log.d(TAG,password);
                    Log.d(TAG,email);
                    Toast.makeText(getApplicationContext(), "Completa los campos ><", Toast.LENGTH_LONG).show();
                }else{
                    if(Cluster.validarEmail(email)){
                        if(list.isEmpty()){
                            Log.d(TAG,"lleno");
                            Cluster.fillInfo(info);
                            List2Json(info,list);
                        }else{
                            if(Cluster.usuarios(list,usu,email)){
                                Log.d(TAG,"Ya existe alguien más");
                                Toast.makeText(getApplicationContext(), "El correo o el usuario ya fueron registrados con anterioridad, pon otros", Toast.LENGTH_LONG).show();
                            }else{
                                Cluster.fillInfo(info);
                                info.setContras(lista);
                                List2Json(info,list);
                            }
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Verifica el correo", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }
    public void List2Json(MyInfo info,List<MyInfo> list){
        Gson gson =null;
        String json= null;
        gson =new Gson();
        list.add(info);
        json =gson.toJson(list, ArrayList.class);
        if (json == null)
        {
            Log.d(TAG, "Error en json");
        }
        else
        {
            Log.d(TAG, json);
            json=myDesUtil.cifrar(json);
            Log.d(TAG, json);
            writeFile(json);
        }
        Toast.makeText(getApplicationContext(), "Todo bien", Toast.LENGTH_LONG).show();
    }
    private boolean writeFile(String text){
        File file =null;
        FileOutputStream fileOutputStream =null;
        try{
            file=getFile();
            fileOutputStream = new FileOutputStream( file );
            fileOutputStream.write( text.getBytes(StandardCharsets.UTF_8) );
            fileOutputStream.close();
            Log.d(TAG, "Bienvenido ><");
            return true;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    private File getFile(){
        return new File(getDataDir(),archivo);
    }
    public boolean Read(){
        if(!isFileExits()){
            return false;
        }
        File file = getFile();
        FileInputStream fileInputStream = null;
        byte[] bytes = null;
        bytes = new byte[(int)file.length()];
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytes);
            json=new String(bytes);
            Log.d(TAG,json);
            json= myDesUtil.desCifrar(json);
            Log.d(TAG,json);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    private boolean isFileExits( )
    {
        File file = getFile( );
        if( file == null )
        {
            return false;
        }
        return file.isFile() && file.exists();
    }
    public void json2List( String json)
    {
        Gson gson = null;
        String mensaje = null;
        if (json == null || json.length() == 0)
        {
            Toast.makeText(getApplicationContext(), "Error json null or empty", Toast.LENGTH_LONG).show();
            return;
        }
        gson = new Gson();
        Type listType = new TypeToken<ArrayList<MyInfo>>(){}.getType();
        list = gson.fromJson(json, listType);
        if (list == null || list.size() == 0 )
        {
            Toast.makeText(getApplicationContext(), "Error list is null or empty", Toast.LENGTH_LONG).show();
            return;
        }
    }
}