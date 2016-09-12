package com.infodat.zbarscannerexample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Principal extends Activity implements Response.Listener<JSONObject>, Response.ErrorListener{

    private static final String URL = "http://192.168.0.103/eps/index.php/services_rest/actualizar/codqr/c30fc304-03f3-5520-a0f3-84ebde03ca7d";
    private Button wsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Principal.this, MainActivity.class));
            }
        });

        findViewById(R.id.wsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue request = Volley.newRequestQueue(Principal.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, Principal.this, Principal.this);
                request.add(jsonObjectRequest);

            }
        });

    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        //Log.d("onErrorResponse", volleyError.toString());
        Toast.makeText(Principal.this, volleyError.toString(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            Log.d("onResponse()", response.toString(0));

            Toast.makeText(Principal.this, response.toString(0), Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
