package com.example.myapplicationforclass;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AddUserActivity extends AppCompatActivity {
    final Database db = new Database(this);

    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;

    private TextView username;
    private TextView password;
    private Button btnAddUser;
    private Button btnClear;
    private Button btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        username = findViewById(R.id.userNameBOX);
        password = findViewById(R.id.PasswordBOX);
        btnAddUser = findViewById(R.id.AddUserBTN);
        btnClear = findViewById(R.id.ClearBTN);
        btnBack = findViewById(R.id.BackBTN);


        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addUser(new User(username.getText().toString(),password.getText().toString()));
                ShowDialog("User is Add !"); alertDialog = alertDialogBuilder.create(); alertDialog.show();
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setText("");
                password.setText("");
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddUserActivity.this, WorkActivity.class));
            }
        });

    }

    public void ShowDialog(String ss) {
        alertDialogBuilder.setMessage(ss);
    }
}