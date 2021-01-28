package com.ming.pregnancyadviserapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ming.pregnancyadviserapp.Database.BasicDataDAO;
import com.ming.pregnancyadviserapp.Database.DiabetesDAO;
import com.ming.pregnancyadviserapp.Database.HypertensionDAO;
import com.ming.pregnancyadviserapp.Database.PregnancyHistoryDAO;
import com.ming.pregnancyadviserapp.Database.UserDAO;
import com.ming.pregnancyadviserapp.Instance.BasicData;
import com.ming.pregnancyadviserapp.Instance.Diabetes;
import com.ming.pregnancyadviserapp.Instance.Hypertension;
import com.ming.pregnancyadviserapp.Instance.PregnancyHistory;
import com.ming.pregnancyadviserapp.Instance.User;
import com.ming.pregnancyadviserapp.Logic.Compute;
import com.ming.pregnancyadviserapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



public class MainActivity extends AppCompatActivity {


    LinearLayout ll_ph;
    LinearLayout ll_hy;
    LinearLayout ll_dia;
    LinearLayout ll_updating;
    TextView id;
    EditText et_ph;
    EditText height;
    EditText weight;
    EditText medicalHistory;
   // EditText et_hy;
   // EditText et_lowp;

    Button updateBloodPressure;
    EditText et_longBPM;
    EditText et_longPM;
    EditText et_longCM;

    EditText et_diaYear;
    Button diaUpdate;

    TextView eddDate;

    TextView tv_firstName;
    TextView tv_lastName;
    TextView bmi;
    TextView tvDate;
    DatePicker dpDate;
    Button submit;
    Button view;
    Button schedule;
    Button logOut;
    Spinner dateType;
    Spinner sp_diaType;
    CheckBox hyper;
    CheckBox diabetes;
    CheckBox pregnancyHistoryViewer;

    Button labSection;

    private SharedPreferences inputData;
    private SharedPreferences dynamicData;

    private int mDay;
    private int mMonth;
    private int mYear;


    private Calendar mainDate;



    private UserDAO userDAO;
    private BasicDataDAO basicDAO;
    private PregnancyHistoryDAO phDAO;
    private HypertensionDAO hyperDAO;
    private DiabetesDAO diaDAO;

    private Handler mainHandler;

    private boolean okToSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("GET UID: ",String.valueOf(getIntent().getIntExtra("id",0)));

