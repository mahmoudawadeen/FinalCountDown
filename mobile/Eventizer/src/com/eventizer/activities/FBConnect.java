package com.eventizer.activities;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eventizer.R;
import com.facebook.Request;
import com.facebook.Request.GraphUserCallback;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphUser;

public class FBConnect extends FragmentActivity {
    private static final String TAG = "FacebookConnect";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.facebook_connect);


    if (Session.getActiveSession() == null
            || Session.getActiveSession().isClosed()) {
        Session.openActiveSession(this, true, new StatusCallback() {

            @SuppressWarnings("deprecation")
			@Override
            public void call(Session session, SessionState state,
                    Exception exception) {
                System.out.println("State= " + state);

                if (session.isOpened()) {
                    System.out.println("Token=" + session.getAccessToken());
                    Request.executeMeRequestAsync(session,
                            new GraphUserCallback() {
                                @Override
                                public void onCompleted(GraphUser user,
                                        Response response) {
                                    if (user != null) {
                                        System.out.println("User=" + user);

                                    }
                                    if (response != null) {
                                        System.out.println("Response="
                                                + response);
                                        GraphObject go  = response.getGraphObject();
                                        JSONObject  jso = go.getInnerJSONObject();
                                        try {
											Toast.makeText(FBConnect.this,
											        response.toString(),
											        Toast.LENGTH_LONG).show();
											((EditText)findViewById(R.id.editText1)).setText(jso.getString("first_name"));
										} catch (JSONException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
                                    }
                                }
                            });
                }
                if (exception != null) {
                    System.out.println("Some thing bad happened!");
                    exception.printStackTrace();
                }
            }
        });
    }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    Session.getActiveSession().onActivityResult(this, requestCode,
            resultCode, data);
    }

}