package com.example.mysplash.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;


import com.example.mysplash.contract.UsuariosContract;
import com.example.mysplash.json.MyData;

import java.util.ArrayList;
import java.util.List;

public class DbContras extends UsuariosDBService{
    Context context;

    public DbContras(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public Boolean saveContra(MyData myData){
        Boolean correcto = false;
        try{
            UsuariosDBService usuariosDBService = new UsuariosDBService(context);
            SQLiteDatabase db =usuariosDBService.getWritableDatabase();
            ContentValues values= new ContentValues();
            db.insert(TABLE_CONTRA,null, UsuariosContract.MyDataEntry.toContentValues(myData));
            correcto = true;

        }catch(Exception ex){
            ex.toString();
            correcto = false;
        }
        finally{
            return correcto;
        }

    }
    public List<MyData> getContras(int id )
    {
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        List<MyData>contras = null;
        MyData myData = null;
        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT*FROM " + TABLE_CONTRA +" WHERE id = "+id+" "  ,null);
        if( cursor == null )
        {
            return null;
        }
        if( cursor.getCount() < 1)
        {
            return null;
        }
        if( !cursor.moveToFirst() )
        {
            return null;
        }
        Log.d(TAG, "Cursor" + cursor.getCount());
        contras = new ArrayList<MyData>( );
        for( int i = 0; i < cursor.getCount(); i++)
        {
            myData = new MyData( );
            myData.setId_usr(cursor.getInt(0));
            myData.setContra(cursor.getString(1));
            myData.setUsuario(cursor.getString(2));
            myData.setId_contra(cursor.getInt(3));
            myData.setImage(cursor.getInt(4));
            myData.setData(cursor.getBlob(5));
            myData.setLatitud(cursor.getString(6));
            myData.setLongitud(cursor.getString(7));
            contras.add(myData);
            cursor.moveToNext( );
        }
        return contras;
    }
    public boolean editaContras(int id, int imagen, String contra, String red){
        boolean correcto = false;
        SQLiteDatabase sqLiteDatabase = null;
        sqLiteDatabase = getWritableDatabase();
        try{
            sqLiteDatabase.execSQL("UPDATE "+TABLE_CONTRA+" SET contra = '"+contra+"', user_c = '"+red+"' WHERE imagen = '"+imagen+"' and id ='"+id+"'");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            sqLiteDatabase.close();
        }
        return correcto;
    }
    public boolean eliminaContra(String Contra, int id){
        boolean correcto = false;
        SQLiteDatabase sqLiteDatabase = null;
        sqLiteDatabase = getWritableDatabase();
        try{
            sqLiteDatabase.execSQL("DELETE FROM "+TABLE_CONTRA+" WHERE contra ='"+Contra+"' and id = '"+id+"'");
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            sqLiteDatabase.close();
        }
        return correcto;
    }

}
