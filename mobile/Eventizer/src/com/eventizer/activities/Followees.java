package com.eventizer.activities;

import com.example.eventizer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Followees extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_followees);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.followees, menu);
		return true;
	}
	public void checkProfileOthers(View view) {
    	Intent intent = new Intent(this, othersProfile.class);
    	startActivity(intent);
    }
	public void checkfriendProfile(View view) {
    	Intent intent = new Intent(this, FriendProfileActivity.class);
    	startActivity(intent);
    }
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
