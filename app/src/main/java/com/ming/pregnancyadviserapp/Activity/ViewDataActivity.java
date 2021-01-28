package com.ming.pregnancyadviserapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ming.pregnancyadviserapp.Database.BasicDataDAO;
import com.ming.pregnancyadviserapp.Database.DiabetesDAO;
import com.ming.pregnancyadviserapp.Database.HypertensionDAO;
import com.ming.pregnancyadviserapp.Database.PregnancyHistoryDAO;
import com.ming.pregnancyadviserapp.Instance.BasicData;
import com.ming.pregnancyadviserapp.Instance.Diabetes;
import com.ming.pregnancyadviserapp.Instance.Hypertension;
import com.ming.pregnancyadviserapp.Instance.PregnancyHistory;
import com.ming.pregnancyadviserapp.R;

import java.util.List;

public class ViewDataActivity extends AppCompatActivity {

    TextView id;
    TextView firstName;
    TextView lastName;
    TextView dateType;
    TextView mainDate;
    TextView mh;
    TextView ph;
    TextView height;
    TextView weight;
    TextView bmi;
    TextView hyper;
    TextView diabetes;

    TextView bp;
    TextView longBPM;
    TextView longPM;
    TextView longCM;
    TextView bpTime;

    TextView diaType;
    TextView diaYear;
    TextView nfg;
    TextView vtv_1hg;
    TextView vtv_2hg;
    TextView increaseI;


    Button schdule;
    Button back;

    LinearLayout vll_ph;
    LinearLayout vll_bp;
    LinearLayout vll_dia;

    SharedPreferences inputData;


