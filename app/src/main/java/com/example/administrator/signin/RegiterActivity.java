package com.example.administrator.signin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class RegiterActivity extends AppCompatActivity {
    private Button mRegistButton1;
    private MyEditText mAccount;
    private MyEditText mPassword;
    String TAG = "SignActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiter);

        mAccount = (MyEditText) findViewById(R.id.account);
        mPassword = (MyEditText) findViewById(R.id.password);

        mRegistButton1 = (Button) findViewById(R.id.button_regist1);
        mRegistButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegiterActivity.this, SignActivity.class);
                startActivity(intent);
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("name",mAccount.getText().toString());
                editor.putString("password",mPassword.getText().toString());
                editor.commit();
                Toast.makeText(RegiterActivity.this,R.string.regitster_toast,Toast.LENGTH_SHORT).show();

            }
        });
    }
}
