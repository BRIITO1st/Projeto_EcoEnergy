package com.example.ecoenergy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button emailButton;
    private Button gmailButton;
    private TextView signUpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailButton = findViewById(R.id.emailButton);
        gmailButton = findViewById(R.id.gmailButton);
        signUpTextView = findViewById(R.id.signUpTextView);

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Botão 'Continue com Email' clicado", Toast.LENGTH_SHORT).show();
                // TODO: Navegar para a tela de login com email
            }
        });

        gmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Botão 'Continue com Gmail' clicado", Toast.LENGTH_SHORT).show();
                // TODO: Iniciar o fluxo de login com a API do Google
            }
        });

        // Define o que acontece quando o texto de Sign Up é clicado
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre a tela de cadastro (RegisterActivity)
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
