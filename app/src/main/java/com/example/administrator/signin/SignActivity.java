package com.example.administrator.signin;


import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class SignActivity extends AppCompatActivity {
    private Button mSignInButton;
    private Button mForgetButton;
    private Button mRegistButton;
    private MyEditText mUsernameEdit;
    private MyEditText mPasswordEdit;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        mUsernameEdit = (MyEditText) findViewById(R.id.account_edit);
        mPasswordEdit = (MyEditText) findViewById(R.id.password_edit);
        mCheckBox = (CheckBox) findViewById(R.id.remember_password);

        mForgetButton = (Button) findViewById(R.id.button_key);
        mForgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignActivity.this,R.string.total_toast,Toast.LENGTH_SHORT).show();
            }
        });
        mRegistButton = (Button)findViewById(R.id.button_regists);
        mRegistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignActivity.this,RegiterActivity.class);
                startActivity(i);
            }
        });

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean isRemember = mSharedPreferences.getBoolean("remember_passwork",false);
        if (isRemember){
            String account = mSharedPreferences.getString("account","");
            String password = mSharedPreferences.getString("password","");
            mPasswordEdit.setText(password);
            mUsernameEdit.setText(account);
            mCheckBox.setChecked(true);
        }
        mSignInButton = (Button) findViewById(R.id.button_sign);
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(SignActivity.this,R.string.total_toast,Toast.LENGTH_SHORT).show();
                String account = mUsernameEdit.getText().toString();
                String password = mPasswordEdit.getText().toString();

                SharedPreferences pre = getSharedPreferences("data",MODE_PRIVATE);

                if (account.equals(pre.getString("name",""))&&password.equals(pre.getString("password","")))
                {
                    mEditor = pre.edit();
                    if (mCheckBox.isChecked()){
                        mEditor.putBoolean("remember_password",true);
                    }
                    else {
                        mEditor.clear();
                    }
                    mEditor.commit();
                    Intent intent = new Intent(SignActivity.this,HelloActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(SignActivity.this,"用户名或密码不正确",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
