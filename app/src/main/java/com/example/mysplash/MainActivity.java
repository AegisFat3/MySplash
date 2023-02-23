package com.example.mysplash;

import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.mysplash.Service.UsuariosDBService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UsuariosDBService usuariosDBService = new UsuariosDBService(MainActivity.this);
        SQLiteDatabase db = usuariosDBService.getWritableDatabase();

        if(db!= null){
            Toast.makeText(this,"DB successfully built",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"Fail",Toast.LENGTH_LONG).show();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this , login_activity.class);
                startActivity( intent );
                finish();

            }
        }, 4000);
    }
}