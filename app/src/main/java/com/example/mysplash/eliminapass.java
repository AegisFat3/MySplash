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

public class eliminapass extends AppCompatActivity {
    public static String TAG = "mensaje";
    private List<MyData> lista;
    Button eliminacontra;
    public static final String KEY = "+4xij6jQRSBdCymMxweza/uMYo+o0EUg";
    private ListView listView;
    private EditText contra2, red;
    private TextView contrasena, red2;
    private int []images = { R.drawable.battle,R.drawable.epicgames,R.drawable.origin, R.drawable.stadia, R.drawable.ps,
            R.drawable.xbox, R.drawable.nins, R.drawable.uplay, R.drawable.gogcom, R.drawable.steam};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminapass);
        listView = (ListView) findViewById(R.id.listViewID);
        eliminacontra = findViewById(R.id.eliminacontra);
        Intent intent = getIntent();
        Object object = null;
        MyInfo info = null;
        object = intent.getExtras().get("MyInfo");
        info = (MyInfo) object;
        DbContras contrasbd = null;
        contrasbd = new DbContras(getBaseContext());
        lista = contrasbd.getContras(info.getId_usr());
        MyAdapter myAdapter = new MyAdapter( lista , getBaseContext() );
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                toast(i);
            }
        });
        eliminacontra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object object = null;
                MyInfo info = null;
                List<MyInfo> list =new ArrayList<MyInfo>();
                object = intent.getExtras().get("MyInfo");
                info = (MyInfo) object;
                info.setContras(lista);
                //List2Json(info,list);
                Intent intent2 = new Intent(eliminapass.this, principal.class);
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
    private int toast(int i)
    {
        DbContras contrasbd = null;
        contrasbd = new DbContras(getBaseContext());
        contrasbd.eliminaContra(lista.get(i).getContra(), lista.get(i).getId_contra());
        lista.remove(i);
        Toast.makeText(getBaseContext(),"Aniquilado", Toast.LENGTH_SHORT).show();
        return lista.size();
    }
}
