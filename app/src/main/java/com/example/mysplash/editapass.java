package com.example.mysplash;

import static com.example.mysplash.registro.archivo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mysplash.MyAdapter.MyAdapter;
import com.example.mysplash.Service.DbContras;
import com.example.mysplash.des.MyDesUtil;
import com.example.mysplash.json.MyData;
import com.example.mysplash.json.MyInfo;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class editapass extends AppCompatActivity {
    public static String TAG = "mensaje";
    private List<MyData> lista;
    Button editacontra;
    public static final String KEY = "+4xij6jQRSBdCymMxweza/uMYo+o0EUg";
    private ListView listView;
    private EditText contra, red;
    private TextView indice;
    private int []images = { R.drawable.battle};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editapass);
        listView = (ListView) findViewById(R.id.lista2);
        editacontra = findViewById(R.id.editacontra);
        Intent intent = getIntent();
        Object object = null;
        MyInfo info = null;
        int idusu;
        object = intent.getExtras().get("MyInfo");
        info = (MyInfo) object;
        DbContras contrasbd = null;
        contrasbd = new DbContras(getBaseContext());
        idusu = info.getId_usr();
        lista = contrasbd.getContras(idusu);
        contra = findViewById(R.id.contra);
        red = findViewById(R.id.socialred);
        indice = findViewById(R.id.indice);
        MyAdapter myAdapter = new MyAdapter( lista , getBaseContext() );
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                toast(i);
                contra.setText(lista.get(i).getContra());
                red.setText(lista.get(i).getUsuario());
                indice.setText(String.format(String.valueOf(i)));
            }

        });
        editacontra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object object = null;
                MyInfo info = null;
                int i = 0;
                DbContras contrasbd = null;
                contrasbd = new DbContras(getBaseContext());
                object = intent.getExtras().get("MyInfo");
                info = (MyInfo) object;
                i= Integer.parseInt(String.valueOf(indice.getText()));
                Log.d(TAG, String.valueOf(i));
                lista.get(i).setContra(String.valueOf(contra.getText()));
                lista.get(i).setUsuario(String.valueOf(red.getText()));
                //List<MyInfo> list =new ArrayList<MyInfo>();
                if(contrasbd.editaContras((info.getId_usr()), lista.get(i).getImage(), String.valueOf(contra.getText()),String.valueOf(red.getText()))){
                    Log.d(TAG, "Contraseña editada");
                }else{
                    Log.d(TAG, "Contraseña no editada");
                }
                info.setContras(lista);
                //List2Json(info,list);
                Intent intent2 = new Intent(editapass.this, principal.class);
                intent2.putExtra("MyInfo", info);
                startActivity(intent2);
            }
        });
    }
    public void List2Json(MyInfo info,List<MyInfo> list){
        Gson gson =null;
        MyDesUtil myDesUtil = null;
        String json= null;
        String json2= null;
        gson =new Gson();
        myDesUtil = new MyDesUtil();
        list.add(info);
        json2 =gson.toJson(list, ArrayList.class);
        if( isNotNullAndNotEmpty( KEY ) )
        {
            myDesUtil.addStringKeyBase64( KEY );
        }
        json= myDesUtil.cifrar(json2);

        if (json == null)
        {
            Log.d(TAG, "Error json");
        }
        else
        {
            Log.d(TAG, json);
            writeFile(json);
        }
        Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_LONG).show();
    }
    public boolean isNotNullAndNotEmpty( String aux )
    {
        return aux != null && aux.length() > 0;
    }
    private boolean writeFile(String text){
        File file =null;
        FileOutputStream fileOutputStream =null;
        try{
            file=getFile();
            fileOutputStream = new FileOutputStream( file );
            fileOutputStream.write( text.getBytes(StandardCharsets.UTF_8) );
            fileOutputStream.close();
            Log.d(TAG, "Hola");
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
    public int toast(int i)
    {
        return i;
    }
}