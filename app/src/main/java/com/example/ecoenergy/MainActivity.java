package com.example.ecoenergy;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Declaração dos componentes da UI
    private Button emailButton;
    private Button gmailButton;
    private TextView signUpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa os componentes da UI buscando pelos seus IDs no layout
        emailButton = findViewById(R.id.emailButton);
        gmailButton = findViewById(R.id.gmailButton);
        signUpTextView = findViewById(R.id.signUpTextView);

        // Define o que acontece quando o botão de Email é clicado
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ação para login com Email
                // Por enquanto, exibimos uma mensagem temporária (Toast)
                Toast.makeText(MainActivity.this, "Botão 'Continue com Email' clicado", Toast.LENGTH_SHORT).show();
                // Aqui você navegaria para a tela de login com email
            }
        });

        // Define o que acontece quando o botão de Gmail é clicado
        gmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ação para login com Gmail
                Toast.makeText(MainActivity.this, "Botão 'Continue com Gmail' clicado", Toast.LENGTH_SHORT).show();
                // Aqui você iniciaria o fluxo de login com a API do Google
            }
        });

        // Define o que acontece quando o texto de Sign Up é clicado
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ação para cadastro (Sign Up)
                Toast.makeText(MainActivity.this, "Texto 'Cadastre-se' clicado", Toast.LENGTH_SHORT).show();
                // Aqui você navegaria para a tela de cadastro
            }
        });
    }
}
