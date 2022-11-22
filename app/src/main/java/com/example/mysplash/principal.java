package com.example.mysplash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysplash.MyAdapter.MyAdapter;
import com.example.mysplash.json.MyData;
import com.example.mysplash.json.MyInfo;

import java.util.ArrayList;
import java.util.List;

public class principal extends AppCompatActivity {
    private ListView listView;
    private List<MyData> lista;
    private TextView user;
     int []images = { R.drawable.steam,R.drawable.origin,R.drawable.battle,R.drawable.epicgames};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        String aux = null;
        MyInfo info = null;
        MyData myData = null;
        Object object = null;
        user = findViewById(R.id.presId);
        listView = (ListView) findViewById(R.id.listViewId);
        lista = new ArrayList<MyData>();
        Intent intent = getIntent();

        if( intent != null)
        {
            aux = intent.getStringExtra("Usuario" );
            if( aux != null && aux.length()> 0 )
            {
                user.setText(aux);
            }
            if( intent.getExtras() != null ) {
                object = intent.getExtras().get("MyInfo");
                if (object != null) {
                    if (object instanceof MyInfo) {
                        info = (MyInfo) object;
                        user.setText("Hola " + info.getUser() + " edad= " + info.getEdad());
                    }
                }
            }
        }
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
            case R.id.agregarId:
                Intent olvideContra = new Intent(principal.this, Add.class);
                olvideContra.putExtra("MyInfo", info);
                startActivity(olvideContra);
                break;
            case R.id.elimId:
                Intent elimContra = new Intent(principal.this, Delete.class);
                elimContra.putExtra("MyInfo", info);
                startActivity(elimContra);
                break;
            case R.id.editarId:
                Intent editaContra = new Intent(principal.this, Edit.class);
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
        Toast.makeText(getBaseContext(), lista.get(i).getContra(), Toast.LENGTH_SHORT).show();
    }

    }