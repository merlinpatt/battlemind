package com.googlehack.battlemind;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class PassActivity extends Activity {
	private EditText passInput;
	private final String GAMELEVEL= "GAMELEVEL";
	private Intent i;
	private Intent i2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_pass);
	}
	
	public void playClicked(View view) {
		/* Button playButton = (Button) findViewById(R.id.button1);
	     playButton.setOnClickListener(new View.OnClickListener() {
	     	public void onClick(View view) {
	     		SharedPreferences settings = getSharedPreferences("GLOBAL_SETTINGS", 0);
	     	//	SharedPreferences.Editor editor = settings.edit();
	     		
	     		EditText passField = (EditText) findViewById(R.id.editText1);
	             String password = passField.getText().toString();
	            // editor.putString("password", password);
	            // editor.commit();
	           //  finish();
	     	}
	     });*/
		i = getIntent();
		i2 = new Intent(this, BattleActivity.class);
		i2.putExtra(GAMELEVEL, i.getIntExtra(MenuActivity.GAMEMODE, 0)); //Sends game level to new activity
		EditText passField = (EditText) findViewById(R.id.input_pass);
        String password = passField.getText().toString();
        i2.putExtra("password", password); //Sending password to new activity
		startActivity(i2);
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pass, menu);
		return true;
	}
	

}
