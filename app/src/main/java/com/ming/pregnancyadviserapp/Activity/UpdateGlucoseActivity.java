package com.ming.pregnancyadviserapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ming.pregnancyadviserapp.Database.DiabetesDAO;
import com.ming.pregnancyadviserapp.Instance.Diabetes;
import com.ming.pregnancyadviserapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateGlucoseActivity extends AppCompatActivity {

    Button update;
    Button back;

    Spinner glucoseType;

    EditText glucoseValue;

    TextView normalFasting;
    TextView firstHrGlucose;
    TextView secondHrGlucose;

    DiabetesDAO diaDAO;

    SharedPreferences inputData;

    Handler mainHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_glucose);

        initView();
        addListener();
        initData();
    }

    private void initView()
    {
        update=findViewById(R.id.ugbt_update);
        back=findViewById(R.id.ugbt_back);

        glucoseType=findViewById(R.id.ugsp_glucoseType);

        glucoseValue=findViewById(R.id.uget_glucoseValue);

        normalFasting=findViewById(R.id.ugtv_normalFasting);
        firstHrGlucose=findViewById(R.id.ugtv_1hrGlucose);
        secondHrGlucose=findViewById(R.id.ugtv_2hrGlucose);

        diaDAO=new DiabetesDAO();

        inputData=getApplicationContext().getSharedPreferences("inputData", Context.MODE_PRIVATE);

        mainHandler=new Handler(getMainLooper());
    }


    private void addListener()
    {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dpUpdate();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doBack();
            }
        });
    }

    private void initData()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Diabetes instance=diaDAO.getDiabetes(inputData.getInt("id",0));
                if(instance!=null)
                {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            normalFasting.setText("normal fasting glucose: "+"\n"+instance.getfGlucose()+"\n measurement time: "+instance.getFgTime());
                            firstHrGlucose.setText("first hour postprandial glucose: "+"\n"+instance.getOnehrglucose()+"\n measurement time: "+instance.getOneHrTime());
                            secondHrGlucose.setText("second hour postprandial glucose: "+"\n"+instance.getTwohrglucose()+"\n measurement time: "+instance.getTwoHrTime());
                        }
                    });
                }
            }
        }).start();
    }

    private void dpUpdate()
    {

        if(glucoseValue.getText().toString().equals(""))
        {
            Toast.makeText(this,"please input glucose value",Toast.LENGTH_SHORT).show();
            glucoseValue.requestFocus();
            return;
        }
        else {

            final String type = glucoseType.getSelectedItem().toString();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy MM dd HH:mm:ss");

            Date date = new Date(System.currentTimeMillis());

            final String currentTime = simpleDateFormat.format(date);

            switch (type) {
                case "normal fasting Glucose":
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            boolean exists=diaDAO.checkExists(inputData.getInt("id",0));
                            if (exists==false) {
                                Diabetes newDiabetes=new Diabetes();
                                newDiabetes.setId(inputData.getInt("id",0));
                                diaDAO.addDiabetesWithOutParam(newDiabetes);
                                Log.d("add data with id: ",String.valueOf(newDiabetes.getId()));
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        LinearLayout update=findViewById(R.id.ugll_update);
                                        update.setVisibility(View.VISIBLE);
                                    }
                                });
                            }
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    LinearLayout update=findViewById(R.id.ugll_update);
                                    update.setVisibility(View.VISIBLE);
                                }
                            });

                            boolean ok = diaDAO.updatefGlucose(inputData.getInt("id", 0), glucoseValue.getText().toString(), currentTime);
                            if(ok==true)
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(UpdateGlucoseActivity.this,"Update glucose value success",Toast.LENGTH_SHORT).show();
                                    }
                                });

                                final Diabetes getDia=diaDAO.getDiabetes(inputData.getInt("id",0));
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        LinearLayout update=findViewById(R.id.ugll_update);
                                        update.setVisibility(View.GONE);
                                        normalFasting.setText("normal fasting glucose: "+"\n"+getDia.getfGlucose()+"\n measurement time: "+getDia.getFgTime());
                                    }
                                });
                            }else
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        LinearLayout update=findViewById(R.id.ugll_update);
                                        update.setVisibility(View.GONE);
                                        Toast.makeText(UpdateGlucoseActivity.this,"Update glucose value fail",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).start();
                    break;
                case "first hour postprandial glucose":
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Diabetes instance = diaDAO.getDiabetes(inputData.getInt("id", 0));
                            if (instance == null) {
                                Diabetes newDiabetes=new Diabetes();
                                newDiabetes.setId(inputData.getInt("id",0));
                                LinearLayout update=findViewById(R.id.ugll_update);
                                update.setVisibility(View.VISIBLE);
                                diaDAO.addDiabetesWithOutParam(newDiabetes);
                            }
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    LinearLayout update=findViewById(R.id.ugll_update);
                                    update.setVisibility(View.VISIBLE);
                                }
                            });
                            boolean ok = diaDAO.updateOneHrGlucose(inputData.getInt("id", 0), glucoseValue.getText().toString(), currentTime);
                            if(ok==true)
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(UpdateGlucoseActivity.this,"Update glucose value success",Toast.LENGTH_SHORT).show();
                                    }
                                });

                                final Diabetes getDia=diaDAO.getDiabetes(inputData.getInt("id",0));
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        LinearLayout update=findViewById(R.id.ugll_update);
                                        update.setVisibility(View.GONE);
                                        firstHrGlucose.setText("first hour postprandial glucose: "+"\n"+getDia.getOnehrglucose()+"\n measurement time: "+getDia.getOneHrTime());
                                    }
                                });
                            }else
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        LinearLayout update=findViewById(R.id.ugll_update);
                                        update.setVisibility(View.GONE);
                                        Toast.makeText(UpdateGlucoseActivity.this,"Update glucose value fail",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).start();
                    break;
                case "second hour postprandial glucose":
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Diabetes instance = diaDAO.getDiabetes(inputData.getInt("id", 0));
                            if (instance == null) {
                                Diabetes newDiabetes=new Diabetes();
                                newDiabetes.setId(inputData.getInt("id",0));
                                LinearLayout update=findViewById(R.id.ugll_update);
                                update.setVisibility(View.VISIBLE);
                                diaDAO.addDiabetesWithOutParam(newDiabetes);
                            }
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    LinearLayout update=findViewById(R.id.ugll_update);
                                    update.setVisibility(View.VISIBLE);
                                }
                            });
                            boolean ok = diaDAO.updateTwoGlucose(inputData.getInt("id", 0), glucoseValue.getText().toString(), currentTime);
                            if(ok==true)
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(UpdateGlucoseActivity.this,"Update glucose value success",Toast.LENGTH_SHORT).show();
                                    }
                                });

                                final Diabetes getDia=diaDAO.getDiabetes(inputData.getInt("id",0));
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        LinearLayout update=findViewById(R.id.ugll_update);
                                        update.setVisibility(View.GONE);
                                        secondHrGlucose.setText("second hour postprandial glucose: "+"\n"+getDia.getTwohrglucose()+"\n measurement time: "+getDia.getTwoHrTime());
                                    }
                                });
                            }else
                            {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        LinearLayout update=findViewById(R.id.ugll_update);
                                        update.setVisibility(View.GONE);
                                        Toast.makeText(UpdateGlucoseActivity.this,"Update glucose value fail",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).start();
                    break;
                default:
                    break;
            }
        }


    }

    private void doBack()
    {
        finish();
    }
}