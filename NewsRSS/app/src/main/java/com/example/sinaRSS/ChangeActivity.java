package com.example.sinaRSS;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ChangeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change);
		
		RadioGroup m_RadioGroup;
        final RadioButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15;
        
        m_RadioGroup = (RadioGroup) findViewById(R.id.typegroup);

		b1 = (RadioButton) findViewById(R.id.radioButtonToutiao);
		b2 = (RadioButton) findViewById(R.id.radioButtonQiche);
		b3 = (RadioButton) findViewById(R.id.radioButtonYule);
		b4 = (RadioButton) findViewById(R.id.radioButtonTiyu);
		b5 = (RadioButton) findViewById(R.id.radioButtonCaijing);
		b6 = (RadioButton) findViewById(R.id.radioButtonKeji);
		b7 = (RadioButton) findViewById(R.id.radioButtonGaoxiao);
		b8 = (RadioButton) findViewById(R.id.radioButtonJingxuan);
		b9 = (RadioButton) findViewById(R.id.radioButtonQiqu);
		b10 = (RadioButton) findViewById(R.id.radioButtonMingxing);
		b11 = (RadioButton) findViewById(R.id.radioButtonJingji);
		b12 = (RadioButton) findViewById(R.id.radioButtonShipin);
		b13 = (RadioButton) findViewById(R.id.radioButtonZhenjing);
		b14 = (RadioButton) findViewById(R.id.radioButtonNuanxin);
		b15 = (RadioButton) findViewById(R.id.radioButtonBagua);
        
        m_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {  
            @Override  
            public void onCheckedChanged(RadioGroup group, int checkedId)  
            {  
                // TODO Auto-generated method stub  
            	int index=1;
            	if(checkedId==b1.getId())
            		index=1;
            	else if(checkedId==b2.getId())
            		index=2;
            	else if(checkedId==b3.getId())
            		index=3;
            	else if(checkedId==b4.getId())
            		index=4;
            	else if(checkedId==b5.getId())
            		index=5;
            	else if(checkedId==b6.getId())
            		index=6;
            	else if(checkedId==b7.getId())
            		index=7;
				else if(checkedId==b8.getId())
					index=8;
				else if(checkedId==b9.getId())
					index=9;
				else if(checkedId==b10.getId())
					index=10;
				else if(checkedId==b11.getId())
					index=11;
				else if(checkedId==b12.getId())
					index=12;
				else if(checkedId==b13.getId())
					index=13;
				else if(checkedId==b14.getId())
					index=14;
				else if(checkedId==b15.getId())
					index=15;
            	NetworkAsyncTask.setUrl(index);
                
            	//finish();
            	Intent intent = new Intent();
				intent.setClass(ChangeActivity.this,MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
            	
            }  
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change, menu);
		return true;
	}

}
