package com.example.mysplash.contract;

import static com.example.mysplash.Service.UsuariosDBService.TABLE_CONTRA;
import static com.example.mysplash.Service.UsuariosDBService.TABLE_USERS;

import android.content.ContentValues;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.mysplash.json.MyData;
import com.example.mysplash.json.MyInfo;

import java.io.Serializable;

public class UsuariosContract implements Serializable {
    public static final String TAG = "UsuariosContract";

    public static abstract class UsuarioEntry implements BaseColumns
    {
        public static final String USUARIO = "usuario";

        public static final String getCreateTable( )
        {
            String table = "CREATE TABLE "+TABLE_USERS+ "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "usuario TEXT NOT NULL UNIQUE," +
                    "paswd TEXT NOT NULL," +
                    "correo TEXT NOT NULL," +
                    "box1 TEXT," +
                    "box2 TEXT," +
                    "gen INTEGER," +
                    "fecha TEXT," +
                    "num TEXT," +
                    "fel TEXT," +
                    "notifi INTEGER," +
                    "edad TEXT" +
                    ")";
            return table;
        }

        public static ContentValues toContentValues(MyInfo info)
        {
            ContentValues values = new ContentValues();
            values.put("usuario", info.getUser());
            values.put("paswd", info.getContrasena());
            values.put("correo", info.getCorreo());
            values.put("box1", info.getBox1());
            values.put("box2", info.getBox2());
            values.put("gen", info.getGen());
            values.put("fecha", info.getFecha());
            values.put("num", info.getNumero());
            values.put("fel", info.getFeliz());
            values.put("notifi", info.getNotifi());
            values.put("edad", info.getEdad());
            return values;
        }
    }
    public abstract static class MyDataEntry implements BaseColumns{
        public static final String getCreateTable( )
        {
            String table ="CREATE TABLE "+TABLE_CONTRA+"(" +
                    "id_contra INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "contra TEXT NOT NULL," +
                    "user_c TEXT NOT NULL," +
                    "idusu INTEGER NOT NULL," +
                    "imagen INTEGER NOT NULL," +
                    "data BLOB," +
                    "latitud TEXT NOT NULL," +
                    "longitud TEXT NOT NULL)";
            return table;
        }
        public static ContentValues toContentValues(MyData myData)
        {
            ContentValues values = new ContentValues();
            values.put("contra", myData.getContra());
            values.put("user_c", myData.getUsuario());
            values.put("idusu", myData.getId_contra());
            values.put("imagen", myData.getImage());
            values.put("data", myData.getData());
            values.put("latitud",myData.getLatitud());
            values.put("longitud",myData.getLongitud());


            return values;
        }
    }
}