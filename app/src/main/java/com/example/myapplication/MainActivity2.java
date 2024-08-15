package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        // Obtener los TextViews
        TextView textViewName = findViewById(R.id.textViewName);
        TextView textViewLastName = findViewById(R.id.textViewLastName);
        TextView textViewEmail = findViewById(R.id.textViewEmail);

        // Obtener los datos del Intent
        String firstName = getIntent().getStringExtra("first_name");
        String lastName = getIntent().getStringExtra("last_name");
        String email = getIntent().getStringExtra("email");

        // Mostrar los datos en los TextViews
        textViewName.setText("Nombre: " + firstName);
        textViewLastName.setText("Apellido: " + lastName);
        textViewEmail.setText("Correo: " + email);
    }
}
