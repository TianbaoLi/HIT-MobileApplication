package com.example.turingmac.programbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputLongLatiActivity extends AppCompatActivity {

    EditText editTextInputLong = null;
    EditText editTextInputLati = null;
    Button buttonLocateLangLati = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_long_lati);

        editTextInputLong = (EditText) this.findViewById(R.id.editTextInputLong);
        editTextInputLati = (EditText) this.findViewById(R.id.editTextInputLati);
        buttonLocateLangLati = (Button) this.findViewById(R.id.buttonLocateLongLati);


        buttonLocateLangLati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String longitudeStr = editTextInputLong.getText().toString();
                String latitudeStr = editTextInputLati.getText().toString();
                try
                {
                    final double longitude = Double.parseDouble(longitudeStr);
                    final double latitude = Double.parseDouble(latitudeStr);

                    Intent intent = new Intent(InputLongLatiActivity.this, BaiduMapActivity.class);
                    intent.putExtra("POS", Double.toString(latitude) + " " + Double.toString(longitude));
                    startActivity(intent);
                }
                catch(Exception e)
                {

                }
            }

        });
    }

}
