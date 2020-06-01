package com.hemant.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView email, password;
    Button button;
    private FirebaseAuth mAuth;


    //Make sure to check email and password (empty and null)


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Toast.makeText(this, "Already in",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        button = findViewById(R.id.button);


        mAuth = FirebaseAuth.getInstance();


    }
    public void onRegister(View view){
        String myEmail = email.getText().toString();
        String myPass = password.getText().toString();
        mAuth.createUserWithEmailAndPassword(myEmail, myPass)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("TAG", "createUserWithEmail:success");
                            Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void onLogin(View view){
        String myEmail = email.getText().toString();
        String myPass = password.getText().toString();
        mAuth.signInWithEmailAndPassword(myEmail, myPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Tag", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            String userID = user.getUid().toString();
                            Toast.makeText(MainActivity.this, " OthSussecss", Toast.LENGTH_SHORT).show();
                            Log.i("USER", "USER: "+user.toString());
                            Log.i("USER", "USER: "+userID);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());

                            Toast.makeText(MainActivity.this, " Ooops...", Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
    public void onLogout(View view){
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
    }
}
