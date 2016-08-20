package com.example.desarrollador.memoriaalpha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class TerminoCondicion extends AppCompatActivity {
    private Button continuar, salir;

    @Override
    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terminos_condiciones);

        continuar = (Button) findViewById(R.id.seguir);
        salir = (Button) findViewById(R.id.salir4);

        continuar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(TerminoCondicion.this, Inicio.class);
                startActivity(intent);
            }
        });

        salir.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });
    }
}
