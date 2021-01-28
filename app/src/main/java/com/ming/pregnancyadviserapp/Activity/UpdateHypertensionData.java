package com.ming.pregnancyadviserapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ming.pregnancyadviserapp.Database.BloodPressureDAO;
import com.ming.pregnancyadviserapp.Instance.BloodPressure;
import com.ming.pregnancyadviserapp.Logic.Compute;
import com.ming.pregnancyadviserapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UpdateHypertensionData extends AppCompatActivity {

    SharedPreferences inputData;

    BloodPressureDAO bpDAO;

    Button update;
    Button back;

    EditText highBloodPressure;
    EditText lowBloodPressure;

    LinearLayout dataDisaplayLL;
    LinearLayout updateData;

    TextView showData;

    Handler mainHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_hypertension_data);

        initView();
        addListeners();
        initData();

    }

    private void initView() {
        update=(Button) findViewById(R.id.uhdbt_update);
        back=(Button) findViewById(R.id.uhdbt_back);

        highBloodPressure=(EditText) findViewById(R.id.uhdet_highBloodPressure);
        lowBloodPressure=(EditText) findViewById(R.id.uhdet_lowBloodPressure);

        dataDisaplayLL=(LinearLayout) findViewById(R.id.uhdll_showData);
        updateData=(LinearLayout) findViewById(R.id.uhdll_update);

        showData=(TextView) findViewById(R.id.uhdtv_showdata);

        bpDAO=new BloodPressureDAO();

        inputData=getApplicationContext().getSharedPreferences("inputData", Context.MODE_PRIVATE);

        mainHandler=new Handler(getMainLooper());

    }

    private void addListeners() {

        highBloodPressure.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkBloodPressure();
            }
        });

        lowBloodPressure.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkBloodPressure();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

    }

    private void initData()
    {


        dataDisaplayLL.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                final  List<BloodPressure> bloodPressureList=bpDAO.getBloodPressure(inputData.getInt("id",0));
               if(bloodPressureList!=null)
               {
                   mainHandler.post(new Runnable() {
                       @Override
                       public void run() {
                           for(int i=0;i<bloodPressureList.size();i++)
                           {

                               showData.setText(showData.getText().toString()+"High Blood Pressure: "+bloodPressureList.get(i).getHighBloodPressure()
                                       +"\n Low Blood Pressure: "+bloodPressureList.get(i).getLowBloodPressure()+"\n Measure Time: "+bloodPressureList.get(i).getTimeStamper()+"\n"+"  \n");

                           }
                       }
                   });

               }
            }
        }).start();
    }

    private void checkBloodPressure()
    {
        boolean emergence=false;
        Compute check=new Compute();
        if(!highBloodPressure.getText().toString().equals("")&&!lowBloodPressure.getText().toString().equals(""))
            emergence=check.checkBooldPressure(Integer.parseInt(highBloodPressure.getText().toString()),Integer.parseInt(lowBloodPressure.getText().toString()));
        if(emergence==true)
            Toast.makeText(this,"Boold pressure is too high!!! Go to emergence room immediately!!!",Toast.LENGTH_LONG).show();
    }

    private void updateData()
    {
        if(highBloodPressure.getText().toString().equals(""))
        {
            Toast.makeText(this,"please input high blood pressure!",Toast.LENGTH_SHORT).show();
            highBloodPressure.requestFocus();
            return;
        }else
            if(lowBloodPressure.getText().toString().equals(""))
            {
                Toast.makeText(this,"please input low blood pressure!",Toast.LENGTH_SHORT).show();
                lowBloodPressure.requestFocus();
                return;
            }else {

                updateData.setVisibility(View.VISIBLE);

                showData.setText("");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy MM dd HH:mm:ss");

                        Date date = new Date(System.currentTimeMillis());

                        final String currentTime = simpleDateFormat.format(date);

                        BloodPressure bloodPressure=new BloodPressure();
                        bloodPressure.setId(inputData.getInt("id",0));
                        bloodPressure.setHighBloodPressure(Integer.parseInt(highBloodPressure.getText().toString()));
                        bloodPressure.setLowBloodPressure(Integer.parseInt(lowBloodPressure.getText().toString()));
                        bloodPressure.setTimeStamper(currentTime);

                        boolean ok=bpDAO.addBloodPressure(bloodPressure);
                        if(ok==true)
                        {
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    updateData.setVisibility(View.GONE);
                                    Toast.makeText(UpdateHypertensionData.this,"blood Pressure data update succeed",Toast.LENGTH_SHORT).show();
                                    dataDisaplayLL.setVisibility(View.VISIBLE);
                                    initData();
                                    showData.requestFocus();
                                }
                            });
                        }else{
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(UpdateHypertensionData.this,"blood Pressure data update failed",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();

            }
    }

    private void goBack()
    {
        finish();
    }
}