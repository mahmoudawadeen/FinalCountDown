package com.eventizer.activities;

import org.json.JSONException;
import org.json.JSONObject;

import com.eventizer.requests.PostRequest;
import com.eventizer.util.ApiRouter;
import com.example.eventizer.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	private Button btnRegister;
	private Button btnLinkToHome;
	private EditText username;
	private EditText email;
	private EditText password;
	private EditText confirmPassword;
	private TextView registerErrorMsg;
	
	JSONObject json = new JSONObject();
	private boolean isDestroyed;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		setupViews();
	}
	
	protected void setupViews() {
		username = (EditText) findViewById(R.id.register_username);
		email = (EditText) findViewById(R.id.register_email);
		password = (EditText) findViewById(R.id.register_password);
		confirmPassword = (EditText) findViewById(R.id.register_confirm_password);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		btnLinkToHome = (Button) findViewById(R.id.btnLinkToSplash);
		
	}
	
	public void register(View view) {
		if(isEmpty(username) || isEmpty(email) || isEmpty(password) || isEmpty(confirmPassword)) {
			return;
		}
		try {
			if (!isValidEmail(email.getText().toString())) {
				Toast.makeText(getApplicationContext(),
						"Error, not a valid email", Toast.LENGTH_SHORT)
						.show();
				return;
			} else if (shortPassword(password.getText().toString())) {

					Toast.makeText(
							getApplicationContext(),
							"Password should be more than six characters",
							Toast.LENGTH_SHORT).show();
					return;

				}
				

				else if (!passwordsMatch(password.getText().toString(),
						confirmPassword.getText().toString())) {
					Toast.makeText(getApplicationContext(),
							"Passwords do not match",
							Toast.LENGTH_SHORT).show();
					return;
				}
				else {

				json.put("username", username.getText().toString());
				json.put("email", email.getText().toString());
				json.put("password", password.getText().toString());
				json.put("age", 21);
//				json.put("confirmPassword", confirmPassword.getText()
//						.toString());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		PostRequest request = new PostRequest(
				ApiRouter.API_BASE_URL + "/users") {
			protected void onPostExecute(String response) {
				if(isDestroyed){
					return;
				}
				if (this.getStatusCode() == 201) {
					Toast.makeText(getApplicationContext(),
							"Registered Successfully!",
							Toast.LENGTH_LONG).show();
					goToLogin(response);
				} else if(this.getStatusCode() == 401){
					Toast.makeText(getApplicationContext(),
							"Not unique username",
							Toast.LENGTH_SHORT).show();
				}
				
				else if(this.getStatusCode() == 402){
					Toast.makeText(getApplicationContext(),
							"Not unique email",
							Toast.LENGTH_SHORT).show();
				}

			}

		};
		request.setBody(json);
		request.execute();

	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}
	
	private boolean isEmpty(EditText editText) {
		if (editText.getText().toString().length() == 0) {
			editText.setError("This Field is Required");
			return true;
		}
		editText.setError(null);
		return false;
	}
	
	private boolean shortPassword(String password) {
		if (password.length() < 7) {
			return true;
		} else {
			return false;
		}
	}
	
	
	private boolean passwordsMatch(String password, String confirmPassword) {
		if (password.equals(confirmPassword)) {
			return true;
		} else
			return false;
	}
	
	private boolean isValidEmail(String email) {
		String regex = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
		return email.matches(regex);
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
	
	public void onPause(){
		super.onPause();
		isDestroyed = true;
	}
	
	public void goToLogin(String response) {
		startActivity(new Intent(this, LoginActivity.class));
		this.finish();
	}
	
}
