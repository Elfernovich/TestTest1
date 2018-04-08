package com.example.anders.createuserapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    ProgressBar progressBar;
    EditText editTextEmail, editTextPassword, editTextConfirmPassword;

    // Declare an instance of FirebaseAuth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextEmail = (EditText) findViewById(R.id.editTextEmailNew);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordNew);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPasswordNew);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //Initialize the FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.btn_SignUp).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email er påkrævet");
            editTextEmail.requestFocus();
            return;
        }

        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Indtast venligst en gyldig email");
            editTextEmail.requestFocus();
            return;
        }

        else if (password.isEmpty()){
            editTextPassword.setError("Kodeord er påkrævet");
            editTextPassword.requestFocus();
            return;
        }

        else if(password.length()<6){
            editTextPassword.setError("Det indtastede kodeord skal minimum bestå af 6 tegn!");
            editTextPassword.requestFocus();
            return;
        }

        else if(confirmPassword.isEmpty()){
            editTextConfirmPassword.setError("Bekræft dit kodeord");
            editTextConfirmPassword.requestFocus();
            return;
        }

        else if(!password.equals(confirmPassword)){
            editTextConfirmPassword.setError("Dit kodeord matcher ikke bekræft kodeord");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    finish();
                    Toast.makeText(getApplicationContext(), "Din konto er nu oprettet", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, UserDataActivity.class);
                    //Prevent the user to be able to go back to the login activity while pressing "back" button
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "Din email er allerede registreret", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Der er desværre sket en fejl, prøv igen", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_SignUp:
                registerUser();
                break;

            case R.id.textViewLogin:
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }


}
