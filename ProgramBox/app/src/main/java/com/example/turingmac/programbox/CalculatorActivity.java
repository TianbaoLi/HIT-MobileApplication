package com.example.turingmac.programbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        Button buttonEqual = (Button) findViewById(R.id.buttonEqual);
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView textViewFormula, textViewResult;
                final com.example.turingmac.programbox.RealCalc realcalc = new com.example.turingmac.programbox.RealCalc();
                textViewFormula = (TextView) findViewById(R.id.textViewFormula);
                textViewResult = (TextView) findViewById(R.id.textViewResult);

                //textViewResult.setText(realcalc.calc(textViewFormula.getText().toString()));
                textViewResult.setText("" + NativeCalc.calc(textViewFormula.getText().toString() + "="));
            }
        });
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView textViewFormula;
                textViewFormula = (TextView) findViewById(R.id.textViewFormula);
                textViewFormula.setText(textViewFormula.getText().toString() + "1");
            }
        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView textViewFormula;
                textViewFormula = (TextView) findViewById(R.id.textViewFormula);
                textViewFormula.setText(textViewFormula.getText().toString() + "2");
            }
        });
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView textViewFormula;
                textViewFormula = (TextView) findViewById(R.id.textViewFormula);
                textViewFormula.setText(textViewFormula.getText().toString() + "3");
            }
        });
        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView textViewFormula;
                textViewFormula = (TextView) findViewById(R.id.textViewFormula);
                textViewFormula.setText(textViewFormula.getText().toString() + "4");
            }
        });
        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView textViewFormula;
                textViewFormula = (TextView) findViewById(R.id.textViewFormula);
                textViewFormula.setText(textViewFormula.getText().toString() + "5");
            }
        });
        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView textViewFormula;
                textViewFormula = (TextView) findViewById(R.id.textViewFormula);
                textViewFormula.setText(textViewFormula.getText().toString() + "6");
            }
        });
        Button button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView textViewFormula;
                textViewFormula = (TextView) findViewById(R.id.textViewFormula);
                textViewFormula.setText(textViewFormula.getText().toString() + "7");
            }
        });
        Button button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView textViewFormula;
                textViewFormula = (TextView) findViewById(R.id.textViewFormula);
                textViewFormula.setText(textViewFormula.getText().toString() + "8");
            }
        });
        Button button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView textViewFormula;
                textViewFormula = (TextView) findViewById(R.id.textViewFormula);
                textViewFormula.setText(textViewFormula.getText().toString() + "9");
            }
        });
        Button button0 = (Button) findViewById(R.id.button0);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView textViewFormula;
                textViewFormula = (TextView) findViewById(R.id.textViewFormula);
                textViewFormula.setText(textViewFormula.getText().toString() + "0");
            }
        });
        Button buttonPlus = (Button) findViewById(R.id.buttonPlus);
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView textViewFormula;
                textViewFormula = (TextView) findViewById(R.id.textViewFormula);
                textViewFormula.setText(textViewFormula.getText().toString() + "+");
            }
        });
        Button buttonSubtract = (Button) findViewById(R.id.buttonSubtract);
        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView textViewFormula;
                textViewFormula = (TextView) findViewById(R.id.textViewFormula);
                textViewFormula.setText(textViewFormula.getText().toString() + "-");
            }
        });
        Button buttonMulti = (Button) findViewById(R.id.buttonMulti);
        buttonMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView textViewFormula;
                textViewFormula = (TextView) findViewById(R.id.textViewFormula);
                textViewFormula.setText(textViewFormula.getText().toString() + "*");
            }
        });
        Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView textViewFormula;
                textViewFormula = (TextView) findViewById(R.id.textViewFormula);
                textViewFormula.setText(textViewFormula.getText().toString() + "/");
            }
        });
        Button buttonLeft = (Button) findViewById(R.id.buttonLeft);
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView textViewFormula;
                textViewFormula = (TextView) findViewById(R.id.textViewFormula);
                textViewFormula.setText(textViewFormula.getText().toString() + "(");
            }
        });
        Button buttonRight = (Button) findViewById(R.id.buttonRight);
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView textViewFormula;
                textViewFormula = (TextView) findViewById(R.id.textViewFormula);
                textViewFormula.setText(textViewFormula.getText().toString() + ")");
            }
        });
        Button buttonBACK = (Button) findViewById(R.id.buttonBACK);
        buttonBACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView textViewFormula;
                textViewFormula = (TextView) findViewById(R.id.textViewFormula);
                String s = textViewFormula.getText().toString();
                if(s.length()==0)
                    return;
                textViewFormula.setText(s.substring(0,s.length()-1));
            }
        });
        Button buttonCLR = (Button) findViewById(R.id.buttonCLR);
        buttonCLR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView textViewFormula, textViewResult;
                textViewFormula = (TextView) findViewById(R.id.textViewFormula);
                textViewResult = (TextView) findViewById(R.id.textViewResult);
                textViewFormula.setText("");
                textViewResult.setText("");
            }
        });
        Button button_ = (Button) findViewById(R.id.button_);
        button_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView textViewFormula;
                textViewFormula = (TextView) findViewById(R.id.textViewFormula);
                textViewFormula.setText(textViewFormula.getText().toString() + ".");
            }
        });
    }
}
