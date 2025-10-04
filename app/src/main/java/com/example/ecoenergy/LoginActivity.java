package com.example.ecoenergy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button loginButton;
    private TextView textViewSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.loginButton);
        textViewSignUp = findViewById(R.id.textViewSignUp);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (validateInput(email, password)) {
                    // TODO: Inserir aqui a lógica de login com o banco de dados
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish(); // Fecha a tela de login para o usuário não voltar
                }
            }
        });

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateInput(String email, String password) {
        // Verifica se o email está vazio
        if (email.isEmpty()) {
            editTextEmail.setError("O campo de email não pode estar vazio.");
            editTextEmail.requestFocus();
            return false;
        }

        // Verifica se o formato do email é válido (contém "@", etc.)
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Por favor, insira um email válido.");
            editTextEmail.requestFocus();
            return false;
        }

        // Verifica se a senha está vazia
        if (password.isEmpty()) {
            editTextPassword.setError("O campo de senha não pode estar vazio.");
            editTextPassword.requestFocus();
            return false;
        }

        // Se tudo estiver certo, retorna true
        return true;
    }
}
