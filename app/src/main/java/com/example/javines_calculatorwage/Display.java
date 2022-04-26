package com.example.javines_calculatorwage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Display extends AppCompatActivity implements View.OnClickListener {

    TextView txtName;
    TextView txtType;
    TextView txtHours;
    TextView txtTotalWage;
    TextView txtRegularWage;
    TextView txtOTWage;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableFullscreen();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_display);

        txtName = findViewById(R.id.txtEmpName);
        txtType = findViewById(R.id.txtEmpType);
        txtHours = findViewById(R.id.txtHR);
        txtTotalWage = findViewById(R.id.txtTW);
        txtRegularWage = findViewById(R.id.txtRegW);
        txtOTWage = findViewById(R.id.txtOT);

        Button btnback = findViewById(R.id.btnback);
        btnback.setOnClickListener(this);


        Intent i = getIntent();

        String EmployeeType = i.getStringExtra("type");
        String EmployeeName = i.getStringExtra("empName");
        Double EmployeeHours = Double.parseDouble(i.getStringExtra("hours"));

        txtName.setText("Name: " + EmployeeName);
        txtType.setText("Type: " + EmployeeType);
        txtHours.setText(String.valueOf(EmployeeHours));
        calcWage(EmployeeType, EmployeeHours, txtTotalWage, txtRegularWage);

    }

    @SuppressLint("SetTextI18n")
    public void calcWage(String employeeType, Double employeeHours, TextView txtTotalWage, TextView txtRegularWage) {
        double totalWage = 0.0;
        double totalOTWage = 0.0;

        //Overtime functions
        if (employeeHours > 8.0) {

            if (employeeType.equals("Full-time")) {
                totalWage = 800 + (115 * (employeeHours - 8.0));
                totalOTWage = (employeeHours - 8.0) * 115;
                txtTotalWage.setText("₱" + totalWage);
                txtRegularWage.setText("₱" + 800);
                txtOTWage.setText("₱" + totalOTWage);

            } else if (employeeType.equals("Part-time")) {
                totalWage = 600 + (90 * (employeeHours - 8.0));
                totalOTWage = (employeeHours - 8.0) * 90;
                txtTotalWage.setText("₱" + totalWage);
                txtRegularWage.setText("₱" + 600);
                txtOTWage.setText("₱" + totalOTWage);
            }
            if (employeeType.equals("Probationary ")) {
                totalWage = 720 + (100 * (employeeHours - 8.0));
                totalOTWage = (employeeHours - 8.0) * 100;
                txtTotalWage.setText("₱"+ totalWage);
                txtRegularWage.setText("₱" + 720);
                txtOTWage.setText("₱" + totalOTWage);
            }
        }
            //1-8 hours function
            else {
                if (employeeType.equals("Full-time")) {
                    totalWage = employeeHours * 100;
                    txtTotalWage.setText("₱" + totalWage);
                    txtRegularWage.setText("₱" + totalWage);
                    txtOTWage.setText("No Overtime added");
                }
                if (employeeType.equals("Part-time")) {
                    totalWage = employeeHours * 75;
                    txtTotalWage.setText("₱" + totalWage);
                    txtRegularWage.setText("₱" + totalWage);
                    txtOTWage.setText("No Overtime added");
                }
                if (employeeType.equals("Probationary ")) {
                    totalWage = employeeHours * 90;
                    txtTotalWage.setText("₱" + totalWage);
                    txtRegularWage.setText("₱" + totalWage);
                    txtOTWage.setText("No Overtime added");
                }
            }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnback) {
            startActivity(new Intent(Display.this, MainActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }
    private void enableFullscreen() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );
    }
}
//Wage Calculator
// Regular Employee:
// 1-8 hours(regular work time): 100 pesos per hour
// overtime(calculated each hour after the 8th work hour): 115 pesos per hour
// Probationary Employee:
// 1-8 hours: 90 pesos per hour
// overtime: 100 pesos per hour
// 90 x 8 = 720 pesos for that day
// 720 + (3 * 100) = 1020 for that day
// Part-time workers:
// 1-8 hours: 75 pesos per hour
// overtime: 90 pesos per hour
//Inputs:
// Name
// Employee type
// how many hours you have worked for that day
//Outputs:
// Display:
//      Total Wage Received
//      Total OT wage (if available)
//      Total Regular wage
//      hours rendered
//      hours OT (if available)

