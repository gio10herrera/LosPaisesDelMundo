package com.example.gio10.lospaisesdelmundo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gio10.lospaisesdelmundo.model.DaoPais;
import com.example.gio10.lospaisesdelmundo.model.Pais;
import com.example.gio10.lospaisesdelmundo.model.PaisDbHelper;

public class DatosPais extends AppCompatActivity {

    Pais p;
    TextView tv;
    Button borrar, actualizar;
    int i;
    PaisDbHelper basedatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_pais);
        p = getIntent().getParcelableExtra("pais");
        i = getIntent().getIntExtra("i",-1);
        tv = findViewById(R.id.txtViewDatosPais);
        borrar = findViewById(R.id.btnBorrar);
        actualizar = findViewById(R.id.btnActualizar);

        String datos = "NOMBRE: " + p.getNombre() + "\nCAPITAL: " + p.getCapital() + "\n"
                + "NUMERO DE HABITANTES: " + p.getnHabitantes() + "\nCONTINENTE: " + p.getContinente()
                + "\nIDIOMA OFICIAL: " + p.getIdiomaOficial() + "\nMONEDA: " + p.getMoneda()
                + "\nTIPO DE GOBIERNO: " + p.getTipoGobierno();
        tv.setText(datos);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarFila();
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizaFilas();
            }
        });

    }

    public void actualizaFilas(){
        Intent elIntent = new Intent(this,Actualizar.class);
        elIntent.putExtra("pais",p);
        startActivity(elIntent);
        finish();
    }

    public void borrarFila(){
        abrir();
        try {
            SQLiteDatabase sqldata = basedatos.getWritableDatabase();
            sqldata.delete(DaoPais.PaisEntry.TABLE_NAME,DaoPais.PaisEntry.COLUMN_NAME_NOMBRE+"=?",new String[]{p.getNombre()});
            Toast.makeText(this,"ELIMINACIÓN EXITOSA", Toast.LENGTH_LONG).show();
            Intent it = new Intent(this,ListaPaises.class);
            startActivity(it);
            finish();

        }
        catch (Exception e){
            Toast.makeText(this,"Error al eliminar Datos", Toast.LENGTH_LONG).show();
        }
        close();
    }

    public void abrir(){
        try {
            basedatos = new PaisDbHelper(this);
        }
        catch(Exception e){
            Toast.makeText(this,"Error al crear base datos", Toast.LENGTH_LONG).show();
        }
    }

    public void close(){
        try {
            basedatos.close();
        }
        catch(Exception e){
            Toast.makeText(this,"Error al cerrar base datos", Toast.LENGTH_LONG).show();
        }
    }
}
