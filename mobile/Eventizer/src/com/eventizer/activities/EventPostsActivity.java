package com.eventizer.activities;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.eventizer.activities.base.BaseActivity;
import com.eventizer.model.Comment;
import com.eventizer.model.Event;
import com.eventizer.model.Post;
import com.eventizer.model.User;
import com.eventizer.util.ApiRouter;
import com.example.eventizer.R;
import com.google.gson.internal.LinkedTreeMap;

public class EventPostsActivity extends BaseActivity {
	private static Event event;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_posts);
		ApiRouter.withoutToken().getEventPosts(event.getId(),
				new Callback<List<Object>>() {

					@Override
					public void failure(RetrofitError arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void success(List<Object> arg0, Response arg1) {

						List<LinkedTreeMap<Object, Object>> postsLtm = ((List<LinkedTreeMap<Object, Object>>) arg0
								.get(0));
						List<List<LinkedTreeMap<Object, Object>>> commentsLtm = ((List<List<LinkedTreeMap<Object, Object>>>) arg0
								.get(1));
						List<LinkedTreeMap<Object, Object>> postersLtm = ((List<LinkedTreeMap<Object, Object>>) arg0
								.get(2));
						ArrayList<Post> posts = new ArrayList<Post>();
						for (LinkedTreeMap<Object, Object> ltm : postsLtm) {
							Post p = new Post();
							if (ltm.get("content") != null)
								p.setContent(ltm.get("content").toString());
							p.setEvent_id((int) ((Double) ltm.get("event_id"))
									.intValue());
							p.setId((int) ((Double) ltm.get("id")).intValue());
							p.setPoster_id((int) ((Double) ltm.get("poster_id"))
									.intValue());
							posts.add(p);
						}
						ArrayList<Comment> comments = new ArrayList<Comment>();
						for (List<LinkedTreeMap<Object, Object>> x : commentsLtm)
							for (LinkedTreeMap<Object, Object> ltm : x) {
								Comment c = new Comment();
								c.setCommenter_id((int) ((Double) ltm
										.get("commenter_id")).intValue());
								if (ltm.get("content") != null)
									c.setContent(ltm.get("content").toString());
								c.setId((int) ((Double) ltm.get("id"))
										.intValue());
								c.setPost_id((int) ((Double) ltm.get("post_id"))
										.intValue());
								comments.add(c);
							}
						ArrayList<User> posters = new ArrayList<User>();
						for (LinkedTreeMap<Object, Object> us : postersLtm) {
							User u = new User();
							if (us.get("age") != null)
								u.setAge((int) ((Double) us.get("age"))
										.intValue());
							u.setEmail(us.get("email").toString());
							u.setId((int) ((Double) us.get("id")).intValue());
							if (us.get("image_url") != null)
								u.setImage_url(us.get("image_url").toString());
							u.setPassword(us.get("password").toString());
							u.setUsername(us.get("username").toString());
							posters.add(u);
						}
						for (int i = 0; i < posts.size(); i++) {
							LayoutInflater inflater = (LayoutInflater) EventPostsActivity.this
									.getLayoutInflater();
							View rl =  inflater
									.inflate(R.layout.post, null);
							((TextView) rl.findViewById(R.id.textView1))
									.setText(posters.get(i).getUsername()
											+ " said "
											+ posts.get(i).getContent());

							((TextView) rl.findViewById(R.id.textView2))
									.setText(comments.get(0).getContent());
							((LinearLayout) EventPostsActivity.this
									.findViewById(R.id.linear1)).addView(rl);
						}

					}

				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_posts, menu);
		return true;
	}

	public void peopleWhoLike(View view) {
		Intent intent = new Intent(this, LikesActivity.class);
		startActivity(intent);
	}

	public void backToEvent(View view) {
		Intent intent = new Intent(this, EventActivity.class);
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

	public static void setEvent(Event eve) {
		event = eve;
	}
}
