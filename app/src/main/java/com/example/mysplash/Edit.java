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

public class Edit extends AppCompatActivity {
    public static String TAG = "mensaje";
    private List<MyData> lista;
    Button editacontra;
    private ListView listView;
    private EditText contra, red;
    private TextView indice;
    private int []images = { R.drawable.origin,R.drawable.steam,R.drawable.epicgames, R.drawable.battle, R.drawable.xbox,
            R.drawable.ps, R.drawable.nins, R.drawable.uplay, R.drawable.gogcom, R.drawable.stadia};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        listView = (ListView) findViewById(R.id.lista2);
        editacontra = findViewById(R.id.editacontra);
        Intent intent = getIntent();
        Object object = null;
        MyInfo info = null;
        object = intent.getExtras().get("MyInfo");
        info = (MyInfo) object;
        lista = info.getContras();
        contra = findViewById(R.id.contra);
        red = findViewById(R.id.plataforma);
        indice = findViewById(R.id.indice);
        MyAdapter myAdapter = new MyAdapter( lista , getBaseContext() );
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                toast(i);
                contra.setText(lista.get(i).getContra());
                red.setText(lista.get(i).getRed());
                indice.setText(String.format(String.valueOf(i)));
            }

        });
        editacontra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object object = null;
                MyInfo info = null;
                int i = 0;
                i= Integer.parseInt(String.valueOf(indice.getText()));
                lista.get(i).setContra(String.valueOf(contra.getText()));
                lista.get(i).setRed(String.valueOf(red.getText()));
                List<MyInfo> list =new ArrayList<MyInfo>();
                object = intent.getExtras().get("MyInfo");
                info = (MyInfo) object;
                info.setContras(lista);
                List2Json(info,list);
                Intent intent2 = new Intent(Edit.this, principal.class);
                intent2.putExtra("MyInfo", info);
                startActivity(intent2);
            }
        });
    }
    public void List2Json(MyInfo info, List<MyInfo> list){
        Gson gson =null;
        String json= null;
        gson =new Gson();
        list.add(info);
        json =gson.toJson(list, ArrayList.class);
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
        Toast.makeText(getBaseContext(),"Hokei", Toast.LENGTH_SHORT).show();
        return i;
    }
}

