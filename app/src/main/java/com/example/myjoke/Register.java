package com.example.myjoke;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    EditText txtUserName, txtEmail, txtMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtUserName = findViewById(R.id.txtUserName);
        txtEmail = findViewById(R.id.txtEmail);
        txtMobile = findViewById(R.id.txtPhone);


    }

    public void onRegisterTapped(View view) {
        String name = txtUserName.getText().toString();
        if (name.isEmpty()) {
            txtUserName.setError("Please Fill User Name");
            return;
        }
        String email = txtEmail.getText().toString();
        if (email.isEmpty()) {
            txtEmail.setError("Please Fill Email ");
            return;

        }
        String  phone=txtMobile.getText().toString();
        if(phone.isEmpty()) {
            txtMobile.setError("Please Fill  number");
            return;
        }
        User user=new User(name,email,Integer.parseInt(phone));
        AlertDialog.Builder builder=new AlertDialog.Builder(Register.this);
        builder.setTitle("Sucess");
        builder.setMessage("Congratulations you are sucessfuly registered");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(Register.this,MainActivity.class);
                intent.putExtra(Constants.key_Name, user.getName());
                intent.putExtra(Constants.key_Email , user.getEmail());
                intent.putExtra(Constants.key_Mobile , user.getPhone());
                startActivity(intent);

            }
        });
        builder.show();
    }
}