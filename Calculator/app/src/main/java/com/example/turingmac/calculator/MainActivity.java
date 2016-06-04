package com.example.turingmac.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import bsh.EvalError;
import bsh.Interpreter;

public class MainActivity extends AppCompatActivity {

    private String getRs(String exp){
        Interpreter bsh = new Interpreter();
        Number result = null;
        try {
            exp = filterExp(exp);
            result = (Number)bsh.eval(exp);
        } catch (EvalError e) {
            e.printStackTrace();
            return "Error";
        }
        return result.doubleValue()+"";
    }

    private String filterExp(String exp) {
        String num[] = exp.split("");
        String temp = null;
        int begin=0,end=0;
        for (int i = 1; i < num.length; i++) {
            temp = num[i];
            if(temp.matches("[+-/()*]")){
                if(temp.equals(".")) continue;
                end = i - 1;
                temp = exp.substring(begin, end);
                if(temp.trim().length() > 0 && temp.indexOf(".")<0)
                    num[i-1] = num[i-1]+".0";
                begin = end + 1;
            }
        }
        return Arrays.toString(num).replaceAll("[\\[\\], ]", "");
    }

    @Override
        Button buttonEqual = (Button) findViewById(R.id.buttonEqual);
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TextView textViewFormula, textViewResult;
                textViewFormula = (TextView) findViewById(R.id.textViewFormula);
                textViewResult = (TextView) findViewById(R.id.textViewResult);
                String s = getRs(textViewFormula.getText().toString());
                textViewResult.setText(s);
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
                TextView textViewFormula;
                textViewFormula = (TextView) findViewById(R.id.textViewFormula);
                textViewFormula.setText("");
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
