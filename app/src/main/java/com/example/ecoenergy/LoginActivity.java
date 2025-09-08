package com.example.ecoenergy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button loginButton;
    private TextView textViewSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializa os componentes da tela
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.loginButton);
        textViewSignUp = findViewById(R.id.textViewSignUp);

        // Ação do botão de login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (validateInput(email, password)) {
                    // --- ESPAÇO PARA LÓGICA DO BANCO DE DADOS ---
                    // Aqui você chamará o método para autenticar o usuário
                    Toast.makeText(LoginActivity.this, "Login bem-sucedido (simulação)", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Ação do texto para ir para a tela de cadastro
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre a tela de cadastro
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateInput(String email, String password) {
        if (email.isEmpty()) {
            editTextEmail.setError("Email é obrigatório");
            editTextEmail.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Senha é obrigatória");
            editTextPassword.requestFocus();
            return false;
        }

        // Adicionar mais validações se necessário (ex: formato de email)
        return true;
    }
}
