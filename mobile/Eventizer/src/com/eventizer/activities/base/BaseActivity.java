package com.eventizer.activities.base;

import com.eventizer.model.User;
import com.example.eventizer.R;
import com.example.eventizer.R.id;
import com.example.eventizer.R.layout;
import com.example.eventizer.R.menu;

//import eg.edu.guc.android.eshop.model.User;



import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

public class BaseActivity extends Activity {

	private static final String PREF_USER_ID = "PREF_USER_ID";
	private static final String PREF_USER_NAME = "PREF_USER_NAME";
	private static final String PREF_USER_EMAIL = "PREF_USER_EMAIL";
	private static final String PREF_USER_TOKEN = "PREF_USER_TOKEN";

	public static User currentUser;

	private int inProgress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
	}
	
	@Override
	protected void onResume() {
		super.onResume();

		invalidateOptionsMenu();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.base, menu);
		return true;
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
	
	protected boolean isRefreshable() {
		return true;
	}

	protected void refreshViews() {
	}
	
	protected void startProgress() {
		setProgressBarIndeterminateVisibility(true);
		inProgress++;
	}

	protected void stopProgress() {
		if (--inProgress == 0) {
			setProgressBarIndeterminateVisibility(false);
		}
	}

	protected void displayError(Exception e) {
		setProgressBarIndeterminateVisibility(false);
		Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT)
				.show();
	}
	protected void setCurrentUser(User user){
		currentUser=user;
	}

	protected User getCurrentUser(){
		return currentUser;
	}

}
