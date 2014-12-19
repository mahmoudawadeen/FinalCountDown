package com.eventizer.activities;

import java.util.Currency;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.example.eventizer.R;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.eventizer.activities.base.BaseActivity;
import com.eventizer.model.Event;
import com.squareup.picasso.Picasso;
import com.eventizer.util.ApiRouter;


public class StreamActivity extends BaseActivity {
	private ArrayAdapter<Event> adpEvents;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stream);
//		System.out.println(getCurrentUser().getEmail());
		setUpViews();
//		TextView tx = (TextView)(findViewById(R.id.textView1));
//		tx.setText(getCurrentUser().getEmail());
//		
	}
	
	@Override
	protected void onResume() {
		super.onResume();

		refreshViews();
	}
	
	private void setUpViews() {
		ListView lstEvents = (ListView) findViewById(R.id.lst_events);
		adpEvents = new ArrayAdapter<Event>(this, 0) {
			private LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				final Event event = getItem(position);

				View view;
				if (convertView == null) {
					view = mInflater.inflate(R.layout.view_event, parent, false);
				} else {
					view = convertView;
				}

				TextView txtName = (TextView) view.findViewById(R.id.txt_name);
				txtName.setText(event.getTitle());

				ImageView imgImage = (ImageView) view.findViewById(R.id.img_image);
				Picasso.with(StreamActivity.this).load(event.getImage_url()).into(imgImage);
				
				txtName.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						EventActivity.setEvent(event);
						Intent intent = new Intent(StreamActivity.this, EventActivity.class);
				    	startActivity(intent);
					}
					
				});
/*
				Button btnBuy = (Button) view.findViewById(R.id.btn_buy);
				btnBuy.setEnabled(product.getStock() > 0);
				btnBuy.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						startProgress();
						
						ApiRouter.withToken(getCurrentUser().getToken()).patchProductBuy(product.getId(), new Callback<Response>() {
							@Override
							public void success(Response response, Response rawResponse) {
								Toast.makeText(ProductsActivity.this, "Bought: " + product.getName(), Toast.LENGTH_LONG).show();
								
								stopProgress();
								
								product.setStock(product.getStock() - 1);
								adpProducts.notifyDataSetChanged();
							}
							
							@Override
							public void failure(RetrofitError e) {
								displayError(e);
							}
						});
					}
					
				});
				*/
				return view;
			}
		};
		lstEvents.setAdapter(adpEvents);
	}
	
	protected void refreshViews() {
		super.refreshViews();
		
		adpEvents.clear();
		
		startProgress();
		
		ApiRouter.withoutToken().getEvents(new Callback<List<Event>>() {
			
			@Override
			public void success(List<Event> events, Response response) {
				// TODO Auto-generated method stub
				adpEvents.addAll(events);
				stopProgress();
				
			}
			
			@Override
			public void failure(RetrofitError e) {
				// TODO Auto-generated method stub
				displayError(e);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stream, menu);
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
	
	public void createEvent(View view) {
    	Intent intent = new Intent(this, CreateEventActivity.class);
    	startActivity(intent);
    }
	
	
	public void goToProfile(View view) {
    	Intent intent = new Intent(this, ProfileActivity.class);
    	startActivity(intent);
    }
	
	public void goToEvent(View view) {
    	Intent intent = new Intent(this, EventActivity.class);
    	startActivity(intent);
    }
	
	
	public void goToAllEvents(View view) {
    	Intent intent = new Intent(this, AllEventsActivity.class);
    	startActivity(intent);
    }
	
}
