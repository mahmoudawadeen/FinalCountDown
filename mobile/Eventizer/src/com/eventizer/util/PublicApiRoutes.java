package com.eventizer.util;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

import com.eventizer.model.Event;
import com.eventizer.model.Post;
import com.eventizer.model.User;


public interface PublicApiRoutes {
	@POST("/sessions")
	@FormUrlEncoded
	void login(@Field("session[email]") String email,
			@Field("session[password]") String password,
			Callback<User> callback);
	
	@GET("/events")
	void getEvents(Callback<List<Event>> callback);
	
	@POST("/users")
	void createUser(Callback<List<User>> callback);
	
	@GET("/users/{id}")
	void getUser(@Path("id") long id,Callback<User> callback);
	
	@GET("/events/{eventId}/posts")
	void getEventPosts(@Path("eventId")long id , Callback<List<Object>> callback);
}

