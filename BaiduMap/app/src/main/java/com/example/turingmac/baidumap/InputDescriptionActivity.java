package com.example.turingmac.baidumap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputDescriptionActivity extends AppCompatActivity {

    EditText editTextInputDescription = null;
    Button buttonLocateDescription = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_description);

        editTextInputDescription = (EditText) this.findViewById(R.id.editTextInputDescription);
        buttonLocateDescription = (Button) this.findViewById(R.id.buttonLocateDescription);

        buttonLocateDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = editTextInputDescription.getText().toString();

                Intent intent = new Intent(InputDescriptionActivity.this, MainActivity.class);
                intent.putExtra("DES", description);
                startActivity(intent);

            }
        });
    }
}
