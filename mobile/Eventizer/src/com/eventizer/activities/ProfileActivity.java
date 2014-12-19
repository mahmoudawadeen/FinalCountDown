package com.eventizer.activities;

import com.example.eventizer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ProfileActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}
	public void editProfile(View view) {
    	Intent intent = new Intent(this, EditProfileActivity.class);
    	startActivity(intent);
    }

	public void goNot(View view){
		Intent intent = new Intent(this, NotificationsActivity.class);
    	startActivity(intent);
	}

	public void checkFollowers(View view) {
    	Intent intent = new Intent(this, Followers.class);
    	startActivity(intent);
    }
	public void checkFollowees(View view) {
    	Intent intent = new Intent(this, Followees.class);
    	startActivity(intent);
    }


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
