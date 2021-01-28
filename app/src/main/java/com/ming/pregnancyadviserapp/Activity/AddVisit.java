package com.ming.pregnancyadviserapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ming.pregnancyadviserapp.Database.AddVisitDAO;
import com.ming.pregnancyadviserapp.Instance.AddVisiting;
import com.ming.pregnancyadviserapp.Logic.CalenderEvent;
import com.ming.pregnancyadviserapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddVisit extends AppCompatActivity {

    Spinner visitType;
    DatePicker visitDate;
    TimePicker visitTime;
    Button submit;
    Button cancel;
    TextView tv_visitTime;
    TextView tv_visitDate;

    private int mDay;
    private int mMonth;
    private int mYear;

    private Calendar mainDate;

    AddVisitDAO addDAO;
    SharedPreferences inputData;
    Handler mainHandler;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_visit);

        initUI();

        addListener();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR},1
                    );
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALENDAR},1
            );
        }

    }


    private void initUI()
    {
        visitType=(Spinner) findViewById(R.id.addSP_type);
        visitDate=(DatePicker) findViewById(R.id.addDP_date);
        visitTime=(TimePicker) findViewById(R.id.addTP_visitTime);
        submit=(Button) findViewById(R.id.addBT_add);
        cancel=(Button) findViewById(R.id.addBT_cancel);
        tv_visitDate=(TextView) findViewById(R.id.addTV_visitDate);
        tv_visitTime=(TextView) findViewById(R.id.addTV_visitTime);

        mainDate=Calendar.getInstance();
        mYear=mainDate.get(Calendar.YEAR);
        mMonth=mainDate.get(Calendar.MONTH)+1;
        mDay=mainDate.get(Calendar.DAY_OF_MONTH);

        addDAO=new AddVisitDAO();
        inputData=getApplicationContext().getSharedPreferences("inputData", Context.MODE_PRIVATE);
        mainHandler=new Handler(getMainLooper());

    }



    private void addListener()
    {
       visitDate.init(mYear, mMonth, mDay, new DatePicker.OnDateChangedListener() {
           @Override
           public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
               int month=i1+1;
               String pickDate=i+"-"+month+"-"+i2;
               tv_visitDate.setText(pickDate);
           }
       });

       visitTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
           @Override
           public void onTimeChanged(TimePicker timePicker, int i, int i1) {
               String pickTime=i+":"+i1;
               tv_visitTime.setText(pickTime);
           }
       });

       submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               submit2DB();
           }
       });

       cancel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Cancel();
           }
       });
    }

    private void submit2DB()
    {

        new Thread(new Runnable() {
            @Override
            public void run() {
                int visitNumber=1;
                List<AddVisiting> list=addDAO.getAddVisit(inputData.getInt("id",0));
                if(list!=null)
                {
                    for(int i=0;i<list.size();i++)
                    {
                        visitNumber++;
                    }
                }

                if(!tv_visitDate.getText().toString().equals("")&&!tv_visitTime.getText().toString().equals(""))
                {
                    AddVisiting instance=new AddVisiting();
                    instance.setId(inputData.getInt("id",0));
                    instance.setVisitNumber(visitNumber);
                    instance.setVisitType(visitType.getSelectedItem().toString());
                    instance.setVisitDate(tv_visitDate.getText().toString());
                    instance.setVisitTime(tv_visitTime.getText().toString());

                    boolean ok=false;

                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            LinearLayout updateLayout=findViewById(R.id.addLL_update);
                            updateLayout.setVisibility(View.VISIBLE);
                        }
                    });

                    ok=addDAO.addAddVisit(instance);

                    final int titleNumber=visitNumber;

                    if(ok==true)
                    {
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {


                                SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
                                String startTime=tv_visitDate.getText().toString()+" "+tv_visitTime.getText().toString();

                                Date date = null;
                                try {
                                    date = sdf.parse(startTime);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(date);
                                long time=calendar.getTimeInMillis();
                                CalenderEvent.addCalendarEvent(AddVisit.this,"Pregnancy check visiting - "+String.valueOf(titleNumber),visitType.getSelectedItem().toString(),time);

                                LinearLayout updateLayout=findViewById(R.id.addLL_update);
                                updateLayout.setVisibility(View.GONE);

                                Toast.makeText(AddVisit.this,"New visiting added success!!!",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(AddVisit.this,ScheduleView.class);
                                intent.putExtra("id",inputData.getInt("id",0));
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                    else
                    {
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                LinearLayout updateLayout=findViewById(R.id.addLL_update);
                                updateLayout.setVisibility(View.GONE);
                               Toast.makeText(AddVisit.this,"Add visiting fail, Something error happened",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }else
                {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(AddVisit.this,"You have to choose visit date and time!!!",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();




    }

    private void Cancel()
    {
        this.finish();
    }
}