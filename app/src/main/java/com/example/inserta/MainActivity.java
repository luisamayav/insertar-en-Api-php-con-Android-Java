package com.example.inserta;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText etNombre, etCorreo;
    Button btnEnviar;
    TextView tvResultado;

    String URL = "http://192.168.12.67/insertar.php"; // cambia si a tu api
    // ejecuta cmd y escribe ipconfig y anota tu ip
    // si fuera un servidor web, debes colocar la url hasta tu php

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        btnEnviar = findViewById(R.id.btnEnviar);
        tvResultado = findViewById(R.id.tvResultado);

        btnEnviar.setOnClickListener(v -> enviarDatos());
    }

    private void enviarDatos() {
        String nombre = etNombre.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST, URL,
                response -> {
                    tvResultado.setText("Respuesta: " + response);
                    etNombre.setText("");
                    etCorreo.setText("");
                },
                error -> tvResultado.setText("Error: " + error.toString())) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> datos = new HashMap<>();
                datos.put("name", nombre);     // Deben coincidir con $_POST['name']
                datos.put("email", correo);    // y $_POST['email'] en el PHP
                return datos;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}