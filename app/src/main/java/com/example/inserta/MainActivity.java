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
    EditText etNombre;
    Button btnInsertar;
    TextView tvRespuesta;

    String URL = "http://192.168.12.67/insertar.php"; // cambia si a tu api
    // ejecuta cmd y escribe ipconfig y anota tu ip
    // si fuera un servidor web, debes colocar la url hasta tu php

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        btnInsertar = findViewById(R.id.btnInsertar);
        tvRespuesta = findViewById(R.id.tvRespuesta);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarNombre();
            }
        });
    }

    private void insertarNombre() {
        String nombre = etNombre.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tvRespuesta.setText(response);
                        etNombre.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tvRespuesta.setText("Error: " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> datos = new HashMap<>();
                datos.put("nombre", nombre);
                datos.put("correo", "ejemplo@correo.com"); // aqui se escribe lo que quiereas mandar al php
                return datos;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
