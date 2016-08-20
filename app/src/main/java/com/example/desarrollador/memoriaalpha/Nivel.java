package com.example.desarrollador.memoriaalpha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Nivel extends AppCompatActivity {
    private Button jugar, salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nivel);

        jugar = (Button) findViewById(R.id.jugar);
        salir = (Button) findViewById(R.id.salir1);

        jugar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Nivel.this, Inicio.class);
                startActivity(intent);
                finish();
            }
        });

        salir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}
