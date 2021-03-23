package com.example.myapplicationforclass;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<User> users = new ArrayList<>();

    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;

    User Rec1 = new User("admin", "1234");
    final Database db = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView userName = (TextView) findViewById(R.id.userNameBOX);
        final TextView password = (TextView) findViewById(R.id.PasswordBOX);
        final Button login = (Button) findViewById(R.id.LoginBUT);

        ImageView img = (ImageView) findViewById(R.id.imageView);

        img.setImageResource(R.mipmap.ic_launcher);

        alertDialogBuilder = new AlertDialog.Builder(this);

        db.addUser(Rec1);
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                users = db.getAllUsers();
                boolean log = true;

                for (User us : users)
                {

                    if(userName.getText().toString().equals(us.get_UserName()) && password.getText().toString().equals(us.get_password()))
                    {
                        log = false;
                        open("Login Successful !", log);
                        alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        break;
                    }
                }
                if(log)
                {
                    open("Unsuccessful !",log);
                    alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }
        });
    }

    public void open(String ss, final boolean log) {

        alertDialogBuilder.setMessage(ss);

        alertDialogBuilder.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

                if (log == false) startActivity(new Intent(MainActivity.this, WorkActivity.class));
            }
        });
        /*         alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
         finish();             }
          });   */
    }
}

