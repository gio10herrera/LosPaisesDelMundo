package com.example.gio10.lospaisesdelmundo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gio10.lospaisesdelmundo.model.DaoPais;
import com.example.gio10.lospaisesdelmundo.model.Pais;
import com.example.gio10.lospaisesdelmundo.model.PaisDbHelper;

public class Actualizar extends AppCompatActivity {

    TextView nom, cont;
    EditText cap, nHab, idOficial, moneda;
    Spinner ActTGob;
    Button btnActualizar;
    Pais p;
    PaisDbHelper basedatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        ActTGob = findViewById(R.id.spinnerActTipGob);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterGob = ArrayAdapter.createFromResource(this,
                R.array.gobiernos_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterGob.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        ActTGob.setAdapter(adapterGob);
        p = getIntent().getParcelableExtra("pais");
        nom = findViewById(R.id.txtTvName);
        cont = findViewById(R.id.txtTvConti);
        nom.setText("NOMBRE DEL PAIS: " + p.getNombre().toUpperCase());
        cont.setText("CONTINENTE: " + p.getContinente().toUpperCase());
        btnActualizar = findViewById(R.id.btnActualizar2);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar();
            }
        });
    }

    public void actualizar(){
        abrir();
        try {
            SQLiteDatabase sqldata = basedatos.getWritableDatabase();
            ContentValues values = new ContentValues();
            cap = (EditText) findViewById(R.id.txtActCap);
            nHab = (EditText) findViewById(R.id.txtActNHab);
            idOficial = (EditText) findViewById(R.id.txtActIdioma);
            moneda = (EditText) findViewById(R.id.txtActMoneda);

            values.put(DaoPais.PaisEntry.COLUMN_NAME_CAPITAL, cap.getText().toString());
            values.put(DaoPais.PaisEntry.COLUMN_NAME_NHABITANTES, nHab.getText().toString());
            values.put(DaoPais.PaisEntry.COLUMN_NAME_IDIOMA, idOficial.getText().toString());
            values.put(DaoPais.PaisEntry.COLUMN_NAME_MONEDA, moneda.getText().toString());
            values.put(DaoPais.PaisEntry.COLUMN_NAME_TGOBIERNO, ActTGob.getSelectedItem().toString());

            sqldata.update(DaoPais.PaisEntry.TABLE_NAME,values,DaoPais.PaisEntry.COLUMN_NAME_NOMBRE+"=?",new String[]{p.getNombre()});
            Toast.makeText(this,"ACTUALIZACION EXITOSA", Toast.LENGTH_LONG).show();

        }
        catch (Exception e){
            Toast.makeText(this,"Error al actualizar Datos", Toast.LENGTH_LONG).show();
        }
        close();

        Intent i = new Intent(Actualizar.this,ListaPaises.class);
        startActivity(i);
        finish();
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
