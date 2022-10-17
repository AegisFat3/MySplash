package com.example.mysplash;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mysplash.json.MyInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class login_activity extends AppCompatActivity {
    public static List<MyInfo> list;
    public static String TAG = "mensaje";
    public static String json = null;
    public static String persona,pass;
    private Button button1, button2, button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button2 = findViewById(R.id.registroid);
        button1 = findViewById(R.id.accesarid);
        button3 = findViewById(R.id.olvidoid);
        EditText user = findViewById(R.id.userNameid);
        EditText contra = findViewById(R.id.editTextTextPassword);
        Read();
        json2List(json);
        if (json == null || json.length() == 0){
            button1.setEnabled(false);
            button3.setEnabled(false);
        }
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persona = String.valueOf(user.getText());
                pass = String.valueOf(contra.getText())+ persona;
                pass = Cluster.bytesToHex(Cluster.createSha1(pass));
                acceso(persona , pass);
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
                persona = String.valueOf(user.getText());
                pass = Cluster.bytesToHex(Cluster.createSha1(String.valueOf(contra.getText())));
                if(persona.equals("")||pass.equals("")){
                    Toast.makeText(getApplicationContext(), "Completa los apartados", Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(login_activity.this,olvidar.class);
                    startActivity(intent);
                }
            }
        });
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void json2List( String json )
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
    private File getFile( )
    {
        return new File( getDataDir() , registro.archivo );
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
    public void acceso(String usr , String pswd){
        int i=0;
        if(usr.equals("")||pswd.equals("")){
            Toast.makeText(getApplicationContext(), "Completa los apartados", Toast.LENGTH_LONG).show();
        }else{
            for(MyInfo myInfo : list){
                if(myInfo.getUser().equals(usr)&&myInfo.getContrasena().equals(pswd)){
                    Intent intent = new Intent(login_activity.this, principal.class);
                    startActivity(intent);
                    i=1;
                }
            }
            if(i==0){
                Toast.makeText(getApplicationContext(), "La contraseña y el usuario no coinciden", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void olvidar_contrasena(String usr, String pswd){
        if(usr.equals("")||pswd.equals("")){
            Toast.makeText(getApplicationContext(), "Completa los apartados", Toast.LENGTH_LONG).show();
        }else{
            Intent intent = new Intent(login_activity.this,olvidar.class);
            startActivity(intent);
        }
    }
}