package com.example.mysplash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.mysplash.MyAdapter.MyAdapter;
import com.example.mysplash.Service.DbContras;
import com.example.mysplash.des.MyDesUtil;
import com.example.mysplash.json.MyData;
import com.example.mysplash.json.MyInfo;
import com.google.gson.Gson;


public class principal extends AppCompatActivity {
    public List<MyInfo> list;
    public MyDesUtil myDesUtil= new MyDesUtil().addStringKeyBase64(registro.KEY);
    public static String TAG = "mensaje";
    public static String json = null;
    private ListView listView;
    private List<MyData> listo;
    public boolean bandera = false;
    public int pos=0;
    public static MyInfo myInfo= null;
    String aux;
    EditText editText,editText1;
    Button button,button1,button2;

    MyData data = new MyData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Object object= null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Intent intent = getIntent();
        if(intent != null){
            if(intent.getExtras() !=null){
                object = intent.getExtras().get("Objeto");
                if (object != null) {
                    if (object instanceof MyInfo) {
                        myInfo = (MyInfo) object;
                    }
                }
            }
        }

        editText=findViewById(R.id.editText1);
        editText1=findViewById(R.id.editText2);
        button=findViewById(R.id.borrid);
        button1=findViewById(R.id.edid);
        button2=findViewById(R.id.masid);
        DbContras dbContras = new DbContras(principal.this);
        listo = dbContras.getContras(myInfo.getId_usr());
        /*for(MyData contra : listo){
            Log.d("Contras",contra.toString());
        }*/
        listView = (ListView) findViewById(R.id.listViewId);
        MyAdapter myAdapter = new MyAdapter(listo, getBaseContext());
        listView.setAdapter(myAdapter);
        button.setEnabled(false);
        button1.setEnabled(false);
        if(listo==null){
            Toast.makeText(getApplicationContext(), "Para agregar una contraseña de clic en el menú o en el botón de agregar", Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "Escriba en los campos", Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), String.valueOf(myInfo.getId_usr()), Toast.LENGTH_LONG).show();
        }
        Toast.makeText(getApplicationContext(), "Para modificar o eliminar una contraseña da click sobre ella", Toast.LENGTH_LONG).show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                data = listo.get(i);
                editText.setText(data.getUsuario());
                editText1.setText(data.getContra());
                pos=i;
                button.setEnabled(true);
                button1.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Para guardar los cambios de click en guardar, esta opción esta en los tres puntos", Toast.LENGTH_LONG).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbContras dbContras = new DbContras(principal.this);
                boolean id=dbContras.eliminarContacto(myInfo.getId_usr(),data.getUsuario(),data.getContra());
                if(id){
                    listo=dbContras.getContras(myInfo.getId_usr());
                    MyAdapter myAdapter = new MyAdapter(listo, getBaseContext());
                    listView.setAdapter(myAdapter);
                    editText.setText("");
                    editText1.setText("");
                    Toast.makeText(getApplicationContext(), "Se eliminó la contraseña", Toast.LENGTH_LONG).show();
                    button.setEnabled(false);
                    button1.setEnabled(false);
                }else{
                    Toast.makeText(getApplicationContext(), "Error al eliminar", Toast.LENGTH_LONG).show();
                }
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usr= String.valueOf(editText.getText());
                String contra = String.valueOf(editText1.getText());
                if(usr.equals("")||contra.equals("")){
                    Toast.makeText(getApplicationContext(), "Llena los campos", Toast.LENGTH_LONG).show();
                }else{
                    DbContras dbContras = new DbContras(principal.this);
                    boolean id=dbContras.AlterContra(data.getUsuario(),data.getContra(),myInfo.getId_usr(),data.getId_contra());
                    if(id){
                        listo = dbContras.getContras(myInfo.getId_usr());
                        MyAdapter myAdapter = new MyAdapter(listo, getBaseContext());
                        listView.setAdapter(myAdapter);
                        editText.setText("");
                        editText1.setText("");
                        Toast.makeText(getApplicationContext(), "Se modificó la contraseña", Toast.LENGTH_LONG).show();
                        button.setEnabled(false);
                        button1.setEnabled(false);
                    }else{
                        Toast.makeText(getApplicationContext(), "Error al modificar", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usr= String.valueOf(editText.getText());
                String contra = String.valueOf(editText1.getText());
                if(usr.equals("")||contra.equals("")){
                    Toast.makeText(getApplicationContext(), "Llena los campos", Toast.LENGTH_LONG).show();
                }else{
                    MyData myData = new MyData();
                    myData.setContra(contra);
                    myData.setUsuario(usr);
                    myData.setId_usr(myInfo.getId_usr());
                    Toast.makeText(getApplicationContext(), String.valueOf(myInfo.getId_usr()), Toast.LENGTH_LONG).show();
                    DbContras dbContras = new DbContras(principal.this);
                    long id=dbContras.saveContra(myData);
                    if (id > 0){
                        listo=dbContras.getContras(myInfo.getId_usr());
                        MyAdapter myAdapter = new MyAdapter(listo, getBaseContext());
                        listView.setAdapter(myAdapter);
                        editText.setText("");
                        editText1.setText("");
                        Toast.makeText(getApplicationContext(), myData.getUsuario()+" "+myData.getContra(), Toast.LENGTH_LONG).show();
                        Toast.makeText(principal.this, "Guardado con éxito",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(principal.this, "No se ha podido guardar",Toast.LENGTH_LONG).show();
                    }
                }
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
        int id = item.getItemId();
        if(id==R.id.item1){
            String usr= String.valueOf(editText.getText());
            String contra = String.valueOf(editText1.getText());
            if(usr.equals("")||contra.equals("")){
                Toast.makeText(getApplicationContext(), "Llena los campos", Toast.LENGTH_LONG).show();
            }else{
                MyData myData = new MyData();
                myData.setContra(contra);
                myData.setUsuario(usr);
                myData.setId_usr(myInfo.getId_usr());
                Toast.makeText(getApplicationContext(), String.valueOf(myInfo.getId_usr()), Toast.LENGTH_LONG).show();
                DbContras dbContras = new DbContras(principal.this);
                long p=dbContras.saveContra(myData);
                if (p > 0){
                    listo=dbContras.getContras(myInfo.getId_usr());
                    MyAdapter myAdapter = new MyAdapter(listo, getBaseContext());
                    listView.setAdapter(myAdapter);
                    editText.setText("");
                    editText1.setText("");
                    Toast.makeText(getApplicationContext(), myData.getUsuario()+" "+myData.getContra(), Toast.LENGTH_LONG).show();
                    Toast.makeText(principal.this, "Guardado con éxito",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(principal.this, "No se ha podidio guardar",Toast.LENGTH_LONG).show();
                }
            }

        }
        if(id==R.id.item2){
            Toast.makeText(getApplicationContext(), "Es un secreto", Toast.LENGTH_LONG).show();
            return true;
        }
        if(id==R.id.item3){
            Intent intent= new Intent(principal.this,login_activity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}