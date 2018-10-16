package com.example.gio10.lospaisesdelmundo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.gio10.lospaisesdelmundo.model.DaoPais;
import com.example.gio10.lospaisesdelmundo.model.Pais;
import com.example.gio10.lospaisesdelmundo.model.PaisDbHelper;

import java.util.ArrayList;

public class ListaPaises extends AppCompatActivity {

    PaisDbHelper basedatos;
    ListView listado;
    ArrayList<Pais> paises = new ArrayList<>();
    Cursor cur;
    SimpleCursorAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_paises);
        listado = (ListView) findViewById(R.id.listaPais);
        cur = GetAllData();
        llenar(cur);
        String from [] = new String[]{DaoPais.PaisEntry.COLUMN_NAME_NOMBRE,DaoPais.PaisEntry.COLUMN_NAME_CAPITAL,DaoPais.PaisEntry.COLUMN_NAME_CONTINENTE};
        int to [] = new int[] {R.id.txtElNombre,R.id.txtLaCapital,R.id.txtElcontin};
        adaptador = new SimpleCursorAdapter(this,R.layout.lista_detalle_pais,cur,from,to,0);
        listado.setAdapter(adaptador);
        close();
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Pais p = paises.get(i);
                String cadena = p.getNombre() + " "  + p.getCapital() + " " + p.getContinente();
                Toast.makeText(ListaPaises.this,"Item seleccionado = " + i + "  " + cadena, Toast.LENGTH_LONG).show();
            }
        });

    }

    public void llenar(Cursor c){
        String nombre, capital, conti, idiom, moned, gobierno;
        int nHab;
        while(c.moveToNext()){
            nombre = c.getString(1);
            capital = c.getString(2);
            nHab = Integer.parseInt(c.getString(3));
            conti = c.getString(4);
            idiom = c.getString(5);
            moned = c.getString(6);
            gobierno = c.getString(7);
            Pais p = new Pais(nombre,capital,nHab,conti,idiom,moned,gobierno);
            paises.add(p);
        }
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

    public Cursor GetAllData()
    {
        abrir();
        SQLiteDatabase sqldata = basedatos.getReadableDatabase();
        Cursor c = sqldata.rawQuery("SELECT * FROM " + DaoPais.PaisEntry.TABLE_NAME, null);

        return c;
    }

}
