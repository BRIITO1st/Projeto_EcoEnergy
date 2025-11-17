package com.example.ecoenergy;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar; // Importar ProgressBar
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPassword;
    private Button signUpButton;
    private TextView textViewLogin;
    private ProgressBar progressBar; // Declarar ProgressBar

    // Declarações do Firebase
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicializa o Firebase Auth e Firestore
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Referências da UI
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        signUpButton = findViewById(R.id.signUpButton);
        textViewLogin = findViewById(R.id.textViewLogin);
        progressBar = findViewById(R.id.progressBar); // Inicializar ProgressBar

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("RegisterActivity", "Botão de cadastro clicado.");
                registerUser(); // Chama o método de registro
            }
        });

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volta para a tela de login
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private void registerUser() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Validações
        if (TextUtils.isEmpty(name)) {
            editTextName.setError("Nome é obrigatório.");
            editTextName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Email é obrigatório.");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Por favor, insira um email válido.");
            editTextEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Senha é obrigatória.");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Senha deve ter no mínimo 6 caracteres.");
            editTextPassword.requestFocus();
            return;
        }

        // --- INÍCIO DA LÓGICA DO FIREBASE ---

        // Mostrar a ProgressBar e desabilitar o botão
        progressBar.setVisibility(View.VISIBLE);
        signUpButton.setEnabled(false);

        // 1. Cria o usuário no Firebase Authentication
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Cadastro na autenticação foi um sucesso
                            Log.d("Firebase", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            // 2. Salva os dados extras (nome) no Firestore
                            saveUserData(user.getUid(), name, email);

                        } else {
                            // Se o cadastro falhar (ex: email já existe)
                            Log.w("Firebase", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Erro ao cadastrar: " + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();

                            // Esconder a ProgressBar e reabilitar o botão
                            progressBar.setVisibility(View.GONE);
                            signUpButton.setEnabled(true);
                        }
                    }
                });
        // --- FIM DA LÓGICA DO FIREBASE ---
    }

    // Método para salvar o nome e email no banco de dados Firestore
    private void saveUserData(String uid, String name, String email) {
        Map<String, Object> userData = new HashMap<>();
        userData.put("name", name);
        userData.put("email", email);

        db.collection("users").document(uid).set(userData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Não precisamos mais esconder o progressbar aqui,
                        // pois a tela Home será aberta

                        if (task.isSuccessful()) {
                            // Dados salvos com sucesso
                            Toast.makeText(RegisterActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();

                            // Navega para a tela Home
                            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            // Erro ao salvar dados no Firestore
                            Toast.makeText(RegisterActivity.this, "Erro ao salvar dados do usuário.", Toast.LENGTH_SHORT).show();

                            // Se falhar, reabilitar os controles
                            progressBar.setVisibility(View.GONE);
                            signUpButton.setEnabled(true);
                        }
                    }
                });
    }
}