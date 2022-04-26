package com.example.javines_calculatorwage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtName, txtHours;
    RadioGroup employeeType;
    RadioButton btnSelected;
    Button calculate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        enableFullscreen();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txtName);
        txtHours = findViewById(R.id.txtHours);

        employeeType = findViewById(R.id.employeeType);

        calculate = findViewById(R.id.btnCal);

        calculate.setOnClickListener(this);
    }

    public void onClick(View v) {


        if (v.getId() == R.id.btnCal) {
            int selectedEmployeeType = employeeType.getCheckedRadioButtonId();

            btnSelected = findViewById(selectedEmployeeType);

            String type = btnSelected.getText().toString();

            String name = txtName.getText().toString();
            String totalHours = (txtHours.getText().toString());

            Intent intent = new Intent(this, Display.class);

            intent.putExtra("type", type);
            intent.putExtra("empName", name);
            intent.putExtra("hours", totalHours);
            startActivity(intent);

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