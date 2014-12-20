package com.eventizer.activities;

import org.json.JSONException;
import org.json.JSONObject;

import com.eventizer.activities.base.BaseActivity;
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

public class CreateEventActivity extends BaseActivity {

	private EditText eventTitle;
	private EditText eventLocation;
	private EditText startDate;
	private EditText endDate;
	int id = getCurrentUser().getId();
	JSONObject json = new JSONObject();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_event);
		setupViews();
	}

	private void setupViews() {
		eventTitle = (EditText) findViewById(R.id.eventName);
		eventLocation = (EditText) findViewById(R.id.EventLocationID);
		startDate = (EditText) findViewById(R.id.startDate);
		endDate = (EditText) findViewById(R.id.endDate);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_event, menu);
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

	public void createEvent(View view) throws JSONException{
		
//		if(isEmpty(eventLocation) || isEmpty(eventTitle) || isEmpty(startDate) || isEmpty(endDate)) {
//			return;
//		}
		
		json.put("title", eventTitle.getText().toString());
		json.put("location", eventLocation.getText().toString());
		json.put("start_date", startDate.getText().toString());
		json.put("end_date", endDate.getText().toString());
		json.put("organizer_id", id);
		
		
		
		PostRequest request = new PostRequest(
				ApiRouter.API_BASE_URL + "/events") {
			protected void onPostExecute(String response) {
				
				if (this.getStatusCode() == 201) {
					Toast.makeText(getApplicationContext(),
							"Event created Successfully!",
							Toast.LENGTH_LONG).show();
					goToStream(response);
				}
			}

		};
		request.setBody(json);
		request.execute();

		
	   	

	}
	
//	private boolean isEmpty(EditText editText) {
//		if (editText.getText().toString().length() == 0) {
//			editText.setError("This Field is Required");
//			return true;
//		}
//		editText.setError(null);
//		return false;
//	}
	
	public void goToStream(String response){
		Intent intent = new Intent(this, StreamActivity.class);
	   	startActivity(intent);
	    
	}
}
