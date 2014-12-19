package com.eventizer.activities;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.eventizer.activities.base.BaseActivity;
import com.eventizer.model.User;
import com.eventizer.util.ApiRouter;
import com.example.eventizer.R;

public class LoginActivity extends BaseActivity {

	private EditText username;
	private EditText password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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

	public void cancel(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		this.finish();
	}

	public void login(View view) {
		Context context = getApplicationContext();
		CharSequence text = "Login Button";
		int duration = Toast.LENGTH_SHORT;

		username = (EditText) findViewById(R.id.login_username);
		password = (EditText) findViewById(R.id.login_password);

		if (username.length() == 0 && password.length() == 0) {
			Toast toast = Toast.makeText(context, "Empty Username or password",
					duration);
			toast.show();

		} else {
			System.out.println(password.toString());
			ApiRouter.withoutToken().login(username.getText().toString(),password.getText().toString(), new Callback<User>(){
				@Override
				public void success(User user, Response response) {
					setCurrentUser(user);
					Intent intent = new Intent(LoginActivity.this, StreamActivity.class);
					startActivity(intent);
					LoginActivity.this.finish();
				}
				
				@Override
				public void failure(RetrofitError e) {
					// TODO Auto-generated method stub
					displayError(e);
					
				}
			});
			
		}
	}
}
