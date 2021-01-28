
package com.ming.pregnancyadviserapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


import com.ming.pregnancyadviserapp.Database.UserDAO;
import com.ming.pregnancyadviserapp.Instance.User;
import com.ming.pregnancyadviserapp.R;

public class Login extends AppCompatActivity {


    Button login;
    Button signup;
    CheckBox rememberPassword;

    EditText userName;
    EditText passWord;

    private SharedPreferences inputData;

    private Handler mainHandler;

    private UserDAO uDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        initViews();
        addListener();
        initData();
    }

    private void initViews()
    {
        login=(Button) findViewById(R.id.lbt_login);
        signup=(Button) findViewById(R.id.lbt_signup);
        userName=(EditText) findViewById(R.id.let_userName);
        passWord=(EditText) findViewById(R.id.let_passWord);
        rememberPassword=(CheckBox) findViewById(R.id.lcb_remeberPassword);

        if(inputData==null)
        {
            inputData=getApplicationContext().getSharedPreferences("inputData", Context.MODE_PRIVATE);
        }

        mainHandler=new Handler(getMainLooper());//get the main thread

        uDAO=new UserDAO();
    }

    private void addListener()
    {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSignUp();
            }
        });

        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SharedPreferences.Editor editor = inputData.edit();
                editor.putString("userName",userName.getText().toString());
                rememberPassword.setChecked(false);
                editor.commit();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        passWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SharedPreferences.Editor editor=inputData.edit();
                editor.putString("password",passWord.getText().toString());
                editor.commit();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        rememberPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences.Editor editor=inputData.edit();
                editor.putBoolean("isRememberPassword",rememberPassword.isChecked());
                editor.commit();
            }
        });
    }

    private void initData()
    {
        userName.setText(inputData.getString("userName",""));
        boolean isRemember=inputData.getBoolean("isRememberPassword",false);
        if(isRemember==true)
        {
            rememberPassword.setChecked(isRemember);
            passWord.setText(inputData.getString("password",""));
        }
        else
        {
            rememberPassword.setChecked(isRemember);
            passWord.setText("");
        }
    }

    private void doLogin()
    {
        final String uName=userName.getText().toString().trim();
        final String uPassWord=passWord.getText().toString().trim();

        if(TextUtils.isEmpty(uName))
        {
            Toast.makeText(this,"please input user name",Toast.LENGTH_SHORT).show();
            userName.requestFocus();
            return;
        }
        else

        if(TextUtils.isEmpty(uPassWord))
        {
            Toast.makeText(this,"please input pass word",Toast.LENGTH_SHORT).show();
            passWord.requestFocus();
            return;
        }else

        new Thread(new Runnable() {
            @Override
            public void run() {
               final User instance=uDAO.getUser(uName,uPassWord);

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(instance==null)
                        {
                            Toast.makeText(Login.this,"Incorrect user name or pass word",Toast.LENGTH_LONG).show();
                            return;
                        }else
                        {
                            int uId=instance.getId();
                            Toast.makeText(Login.this,"Login success",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(Login.this, MainActivity.class);
                            intent.putExtra("id",uId);
                            Log.d("login id",String.valueOf(uId));
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        }).start();
    }

    private void doSignUp()
    {
        Intent intent=new Intent(this,SignUP.class);
        startActivity(intent);
    }
}