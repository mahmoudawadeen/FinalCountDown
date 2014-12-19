package com.eventizer.activities;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.eventizer.activities.base.BaseActivity;
import com.eventizer.model.Event;
import com.eventizer.model.User;
import com.eventizer.util.ApiRouter;
import com.example.eventizer.R;



public class EventActivity extends BaseActivity {
	private static Event event;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);
		startProgress();
		setOrg();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event, menu);
		return true;
	}

	public void editEvent(View view) {
		Intent intent = new Intent(this, EditEventEActivity.class);
		startActivity(intent);
	}

	public void checkPosts(View view) {
		EventPostsActivity.setEvent(event);
		Intent intent = new Intent(this, EventPostsActivity.class);
		startActivity(intent);
		finish();
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

	public static void setEvent(Event e) {
		event = e;
	}
	public void setOrg(){
		ApiRouter.withoutToken().getUser(event.getOrganizer_id(), new Callback<User>() {
			@Override
			public void success(User user, Response response) { 
				event.setOrganizer(user);
				TextView eventText = (TextView) (EventActivity.this.findViewById(R.id.textView1));
				eventText.setText(event.getTitle());
				eventText = (TextView) (EventActivity.this.findViewById(R.id.textView6));
				eventText.setText(event.getCategory());
				eventText = (TextView) (EventActivity.this.findViewById(R.id.textView7));
				eventText.setText(event.getOrganizer().getUsername());
				eventText = (TextView) (EventActivity.this.findViewById(R.id.textView9));
				eventText.setText(event.getLocation());
				eventText = (TextView) (EventActivity.this.findViewById(R.id.textView10));
				eventText.setText(event.getStart_date());
				eventText = (TextView) (EventActivity.this.findViewById(R.id.textView11));
				eventText.setText(event.getEnd_date());
				if(getCurrentUser()!=null && getCurrentUser().getId()==user.getId()){
					EventActivity.this.findViewById(R.id.create_button).setVisibility(View.VISIBLE);
				}
				stopProgress();
			}
			@Override
			public void failure(RetrofitError e) {
				displayError(e); 
			}
		});
	}
}
