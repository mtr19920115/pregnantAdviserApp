package com.ming.pregnancyadviserapp.Activity;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ming.pregnancyadviserapp.Database.UserDAO;
import com.ming.pregnancyadviserapp.Instance.User;
import com.ming.pregnancyadviserapp.R;

public class SignUP extends AppCompatActivity {

    EditText userName;
    EditText passWord;
    EditText rPassWord;
    EditText zipCode;
    EditText firstName;
    EditText lastName;

    Button signUp;
    Button cancel;

    private UserDAO dao;

    private Handler mainHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_u_p);

        initView();
        addListener();
    }

    private void initView()
    {
        userName=findViewById(R.id.suet_userName);
        passWord=findViewById(R.id.suet_passWord);
        rPassWord=findViewById(R.id.suet_rpassWord);
        zipCode=findViewById(R.id.suet_zipCode);
        firstName=findViewById(R.id.suet_firstName);
        lastName=findViewById(R.id.suet_lastName);

        signUp=findViewById(R.id.subt_signUp);
        cancel=findViewById(R.id.subt_cancel);

        dao=new UserDAO();

        mainHandler=new Handler(getMainLooper());
    }

    private void addListener()
    {
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAdd();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rPassWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!rPassWord.getText().toString().equals(passWord.getText().toString()))
                {
                    Toast.makeText(SignUP.this,"The repeat password is different with password!!!",Toast.LENGTH_SHORT).show();
                    rPassWord.setTextColor(Color.parseColor("#FF0000"));
                }
                else
                {
                    rPassWord.setTextColor(Color.parseColor("#000000"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void doAdd()
    {
        final String  uName=userName.getText().toString().trim();
        final String uPassword=passWord.getText().toString().trim();
        final String uZip=String.valueOf(zipCode.getText());
        final String ufn=firstName.getText().toString();
        final String uln=lastName.getText().toString();

        if(TextUtils.isEmpty(uName))
        {
            Toast.makeText(this,"Please input username",Toast.LENGTH_SHORT).show();
            userName.requestFocus();
            return;
        }else
        if(TextUtils.isEmpty(uPassword))
        {
            Toast.makeText(this,"Please input password",Toast.LENGTH_SHORT).show();
            passWord.requestFocus();
            return;
        }else
        if(TextUtils.isEmpty(zipCode.getText().toString()))
        {
            Toast.makeText(this,"Please input zip code",Toast.LENGTH_SHORT).show();
            zipCode.requestFocus();
            return;
        }else
        if(TextUtils.isEmpty(ufn))
        {
            Toast.makeText(this,"Please input first Name",Toast.LENGTH_SHORT).show();
            firstName.requestFocus();
            return;
        }else
        if(TextUtils.isEmpty(uln))
        {
            Toast.makeText(this,"Please input last name",Toast.LENGTH_SHORT).show();
            lastName.requestFocus();
            return;
        }
        else
        {
            final User instance=new User();
            instance.setUserName(uName);
            instance.setPassWord(uPassword);
            instance.setZipCode(Integer.parseInt(uZip));
            instance.setFirstName(ufn);
            instance.setLastName(uln);



            new Thread(new Runnable() {
                @Override
                public void run() {



                    if(dao.checkExists(uName))
                    {
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(SignUP.this,"The user name already exists, change your user name please",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        });
                    }
                    else
                    {
                        final int ok =dao.addUser(instance);

                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (ok!=0)
                                {
                                    Toast.makeText(SignUP.this,"Sign Up successed",Toast.LENGTH_LONG).show();
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(SignUP.this,"Sign Up failed",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        });
                    }
                }
            }).start();
        }
    }
}