        initViews();
        getUID();
        initData();


    }

    private void getUID()
    {
        if(inputData==null)
        {
            inputData=getApplicationContext().getSharedPreferences("inputData", Context.MODE_PRIVATE);
        }

        SharedPreferences.Editor editor=inputData.edit();

        if(getIntent().getIntExtra("id",0)==inputData.getInt("id",0))
        {
            editor.putInt("sameUser",1);
            editor.commit();
        }
        else
        {
            editor.putInt("sameUser",0);
            editor.commit();
        }
        editor.putInt("id",getIntent().getIntExtra("id",0));
        editor.commit();
    }

    private void initViews()
    {
        eddDate=findViewById(R.id.tv_EDDDate);
        ll_ph=(LinearLayout) findViewById(R.id.ll_ph);
        ll_hy=(LinearLayout) findViewById(R.id.ll_hy);
        ll_dia=(LinearLayout) findViewById(R.id.ll_dia);
        ll_updating=findViewById(R.id.ll_updating);
        et_ph=(EditText) findViewById(R.id.et_ph);
        weight=(EditText) findViewById(R.id.et_weight);
        height=(EditText) findViewById(R.id.et_height);
        id= findViewById(R.id.tv_id);
        medicalHistory=(EditText) findViewById(R.id.et_mh);
       // et_hy=(EditText) findViewById(R.id.et_hp);
       // et_lowp=(EditText) findViewById(R.id.et_lowp);

        et_longBPM=(EditText) findViewById(R.id.et_longBPM);
        et_longPM=(EditText) findViewById(R.id.et_longPM);
        et_longCM=(EditText) findViewById(R.id.et_longCM);

        et_diaYear=(EditText) findViewById(R.id.et_diaYear);
        diaUpdate=findViewById(R.id.bt_diaUpdate);

        tv_firstName= findViewById(R.id.tv_fn);
        tv_lastName= findViewById(R.id.tv_ln);
        bmi=(TextView) findViewById(R.id.tv_bmi);
        tvDate=(TextView) findViewById(R.id.tv_date);
        dpDate=(DatePicker) findViewById(R.id.dp_date);
        submit=(Button) findViewById(R.id.bt_submit);
        view=(Button) findViewById(R.id.bt_view);
        schedule=(Button) findViewById(R.id.bt_schedule);
        logOut=(Button) findViewById(R.id.bt_logOut);
        dateType=(Spinner) findViewById(R.id.sp_date);
        sp_diaType=(Spinner) findViewById(R.id.sp_diaType);
        hyper=(CheckBox) findViewById(R.id.cb_hyper);
        diabetes=(CheckBox) findViewById(R.id.cb_diabetes);

        mainDate=Calendar.getInstance();
        mYear=mainDate.get(Calendar.YEAR);
        mMonth=mainDate.get(Calendar.MONTH)+1;
        mDay=mainDate.get(Calendar.DAY_OF_MONTH);

        pregnancyHistoryViewer=(CheckBox) findViewById(R.id.et_phViewer);

        updateBloodPressure=(Button) findViewById(R.id.bt_hyUpdate);

        userDAO=new UserDAO();
        basicDAO=new BasicDataDAO();
        phDAO=new PregnancyHistoryDAO();
        hyperDAO=new HypertensionDAO();
        diaDAO=new DiabetesDAO();

        mainHandler=new Handler(getMainLooper());

        labSection=(Button) findViewById(R.id.bt_lab);

        okToSchedule=false;

        if(inputData==null)
        {
            inputData=getApplicationContext().getSharedPreferences("inputData", Context.MODE_PRIVATE);
        }



        addListeners();



    }

    private void addNewViews()
    {
        if(pregnancyHistoryViewer.isChecked())
        {
            ll_ph.setVisibility(View.VISIBLE);

            if(!et_ph.getText().toString().equals("")) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final BasicData bd = basicDAO.getBasicData(inputData.getInt("id", 0));
                        if (bd != null&&Integer.parseInt(et_ph.getText().toString())>=Integer.parseInt(bd.getPregnancyHistory())) {
                            if (!bd.getPregnancyHistory().equals("0")) {

                                List<PregnancyHistory> list = phDAO.getAllPH(inputData.getInt("id", 0));

                                for (int j = 0; j < list.size(); j++) {
                                    int k = j + 1;
                                    PregnancyHistory instance = list.get(j);
                                    Log.v("Pregnancy get looP: ", String.valueOf(k));
                                    final TextView deliveryDate = ll_ph.findViewWithTag("tv_date" + k);
                                    final Spinner deliveryWay = ll_ph.findViewWithTag("sp" + k);
                                    final CheckBox pree = ll_ph.findViewWithTag("pr" + k);
                                    final CheckBox ba = ll_ph.findViewWithTag("ba" + k);
                                    final CheckBox bf37 = ll_ph.findViewWithTag("bf37" + k);
                                    final EditText babyWeight = ll_ph.findViewWithTag("babyWeight" + k);


                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            deliveryDate.setText(instance.getDeliverDate());
                                            babyWeight.setText(instance.getBabyWeight());
                                        }
                                    });

                                    if (instance.getWayDeliver().equals("Vaginal")) {
                                        mainHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                deliveryWay.setSelection(0);
                                            }
                                        });
                                    }


                                    if (instance.getWayDeliver().equals("C-section")) {
                                        mainHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                deliveryWay.setSelection(1);
                                            }
                                        });
                                    }
                                    if (instance.getPree().equals("yes")) {
                                        mainHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                pree.setChecked(true);
                                            }
                                        });
                                    }
                                    if (instance.getBa().equals("yes")) {
                                        mainHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                ba.setChecked(true);
                                            }
                                        });
                                    }
                                    if (instance.getBf37().equals("yes")) {
                                        mainHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                bf37.setChecked(true);
                                            }
                                        });

                                    }
                                }
                            }
                        }
                    }
                }).start();
            }


        }else{
            ll_ph.setVisibility(View.GONE);
        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0,0,0,25);

        if(dynamicData==null)
        {
            dynamicData=getApplicationContext().getSharedPreferences("dynamicData",Context.MODE_PRIVATE);
        }



        if(!et_ph.getText().toString().equals("")) {
            ll_ph.removeAllViews();
            int i = Integer.parseInt(et_ph.getText().toString());
            for (int j = 1; j <= i; j++) {

                //title for each pregnancy
                TextView title=new TextView(this);
                title.setText("pregnancy #"+j);
                title.setLayoutParams(lp);
                ll_ph.addView(title);



                //Month/year of delivery
                TextView tv_dd=new TextView(this);
                tv_dd.setText("Please choose the Month/year of delivery");
                tv_dd.setLayoutParams(lp);
                ll_ph.addView(tv_dd);

                final TextView tv_date=new TextView(this);
                tv_date.setTag("tv_date"+j);
                tv_date.setLayoutParams(lp);


                DatePicker dd=new DatePicker(this);
                dd.setTag("dd"+j);
                dd.setLayoutParams(lp);
                ll_ph.addView(dd);
                final int finalJ = j;
                dd.init(mYear, mMonth, mDay, new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {

                        int month=i1+1;
                        String deliveryDate=i+"-"+month+"-"+i2;
                        tv_date.setText(deliveryDate);

                    }
                });


                TextView tv_deliverDate=new TextView(this);
                tv_deliverDate.setTag("tv_deliverDate"+j);
                tv_deliverDate.setText("History Deliver Date: ");
                tv_deliverDate.setLayoutParams(lp);

                ll_ph.addView(tv_deliverDate);
                ll_ph.addView(tv_date);

                //way to delivery for each pregnancy

                String[] way={"Vaginal","C-section"};
                Spinner wayToDelivery=new Spinner(this);
                wayToDelivery.setTag("sp"+j);
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,way);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                wayToDelivery.setAdapter(adapter);
                wayToDelivery.setLayoutParams(lp);
                ll_ph.addView(wayToDelivery);





                //checkbox for preeclampsia
                final CheckBox pr=new CheckBox(this);
                pr.setTag("pr"+j);
                pr.setText("preeclampsia ?");
                pr.setLayoutParams(lp);

                ll_ph.addView(pr);



                //checkbox for Born alive
                final CheckBox ba=new CheckBox(this);
                ba.setTag("ba"+j);
                ba.setText("Born alive ?");
                ba.setLayoutParams(lp);

                ll_ph.addView(ba);





                //checkbox for Born before 37 weeks
                final CheckBox bf37=new CheckBox(this);
                bf37.setTag("bf37"+j);
                bf37.setText("Born before 37 weeks");
                bf37.setLayoutParams(lp);

                ll_ph.addView(bf37);

                //let user know that they need input baby weight

                final TextView bWeight=new TextView(this);
                bWeight.setText("Baby Weight(lb): ");
                bWeight.setLayoutParams(lp);
                bWeight.setTextColor(android.graphics.Color.BLACK);
                ll_ph.addView(bWeight);


                //input baby weight
                final EditText babyWeight=new EditText(this);
                babyWeight.setTag("babyWeight"+j);
                babyWeight.setLayoutParams(lp);
                babyWeight.setInputType(InputType.TYPE_CLASS_NUMBER);
                babyWeight.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                babyWeight.setHint("input baby weight (lb)");


                ll_ph.addView(babyWeight);






                //end of each pregnancy
                TextView textview = new TextView(this);
                textview.setText("======================================================");
                textview.setLayoutParams(lp);
                textview.setTextColor(android.graphics.Color.RED);
                ll_ph.addView(textview);
            }

        }
    }

    private void initData()
    {
        if(inputData==null)
        {
            inputData=getApplicationContext().getSharedPreferences("inputData", Context.MODE_PRIVATE);
        }




        new Thread(new Runnable() {
            @Override
            public void run() {
                final User user=userDAO.getUserById(inputData.getInt("id",0));
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(user==null)
                        {
                            Toast.makeText(MainActivity.this,"Did not found the user",Toast.LENGTH_SHORT).show();
                            return;
                        }else
                        {
                            id.setText(String.valueOf(user.getId()));
                            tv_firstName.setText(user.getFirstName());
                            tv_lastName.setText(user.getLastName());
                        }
                    }
                });
                final  BasicData bd=basicDAO.getBasicData(inputData.getInt("id",0));
                if(bd!=null)
                {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            dateType.setSelection(bd.getDateTypePosition());
                            weight.setText(bd.getWeight());
                            height.setText(bd.getHeight());
                            et_ph.setText(bd.getPregnancyHistory());
                            medicalHistory.setText(bd.getMedicalHistory());
                            tvDate.setText(bd.getMainDate());
                            if(bd.getHypertension().equals("yes")) {
                             hyper.setChecked(true);
                             }
                             if(bd.getDiabetes().equals("yes")) {
                                 diabetes.setChecked(true);
                             }
                        }
                    });
                    if(bd.getHypertension().equals("yes"))
                    {

                      final  Hypertension instance=hyperDAO.getHypertension(inputData.getInt("id",0));

                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                //et_hy.setText(String.valueOf(instance.getHighBloodPressure()));
                               // et_lowp.setText(String.valueOf(instance.getLowBloodPressure()));
                                et_longBPM.setText(String.valueOf(instance.getLongBPMedication()));
                                et_longPM.setText(String.valueOf(instance.getLongPregnancyMedication()));
                                et_longCM.setText(String.valueOf(instance.getLongCurrentMedication()));
                            }
                        });

                    }

                    if(bd.getDiabetes().equals("yes"))
                    {
                        final Diabetes instance= diaDAO.getDiabetes(inputData.getInt("id",0));

                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                sp_diaType.setSelection(instance.getTypePosition());
                                et_diaYear.setText(instance.getYears());
                            }
                        });

                    }




                }
            }
        }).start();

    }


    private void getBMI()
    {
        if(!height.getText().toString().equals("")&&!weight.getText().toString().equals(""))
        {
          Compute Computer=new Compute();
          String bmiData= Computer.getBMI(Double.parseDouble(height.getText().toString()),Double.parseDouble(weight.getText().toString()));
          bmi.setText(bmiData);
        }
    }

    private void addListeners()
    {



        et_ph.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                addNewViews();
            }
        });

        pregnancyHistoryViewer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                addNewViews();
            }
        });



        weight.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                SharedPreferences.Editor editor=inputData.edit();
                editor.putString("weight",weight.getText().toString());
                editor.commit();
                getBMI();
            }

        });

        //get the height data for compute BMI
        height.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                SharedPreferences.Editor editor=inputData.edit();
                editor.putString("height",height.getText().toString());
                editor.commit();
                getBMI();

            }
        });



        dpDate.init(mYear, mMonth, mDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                int month=i1+1;
                 String date=i+"-"+month+"-"+i2;
                 tvDate.setText(date);
            }
        });

        //show Edd Date
        tvDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Compute getEdd=new Compute();
                String date=tvDate.getText().toString();
                try {
                    eddDate.setText(getEdd.getOk(date)[0]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit2db();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewData();
            }
        });

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //submit2db();
                showSchedule();
            }
        });

        hyper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                hyperInfo();
            }
        });

        diabetes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                diabetesInfo();
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogOut();
            }
        });

        updateBloodPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateHypertensionData();
            }
        });

        labSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLabSection();
            }
        });

    }


    private void showSchedule()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean exists=basicDAO.checkExists(inputData.getInt("id",0));
                if(exists==true)
                {

                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(MainActivity.this, ScheduleView.class);
                                intent.putExtra("id", id.getText().toString());
                                startActivity(intent);
                            }
                        });

                }
                else
                {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,"You must submit data first!!!",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        }).start();
    }

    private void hyperInfo()
    {
        if(hyper.isChecked()) {
            ll_hy.setVisibility(View.VISIBLE);



            if(dynamicData==null)
            {
                dynamicData=getApplicationContext().getSharedPreferences("dynamicData",Context.MODE_PRIVATE);
            }


            final SharedPreferences.Editor dynamicEditor=dynamicData.edit();

           // et_hy.setText(dynamicData.getString("hy",""));
            //et_lowp.setText(dynamicData.getString("lowp",""));
            et_longBPM.setText("0");
            et_longPM.setText("0");
            et_longCM.setText("0");

            /*
            if(et_hy.getText().toString().equals(""))
            {
                Toast.makeText(MainActivity.this,"please input high blood pressure",Toast.LENGTH_SHORT).show();
                et_hy.requestFocus();

            }else
                if(et_lowp.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this,"please input low blood pressure",Toast.LENGTH_SHORT).show();
                    et_lowp.requestFocus();
                }



        else
        if(et_lowp.getText().toString().equals(""))
        {

            Toast.makeText(MainActivity.this,"please input low blood pressure",Toast.LENGTH_SHORT).show();
            et_lowp.requestFocus();



        }


            et_hy.addTextChangedListener(new TextWatcher() {
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

            et_lowp.addTextChangedListener(new TextWatcher() {
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

             */
            et_longBPM.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            et_longPM.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });


            et_longCM.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });



        }
        else
        {
            ll_hy.setVisibility(View.GONE);
            return;

        }
    }


    private void updateHypertensionData()
    {
        Intent intent=new Intent(this,UpdateHypertensionData.class);

        startActivity(intent);
    }

    private void diabetesInfo()
    {
        if(diabetes.isChecked())
        {
            ll_dia.setVisibility(View.VISIBLE);


            sp_diaType.setSelection(0);
            et_diaYear.setText("0");

            if(et_diaYear.getText().toString().equals(""))
            {
                Toast.makeText(this,"please input diabetes years",Toast.LENGTH_SHORT).show();
                et_diaYear.requestFocus();
            }



            sp_diaType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            et_diaYear.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    if(et_diaYear.getText().toString().equals(""))
                    {
                        Toast.makeText(MainActivity.this,"please input diabetes years",Toast.LENGTH_SHORT).show();
                        et_diaYear.requestFocus();
                    }

                }

                @Override
                public void afterTextChanged(Editable editable) {


                }
            });

            diaUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toUpdateDiabetes();
                }
            });
        }

        else
        {
            ll_dia.setVisibility(View.GONE);
            return;
        }
    }



    private void submit2db()
    {



        String mainDate=tvDate.getText().toString();
        String mh=medicalHistory.getText().toString();
        String pregnancyHistory=et_ph.getText().toString();
        String uHeight=height.getText().toString();
        String uWeight=weight.getText().toString();
        int dateTypePosition=dateType.getSelectedItemPosition();

        String hy="no";
        String isDiabetes="no";


        if(TextUtils.isEmpty(mainDate))
        {
            Toast.makeText(MainActivity.this,"Please input"+dateType.getSelectedItem()+" Date!!!",Toast.LENGTH_SHORT).show();
            dpDate.requestFocus();
            return;
        }else
        if(TextUtils.isEmpty(mh))
        {
            Toast.makeText(MainActivity.this,"Please input Medical History",Toast.LENGTH_SHORT).show();
            medicalHistory.requestFocus();
            return;
        }else
        if(TextUtils.isEmpty(pregnancyHistory))
        {
            Toast.makeText(MainActivity.this,"Please input Pregnancy History (input 0 if don't have pregnancy)",Toast.LENGTH_SHORT).show();
            et_ph.requestFocus();
            return;
        }else
        if(TextUtils.isEmpty(uHeight))
        {
            Toast.makeText(MainActivity.this,"Please input your height",Toast.LENGTH_SHORT).show();
            height.requestFocus();
            return;
        }else
        if(TextUtils.isEmpty(uWeight))
        {
            Toast.makeText(MainActivity.this,"Please input Your weight",Toast.LENGTH_SHORT).show();
            weight.requestFocus();
            return;
        }
        else
            {


                if(hyper.isChecked())
                {
                    hy="yes";

                    /*
                    if(et_hy.getText().toString().equals(""))
                    {
                        Toast.makeText(MainActivity.this,"Please input high blood pressure",Toast.LENGTH_SHORT).show();
                        et_hy.requestFocus();
                        return;
                    }
                    if(et_lowp.getText().toString().equals(""))
                    {
                        Toast.makeText(MainActivity.this,"Please input low blood pressure",Toast.LENGTH_SHORT).show();
                        et_lowp.requestFocus();
                        return;
                    }

                     */
                }




                if(diabetes.isChecked())
                {
                    isDiabetes="yes";
                    if(et_diaYear.getText().toString().equals(""))
                    {
                        Toast.makeText(MainActivity.this,"Please input diabetes years",Toast.LENGTH_SHORT).show();
                        et_diaYear.requestFocus();
                        return;
                    }
                }

                ll_updating.setVisibility(View.VISIBLE);

                final BasicData basicData = new BasicData();
                basicData.setId(inputData.getInt("id", 0));
                basicData.setDateTypePosition(dateTypePosition);
                basicData.setFirstName(tv_firstName.getText().toString());
                basicData.setLastName(tv_lastName.getText().toString());
                basicData.setDateType(dateType.getSelectedItem().toString());
                basicData.setMainDate(mainDate);
                basicData.setMedicalHistory(mh);
                basicData.setPregnancyHistory(pregnancyHistory);
                basicData.setHeight(uHeight);
                basicData.setWeight(uWeight);
                basicData.setBMI(bmi.getText().toString());
                basicData.setHypertension(hy);
                basicData.setDiabetes(isDiabetes);


                new Thread(new Runnable() {
                    @Override
                    public void run() {


                        //check and update for pregancny history
                        if (!et_ph.getText().toString().equals("")&&pregnancyHistoryViewer.isChecked()) {

                            phDAO.deletePH(inputData.getInt("id", 0));

                            for (int j = 1; j <= Integer.parseInt(et_ph.getText().toString()); j++) {
                                Log.v("phData", "input loop" + j);
                                String preeCheck = "No";
                                String baCheck = "No";
                                String bf37Check = "No";


                                TextView deliveryDate = ll_ph.findViewWithTag("tv_date" + j);
                                Spinner deliveryWay = ll_ph.findViewWithTag("sp" + j);
                                CheckBox pree = ll_ph.findViewWithTag("pr" + j);
                                EditText babyWeight = ll_ph.findViewWithTag("babyWeight" + j);


                                if (pree.isChecked())
                                    preeCheck = "yes";
                                CheckBox ba = ll_ph.findViewWithTag("ba" + j);
                                if (ba.isChecked())
                                    baCheck = "yes";
                                CheckBox bf37 = ll_ph.findViewWithTag("bf37" + j);
                                if (bf37.isChecked())
                                    bf37Check = "yes";

                                if (!deliveryDate.getText().toString().equals("")) {

                                    final PregnancyHistory nPH = new PregnancyHistory();
                                    nPH.setId(inputData.getInt("id", 0));
                                    nPH.setpNumber(j);
                                    nPH.setDeliverDate(deliveryDate.getText().toString());
                                    nPH.setWayDeliver(deliveryWay.getSelectedItem().toString());
                                    nPH.setPree(preeCheck);
                                    nPH.setBa(baCheck);
                                    nPH.setBf37(bf37Check);
                                    nPH.setBabyWeight(babyWeight.getText().toString());

                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            boolean ok = false;
                                            ok = phDAO.addPH(nPH);
                                            if (ok) {
                                                Log.v("Add ph: ", String.valueOf(nPH.getpNumber()));
                                            }
                                        }
                                    }).start();

                                } else {
                                    Toast.makeText(MainActivity.this, "You have to fill in all blank input data block!!!", Toast.LENGTH_SHORT).show();
                                }
                            }


                        }


                        //check and add hypertension information

                        if (hyper.isChecked()) {
                            {
                                /*
                                if (et_hy.getText().toString().equals("")) {
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(MainActivity.this, "please input high blood pressure", Toast.LENGTH_SHORT).show();
                                            et_hy.requestFocus();
                                        }
                                    });
                                } else if (et_lowp.getText().toString().equals("")) {
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(MainActivity.this, "please input low blood pressure", Toast.LENGTH_SHORT).show();
                                            et_lowp.requestFocus();
                                        }
                                    });

                                 */

                                 {

                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy MM dd HH:mm:ss");

                                    Date date = new Date(System.currentTimeMillis());

                                    String currentTime = simpleDateFormat.format(date);

                                    Hypertension instance = new Hypertension();
                                    instance.setId(inputData.getInt("id", 0));
                                    //instance.setHighBloodPressure(Integer.parseInt(et_hy.getText().toString()));
                                    //instance.setLowBloodPressure(Integer.parseInt(et_lowp.getText().toString()));
                                    instance.setLongBPMedication(Integer.parseInt(et_longBPM.getText().toString()));
                                    instance.setLongPregnancyMedication(Integer.parseInt(et_longPM.getText().toString()));
                                    instance.setLongCurrentMedication(Integer.parseInt(et_longCM.getText().toString()));
                                    instance.setTime(currentTime);

                                    boolean exists = hyperDAO.checkExists(inputData.getInt("id", 0));
                                    if (exists) {
                                        hyperDAO.deleteHypertension(inputData.getInt("id", 0));
                                    }

                                    boolean ok = hyperDAO.addHypertension(instance);
                                }

                            }

                        }

                        //check and add diabetes information
                        if (diabetes.isChecked()) {

                            boolean exists = diaDAO.checkExists(inputData.getInt("id", 0));
                            if (exists) {
                                diaDAO.updateDiaInfo(inputData.getInt("id",0),sp_diaType.getSelectedItem().toString(),et_diaYear.getText().toString());
                            }else
                            {
                            if (!et_diaYear.getText().toString().equals("")) {

                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy MM dd HH:mm:ss");

                                Date date = new Date(System.currentTimeMillis());

                                String currentTime = simpleDateFormat.format(date);

                                Diabetes instance = new Diabetes();

                                instance.setTime(currentTime);
                                instance.setId(inputData.getInt("id", 0));
                                instance.setYears(et_diaYear.getText().toString());
                                instance.setType(sp_diaType.getSelectedItem().toString());
                                instance.setTypePosition(sp_diaType.getSelectedItemPosition());

                                diaDAO.addDiabetes(instance);
                            }
                            }


                        }


                        //check and add the basic data information
                        boolean exists = false;
                        exists = basicDAO.checkExists(inputData.getInt("id", 0));
                        if (exists == true) {
                            basicDAO.deleteUser(inputData.getInt("id", 0));
                        }
                        {
                            int ok = basicDAO.addBasicData(basicData);
                            if (ok != 0) {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        okToSchedule = true;
                                        ll_updating.setVisibility(View.GONE);
                                        Toast.makeText(MainActivity.this, "Data saved!!!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Data save fail, Error Happened", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }


                    }
                }).start();


            }






    }

    private void toUpdateDiabetes()
    {
        Intent intent=new Intent(this,UpdateGlucoseActivity.class);
        startActivity(intent);
    }

    private void viewData()
    {
        int count=0;

        new Thread(new Runnable() {
            @Override
            public void run() {
                BasicData instance=new BasicData();
                instance=basicDAO.getBasicData(inputData.getInt("id",0));
                if(instance!=null)
                {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent=new Intent(MainActivity.this,ViewDataActivity.class);
                            intent.putExtra("id",inputData.getInt("id",0));
                            startActivity(intent);
                        }
                    });
                }
                else
                {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "You must submit data first!!!", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        }).start();
    }

    /*
    private void checkBloodPressure()
    {
        boolean emergence=false;
        Compute check=new Compute();
        if(!et_hy.getText().toString().equals("")&&!et_lowp.getText().toString().equals(""))
        emergence=check.checkBooldPressure(Integer.parseInt(et_hy.getText().toString()),Integer.parseInt(et_lowp.getText().toString()));
        if(emergence==true)
            Toast.makeText(this,"Boold pressure is too high!!! Go to emergence room immediately!!!",Toast.LENGTH_LONG).show();
    }

     */

    private void checkGlucose(String type)
    {
        if(dynamicData==null)
        {
            dynamicData=getApplicationContext().getSharedPreferences("dynamicData",Context.MODE_PRIVATE);
        }

        final SharedPreferences.Editor dynamicEditor=dynamicData.edit();

        int increaseNumber=0;

        Compute check=new Compute();


    }

    private void doLogOut()
    {
        Intent intent=new Intent(MainActivity.this,Login.class);
        Toast.makeText(this,"Log out success",Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();
    }

    private void goToLabSection()
    {
        Intent intent=new Intent(MainActivity.this,LabActivity.class);
        startActivity(intent);
    }
}