package com.eventizer.activities;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.eventizer.activities.base.BaseActivity;
import com.eventizer.model.User;
import com.eventizer.util.ApiRouter;
import com.example.eventizer.R;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends BaseActivity {
	int userId = getCurrentUser().getId();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		startProgress();
		setViewSetup();
		
	}

	private void setViewSetup() {
		ApiRouter.withoutToken().getUser(userId, new Callback<User>() {

			@Override
			public void failure(RetrofitError e) {
				// TODO Auto-generated method stub
				displayError(e); 
			}

			@Override
			public void success(User arg0, Response arg1) {
				// TODO Auto-generated method stub
				TextView userText = (TextView) (ProfileActivity.this.findViewById(R.id.username));
				userText.setText(currentUser.getUsername());
				
				userText = (TextView) (ProfileActivity.this.findViewById(R.id.userEmail));
				userText.setText(currentUser.getEmail());
				
				
				//@TODO: Missing image display
				
				//ImageView userimg = (ImageView) (ProfileActivity.this.findViewById(R.id.userImage));
				//Picasso.with(ProfileActivity.this).load(currentUser.getImage_url()).into(userimg);
				
				stopProgress();
			}
			
			
		});
		
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
