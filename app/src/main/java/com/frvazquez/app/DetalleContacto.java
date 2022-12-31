package com.frvazquez.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetalleContacto extends AppCompatActivity {

    private TextView tvNombre, tvFechaNacimiento, tvTelefono, tvEmail, tvDescripcion;
    private Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);
        cargarDatosContacto();
        editarContacto();
    }

    private void cargarDatosContacto() {
        tvNombre = findViewById(R.id.tvNombre);
        tvFechaNacimiento = findViewById(R.id.tvFechaNacimiento);
        tvTelefono = findViewById(R.id.tvTelefono);
        tvEmail = findViewById(R.id.tvEmail);
        tvDescripcion = findViewById(R.id.tvDescripcion);

        Bundle parametros = getIntent().getExtras();

        String nombreCompleto = parametros.getString(getResources().getString(R.string.pnombre_completo));
        String fechaNacimiento = parametros.getString(getResources().getString(R.string.pfecha_nacimiento));
        String telefono = parametros.getString(getResources().getString(R.string.ptelefono));
        String email = parametros.getString(getResources().getString(R.string.pemail));
        String descripcion = parametros.getString(getResources().getString(R.string.pdescripcion));

        tvNombre.setText(getResources().getString(R.string.dnombre_completo));
        tvFechaNacimiento.setText(getResources().getString(R.string.dfecha_nacimiento));
        tvTelefono.setText(getResources().getString(R.string.dtelefono));
        tvEmail.setText(getResources().getString(R.string.demail));
        tvDescripcion.setText(getResources().getString(R.string.ddescripcion));


        Log.i("datos", nombreCompleto+" "+telefono+" "+getResources().getString(R.string.dnombre_completo));
        if(nombreCompleto != null && !nombreCompleto.isEmpty())
            tvNombre.setText(nombreCompleto);
        if(fechaNacimiento != null && !fechaNacimiento.isEmpty())
            tvFechaNacimiento.setText(fechaNacimiento);
        if(telefono != null && !telefono.isEmpty())
            tvTelefono.setText(telefono);
        if(email != null && !email.isEmpty())
            tvEmail.setText(email);
        if(descripcion != null && !descripcion.isEmpty())
            tvDescripcion.setText(descripcion);
    }

    private void editarContacto() {
        if(null == btnEdit)
            btnEdit = findViewById(R.id.btnEdit);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetalleContacto.this, MainActivity.class);
                intent.putExtra(getResources().getString(R.string.pnombre_completo), tvNombre.getText().toString());
                intent.putExtra(getResources().getString(R.string.pfecha_nacimiento), tvFechaNacimiento.getText().toString());
                intent.putExtra(getResources().getString(R.string.ptelefono), tvTelefono.getText().toString());
                intent.putExtra(getResources().getString(R.string.pemail), tvEmail.getText().toString());
                intent.putExtra(getResources().getString(R.string.pdescripcion), tvDescripcion.getText().toString());
                startActivity(intent);

            }
        });
    }
}