    private BasicDataDAO basicDAO;
    private PregnancyHistoryDAO phDAO;
    private HypertensionDAO hyperDAO;
    private DiabetesDAO diaDAO;
    private Handler mainHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);



        initUI();
        initData();
    }

    private void initUI()
    {
        id=(TextView) findViewById(R.id.vtv_id);
        firstName=(TextView) findViewById(R.id.vtv_fn);
        lastName=(TextView) findViewById(R.id.vtv_ln);
        dateType=(TextView) findViewById(R.id.vtv_dateType);
        mainDate=(TextView) findViewById(R.id.vtv_mainDate);
        mh=(TextView) findViewById(R.id.vtv_mh);
        ph=(TextView) findViewById(R.id.vtv_ph);
        height=(TextView) findViewById(R.id.vtv_height);
        weight=(TextView) findViewById(R.id.vtv_weight);
        bmi=(TextView) findViewById(R.id.vtv_bmi);
        hyper=(TextView) findViewById(R.id.vtv_hyper);
        diabetes=(TextView) findViewById(R.id.vtv_dia);
        vll_ph=(LinearLayout) findViewById(R.id.vll_ph);
        schdule=(Button) findViewById(R.id.vbt_schedule);

        vll_bp=(LinearLayout) findViewById(R.id.vll_hyper);
        bp=(TextView) findViewById(R.id.vtv_bp);
        longBPM=(TextView) findViewById(R.id.vtv_longBPM);
        longPM=(TextView) findViewById(R.id.vtv_longPM);
        longCM=(TextView) findViewById(R.id.vtv_longCM);
        bpTime=(TextView) findViewById(R.id.vtv_bpTime);

        vll_dia=(LinearLayout) findViewById(R.id.vll_diabetes);
        diaType=(TextView) findViewById(R.id.vtv_diaType);
        diaYear=(TextView) findViewById(R.id.vtv_diaYear);
        nfg=(TextView) findViewById(R.id.vtv_nfg);
        vtv_1hg=(TextView) findViewById(R.id.vtv_1hg);
        vtv_2hg=(TextView) findViewById(R.id.vtv_2hg);
        increaseI=(TextView) findViewById(R.id.vtv_increaseI);
        back=(Button) findViewById(R.id.vbt_back);

        inputData=getApplicationContext().getSharedPreferences("inputData", Context.MODE_PRIVATE);

        basicDAO=new BasicDataDAO();
        phDAO=new PregnancyHistoryDAO();
        hyperDAO=new HypertensionDAO();
        diaDAO=new DiabetesDAO();

        mainHandler=new Handler(getMainLooper());

        setListener();
    }

    private void setListener()
    {
        schdule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSchedule();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToInputDataPage();
            }
        });
    }

    private void showSchedule()
    {
        Intent intent=new Intent(ViewDataActivity.this, ScheduleView.class);
        startActivity(intent);
        finish();
    }



    private void initData()
    {



        new Thread(new Runnable() {
            @Override
            public void run() {



                //load basic data
                final BasicData basicData=basicDAO.getBasicData(inputData.getInt("id",0));
                if(basicData!=null)
                {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            id.setText("Your id: "+basicData.getId());
                            firstName.setText("Your first name: "+basicData.getFirstName());
                            lastName.setText("Your last Name: "+basicData.getLastName());
                            dateType.setText("Date type is: "+basicData.getDateType());
                            mainDate.setText("Date: "+basicData.getMainDate());
                            mh.setText("Medical history: "+basicData.getMedicalHistory());
                            ph.setText("The number of pregnancy: "+basicData.getPregnancyHistory());
                            height.setText("Height: "+basicData.getHeight()+" inch");
                            weight.setText("Weight: "+basicData.getWeight()+" lb");
                            bmi.setText("BMI: "+basicData.getBMI());
                            hyper.setText("Hypertension: "+basicData.getHypertension());
                            diabetes.setText("Diabetes: "+basicData.getDiabetes());
                        }

                    });
                }


                //if have hypertension
                if(basicData.getHypertension().equals("yes"))
                {
                    Hypertension instance=hyperDAO.getHypertension(inputData.getInt("id",0));
                    if(instance!=null)
                    {
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                vll_bp.setVisibility(View.VISIBLE);
                                bp.setText("Blood pressure: "+instance.getHighBloodPressure()+"/"+instance.getLowBloodPressure());
                                longBPM.setText("how long on BP medication: "+instance.getLongBPMedication());
                                longPM.setText("how long on prepregnancy medication: "+instance.getLongPregnancyMedication());
                                longCM.setText("how long on current medication: "+instance.getLongCurrentMedication());
                                bpTime.setText("Blood pressure measurement time: "+instance.getTime());
                            }
                        });
                    }
                }


                //if have diabetes
                if(basicData.getDiabetes().equals("yes"))
                {
                    Diabetes instance=diaDAO.getDiabetes(inputData.getInt("id",0));
                    if(instance!=null)
                    {
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                vll_dia.setVisibility(View.VISIBLE);
                                diaType.setText("Diabetes Type: "+instance.getType());
                                diaYear.setText("number of years being diabetic: "+instance.getYears());
                                nfg.setText("Recent normal fasting glucose: "+instance.getfGlucose()+" measurement time: "+instance.getFgTime());
                                vtv_1hg.setText("Recent 1hr  postprandial glucose: "+instance.getOnehrglucose()+" measurement time: "+instance.getOneHrTime());
                                vtv_2hg.setText("Recent 2hr postprandial glucose: "+instance.getTwohrglucose()+" measurement time: "+instance.getTwoHrTime());
                                increaseI.setText("Numbers of increase insulin by 10-15%: "+instance.getIncreaseInsulin());
                            }
                        });
                    }
                }

                //Pregnancy history
                if(Integer.parseInt(basicData.getPregnancyHistory())!=0)
                {
                    List<PregnancyHistory> list=phDAO.getAllPH(inputData.getInt("id",0));
                    if(list!=null)
                    {
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                lp.setMargins(0,0,0,25);
                                for(int j=0;j<list.size();j++) {

                                    PregnancyHistory instance=list.get(j);

                                    int i=j+1;
                                        Log.v("viewData","show dd");
                                        TextView tv_dd=new TextView(ViewDataActivity.this);
                                        tv_dd.setText("Pregnancy number: "+instance.getpNumber());
                                        tv_dd.setLayoutParams(lp);
                                        vll_ph.addView(tv_dd);

                                        Log.v("viewData","show delieryDate");

                                        TextView deliveryDate=new TextView(ViewDataActivity.this);
                                        deliveryDate.setText("Delivery date: "+instance.getDeliverDate());
                                        deliveryDate.setLayoutParams(lp);
                                        vll_ph.addView(deliveryDate);


                                        Log.v("viewData","show way");
                                        TextView way=new TextView(ViewDataActivity.this);
                                        way.setText("Way to delivery: "+instance.getWayDeliver());
                                        way.setLayoutParams(lp);
                                        vll_ph.addView(way);

                                        Log.v("viewData","show pree");

                                        TextView pree=new TextView(ViewDataActivity.this);
                                        pree.setText("Preeclampsia: "+instance.getPree());
                                        pree.setLayoutParams(lp);
                                        vll_ph.addView(pree);

                                        Log.v("viewData","show ba");

                                        TextView ba=new TextView(ViewDataActivity.this);
                                        ba.setText("Born alive: "+instance.getBa());
                                        ba.setLayoutParams(lp);
                                        vll_ph.addView(ba);

                                        Log.v("viewData","show bf37");

                                        TextView bf37=new TextView(ViewDataActivity.this);
                                        bf37.setText("Preeclampsia: "+instance.getBf37());
                                        bf37.setLayoutParams(lp);
                                        vll_ph.addView(bf37);


                                        TextView babyWeight=new TextView(ViewDataActivity.this);
                                        babyWeight.setText("Baby Weight: "+instance.getBabyWeight()+" lb");
                                        babyWeight.setLayoutParams(lp);
                                        vll_ph.addView(babyWeight);





                                        //end of each pregnancy
                                        TextView textview = new TextView(ViewDataActivity.this);
                                        textview.setText("======================================================");
                                        textview.setLayoutParams(lp);
                                        textview.setTextColor(android.graphics.Color.RED);
                                        vll_ph.addView(textview);





                                }

                            }
                        });
                    }
                }

            }
        }).start();


    }

    private void backToInputDataPage()
    {

        Intent intent=new Intent(this,MainActivity.class);
        intent.putExtra("id",inputData.getInt("id",0));
        startActivity(intent);
        finish();
    }
}