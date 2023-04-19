package com.example.mysplash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysplash.MyAdapter.MyAdapter;
import com.example.mysplash.Service.DbContras;
import com.example.mysplash.json.MyData;
import com.example.mysplash.json.MyInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class principal extends AppCompatActivity {
    private TextView usuario;
    private Button api, Csesion;
    private ListView listView;
    private List<MyData> list;
    public static String TAG = "Menu";
    private int []images = { R.drawable.battle};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        int idusu = 0;
        String aux = null;
        MyInfo info = null;
        Object object = null;
        MyData myData = null;
        DbContras contrasbd = null;
        contrasbd = new DbContras(getBaseContext());
        api = findViewById(R.id.config);
        Csesion = findViewById(R.id.Csesion);
        usuario = findViewById(R.id.textUser);
        Intent intent = getIntent();
        listView = (ListView) findViewById(R.id.listViewId);
        Csesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(principal.this, login_activity.class);
                startActivity(intent);
            }
        });
        api.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(principal.this, Api.class);
                startActivity(intent);
            }
        });

        list = new ArrayList<MyData>();
        if( intent != null)
        {
            aux = intent.getStringExtra("Usuario" );
            if( aux != null && aux.length()> 0 )
            {
                usuario.setText(aux);
            }
            if( intent.getExtras() != null ) {
                object = intent.getExtras().get("MyInfo");
                if (object != null) {
                    if (object instanceof MyInfo) {
                        info = (MyInfo) object;
                        usuario.setText("Bienvenido " + info.getUser() + " de edad " + info.getEdad());
                        idusu = info.getId_usr();
                        Log.d("Id usu", String.valueOf(idusu));
                        list = contrasbd.getContras(idusu);
                        if(list == null){
                            Toast.makeText(getBaseContext(), "No hay contras", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }

        MyAdapter myAdapter = new MyAdapter( list , getBaseContext() );
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                toast( i );
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        boolean flag = false;
        flag = super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu ,  menu);
        return flag;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        MyInfo info = null;
        Object object = null;
        Intent intent = getIntent();
        object = intent.getExtras().get("MyInfo");
        info = (MyInfo) object;
        switch (item.getItemId() ) {
            case R.id.item1:
                Intent olvideContra = new Intent(principal.this, agregapass.class);
                olvideContra.putExtra("MyInfo", info);
                startActivity(olvideContra);
                break;
            case R.id.item2:
                Intent elimContra = new Intent(principal.this, eliminapass.class);
                elimContra.putExtra("MyInfo", info);
                startActivity(elimContra);
                break;
            case R.id.item3:
                Intent editaContra = new Intent(principal.this, editapass.class);
                editaContra.putExtra("MyInfo", info);
                startActivity(editaContra);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }
    private void toast( int i )
    {
        MyInfo info = null;
        Object object = null;
        Intent intent = getIntent();
        object = intent.getExtras().get("MyInfo");
        info = (MyInfo) object;
        Toast.makeText(getBaseContext(), list.get(i).getContra(), Toast.LENGTH_SHORT).show();
        String Latitud = list.get(i).getLatitud();
        String Longitud = list.get(i).getLongitud();
        Intent mapa = new Intent(principal.this,mapa.class);
        mapa.putExtra("MyInfo",info);
        mapa.putExtra("Latitud",Latitud);
        mapa.putExtra("Longitud",Longitud);
        startActivity(mapa);
    }

}