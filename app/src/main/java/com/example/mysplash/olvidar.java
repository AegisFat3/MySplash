package com.example.mysplash;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mysplash.Service.DbUsuarios;
import com.example.mysplash.Service.UsuariosDBService;
import com.example.mysplash.des.MyDesUtil;
import com.example.mysplash.json.MyInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class olvidar extends AppCompatActivity {
    public static List<MyInfo> list;
    public static String json = null;
    public static String TAG = "mensaje";
    public static String TOG = "error";
    public static String cadena= null;
    public MyDesUtil myDesUtil= new MyDesUtil().addStringKeyBase64(registro.KEY);
    public String usr=null;
    public String correo,mensaje;
    EditText usuario,email;
    Button button,button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvidar);
        usuario= findViewById(R.id.clid);
        email=findViewById(R.id.correoRecupera);
        button = findViewById(R.id.recid);
        button1 = findViewById(R.id.loid);

        DbUsuarios dbUsuarios = new DbUsuarios(olvidar.this);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(olvidar.this, login_activity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usr = String.valueOf(usuario.getText());
                correo= String.valueOf(email.getText());
                MyInfo User = dbUsuarios.GetUsuario(usr,correo);
                if(usr.equals("")&&email.equals("")){
                    Toast.makeText(getApplicationContext(), "Completa porfavor", Toast.LENGTH_LONG).show();
                }else{
                    if(User == null){
                        Toast.makeText(getApplicationContext(), "No existe alguno de los dos", Toast.LENGTH_LONG).show();
                    }else{
                        correo=User.getCorreo();
                        String contra=User.getContrasena();
                        final String CADENA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
                        final int LONGITUD = 10;
                        StringBuilder sb = new StringBuilder();
                        for(int i=0;i<LONGITUD;i++){
                            double aleatorio = Math.random()*CADENA.length();
                            int posicion = (int) aleatorio;
                            char letra = CADENA.charAt(posicion);
                            sb.append(letra);
                        }

                        String nueva = sb.toString();
                        mensaje="<html lang=\"en\">\n" +
                                "<head>\n" +
                                "  <meta charset=\"UTF-8\">\n" +
                                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                                "  <title>Recuperar mi contrasena</title>\n" +
                                "  <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css\">\n" +
                                "  <link href=\"https://fonts.googleapis.com/css2?family=Montserrat&display=swap\" rel=\"stylesheet\">\n" +
                                "  <link rel=\"stylesheet\" href=\"style.css\">\n" +
                                "\n" +
                                "</head>\n" +
                                "<body>\n" +
                                "  <main class=\"card\">\n" +
                                "    <header>\n" +
                                "      <center><img src=\"https://mba.americaeconomia.com/sites/mba.americaeconomia.com/files/styles/article_main_image/public/field/image/emails.jpg?itok=1ZSYbr4F\" alt=\"\"></center>\n" +
                                "    </header>\n" +
                                "\n" +
                                "    <section>\n" +
                                "      <h1><center>Recuperar contraseña</center></h1>\n" +
                                "       <br>\n" +
                                "      <h3>Tu anterior contraseña era: "+contra+"</h3>\n" +
                                "       <br>\n" +
                                "      <h3>Tu nueva contraseña es: "+nueva+"</h3>\n" +
                                "        <br>\n" +
                                "      <ul>\n" +
                                "<p><center><img src=\"https://i.kym-cdn.com/photos/images/newsfeed/001/187/956/8c5.gif\" height=\"150\" ></center></p>\n" +
                                "      </ul>\n" +
                                "    </section>\n" +
                                "\n" +
                                "    <footer>\n" +
                                "      <p><strong><center><h3>X-type</h3></center></strong></p>\n" +
                                "    </footer>\n" +
                                "  </main>\n" +
                                "</body>\n" +
                                "</html>\n" +
                                "\n" +
                                "<style>\n" +
                                "    * {\n" +
                                "  margin: 0;\n" +
                                "  padding: 0;\n" +
                                "  box-sizing: border-box;\n" +
                                "}\n" +
                                "\n" +
                                "body {\n" +
                                "  font-family: 'Montserrat', sans-serif;\n" +
                                "  background-color: #bec6ce;\n" +
                                "}\n" +
                                "\n" +
                                ".card {\n" +
                                "  width: 400px;\n" +
                                "  overflow: hidden;\n" +
                                "  position: absolute;\n" +
                                "  top: 50%;\n" +
                                "  left: 50%;\n" +
                                "  transform: translate(-50%, -50%);\n" +
                                "  background: white;\n" +
                                "  border-radius: 15px;\n" +
                                "  box-shadow: 7px 13px 37px rgba(0, 0, 0, .6);\n" +
                                "}\n" +
                                "\n" +
                                "header {\n" +
                                "  width: 100%;\n" +
                                "  height: 200px;\n" +
                                "  overflow: hidden;\n" +
                                "  position: relative;\n" +
                                "}\n" +
                                "\n" +
                                "header::before {\n" +
                                "  content: '';\n" +
                                "  position: absolute;\n" +
                                "  border-top: 30px solid transparent;\n" +
                                "  border-left: 400px solid white;\n" +
                                "  bottom: 0;\n" +
                                "}\n" +
                                "\n" +
                                "header img {\n" +
                                "  width: 100%;\n" +
                                "  height: 199px;\n" +
                                "}\n" +
                                "\n" +
                                "section {\n" +
                                "  padding: 10px;\n" +
                                "  overflow: hidden;\n" +
                                "  text-align: center;\n" +
                                "}\n" +
                                "\n" +
                                "section h2 {\n" +
                                "  margin: .5em 0;\n" +
                                "}\n" +
                                "\n" +
                                "section p {\n" +
                                "  margin: 1em 0;\n" +
                                "}\n" +
                                "\n" +
                                "ul li {\n" +
                                "  list-style: none;\n" +
                                "  display: inline-block;\n" +
                                "  margin: .5em .2em;\n" +
                                "}\n" +
                                "h1 {\n" +
                                "  color: #161840;\n" +
                                "  font-size: 38px;\n" +
                                "  font-family: Arial;\n" +
                                "}\n" +
                                "h3 {\n" +
                                "  color: #2C2E63;\n" +
                                "  font-size: 28px;\n" +
                                "  font-family: Arial;\n" +
                                "}\n" +
                                "\n" +
                                "ul li a {\n" +
                                "  text-decoration: none;\n" +
                                "  color: white;\n" +
                                "  background: #f5c31e;\n" +
                                "  width: 30px;\n" +
                                "  height: 30px;\n" +
                                "  line-height: 30px !important;\n" +
                                "  border-radius: 50%;\n" +
                                "  transition: all .3s ease-in-out;\n" +
                                "}\n" +
                                "\n" +
                                "ul li a:hover {\n" +
                                "  transform: scale(1.2);\n" +
                                "}\n" +
                                "\n" +
                                "footer {\n" +
                                "  width: 100%;\n" +
                                "  height: 80px;\n" +
                                "  background: #031231;\n" +
                                "  color: white;\n" +
                                "  position: relative;\n" +
                                "}\n" +
                                "\n" +
                                "footer::before {\n" +
                                "  content: '';\n" +
                                "  position: absolute;\n" +
                                "  border-bottom: 30px solid transparent;\n" +
                                "  border-right: 400px solid white;\n" +
                                "  top: 0;\n" +
                                "}\n" +
                                "\n" +
                                "footer p {\n" +
                                "  padding: 30px;\n" +
                                "}\n" +
                                "</style>";
                        correo=myDesUtil.cifrar(correo);
                        mensaje=myDesUtil.cifrar(mensaje);
                        boolean f = dbUsuarios.AlterUser(usr,nueva);
                        if(f){
                            if(sendInfo(correo,mensaje)){
                                Toast.makeText(getApplicationContext(), "Correo enviado", Toast.LENGTH_LONG).show();
                            }else{Toast.makeText(getApplicationContext(), "Error con sendinfo", Toast.LENGTH_LONG).show();}

                        }else{
                            Toast.makeText(getApplicationContext(), "ERROR correo no enviado", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });


    }
    public boolean sendInfo( String correo ,String mensaje)
    {
        JsonObjectRequest jsonObjectRequest = null;
        JSONObject jsonObject = null;
        String url = "https://us-central1-nemidesarrollo.cloudfunctions.net/envio_correo";
        RequestQueue requestQueue = null;
        if( correo == null || correo.length() == 0 )
        {
            return false;
        }
        jsonObject = new JSONObject( );
        try
        {
            jsonObject.put("correo" , correo );
            jsonObject.put("mensaje", mensaje);
            String hola = jsonObject.toString();
            Log.i(TAG,hola);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                Log.i(TAG, response.toString());
            }
        } , new  Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e  (TOG, error.toString());
            }
        } );
        requestQueue = Volley.newRequestQueue( getBaseContext() );
        requestQueue.add(jsonObjectRequest);

        return true;
    }

}