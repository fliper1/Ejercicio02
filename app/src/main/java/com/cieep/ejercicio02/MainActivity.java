package com.cieep.ejercicio02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cieep.ejercicio02.modelos.Nota;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int EDIT_NOTA = 2;
    private final int AGREGAR_NOTA = 1;
    // Vista
    private LinearLayout contenedor;
    private Button btnAgregar;

    // Modelo Datos
    private ArrayList<Nota> listaNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contenedor = findViewById(R.id.contenedorMain);
        btnAgregar = findViewById(R.id.btnAgregarMain);
        listaNotas = new ArrayList<>();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AgregarActivity.class);
                startActivityForResult(intent, AGREGAR_NOTA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AGREGAR_NOTA && resultCode == RESULT_OK) {
            if (data != null){
                final Nota nota = data.getExtras().getParcelable("NOTA");
                listaNotas.add(nota);
                repintaNotas();
            }
        }


        if (requestCode == EDIT_NOTA && resultCode == RESULT_OK) {
            if (data != null) {
                Nota nota = data.getExtras().getParcelable("NOTA");
                int posicion = data.getExtras().getInt("POS");
                listaNotas.get(posicion).setTitulo(nota.getTitulo());
                listaNotas.get(posicion).setContenido(nota.getContenido());
                repintaNotas();
            }
        }
    }

    private void repintaNotas() {
        
        contenedor.removeAllViews();
        for (int i = 0; i < listaNotas.size(); i++) {

            final Nota nota = listaNotas.get(i);
            final int posicion = i;

            View filaNota = LayoutInflater.from(this).inflate(R.layout.fila_nota, null);


            TextView txtTitulo = filaNota.findViewById(R.id.txtTituloFilaNota);
            txtTitulo.setText(nota.getTitulo());


            txtTitulo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("NOTA", nota);
                    bundle.putInt("POS", posicion);
                    Intent intent = new Intent(MainActivity.this, EditNotaActivity.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, EDIT_NOTA);
                }
            });
            // 2. Button para eliminar
            ImageButton btnEliminar = filaNota.findViewById(R.id.btnEliminarFilaNota);


            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listaNotas.remove(posicion);
                    repintaNotas();
                }
            });

            // 4. Insertar el Linear en el contenedor
            contenedor.addView(filaNota);


        }
    }
}