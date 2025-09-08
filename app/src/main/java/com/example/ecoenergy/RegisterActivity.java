package com.example.ecoenergy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    // ... (variáveis existentes)
    private TextView textViewLogin; // Adicione esta variável

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // ... (inicializações existentes)
        textViewLogin = findViewById(R.id.textViewLogin); // Inicialize o novo TextView

        // ... (seu código existente do signUpButton.setOnClickListener) ...

        // Adicione este bloco para o clique no texto de login
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fecha a tela de cadastro e volta para a de login
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Opcional: fecha a tela de cadastro para não ficar na pilha
            }
        });
    }

    // ... (seu método validateInput existente) ...
}
