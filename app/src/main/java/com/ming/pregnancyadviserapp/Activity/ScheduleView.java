package com.ming.pregnancyadviserapp.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ming.pregnancyadviserapp.Database.AddVisitDAO;
import com.ming.pregnancyadviserapp.Database.BasicDataDAO;
import com.ming.pregnancyadviserapp.Instance.AddVisiting;
import com.ming.pregnancyadviserapp.Instance.BasicData;
import com.ming.pregnancyadviserapp.Logic.CalenderEvent;
import com.ming.pregnancyadviserapp.Logic.Compute;
import com.ming.pregnancyadviserapp.R;

import java.text.ParseException;
import java.util.List;

public class ScheduleView extends AppCompatActivity {




    TextView startDate;
    TextView fdss;
    TextView sdss;
    TextView tdss;
    TextView gbs;
    TextView ntt;
    TextView au;
    TextView nts;
    TextView ok;
    TextView gId;
    TextView gfn;
    TextView gln;
    TextView edd;
    TextView hyper;


    LinearLayout sll_dia;
    TextView diaCheck;
    TextView diaEC;
    TextView diaNV;
    TextView diaGlucoseVisiting;

    LinearLayout sll_addVisit;
    Button addVisit;

    Button back;

    private Handler mainHandler;
    private BasicDataDAO basicDAO;
    private AddVisitDAO addDAO;
    private SharedPreferences inputData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_view);


        initUI();
        try {
            renewUI();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        addListener();
    }



    private void initUI()
    {
        gId=(TextView) findViewById(R.id.stv_id);
        gfn=(TextView) findViewById(R.id.stv_fn);
        gln=(TextView) findViewById(R.id.stv_ln);
        startDate=(TextView) findViewById(R.id.stv_date);
        fdss=(TextView) findViewById(R.id.stv_fdss);
        sdss=(TextView) findViewById(R.id.stv_sdss);
        //tdss=(TextView) findViewById(R.id.stv_tdss);
        gbs=(TextView) findViewById(R.id.stv_gbs);
        ntt=(TextView) findViewById(R.id.stv_ntt);
        au=(TextView) findViewById(R.id.stv_au);
        nts=(TextView) findViewById(R.id.stv_nts);
        ok=(TextView) findViewById(R.id.stv_ok);
        edd=(TextView) findViewById(R.id.stv_edd);
        hyper=(TextView) findViewById(R.id.stv_hyper);

        sll_dia=(LinearLayout) findViewById(R.id.sll_diabetes);
        diaCheck=(TextView) findViewById(R.id.stv_diaCheck);
        diaEC=(TextView) findViewById(R.id.stv_diaEC);
        diaNV=(TextView) findViewById(R.id.stv_diaNV);
        diaGlucoseVisiting=(TextView) findViewById(R.id.stv_glucoseVisiting);

        sll_addVisit=(LinearLayout) findViewById(R.id.sll_addVisit);
        addVisit=(Button) findViewById(R.id.sbt_add);

        back=(Button) findViewById(R.id.sbt_back);

        basicDAO=new BasicDataDAO();
        addDAO=new AddVisitDAO();
        mainHandler=new Handler(getMainLooper());
        inputData=getApplicationContext().getSharedPreferences("inputData", Context.MODE_PRIVATE);
    }

    private void renewUI() throws ParseException {

        new Thread(new Runnable() {
            @Override
            public void run() {

                final BasicData instance=basicDAO.getBasicData(inputData.getInt("id",0));

                if(instance!=null)
                {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            String maindate = instance.getMainDate();
                            String[] firstLab = new String[2];
                            String[] secondLab = new String[2];
                            String[] thirdLab = new String[2];
                            String[] getGBS=new String[2];
                            String[] getNtt=new String[2];
                            String[] getAu=new String[2];
                            String[] getNts=new String[2];
                            String[] getOk=new String[2];
                            String[] hyperDate=new String[2];
                            String[] diacheckDate=new String[2];
                            String[] diaECDate=new String[2];
                            String[] diaNVDate=new String[7];

                            String firstName="";
                            String lastName="";
                            String isHypertension="no";
                            String isDiabetes="no";

                            isHypertension=instance.getHypertension();
                            isDiabetes=instance.getDiabetes();
                            firstName=instance.getFirstName();
                            lastName=instance.getLastName();
                            int id=instance.getId();

                            startDate.setText(startDate.getText()+maindate);

                            Compute dateHandle = new Compute();

                            try {
                                firstLab=dateHandle.getFdss(maindate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                secondLab=dateHandle.getSdss(maindate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                thirdLab=dateHandle.getTdss(maindate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                getGBS=dateHandle.getGBS(maindate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                getNtt=dateHandle.getNtt(maindate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                getAu=dateHandle.getAu(maindate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                getNts=dateHandle.getNts(maindate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                getOk=dateHandle.getOk(maindate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                hyperDate=dateHandle.getHyper(maindate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                diacheckDate=dateHandle.getDiacheckDate(maindate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                diaECDate=dateHandle.getDiaECDate(maindate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                diaNVDate=dateHandle.getDiaNVDate(maindate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            gId.setText(gId.getText()+String.valueOf(id));
                            gfn.setText(gfn.getText()+firstName);
                            gln.setText(gln.getText()+lastName);

                            fdss.setText(fdss.getText()+"\n"+firstLab[0]+" (Week 11) to "+firstLab[1]+" (week 14)");
                            sdss.setText(sdss.getText()+"\n"+secondLab[0]+" (Week 16) to "+secondLab[1]+" (week 20)");
                            //tdss.setText(tdss.getText()+"\n"+thirdLab[0]+" (Week 24) to "+thirdLab[1]+" (week 28)");
                            gbs.setText(gbs.getText()+"\n"+getGBS[0]+" (Week 35) to "+getGBS[1]+" (week 38)");
                            ntt.setText(ntt.getText()+"\n"+getNtt[0]+" (Week 11) to "+getNtt[1]+" (week 13)");
                            au.setText(au.getText()+"\n"+getAu[0]+" (Week 18) to "+getAu[1]+" (week 22)");




                            if(isHypertension.equals("yes"))
                            {
                                try {
                                    getNts=dateHandle.getHyperNts(maindate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                nts.setText(nts.getText()+"\n"+getNts[0]+" (Week32) to "+getNts[1]+" (Week39), twice weekly because hypertension");
                            }
                            else {
                                nts.setText(nts.getText() + "\n" + getNts[0] + " (Week 40) to " + getNts[1] + " (week 44)");
                            }
                            ok.setText(ok.getText()+"\n"+getOk[0]+" (Week 40) ");
                            edd.setText(edd.getText()+"\n"+getOk[0]);

                            if(instance.getHypertension().equals("yes"))
                            {
                                hyper.setText("You has hypertension, should take test include LFT, 24hr urine protein test, creatinine, uric acid between: "+hyperDate[0]+" (week 4) to "+hyperDate[1]+" (week 20)");
                            }


                            if(isDiabetes.equals("yes"))
                            {
                                sll_dia.setVisibility(View.VISIBLE);
                                diaCheck.setText("You has diabetes, should take test include LFT, 24hr urine protein , creatinine, uric acid, HgbA1C between: "+
                                        diacheckDate[0]+" (week4) to "+diacheckDate[1]+" (week 20)");
                                diaEC.setText("You should take Fetal echocardiogram in "+diaECDate[0]+" (Week22)");
                                diaNV.setText("Diabetes nurse visit in: \n"+ diaNVDate[0]+" (week6) \n"
                                        +diaNVDate[1]+" (week10) \n"+diaNVDate[2]+" (week14) \n"+diaNVDate[3]+" (week18) \n"+diaNVDate[4]+" (week22) \n"+
                                        diaNVDate[5]+" (week26) \n"+diaNVDate[6]+" (week30) ");
                            }
                            else
                            {
                                sll_dia.setVisibility(View.GONE);
                            }
                        }
                    });
                }

                final List<AddVisiting> addList= addDAO.getAddVisit(inputData.getInt("id",0));

                if(addList!=null)
                {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            sll_addVisit.setVisibility(View.VISIBLE);
                        }
                    });

                    for(int i=0;i<addList.size();i++)
                    {
                        AddVisiting newVisiting=addList.get(i);

                        final int tag=i+1;

                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                lp.setMargins(0,0,0,25);

                                TextView addVisitNumber=new TextView(ScheduleView.this);
                                addVisitNumber.setTag("visit number"+tag);
                                addVisitNumber.setText("The visiting Number is: "+newVisiting.getVisitNumber());
                                addVisitNumber.setLayoutParams(lp);
                                sll_addVisit.addView(addVisitNumber);

                                TextView addVisitType=new TextView(ScheduleView.this);
                                addVisitType.setTag("visit type"+tag);
                                addVisitType.setLayoutParams(lp);
                                addVisitType.setText("The visiting Type is: "+newVisiting.getVisitType());
                                sll_addVisit.addView(addVisitType);

                                TextView addVisitDate=new TextView(ScheduleView.this);
                                addVisitDate.setTag("visit date"+tag);
                                addVisitDate.setLayoutParams(lp);
                                addVisitDate.setText("The visiting Date: "+newVisiting.getVisitDate());
                                sll_addVisit.addView(addVisitDate);

                                TextView addVisitTime=new TextView(ScheduleView.this);
                                addVisitTime.setLayoutParams(lp);
                                addVisitTime.setTag("visit time"+tag);
                                addVisitTime.setText("The visiting Time: "+newVisiting.getVisitTime());
                                sll_addVisit.addView(addVisitTime);

                                Button deleteVisit=new Button(ScheduleView.this);
                                deleteVisit.setTag(tag);
                                deleteVisit.setText("Delete this visiting");
                                deleteVisit.setLayoutParams(lp);
                                sll_addVisit.addView(deleteVisit);

                                //add button listener
                                deleteVisit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        deleteVisiting(deleteVisit.getTag().toString());
                                    }
                                });

                                TextView textview = new TextView(ScheduleView.this);
                                textview.setText("======================================================");
                                textview.setLayoutParams(lp);
                                textview.setTextColor(android.graphics.Color.RED);
                                sll_addVisit.addView(textview);

                            }
                        });
                    }


                }

            }

        }).start();


    }

    private void deleteVisiting(String tag)
    {
        System.out.println("the button tag is: "+tag);
        final int visitNumber=Integer.parseInt(tag);

        new AlertDialog.Builder(ScheduleView.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("warning")
                .setMessage("Are you sure you want to delete this visiting?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                addDAO.deleteVisit(inputData.getInt("id",0),visitNumber);
                            }
                        }).start();
                        CalenderEvent.deleteCalendarEvent(ScheduleView.this,"Pregnancy check visiting - "+visitNumber);
                        Intent intent=new Intent(ScheduleView.this,ScheduleView.class);
                        Toast.makeText(ScheduleView.this,"Visiting delete success",Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                }).create().show();
    }

    private void addListener()
    {
        addVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVisit();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToInputDataPage();
            }
        });
    }

    private void setVisit()
    {
        Intent intent = new Intent(this, AddVisit.class);
        startActivity(intent);
    }



    private void backToInputDataPage()
    {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("id",inputData.getInt("id",0));
        startActivity(intent);
        finish();
    }
}