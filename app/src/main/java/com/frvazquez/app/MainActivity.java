package com.frvazquez.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPersonName, editTextBirthDate, editTextPhone, editTextEmail, editTextDescription;
    private Button btnNext;
    private int anyio, mes, dia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarDatosContacto();
        mostrarDataPicker();
        verDetalleContacto();
    }

    private void cargarDatosContacto() {
        editTextPersonName = findViewById(R.id.editTextPersonName);
        editTextBirthDate = findViewById(R.id.editTextBirthDate);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextDescription = findViewById(R.id.editTextDescription);

        Bundle parametros = getIntent().getExtras();

        if(parametros != null) {
            String nombreCompleto = parametros.getString(getResources().getString(R.string.pnombre_completo));
            String fechaNacimiento = parametros.getString(getResources().getString(R.string.pfecha_nacimiento));
            String telefono = parametros.getString(getResources().getString(R.string.ptelefono));
            String email = parametros.getString(getResources().getString(R.string.pemail));
            String descripcion = parametros.getString(getResources().getString(R.string.pdescripcion));

            if(nombreCompleto != null || nombreCompleto != "")
                editTextPersonName.setText(nombreCompleto);
            if(fechaNacimiento != null || fechaNacimiento != "")
                editTextBirthDate.setText(fechaNacimiento);
            if(telefono != null || telefono != "")
                editTextPhone.setText(telefono);
            if(email != null || email != "")
                editTextEmail.setText(email);
            if(descripcion != null || descripcion != "")
                editTextDescription.setText(descripcion);

        }

    }

    private void verDetalleContacto() {

            if(btnNext == null)
                btnNext = findViewById(R.id.btnNext);

            editTextPersonName = findViewById(R.id.editTextPersonName);
            editTextBirthDate = findViewById(R.id.editTextBirthDate);
            editTextPhone = findViewById(R.id.editTextPhone);
            editTextEmail = findViewById(R.id.editTextEmail);
            editTextDescription = findViewById(R.id.editTextDescription);

            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, DetalleContacto.class);
                    intent.putExtra(getResources().getString(R.string.pnombre_completo), editTextPersonName.getText().toString());
                    intent.putExtra(getResources().getString(R.string.pfecha_nacimiento), editTextBirthDate.getText().toString());
                    intent.putExtra(getResources().getString(R.string.ptelefono), editTextPhone.getText().toString());
                    intent.putExtra(getResources().getString(R.string.pemail), editTextEmail.getText().toString());
                    intent.putExtra(getResources().getString(R.string.pdescripcion), editTextDescription.getText().toString());
                    startActivity(intent);
                    finish();
                }
        });
    }


    private void mostrarDataPicker() {
        editTextBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialogoFecha =
                        new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int anio, int mesSeleccionado, int diaDelMes) {
                                anyio = anio;
                                mes = mesSeleccionado;
                                dia = diaDelMes;

                                actualizarFechaNacimiento();
                            }
                        }, anyio, mes, dia);
                dialogoFecha.show();
            }
        });
    }

    private void actualizarFechaNacimiento() {
        String fecha = String.format(Locale.getDefault(), "%02d/%02d/%02d", dia, mes+1, anyio);
        editTextBirthDate.setText(fecha);
        Log.i("fecha actualizable", fecha);
    }

}