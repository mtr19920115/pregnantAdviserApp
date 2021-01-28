package com.ming.pregnancyadviserapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ming.pregnancyadviserapp.Database.LabDAO;
import com.ming.pregnancyadviserapp.Instance.Lab;
import com.ming.pregnancyadviserapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LabActivity extends AppCompatActivity {

    Spinner labTitle;

    private SharedPreferences inputData;

    LinearLayout updateLL;
    LinearLayout loadingLL;

    EditText labValues;

    LabDAO labDAO;

    Handler mainHandler;

    Button back;
    Button update;

    TextView bloodType;
    TextView hemoglobin;
    TextView platelet;
    TextView AST;
    TextView ALT;
    TextView creatinine;
    TextView urineProtein;
    TextView firstTrimester;
    TextView downSyndromeScreen;
    TextView secondTrimesterDownSyndromeScreen;
    TextView NIPT;
    TextView amniocentesis;
    TextView sickleCellScreen;
    TextView cysticFibrosisScreen;
    TextView HIV;
    TextView syphilis;
    TextView hepatitisBC;
    TextView gestationalDiabetesScreen;
    TextView GBS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);

        initView();
        addListener();
        initData();

    }

    private void initView()
    {
        labTitle=(Spinner) findViewById(R.id.labSp_title);

        labValues=(EditText) findViewById(R.id.labEt_values);

        updateLL=(LinearLayout) findViewById(R.id.labll_update);
        loadingLL=(LinearLayout) findViewById(R.id.labLoadll_update);

        labDAO=new LabDAO();

        mainHandler=new Handler(getMainLooper());

        back=(Button) findViewById(R.id.labBt_back);
        update=(Button) findViewById(R.id.labBt_update);

        bloodType=(TextView) findViewById(R.id.labTv_blDisplay);
        hemoglobin=(TextView) findViewById(R.id.labTv_HemDisplay);
        platelet=(TextView) findViewById(R.id.labTv_PalDisplay);
        AST=(TextView) findViewById(R.id.labTv_ASTDisplay);
        ALT=(TextView) findViewById(R.id.labTv_ALTDisplay);
        creatinine=(TextView) findViewById(R.id.labTv_CreDisplay);
        urineProtein=(TextView) findViewById(R.id.labTv_UPDisplay);
        firstTrimester=(TextView) findViewById(R.id.labTv_FTDisplay);
        downSyndromeScreen=(TextView) findViewById(R.id.labTv_DSSDisplay);
        secondTrimesterDownSyndromeScreen=(TextView) findViewById(R.id.labTv_STDSSDisplay);
        NIPT=(TextView) findViewById(R.id.labTv_NIPTDisplay);
        amniocentesis=(TextView) findViewById(R.id.labTv_AmnDisplay);
        sickleCellScreen=(TextView) findViewById(R.id.labTv_SCSDisplay);
        cysticFibrosisScreen=(TextView) findViewById(R.id.labTv_CFSDisplay);
        HIV=(TextView) findViewById(R.id.labTv_HIVDisplay);
        syphilis=(TextView) findViewById(R.id.labTv_SypDisplay);
        hepatitisBC=(TextView) findViewById(R.id.labTv_HBCDisplay);
        gestationalDiabetesScreen=(TextView) findViewById(R.id.labTv_GDSDisplay);
        GBS=(TextView) findViewById(R.id.labTv_GBSDisplay);

        inputData=getApplicationContext().getSharedPreferences("inputData", Context.MODE_PRIVATE);
    }

    private void addListener()
    {
     back.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             doBack();
         }
     });

     update.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             doUpdate(labTitle.getSelectedItem().toString());
         }
     });
    }

    private void initData()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean exsists=labDAO.checkExists(inputData.getInt("id",0));
                if(exsists==true) {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            loadingLL.setVisibility(View.VISIBLE);
                        }
                    });
                    Lab instance = labDAO.getLab(inputData.getInt("id", 0));
                    if(instance!=null){
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                if(instance.getBloodType()!=null) {
                                    bloodType.setText("Blood Type: " + instance.getBloodType() + " measure time: " + instance.getBtTime() + " \n");
                                }
                                if(instance.getHemoglobin()!=null) {
                                    hemoglobin.setText("Hemoglobin: " + instance.getHemoglobin() + " measure time: " + instance.getHemTime() + "\n");
                                }
                                if(instance.getPlatelet()!=null) {
                                    platelet.setText("Platelet: " + instance.getPlatelet() + " measure time:　" + instance.getPlaTime() + " \n");
                                }
                                if(instance.getAST()!=null) {
                                    AST.setText("AST: " + instance.getAST() + " measure time: " + instance.getASTTime() + " \n");
                                }
                                if(instance.getALT()!=null){
                                    ALT.setText("ALT: "+instance.getALT()+" measure time: "+instance.getALTTime()+" \n");
                                }
                                if(instance.getCreatinine()!=null){
                                    creatinine.setText("Creatinine: "+instance.getCreatinine()+" measure time: "+instance.getCreTime()+" \n");
                                }
                                if(instance.getUrineProtein()!=null){
                                    urineProtein.setText("Urine Protein: "+instance.getUrineProtein()+" measure time: "+instance.getUPTime()+" \n");
                                }
                                if(instance.getFirstTrimester()!=null){
                                    firstTrimester.setText("First Trimester: "+instance.getFirstTrimester()+" measure time: "+instance.getFTTime()+" \n");
                                }
                                if(instance.getDownSyndromeScreen()!=null){
                                    downSyndromeScreen.setText("Down Syndrome Screen: "+instance.getDownSyndromeScreen()+" measure time: "+instance.getDSSTime()+" \n");
                                }
                                if(instance.getSecondTrimesterDownSyndromeScreen()!=null)
                                {
                                    secondTrimesterDownSyndromeScreen.setText("Second Trimester Down Syndrome Screen: "+instance.getSecondTrimesterDownSyndromeScreen()+" measure time: "+instance.getSTDSSTime()+" \n");
                                }
                                if(instance.getNIPT()!=null){
                                    NIPT.setText("NIPT: "+instance.getNIPT()+" measure time: "+instance.getNIPTTime()+" \n");
                                }
                                if(instance.getAmniocentesis()!=null){
                                    amniocentesis.setText("Amniocentesis: "+instance.getAmniocentesis()+" measure time: "+instance.getAmnTime()+" \n");
                                }
                                if(instance.getSickleCellScreen()!=null){
                                    sickleCellScreen.setText("sickle Cell Screen: "+instance.getSickleCellScreen()+" measure time: "+instance.getSCSTime()+" \n");
                                }
                                if(instance.getCysticFibrosisScreen()!=null){
                                    cysticFibrosisScreen.setText("Cystic Fibrosis Screen: "+instance.getCysticFibrosisScreen()+" measure time: "+instance.getCFSTime()+" \n");
                                }
                                if(instance.getHIV()!=null){
                                    HIV.setText("HIV: "+instance.getHIV()+" measure time: "+instance.getHIVTime()+" \n");
                                }
                                if(instance.getSyphilis()!=null){
                                    syphilis.setText("Syphilis: "+instance.getSyphilis()+" measure time: "+instance.getSypTime()+" \n");
                                }
                                if(instance.getHepatitisBC()!=null){
                                    hepatitisBC.setText("Hepatitis B/C: "+instance.getHepatitisBC()+" measure time: "+instance.getHBCTime()+" \n");
                                }
                                if(instance.getGestationalDiabetesScreen()!=null){
                                    gestationalDiabetesScreen.setText("Gestational Diabetes Screen: "+instance.getGestationalDiabetesScreen()+" measure time: "+instance.getGDSTime()+" \n");
                                }
                                if(instance.getGBS()!=null){
                                    GBS.setText("GBS: "+instance.getGBS()+" measure time: "+instance.getGBSTime()+" \n");
                                }
                                loadingLL.setVisibility(View.GONE);
                            }
                        });
                    }
                }
            }
        }).start();
    }

    private void doUpdate(String title)
    {
        if(labValues.getText().toString().equals(""))
        {
            Toast.makeText(this,"Please input data value!!!",Toast.LENGTH_SHORT).show();
            labValues.requestFocus();
        }
        else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy MM dd HH:mm:ss");

            Date date = new Date(System.currentTimeMillis());

            final String currentTime = simpleDateFormat.format(date);

            Lab instance = new Lab();
            instance.setId(inputData.getInt("id", 0));

            /*
            switch (title) {
                case "blood type":
                    instance.setBloodType(labValues.getText().toString());
                    instance.setBtTime(currentTime);
                    break;

                case "Hemoglobin":
                    instance.setHemoglobin(labValues.getText().toString());
                    instance.setHemTime(currentTime);
                    break;

                case "platelet":
                    instance.setPlatelet(labValues.getText().toString());
                    instance.setPlaTime(currentTime);
                    break;

                case "AST":
                    instance.setAST(labValues.getText().toString());
                    instance.setASTTime(currentTime);
                    break;

                case "ALT":
                    instance.setALT(labValues.getText().toString());
                    instance.setALTTime(currentTime);
                    break;

                case "creatinine":
                    instance.setCreatinine(labValues.getText().toString());
                    instance.setCreTime(currentTime);
                    break;

                case "Urine Protein":
                    instance.setUrineProtein(labValues.getText().toString());
                    instance.setUPTime(currentTime);
                    break;

                case "first Trimester":
                    instance.setFirstTrimester(labValues.getText().toString());
                    instance.setFTTime(currentTime);
                    break;

                case "down Syndrome Screen":
                    instance.setDownSyndromeScreen(labValues.getText().toString());
                    instance.setDSSTime(currentTime);
                    break;

                case "second Trimester Down Syndrome Screen":
                    instance.setSecondTrimesterDownSyndromeScreen(labValues.getText().toString());
                    instance.setSTDSSTime(currentTime);
                    break;

                case "NIPT":
                    instance.setNIPT(labValues.getText().toString());
                    instance.setNIPTTime(currentTime);
                    break;

                case "amniocentesis":
                    instance.setAmniocentesis(labValues.getText().toString());
                    instance.setAmnTime(currentTime);
                    break;

                case "sickle Cell Screen":
                    instance.setSickleCellScreen(labValues.getText().toString());
                    instance.setSCSTime(currentTime);
                    break;

                case "cystic Fibrosis Screen":
                    instance.setCysticFibrosisScreen(labValues.getText().toString());
                    instance.setCFSTime(currentTime);
                    break;

                case "HIV":
                    instance.setHIV(labValues.getText().toString());
                    instance.setHIVTime(currentTime);
                    break;

                case "syphilis":
                    instance.setSyphilis(labValues.getText().toString());
                    instance.setSypTime(currentTime);
                    break;

                case "hepatitisBC":
                    instance.setHepatitisBC(labValues.getText().toString());
                    instance.setHBCTime(currentTime);
                    break;

                case "gestational Diabetes Screen":
                    instance.setGestationalDiabetesScreen(labValues.getText().toString());
                    instance.setGDSTime(currentTime);
                    break;

                case "GBS":
                    instance.setGBS(labValues.getText().toString());
                    instance.setGBSTime(currentTime);
                    break;

                default:
                    break;
            }
            */
            updateLL.setVisibility(View.VISIBLE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean exists=labDAO.checkExists(inputData.getInt("id",0));
                    if(exists==false) {
                        labDAO.addLab(instance);
                    }

                        boolean ok = labDAO.updateLab(inputData.getInt("id", 0), labTitle.getSelectedItem().toString(), labValues.getText().toString(), currentTime);
                        if (ok == true) {
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    initData();
                                    updateLL.setVisibility(View.GONE);
                                    Toast.makeText(LabActivity.this, "data update success!!!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    updateLL.setVisibility(View.GONE);
                                    Toast.makeText(LabActivity.this, "data update fail!!!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }


            }).start();
        }
    }

    private void doBack()
    {
        finish();
    }
}