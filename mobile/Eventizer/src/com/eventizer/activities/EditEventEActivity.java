package com.eventizer.activities;

import org.json.JSONException;
import org.json.JSONObject;

import com.eventizer.activities.base.BaseActivity;
import com.eventizer.requests.PutRequest;
import com.eventizer.util.ApiRouter;
import com.example.eventizer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditEventEActivity extends BaseActivity {
	private TextView eventTitle;
	private TextView eventLocation;
	private TextView eventStartDate;
	private TextView eventEndDate;
	JSONObject json = new JSONObject();
	int eventId = this.getEvent().getId();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_event_e);
		setupViews();
		
	}

	private void setupViews() {
		eventTitle = (EditText) findViewById(R.id.eventName);
		eventLocation = (EditText) findViewById(R.id.eventLocation);
		eventStartDate = (EditText) findViewById(R.id.startDate);
		eventEndDate = (EditText) findViewById(R.id.endDate);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_event_e, menu);
		return true;
	}
	public void editEvent(View view) throws JSONException {
		
		if(!eventTitle.equals("") || eventTitle != null){
			json.put("title", eventTitle.getText().toString());
		}
		
		if(!eventStartDate.equals("") || eventStartDate != null){
			json.put("start_date", eventStartDate.getText().toString());
		}
		
		if(!eventEndDate.equals("") || eventEndDate != null){
			json.put("end_date", eventEndDate.getText().toString());
		}
		
		if(!eventLocation.equals("") || eventLocation != null){
			json.put("location", eventLocation.getText().toString());
		}
		
		PutRequest request = new PutRequest(ApiRouter.API_BASE_URL + "/events/"+ eventId) {
			protected void onPutExecute(String response){
				if (this.getStatusCode() == 20) {
					Toast.makeText(getApplicationContext(),
							"Event Edited Successfully",
							Toast.LENGTH_LONG).show();
					goToLogin(response);
				}
			}

			
		};
		
		request.setBody(json);
		request.execute();
		
    	
    }
	
	private void goToLogin(String response) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, StreamActivity.class);
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
