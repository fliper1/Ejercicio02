package com.cieep.ejercicio02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cieep.ejercicio02.modelos.Nota;

public class AgregarActivity extends AppCompatActivity {

    // Variables para Vista
    private EditText txtTitulo, txtContenido;
    private Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        txtContenido = findViewById(R.id.txtContenidoAgregar);
        txtTitulo = findViewById(R.id.txtTituloAgregar);
        btnAgregar = findViewById(R.id.btnGuardarAgregar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtTitulo.getText().toString().isEmpty() &&
                    !txtContenido.getText().toString().isEmpty()) {
                    Nota nota = new Nota(txtTitulo.getText().toString(), txtContenido.getText().toString());
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("NOTA", nota);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else {
                    Toast.makeText(AgregarActivity.this, "Todos es Obligatorio", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}