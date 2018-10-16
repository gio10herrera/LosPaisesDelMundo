package com.example.gio10.lospaisesdelmundo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPrincipal extends AppCompatActivity {
    Button about,appPaises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        about = findViewById(R.id.btnAcercaDe);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuPrincipal.this,AcercaDe.class);
                startActivity(i);
            }
        });

        appPaises = findViewById(R.id.btnAplicacion);
        appPaises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MenuPrincipal.this,MainActivity.class);
                startActivity(it);
            }
        });
    }
}
