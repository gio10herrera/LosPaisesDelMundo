package com.example.gio10.lospaisesdelmundo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gio10.lospaisesdelmundo.model.DaoPais;
import com.example.gio10.lospaisesdelmundo.model.PaisDbHelper;

public class MainActivity extends AppCompatActivity{

    EditText nombre, capital, nHab, idiom, moned;
    Spinner contin, tGob;

    Button guardar, mostrar;

    PaisDbHelper basedatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contin = findViewById(R.id.spinnerCont);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.continentes_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        contin.setAdapter(adapter);

        tGob = findViewById(R.id.spinnerTipGob);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterGob = ArrayAdapter.createFromResource(this,
                R.array.gobiernos_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterGob.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        tGob.setAdapter(adapterGob);

        guardar = (Button) findViewById(R.id.btnguardar);
        mostrar = (Button) findViewById(R.id.btnmostrar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });
        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ListaPaises.class);
                startActivity(i);
            }
        });

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

    public void guardar(){
        abrir();
        try {
            SQLiteDatabase sqldata = basedatos.getWritableDatabase();
            ContentValues values = new ContentValues();
            nombre = (EditText) findViewById(R.id.txtNombPais);
            capital = (EditText) findViewById(R.id.txtCapital);
            nHab = (EditText) findViewById(R.id.txtNHabitantes);


            idiom = (EditText) findViewById(R.id.txtIdioma);
            moned = (EditText) findViewById(R.id.txtMoneda);

            values.put(DaoPais.PaisEntry.COLUMN_NAME_NOMBRE, nombre.getText().toString());
            values.put(DaoPais.PaisEntry.COLUMN_NAME_CAPITAL, capital.getText().toString());
            values.put(DaoPais.PaisEntry.COLUMN_NAME_NHABITANTES, nHab.getText().toString());
            values.put(DaoPais.PaisEntry.COLUMN_NAME_CONTINENTE, contin.getSelectedItem().toString());
            values.put(DaoPais.PaisEntry.COLUMN_NAME_IDIOMA, idiom.getText().toString());
            values.put(DaoPais.PaisEntry.COLUMN_NAME_MONEDA, moned.getText().toString());
            values.put(DaoPais.PaisEntry.COLUMN_NAME_TGOBIERNO, tGob.getSelectedItem().toString());

            long newRowId = sqldata.insert(DaoPais.PaisEntry.TABLE_NAME, null, values);
            Toast.makeText(this,"INSERCIÔN EXITOSA", Toast.LENGTH_LONG).show();
            close();
        }
        catch (Exception e){
            Toast.makeText(this,"Error al insertar Datos", Toast.LENGTH_LONG).show();
        }
    }
}
