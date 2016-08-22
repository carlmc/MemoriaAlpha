package com.example.desarrollador.memoriaalpha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Premio extends AppCompatActivity {
    private Button ter_cond;
    private Button continuar;
    private Button salir;

    @Override
    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.premio);

        ter_cond = (Button) findViewById(R.id.termino_condicion);
        continuar = (Button) findViewById(R.id.continuar);
        salir = (Button) findViewById(R.id.salir3);

        ter_cond.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Premio.this, TerminoCondicion.class);
                startActivity(intent);
            }
        });

        continuar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Premio.this, InterfazJuegoFragment.class);
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
